package com.example.peter.project2;

import android.R.*;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;


import com.example.peter.project2.RetrofitAPI.APIClient;
import com.example.peter.project2.RetrofitAPI.UserAPI;
import com.example.peter.project2.Service.SaveLocal;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login extends AppCompatActivity implements View.OnClickListener, GoogleApiClient.OnConnectionFailedListener {
    TextView tv_boqua;
    private GoogleApiClient mGoogleApiClient;
    int RC_SIGN_IN = 001;
    TextView txtName, txtEmail;
    //    ImageView imageView;
//    private static final String TAG = "peterTruong";
//    private FirebaseAuth mAuth;
//    public FirebaseUser user;
    UserAPI userAPI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        tv_boqua = findViewById(R.id.tv_boqua);
        txtName = findViewById(R.id.txtusername);
//        txtEmail = findViewById(R.id.txtEmail);
//        imageView = findViewById((R.id.Avatar));
        SignInButton signInButton = findViewById(R.id.sign_in_button);
        signInButton.setSize(SignInButton.SIZE_STANDARD);


        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();
        ;
        findViewById(R.id.sign_in_button).setOnClickListener(this);

        tv_boqua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Login.this, MainActivity.class);
                startActivity(i);
            }
        });

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.sign_in_button:
                signIn();
                break;
        }
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Log.d("Failed", connectionResult + "");
    }

    private void signIn() {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);
        Log.d("Success", mGoogleApiClient.isConnected() + "");
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(result);
        }
    }

    private void handleSignInResult(GoogleSignInResult result) {
        if (result.isSuccess()) {
            GoogleSignInAccount acct = result.getSignInAccount();
            // lấy thông tin user từ google account
            MemberData user = new MemberData();
            user.setEmail(acct.getEmail());
            user.setName(acct.getDisplayName());
            user.setUrlAvatar(acct.getPhotoUrl().toString());
            // lưu lên sever
            getResponse(user);
            // lưu email vào local
            SaveLocal.saveUserNameToLocal(acct.getEmail(), this);

//            txtEmail.setText(acct.getEmail().toString());
//            txtName.setText(acct.getDisplayName().toString());
//            txtEmail.setText(acct.getPhotoUrl().toString());

//            Picasso.get().load(acct.getPhotoUrl()).into(imageView);

//            Intent intent = new Intent(Login.this,MainActivity.class);
//            startActivity(intent);
        }
    }

    //
    @Override
    protected void onStart() {
        super.onStart();
        mGoogleApiClient.connect();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mGoogleApiClient.disconnect();
    }

    void getResponse(MemberData user) {
        //khởi tạo retrofit
        userAPI = APIClient.getClient().create(UserAPI.class);
        Call<MemberData> call = userAPI.createUser(user);
        call.enqueue(new Callback<MemberData>() {
            @Override
            public void onResponse(Call<MemberData> call, Response<MemberData> response) {
                MemberData currrentUser = response.body();
                // Di chuyển qua màn hình chính
                Intent i = new Intent(Login.this, MainActivity.class);
                i.putExtra("user-info", currrentUser);
                startActivity(i);
            }

            @Override
            public void onFailure(Call<MemberData> call, Throwable t) {
                Log.d("Có lỗi", "" + t);
            }
        });
    }


}
