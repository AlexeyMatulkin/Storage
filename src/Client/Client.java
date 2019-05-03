package Client;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.Order;
import sample.Product;
import sample.Employer;
import Server.User;
import sample.Provider;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class Client extends Application {
    public static ObjectInputStream cois;
    private static ObjectOutputStream coos;
    private static Socket clientSoket;
    private static String answer;

    public static void main(String[] args) throws IOException {
        try {
            clientSoket = new Socket("127.0.0.1", 8000);
            coos = new ObjectOutputStream(clientSoket.getOutputStream());
            cois = new ObjectInputStream(clientSoket.getInputStream());
            launch(args);
        } catch (Exception e) {
            System.err.println("Нет соеденения с сервером");
            try {
                clientSoket.close();
                coos.close();
                cois.close();
            } catch (Exception el) {
            }
        }
    }

    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/sample/sample.fxml"));
        primaryStage.setTitle("Storage");
        primaryStage.setScene(new Scene(root, 700, 400));
        primaryStage.show();
    }

    public static String SignUp_User(String mail, String login, String password) throws Exception {
        String str = "SignUp" + mail + "->" + login + "->" + password;
        coos.writeObject(str);
        answer = (String) cois.readObject();
        return answer;
    }

    public static String SignIn(String login, String password) throws IOException {
        String str = "login" + "->" + login + "->" + password;
        coos.writeObject(str);
        try {
            answer = (String) cois.readObject();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return answer;
    }

    public static ArrayList Show(String str_to_server) throws IOException, ClassNotFoundException {
        coos.writeObject(str_to_server);
        ArrayList<String> server_list = (ArrayList<String>) cois.readObject();
        ArrayList ret_List = null;

        if (str_to_server.equals("ShowProduct")) {
            ret_List = new ArrayList<Product>();
            for (String i : server_list) {
                String[] product_data = i.split("->");
                ret_List.add(new Product(Integer.parseInt(product_data[0]), product_data[1], Integer.parseInt(product_data[2]),
                        product_data[3], product_data[4]));
            }
        } else if (str_to_server.equals("ShowEmployer")) {
            ret_List = new ArrayList<Employer>();
            for (String i : server_list) {
                String[] employer_data = i.split("->");
                ret_List.add(new Employer(Integer.parseInt(employer_data[0]), employer_data[1], Integer.parseInt(employer_data[2]),
                        Integer.parseInt(employer_data[3]), Integer.parseInt(employer_data[4])));

            }

        } else if (str_to_server.equals("ShowOrder")) {
            ret_List = new ArrayList<Order>();
            for (String i : server_list) {
                String[] order_data = i.split("->");
                ret_List.add(new Order(Integer.parseInt(order_data[0]), order_data[1], Integer.parseInt(order_data[2]), order_data[3], order_data[4]));

            }
        } else if (str_to_server.equals("ShowUser")) {
            ret_List = new ArrayList<User>();
            for (String i : server_list) {
                String[] user_data = i.split("->");
                ret_List.add(new User(user_data[0], user_data[1], user_data[2]));
            }
        } else if (str_to_server.equals("ShowProvider")) {
            ret_List = new ArrayList<Provider>();
            for (String i : server_list) {
                String[] provider_data = i.split("->");
                ret_List.add(new Provider(Integer.parseInt(provider_data[0]), provider_data[1]));
            }
        }
        return ret_List;
    }



    public static ArrayList add_product(String product_data) {
        String str = "add_product->" + product_data;
        ArrayList ret_List = null;
        try {
            System.out.println(str);
            coos.writeObject(str);
            ArrayList<String> list = (ArrayList<String>) cois.readObject();
            ret_List = new ArrayList<Product>();

            for (String tmp : list) {
                String[] product_information = tmp.split("->");
                ret_List.add(new Product(Integer.parseInt(product_information[0]), product_information[1], Integer.parseInt(product_information[2]),
                        product_information[3], product_information[4]));
            }

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return ret_List;
    }

    public static ArrayList add_employer(String employer_data) {
        String str = "add_employer->" + employer_data;
        ArrayList ret_List = null;
        try {
            coos.writeObject(str);
            ArrayList<String> list = (ArrayList<String>) cois.readObject();
            ret_List = new ArrayList<Product>();

            for (String tmp : list) {
                String[] employer_information = tmp.split("->");
                ret_List.add(new Employer(Integer.parseInt(employer_information[0]), employer_information[1], Integer.parseInt(employer_information[2]),
                        Integer.parseInt(employer_information[3]), Integer.parseInt(employer_information[4])));
            }

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return ret_List;
    }

    public static ArrayList add_order(String order_data) {
        String str = "add_order->" + order_data;
        ArrayList ret_List = null;
        try {
            System.out.println(str);
            coos.writeObject(str);
            ArrayList<String> list = (ArrayList<String>) cois.readObject();
            ret_List = new ArrayList<Order>();

            for (String tmp : list) {
                String[] order_information = tmp.split("->");
                ret_List.add(new Order(Integer.parseInt(order_information[0]), order_information[1], Integer.parseInt(order_information[2]),
                        order_information[3], order_information[4]));
            }

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return ret_List;
    }

    public static ArrayList add_user(String user_data) {
        String str = "add_user->" + user_data;
        ArrayList ret_List = null;
        try {
            System.out.println(str);
            coos.writeObject(str);
            ArrayList<String> list = (ArrayList<String>) cois.readObject();
            ret_List = new ArrayList<User>();

            for (String tmp : list) {
                String[] user_information = tmp.split("->");
                ret_List.add(new User(user_information[0], user_information[1], user_information[2]));
            }

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return ret_List;
    }

    public static ArrayList add_provider(String provider_data) {
        String str = "add_provider->" + provider_data;
        ArrayList ret_List = null;
        try {
            System.out.println(str);
            coos.writeObject(str);
            ArrayList<String> list = (ArrayList<String>) cois.readObject();
            ret_List = new ArrayList<Provider>();

            for (String tmp : list) {
                String[] provider_information = tmp.split("->");
                ret_List.add(new Provider(Integer.parseInt(provider_information[0]), provider_information[1]));
            }

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return ret_List;
    }


    public static void writeInFile(String information_report){
        try {
            PrintWriter File_Write = null;
            File_Write = new PrintWriter("report.txt");
            String[] list = information_report.split("->");
            for (int i = 0; i < list.length; i++)
                File_Write.println(list[i]);
            File_Write.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList delete(String product_data, String table) throws IOException, ClassNotFoundException {
        String str = "delete_" + table + "->" + product_data;
        coos.writeObject(str);

        ArrayList<String> list = null;
        list = (ArrayList<String>) cois.readObject();
        ArrayList ret_List = null;

        if (table.equals("product")) {
            ret_List = new ArrayList<Product>();
            for (String tmp : list) {
                String[] product_information = tmp.split("->");
                ret_List.add(new Product(Integer.parseInt(product_information[0]), product_information[1], Integer.parseInt(product_information[2]),
                        product_information[3], product_information[4]));
            }
        } else if (table.equals("employer")) {
            ret_List = new ArrayList<Employer>();
            for (String tmp : list) {
                String[] employer_information = tmp.split("->");
                ret_List.add(new Employer(Integer.parseInt(employer_information[0]), employer_information[1], Integer.parseInt(employer_information[2]),
                        Integer.parseInt(employer_information[3]), Integer.parseInt(employer_information[4])));
            }
        } else if (table.equals("order")) {
            ret_List = new ArrayList<Order>();
            for (String tmp : list) {
                String[] order_information = tmp.split("->");
                ret_List.add(new Order(Integer.parseInt(order_information[0]), order_information[1], Integer.parseInt(order_information[2]),
                        order_information[3], order_information[4]));
            }
        } else if (table.equals("provider")) {
            ret_List = new ArrayList<Provider>();
            for (String tmp : list) {
                String[] provider_information = tmp.split("->");
                ret_List.add(new Provider(Integer.parseInt(provider_information[0]), provider_information[1]));
            }
        }
        return ret_List;
    }

    public static ArrayList find(String find_str, String table) throws IOException, ClassNotFoundException {
        String str_to_server = "find_" + table + "->" + find_str;
        coos.writeObject(str_to_server);

        ArrayList<String> list = null;
        list = (ArrayList<String>) cois.readObject();
        ArrayList ret_List = null;

        //ArrayList<String> list = (ArrayList<String>) cois.readObject();
        switch (table) {
            case "product":
            ret_List = new ArrayList<Product>();
            for (String tmp : list) {
                String[] product_information = tmp.split("->");
                ret_List.add(new Product(Integer.parseInt(product_information[0]), product_information[1], Integer.parseInt(product_information[2]),
                        product_information[3], product_information[4]));
            }
                break;
            case "employer":
                ret_List = new ArrayList<Employer>();
                for (String tmp : list) {
                    String[] employer_information = tmp.split("->");
                    ret_List.add(new Employer(Integer.parseInt(employer_information[0]), employer_information[1], Integer.parseInt(employer_information[2]),
                            Integer.parseInt(employer_information[3]), Integer.parseInt(employer_information[4])));
                }
                break;
            case "user":
                ret_List = new ArrayList<User>();
                for (String tmp : list) {
                    String[] user_information = tmp.split("->");
                    ret_List.add(new User(user_information[0], user_information[1], user_information[2]));
                }
                break;
            case "order":
                ret_List = new ArrayList<Order>();
                for (String tmp : list) {
                    String[] order_information = tmp.split("->");
                    ret_List.add(new Order(Integer.parseInt(order_information[0]), order_information[1], Integer.parseInt(order_information[2]),
                            order_information[3], order_information[4]));
                }
                break;
            case "provider":
                ret_List = new ArrayList<Provider>();
                for (String tmp : list) {
                    String[] provider_information = tmp.split("->");
                    ret_List.add(new Provider(Integer.parseInt(provider_information[0]), provider_information[1]));
                }
                break;
        }

        return ret_List;
    }



}





