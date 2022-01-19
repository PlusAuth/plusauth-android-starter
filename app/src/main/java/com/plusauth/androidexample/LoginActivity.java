package com.plusauth.androidexample;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.plusauth.android.OIDC;
import com.plusauth.android.auth.exceptions.AuthenticationException;
import com.plusauth.android.auth.login.LoginRequest;
import com.plusauth.android.auth.logout.LogoutRequest;
import com.plusauth.android.model.Credentials;
import com.plusauth.android.model.UserProfile;
import com.plusauth.android.util.AuthenticationCallback;
import com.plusauth.android.util.PACallback;
import com.plusauth.android.util.VoidCallback;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = "LoginActivity";
    Button buttonLogin, buttonLogout, buttonProfile;
    TextView usernameTextview;
    OIDC plusAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get Button Views
        buttonLogin = findViewById(R.id.button_login);
        buttonLogout = findViewById(R.id.button_logout);
        buttonProfile = findViewById(R.id.button_profile);
        usernameTextview = findViewById(R.id.textview_username);

        // Create PlusAuth instance to initialize auth
        plusAuth = PlusAuthInstance.get(this);
        // Get User Info to configure views
        getUserInfo();

        buttonLogin.setOnClickListener(v -> {
            buttonLogin.setEnabled(false);
            // Trigger Login to PlusAuth
            plusAuth.login(this, new LoginRequest().setScope("openid offline_access profile email"), new AuthenticationCallback() {
                @Override
                public void onSuccess(Credentials credentials) {
                    getUserInfo();
                }

                @Override
                public void onFailure(AuthenticationException e) {
                    Log.e(TAG, "Login failed", e);
                    runOnUiThread(() ->buttonLogin.setEnabled(true));
                }

            });
        });

        buttonLogout.setOnClickListener(v -> {
            buttonLogout.setEnabled(false);
            plusAuth.logout(this, new LogoutRequest(), new VoidCallback() {
                @Override
                public void onSuccess(Void aVoid) {
                    // Get User Info and reconfigure views after successful authentication
                    configureViews(false, null);
                }

                @Override
                public void onFailure(AuthenticationException e) {
                    Log.e(TAG, "Logout failed", e);
                    runOnUiThread(() -> buttonLogout.setEnabled(true));
                }
            });
        });

        buttonProfile.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, ProfileActivity.class);
            startActivity(intent);
        });
    }

    private void getUserInfo() {
        // Show Profile and Logout Buttons if user authenticated
        // Hide Profile and Logout Buttons if user not authenticated
        if(plusAuth.getCredentialsManager().hasValidCredentials()) {
            plusAuth.getApi().userInfo().call(new PACallback<UserProfile, AuthenticationException>() {
                @Override
                public void onSuccess(UserProfile userProfile) {
                    // Show Profile and Logout buttons , hide Login Button
                    runOnUiThread(() -> configureViews(true, userProfile.getUsername()));
                }

                @Override
                public void onFailure(AuthenticationException e) {
                    // Show Login button, hide Profile and Logout buttons
                    runOnUiThread(() -> configureViews(false, null));
                }
            });
        } else {
            // Show Login button, hide Profile and Logout buttons
            configureViews(false, null);
        }
    }

    private void configureViews(Boolean isLoggedIn, String userName) {
        buttonLogin.setVisibility(isLoggedIn ? View.GONE : View.VISIBLE);
        buttonProfile.setVisibility(isLoggedIn ? View.VISIBLE : View.GONE);
        buttonLogout.setVisibility(isLoggedIn ? View.VISIBLE : View.GONE);
        usernameTextview.setText("User: " + (userName == null ? "-" : userName));
    }
}