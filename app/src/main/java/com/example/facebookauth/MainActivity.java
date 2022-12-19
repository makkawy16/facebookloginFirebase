package com.example.facebookauth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.facebookauth.databinding.ActivityMainBinding;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;

    CallbackManager callbackManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        callbackManager = CallbackManager.Factory.create();

        LoginManager.getInstance().registerCallback(callbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        startActivity(new Intent(MainActivity.this,MainActivity2.class));
                        Log.d("ssssssssss", "onSuccess: " + loginResult.toString());
                        finish();
                    }

                    @Override
                    public void onCancel() {
                        Log.d("ssssssssssss", "onCancel: " );

                    }

                    @Override
                    public void onError(@NonNull FacebookException e) {
                        Log.d("sssssssssssssssss", "onError: "  + e.getLocalizedMessage());
                        Log.d("sssssssssssssssss", "onError: "  + e.getMessage());
                        Log.d("sssssssssssssssss", "onError: "  + e.getCause());
                        Log.d("sssssssssssssssss", "onError: "  + e.getStackTrace());
                    }
                });

        binding.loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                LoginManager.getInstance().logInWithReadPermissions(MainActivity.this, Arrays.asList("public_profile"));
            }
        });

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }
}