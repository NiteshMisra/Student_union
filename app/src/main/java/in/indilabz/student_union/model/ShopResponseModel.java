package in.indilabz.student_union.model;

import com.google.gson.annotations.SerializedName;

public class ShopResponseModel {

    @SerializedName("id")
    private Integer id;

    @SerializedName("unique_id")
    private String uniqueId;

    @SerializedName("name")
    private String name;

    @SerializedName("email")
    private String email;

    @SerializedName("phone")
    private String phone;

    @SerializedName("password")
    private String password;

    @SerializedName("category")
    private Integer category;

    @SerializedName("shop_image")
    private String shop_image;

    @SerializedName("current_address")
    private String currentAddress;

    @SerializedName("permanent_address")
    private String permanentAddress;

    @SerializedName("wallet")
    private Float wallet;

    @SerializedName("verified")
    private Integer verified;

    @SerializedName("created_at")
    private String createdAt;

    @SerializedName("updated_at")
    private String updatedAt;

    public ShopResponseModel(Integer id, String uniqueId, String name, String email, String phone, String password, Integer category, String shop_image, String currentAddress, String permanentAddress, Float wallet, Integer verified, String createdAt, String updatedAt) {
        this.id = id;
        this.uniqueId = uniqueId;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.category = category;
        this.shop_image = shop_image;
        this.currentAddress = currentAddress;
        this.permanentAddress = permanentAddress;
        this.wallet = wallet;
        this.verified = verified;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Integer getId() {
        return id;
    }

    public String getUniqueId() {
        return uniqueId;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getPassword() {
        return password;
    }

    public Integer getCategory() {
        return category;
    }

    public String getShop_image() {
        return shop_image;
    }

    public String getCurrentAddress() {
        return currentAddress;
    }

    public String getPermanentAddress() {
        return permanentAddress;
    }

    public Float getWallet() {
        return wallet;
    }

    public Integer getVerified() {
        return verified;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }
}
