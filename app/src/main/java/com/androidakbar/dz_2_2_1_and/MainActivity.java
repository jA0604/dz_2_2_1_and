package com.androidakbar.dz_2_2_1_and;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText etInput;
    private Button btnSave;
    private SharedPreferences shpNote;
    private static String NOTE_TEXT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        getDateFromSharedPref();
    }

    private void getDateFromSharedPref() {
        if (shpNote.getString(NOTE_TEXT, "").equals("") ) {
            Toast.makeText(MainActivity.this, "В заметке ничего не сохранено", Toast.LENGTH_LONG).show();
        } else {
            etInput.setText(shpNote.getString(NOTE_TEXT, ""));
        }
    }

    private void initViews() {
        etInput = findViewById(R.id.et_input);
        btnSave = findViewById(R.id.btn_save);
        NOTE_TEXT = getResources().getString(R.string.str_note_key);

        shpNote = getSharedPreferences(getResources().getString(R.string.str_note_name), MODE_PRIVATE);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (etInput.getText().length() > 0) {
                    shpNote.edit().putString(NOTE_TEXT, etInput.getText().toString()).apply();
                    Toast.makeText(MainActivity.this, "данные сохранены", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(MainActivity.this, "Текстовое поле пусто. Данные НЕ сохранены", Toast.LENGTH_LONG).show();
                }


            }
        });
    }
}