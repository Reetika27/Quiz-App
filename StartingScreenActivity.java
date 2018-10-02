package com.example.shanay.quizapp2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;


public class StartingScreenActivity extends AppCompatActivity {

    private static final int REQUEST_CODE_QUIZ = 1;
    public static  final String EXTRA_DIFFICULTY ="extraDifficulty";

    public static final String SHARED_PREFERENCES ="sharedPreferences";
    public static final String KEY_HIGHSCORE ="highScoreKey";

    private TextView textViewHighScore;
    private Spinner spinnerDifficulty;

    private int highScore;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_starting_screen);

        textViewHighScore = findViewById(R.id.text_highscore);
        spinnerDifficulty = findViewById(R.id.spinner_difficulty);

        String[] difficultyLevels = Questions.getAllDifficultyLevels();
        ArrayAdapter<String> adapterDifficulty = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,difficultyLevels);
        adapterDifficulty.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerDifficulty.setAdapter(adapterDifficulty);


        loadHighScore();

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
       String difficulty = spinnerDifficulty.getSelectedItem().toString();
       Intent intent = new Intent(StartingScreenActivity.this,QuizActivity.class);
       intent.putExtra(EXTRA_DIFFICULTY,difficulty);
       startActivityForResult(intent,REQUEST_CODE_QUIZ);
   }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==REQUEST_CODE_QUIZ)
        {
            if(resultCode == RESULT_OK)
            {
                int score = data.getIntExtra(QuizActivity.EXTRA_SCORE,0);
                if(score > highScore)
                {
                    updateHighscore(score);
                }
            }
        }
    }

    private void  loadHighScore()
    {
        SharedPreferences prefs1 = getSharedPreferences(SHARED_PREFERENCES,MODE_PRIVATE);
        highScore = prefs1.getInt(KEY_HIGHSCORE,0);
        textViewHighScore.setText("HighScore: " + highScore);
    }
    private void updateHighscore(int highscoreNew)
    {
        highScore = highscoreNew;
        textViewHighScore.setText("HighScore: " + highScore);

        SharedPreferences prefs =  getSharedPreferences(SHARED_PREFERENCES,MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt(KEY_HIGHSCORE,highScore);
        editor.apply();

    }

}
