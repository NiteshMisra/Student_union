package in.indilabz.student_union.response;

import com.google.gson.annotations.SerializedName;

public class DiscountInfoResponse {

    @SerializedName("value_alloted")
    private String valueAllotted;

    @SerializedName("discount")
    private String discount;

    @SerializedName("amount")
    private String amount;

    @SerializedName("response_code")
    private Integer responseCode;

    @SerializedName("response_msg")
    private String responseMsg;

    public DiscountInfoResponse(String valueAllotted, String discount, String amount, Integer responseCode, String responseMsg) {
        this.valueAllotted = valueAllotted;
        this.discount = discount;
        this.amount = amount;
        this.responseCode = responseCode;
        this.responseMsg = responseMsg;
    }

    public void setValueAlloted(String valueAlloted) {
        this.valueAllotted = valueAlloted;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public void setResponseCode(Integer responseCode) {
        this.responseCode = responseCode;
    }

    public void setResponseMsg(String responseMsg) {
        this.responseMsg = responseMsg;
    }

    public String getValueAllotted() {
        return valueAllotted;
    }

    public String getDiscount() {
        return discount;
    }

    public String getAmount() {
        return amount;
    }

    public Integer getResponseCode() {
        return responseCode;
    }

    public String getResponseMsg() {
        return responseMsg;
    }
}
