package Server;

//import DateBase;
//import User;

import sample.Order;
import sample.Product;
import sample.Employer;
import sample.Provider;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server extends Thread {

    private static DateBase DB;
    private Socket clientSocket;
    private ObjectOutputStream soos;
    private ObjectInputStream sois;

    public Server(Socket s) throws IOException{
        clientSocket = s;
        sois = new ObjectInputStream(clientSocket.getInputStream());
        soos = new ObjectOutputStream(clientSocket.getOutputStream());
        start();
    }

    public static void main(String[] args) throws IOException{
        ServerSocket serverSocket = new ServerSocket(8000);
        System.out.println("Server Started");
        try{
            while(true){
                Socket newSocket = serverSocket.accept();
                System.out.println("new client connected");
                try {
                    new Server(newSocket);
                }catch (Exception e){
                    newSocket.close();
                }
            }

        }finally {
            serverSocket.close();
        }
    }

    public void run(){
        DB = new DateBase();
        try{
            while (true){
                System.out.println("Ожидание ввода");
                String clientMessageRecieved = (String) sois.readObject();
                System.out.println("Сообщение принято: "+ clientMessageRecieved);
                String information[]=clientMessageRecieved.split("->");
                User user;
                Product product;
                Employer employer;
                Order order;
                Provider provider;
                String action = information[0];
                if (action.equals("quit")){
                    break;
                }
                switch (action){
                    case "login":
                        user = new User(information[1], information[2]);
                        String answer = DB.checkUser(user);
                        soos.writeObject(answer);
                        break;

                    case "SignUp":
                        user = new User(information[1], information[2]);
                        if (DB.signUpUser(user)){
                            soos.writeObject("true");
                        } else {
                            soos.writeObject("false");
                        }
                        break;

                    case "ShowProduct":
                        ArrayList<String> answer_list = DB.all_table("product");
                        soos.writeObject(answer_list);
                        break;

                    case "ShowEmployer":
                        answer_list = DB.all_table("employer");
                        soos.writeObject(answer_list);
                        break;

                    case "ShowOrder":
                        answer_list = DB.all_table("order");
                        soos.writeObject(answer_list);
                        break;

                    case "ShowUser":
                        answer_list = DB.all_table("user");
                        soos.writeObject(answer_list);
                        break;

                    case "ShowProvider":
                        answer_list = DB.all_table("provider");
                        soos.writeObject(answer_list);
                        break;

                    case "add_product":
                        System.out.println("INTO CASE");
                        product = new Product(Integer.parseInt(information[1]), information[2], Integer.parseInt(information[3]),
                                information[4], information[5]);
                        DB.add_product(product);
                        answer_list = DB.all_table("product");
                        soos.writeObject(answer_list);
                        break;

                    case "add_employer":
                        employer = new Employer(Integer.parseInt(information[1]), information[2], Integer.parseInt(information[3]),
                                Integer.parseInt(information[4]), Integer.parseInt(information[5]));
                        DB.add_employer(employer);
                        answer_list = DB.all_table("employer");
                        soos.writeObject(answer_list);
                        break;

                    case "add_order":
                        System.out.println("INTO CASE");
                        order = new Order(Integer.parseInt(information[1]), information[2], Integer.parseInt(information[3]),
                                information[4], information[5]);
                        DB.add_order(order);

                        answer_list = DB.all_table("order");
                        soos.writeObject(answer_list);
                        break;

                    case "add_user":
                        user = new User(information[1], information[2], information[3]);
                        DB.add_user(user);
                        answer_list = DB.all_table("user");
                        soos.writeObject(answer_list);
                        break;

                    case "add_provider":
                        provider = new Provider(Integer.parseInt(information[1]), information[2]);
                        DB.add_provider(provider);
                        answer_list = DB.all_table("provider");
                        soos.writeObject(answer_list);
                        break;

                    case "delete_product":
                        DB.delete_product(information[1]);
                        answer_list = DB.all_table("product");
                        soos.writeObject(answer_list);
                        break;
                    case "delete_employer":
                        DB.delete_employer(information[1]);
                        answer_list = DB.all_table("employer");
                        soos.writeObject(answer_list);
                        break;
                    case "delete_order":
                        DB.delete_order(information[1]);
                        answer_list = DB.all_table("order");
                        soos.writeObject(answer_list);
                        break;
                    case "delete_provider":
                        DB.delete_provider(information[1]);
                        answer_list = DB.all_table("provider");
                        soos.writeObject(answer_list);
                        break;

                    case "find_product":
                        answer_list = DB.find_product(information[1]);
                        soos.writeObject(answer_list);
                        break;
                    case "find_employer":
                        answer_list = DB.find_employer(information[1]);
                        soos.writeObject(answer_list);
                        break;
                    case "find_user":
                        answer_list = DB.find_user(information[1]);
                        soos.writeObject(answer_list);
                        break;
                    case "find_order":
                        answer_list = DB.find_order(information[1]);
                        soos.writeObject(answer_list);
                        break;
                    case "find_provider":
                        answer_list = DB.find_provider(information[1]);
                        soos.writeObject(answer_list);
                        break;


                }
            }
        }catch (Exception e){
            System.err.println(("IO Exeption"));
        }finally {
            try {
                clientSocket.close();
            }catch (Exception e){
                System.err.println("Socket not closed");
            }
        }
    }

}
