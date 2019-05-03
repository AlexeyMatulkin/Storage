package sample;

import java.io.IOException;
import java.net.URL;
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

import static Client.Client.SignUp_User;

public class SignUpController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField login_field;

    @FXML
    private Button UpButton;

    @FXML
    private PasswordField password_field;

    @FXML
    private TextField mail_field;

    @FXML
    private Button back;

    @FXML
    void initialize(){
        UpButton.setOnAction(event -> {
            signUpNewUser();
        });
        back.setOnAction(event -> {
            display_SignUp("/sample/sample.fxml");
        });
    }

    private void signUpNewUser() {
        DateBase dbHandler = new DateBase();
        String mail = mail_field.getText();
        String login = login_field.getText();
        String password = password_field.getText();

        User user = new User(mail,login,password);

        dbHandler.signUpUser(user);
        display_SignUp("/sample/sample.fxml");
    }

    private void display_SignUp(String page){
        Stage stage = (Stage) back.getScene().getWindow();
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
