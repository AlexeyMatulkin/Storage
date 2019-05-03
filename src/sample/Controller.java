package sample;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import Server.DateBase;
import Server.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import static Client.Client.*;

public class Controller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField login_field;

    @FXML
    private Button signUpButton;

    @FXML
    private Button signInButton;

    @FXML
    private PasswordField password_field;

    @FXML
    void initialize() {

        signInButton.setOnAction(event -> {
            String loginName = login_field.getText().trim();
            String loginPassword = password_field.getText().trim();
            String role;

            if(!loginName.equals("") && !loginPassword.equals("")) {
                try {
                    System.out.println();
                    role = SignIn(loginName,loginPassword);
                    if (role.equals("1")){
                        display("/sample/admin.fxml");
                    } else if (role.equals("0")) {
                      display("/sample/user.fxml");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            else
                System.out.println("Данные введены не верно");
        });

        signUpButton.setOnAction(event -> {
           display("/sample/SignUp.fxml");
        });

    }
    private void display(String page){
        Stage stage = (Stage) signInButton.getScene().getWindow();
        stage.close();

        FXMLLoader loader = new FXMLLoader(getClass().getResource(page));
        try {
            Parent root = (Parent) loader.load();
            stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
