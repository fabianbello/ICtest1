package cl.mingeso.evaluacion2.model;

import com.google.gson.annotations.SerializedName;




public class Product {
    
    @SerializedName("id_product")
    private Integer id_product;

    @SerializedName("code")
    private Integer code;
    
    @SerializedName("name_product")
    private String name_product;
    
    @SerializedName("expiration")
    private String expiration;

    @SerializedName("category")
    private String category;

    @SerializedName("price")
    private Integer price;

    @SerializedName("isVisible")
    private boolean isVisible;



    public boolean isVisible() {
        return this.isVisible;
    }

    public void setIsVisible(boolean isVisible) {
        this.isVisible = isVisible;
    }

    public Integer getId() {
        return id_product;
    }

    public void setId(Integer id) {
        this.id_product = id;
    }

    public Integer getCode() {
        return code;
    }
    public Integer getPrice() {
        return price;
    }
    public void setPrice(Integer price) {
        this.price = price;
    }
    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
    }
    public String getExpiration() {
        return expiration;
    }
    public void setExpiration(String expiration) {
        this.expiration = expiration;
    }
    public String getName() {
        return name_product;
    }
    public void setName(String name) {
        this.name_product = name;
    }
    public void setCode(Integer code) {
        this.code = code;
    }

    
}
