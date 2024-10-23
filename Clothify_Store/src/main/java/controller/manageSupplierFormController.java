package controller;

import com.jfoenix.controls.JFXComboBox;
import dto.productIDAndName;
import dto.supplier;
import entity.productEntity;
import entity.supplierEntity;
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
import service.superService;
import util.ServiceType;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class manageSupplierFormController implements Initializable {

    public TableColumn colproductID;
    public TableView tblProduct;
    public Label lblDate1;
    public JFXComboBox cmbSupplierID;
    public TableView tblSupplier;
    @FXML
    private JFXComboBox<?> cmbID;

    @FXML
    private TableColumn<?, ?> colCompany;

    @FXML
    private TableColumn<?, ?> colEmail;

    @FXML
    private TableColumn<?, ?> colID;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colProductName;

    @FXML
    private Label lblDate;

    @FXML
    private Label lblTime;

    @FXML
    private TableView<?> tblEmployee;

    @FXML
    private TextField txtCompany;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtPassword;

    @FXML
    void btnAddOnAction(ActionEvent event) {

        supplierService service = serviceFactory.getInstance().getServiceType(ServiceType.supplier);
        boolean isAdded =service.addSupplier(new supplier(txtName.getText(),txtCompany.getText(),txtEmail.getText()));
        if(isAdded)
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("TSupplier Added!");

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

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        supplierService service = serviceFactory.getInstance().getServiceType(ServiceType.supplier);
        try
        {
            boolean isDeleted=service.deleteSupplier(Integer.parseInt(cmbSupplierID.getValue().toString()));
            if (isDeleted)
            {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Dialog");
                alert.setHeaderText(null);
                alert.setContentText("Supplier Deleted!");

                // Show the alert
                alert.showAndWait();
                reload(event);
            }

            else
            {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Information Dialog");
                alert.setHeaderText(null);
                alert.setContentText("Supplier Not Deleted!");

                // Show the alert
                alert.showAndWait();
            }
        }

        catch (NullPointerException ex)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Please Select Supplier ID!");

            // Show the alert
            alert.showAndWait();
        }

    }

    private void reload(ActionEvent event) {
        Stage currentStage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        currentStage.close();

        try {

            Stage stage = new Stage();
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/manageSupplierForm.fxml"))));
            stage.show();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
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
    void btnSearchOnAction(ActionEvent event) {
        supplierService service = serviceFactory.getInstance().getServiceType(ServiceType.supplier);
        ObservableList<supplierEntity> supplierEntityObservableList=service.getAll();
        supplierEntityObservableList.forEach(entity->{
            if(Integer.parseInt(cmbSupplierID.getValue().toString())==entity.getSupplierID())
            {
                txtName.setText(entity.getSupplierName());
                txtCompany.setText(entity.getCompany());
                txtEmail.setText(entity.getSupplierEmail());

            }

        });
        loadProductTable();


    }

    private void loadProductTable() {
        colproductID.setCellValueFactory(new PropertyValueFactory<>("pid"));
        colProductName.setCellValueFactory(new PropertyValueFactory<>("pname"));

        productService service = serviceFactory.getInstance().getServiceType(ServiceType.product);
        ObservableList<productEntity> productEntityObservableList=service.getAll();
        ObservableList<productIDAndName> idAndNamesList = FXCollections.observableArrayList();

        productEntityObservableList.forEach(product->{
            if(Integer.parseInt(cmbSupplierID.getValue().toString())==product.getSupplierID())
            {
                idAndNamesList.add(new productIDAndName(product.getProductID(),product.getProductName()));
            }


        });

        tblProduct.setItems(idAndNamesList);

    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        supplierService service = serviceFactory.getInstance().getServiceType(ServiceType.supplier);
    try
    {
        boolean isUpdated=service.updateSupplier(new supplier(txtName.getText(),txtCompany.getText(),txtEmail.getText()),Integer.parseInt(cmbSupplierID.getValue().toString()));
        if(isUpdated)
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Supplier Updated!");

            // Show the alert
            alert.showAndWait();
            reload(event);
        }

        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Supplier Not Updated!");

            // Show the alert
            alert.showAndWait();
        }
    }

    catch (NullPointerException ex)
    {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText("Please Select Supplier ID!");

        // Show the alert
        alert.showAndWait();
    }


    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        supplierService service = serviceFactory.getInstance().getServiceType(ServiceType.supplier);
        ObservableList<Integer> supplierIDs =service.getSupplierIDs();
        cmbSupplierID.setItems(supplierIDs);

        //view all

        colID.setCellValueFactory(new PropertyValueFactory<>("supplierID"));
        colName.setCellValueFactory(new PropertyValueFactory<>("supplierName"));
        colCompany.setCellValueFactory(new PropertyValueFactory<>("company"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("supplierEmail"));

        ObservableList<supplierEntity> supplierEntityObservableList=service.getAll();
        tblSupplier.setItems(supplierEntityObservableList);


    }
}
