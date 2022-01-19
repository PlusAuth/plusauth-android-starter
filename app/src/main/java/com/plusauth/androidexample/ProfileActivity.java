package com.plusauth.androidexample;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.plusauth.android.OIDC;
import com.plusauth.android.auth.exceptions.AuthenticationException;
import com.plusauth.android.model.UserProfile;
import com.plusauth.android.util.PACallback;

public class ProfileActivity extends AppCompatActivity {

    private static final String TAG = "ProfileActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        OIDC plusAuth = PlusAuthInstance.get(this);
        Button backButton = findViewById(R.id.button_back);
        TextView userInfoTextview = findViewById(R.id.textview_user_info);

        plusAuth.getApi().userInfo().call(new PACallback<UserProfile, AuthenticationException>() {
            @Override
            public void onSuccess(UserProfile userProfile) {
                runOnUiThread(() -> {
                    userInfoTextview.setText(userProfile.toString());
                });
            }

            @Override
            public void onFailure(AuthenticationException e) {
                Log.e(TAG, "Could not get profile", e);
            }
        });

        backButton.setOnClickListener(v -> {
            finish();
        });
    }
}