package xyz.sktechbd.fbassignment.login.presenter;

import android.app.Activity;

import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import xyz.sktechbd.fbassignment.R;
import xyz.sktechbd.fbassignment.api.AssignmentDBClient;
import xyz.sktechbd.fbassignment.login.view.LoginView;

import static xyz.sktechbd.fbassignment.utiles.Tools.isContain;

public class LoginPresenter {

    private LoginView loginView;
    private Activity activity;

    public LoginPresenter(LoginView loginView, Activity activity) {
        this.loginView = loginView;
        this.activity = activity;
    }

    public void getToken(JsonObject userAuthInfo) {

        Call<JsonObject> call = AssignmentDBClient
                .getInstance()
                .getApi()
                .getToken(userAuthInfo);

        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {

                if (response.isSuccessful() && response.body() != null) {

                    JsonObject jsonObject = response.body();

                    if (isContain(jsonObject, "success")) {

                        boolean isSuccess = jsonObject.get("success").getAsBoolean();
                        // if success is true than it has token
                        // and ready to go applicantInfo activity
                        if (isSuccess) {
                            String token = jsonObject.get("token").getAsString();
                            loginView.login(token, isSuccess);
                        }

                    }
                }
                else {
                    loginView.login(activity.getString(R.string.api_error), false);
                }

                loginView.hideProgressBar();

            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                loginView.hideProgressBar();
            }

        });

    }

}
