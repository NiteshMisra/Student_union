package in.indilabz.student_union.response;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ProfileResponse {

    @SerializedName("result")
    private ArrayList<result2> results;

    @SerializedName("response_code")
    private Integer responseCode;

    @SerializedName("response_msg")
    private String responseMsg;

    public ProfileResponse(ArrayList<result2> results, Integer responseCode, String responseMsg) {
        this.results = results;
        this.responseCode = responseCode;
        this.responseMsg = responseMsg;
    }

    public void setResults(ArrayList<result2> results) {
        this.results = results;
    }

    public void setResponseCode(Integer responseCode) {
        this.responseCode = responseCode;
    }

    public void setResponseMsg(String responseMsg) {
        this.responseMsg = responseMsg;
    }

    public ArrayList<result2> getResults() {
        return results;
    }

    public Integer getResponseCode() {
        return responseCode;
    }

    public String getResponseMsg() {
        return responseMsg;
    }
}

class result2 {

    @SerializedName("id")
    private Integer id;

    @SerializedName("student_id")
    private String studentId;

    @SerializedName("user_id")
    private Integer userId;

    @SerializedName("name")
    private String name;

    @SerializedName("dob")
    private String dob;

    @SerializedName("gender")
    private String gender;

    @SerializedName("contact_number")
    private String contactNumber;

    @SerializedName("course")
    private String course;

    @SerializedName("college")
    private String college;

    @SerializedName("year")
    private String year;

    @SerializedName("current_address")
    private String currentAddress;

    @SerializedName("father_name")
    private Integer fatherName;

    @SerializedName("paramanent_address")
    private String permanentAddress;

    @SerializedName("user_img")
    private String userImage;

    @SerializedName("created_on")
    private String createdOn;

    @SerializedName("is_active")
    private String isActive;

    @SerializedName("is_deleted")
    private String isDeleted;

    @SerializedName("updated_on")
    private String updatedOn;

    public result2(Integer id, String studentId, Integer userId, String name, String dob, String gender, String contactNumber, String course, String college, String year, String currentAddress, Integer fatherName, String permanentAddress, String userImage, String createdOn, String isActive, String isDeleted, String updatedOn) {
        this.id = id;
        this.studentId = studentId;
        this.userId = userId;
        this.name = name;
        this.dob = dob;
        this.gender = gender;
        this.contactNumber = contactNumber;
        this.course = course;
        this.college = college;
        this.year = year;
        this.currentAddress = currentAddress;
        this.fatherName = fatherName;
        this.permanentAddress = permanentAddress;
        this.userImage = userImage;
        this.createdOn = createdOn;
        this.isActive = isActive;
        this.isDeleted = isDeleted;
        this.updatedOn = updatedOn;
    }
}
