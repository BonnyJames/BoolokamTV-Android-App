package com.boolokam.boolokamtv.ui.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.boolokam.boolokamtv.Provider.PrefManager;
import com.boolokam.boolokamtv.R;
import com.boolokam.boolokamtv.api.apiClient;
import com.boolokam.boolokamtv.api.apiRest;
import com.boolokam.boolokamtv.config.Global;
import com.boolokam.boolokamtv.entity.ApiResponse;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.Timer;
import java.util.TimerTask;

public class SplashActivity extends AppCompatActivity {

    private PrefManager prf;
    private boolean readyToPurchase = false;
    private static final String LOG_TAG = "iabv3";
    private static final String MERCHANT_ID=null;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        getFirebaseMessaging();


        prf= new PrefManager(getApplicationContext());
       /* ( (RubberLoaderView) findViewById(R.id.loader1)).startLoading();*/
        Timer myTimer = new Timer();
        myTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                // If you want to modify a view in your Activity
                SplashActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        checkAccount();


                    }
                });
            }
        }, 3000);

        prf.setString("ADMIN_REWARDED_ADMOB_ID","");

        prf.setString("ADMIN_INTERSTITIAL_ADMOB_ID","");
        prf.setString("ADMIN_INTERSTITIAL_FACEBOOK_ID","");
        prf.setString("ADMIN_INTERSTITIAL_TYPE","FALSE");
        prf.setInt("ADMIN_INTERSTITIAL_CLICKS",3);

        prf.setString("ADMIN_BANNER_ADMOB_ID","");
        prf.setString("ADMIN_BANNER_FACEBOOK_ID","");
        prf.setString("ADMIN_BANNER_TYPE","FALSE");

        prf.setString("ADMIN_NATIVE_FACEBOOK_ID","");
        prf.setString("ADMIN_NATIVE_ADMOB_ID","");
        prf.setString("ADMIN_NATIVE_LINES","6");
        prf.setString("ADMIN_NATIVE_TYPE","FALSE");
        prf.setString("APP_STRIPE_ENABLED","FALSE");
        prf.setString("APP_PAYPAL_ENABLED","FALSE");
        prf.setString("APP_PAYPAL_CLIENT_ID","");
        prf.setString("APP_CASH_ENABLED","FALSE");
        prf.setString("APP_LOGIN_REQUIRED","FALSE");



    }

    private void getFirebaseMessaging(){
        FirebaseMessaging.getInstance().getToken()
                .addOnCompleteListener(new OnCompleteListener<String>() {
                    @Override
                    public void onComplete(@NonNull Task<String> task) {
                        if (!task.isSuccessful()) {
                            if(Global.IS_LOGG)
                                Log.w(Global.TAG, "Fetching FCM registration token failed", task.getException());
                            return;
                        }

                        // Get new FCM registration token
                        String token = task.getResult();

                        // Log and toast
                      String msg = getString(R.string.msg_token_fmt, token);
                        if(Global.IS_LOGG)
                            Log.d(Global.TAG, msg);
                        //Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
                    }
                });
    }


    private void checkAccount() {

        Integer version = -1;
        try {
            PackageInfo pInfo = this.getPackageManager().getPackageInfo(getPackageName(), 0);
            version = pInfo.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        if (version!=-1){
            Integer id_user = 0;
            if (prf.getString("LOGGED").toString().equals("TRUE")) {
                 id_user = Integer.parseInt(prf.getString("ID_USER"));
            }
            Retrofit retrofit = apiClient.getClient();
            apiRest service = retrofit.create(apiRest.class);
            Call<ApiResponse> call = service.check(version,id_user);
            call.enqueue(new Callback<ApiResponse>() {
                @Override
                public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                    updateTextViews();
                    if (response.isSuccessful()){
                        for (int i = 0; i < response.body().getValues().size(); i++) {
                            if ( response.body().getValues().get(i).getName().equals("ADMIN_REWARDED_ADMOB_ID") ){
                                if (response.body().getValues().get(i).getValue()!=null)
                                    prf.setString("ADMIN_REWARDED_ADMOB_ID",response.body().getValues().get(i).getValue());
                            }
                            if ( response.body().getValues().get(i).getName().equals("ADMIN_INTERSTITIAL_ADMOB_ID") ){
                                if (response.body().getValues().get(i).getValue()!=null)
                                    prf.setString("ADMIN_INTERSTITIAL_ADMOB_ID",response.body().getValues().get(i).getValue());
                            }
                            if ( response.body().getValues().get(i).getName().equals("ADMIN_INTERSTITIAL_FACEBOOK_ID") ){
                                if (response.body().getValues().get(i).getValue()!=null)
                                    prf.setString("ADMIN_INTERSTITIAL_FACEBOOK_ID",response.body().getValues().get(i).getValue());
                            }
                            if ( response.body().getValues().get(i).getName().equals("ADMIN_INTERSTITIAL_TYPE") ){
                                if (response.body().getValues().get(i).getValue()!=null)
                                    prf.setString("ADMIN_INTERSTITIAL_TYPE",response.body().getValues().get(i).getValue());
                            }
                            if ( response.body().getValues().get(i).getName().equals("ADMIN_INTERSTITIAL_CLICKS") ){
                                if (response.body().getValues().get(i).getValue()!=null)
                                    prf.setInt("ADMIN_INTERSTITIAL_CLICKS",Integer.parseInt(response.body().getValues().get(i).getValue()));
                            }
                            if ( response.body().getValues().get(i).getName().equals("ADMIN_BANNER_ADMOB_ID") ){
                                if (response.body().getValues().get(i).getValue()!=null)
                                    prf.setString("ADMIN_BANNER_ADMOB_ID",response.body().getValues().get(i).getValue());
                            }
                            if ( response.body().getValues().get(i).getName().equals("ADMIN_BANNER_FACEBOOK_ID") ){
                                if (response.body().getValues().get(i).getValue()!=null)
                                    prf.setString("ADMIN_BANNER_FACEBOOK_ID",response.body().getValues().get(i).getValue());
                            }
                            if ( response.body().getValues().get(i).getName().equals("ADMIN_BANNER_TYPE") ){
                                if (response.body().getValues().get(i).getValue()!=null)
                                    prf.setString("ADMIN_BANNER_TYPE",response.body().getValues().get(i).getValue());
                            }
                            if ( response.body().getValues().get(i).getName().equals("ADMIN_NATIVE_FACEBOOK_ID") ){
                                if (response.body().getValues().get(i).getValue()!=null)
                                    prf.setString("ADMIN_NATIVE_FACEBOOK_ID",response.body().getValues().get(i).getValue());
                            }
                            if ( response.body().getValues().get(i).getName().equals("ADMIN_NATIVE_ADMOB_ID") ){
                                if (response.body().getValues().get(i).getValue()!=null)
                                    prf.setString("ADMIN_NATIVE_ADMOB_ID",response.body().getValues().get(i).getValue());
                            }
                            if ( response.body().getValues().get(i).getName().equals("ADMIN_NATIVE_LINES") ){
                                if (response.body().getValues().get(i).getValue()!=null)
                                    prf.setString("ADMIN_NATIVE_LINES",response.body().getValues().get(i).getValue());
                            }
                            if ( response.body().getValues().get(i).getName().equals("ADMIN_NATIVE_TYPE") ){
                                if (response.body().getValues().get(i).getValue()!=null)
                                    prf.setString("ADMIN_NATIVE_TYPE",response.body().getValues().get(i).getValue());
                            }
                            if ( response.body().getValues().get(i).getName().equals("APP_CURRENCY") ){
                                if (response.body().getValues().get(i).getValue()!=null)
                                    prf.setString("APP_CURRENCY",response.body().getValues().get(i).getValue());
                            }
                            if ( response.body().getValues().get(i).getName().equals("APP_CASH_ACCOUNT") ){
                                if (response.body().getValues().get(i).getValue()!=null)
                                    prf.setString("APP_CASH_ACCOUNT",response.body().getValues().get(i).getValue());
                            }
                            if ( response.body().getValues().get(i).getName().equals("APP_STRIPE_PUBLIC_KEY") ){
                                if (response.body().getValues().get(i).getValue()!=null)
                                    prf.setString("APP_STRIPE_PUBLIC_KEY",response.body().getValues().get(i).getValue());
                            }

                            if ( response.body().getValues().get(i).getName().equals("APP_CASH_ENABLED") ){
                                if (response.body().getValues().get(i).getValue()!=null)
                                    prf.setString("APP_CASH_ENABLED",response.body().getValues().get(i).getValue());
                            }

                            if ( response.body().getValues().get(i).getName().equals("APP_PAYPAL_ENABLED") ){
                                if (response.body().getValues().get(i).getValue()!=null)
                                    prf.setString("APP_PAYPAL_ENABLED",response.body().getValues().get(i).getValue());
                            }

                            if ( response.body().getValues().get(i).getName().equals("APP_PAYPAL_CLIENT_ID") ){
                                if (response.body().getValues().get(i).getValue()!=null)
                                    prf.setString("APP_PAYPAL_CLIENT_ID",response.body().getValues().get(i).getValue());
                            }
                            if ( response.body().getValues().get(i).getName().equals("APP_STRIPE_ENABLED") ){
                                if (response.body().getValues().get(i).getValue()!=null)
                                    prf.setString("APP_STRIPE_ENABLED",response.body().getValues().get(i).getValue());
                            }

                            if ( response.body().getValues().get(i).getName().equals("APP_LOGIN_REQUIRED") ){
                                if (response.body().getValues().get(i).getValue()!=null)
                                    prf.setString("APP_LOGIN_REQUIRED",response.body().getValues().get(i).getValue());
                            }
                            if ( response.body().getValues().get(i).getName().equals("subscription") ){
                                if (response.body().getValues().get(i).getValue()!=null)
                                    prf.setString("NEW_SUBSCRIBE_ENABLED",response.body().getValues().get(i).getValue());
                            }



                        }
                        if (response.body().getValues().get(1).getValue().equals("403")){
                            prf.remove("ID_USER");
                            prf.remove("SALT_USER");
                            prf.remove("TOKEN_USER");
                            prf.remove("NAME_USER");
                            prf.remove("TYPE_USER");
                            prf.remove("USERN_USER");
                            prf.remove("IMAGE_USER");
                            prf.remove("LOGGED");
                            prf.remove("NEW_SUBSCRIBE_ENABLED");
                            Toasty.error(getApplicationContext(),getResources().getString(R.string.account_disabled), Toast.LENGTH_SHORT, true).show();
                        }
                        if (response.body().getCode().equals(200)) {
                            redirect();
                        }else if (response.body().getCode().equals(202)) {
                            String title_update=response.body().getValues().get(0).getValue();
                            String featurs_update=response.body().getMessage();
                            View v = (View)  getLayoutInflater().inflate(R.layout.update_message,null);
                            TextView update_text_view_title=(TextView) v.findViewById(R.id.update_text_view_title);
                            TextView update_text_view_updates=(TextView) v.findViewById(R.id.update_text_view_updates);
                            update_text_view_title.setText(title_update);
                            update_text_view_updates.setText(featurs_update);
                            AlertDialog.Builder builder;
                            builder = new AlertDialog.Builder(SplashActivity.this);
                            builder.setTitle("New Update")
                                    //.setMessage(response.body().getValue())
                                    .setView(v)
                                    .setPositiveButton(getResources().getString(R.string.update_now), new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int which) {
                                            final String appPackageName=getApplication().getPackageName();
                                            try {
                                                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName)));
                                            } catch (android.content.ActivityNotFoundException anfe) {
                                                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://play.google.com/store/apps/details?id=" + appPackageName)));
                                            }
                                            finish();
                                        }
                                    })
                                    .setNegativeButton(getResources().getString(R.string.skip), new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int which) {
                                            redirect();
                                        }
                                    })
                                    .setCancelable(false)
                                    .setIcon(R.drawable.ic_update)
                                    .show();
                        } else {
                            redirect();
                        }
                    }else {
                        redirect();
                    }
                }
                @Override
                public void onFailure(Call<ApiResponse> call, Throwable t) {
                    updateTextViews();

                }
            });
        }else{
            redirect();
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private void updateTextViews() {
        PrefManager prf = new PrefManager(getApplicationContext());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

            super.onActivityResult(requestCode, resultCode, data);
    }
    public void redirect(){
        if (!prf.getString("first").equals("true")) {
            Intent intent = new Intent(SplashActivity.this,IntroActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.enter, R.anim.exit);
            finish();
            prf.setString("first","true");
        }else{
            if (prf.getString("APP_LOGIN_REQUIRED").toString().equals("TRUE")){
                if (prf.getString("LOGGED").toString().equals("TRUE")){
                    Intent intent = new Intent(SplashActivity.this,HomeActivity.class);
                    startActivity(intent);
                    overridePendingTransition(R.anim.enter, R.anim.exit);
                    finish();
                }else{
                    Intent intent= new Intent(SplashActivity.this, LoginActivity.class);
                    startActivity(intent);
                    overridePendingTransition(R.anim.slide_up, R.anim.slide_down);
                    finish();
                }
            }else{
                Intent intent = new Intent(SplashActivity.this,HomeActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.enter, R.anim.exit);
                finish();
            }
        }

    }

}
