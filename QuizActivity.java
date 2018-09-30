package com.example.shanay.quizapp2;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Collections;
import java.util.List;

public class QuizActivity extends AppCompatActivity {

    //public static final String EXTRA_SCORE = "newScore";

    private TextView textViewQuestion;
    private  TextView textViewScore;
    private TextView textViewQuestionNumber;
    private TextView textViewCountDown;
    private RadioGroup rGroup;
    private RadioButton rb1;
    private RadioButton rb2;
    private RadioButton rb3;
    private RadioButton rb4;
    private Button buttonSubmitNext;

    private ColorStateList textColorDefaultrb;

    private List<Questions> questionsList;

    private int questionCounter;
    private int questionTotal;
    private Questions currentQuestion;
    private int score;
    private boolean answered;

    private QuizDBHelper quizDBHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        textViewQuestion = findViewById(R.id.text_question_statement);
        textViewQuestionNumber = findViewById(R.id.text_question);
        textViewScore = findViewById(R.id.text_score);
        textViewCountDown = findViewById(R.id.text_countdown);
        rGroup = findViewById(R.id.radio_group);
        rb1 = findViewById(R.id.radio_btn1);
        rb2 = findViewById(R.id.radio_btn2);
        rb3 = findViewById(R.id.radio_btn3);
        rb4 = findViewById(R.id.radio_btn4);
        buttonSubmitNext = findViewById(R.id.btn_submit_next);

        textColorDefaultrb = rb1.getTextColors();

        this.quizDBHelper = new QuizDBHelper(this);
        questionsList = quizDBHelper.getAllQuestions();

        questionTotal = questionsList.size();
        Collections.shuffle(questionsList);

        showNextQuestion();
        buttonSubmitNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!answered)
                {
                    if(rb1.isChecked() || rb2.isChecked() || rb3.isChecked() || rb4.isChecked())
                    {
                        checkAnswer();
                    }
                    else
                    {
                        Toast.makeText(QuizActivity.this,"Please select an answer",Toast.LENGTH_SHORT).show();
                    }
                }
                else
                {
                    showNextQuestion();
                }
            }
        });
    }

    protected void onDestroy() {
        this.quizDBHelper.close();
        super.onDestroy();
    }

    private void showNextQuestion()
    {
        rb1.setTextColor(textColorDefaultrb);
        rb2.setTextColor(textColorDefaultrb);
        rb3.setTextColor(textColorDefaultrb);
        rb4.setTextColor(textColorDefaultrb);
        rGroup.clearCheck();

        if(questionCounter < questionTotal)
        {
            currentQuestion = questionsList.get(questionCounter);

            textViewQuestion.setText(currentQuestion.getQuestion());
            rb1.setText(currentQuestion.getOption1());
            rb2.setText(currentQuestion.getOption2());
            rb3.setText(currentQuestion.getOption3());
            rb4.setText(currentQuestion.getOption4());

            questionCounter++;
            textViewQuestionNumber.setText("Question: "+questionCounter + "/" +questionTotal);
            answered =false;
            buttonSubmitNext.setText("Confirm");

        }
        else {
            finishQuiz();
        }
    }

    private void checkAnswer()
    {
        answered =true;
        RadioButton rbSelected = findViewById(rGroup.getCheckedRadioButtonId());
        int ansNo = rGroup.indexOfChild(rbSelected) + 1;

        if(ansNo == currentQuestion.getAnsNr())
        {
            score++;
            textViewScore.setText("Score: " + score);
        }

        showSolution();

    }


    private void showSolution()
    {
        rb1.setTextColor(Color.RED);
        rb2.setTextColor(Color.RED);
        rb3.setTextColor(Color.RED);
        rb4.setTextColor(Color.RED);

        switch(currentQuestion.getAnsNr())
        {
            case 1: rb1.setTextColor(Color.GREEN);
                textViewQuestion.setText("Answer 1 is correct");
                break;
            case 2: rb2.setTextColor(Color.GREEN);
                textViewQuestion.setText("Answer 2 is correct");
                break;
            case 3: rb3.setTextColor(Color.GREEN);
                textViewQuestion.setText("Answer 3 is correct");
                break;
            case 4: rb4.setTextColor(Color.GREEN);
                textViewQuestion.setText("Answer 4 is correct");
                break;


        }

        if(questionCounter < questionTotal)
        {
            buttonSubmitNext.setText("Next");
        }
        else
        {
            buttonSubmitNext.setText("Finish");
        }

}
    private void finishQuiz()
    {
        /*Intent resultIntent = new Intent();
        resultIntent.putExtra(EXTRA_SCORE,score);
        setResult(RESULT_OK,resultIntent);*/
        finish();
    }
}
