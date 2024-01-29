package com.example.examplesharedpreferences;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText ed1, ed2, ed3;
    Button clear, save, get;

    public static final String myPref = "myPref";
    public static final String nameKey = "nameKey";
    public static final String phoneNoKey = "phoneKey";
    public static final String emailKey = "emailKey";

    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ed1 = findViewById(R.id.editText);
        ed2 = findViewById(R.id.editText2);
        ed3 = findViewById(R.id.editText3);

        clear = findViewById(R.id.buttonRight);
        save = findViewById(R.id.button);
        get = findViewById(R.id.buttonLeft);

        sp = getSharedPreferences(myPref, Context.MODE_PRIVATE);

        clear.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        SharedPreferences.Editor editor = sp.edit();
                        editor.clear();
                        editor.commit();
                    }
                }
        );

        save.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String name = ed1.getText().toString();
                        String phoneNo = ed2.getText().toString();
                        String email = ed3.getText().toString();

                        SharedPreferences.Editor editor = sp.edit();
                        editor.putString(nameKey, name);
                        editor.putString(phoneNoKey, phoneNo);
                        editor.putString(emailKey, email);
                        editor.commit();
                        Toast.makeText(MainActivity.this, "Data Saved into SharedPrefs!", Toast.LENGTH_LONG).show();
                    }
                }
        );

        get.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (sp.getAll().isEmpty()) {
                            Toast.makeText(MainActivity.this, "SharedPrefs is empty!", Toast.LENGTH_LONG).show();
                        } else {
                            ed1.setText(sp.getString(nameKey, ""));
                            ed2.setText(sp.getString(phoneNoKey, ""));
                            ed3.setText(sp.getString(emailKey, ""));
                        }
                    }
                }
        );
    }
}