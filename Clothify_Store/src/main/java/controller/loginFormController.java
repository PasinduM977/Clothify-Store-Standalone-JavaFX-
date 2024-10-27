package controller;

import dto.user;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import service.custom.userService;
import service.serviceFactory;
import util.ServiceType;

import java.io.IOException;

public class loginFormController {

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtPassword;

    @FXML
    void btnForgotPasswordOnAction(ActionEvent event) {
        Stage currentStage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        currentStage.close();

        try {

            Stage stage = new Stage();
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/resetPasswordForm.fxml"))));
            stage.show();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

    @FXML
    void btnLogInOnAction(ActionEvent event) {

        if(txtEmail.getText().equals("admin@gmail.com") & txtPassword.getText().equals("admin"))
        {
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

        else

        {
            userService service = serviceFactory.getInstance().getServiceType(ServiceType.user);
            String password=service.getPassword(txtEmail.getText());

            if(txtPassword.getText().equals(password))
            {

            Stage currentStage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            currentStage.close();

            try {

                Stage stage = new Stage();
                stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/defaultDashboardForm.fxml"))));
                stage.show();

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            }

//            Stage currentStage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
//            currentStage.close();
//
//            try {
//
//                Stage stage = new Stage();
//                stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/defaultDashboardForm.fxml"))));
//                stage.show();
//
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
        }



    }

}
