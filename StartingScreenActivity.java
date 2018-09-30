package com.example.shanay.quizapp2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class StartingScreenActivity extends AppCompatActivity {

   // private static final int REQUEST_CODE_QUIZ = 1;

   // public static final String SHARED_PREFERENCES ="sharedPreferences";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_starting_screen);

       Button buttonStartQuiz =(Button)findViewById(R.id.btn_start_quiz);
      buttonStartQuiz.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              StartQuiz();
          }
      });
    }

   private void StartQuiz()
   {
       Intent intent = new Intent(StartingScreenActivity.this,QuizActivity.class);
       //startActivityForResult(intent,REQUEST_CODE_QUIZ);
       startActivity(intent);
   }


}
