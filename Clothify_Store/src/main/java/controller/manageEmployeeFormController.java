package controller;

import com.jfoenix.controls.JFXComboBox;
import dto.product;
import dto.supplier;
import dto.user;
import entity.productEntity;
import entity.supplierEntity;
import entity.userEntity;
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
import service.custom.userService;
import service.serviceFactory;
import util.ServiceType;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class manageEmployeeFormController implements Initializable {


    public JFXComboBox cmbUserID;
    @FXML
    private TableColumn<?, ?> colCompany;

    @FXML
    private TableColumn<?, ?> colEmail;

    @FXML
    private TableColumn<?, ?> colID;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colPassword;

    @FXML
    private Label lblDate;

    @FXML
    private Label lblTime;

    @FXML
    private TableView<userEntity> tblEmployee;

    @FXML
    private TextField txtCompany;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtPassword;

    @FXML
    void btnDeleteOnAction(ActionEvent event) {

        userService service = serviceFactory.getInstance().getServiceType(ServiceType.user);
        try
        {
            boolean isDeleted=service.deleteUser(Integer.parseInt(cmbUserID.getValue().toString()));
            if (isDeleted)
            {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Dialog");
                alert.setHeaderText(null);
                alert.setContentText("User Deleted!");

                // Show the alert
                alert.showAndWait();
                reload(event);
            }

            else
            {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Information Dialog");
                alert.setHeaderText(null);
                alert.setContentText("User Not Deleted!");

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
    void btnRegisterOnAction(ActionEvent event) {
        userService service = serviceFactory.getInstance().getServiceType(ServiceType.user);
        boolean isAdded =service.addUser(new user(txtName.getText(),txtEmail.getText(),txtPassword.getText()));
        if(isAdded)
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("User Added!");

            // Show the alert
            alert.showAndWait();
            reload(event);
        }

        else
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("User Not Added!");

            // Show the alert
            alert.showAndWait();
        }


    }

    private void reload(ActionEvent event) {
        Stage currentStage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        currentStage.close();

        try {

            Stage stage = new Stage();
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/manageEmployeeForm.fxml"))));
            stage.show();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        userService service = serviceFactory.getInstance().getServiceType(ServiceType.user);
        try
        {
            boolean isUpdated=service.updateUser(new user(txtName.getText(),txtEmail.getText(),txtPassword.getText()),Integer.parseInt(cmbUserID.getValue().toString()));
            if(isUpdated)
            {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Dialog");
                alert.setHeaderText(null);
                alert.setContentText("User Updated!");

                // Show the alert
                alert.showAndWait();
                reload(event);
            }

            else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("User Not Updated!");

                // Show the alert
                alert.showAndWait();
            }
        }

        catch (NullPointerException ex)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Please Select User ID!");

            // Show the alert
            alert.showAndWait();
        }

    }

    public void btnSearchOnAction(ActionEvent actionEvent) {
        userService service = serviceFactory.getInstance().getServiceType(ServiceType.user);
        ObservableList<userEntity> userEntityObservableList=service.getAll();
        userEntityObservableList.forEach(entity->{
            if(Integer.parseInt(cmbUserID.getValue().toString())==entity.getUserID())
            {
                txtName.setText(entity.getUserName());
                txtEmail.setText(entity.getUserEmail());
                txtPassword.setText(entity.getPassword());

            }

        });

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadUserIDs();
        loaduser();
    }

    private void loaduser() {
        colID.setCellValueFactory(new PropertyValueFactory<>("userID"));
        colName.setCellValueFactory(new PropertyValueFactory<>("userName"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("userEmail"));
        colPassword.setCellValueFactory(new PropertyValueFactory<>("password"));

        userService service = serviceFactory.getInstance().getServiceType(ServiceType.user);
        ObservableList<userEntity> userrEntityObservableList=service.getAll();
        tblEmployee.setItems(userrEntityObservableList);
    }

    private void loadUserIDs() {
        userService service = serviceFactory.getInstance().getServiceType(ServiceType.user);
        ObservableList<Integer> userIDs =service.getUserIDs();
        cmbUserID.setItems(userIDs);
    }
}
