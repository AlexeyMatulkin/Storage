package sample;

public class Order {

    private int id_order;
    private String ordered_product;
    private String customer_order;
    private int amount_order;
    private String mail_order;


    public int getId_order() {
        return id_order;
    }

    public void setId_order(int id_order) {
        this.id_order = id_order;
    }

    public String getOrdered_product() {
        return ordered_product;
    }

    public void setOrdered_product(String ordered_product) {
        this.ordered_product = ordered_product;
    }

    public String getCustomer_order() {
        return customer_order;
    }

    public void setCustomer_order(String customer_order) {
        this.customer_order = customer_order;
    }

    public String getMail_order() {
        return mail_order;
    }

    public void setMail_order(String mail_order) {
        this.mail_order = mail_order;
    }

    public int getAmount_order() {
        return amount_order;
    }

    public void setAmount_order(int amount_order) {
        this.amount_order = amount_order;
    }

    public Order(int id_order, String ordered_product, int amount_order, String customer_order, String mail_order) {
        this.id_order = id_order;
        this.ordered_product = ordered_product;
        this.customer_order = customer_order;
        this.amount_order = amount_order;
        this.mail_order = mail_order;

    }
}
