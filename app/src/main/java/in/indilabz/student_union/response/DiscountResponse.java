package in.indilabz.student_union.response;

import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;

import in.indilabz.student_union.model.Shop;

public class DiscountResponse {

    @SerializedName("result")
    private ArrayList<Shop> results;

    @SerializedName("response_code")
    private Integer responseCode;

    @SerializedName("response_msg")
    private String responseMsg;

    public DiscountResponse(ArrayList<Shop> results, Integer responseCode, String responseMsg) {
        this.results = results;
        this.responseCode = responseCode;
        this.responseMsg = responseMsg;
    }

    public void setResults(ArrayList<Shop> results) {
        this.results = results;
    }

    public void setResponseCode(Integer responseCode) {
        this.responseCode = responseCode;
    }

    public void setResponseMsg(String responseMsg) {
        this.responseMsg = responseMsg;
    }

    public ArrayList<Shop> getResults() {
        return results;
    }

    public Integer getResponseCode() {
        return responseCode;
    }

    public String getResponseMsg() {
        return responseMsg;
    }
}

class result {

    @SerializedName("discount")
    private String discount;

    @SerializedName("expiry_on")
    private String expiryOn;

    @SerializedName("shop_name")
    private String shopName;

    @SerializedName("contact_number")
    private String contactNumber;

    @SerializedName("category")
    private String category;

    @SerializedName("current_address")
    private String currentAddress;

    @SerializedName("user_img")
    private String userImage;

    public result(String discount, String expiryOn, String shopName, String contactNumber, String category, String currentAddress, String userImage) {
        this.discount = discount;
        this.expiryOn = expiryOn;
        this.shopName = shopName;
        this.contactNumber = contactNumber;
        this.category = category;
        this.currentAddress = currentAddress;
        this.userImage = userImage;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public void setExpiryOn(String expiryOn) {
        this.expiryOn = expiryOn;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setCurrentAddress(String currentAddress) {
        this.currentAddress = currentAddress;
    }

    public void setUserImage(String userImage) {
        this.userImage = userImage;
    }

    public String getDiscount() {
        return discount;
    }

    public String getExpiryOn() {
        return expiryOn;
    }

    public String getShopName() {
        return shopName;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public String getCategory() {
        return category;
    }

    public String getCurrentAddress() {
        return currentAddress;
    }

    public String getUserImage() {
        return userImage;
    }
}
