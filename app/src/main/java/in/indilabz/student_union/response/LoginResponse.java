package in.indilabz.student_union.response;

import com.google.gson.annotations.SerializedName;
import org.jetbrains.annotations.Nullable;
import in.indilabz.student_union.model.Result;

public class LoginResponse {

    @SerializedName("success")
    private Boolean success;

    @SerializedName("error")
    private String error;

    @SerializedName("message")
    private String message;

    @Nullable
    @SerializedName("result")
    private Result result;

    public LoginResponse(Boolean success, String message, String error, @Nullable Result result) {
        this.success = success;
        this.message = message;
        this.error = error;
        this.result = result;
    }

    public Boolean getSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public String getError() {
        return error;
    }

    @Nullable
    public Result getResult() {
        return result;
    }
}
