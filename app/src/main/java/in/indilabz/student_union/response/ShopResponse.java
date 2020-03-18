package in.indilabz.student_union.response;

import com.google.gson.annotations.SerializedName;

import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import in.indilabz.student_union.model.ShopResult;

public class ShopResponse {

    @SerializedName("success")
    private Boolean success;

    @SerializedName("error")
    private String error;

    @Nullable
    @SerializedName("result")
    private ArrayList<ShopResult> result;

    public ShopResponse(Boolean success, String error, @Nullable ArrayList<ShopResult> result) {
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

    public @Nullable ArrayList<ShopResult> getResult() {
        return result;
    }
}
