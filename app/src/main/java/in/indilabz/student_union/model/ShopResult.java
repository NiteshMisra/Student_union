package in.indilabz.student_union.model;

import com.google.gson.annotations.SerializedName;

public class ShopResult {

    @SerializedName("id")
    private Integer id;

    @SerializedName("student")
    private Integer studentId;

    @SerializedName("shop")
    private ShopResponseModel shopResponseModel;

    @SerializedName("discount")
    private Integer discount;

    @SerializedName("allowed_discount")
    private Integer allowedDiscount;

    @SerializedName("availed")
    private Integer availed;

    @SerializedName("created_at")
    private String createdAt;

    @SerializedName("updated_at")
    private String updatedAt;

    public ShopResult(Integer id, Integer studentId, ShopResponseModel shopResponseModel, Integer discount, Integer allowedDiscount, Integer availed, String createdAt, String updatedAt) {
        this.id = id;
        this.studentId = studentId;
        this.shopResponseModel = shopResponseModel;
        this.discount = discount;
        this.allowedDiscount = allowedDiscount;
        this.availed = availed;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Integer getId() {
        return id;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public ShopResponseModel getShopResponseModel() {
        return shopResponseModel;
    }

    public Integer getDiscount() {
        return discount;
    }

    public Integer getAllowedDiscount() {
        return allowedDiscount;
    }

    public Integer getAvailed() {
        return availed;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }
}
