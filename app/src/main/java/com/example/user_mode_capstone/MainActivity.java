package com.example.user_mode_capstone;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {


    String username, password;
    EditText lblUsername, lblpassword;
    Button signin;
    private ProgressDialog progressDialog;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lblUsername = (EditText) findViewById(R.id.username);
        lblpassword = (EditText)findViewById(R.id.password);
        signin = (Button)findViewById(R.id.button);
        progressDialog = new ProgressDialog( this);
        mAuth=FirebaseAuth.getInstance();
    }
    public void btnClick (View V)
    {
        if(V==signin)
        {
            username = lblUsername.getText().toString().trim();
            password = lblpassword.getText().toString().trim();
            if(TextUtils.isEmpty(username))
            {
                Toast.makeText(this,  "Enter Email",Toast.LENGTH_SHORT).show();
                return;
            }
            if(TextUtils.isEmpty(password))
            {
                Toast.makeText(this,  "Enter Password",Toast.LENGTH_SHORT).show();
                return;
            }
            progressDialog.setMessage("Signing in Please Wait...");
            progressDialog.show();
            progressDialog.setCanceledOnTouchOutside(false);
            mAuth.signInWithEmailAndPassword(username, password)
                    .addOnCompleteListener(this, (OnCompleteListener) (task) -> {
                        if(task.isSuccessful())
                        {
                            finish();
                            startActivity(new Intent(getApplicationContext(), Home_Page.class));
                            Toast.makeText(MainActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Toast.makeText(MainActivity.this, "Logid ID and Password Does Not Match", Toast.LENGTH_SHORT).show();
                        }
                        progressDialog.dismiss();
                    });



        }
    }
}