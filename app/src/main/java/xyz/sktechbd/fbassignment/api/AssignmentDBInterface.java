package xyz.sktechbd.fbassignment.api;

import com.google.gson.JsonObject;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;
import xyz.sktechbd.fbassignment.applicant_info.model.ApplicantInfo;
import xyz.sktechbd.fbassignment.utiles.ConstantFile;

public interface AssignmentDBInterface {

    @POST(ConstantFile.LOGIN)
    Call<JsonObject> getToken(
            @Body JsonObject userAuthInfo
    );

    @POST(ConstantFile.APPLICANT_ENTITIES)
    Call<JsonObject> getFileToken(
            @Header("Authorization") String authToken,
            @Body ApplicantInfo applicantInfo
    );

    @Multipart
    @PUT(ConstantFile.SEND_PDF)
    Call<JsonObject> sendPdfFile(
            @Header("Authorization") String authToken,
            @Path("FILE_TOKEN_ID") String fileToken,
            @Part MultipartBody.Part file
    );

}
