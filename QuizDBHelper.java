package com.example.swarangigaurkar.learnersapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


import java.util.ArrayList;
import java.util.List;

import static android.provider.BaseColumns._ID;


public class QuizDBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME ="NewQuizDb.db";
    private static final int DATABASE_VERSION =1;

    private  static  QuizDBHelper instance;

    private SQLiteDatabase db;

    private QuizDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static  synchronized QuizDBHelper getInstance(Context context)
    {
        if(instance==null)
        {
            instance = new QuizDBHelper(context.getApplicationContext());
        }
        return  instance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        this.db = db;

        final String SQL_CREATE_CATEGORIES_TABLE = "CREATE TABLE "+
                QuizContract.CategoriesTable.TABLE_NAME + " ( " +
                _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                QuizContract.CategoriesTable.NAME + " TEXT " +
                " )";

        final String SQL_CREATE_QUESTION_TABLE = "CREATE TABLE " +
                QuizContract.QuestionTable.TABLE_NAME + " ( "+
                _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "+
                QuizContract.QuestionTable.COLUMN_QUESTION + " TEXT, " +
                QuizContract.QuestionTable.COLUMN_OPTION1 + " TEXT, "  +
                QuizContract.QuestionTable.COLUMN_OPTION2 + " TEXT, "  +
                QuizContract.QuestionTable.COLUMN_OPTION3 + " TEXT, "  +
                QuizContract.QuestionTable.COLUMN_OPTION4 + " TEXT,"  +
                QuizContract.QuestionTable.COLUMN_ANS + " INTEGER, "  +
                QuizContract.QuestionTable.COLUMN_DIFFICULTY+ " TEXT, " +
                QuizContract.QuestionTable.COLUMN_CATEGORY_ID+ " INTEGER, " +
                "FOREIGN KEY(" + QuizContract.QuestionTable.COLUMN_CATEGORY_ID  + ") REFERENCES " +
                QuizContract.CategoriesTable.TABLE_NAME + "(" + _ID + ")" + "ON DELETE CASCADE" +
                " )";

        db.execSQL(SQL_CREATE_CATEGORIES_TABLE);
        db.execSQL(SQL_CREATE_QUESTION_TABLE);

        fillCategoriesTable();
        fillQuestionsTable();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + QuizContract.CategoriesTable.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + QuizContract.QuestionTable.TABLE_NAME);
        onCreate(db);

    }

    @Override
    public void onConfigure(SQLiteDatabase db) {
        super.onConfigure(db);
        db.setForeignKeyConstraintsEnabled(true);
    }

    public void fillCategoriesTable()
    {
        Category c1 = new Category("C");
        addCategory(c1);
        Category c2 = new Category("Java");
        addCategory(c2);
        Category c3 = new Category("Android");
        addCategory(c3);
        Category c4 = new Category("CPP");
        addCategory(c4);

    }
    private  void addCategory(Category category)
    {
        ContentValues cv = new ContentValues();
        cv.put(QuizContract.CategoriesTable.NAME,category.getName());
        db.insert(QuizContract.CategoriesTable.TABLE_NAME,null,cv);
    }

    public void fillQuestionsTable()
    {
        Questions q1= new Questions("Which is not an access specifier" , "public","private","protected","main",4,
                Questions.DIFFICULTY_EASY,Category.C);
        addQuestion(q1);
        Questions q2= new Questions("Inline functions are useful when" , "Function is large with many nested loops","Function has many static variables","Function is small and we want to avoid function call overhead","None of the above",3,
                Questions.DIFFICULTY_EASY,Category.CPP);
        addQuestion(q2);
        Questions q3= new Questions("Which of the following is true?" , "All objects of a class share all data members of class","Objects of a class do not share non-static members. Every object has its own copy.","Objects of a class do not share codes of non-static methods, they have their own copy","None Of the above",2,
                Questions.DIFFICULTY_MEDIUM,Category.JAVA);
        addQuestion(q3);
        Questions q4= new Questions("ANDROID, Hard: #include<iostream>\n" +
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
                "}\n" , "12 4","12 12","8 4","8 8",3,Questions.DIFFICULTY_HARD,Category.ANDROID);
        addQuestion(q4);
        Questions q5= new Questions("ANDROID, Hard: #include<iostream>\n" +
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
                "}" , "There are compiler errors in lines \"Base b;\" and \"Base bp;\"","There is compiler error in line \"Base b;","There is compiler error in line \"Base bp;\"","No compiler Error",2,Questions.DIFFICULTY_HARD,Category.ANDROID);
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
        values.put(QuizContract.QuestionTable.COLUMN_DIFFICULTY, question.getDifficulty());
        values.put(QuizContract.QuestionTable.COLUMN_CATEGORY_ID, question.getCategory_id());

        db.insert(QuizContract.QuestionTable.TABLE_NAME,null,values);

    }
    public List<Category> getAllCategories()
    {
        List<Category> categoryList = new ArrayList<Category>();
        db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + QuizContract.CategoriesTable.TABLE_NAME ,null);
        if(cursor.moveToFirst())
        {
            do {
                Category category = new Category();
                category.setId(cursor.getInt(cursor.getColumnIndex(QuizContract.CategoriesTable._ID)));
                category.setName(cursor.getString(cursor.getColumnIndex(QuizContract.CategoriesTable.NAME)));
                categoryList.add(category);
            }while(cursor.moveToNext());
        }
        cursor.close();
        return  categoryList;
    }

    public List<Questions> getQuestions(int categoryID,String difficulty )
    {
        List<Questions> questionsList =new ArrayList<Questions>();
        db =getReadableDatabase();
        String selection = QuizContract.QuestionTable.COLUMN_CATEGORY_ID + " = ? " +
                " AND " + QuizContract.QuestionTable.COLUMN_DIFFICULTY + " = ? ";
        String[] selectionArgs = new String[]{String.valueOf(categoryID),difficulty};


        Cursor c = db.query(QuizContract.QuestionTable.TABLE_NAME,null,selection,selectionArgs,null,null,
                null);

        if(c.moveToFirst())
        {
            do {

                Questions questions = new Questions();
                questions.setId(c.getInt(c.getColumnIndex(_ID)));
                questions.setQuestion(c.getString(c.getColumnIndex(QuizContract.QuestionTable.COLUMN_QUESTION)));
                questions.setOption1(c.getString(c.getColumnIndex(QuizContract.QuestionTable.COLUMN_OPTION1)));
                questions.setOption2(c.getString(c.getColumnIndex(QuizContract.QuestionTable.COLUMN_OPTION2)));
                questions.setOption3(c.getString(c.getColumnIndex(QuizContract.QuestionTable.COLUMN_OPTION3)));
                questions.setOption4(c.getString(c.getColumnIndex(QuizContract.QuestionTable.COLUMN_OPTION4)));
                questions.setAnsNr(c.getInt(c.getColumnIndex(QuizContract.QuestionTable.COLUMN_ANS)));
                questions.setDifficulty(c.getString(c.getColumnIndex(QuizContract.QuestionTable.COLUMN_DIFFICULTY)));
                questions.setCategory_id(c.getInt(c.getColumnIndex(QuizContract.QuestionTable.COLUMN_CATEGORY_ID)));

                questionsList.add(questions);

            }while(c.moveToNext());
        }

        c.close();
        return questionsList;
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
                questions.setId(c.getInt(c.getColumnIndex(_ID)));
                questions.setQuestion(c.getString(c.getColumnIndex(QuizContract.QuestionTable.COLUMN_QUESTION)));
                questions.setOption1(c.getString(c.getColumnIndex(QuizContract.QuestionTable.COLUMN_OPTION1)));
                questions.setOption2(c.getString(c.getColumnIndex(QuizContract.QuestionTable.COLUMN_OPTION2)));
                questions.setOption3(c.getString(c.getColumnIndex(QuizContract.QuestionTable.COLUMN_OPTION3)));
                questions.setOption4(c.getString(c.getColumnIndex(QuizContract.QuestionTable.COLUMN_OPTION4)));
                questions.setAnsNr(c.getInt(c.getColumnIndex(QuizContract.QuestionTable.COLUMN_ANS)));
                questions.setDifficulty(c.getString(c.getColumnIndex(QuizContract.QuestionTable.COLUMN_DIFFICULTY)));
                questions.setCategory_id(c.getInt(c.getColumnIndex(QuizContract.QuestionTable.COLUMN_CATEGORY_ID)));

                questionsList.add(questions);

            }while(c.moveToNext());
        }

        c.close();
        return questionsList;
    }

}