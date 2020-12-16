package xyz.sktechbd.fbassignment.utiles;

import android.app.Activity;
import android.text.TextUtils;
import android.webkit.URLUtil;

import com.google.android.material.textfield.TextInputLayout;
import com.google.gson.JsonObject;

import java.io.File;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.UUID;

import xyz.sktechbd.fbassignment.R;

public class Tools {

    // check for is jsonObject null and has particular value
    public static boolean isContain(JsonObject jsonObject, String key) {
        return jsonObject != null && jsonObject.has(key) ? true : false;
    }


    // form validation
    public static boolean tilValidation(Activity activity, TextInputLayout name, TextInputLayout email, TextInputLayout mobileNo,
                                        TextInputLayout nameOfUniversity, TextInputLayout graduationYear, TextInputLayout cgpa,
                                        TextInputLayout workExperience, TextInputLayout applyIn,
                                        TextInputLayout expectedSalary, TextInputLayout githubProjectUrl,
                                        TextInputLayout cvFile) {

        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

        /*
        * Name text input field validation
        * check, Is name field empty */
        if (TextUtils.isEmpty(name.getEditText().getText())) {
            name.setError(activity.getString(R.string.required));
            return false;
        }
        else {
            name.setErrorEnabled(false);
        }

        /*
         * Email text input field validation
         * check, Is email field empty
         * check, Is email valid */
        if (TextUtils.isEmpty(email.getEditText().getText())) {
            email.setError(activity.getString(R.string.required));
            return false;
        }
        else {
            if (!email.getEditText().getText().toString().trim().matches(emailPattern)) {
                email.setError(activity.getString(R.string.email_error));
                return false;
            }
            else {
                email.setErrorEnabled(false);
            }
        }

        /*
         * Mobile no text input field validation
         * check, Is mobile field empty */
        if (TextUtils.isEmpty(mobileNo.getEditText().getText())) {
            mobileNo.setError(activity.getString(R.string.required));
            return false;
        }
        else {
            mobileNo.setErrorEnabled(false);
        }

        /*
         * Name of university text input field validation
         * check, Is university name of university field empty */
        if (TextUtils.isEmpty(nameOfUniversity.getEditText().getText())) {
            nameOfUniversity.setError(activity.getString(R.string.required));
            return false;
        }
        else {
            nameOfUniversity.setErrorEnabled(false);
        }

        /*
         * Graduation year text input field validation
         * check, Is graduation year field empty
         * check, Is graduation year within 2015 to 2020 */
        if (TextUtils.isEmpty(graduationYear.getEditText().getText())) {
            graduationYear.setError(activity.getString(R.string.required));
            return false;
        }
        else {
            if (!(Integer.valueOf((graduationYear.getEditText().getText()).toString()) >= 2015 && Integer.valueOf((graduationYear.getEditText().getText()).toString()) <= 2020)) {
                graduationYear.setError(activity.getString(R.string.graduation_year_min_max_error));
                return false;
            }
            else {
                graduationYear.setErrorEnabled(false);
            }
        }

        /*
         * CGPA text input field validation
         * check, Is CGPA within 2.0 to 4.0 */
        if (!TextUtils.isEmpty(cgpa.getEditText().getText())) {
            if (!(Double.valueOf((cgpa.getEditText().getText()).toString()) >= 2.0 && Double.valueOf((cgpa.getEditText().getText()).toString()) <= 4.0)) {
                cgpa.setError(activity.getString(R.string.cgpa_min_max_error));
                return false;
            } else {
                cgpa.setErrorEnabled(false);
            }
        }

        /*
         * Work experience filed text input field validation
         * check, Is experience within 0 to 100 */
        if (!TextUtils.isEmpty(workExperience.getEditText().getText())) {
            if (!(Integer.valueOf((workExperience.getEditText().getText()).toString()) >= 0 && Integer.valueOf((workExperience.getEditText().getText()).toString()) <= 100)) {
                workExperience.setError(activity.getString(R.string.experience_min_max_error));
                return false;
            } else {
                workExperience.setErrorEnabled(false);
            }
        }


        /*
         * apply in text input field validation
         * check, Is apply in field empty */
        if (TextUtils.isEmpty(applyIn.getEditText().getText())) {
            applyIn.setError(activity.getString(R.string.required));
            return false;
        }
        else {
            applyIn.setErrorEnabled(false);
        }


        /*
         * Expected salary text input field validation
         * check, Is expected salary field empty
         * check, Is expected salary within 15000 to 60000 */
        if (TextUtils.isEmpty(expectedSalary.getEditText().getText())) {
            expectedSalary.setError(activity.getString(R.string.required));
            return false;
        }
        else {
            if (!(Integer.valueOf((expectedSalary.getEditText().getText()).toString()) >= 15000 && Integer.valueOf((expectedSalary.getEditText().getText()).toString()) <= 60000)) {
                expectedSalary.setError(activity.getString(R.string.expected_salary_min_max_error));
                return false;
            }
            else {
                expectedSalary.setErrorEnabled(false);
            }
        }


        /*
         * Github project url text input field validation
         * check, Is github project url field empty
         * check, Is github project url valid url */
        if (TextUtils.isEmpty(githubProjectUrl.getEditText().getText())) {
            githubProjectUrl.setError(activity.getString(R.string.required));
            return false;
        }
        else {
            if (!URLUtil.isValidUrl(githubProjectUrl.getEditText().getText().toString())) {
                githubProjectUrl.setError(activity.getString(R.string.github_url_error));
                return false;
            }
            else {
                githubProjectUrl.setErrorEnabled(false);
            }
        }


        /*
         * Cv file input field validation
         * check, Is cv file field empty */
        if (TextUtils.isEmpty(cvFile.getEditText().getText())) {
            cvFile.setError(activity.getString(R.string.required));
            return false;
        }
        else {
            cvFile.setErrorEnabled(false);
        }

        return true;
    }

    // generates random UUID
    public static String getUUID() {
        return UUID.randomUUID().toString();
    }

    // unix timestamp in milliseconds
    public static long getUnixTime() {
        return System.currentTimeMillis();
    }

    // get file size
    public static double getFileSizeInMB(File file) {
        double length = file.length();
        double lengthInMb = (length/1000)/1000;

        DecimalFormat df2 = new DecimalFormat("#.##");
        df2.setRoundingMode(RoundingMode.UP);
        return Double.valueOf(df2.format(lengthInMb));
    }

}
