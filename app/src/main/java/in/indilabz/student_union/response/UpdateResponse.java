package in.indilabz.student_union.response;

import com.google.gson.annotations.SerializedName;

public class UpdateResponse {

    @SerializedName("success")
    private Boolean success;

    @SerializedName("error")
    private String error;

    @SerializedName("result")
    private Integer result;

    public UpdateResponse(Boolean success, String error, Integer result) {
        this.success = success;
        this.error = error;
        this.result = result;
    }

    public Boolean getSuccess() {
        return success;
    }

    public String getError() {
        return error;
    }

    public Integer getResult() {
        return result;
    }
}
