package Server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import sample.Product;
import sample.Employer;
import sample.Order;
import sample.Provider;


public class DateBase extends Config{
    Connection dbConnection;

    public DateBase(){}

    public Connection getDbConnection() throws ClassNotFoundException, SQLException{
        String connectionString = "jdbc:mysql://" + dbHost + ":"+dbPort + "/" +dbName+ "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
        Class.forName("com.mysql.cj.jdbc.Driver");
        dbConnection = DriverManager.getConnection(connectionString,dbUser,dbPass);

        return dbConnection;
    }

    public boolean signUpUser(User user){

        String insert = " INSERT INTO " + Const.USER_TABLE + "(" + Const.USER_MAIL + "," + Const.USER_LOGIN+ "," + Const.USER_PASSWORD+ ")"+ " VALUE(?,?,?)";
        try {
        PreparedStatement pr = getDbConnection().prepareStatement(insert);
        pr.setString(1,user.getMail());
        pr.setString(2,user.getLogin());
        pr.setString(3,user.getPassword());
            pr.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }
    public String checkUser(User user) {
        ResultSet resSet = null;
        String select = " SELECT * FROM " + Const.USER_TABLE + " WHERE " + Const.USER_LOGIN + "=? AND " + Const.USER_PASSWORD + " =? ";
        String role = "";
        try {
            PreparedStatement pr = getDbConnection().prepareStatement(select);
            pr.setString(1, user.getLogin());
            pr.setString(2, user.getPassword());
            resSet =pr.executeQuery();

            if (resSet.next()){
                role = resSet.getString("role");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return role;
    }

    public ArrayList<String> all_table(String name_table) {
        ArrayList<String> list = new ArrayList<>();
        ResultSet resultSet = null;

        String select_string = "";
        switch (name_table){

            case "product":
                select_string = "SELECT * FROM " + Const.PRODUCT_TABLE;
                break;
            case "employer":
                select_string = "SELECT * FROM " + Const.EMPLOYER_TABLE;
                break;
            case "order":
                select_string = "SELECT * FROM storage."  + Const.ORDER_TABLE ;
                break;
            case "user":
                select_string = "SELECT * FROM "  + Const.USER_TABLE ;
                break;
            case "provider":
                select_string = "SELECT * FROM "  + Const.PROVIDER_TABLE ;
                break;
        }
        PreparedStatement prSt;
        try {
            prSt = getDbConnection().prepareStatement(select_string);
            resultSet = prSt.executeQuery(select_string);

            if(name_table.equals("product")) {
                while (resultSet.next()) {
                    String product_list = "";
                    product_list += resultSet.getString(Const.CODE_PRODUCT) + "->";
                    product_list += resultSet.getString(Const.NAME_PRODUCT) + "->";
                    product_list += resultSet.getString(Const.AMOUNT_PRODUCT) + "->";
                    product_list += resultSet.getString(Const.LOCATION_PRODUCT) + "->";
                    product_list += resultSet.getString(Const.PROVIDER_PRODUCT);
                    list.add(product_list);
                }
            }else if(name_table.equals("employer")){
             while (resultSet.next()) {
                    String employer_list = "";
                    employer_list += resultSet.getString(Const.ID_EMPLOYER) + "->";
                    employer_list += resultSet.getString(Const.NAME_EMPLOYER) + "->";
                    employer_list += resultSet.getString(Const.DEPARTMENT_EMPLOYER) + "->";
                    employer_list += resultSet.getString(Const.EXPERIENCE_EMPLOYER) + "->";
                    employer_list += resultSet.getString(Const.SALARY_EMPLOYER);
                    list.add(employer_list);
                }
            }else if(name_table.equals("order")){
                while (resultSet.next()) {
                    String order_list = "";
                    order_list += resultSet.getString(Const.ID_ORDER) + "->";
                    order_list += resultSet.getString(Const.ORDERED_PRODUCT) + "->";
                    order_list += resultSet.getString(Const.AMOUNT_ORDER) + "->";
                    order_list += resultSet.getString(Const.CUSTOMER_ORDER) + "->";

                    order_list += resultSet.getString(Const.MAIL_ORDER);
                    list.add(order_list);
                }
            }else if(name_table.equals("user")){
                while (resultSet.next()) {
                    String user_list = "";
                    user_list += resultSet.getString(Const.USER_LOGIN) + "->";
                    user_list += resultSet.getString(Const.USER_PASSWORD) + "->";
                    user_list += resultSet.getString(Const.USER_MAIL);
                    list.add(user_list);
                }
            }else if(name_table.equals("provider")){
                while (resultSet.next()) {
                    String provider_list = "";
                    provider_list += resultSet.getString(Const.ID_PROVIDER) + "->";
                    provider_list += resultSet.getString(Const.NAME_PROVIDER);
                    list.add(provider_list);
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return list;
    }

    public void add_product(Product product) {
        String insert = "INSERT INTO " + Const.PRODUCT_TABLE + "(" + Const.CODE_PRODUCT + "," +
                Const.NAME_PRODUCT + "," + Const.AMOUNT_PRODUCT + "," + Const.LOCATION_PRODUCT + "," + Const.PROVIDER_PRODUCT +
                ")" + "VALUES(?,?,?,?,?)";
           System.out.println("SQL --" + insert);
        PreparedStatement prSt = null;

        try {
            prSt = getDbConnection().prepareStatement(insert);
            prSt.setInt(1, product.getCode_product());
            prSt.setString(2, product.getName_product());
            prSt.setInt(3, product.getAmount_product());
            prSt.setString(4,product.getLocation_product());
            prSt.setString(5,product.getProvider_product());
            prSt.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void add_employer(Employer employer) {
        String insert = "INSERT INTO " + Const.EMPLOYER_TABLE + "(" + Const.ID_EMPLOYER + "," +
                Const.NAME_EMPLOYER + "," + Const.DEPARTMENT_EMPLOYER + "," + Const.EXPERIENCE_EMPLOYER + "," + Const.SALARY_EMPLOYER +
                ")" + "VALUES(?,?,?,?,?)";
        PreparedStatement prSt = null;

        try {
            prSt = getDbConnection().prepareStatement(insert);
            prSt.setInt(1, employer.getId_employer());
            prSt.setString(2, employer.getName_employer());
            prSt.setInt(3, employer.getDepartment_employer());
            prSt.setInt(4,employer.getExperience_employer());
            prSt.setInt(5,employer.getSalary_employer());
            prSt.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void add_order(Order order) {
        ResultSet resultSet = null;
        String select = " SELECT " + Const.AMOUNT_PRODUCT + " FROM " +Const.PRODUCT_TABLE +  " WHERE " + Const.NAME_PRODUCT + "=?";
        PreparedStatement prSt = null;
        int amount_product_on_storage = 0;
        try {
            prSt = getDbConnection().prepareStatement(select);
            prSt.setString(1, order.getOrdered_product());
            resultSet = prSt.executeQuery();
            while (resultSet.next()) {
                amount_product_on_storage = resultSet.getInt(Const.AMOUNT_PRODUCT);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        int amount_in_order = amount_product_on_storage - order.getAmount_order();
        String update = "UPDATE storage." + Const.PRODUCT_TABLE + " SET " + Const.AMOUNT_PRODUCT + "="+ amount_in_order + " WHERE " + Const.NAME_PRODUCT + "=?";
        try {
            prSt = getDbConnection().prepareStatement(update);
            prSt.setString(1, order.getOrdered_product());
            prSt.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        String insert = "INSERT INTO storage." + Const.ORDER_TABLE + "(" + Const.ID_ORDER + "," +
                Const.ORDERED_PRODUCT + "," + Const.CUSTOMER_ORDER + "," + Const.AMOUNT_ORDER + "," + Const.MAIL_ORDER +
                ")" + "VALUES(?,?,?,?,?)";
        System.out.println("SQL --" + insert);

        try {
            prSt = getDbConnection().prepareStatement(insert);
            prSt.setInt(1, order.getId_order());
            prSt.setString(2, order.getOrdered_product());
            prSt.setString(3, order.getCustomer_order());
            prSt.setInt(4,order.getAmount_order());
            prSt.setString(5,order.getMail_order());
            prSt.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void add_user(User user) {
        String insert = "INSERT INTO storage." + Const.ORDER_TABLE + "(" + Const.USER_LOGIN + "," +
                Const.USER_PASSWORD + "," + Const.USER_MAIL +")" + "VALUES(?,?,?)";
        System.out.println("SQL --" + insert);
        PreparedStatement prSt = null;

        try {
            prSt = getDbConnection().prepareStatement(insert);
            prSt.setString(1, user.getLogin());
            prSt.setString(2, user.getPassword());
            prSt.setString(3, user.getMail());
            prSt.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void add_provider(Provider provider) {
        String insert = "INSERT INTO storage." + Const.PROVIDER_TABLE + "(" + Const.ID_PROVIDER + "," +
                Const.NAME_PROVIDER +")" + "VALUES(?,?)";
        System.out.println("SQL --" + insert);
        PreparedStatement prSt = null;

        try {
            prSt = getDbConnection().prepareStatement(insert);
            prSt.setInt(1, provider.getId_provider());
            prSt.setString(2, provider.getName_provider());
            prSt.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    public void delete_product(String code_product) {
        String select_string = "DELETE FROM " + Const.PRODUCT_TABLE + " WHERE " + Const.CODE_PRODUCT + " =?";
        PreparedStatement prSt = null;
        try {
            prSt = getDbConnection().prepareStatement(select_string);
            prSt.setString(1, code_product);

            prSt.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void delete_employer(String id_employer) {
        String select_string = "DELETE FROM " + Const.EMPLOYER_TABLE + " WHERE " + Const.ID_EMPLOYER + " =?";
        PreparedStatement prSt = null;
        try {
            prSt = getDbConnection().prepareStatement(select_string);
            prSt.setString(1, id_employer);

            prSt.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void delete_provider(String id_provider) {
        String select_string = "DELETE FROM " + Const.PROVIDER_TABLE + " WHERE " + Const.ID_PROVIDER + " =?";
        PreparedStatement prSt = null;
        try {
            prSt = getDbConnection().prepareStatement(select_string);
            prSt.setString(1, id_provider);

            prSt.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<String> find_product(String find_str) {
        ArrayList<String> product_list = new ArrayList<>();
        ResultSet resultSet = null;
        String find_field = "";

        String select_string = "SELECT * FROM " + Const.PRODUCT_TABLE + " WHERE " + Const.NAME_PRODUCT+" ='" + find_str + "'";
        PreparedStatement prSt;
        try {
            prSt = getDbConnection().prepareStatement(select_string);
            resultSet = prSt.executeQuery(select_string);

            while (resultSet.next()) {
                String pl = "";
                pl += resultSet.getString(Const.CODE_PRODUCT) + "->";
                pl += resultSet.getString(Const.NAME_PRODUCT) + "->";
                pl += resultSet.getString(Const.AMOUNT_PRODUCT) + "->";
                pl += resultSet.getString(Const.LOCATION_PRODUCT) + "->";
                pl += resultSet.getString(Const.PROVIDER_PRODUCT);
                product_list.add(pl);
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return product_list;
    }

    public ArrayList<String> find_employer(String find_str) {
        ArrayList<String> employer_list = new ArrayList<>();
        ResultSet resultSet = null;
        String find_field = "";

        String select_string = "SELECT * FROM " + Const.EMPLOYER_TABLE + " WHERE " + Const.NAME_EMPLOYER + " ='" + find_str + "'";
        PreparedStatement prSt;
        try {
            prSt = getDbConnection().prepareStatement(select_string);
            resultSet = prSt.executeQuery(select_string);

            while (resultSet.next()) {
                String el = "";
                el += resultSet.getString(Const.ID_EMPLOYER) + "->";
                el += resultSet.getString(Const.NAME_EMPLOYER) + "->";
                el += resultSet.getString(Const.DEPARTMENT_EMPLOYER) + "->";
                el += resultSet.getString(Const.EXPERIENCE_EMPLOYER) + "->";
                el += resultSet.getString(Const.SALARY_EMPLOYER);
                employer_list.add(el);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return employer_list;
    }

    public ArrayList<String> find_user(String find_str) {
        ArrayList<String> user_list = new ArrayList<>();
        ResultSet resultSet = null;
        String find_field = "";

        String select_string = "SELECT * FROM storage." + Const.USER_TABLE + " WHERE " + Const.USER_LOGIN + " ='" + find_str + "'";
        PreparedStatement prSt;
        try {
            prSt = getDbConnection().prepareStatement(select_string);
            resultSet = prSt.executeQuery(select_string);

            while (resultSet.next()) {
                String ul = "";
                ul += resultSet.getString(Const.USER_LOGIN) + "->";
                ul += resultSet.getString(Const.USER_MAIL) + "->";
                ul += resultSet.getString(Const.USER_PASSWORD);
                user_list.add(ul);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return user_list;
    }


    public ArrayList<String> find_order(String find_str) {
        ArrayList<String> order_list = new ArrayList<>();
        ResultSet resultSet = null;
        String find_field = "";

        String select_string = "SELECT * FROM storage." + Const.ORDER_TABLE + " WHERE " + Const.ORDERED_PRODUCT + " ='" + find_str + "'";
        PreparedStatement prSt;
        try {
            prSt = getDbConnection().prepareStatement(select_string);
            resultSet = prSt.executeQuery(select_string);

            while (resultSet.next()) {
                String ol = "";
                ol += resultSet.getString(Const.ID_ORDER) + "->";
                ol += resultSet.getString(Const.ORDERED_PRODUCT) + "->";
                ol += resultSet.getString(Const.AMOUNT_ORDER) + "->";
                ol += resultSet.getString(Const.CUSTOMER_ORDER) + "->";
                ol += resultSet.getString(Const.MAIL_ORDER);
                order_list.add(ol);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return order_list;
    }

    public ArrayList<String> find_provider(String find_str) {
        ArrayList<String> provider_list = new ArrayList<>();
        ResultSet resultSet = null;
        String find_field = "";

        String select_string = "SELECT * FROM " + Const.PROVIDER_TABLE + " WHERE " + Const.NAME_PROVIDER + " ='" + find_str + "'";
        PreparedStatement prSt;
        try {
            prSt = getDbConnection().prepareStatement(select_string);
            resultSet = prSt.executeQuery(select_string);

            while (resultSet.next()) {
                String provl = "";
                provl += resultSet.getString(Const.ID_ORDER) + "->";
                provl += resultSet.getString(Const.ORDERED_PRODUCT) + "->";
                provl += resultSet.getString(Const.CUSTOMER_ORDER) + "->";
                provl += resultSet.getString(Const.AMOUNT_PRODUCT) + "->";
                provl += resultSet.getString(Const.MAIL_ORDER);
                provider_list.add(provl);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return provider_list;
    }


    public void delete_order(String id_order) {
        String select_string = "DELETE FROM storage." + Const.ORDER_TABLE + " WHERE " + Const.ID_ORDER + " =?";
        PreparedStatement prSt = null;
        try {
            prSt = getDbConnection().prepareStatement(select_string);
            prSt.setString(1, id_order);

            prSt.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }




}
