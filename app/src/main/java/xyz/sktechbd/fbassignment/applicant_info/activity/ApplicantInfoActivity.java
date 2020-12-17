package xyz.sktechbd.fbassignment.applicant_info.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import xyz.sktechbd.fbassignment.R;
import xyz.sktechbd.fbassignment.applicant_info.model.ApplicantInfo;
import xyz.sktechbd.fbassignment.applicant_info.model.CvFile;
import xyz.sktechbd.fbassignment.applicant_info.presenter.ApplicantInfoPresenter;
import xyz.sktechbd.fbassignment.applicant_info.view.ApplicantInfoView;

import static xyz.sktechbd.fbassignment.utiles.ConstantFile.AUTH_TOKEN_KEY;
import static xyz.sktechbd.fbassignment.utiles.ConstantFile.PDF_REQUEST_CODE;
import static xyz.sktechbd.fbassignment.utiles.ConstantFile.STORAGE_PERMISSION_CODE;
import static xyz.sktechbd.fbassignment.utiles.Tools.getFileSizeInMB;
import static xyz.sktechbd.fbassignment.utiles.Tools.getUUID;
import static xyz.sktechbd.fbassignment.utiles.Tools.getUnixTime;
import static xyz.sktechbd.fbassignment.utiles.Tools.tilValidation;
import static xyz.sktechbd.fbassignment.utiles.UriUtils.getPathFromUri;

public class ApplicantInfoActivity extends AppCompatActivity
        implements View.OnClickListener, ApplicantInfoView {

    private TextInputLayout tilName, tilEmail, tilMobileNo, tilAddress;
    private TextInputLayout tilNameOfUniversity, tilGraduationYear, tilCGPA;
    private TextInputLayout tilWorkExperience, tilCompanyName;
    private TextInputLayout tilApplyIn, tilExpectedSalary, tilFieldBuzzReference, tilGithubProjectUrl, tilCvFile;
    private TextInputEditText etCvFile;

    private Button btnSubmit;
    private ProgressBar pbSubmit;

    private String authToken;

    private ApplicantInfoPresenter applicantInfoPresenter;
    private MultipartBody.Part part;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employe_info);

        init();
        getAuthToken();
        //testSetValue();
        setApplyInValue();
        requestStoragePermission();

        btnSubmit.setOnClickListener(this);
        etCvFile.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.btn_submit:

                if (tilValidation(this, tilName, tilEmail, tilMobileNo,
                        tilNameOfUniversity, tilGraduationYear, tilCGPA,
                        tilWorkExperience, tilApplyIn, tilExpectedSalary, tilGithubProjectUrl,
                        tilCvFile)) {

                    ApplicantInfo applicantInfo = new ApplicantInfo(getUUID(), tilName.getEditText().getText().toString(), tilEmail.getEditText().getText().toString(), tilMobileNo.getEditText().getText().toString(), tilAddress.getEditText().getText().toString(),
                            tilNameOfUniversity.getEditText().getText().toString(), tilGraduationYear.getEditText().getText().toString(), Double.valueOf(tilCGPA.getEditText().getText().toString()),
                            tilCompanyName.getEditText().getText().toString(), tilWorkExperience.getEditText().getText().toString(),
                            tilApplyIn.getEditText().getText().toString(), Integer.valueOf(tilExpectedSalary.getEditText().getText().toString()),
                            tilFieldBuzzReference.getEditText().getText().toString(), tilGithubProjectUrl.getEditText().getText().toString(), new CvFile(getUUID()),
                            getUnixTime(), getUnixTime());
                    applicantInfoPresenter.getFileToken(part, authToken, applicantInfo);

                    Log.i("APPLICANT_INFO", applicantInfo.toString());
                }
                break;

            case R.id.et_cv_file:
                choosePdfFile();
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && requestCode == PDF_REQUEST_CODE) {
            Uri uri = data.getData();
            String path = getPathFromUri(this, uri);

            // check file size within 4mb or not
            if (getFileSizeInMB(new File(path)) <= 4.0) {
                tilCvFile.setErrorEnabled(false);
                tilCvFile.getEditText().setText(new File(path).getName());

                //Parsing any Media type file
                RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), new File(path));
                //part = MultipartBody.Part.createFormData("file", new File(path).getName(), requestFile);
                part = MultipartBody.Part.createFormData("file", new File(path).getName(), requestFile);
            }
            else {
                tilCvFile.setError(getString(R.string.cv_error));
            }

            Log.i("APPLICANT_INFO", "pdf_file_path: "+path);
            Log.i("APPLICANT_INFO", "pdf_file_size: "+getFileSizeInMB(new File(path)));
            Log.i("APPLICANT_INFO", "pdf_file_name: "+new File(path).getName());
        }

    }

    @Override
    public void getFileToken(String message, boolean isFirstTime) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showProgressBar() {
        pbSubmit.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        pbSubmit.setVisibility(View.GONE);
    }

    /**
     * method: */
    private void init() {
        tilName = findViewById(R.id.tf_name);
        tilEmail = findViewById(R.id.tf_email);
        tilMobileNo = findViewById(R.id.tf_mobile_no);
        tilAddress = findViewById(R.id.tf_address);

        tilNameOfUniversity = findViewById(R.id.tf_name_of_university);
        tilGraduationYear = findViewById(R.id.tf_graduation_year);
        tilCGPA = findViewById(R.id.tf_cgpa);

        tilWorkExperience = findViewById(R.id.tf_experience_in_months);
        tilCompanyName = findViewById(R.id.tf_work_place_name);

        tilApplyIn = findViewById(R.id.tf_applying_in);
        tilExpectedSalary = findViewById(R.id.tf_expected_salary);
        tilFieldBuzzReference = findViewById(R.id.tf_field_buzz_ref);
        tilGithubProjectUrl = findViewById(R.id.tf_github_url);
        tilCvFile = findViewById(R.id.tf_cv_file);
        etCvFile = findViewById(R.id.et_cv_file);

        btnSubmit = findViewById(R.id.btn_submit);
        pbSubmit = findViewById(R.id.pb_submit);

        applicantInfoPresenter = new ApplicantInfoPresenter(ApplicantInfoActivity.this, this);
    }

    // auth token for get file token
    // and update applicant information
    private void getAuthToken() {
        Intent intent = getIntent();
        authToken = "Token "+intent.getStringExtra(AUTH_TOKEN_KEY);
    }

    // set value into applyIn field
    // there will be two option
    private void setApplyInValue() {
        AutoCompleteTextView autoCompleteTextView = (AutoCompleteTextView) tilApplyIn.getEditText();
        String[] items = {"Mobile", "Backend"};
        ArrayAdapter adapter = new ArrayAdapter(ApplicantInfoActivity.this, R.layout.apply_in_list_item, items);
        autoCompleteTextView.setAdapter(adapter);
    }

    // initially set value into edit text
    // for test purpose
    void testSetValue() {
        tilName.getEditText().setText("Md. Salauddin");
        tilEmail.getEditText().setText("salauddin.csese@gmail.com");
        tilAddress.getEditText().setText("");
        tilMobileNo.getEditText().setText("");
        tilNameOfUniversity.getEditText().setText("");
        tilGraduationYear.getEditText().setText("");
        tilCGPA.getEditText().setText("");

        tilCompanyName.getEditText().setText("");
        tilWorkExperience.getEditText().setText("");
        tilExpectedSalary.getEditText().setText("");
        tilGithubProjectUrl.getEditText().setText("");
    }

    // requesting permission
    private void requestStoragePermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED)
            return;

        if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_EXTERNAL_STORAGE)) {
            //If user denied the permission, this block will be executed
        }
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, STORAGE_PERMISSION_CODE);
    }

    // choose pdf file
    private void choosePdfFile() {
        Intent intent = new Intent();
        intent.setType("application/pdf");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Pdf"), PDF_REQUEST_CODE);
    }


}