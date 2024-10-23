package controller;

import com.jfoenix.controls.JFXComboBox;
import dto.product;
import dto.supplier;
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
import service.custom.productService;
import service.custom.supplierService;
import service.serviceFactory;
import util.ServiceType;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class manageProductFormController implements Initializable {


    public JFXComboBox cmbSupplierID;

    public JFXComboBox cmbProductID;

    @FXML
    private JFXComboBox<String> cmbSize;

    @FXML
    private JFXComboBox<String> cmbType;

    @FXML
    private TableColumn<?, ?> colAvailableQty;

    @FXML
    private TableColumn<?, ?> colID;

    @FXML
    private TableColumn<?, ?> colImage;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colPrice;

    @FXML
    private TableColumn<?, ?> colSize;

    @FXML
    private Label lblAvailableQty;

    @FXML
    private Label lblDate;

    @FXML
    private Label lblTime;

    @FXML
    private TableView<productEntity> tblProduct;

    @FXML
    private TextField txtAddingQty;

    @FXML
    private TextField txtPrice;

    @FXML
    private TextField txtProductName;

    @FXML
    private TextField txtSupplier;

    @FXML
    void btnAddOnAction(ActionEvent event) {
        productService service = serviceFactory.getInstance().getServiceType(ServiceType.product);
        boolean isAdded =service.addProduct(new product(txtProductName.getText(),cmbSize.getValue(),Double.parseDouble(txtPrice.getText()),cmbType.getValue(),Integer.parseInt(txtAddingQty.getText()),Integer.parseInt(cmbSupplierID.getValue().toString())));
        if(isAdded)
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Product Added!");

            // Show the alert
            alert.showAndWait();
            reload(event);
        }

        else
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Supplier Not Added!");

            // Show the alert
            alert.showAndWait();
        }

    }

    private void reload(ActionEvent event) {
        Stage currentStage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        currentStage.close();

        try {

            Stage stage = new Stage();
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/manageProductForm.fxml"))));
            stage.show();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        productService service = serviceFactory.getInstance().getServiceType(ServiceType.product);
        try
        {
            boolean isDeleted=service.deleteProduct(Integer.parseInt(cmbProductID.getValue().toString()));
            if (isDeleted)
            {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Dialog");
                alert.setHeaderText(null);
                alert.setContentText("Product Deleted!");

                // Show the alert
                alert.showAndWait();
                reload(event);
            }

            else
            {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Information Dialog");
                alert.setHeaderText(null);
                alert.setContentText("Product Not Deleted!");

                // Show the alert
                alert.showAndWait();
            }
        }

        catch (NullPointerException ex)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Please Select Product ID!");

            // Show the alert
            alert.showAndWait();
        }

    }

    @FXML
    void btnGentsOnAction(ActionEvent event) {
        ObservableList<productEntity> allproductlist = loadProductTableData();
        ObservableList<productEntity> gentsproductlist = FXCollections.observableArrayList();

        allproductlist.forEach(product->{
            if (product.getCategory().equals("Gents"))
            {
                gentsproductlist.add(product);
            }
        });

        tblProduct.setItems(gentsproductlist);


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
    void btnKidsOnAction(ActionEvent event) {
        ObservableList<productEntity> allproductlist = loadProductTableData();
        ObservableList<productEntity> kidssproductlist = FXCollections.observableArrayList();

        allproductlist.forEach(product->{
            if (product.getCategory().equals("Kids"))
            {
                kidssproductlist.add(product);
            }
        });

        tblProduct.setItems(kidssproductlist);

    }

    @FXML
    void btnLadiesOnAction(ActionEvent event) {
        ObservableList<productEntity> allproductlist = loadProductTableData();
        ObservableList<productEntity> ladiessproductlist = FXCollections.observableArrayList();

        allproductlist.forEach(product->{
            if (product.getCategory().equals("Ladies"))
            {
                ladiessproductlist.add(product);
            }
        });

        tblProduct.setItems(ladiessproductlist);


    }

    @FXML
    void btnSearchOnAction(ActionEvent event) {
        productService service = serviceFactory.getInstance().getServiceType(ServiceType.product);
        ObservableList<productEntity> productEntityObservableList=service.getAll();
        productEntityObservableList.forEach(entity->{
            if(Integer.parseInt(cmbProductID.getValue().toString())==entity.getProductID())
            {
                txtProductName.setText(entity.getProductName());
                cmbSize.setValue(entity.getSize());
                txtPrice.setText(entity.getUnitPrice()+"");
                cmbType.setValue(entity.getCategory());
                lblAvailableQty.setText(entity.getAvailableQty()+"");
                cmbSupplierID.setValue((Object)entity.getSupplierID());
                txtAddingQty.setText(entity.getAvailableQty()+"");

            }

        });

    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        productService service = serviceFactory.getInstance().getServiceType(ServiceType.product);
        try
        {
            boolean isUpdated=service.updateProduct(new product(txtProductName.getText(),cmbSize.getValue(),Double.parseDouble(txtPrice.getText()),cmbType.getValue(),Integer.parseInt(txtAddingQty.getText()),Integer.parseInt(cmbSupplierID.getValue().toString())),Integer.parseInt(cmbProductID.getValue().toString()));
            if(isUpdated)
            {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Dialog");
                alert.setHeaderText(null);
                alert.setContentText("Product Updated!");

                // Show the alert
                alert.showAndWait();
                reload(event);
            }

            else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Product Not Updated!");

                // Show the alert
                alert.showAndWait();
            }
        }

        catch (NullPointerException ex)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Please Select Product ID!");

            // Show the alert
            alert.showAndWait();
        }


    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
      loadSizes();
      loadTypes();
      loadProductIDs();
      loadSupplierIDs();

      //load product table

        tblProduct.setItems(loadProductTableData());
    }

    private ObservableList<productEntity> loadProductTableData() {
        //view all

        colID.setCellValueFactory(new PropertyValueFactory<>("productID"));
        colName.setCellValueFactory(new PropertyValueFactory<>("productName"));
        colSize.setCellValueFactory(new PropertyValueFactory<>("size"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        colAvailableQty.setCellValueFactory(new PropertyValueFactory<>("availableQty"));

        productService service = serviceFactory.getInstance().getServiceType(ServiceType.product);
        ObservableList<productEntity> productEntityObservableList=service.getAll();
        return productEntityObservableList;

    }

    private void loadProductIDs() {
        productService service = serviceFactory.getInstance().getServiceType(ServiceType.product);
        ObservableList<Integer> productIDs =service.getProductIDs();
        cmbProductID.setItems(productIDs);
    }

    private void loadSupplierIDs() {
        productService service = serviceFactory.getInstance().getServiceType(ServiceType.product);
        ObservableList<Integer> supplierIDlist=service.getSupplierIDs();
        cmbSupplierID.setItems(supplierIDlist);
    }

    private void loadTypes() {
        ObservableList<String> typeList = FXCollections.observableArrayList();
        typeList.add("Ladies");
        typeList.add("Gents");
        typeList.add("Kids");
        cmbType.setItems(typeList);
    }

    private void loadSizes() {
        ObservableList<String> sizeList = FXCollections.observableArrayList();
        sizeList.add("XS");
        sizeList.add("S");
        sizeList.add("M");
        sizeList.add("L");
        sizeList.add("XL");
        cmbSize.setItems(sizeList);
    }
}
