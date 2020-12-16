package xyz.sktechbd.fbassignment.api;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static xyz.sktechbd.fbassignment.utiles.ConstantFile.BASE_URL;


public class AssignmentDBClient {

    private static AssignmentDBClient assignmentDBClient;
    private Retrofit retrofit;

    private AssignmentDBClient() {
        OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder();
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        clientBuilder.addInterceptor(loggingInterceptor);

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(clientBuilder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static synchronized AssignmentDBClient getInstance() {
        if (assignmentDBClient == null )
            assignmentDBClient = new AssignmentDBClient();
        return assignmentDBClient;
    }

    public AssignmentDBInterface getApi() {
        return retrofit.create(AssignmentDBInterface.class);
    }


}
