package controller;

import com.jfoenix.controls.JFXComboBox;
import dto.cartItems;
import dto.orderDetails;
import dto.orders;
import dto.productIDAndQty;
import entity.orderDetailsEntity;
import entity.productEntity;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import service.custom.orderDetailsService;
import service.custom.orderService;
import service.custom.productService;
import service.serviceFactory;
import util.ServiceType;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class manageOrderFormController implements Initializable {

    public Label lblNetTotal;
    public JFXComboBox cmbPaymentType;
    public TextField txtAddingQty;
    public TableColumn colProductID;
    @FXML
    private JFXComboBox<Integer> cmbProductID;

    @FXML
    private TableColumn<?, ?> colProductName;

    @FXML
    private TableColumn<?, ?> colQty;

    @FXML
    private TableColumn<?, ?> colTotal;

    @FXML
    private TableColumn<?, ?> colUnitPrice;

    @FXML
    private Label lblAvailableQty;

    @FXML
    private Label lblDate;

    @FXML
    private Label lblTime;

    @FXML
    private TableView<cartItems> tblCart;

    @FXML
    private TextField txtCustomerEmail;

    @FXML
    private TextField txtCustomerName;

    @FXML
    private TextField txtPassword;

    @FXML
    private TextField txtProductName;
    ObservableList<cartItems> cartitemlist = FXCollections.observableArrayList();
    productService service = serviceFactory.getInstance().getServiceType(ServiceType.product);
    ObservableList<productEntity> productEntityObservableList=service.getAll();
    @FXML
    void btnAddToCartOnAction(ActionEvent event) {
        colProductName.setCellValueFactory(new PropertyValueFactory<>("productName"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colTotal.setCellValueFactory(new PropertyValueFactory<>("total"));
        colProductID.setCellValueFactory(new PropertyValueFactory<>("productID"));

        ObservableList<cartItems> itemlist = tblCart.getItems();
        boolean isincart=false;
        int index=1;



        for(cartItems c:itemlist)
        {
            if(cmbProductID.getValue()==c.getProductID())
            {
                isincart=true;
                index=itemlist.indexOf(c);
            }
        }

        if(isincart)
        {
            itemlist.get(index).setQty(Integer.parseInt(txtAddingQty.getText())+itemlist.get(index).getQty());
            itemlist.get(index).setTotal(itemlist.get(index).getUnitPrice()*itemlist.get(index).getQty());
            cartitemlist=itemlist;
            tblCart.refresh();
            tblCart.setItems(cartitemlist);


        }

        else
        {
            productEntityObservableList.forEach(productEntity -> {
                if(productEntity.getProductID()==cmbProductID.getValue())
                {
                    cartitemlist.add(new cartItems(productEntity.getProductName(),productEntity.getUnitPrice(),Integer.parseInt(txtAddingQty.getText()),(productEntity.getUnitPrice()*Integer.parseInt(txtAddingQty.getText())),productEntity.getProductID()));
                }
            });
            tblCart.setItems(cartitemlist);


        }

// set Net balance

        double tot=0;

        for(cartItems c :cartitemlist)
        {
            tot+=c.getTotal();
        }

        lblNetTotal.setText(tot+"");

    }

    @FXML
    void btnHomeOnAction(ActionEvent event) {

        Stage currentStage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        currentStage.close();

        try {

            Stage stage = new Stage();
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/adminDashboardForm.fxml"))));
            stage.show();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    void btnPlaceOrderOnAction(ActionEvent event) {

        //save data in order table

        orderService service = serviceFactory.getInstance().getServiceType(ServiceType.order);
        boolean isOrderAdded =service.addOrder(new orders(Double.parseDouble(lblNetTotal.getText()),cmbPaymentType.getValue().toString(),LocalDate.now(),"notReturned",1,txtCustomerName.getText(),txtCustomerEmail.getText()));

        //get orderID
        int orderID=service.getOrderID();


        //save data in orderdetails table
        ObservableList<cartItems> cartitemlist = tblCart.getItems();
        ObservableList<orderDetailsEntity> orderDetailsObservableList = FXCollections.observableArrayList();

        cartitemlist.forEach(cartItems -> {
            orderDetailsObservableList.add(new orderDetailsEntity(orderID,cartItems.getProductID(),cartItems.getQty()));
        });

        orderDetailsService ser = serviceFactory.getInstance().getServiceType(ServiceType.orderdetails);
        boolean isOrderDetailsAdded=ser.addOrderDetails(orderDetailsObservableList);
        //update stock in product table

        productService sr = serviceFactory.getInstance().getServiceType(ServiceType.product);
        boolean isPQtyupdated=sr.updateProductQty(orderDetailsObservableList);

        if(isOrderAdded & isOrderDetailsAdded & isPQtyupdated)
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Order Added Successfully");

            // Show the alert
            alert.showAndWait();
            reload(event);
        }


    }

    private void reload(ActionEvent event) {
        Stage currentStage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        currentStage.close();

        try {

            Stage stage = new Stage();
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/manageOrderForm.fxml"))));
            stage.show();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnReturnOrderOnAction(ActionEvent event) {
        Stage currentStage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        currentStage.close();

        try {

            Stage stage = new Stage();
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/returnOrderForm.fxml"))));
            stage.show();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadProductIDs();
        loadPaymentTypes();
        cmbProductID.setOnAction(actionEvent -> {
            loadproductDetails();
        });
    }

    private void loadproductDetails() {
        productService service = serviceFactory.getInstance().getServiceType(ServiceType.product);
        ObservableList<productEntity> productEntityObservableList=service.getAll();

        productEntityObservableList.forEach(productEntity -> {
            if(productEntity.getProductID()==cmbProductID.getValue())
            {
                txtProductName.setText(productEntity.getProductName());
                lblAvailableQty.setText(productEntity.getAvailableQty()+"");

            }
        });
    }

    private void loadProductIDs() {
        productService service = serviceFactory.getInstance().getServiceType(ServiceType.product);
        ObservableList<Integer> productIDs =service.getProductIDs();
        cmbProductID.setItems(productIDs);
    }

    private void loadPaymentTypes() {
        ObservableList<String> paymentTypeList = FXCollections.observableArrayList();
        paymentTypeList.add("Cash");
        paymentTypeList.add("Card");

        cmbPaymentType.setItems(paymentTypeList);

    }


}
