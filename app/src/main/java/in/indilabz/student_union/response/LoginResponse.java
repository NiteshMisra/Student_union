package in.indilabz.student_union.response;

import com.google.gson.annotations.SerializedName;

public class LoginResponse {

    @SerializedName("user_id")
    private String userId;

    @SerializedName("username")
    private String userName;

    @SerializedName("name")
    private String name;

    @SerializedName("user_img")
    private String userImage;

    @SerializedName("student_id")
    private String studentId;

    @SerializedName("student_p_id")
    private String studentPId;

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

    @SerializedName("father_name")
    private String fatherName;

    @SerializedName("current_address")
    private String currentAddress;

    @SerializedName("paramanent_address")
    private String permanentAddress;

    @SerializedName("login_status")
    private Boolean loginStatus;

    @SerializedName("error")
    private String error;

    public LoginResponse(String userId, String userName, String name, String userImage, String studentId, String studentPId, String dob, String gender, String contactNumber, String course, String college, String year, String fatherName, String currentAddress, String permanentAddress, Boolean loginStatus, String error) {
        this.userId = userId;
        this.userName = userName;
        this.name = name;
        this.userImage = userImage;
        this.studentId = studentId;
        this.studentPId = studentPId;
        this.dob = dob;
        this.gender = gender;
        this.contactNumber = contactNumber;
        this.course = course;
        this.college = college;
        this.year = year;
        this.fatherName = fatherName;
        this.currentAddress = currentAddress;
        this.permanentAddress = permanentAddress;
        this.loginStatus = loginStatus;
        this.error = error;
    }

    public String getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public String getName() {
        return name;
    }

    public String getUserImage() {
        return userImage;
    }

    public String getStudentId() {
        return studentId;
    }

    public String getStudentPId() {
        return studentPId;
    }

    public String getDob() {
        return dob;
    }

    public String getGender() {
        return gender;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public String getCourse() {
        return course;
    }

    public String getCollege() {
        return college;
    }

    public String getYear() {
        return year;
    }

    public String getFatherName() {
        return fatherName;
    }

    public String getCurrentAddress() {
        return currentAddress;
    }

    public String getPermanentAddress() {
        return permanentAddress;
    }

    public Boolean getLoginStatus() {
        return loginStatus;
    }

    public String getError() {
        return error;
    }
}
