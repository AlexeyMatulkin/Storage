package sample;

public class Product {

    private int code_product;
    private String name_product;
    private int amount_product;
    private String location_product;
    private String provider_product;

    public void setCode_product(int code_product) {
        this.code_product = code_product;
    }

    public void setName_product(String name_product) {
        this.name_product = name_product;
    }

    public void setamount__product(int amount__product) {
        this.amount_product = amount__product;
    }

    public void setLocation_product(String location_product) {
        this.location_product = location_product;
    }

    public void setProvider_product(String provider_product) {
        this.provider_product = provider_product;
    }

    public int getCode_product() {
        return code_product;
    }

    public String getName_product() {
        return name_product;
    }

    public int getAmount_product() {
        return amount_product;
    }

    public String getLocation_product() {
        return location_product;
    }

    public String getProvider_product() {
        return provider_product;
    }

    public Product(int code_product, String name_product, int amount__product, String location_product, String provider_product) {
        this.code_product = code_product;
        this.name_product = name_product;
        this.amount_product = amount__product;
        this.location_product = location_product;
        this.provider_product = provider_product;
    }
}
