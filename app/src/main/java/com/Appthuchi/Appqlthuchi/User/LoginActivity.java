package com.Appthuchi.Appqlthuchi.User;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.Appthuchi.Appqlthuchi.MainActivity;
import com.Appthuchi.Appqlthuchi.R;

public class LoginActivity extends AppCompatActivity {

    private EditText edtUserName, edtPassword;
    private CheckBox chkLuuTT;

    private UserSQLite sqLite;
    private User user;

    private String password = "";

    public static SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        thamChieu();

        sqLite = new UserSQLite(this);

        sharedPreferences = getSharedPreferences("User", MODE_PRIVATE);

        if (!sharedPreferences.getString("user", "").isEmpty()) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        }

    }

    private void thamChieu() {
        edtUserName = findViewById(R.id.edtUserName);
        edtPassword = findViewById(R.id.edtPassword);
        chkLuuTT = findViewById(R.id.chkLuuTT);
    }

    public void login(View view) {
        if (edtUserName.getText().toString().trim().isEmpty() || edtPassword.getText().toString().trim().isEmpty()) {
            Toast.makeText(this, "Vui lòng nhập đầy đủ thông tin!", Toast.LENGTH_SHORT).show();
        } else {
            password = sqLite.getUser(edtUserName.getText().toString().trim());
            if (edtPassword.getText().toString().trim().equals(password)) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                if (chkLuuTT.isChecked()) {
                    editor.putString("user", edtUserName.getText().toString().trim());
                } else {
                    editor.remove("user");
                }
                editor.commit();
                Toast.makeText(this, "Đăng nhập thành công!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(this, "Sai Tài khoản hoặc Mật khẩu!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void signUp(View view) {
        Intent intent = new Intent(this, SighUpActivity.class);
        startActivity(intent);
    }
}