package controller;

import db.DBConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;
import org.hibernate.Session;
import util.hibernateUtil;

import java.io.IOException;

public class defaultDashboardFormController {

    @FXML
    private Label lblUserName;

    @FXML
    void btnManageOrdersOnAction(ActionEvent event) {

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
    void btnManageProductOnAction(ActionEvent event) {

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
    void btnManageSuppllersOnAction(ActionEvent event) {

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

    public void btnProductReportonAction(ActionEvent actionEvent) {
        try {
            JasperDesign jasperDesign= JRXmlLoader.load("src/main/resources/reports/Blank_A4.jrxml");
            JasperReport jasperReport= JasperCompileManager.compileReport(jasperDesign);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, DBConnection.getInstance().getConnection());
            JasperViewer.viewReport(jasperPrint,false);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}
