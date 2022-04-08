package com.example.quizapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = getIntent();
        String userName = intent.getStringExtra("username");
        if(!(userName == null)){
            username = (TextView) findViewById(R.id.userName);
            username.setText(userName);
        }
    }

    public void jumpClick(View view){
    try{
        username = (TextView) findViewById(R.id.userName);
        Intent intent = new Intent(this,QuestionActivity.class);
        intent.putExtra("username",username.getText().toString());
        intent.putExtra("progress","1");
        startActivity(intent);
    }
    catch (Exception E){
        Toast.makeText(MainActivity.this,E.getMessage(),Toast.LENGTH_LONG).show();
    }
    }




}