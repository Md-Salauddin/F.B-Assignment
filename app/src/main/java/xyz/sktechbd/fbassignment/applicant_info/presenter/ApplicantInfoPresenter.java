package xyz.sktechbd.fbassignment.applicant_info.presenter;

import android.app.Activity;
import android.util.Log;

import com.google.gson.JsonObject;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import xyz.sktechbd.fbassignment.api.AssignmentDBClient;
import xyz.sktechbd.fbassignment.applicant_info.model.ApplicantInfo;
import xyz.sktechbd.fbassignment.applicant_info.view.ApplicantInfoView;

import static xyz.sktechbd.fbassignment.utiles.Tools.isContain;

public class ApplicantInfoPresenter {

    private Activity activity;
    private ApplicantInfoView applicantInfoView;

    public ApplicantInfoPresenter(Activity activity, ApplicantInfoView applicantInfoView) {
        this.activity = activity;
        this.applicantInfoView = applicantInfoView;
    }

    // submit user info into server
    // and get file token id for submit
    // cv pdf file
    public void getFileToken(MultipartBody.Part part, String authToken, ApplicantInfo applicantInfo) {

        applicantInfoView.showProgressBar();

        Call<JsonObject> call = AssignmentDBClient
                .getInstance()
                .getApi()
                .getFileToken(authToken, applicantInfo);

        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {

                if (response.isSuccessful() && response.body() != null) {
                    JsonObject jsonObject = response.body();

                    if (isContain(jsonObject, "cv_file")) {
                        JsonObject cvObject = jsonObject.getAsJsonObject("cv_file");
                        String fileTokenId = cvObject.get("id").getAsString();
                        sendPdfFile(part, authToken, fileTokenId);
                    }

                }
                applicantInfoView.hideProgressBar();
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                applicantInfoView.hideProgressBar();
                Log.i("APPLICANT_INFO", "error_getting_file_token: "+t.getMessage());
            }
        });
    }

    // send pdf file into server
    public void sendPdfFile(MultipartBody.Part part, String authToken, String fileToken) {

        Call<JsonObject> call = AssignmentDBClient
                .getInstance()
                .getApi()
                .sendPdfFile(authToken, fileToken, part);

        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if (response.isSuccessful() && response.body() != null) {
                        JsonObject jsonObject = response.body();

                        // if successfully send show a success message
                        if (isContain(jsonObject, "message")) {
                            String message = jsonObject.get("message").getAsString();
                            applicantInfoView.getFileToken(message, true);
                        }
                }
                applicantInfoView.hideProgressBar();
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                applicantInfoView.hideProgressBar();
                Log.i("APPLICANT_INFO", "error_while_sending_pdf: "+t.getMessage());

            }
        });

    }

}
