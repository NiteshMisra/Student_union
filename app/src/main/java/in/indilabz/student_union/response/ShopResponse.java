package in.indilabz.student_union.response;

import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import in.indilabz.student_union.model.ShopResult;

public class ShopResponse {

    @SerializedName("success")
    private Boolean success;

    @SerializedName("error")
    private String error;

    @SerializedName("result")
    private ArrayList<ShopResult> result;

    public ShopResponse(Boolean success, String error, ArrayList<ShopResult> result) {
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

    public ArrayList<ShopResult> getResult() {
        return result;
    }
}
