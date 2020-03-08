package in.indilabz.student_union.response;

import com.google.gson.annotations.SerializedName;

public class RegisterResponse {

    @SerializedName("response_code")
    private Integer responseCode;

    @SerializedName("contact_number")
    private String contactNumber;

    @SerializedName("full-name")
    private String fullName;

    @SerializedName("gender")
    private String gender;

    @SerializedName("course")
    private String course;

    @SerializedName("email")
    private String email;

    @SerializedName("year")
    private String year;

    @SerializedName("father_name")
    private String fatherName;

    @SerializedName("current_address")
    private String currentAddress;

    @SerializedName("paramanent_address")
    private String permanentAddress;

    @SerializedName("dob")
    private String dob;

    @SerializedName("college")
    private String college;

    @SerializedName("password")
    private String password;

    @SerializedName("response_msg")
    private String responseMsg;

    public RegisterResponse(Integer responseCode, String contactNumber, String fullName, String gender, String course, String email, String year, String fatherName, String currentAddress, String permanentAddress, String dob, String college, String password, String responseMsg) {
        this.responseCode = responseCode;
        this.contactNumber = contactNumber;
        this.fullName = fullName;
        this.gender = gender;
        this.course = course;
        this.email = email;
        this.year = year;
        this.fatherName = fatherName;
        this.currentAddress = currentAddress;
        this.permanentAddress = permanentAddress;
        this.dob = dob;
        this.college = college;
        this.password = password;
        this.responseMsg = responseMsg;
    }

    public String getResponseMsg() {
        return responseMsg;
    }

    public void setResponseMsg(String responseMsg) {
        this.responseMsg = responseMsg;
    }

    public Integer getResponseCode() {
        return responseCode;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public String getFullName() {
        return fullName;
    }

    public String getGender() {
        return gender;
    }

    public String getCourse() {
        return course;
    }

    public String getEmail() {
        return email;
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

    public String getDob() {
        return dob;
    }

    public String getCollege() {
        return college;
    }

    public String getPassword() {
        return password;
    }

    public void setResponseCode(Integer responseCode) {
        this.responseCode = responseCode;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public void setCurrentAddress(String currentAddress) {
        this.currentAddress = currentAddress;
    }

    public void setPermanentAddress(String permanentAddress) {
        this.permanentAddress = permanentAddress;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
