package com.example.amiotj.tp0;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import static com.example.amiotj.tp0.UserStorage.saveUserInfo;

public class NamePickerActivity extends AppCompatActivity {

    public static final String TAG = NamePickerActivity.class.getSimpleName();

    EditText nameEditText;
    EditText emailEditText;
    Button submitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_namepicker);
        nameEditText = findViewById(R.id.name);
        emailEditText = findViewById(R.id.email);
        submitButton = findViewById(R.id.submit);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleLogin();
            }
        });
    }

    private void handleLogin() {
        if (!nameEditText.getText().toString().isEmpty() && !emailEditText.getText().toString().isEmpty()) {
            saveUserInfo(this, nameEditText.getText().toString(), emailEditText.getText().toString());
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
    }
}

