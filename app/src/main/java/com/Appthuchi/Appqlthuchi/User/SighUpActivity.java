package com.Appthuchi.Appqlthuchi.User;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.Appthuchi.Appqlthuchi.R;

public class SighUpActivity extends AppCompatActivity {

    private EditText edtUserName, edtPassword,edtRePassword;
    private UserSQLite sqLite;
    private User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sigh_up);
        thamChieu();

        sqLite = new UserSQLite(this);
        user = new User();
    }

    private void thamChieu() {
        edtUserName = findViewById(R.id.edtUserName);
        edtPassword = findViewById(R.id.edtPassword);
        edtRePassword = findViewById(R.id.edtRePassword);
    }

    public void signUp(View view) {
        if (edtUserName.getText().toString().trim().isEmpty() || edtPassword.getText().toString().trim().isEmpty() || edtRePassword.getText().toString().trim().isEmpty()) {
            Toast.makeText(this, "Vui lòng điền đầy đủ thông tin!", Toast.LENGTH_SHORT).show();
        }else {
            if (!edtPassword.getText().toString().trim().equals(edtRePassword.getText().toString().trim())) {
                Toast.makeText(this, "Mật khẩu nhập lại không đúng!", Toast.LENGTH_SHORT).show();
            } else {

               user.setUsernameId(edtUserName.getText().toString().trim());
               user.setUsername(edtUserName.getText().toString().trim());
               user.setPassword(edtPassword.getText().toString().trim());

                long result = sqLite.insertUser(user);
                if (result>0){
                    Toast.makeText(this,"Thành Công",Toast.LENGTH_SHORT).show();
                    finish();
                }else {
                    Toast.makeText(this,"Tài khoản đã có người sử dụng!",Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
}