package xyz.sktechbd.fbassignment.applicant_info.model;

import com.google.gson.annotations.SerializedName;

public class ApplicantInfo {

    @SerializedName("tsync_id")
    private String userId;
    private String name;
    private String email;
    @SerializedName("phone")
    private String mobileNo;
    @SerializedName("full_address")
    private String address;

    @SerializedName("name_of_university")
    private String universityName;
    @SerializedName("graduation_year")
    private String graduationYear;
    private double cgpa;

    @SerializedName("current_work_place_name")
    private String companyName;
    @SerializedName("experience_in_months")
    private String workExperience;

    @SerializedName("applying_in")
    private String applyIn;
    @SerializedName("expected_salary")
    private int expectedSalary;
    @SerializedName("field_buzz_reference")
    private String fieldBuzzReference;
    @SerializedName("github_project_url")
    private String githubProjectLink;
    @SerializedName("cv_file")
    private CvFile cvFile;

    @SerializedName("on_spot_update_time")
    private long updateTime;
    @SerializedName("on_spot_creation_time")
    private long creationTime;


    public ApplicantInfo(String userId, String name, String email, String mobileNo, String address,
                         String universityName, String graduationYear, double cgpa,
                         String companyName, String workExperience,
                         String applyIn, int expectedSalary, String fieldBuzzReference,
                         String githubProjectLink, CvFile cvFile,
                         long updateTime, long creationTime) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.mobileNo = mobileNo;
        this.address = address;
        this.universityName = universityName;
        this.graduationYear = graduationYear;
        this.cgpa = cgpa;
        this.companyName = companyName;
        this.workExperience = workExperience;
        this.applyIn = applyIn;
        this.expectedSalary = expectedSalary;
        this.fieldBuzzReference = fieldBuzzReference;
        this.githubProjectLink = githubProjectLink;
        this.cvFile = cvFile;
        this.updateTime = updateTime;
        this.creationTime = creationTime;
    }


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getUniversityName() {
        return universityName;
    }

    public void setUniversityName(String universityName) {
        this.universityName = universityName;
    }

    public String getGraduationYear() {
        return graduationYear;
    }

    public void setGraduationYear(String graduationYear) {
        this.graduationYear = graduationYear;
    }

    public double getCgpa() {
        return cgpa;
    }

    public void setCgpa(double cgpa) {
        this.cgpa = cgpa;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getWorkExperience() {
        return workExperience;
    }

    public void setWorkExperience(String workExperience) {
        this.workExperience = workExperience;
    }

    public String getApplyIn() {
        return applyIn;
    }

    public void setApplyIn(String applyIn) {
        this.applyIn = applyIn;
    }

    public int getExpectedSalary() {
        return expectedSalary;
    }

    public void setExpectedSalary(int expectedSalary) {
        this.expectedSalary = expectedSalary;
    }

    public String getFieldBuzzReference() {
        return fieldBuzzReference;
    }

    public void setFieldBuzzReference(String fieldBuzzReference) {
        this.fieldBuzzReference = fieldBuzzReference;
    }

    public String getGithubProjectLink() {
        return githubProjectLink;
    }

    public void setGithubProjectLink(String githubProjectLink) {
        this.githubProjectLink = githubProjectLink;
    }

    public CvFile getCvFile() {
        return cvFile;
    }

    public void setCvFile(CvFile cvFile) {
        this.cvFile = cvFile;
    }

    public long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(long updateTime) {
        this.updateTime = updateTime;
    }

    public long getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(long creationTime) {
        this.creationTime = creationTime;
    }


    @Override
    public String toString() {
        return "ApplicantInfo{" +
                "userId='" + userId + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", mobileNo='" + mobileNo + '\'' +
                ", address='" + address + '\'' +
                ", universityName='" + universityName + '\'' +
                ", graduationYear='" + graduationYear + '\'' +
                ", cgpa=" + cgpa +
                ", companyName='" + companyName + '\'' +
                ", workExperience='" + workExperience + '\'' +
                ", applyIn='" + applyIn + '\'' +
                ", expectedSalary=" + expectedSalary +
                ", fieldBuzzReference='" + fieldBuzzReference + '\'' +
                ", githubProjectLink='" + githubProjectLink + '\'' +
                ", cvFile=" + cvFile +
                ", updateTime=" + updateTime +
                ", creationTime=" + creationTime +
                '}';
    }


}
