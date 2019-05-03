package sample;

import java.io.IOException;
import java.util.ArrayList;

import Server.User;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import static Client.Client.*;
import static Client.Client.delete;
import static Client.Client.find;

public class adminController {

    @FXML
    private Button product_table;

    @FXML
    private Button employer_table;

    @FXML
    private Button user_table;


    @FXML
    private Button order_table;

    @FXML
    private Button provider_table;

    @FXML
    private AnchorPane view_product;

    @FXML
    private TableView<Product> table_product;

    @FXML
    private TableColumn<Product, Integer> code_product;

    @FXML
    private TableColumn<Product, String> name_product;

    @FXML
    private TableColumn<Product, Integer> amount_product;

    @FXML
    private TableColumn<Product, String> location_product;

    @FXML
    private TableColumn<Product, String> provider_product;

    @FXML
    private TextField code_product_field;

    @FXML
    private TextField amount_product_field;

    @FXML
    private TextField name_product_field;

    @FXML
    private TextField location_product_field;

    @FXML
    private TextField provider_product_field;

    @FXML
    private Button add_product;

    @FXML
    private TextField search_product_field;

    @FXML
    private Button search_product;

    @FXML
    private Button del_product;

    @FXML
    private AnchorPane view_employer;

    @FXML
    private TableView<Employer> table_employer;

    @FXML
    private TableColumn<Employer, Integer> id_employer;

    @FXML
    private TableColumn<Employer, String> name_employer;

    @FXML
    private TableColumn<Employer, Integer> department_employer;

    @FXML
    private TableColumn<Employer, Integer> experience_employer;

    @FXML
    private TableColumn<Employer, Integer> salary_employer;

    @FXML
    private TextField id_employer_field;

    @FXML
    private TextField department_employer_field;

    @FXML
    private TextField name_employer_field;

    @FXML
    private TextField experience_employer_field;

    @FXML
    private TextField salary_employer_field;

    @FXML
    private Button add_employer;

    @FXML
    private TextField search_employer_field;

    @FXML
    private Button search_employer;

    @FXML
    private Button del_employer;

    @FXML
    private AnchorPane view_user;

    @FXML
    private TableView<User> table_user;

    @FXML
    private TableColumn<User, String> login_user;

    @FXML
    private TableColumn<User, String> password_user;

    @FXML
    private TableColumn<User, String> mail_user;

    @FXML
    private TextField login_user_field;

    @FXML
    private TextField password_user_field;

    @FXML
    private TextField mail_user_field;

    @FXML
    private Button add_user;

    @FXML
    private TextField search_user_field;

    @FXML
    private Button serach_user;

    @FXML
    private Button del_user;

    @FXML
    private AnchorPane view_order;

    @FXML
    private TableView<Order> table_order;

    @FXML
    private TableColumn<Order, Integer> id_order;

    @FXML
    private TableColumn<Order, String> ordered_product;

    @FXML
    private TableColumn<Order, String> customer_order;

    @FXML
    private TableColumn<Order, Integer> amount_order;

    @FXML
    private TableColumn<Order, String> mail_order;

    @FXML
    private TextField id_order_field;

    @FXML
    private TextField customer_order_field;

    @FXML
    private TextField ordered_product_field;

    @FXML
    private TextField mail_order_field;

    @FXML
    private TextField amount_order_field;

    @FXML
    private Button add_order;

    @FXML
    private Button quite_order;

    @FXML
    private TextField search_order_field;

    @FXML
    private Button serach_order;

    @FXML
    private Button del_order;

    @FXML
    private AnchorPane page;

    @FXML
    private AnchorPane view_provider;

    @FXML
    private TableView<Provider> table_provider;

    @FXML
    private TableColumn<Provider, Integer> id_provider;

    @FXML
    private TableColumn<Provider, String> name_provider;

    @FXML
    private TextField id_provider_field;

    @FXML
    private TextField name_provider_field;

    @FXML
    private Button add_provider;

    @FXML
    private TextField search_provider_field;

    @FXML
    private Button search_provider;

    @FXML
    private Button del_provider;

    @FXML
    private Button restart;


    @FXML
    void initialize() {
        page = view_product;

        employer_table.setOnAction(event -> {
            page.setVisible(false);
            view_employer.setVisible(true);
            page = view_employer;
        });
        user_table.setOnAction(event -> {
            page.setVisible(false);
            view_user.setVisible(true);
            page = view_user;
        });

        order_table.setOnAction(event -> {
            page.setVisible(false);
            view_order.setVisible(true);
            page = view_order;
        });

        product_table.setOnAction(event -> {
            page.setVisible(false);
            view_product.setVisible(true);
            page = view_product;
        });

        provider_table.setOnAction(event -> {
            page.setVisible(false);
            view_provider.setVisible(true);
            page = view_provider;
        });


        ArrayList<Product> product;
        ArrayList<Employer> employer;
        ArrayList<Order> order;
        ArrayList<User> user;
        ArrayList<Provider> provider;
        try {
            product = Show("ShowProduct");
            employer = Show("ShowEmployer");
            order = Show("ShowOrder");
            user = Show("ShowUser");
            provider = Show("ShowProvider");

            for(User i : user){
                System.out.println(i.getLogin());
            }

        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
            product = new ArrayList<>();
            employer = new ArrayList<>();
            order = new ArrayList<>();
            user = new ArrayList<>();
            provider = new ArrayList<>();
        }
        drawing_table(product, "product");
        drawing_table(employer, "employer");
        drawing_table(order, "order");
        drawing_table(user, "user");
        drawing_table(provider, "provider");

        restart.setOnAction(event -> {
                    display_Admin("/sample/admin.fxml");
                });

        quite_order.setOnAction(event -> {
            display_Admin("/sample/sample.fxml");
        });

        add_product.setOnAction(event -> {
            String add_product_data = code_product_field.getText() + "->" + name_product_field.getText() + "->" + amount_product_field.getText() +
                    "->" + location_product_field.getText() + "->" + provider_product_field.getText();
            ArrayList<Product> product_data;
            product_data = add_product(add_product_data);
            drawing_table(product_data, "product");
            clear_text_field();
        });

        add_employer.setOnAction(event -> {
            String add_employer_data = id_employer_field.getText() + "->" + name_employer_field.getText() + "->" + department_employer_field.getText() +
                    "->" + experience_employer_field.getText() + "->" + salary_employer_field.getText();
            ArrayList<Product> employer_data;
            employer_data = add_employer(add_employer_data);
            drawing_table(employer_data, "employer");
            clear_text_field();
        });

        add_order.setOnAction(event -> {
            String add_order_data = id_order_field.getText() + "->" + ordered_product_field.getText() + "->" + amount_order_field.getText()+ "->" + customer_order_field.getText() +
                            "->" + mail_order_field.getText();
            ArrayList<Order> order_data;
            order_data = add_order(add_order_data);
            drawing_table(order_data, "order");
            clear_text_field();
                });

        add_user.setOnAction(event -> {
            String add_user_data = login_user_field.getText() + "->" + password_user_field.getText() + "->" + mail_user_field.getText();
            ArrayList<User> user_data;
            user_data = add_user(add_user_data);
            drawing_table(user_data, "user");
            clear_text_field();
        });


        add_provider.setOnAction(event -> {
            String add_provider_data = id_provider_field.getText() + "->" + name_provider_field.getText();
            ArrayList<Provider> provider_data;
            provider_data = add_provider(add_provider_data);
            drawing_table(provider_data, "provider");
            clear_text_field();
        });

        del_product.setOnAction(event -> {
            String product_data = code_product_field.getText();
            ArrayList<Product> all_product;
            try {
                all_product = delete(product_data, "product");
                drawing_table(all_product, "product");
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
            clear_text_field();
        });

        del_employer.setOnAction(event -> {
            String employer_data = id_employer_field.getText();
            ArrayList<Employer> all_employer;
            try {
                all_employer = delete(employer_data, "employer");
                drawing_table(all_employer, "employer");
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
            clear_text_field();
        });

        del_order.setOnAction(event -> {
            String order_data = id_order_field.getText();
            ArrayList<Order> all_order;
            try {
                all_order = delete(order_data, "order");
                drawing_table(all_order, "order");
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
            clear_text_field();
        });

        del_provider.setOnAction(event -> {
            String provider_data = id_provider_field.getText();
            ArrayList<Provider> all_provider;
            try {
                all_provider = delete(provider_data, "provider");
                drawing_table(all_provider, "provider");
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
            clear_text_field();
        });

        search_product.setOnAction(event -> {
            ArrayList<Product> all_product = null;
            try {
                all_product = find((String) search_product_field.getText(), "product");
                for(Product i : all_product){
                }
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
            drawing_table(all_product, "product");
        });

        search_employer.setOnAction(event -> {
            ArrayList<Employer> all_employer = null;
            try {
                all_employer = find((String) search_employer_field.getText(), "employer");
                for(Employer i : all_employer){
                }
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
            drawing_table(all_employer, "employer");
        });

        serach_user.setOnAction(event -> {
            ArrayList<User> all_user = null;
            try {
                all_user = find((String) search_user_field.getText(), "user");
                for(User i : all_user){
                }
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
            drawing_table(all_user, "user");
        });

        serach_order.setOnAction(event -> {
            ArrayList<Order> all_order = null;
            try {
                all_order = find((String) search_order_field.getText(), "order");
                for(Order i : all_order){
                }
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
            drawing_table(all_order, "order");
        });



        search_provider.setOnAction(event -> {
            ArrayList<Provider> all_provider = null;
            try {
                all_provider = find((String) search_provider_field.getText(), "provider");
                for(Provider i : all_provider){
                }
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
            drawing_table(all_provider, "provider");
        });

    }

    private void clear_text_field() {
        code_product_field.clear();
        name_product_field.clear();
        amount_product_field.clear();
        location_product_field.clear();
        provider_product_field.clear();
        id_employer_field.clear();
        name_employer_field.clear();
        department_employer_field.clear();
        experience_employer_field.clear();
        salary_employer_field.clear();
        id_order_field.clear();
        ordered_product_field.clear();
        customer_order_field.clear();
        amount_order_field.clear();
        mail_order_field.clear();
        id_provider_field.clear();
        name_provider_field.clear();


    }

    private void display_Admin(String page){
        Stage stage = (Stage) quite_order.getScene().getWindow();
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

    private void display_Window(String page){
        Stage stage = (Stage) restart.getScene().getWindow();
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


    private void drawing_table(ArrayList list, String table) {
            switch (table){
                case "product":
                    code_product.setCellValueFactory(new PropertyValueFactory<>("code_product"));
                    name_product.setCellValueFactory(new PropertyValueFactory<>("name_product"));
                    amount_product.setCellValueFactory(new PropertyValueFactory<>("amount_product"));
                    location_product.setCellValueFactory(new PropertyValueFactory<>("location_product"));
                    provider_product.setCellValueFactory(new PropertyValueFactory<>("provider_product"));
                    table_product.setItems(FXCollections.observableArrayList(list));
                    break;
                case "employer":
                    id_employer.setCellValueFactory(new PropertyValueFactory<>("id_employer"));
                    name_employer.setCellValueFactory(new PropertyValueFactory<>("name_employer"));
                    department_employer.setCellValueFactory(new PropertyValueFactory<>("department_employer"));
                    experience_employer.setCellValueFactory(new PropertyValueFactory<>("experience_employer"));
                    salary_employer.setCellValueFactory(new PropertyValueFactory<>("salary_employer"));
                    table_employer.setItems(FXCollections.observableArrayList(list));
                    break;
                case "order":
                    id_order.setCellValueFactory(new PropertyValueFactory<>("id_order"));
                    ordered_product.setCellValueFactory(new PropertyValueFactory<>("ordered_product"));
                    customer_order.setCellValueFactory(new PropertyValueFactory<>("customer_order"));
                    amount_order.setCellValueFactory(new PropertyValueFactory<>("amount_order"));
                    mail_order.setCellValueFactory(new PropertyValueFactory<>("mail_order"));
                    table_order.setItems(FXCollections.observableArrayList(list));
                    break;
                case "user":
                    login_user.setCellValueFactory(new PropertyValueFactory<>("login"));
                    mail_user.setCellValueFactory(new PropertyValueFactory<>("mail"));
                    password_user.setCellValueFactory(new PropertyValueFactory<>("password"));
                    table_user.setItems(FXCollections.observableArrayList(list));
                    break;
                case "provider":
                    id_provider.setCellValueFactory(new PropertyValueFactory<>("id_provider"));
                    name_provider.setCellValueFactory(new PropertyValueFactory<>("name_provider"));
                    table_provider.setItems(FXCollections.observableArrayList(list));
                    break;
            }


    }
}
