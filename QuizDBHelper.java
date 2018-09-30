package com.example.shanay.quizapp2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


import java.util.ArrayList;
import java.util.List;


public class QuizDBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME ="Quiz2.db";
    private static final int DATABASE_VERSION =1;

    private SQLiteDatabase db;

    public QuizDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        this.db = db;

        final String SQL_CREATE_QUESTION_TABLE = "CREATE TABLE " +
                QuizContract.QuestionTable.TABLE_NAME + " ( "+
                QuizContract.QuestionTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "+
                QuizContract.QuestionTable.COLUMN_QUESTION + " TEXT, " +
                QuizContract.QuestionTable.COLUMN_OPTION1 + " TEXT, "  +
                QuizContract.QuestionTable.COLUMN_OPTION2 + " TEXT, "  +
                QuizContract.QuestionTable.COLUMN_OPTION3 + " TEXT, "  +
                QuizContract.QuestionTable.COLUMN_OPTION4 + " TEXT,"  +
                QuizContract.QuestionTable.COLUMN_ANS + " INTEGER"  +
                " )";

        db.execSQL(SQL_CREATE_QUESTION_TABLE);

        fillQuestionsTable();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + QuizContract.QuestionTable.TABLE_NAME);
        onCreate(db);

    }

    public void fillQuestionsTable()
    {
        Questions q1= new Questions("Which is not an access specifier" , "public","private","protected","main",4);
        addQuestion(q1);
        Questions q2= new Questions("Inline functions are useful when" , "Function is large with many nested loops","Function has many static variables","Function is small and we want to avoid function call overhead","None of the above",3);
        addQuestion(q2);
        Questions q3= new Questions("Which of the following is true?" , "All objects of a class share all data members of class","Objects of a class do not share non-static members. Every object has its own copy.","Objects of a class do not share codes of non-static methods, they have their own copy","None Of the above",2);
        addQuestion(q3);
        Questions q4= new Questions("#include<iostream>\n" +
                "using namespace std;\n" +
                " \n" +
                "class Test\n" +
                "{\n" +
                "    static int x;\n" +
                "    int *ptr;\n" +
                "    int y;\n" +
                "};\n" +
                " \n" +
                "int main()\n" +
                "{\n" +
                "    Test t;\n" +
                "    cout << sizeof(t) << \" \";\n" +
                "    cout << sizeof(Test *);\n" +
                "}\n" , "12 4","12 12","8 4","8 8",3);
        addQuestion(q4);
        Questions q5= new Questions("#include<iostream>\n" +
                "using namespace std;\n" +
                " \n" +
                "class Base\n" +
                "{\n" +
                "public:\n" +
                "    virtual void show() = 0;\n" +
                "};\n" +
                " \n" +
                "int main(void)\n" +
                "{\n" +
                "    Base b;\n" +
                "    Base *bp;\n" +
                "    return 0;\n" +
                "}" , "There are compiler errors in lines \"Base b;\" and \"Base bp;\"","There is compiler error in line \"Base b;","There is compiler error in line \"Base bp;\"","No compiler Error",2);
        addQuestion(q5);

    }

    private void addQuestion(Questions question)
    {
        ContentValues values = new ContentValues();
        values.put(QuizContract.QuestionTable.COLUMN_QUESTION, question.getQuestion());
        values.put(QuizContract.QuestionTable.COLUMN_OPTION1, question.getOption1());
        values.put(QuizContract.QuestionTable.COLUMN_OPTION2, question.getOption2());
        values.put(QuizContract.QuestionTable.COLUMN_OPTION3, question.getOption3());
        values.put(QuizContract.QuestionTable.COLUMN_OPTION4, question.getOption4());
        values.put(QuizContract.QuestionTable.COLUMN_ANS, question.getAnsNr());

        db.insert(QuizContract.QuestionTable.TABLE_NAME,null,values);

    }

    public List<Questions> getAllQuestions()
    {
        List<Questions> questionsList =new ArrayList<Questions>();
        db =getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM "+ QuizContract.QuestionTable.TABLE_NAME , null);

        if(c.moveToFirst())
        {
            do {

                Questions questions = new Questions();
                questions.setQuestion(c.getString(c.getColumnIndex(QuizContract.QuestionTable.COLUMN_QUESTION)));
                questions.setOption1(c.getString(c.getColumnIndex(QuizContract.QuestionTable.COLUMN_OPTION1)));
                questions.setOption2(c.getString(c.getColumnIndex(QuizContract.QuestionTable.COLUMN_OPTION2)));
                questions.setOption3(c.getString(c.getColumnIndex(QuizContract.QuestionTable.COLUMN_OPTION3)));
                questions.setOption4(c.getString(c.getColumnIndex(QuizContract.QuestionTable.COLUMN_OPTION4)));
                questions.setAnsNr(c.getInt(c.getColumnIndex(QuizContract.QuestionTable.COLUMN_ANS)));

                questionsList.add(questions);

            }while(c.moveToNext());
        }

        c.close();
        return questionsList;
    }

}
