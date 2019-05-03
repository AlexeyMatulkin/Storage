package sample;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

import static Client.Client.*;
//import static Client.Client.save_report;

public class UserController {

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
    private TextField id_order_field;

    @FXML
    private TextField amount_order_field;

    @FXML
    private TextField ordered_product_field;

    @FXML
    private Button add_order;

    @FXML
    private TextField mail_order_field;

    @FXML
    private TextField customer_order_field;

    @FXML
    private Button back_user;

    @FXML
    private Button make_order;

    @FXML
    private Button report;

    @FXML
    private Button graphic;

    @FXML
    private AnchorPane view_graphic;

    @FXML
    private PieChart piechart;

    @FXML
    private AnchorPane page;

    @FXML
    void initialize() {

        page = view_product;

        graphic.setOnAction(event -> {
            page.setVisible(false);
            view_graphic.setVisible(true);
            page = view_graphic;
        });

        make_order.setOnAction(event -> {
            page.setVisible(false);
            view_product.setVisible(true);
            page = view_product;
        });

        add_order.setOnAction(event -> {
            String add_order_data = id_order_field.getText() + "->" + ordered_product_field.getText() + "->" +
                    customer_order_field.getText()+ "->" + amount_order_field.getText() +
                    "->" + mail_order_field.getText();
            ArrayList<Order> order_data;
            order_data = add_order(add_order_data);

            clear_text_field();
        });
//
        report.setOnAction(event -> {
                ArrayList<Order> stringOrder;
                String fullList = "";
                try {
                    stringOrder = Show("ShowOrder");
                    int count = 0;
                    String rep;
                    for (Order tmp : stringOrder) {
                        if(count == 0){
                            rep = String.format("%-12s %-16s %-10s %-8s %-5s%n", "Номер заказа", "Заказанный товар",
                                    "Количество","Заказчик", "Почта");
                            count++;
                        }else {
                            rep = String.format("%-12s %-16s %-10s %-8s %-5s%n", tmp.getId_order(), tmp.getOrdered_product(),
                                    tmp.getCustomer_order(),tmp.getAmount_order(), tmp.getMail_order());
                        }
                        fullList += rep + "->";

                    }
                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                }

            writeInFile(fullList);
            });

        back_user.setOnAction(event -> {
            display_User("/sample/sample.fxml");
        });

        ArrayList<Product> product;
        try {
            product = Show("ShowProduct");
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
            product = new ArrayList<>();
        }
        drawing_table(product);
//        graphic();
    }


//    void graphic(){
//        ObservableList<PieChart.Data> pie_data = FXCollections.observableArrayList();
//        ArrayList<String> pie;
//        try {
//            pie = download_depart_bonus();
//            for(String str : pie){
//                String[] departs = str.split("->");
//                pie_data.add(new PieChart.Data(departs[0], Double.parseDouble(departs[1])));
//            }
//        } catch (IOException | ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//        piechart.setLabelsVisible(false);
//        piechart.setTitle("Премии - Отделы");
//        piechart.setData(pie_data);
//
//        final Label caption = new Label("");
//        caption.setTextFill(Color.WHITESMOKE);
//
//        for (final PieChart.Data data : pie_bonus_depart.getData()) {
//            data.getNode().addEventHandler(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
//                @Override public void handle(MouseEvent e) {
//                    caption.setTranslateX(e.getSceneX());
//                    caption.setTranslateY(e.getSceneY());
//                    caption.setText(String.valueOf(data.getPieValue()));
//                }
//            });
//        }
//
//    }

        private void drawing_table (ArrayList list){
                    code_product.setCellValueFactory(new PropertyValueFactory<>("code_product"));
                    name_product.setCellValueFactory(new PropertyValueFactory<>("name_product"));
                    amount_product.setCellValueFactory(new PropertyValueFactory<>("amount_product"));
                    location_product.setCellValueFactory(new PropertyValueFactory<>("location_product"));
                    provider_product.setCellValueFactory(new PropertyValueFactory<>("provider_product"));
                    table_product.setItems(FXCollections.observableArrayList(list));

        }

    private void clear_text_field() {

        id_order_field.clear();
        ordered_product_field.clear();
        customer_order_field.clear();
        mail_order_field.clear();
        amount_order_field.clear();
    }


    private void display_User(String page){
        Stage stage = (Stage) back_user.getScene().getWindow();
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
