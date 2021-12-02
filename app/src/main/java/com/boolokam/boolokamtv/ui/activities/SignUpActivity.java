package com.boolokam.boolokamtv.ui.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.boolokam.boolokamtv.Provider.PrefManager;
import com.boolokam.boolokamtv.R;
import com.boolokam.boolokamtv.Utils.NetworkState;
import com.boolokam.boolokamtv.api.apiClient;
import com.boolokam.boolokamtv.api.apiRest;
import com.boolokam.boolokamtv.config.Global;
import com.boolokam.boolokamtv.entity.ApiResponse;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class SignUpActivity extends AppCompatActivity {
    EditText editTextName;
    EditText editTextTextEmailAddress;
    EditText editTextTextPassword;
    EditText editTextRePassword;
    TextView loginBtnTV;
    Button registrationBtn;
    private ProgressDialog register_progress;
    String firebaseToken;

    String id_user;
    String name_user;
    String username_user;
    String salt_user;
    String token_user;
    String type_user;
    String image_user;
    String enabled;
    String user_subscribed;
    private PrefManager prf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        init();

    }

    void init() {
        final String registrationType = "email";
        final String photo = "https://lh3.googleusercontent.com/-XdUIqdMkCWA/AAAAAAAAAAI/AAAAAAAAAAA/4252rscbv5M/photo.jpg" ;
        editTextName = findViewById(R.id.editTextName);
        editTextTextEmailAddress = findViewById(R.id.editTextTextEmailAddress);
        editTextTextPassword = findViewById(R.id.editTextTextPassword);
        editTextRePassword = findViewById(R.id.editTextRePassword);
        registrationBtn = findViewById(R.id.registrationBtn);
        loginBtnTV = findViewById(R.id.loginBtnTV);
        registrationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (NetworkState.isOnline(SignUpActivity.this)) {
                    if (editTextName.getText().toString().isEmpty()) {
                        editTextName.setError("Please enter your full name");
                        return;
                    } else {
                        if(editTextName.getText().toString().length()<=2)
                        {
                            editTextName.setError("Please enter valid name");
                            return;
                        }
                        editTextName.setError(null);
                    }

                    if (editTextTextEmailAddress.getText().toString().isEmpty()) {
                        editTextTextEmailAddress.setError("Please enter email address");
                        return;
                    } else {

                        if (!isEmailValid(editTextTextEmailAddress.getText().toString())) {
                            editTextTextEmailAddress.setError("Please enter valid email address");
                            return;
                        } else {
                            editTextTextEmailAddress.setError(null);
                        }
                    }

                    if (!validatePassword(editTextTextPassword)) {
                        editTextTextPassword.setError("Password should be at least 6 characters long");
                        return;
                    }/*else{
                        editTextTextPassword.setError(null);
                    }*/

                    if (!editTextTextPassword.getText().toString().trim().matches(editTextRePassword.getText().toString().trim())) {
                        editTextRePassword.setError("Re-Password is not matching with password");
                        return;
                    } else {
                        editTextRePassword.setError(null);
                    }

                    signUp(editTextTextEmailAddress.getText().toString(),editTextTextPassword.getText().toString(),editTextName.getText().toString(),registrationType,photo);

                } else {
                    Toasty.error(getApplicationContext(), "No Internet Connection", Toast.LENGTH_SHORT, true).show();
                }
            }
        });
        loginBtnTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openLoginPage();
            }
        });
    }

    void openLoginPage()
    {
        Intent intent = new Intent(SignUpActivity.this, SignInActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.enter, R.anim.exit);
        finish();
    }

    public static boolean isEmailValid(String email) {
        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    private boolean validatePassword(EditText passET) {
        if (passET.getText().toString().trim().isEmpty() || passET.getText().toString().length() < 6) {
            passET.setError(getString(R.string.error_short_value));
            return false;
        } else {
            passET.setError(null);
        }
        return true;
    }

    public void signUp(String email, String password, String name, String type, String image) {
        register_progress = ProgressDialog.show(this, null, getResources().getString(R.string.operation_progress), true);
        Retrofit retrofit = apiClient.getClient();
        apiRest service = retrofit.create(apiRest.class);
        Call<ApiResponse> call = service.register(name, email, password, type, image);
        call.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                if (response.body() != null) {
                    if (response.body().getCode() == 200) {

                        id_user = "0";
                        name_user = "x";
                        username_user = "x";
                        salt_user = "0";
                        token_user = "0";
                        type_user = "x";
                        image_user = "x";
                        enabled = "x";
                        user_subscribed = "FALSE";

                        for (int i = 0; i < response.body().getValues().size(); i++) {
                            if (response.body().getValues().get(i).getName().equals("salt")) {
                                salt_user = response.body().getValues().get(i).getValue();
                            }
                            if (response.body().getValues().get(i).getName().equals("token")) {
                                token_user = response.body().getValues().get(i).getValue();
                            }
                            if (response.body().getValues().get(i).getName().equals("id")) {
                                id_user = response.body().getValues().get(i).getValue();
                            }
                            if (response.body().getValues().get(i).getName().equals("name")) {
                                name_user = response.body().getValues().get(i).getValue();
                            }
                            if (response.body().getValues().get(i).getName().equals("type")) {
                                type_user = response.body().getValues().get(i).getValue();
                            }
                            if (response.body().getValues().get(i).getName().equals("username")) {
                                username_user = response.body().getValues().get(i).getValue();
                            }
                            if (response.body().getValues().get(i).getName().equals("url")) {
                                image_user = response.body().getValues().get(i).getValue();
                            }
                            if (response.body().getValues().get(i).getName().equals("enabled")) {
                                enabled = response.body().getValues().get(i).getValue();
                            }
                            if (response.body().getValues().get(i).getName().equals("subscribed")) {
                                user_subscribed = response.body().getValues().get(i).getValue();
                            }
                        }
                        if (enabled.equals("true")) {
                            PrefManager prf = new PrefManager(getApplicationContext());
                            prf.setString("ID_USER", id_user);
                            prf.setString("SALT_USER", salt_user);
                            prf.setString("TOKEN_USER", token_user);
                            prf.setString("NAME_USER", name_user);
                            prf.setString("TYPE_USER", type_user);
                            prf.setString("USERN_USER", username_user);
                            prf.setString("IMAGE_USER", image_user);
                            prf.setString("LOGGED", "TRUE");
                            prf.setString("NEW_SUBSCRIBE_ENABLED", user_subscribed);

                            //String  token = FirebaseInstanceId.getInstance().getToken();
                            FirebaseMessaging.getInstance().getToken()
                                    .addOnCompleteListener(new OnCompleteListener<String>() {
                                        @Override
                                        public void onComplete(@NonNull Task<String> task) {
                                            if (!task.isSuccessful()) {
                                                if (Global.IS_LOGG)
                                                    Log.d(Global.TAG, "Fetching FCM registration token failed", task.getException());
                                                return;
                                            }

                                            // Get new FCM registration token
                                            firebaseToken = task.getResult();
                                            updateToken(Integer.parseInt(id_user), token_user, firebaseToken, name_user);
                                        }
                                    });

                        } else {
                            Toasty.error(getApplicationContext(), getResources().getString(R.string.account_disabled), Toast.LENGTH_SHORT, true).show();
                        }
                    }else{
                        if (register_progress != null)
                            if (register_progress.isShowing())
                                register_progress.dismiss();
                    }
                    if (response.body().getCode() == 500) {
                        if (register_progress != null)
                            if (register_progress.isShowing())
                                register_progress.dismiss();
                        Toasty.error(getApplicationContext(), response.body().getMessage(), Toast.LENGTH_SHORT, true).show();

                    }
                } else {
                    if (register_progress != null)
                        if (register_progress.isShowing())
                            register_progress.dismiss();
                    Toasty.error(getApplicationContext(), "Operation has been cancelled ! ", Toast.LENGTH_SHORT, true).show();
                }

            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                Toasty.error(getApplicationContext(), "Operation has been cancelled ! ", Toast.LENGTH_SHORT, true).show();
                if (register_progress != null)
                    if (register_progress.isShowing())
                        register_progress.dismiss();
            }
        });
    }

    public void updateToken(Integer id, String key, String token, String name) {
        if(register_progress==null)
            register_progress = ProgressDialog.show(this, null, getResources().getString(R.string.operation_progress), true);
        Retrofit retrofit = apiClient.getClient();
        apiRest service = retrofit.create(apiRest.class);
        Call<ApiResponse> call = service.editToken(id, key, token, name);
        call.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                if (response.isSuccessful()) {


                    //Toasty.success(getApplicationContext(), response.body().getMessage(), Toast.LENGTH_SHORT, true).show();
                    if (register_progress != null)
                        if (register_progress.isShowing())
                            register_progress.dismiss();
                    Intent intent = new Intent(SignUpActivity.this, HomeActivity.class);
                    startActivity(intent);
                    finish();
                }
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                Toasty.error(getApplicationContext(), "Operation has been cancelled ! ", Toast.LENGTH_SHORT, true).show();
                if (register_progress != null)
                    if (register_progress.isShowing())
                        register_progress.dismiss();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        openLoginPage();
    }
}