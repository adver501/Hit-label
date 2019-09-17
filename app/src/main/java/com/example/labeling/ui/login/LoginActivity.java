package com.example.labeling.ui.login;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
//Text Watcher

import com.example.labeling.LabelData;
import com.example.labeling.R;

public class LoginActivity extends AppCompatActivity {

    final EditText usernameEditText = findViewById(R.id.username);
    final EditText passwordEditText = findViewById(R.id.password);

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final Button loginButton = findViewById(R.id.login);
        final ProgressBar loadingProgressBar = findViewById(R.id.loading);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), LabelData.class);
                i.putExtra("userType", usernameEditText.getText().toString());
                startActivity(i);
//                loginUser();
//                loadingProgressBar.setVisibility(View.VISIBLE);
//                loginViewModel.login(usernameEditText.getText().toString(),
//                        passwordEditText.getText().toString());
//                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        });
    }
/*
    private void loginUser(){
        final String email = usernameEditText.getText().toString();
        final String password = passwordEditText.getText().toString();
        LoginRequest requestJson = new LoginRequest();
        requestJson.setUsername(email);
        requestJson.setPassword(password);
        RequestBody body = RequestBody.create(HttpHelper.JSON, new Gson().toJson(requestJson));
        Request request = new Request.Builder()
                .post(body)
                .url(HttpAddresses.LoginAddress).build();
        final ProgressDialog dialog = ProgressDialog.show(this, "",
                "Loading. Please wait...", true);
        HttpHelper.getInstance().getClient().newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                LoginActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(LoginActivity.this, "Login Failed", Toast.LENGTH_LONG).show();
                        dialog.dismiss();
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String responseString = response.body().string();
                LoginResponse res = new Gson().fromJson(responseString, LoginResponse.class);
                if(res.isSuccess()){
                    //save login data
                    LoginHelper.saveLoginData(LoginActivity.this, res, email);

                    //go to home page
                    Intent loginActivityIntent = new Intent(LoginActivity.this, MainActivity.class);
                    finish();
                    startActivity(loginActivityIntent);
                    dialog.dismiss();
                }else{
                    LoginActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(LoginActivity.this, "Login Failed", Toast.LENGTH_LONG).show();
                            dialog.dismiss();
                        }
                    });
                }
            }
        });

    }*/
}
