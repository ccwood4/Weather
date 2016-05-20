package com.example.clint.owm_client;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    public final static String EXTRA_MESSAGE = "com.example.clint.owm_client.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    /** Called when the user clicks the New User button */
    public void newUser(View view) {
        MySQLiteHelper db = new MySQLiteHelper(this);
        EditText userName = (EditText) findViewById(R.id.userNameText);
        if(!userName.getText().toString().matches("")) {
            if (db.getUser(userName.getText().toString()).getUsername().matches("none")) {
                UserModel user = new UserModel(userName.getText().toString());
                db.addUser(user);

                Intent intent = new Intent(this, WeatherActivity.class);
                String message = userName.getText().toString();
                intent.putExtra(EXTRA_MESSAGE, message);
                startActivity(intent);
            } else {
                Toast.makeText(getApplicationContext(), "User Already Exists.", Toast.LENGTH_SHORT).show();
            }
        }
        else {
            Toast.makeText(getApplicationContext(), "User Name Can't Be Blank.", Toast.LENGTH_SHORT).show();
        }
    }

    /** Called when the user clicks the Sign In button */
    public void signIn(View view) {
        MySQLiteHelper db = new MySQLiteHelper(this);
        EditText userName = (EditText) findViewById(R.id.userNameText);
        if(!userName.getText().toString().matches("")) {
            if (!db.getUser(userName.getText().toString()).getUsername().matches("none")) {
                Intent intent = new Intent(this, WeatherActivity.class);
                String message = userName.getText().toString();
                intent.putExtra(EXTRA_MESSAGE, message);
                startActivity(intent);
            } else {
                Toast.makeText(getApplicationContext(), "User Does Not Exist.", Toast.LENGTH_SHORT).show();
            }
        }
        else {
            Toast.makeText(getApplicationContext(), "User Name Can't Be Blank.", Toast.LENGTH_SHORT).show();
        }
    }
}
