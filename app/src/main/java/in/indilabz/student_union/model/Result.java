package in.indilabz.student_union.model;

import com.google.gson.annotations.SerializedName;

public class Result {

    @SerializedName("phone")
    private String contactNumber;

    @SerializedName("full_name")
    private String fullName;

    @SerializedName("email")
    private String email;

    @SerializedName("dob")
    private String dob;

    @SerializedName("course")
    private String course;

    @SerializedName("course_year")
    private String year;

    @SerializedName("college")
    private String college;

    @SerializedName("father_name")
    private String fatherName;

    @SerializedName("permanent_address")
    private String permanentAddress;

    @SerializedName("current_address")
    private String currentAddress;

    @SerializedName("password")
    private String password;

    @SerializedName("updated_at")
    private String updatedAt;

    @SerializedName("created_at")
    private String createdAt;

    @SerializedName("gender")
    private String gender;

    @SerializedName("job")
    private String job;

    @SerializedName("accommodation")
    private String accommodation;

    @SerializedName("id")
    private Integer id;

    @SerializedName("verified")
    private Integer verified;

    public Result(String contactNumber, String fullName, String email, String dob, String course, String year, String college, String fatherName, String permanentAddress, String currentAddress, String password, String updatedAt, String createdAt, String gender, String job, String accommodation, Integer id, Integer verified) {
        this.contactNumber = contactNumber;
        this.fullName = fullName;
        this.email = email;
        this.dob = dob;
        this.course = course;
        this.year = year;
        this.college = college;
        this.fatherName = fatherName;
        this.permanentAddress = permanentAddress;
        this.currentAddress = currentAddress;
        this.password = password;
        this.updatedAt = updatedAt;
        this.createdAt = createdAt;
        this.gender = gender;
        this.job = job;
        this.accommodation = accommodation;
        this.id = id;
        this.verified = verified;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public String getFullName() {
        return fullName;
    }

    public String getEmail() {
        return email;
    }

    public String getDob() {
        return dob;
    }

    public String getCourse() {
        return course;
    }

    public String getYear() {
        return year;
    }

    public String getCollege() {
        return college;
    }

    public String getFatherName() {
        return fatherName;
    }

    public String getPermanentAddress() {
        return permanentAddress;
    }

    public String getCurrentAddress() {
        return currentAddress;
    }

    public String getPassword() {
        return password;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getGender() {
        return gender;
    }

    public String getJob() {
        return job;
    }

    public String getAccommodation() {
        return accommodation;
    }

    public Integer getId() {
        return id;
    }

    public Integer getVerified() {
        return verified;
    }
}
