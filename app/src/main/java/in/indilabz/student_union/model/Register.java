package in.indilabz.student_union.model;

public class Register {

    private String phone;
    private String fullname;
    private String course;
    private String email;
    private String year;
    private String fName;
    private String curAddress;
    private String perAddress;
    private String dob;
    private String college;
    private String password;
    private String gender;
    private String job;
    private String accommodation;

    public Register(String phone, String fullname, String course, String email, String year, String fName, String curAddress, String perAddress, String dob, String college, String password, String gender, String job, String accommodation) {
        this.phone = phone;
        this.fullname = fullname;
        this.course = course;
        this.email = email;
        this.year = year;
        this.fName = fName;
        this.curAddress = curAddress;
        this.perAddress = perAddress;
        this.dob = dob;
        this.college = college;
        this.password = password;
        this.gender = gender;
        this.job = job;
        this.accommodation = accommodation;
    }

    public String getPhone() {
        return phone;
    }

    public String getFullname() {
        return fullname;
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

    public String getFName() {
        return fName;
    }

    public String getCurAddress() {
        return curAddress;
    }

    public String getPerAddress() {
        return perAddress;
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

    public String getGender() {
        return gender;
    }

    public String getJob() {
        return job;
    }

    public String getAccommodation() {
        return accommodation;
    }
}
