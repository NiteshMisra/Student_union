package in.indilabz.student_union.response;

import com.google.gson.annotations.SerializedName;
import org.jetbrains.annotations.Nullable;
import in.indilabz.student_union.model.Result;

public class RegisterResponse {

    @SerializedName("success")
    private Boolean success;

    @SerializedName("error")
    private String error;

    @SerializedName("message")
    private String message;

    @Nullable
    @SerializedName("result")
    private Result result;

    public RegisterResponse(Boolean success, String error, String message, @Nullable Result result) {
        this.success = success;
        this.error = error;
        this.message = message;
        this.result = result;
    }

    public Boolean getSuccess() {
        return success;
    }

    public String getError() {
        return error;
    }

    public String getMessage() {
        return message;
    }

    @Nullable
    public Result getResult() {
        return result;
    }
}