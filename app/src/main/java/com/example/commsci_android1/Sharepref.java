package com.example.commsci_android1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class Sharepref extends AppCompatActivity  {
    private TextView textView;
    private EditText editText;
    private Button applybut;
    private Button savebut;
    private Switch switch1;

    public static final String SHARED_PREF = "sharedpref";
    public static final String TEXT = "sharedpref";
    public static final String SWITCH1 = "switch1";
    private String txt;
    private boolean swonoff;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sharepref);

        textView = findViewById(R.id.sp_textView);
        editText = findViewById(R.id.sp_editText);
        applybut = findViewById(R.id.sp_apply_but);
        switch1 = findViewById(R.id.sp_switch1);
        savebut = findViewById(R.id.sp_save_but);
        
        applybut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setText(editText.getText().toString());
            }
        });
        
        savebut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                savedata();
            }
        });

        loaddata();
        updateViews();
    }

    private void savedata() {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREF, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString(TEXT, textView.getText().toString());
        editor.putBoolean(SWITCH1, switch1.isChecked());

        editor.apply();

//        editor.remove(TEXT);
//        editor.clear();
        Toast.makeText(this, "data saved", Toast.LENGTH_SHORT).show();
    }

    public void loaddata(){
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREF, MODE_PRIVATE);
        txt = sharedPreferences.getString(TEXT, "");
        swonoff = sharedPreferences.getBoolean(SWITCH1, false);
    }

    public void updateViews(){
        textView.setText(txt);
        switch1.setChecked(swonoff);
    }
}
