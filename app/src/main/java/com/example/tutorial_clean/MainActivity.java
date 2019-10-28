package com.example.tutorial_clean;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText etUserName, etPassword;

    private Button onInsert, onSyn;
    private Syn syn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etUserName = (EditText) findViewById(R.id.etUserName);
        etPassword = (EditText) findViewById(R.id.etPassword);

        setContentView(R.layout.activity_main);
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        // initilizing propreties
        this.onInsert=(Button)findViewById(R.id.onInsert);
        this.onSyn=(Button)findViewById(R.id.onSyn);
        this.syn=new Syn();
        // preparing listener (onAction)

        this.onInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String username = "GGGGG"; //etUserName.getText().toString();
                String password = "HHHH"; //etPassword.getText().toString();

                String msg=syn.doInBackground("insert", username, password);

                Toast.makeText(getBaseContext(),msg,Toast.LENGTH_SHORT).show();
            }
        });

        this.onSyn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                syn.doInBackground("syn");
            }
        });
    }
}
