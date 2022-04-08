package com.example.quizapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.os.Process;
import android.widget.TextView;
import android.widget.Toast;

public class ResultActivity extends AppCompatActivity {
    Button finish;
    Button restart;
    TextView resultPopUp,resultPopUp3;
    TextView username;
    String takeAgain = "yes";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result_activity);
        finish = (Button) findViewById(R.id.buttonFinsh);
        Intent intent = new Intent(this,MainActivity.class);
        resultPopUp = (TextView) findViewById(R.id.resultPopUp);
        resultPopUp3 = (TextView) findViewById(R.id.resultPopUp3);
        Intent oldIntent = getIntent();

        String Popup = oldIntent.getStringExtra("username");
        resultPopUp.setText("Congratulation "+ Popup+"!");

        String popUp3 = oldIntent.getStringExtra("correct");
        resultPopUp3.setText(popUp3+"/5");

        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finishAffinity();
                System.exit(0);
            }
        });

        restart = (Button) findViewById(R.id.buttonTakeQuizAgain);
        restart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.putExtra("username",oldIntent.getStringExtra("username"));

                startActivity(intent);
            }
        });

    }

}