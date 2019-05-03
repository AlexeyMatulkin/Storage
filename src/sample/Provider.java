package sample;

public class Provider {
    private int id_provider;
    private String name_provider;

    public int getId_provider() {
        return id_provider;
    }

    public void setId_provider(int id_provider) {
        this.id_provider = id_provider;
    }

    public String getName_provider() {
        return name_provider;
    }

    public void setName_provider(String name_provider) {
        this.name_provider = name_provider;
    }

    public Provider(int id_provider, String name_provider) {
        this.id_provider = id_provider;
        this.name_provider = name_provider;
    }
}
