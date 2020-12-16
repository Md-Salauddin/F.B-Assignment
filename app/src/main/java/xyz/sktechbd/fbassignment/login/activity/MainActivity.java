package xyz.sktechbd.fbassignment.login.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.JsonObject;

import xyz.sktechbd.fbassignment.applicant_info.activity.ApplicantInfoActivity;
import xyz.sktechbd.fbassignment.R;
import xyz.sktechbd.fbassignment.login.presenter.LoginPresenter;
import xyz.sktechbd.fbassignment.login.view.LoginView;

import static xyz.sktechbd.fbassignment.utiles.ConstantFile.AUTH_TOKEN_KEY;

public class MainActivity extends AppCompatActivity implements LoginView {

    private ProgressBar pbLogin;
    private TextView tvError;

    private LoginPresenter loginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

    }

    private void init() {
        pbLogin = findViewById(R.id.pb_login);
        tvError = findViewById(R.id.tv_error);

        loginPresenter = new LoginPresenter(this, MainActivity.this);
        loginPresenter.getToken(getAuthJsonParam());
    }


    @Override
    public void login(String message, boolean isSuccess) {

        if (isSuccess) {
            startActivity(new Intent(MainActivity.this, ApplicantInfoActivity.class).putExtra(AUTH_TOKEN_KEY, message));
            finish();
        }
        else {
            tvError.setVisibility(View.VISIBLE);
            tvError.setText(message);
        }

    }


    @Override
    public void hideProgressBar() {
        pbLogin.setVisibility(View.GONE);
    }


    // create an application/json type raw body
    public JsonObject getAuthJsonParam() {

        /*
         * Credentials
         * Username: salauddin.csese@gmail.com
         * Password: g8RhHdc11*/
        JsonObject paramObject = new JsonObject();

        paramObject.addProperty("username", "salauddin.csese@gmail.com");
        paramObject.addProperty("password", "g8RhHdc11");

        return paramObject;
    }

}