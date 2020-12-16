package xyz.sktechbd.fbassignment.applicant_info.view;

public interface ApplicantInfoView {
    void getFileToken(String message, boolean isFirstTime);
    void showProgressBar();
    void hideProgressBar();
}
