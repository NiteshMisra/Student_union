package in.indilabz.student_union.response;

import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import in.indilabz.student_union.model.CategoryResult;

public class CategoryResponse {

    @SerializedName("success")
    private Boolean success;

    @SerializedName("error")
    private String error;

    @SerializedName("result")
    private ArrayList<CategoryResult> categoryResults;

    public CategoryResponse(Boolean success, String error, ArrayList<CategoryResult> categoryResults) {
        this.success = success;
        this.error = error;
        this.categoryResults = categoryResults;
    }

    public Boolean getSuccess() {
        return success;
    }

    public String getError() {
        return error;
    }

    public ArrayList<CategoryResult> getCategoryResults() {
        return categoryResults;
    }
}
