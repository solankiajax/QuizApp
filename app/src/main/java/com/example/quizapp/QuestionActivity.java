package com.example.quizapp;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class QuestionActivity extends AppCompatActivity{


    int progress=0;
    int correctAnswer = 0;
    String selectedAnswer=null;

    TextView welcomeHeading,progressValue,questionTitle,Question;
    ProgressBar barProgress;
    Button option1,option2,option3,buttonSubmit;


    String questionList[] = {"Which method do we use to get result from another activity?","Which one from the following is not a state of lifecycle in activities?","Which one from the following is a method not used to navigate between stages of activities lifecycle?","we usually call onPause method to","We usually call onStop method when"};
    String optionOnes[] = {"result()","Foreground","onCreate()","User receives phone while using app","User receives phone call while using app"};
    String optionTwos[] = {"startActivity()","Fully hidden ","onStop()","Commit unsaved changes","Stop animations or other ongoing actions that could consume CPU"};
    String optionThrees[] = {"startActivityForResult()","Stop","onForeground()","When a new Activity is launched","To Commit unsaved changes"};
    String answers[] = {"startActivityForResult()","Stop","onForeground()","Commit unsaved changes","User receives phone call while using app"};

    public void setHeading(Intent I){
        welcomeHeading = (TextView) findViewById(R.id.welcomeYourName);
        String name = I.getStringExtra("username");
        String heading = String.format("Welcome %s!",name);
        welcomeHeading.setText(heading);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.question_activity);

        //initialization
        progress = 0;
        correctAnswer = 0;
        selectedAnswer = null;



        // Text View find by ID
        welcomeHeading = (TextView) findViewById(R.id.welcomeYourName);
        questionTitle = (TextView) findViewById(R.id.questionTitle);
        Question = (TextView) findViewById(R.id.Question);
        progressValue = (TextView) findViewById(R.id.progressNumber);

        // progress Bar find by ID
        barProgress = (ProgressBar) findViewById(R.id.progressBar2);

        //Button find by ID
        option1 = (Button) findViewById(R.id.option1);
        option2 = (Button) findViewById(R.id.option2);
        option3 = (Button) findViewById(R.id.option3);
        buttonSubmit = (Button) findViewById(R.id.buttonSubmit);


        // getting intent from Main Activity
        Intent oldIntent = getIntent();

        // setting heading

        setHeading(oldIntent);

        // calling next question method
        nextQuestion(progress);

        //Adding listeners to the option buttons
        option1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(selectedAnswer==null){
                    selectedAnswer = option1.getText().toString();
                    option1.setBackgroundResource(R.drawable.yellow_button_round_corner_one);
                }
                else{
                    Toast.makeText(QuestionActivity.this,"Please select submit or next option to continue",Toast.LENGTH_LONG).show();
                }

            }
        });

        option2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(selectedAnswer==null){
                    selectedAnswer = option2.getText().toString();
                    option2.setBackgroundResource(R.drawable.yellow_button_round_corner_one);
                }
                else{
                    Toast.makeText(QuestionActivity.this,"Please select submit or next option to continue",Toast.LENGTH_LONG).show();
                }

            }
        });

        option3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(selectedAnswer==null){
                    selectedAnswer = option3.getText().toString();
                    option3.setBackgroundResource(R.drawable.yellow_button_round_corner_one);
                }
                else{
                    Toast.makeText(QuestionActivity.this,"Please select submit or next option to continue",Toast.LENGTH_LONG).show();
                }

            }
        });



    }

    public void nextQuestion(int currentProgress){

        progress = currentProgress +1;

        //changing color of all options
        option1.setBackgroundResource(R.drawable.white_button_round_corner_one);
        option2.setBackgroundResource(R.drawable.white_button_round_corner_one);
        option3.setBackgroundResource(R.drawable.white_button_round_corner_one);

        //setting up questions for the turn
        barProgress.setProgress(progress);
        questionTitle.setText("Question "+Integer.toString(progress)+":");
        Question.setText(questionList[progress-1]);
        progressValue.setText(Integer.toString(progress)+"/5");

        //setting up the options value
        option1.setText(optionOnes[progress-1]);
        option2.setText(optionTwos[progress-1]);
        option3.setText(optionThrees[progress-1]);

    }

    public void submit(View view) {

        if (buttonSubmit.getText().toString().equals("Next") && (progress < 5)) {
            //submit= true;

            buttonSubmit.setText("Submit");
            selectedAnswer = null;
            nextQuestion(progress);
            return;
        }
        if ((progress == 5) && buttonSubmit.getText().toString().equals("Next")) {
            Intent oldIntent = getIntent();
            Intent intent = new Intent(this, ResultActivity.class);
            intent.putExtra("username", oldIntent.getStringExtra("username"));
            intent.putExtra("correct", Integer.toString(correctAnswer));
            startActivity(intent);
        }


        if (selectedAnswer != null && (progress < 6)) {
            buttonSubmit.setText("Next");
            if (selectedAnswer.equals(answers[progress - 1])) {
                correctAnswer++;
                if (option1.getText().toString().equals(answers[progress - 1])) {
                    option1.setBackgroundResource(R.drawable.green_button_round_corner_one);
                } else if (option2.getText().toString().equals(answers[progress - 1])) {
                    option2.setBackgroundResource(R.drawable.green_button_round_corner_one);
                } else if (option3.getText().toString().equals(answers[progress - 1])) {
                    option3.setBackgroundResource(R.drawable.green_button_round_corner_one);
                }
            } else {
                if (selectedAnswer.equals(option1.getText().toString())) {
                    option1.setBackgroundResource(R.drawable.red_button_round_corner_one);
                } else if (selectedAnswer.equals(option2.getText().toString())) {
                    option2.setBackgroundResource(R.drawable.red_button_round_corner_one);
                } else if (selectedAnswer.equals(option3.getText().toString())) {
                    option3.setBackgroundResource(R.drawable.red_button_round_corner_one);
                }

                if (option1.getText().toString().equals(answers[progress - 1])) {
                    option1.setBackgroundResource(R.drawable.green_button_round_corner_one);
                } else if (option2.getText().toString().equals(answers[progress - 1])) {
                    option2.setBackgroundResource(R.drawable.green_button_round_corner_one);
                } else if (option3.getText().toString().equals(answers[progress - 1])) {
                    option3.setBackgroundResource(R.drawable.green_button_round_corner_one);
                }
            }
            return;
        } else {
            Toast.makeText(QuestionActivity.this, "Please select an option", Toast.LENGTH_LONG).show();
        }

    }
}