package in.indilabz.student_union.response;

import com.google.gson.annotations.SerializedName;

public class UpdateResponse {

    @SerializedName("response_code")
    private Integer responseCode;

    @SerializedName("response_msg")
    private String responseMsg;

    public UpdateResponse(Integer responseCode, String responseMsg) {
        this.responseCode = responseCode;
        this.responseMsg = responseMsg;
    }

    public Integer getResponseCode() {
        return responseCode;
    }

    public String getResponseMsg() {
        return responseMsg;
    }

    public void setResponseCode(Integer responseCode) {
        this.responseCode = responseCode;
    }

    public void setResponseMsg(String responseMsg) {
        this.responseMsg = responseMsg;
    }
}
