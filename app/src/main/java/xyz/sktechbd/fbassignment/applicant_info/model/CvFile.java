package xyz.sktechbd.fbassignment.applicant_info.model;

import com.google.gson.annotations.SerializedName;

public class CvFile {
    @SerializedName("tsync_id")
    private String fileId;

    public CvFile(String fileId) {
        this.fileId = fileId;
    }

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    @Override
    public String toString() {
        return "CvFile{" +
                "fileId='" + fileId + '\'' +
                '}';
    }
}
