package com.example.swarangigaurkar.learnersapp;
import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import static com.example.swarangigaurkar.learnersapp.StartingScreenActivity.EXTRA_DIFFICULTY;

public class java extends Fragment
{
    private Spinner spinnerDifficulty;
    public Spinner spinnerCategory;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View v=inflater.inflate(R.layout.fragment_java,container,false);

      //  ProgressBar.setMin(1);
      //  ProgressBar.setMax(40);



        final TextView textView=(TextView)v.findViewById(R.id.tv2);

       SeekBar seekBar = (SeekBar) v.findViewById(R.id.sb1);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean b)
            {
                textView.setText(String.valueOf(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });



        FloatingActionButton fabbutton=(FloatingActionButton)v.findViewById(R.id.fab);
        fabbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(),QuizActivity.class);
                startActivity(intent);

            }
        });

     //   String difficulty = spinnerDifficulty.getSelectedItem().toString();


        return v;
    }
    private void StartQuiz()
    {
        String value=getArguments().getString("one");
        int categoryID = selectedCategory.getId();
        String categoryName = selectedCategory.getName();
        String difficulty = spinnerDifficulty.getSelectedItem().toString();

        Intent intent = new Intent(getActivity(),QuizActivity.class);
        intent.putExtra(EXTRA_CATEGORY_ID,categoryID);
        intent.putExtra(EXTRA_CATEGORY_NAME,categoryName);
        intent.putExtra(EXTRA_DIFFICULTY,difficulty);
        startActivityForResult(intent,REQUEST_CODE_QUIZ);
    }

}
