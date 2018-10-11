package com.example.shanay.newquiz1;

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
    private static final int DATABASE_VERSION =2;

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
                Questions.DIFFICULTY_EASY,Category.CPP);
        addQuestion(q1);
        Questions q2= new Questions("Inline functions are useful when" , "Function is large with many nested loops","Function has many static variables","Function is small and we want to avoid function call overhead","None of the above",3,
                Questions.DIFFICULTY_EASY,Category.CPP);
        addQuestion(q2);
        Questions q3= new Questions("Which of the following is true?" , "All objects of a class share all data members of class","Objects of a class do not share non-static members. Every object has its own copy.","Objects of a class do not share codes of non-static methods, they have their own copy","None Of the above",2,
                Questions.DIFFICULTY_MEDIUM,Category.CPP);
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
                "}\n" , "12 4","12 12","8 4","8 8",3,Questions.DIFFICULTY_HARD,Category.CPP);
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
                "}" , "There are compiler errors in lines \"Base b;\" and \"Base bp;\"","There is compiler error in line \"Base b;","There is compiler error in line \"Base bp;\"","No compiler Error",2,Questions.DIFFICULTY_HARD,Category.CPP);
        addQuestion(q5);

        Questions q6  = new Questions("Can dot operator be applied to pointers","yes","no","yes with some limitations",
                "no,with some limitations",2,Questions.DIFFICULTY_EASY, Category.CPP
               );
        addQuestion(q6);

        Questions q7  = new Questions(" Which of the following is not used to seek a file pointer?","ios::cur","ios::set ",
                " ios::end ",
                " ios::beg",2,Questions.DIFFICULTY_MEDIUM, Category.CPP
        );
        addQuestion(q7);
        Questions q8  = new Questions(" During dynamic memory allocation in CPP, new operator returns _________ value if memory allocation is unsuccessful",
                "False","NULL","Zero ","None of these",2,Questions.DIFFICULTY_MEDIUM, Category.CPP
        );
        addQuestion(q8);
        Questions q9  = new Questions("Can dot operator be applied to pointers","yes","no","yes with some limitations",
                "no,with some limitations",2,Questions.DIFFICULTY_HARD, Category.CPP
        );
        addQuestion(q9);
        Questions q10  = new Questions(" C99 standard guarantees uniqueness of ____ characters for internal names."," 31" ,"63","12",
                "14",2,Questions.DIFFICULTY_EASY, Category.C
        );
        addQuestion(q10);
        Questions q11  = new Questions(" Which of the following is not a valid variable name declaration?"," int _a3" ,"int 3_a","int a_3",
                "int _3a",2,Questions.DIFFICULTY_EASY, Category.C
        );
        addQuestion(q11);
        Questions q12  = new Questions("Why do variable names beginning with the underscore is not encouraged?",
                " It is not standardized" ,
                " To avoid conflicts since assemblers and loaders use such names",
                " To avoid conflicts since library routines use such names" ,
                " To avoid conflicts with environment variables of an operating system",2,Questions.DIFFICULTY_MEDIUM, Category.C);
        addQuestion(q12);
        Questions q13  = new Questions("Arguments that take input by user before running a program are called?",
                " main function arguments\n" ,
                " main arguments\n" ,
                " Command-Line arguments\n" ,
                " Parameterized arguments",3,Questions.DIFFICULTY_EASY, Category.C
        );
        addQuestion(q13);
        Questions q14  = new Questions("The maximum number of arguments that can be passed in a single function are_____________\n" ,
                " 127\n" ,
                " 253\n" ,
                " 361\n" ,
                " No limits in number of arguments",2,Questions.DIFFICULTY_MEDIUM, Category.C
        );
        addQuestion(q14);
        Questions q15  = new Questions("1. Which of the following are C preprocessors?\n" ,
                " #ifdef\n" ,
                " #define\n" ,
                " #endif\n" ,
                " all of the mentioned",4,Questions.DIFFICULTY_HARD, Category.C
        );
        addQuestion(q15);
        Questions q16  = new Questions("The C-preprocessors are specified with _________symbol.\n" ,
                " #\n" ,
                " $\n" ,
                " ” ”\n" ,
                " None of the mentioned\n",1,Questions.DIFFICULTY_MEDIUM, Category.C
        );
        addQuestion(q16);
        Questions q17  = new Questions(" Automatic variables are variables that are\n" ,
                " Declared within the scope of a block, usually a function\n" ,
                " Declared outside all functions\n" ,
                " Declared with auto keyword\n" ,
                " Declared within the keyword extern",1,Questions.DIFFICULTY_HARD, Category.C
        );
        addQuestion(q17);
        Questions q18  = new Questions("Automatic variables\n" ,
                " Exist only within that scope in which it is declared\n" ,
                " Cease to exist after the block is exited\n" ,
                " Exist only within that scope in which it is declared & exist after the block is exited\n" ,
                " Only 1",3,Questions.DIFFICULTY_HARD, Category.C
        );
        addQuestion(q18);
        Questions q19  = new Questions("Which object of HttpSession can be used to view and manipulate information about a session?\n",
                " session identifier\n" ,
                " creation time\n" ,
                " last accessed time\n" ,
                " All mentioned above ",4,Questions.DIFFICULTY_HARD, Category.JAVA
        );
        addQuestion(q19);
        Questions q20  = new Questions("Which case of a session bean obtains the UserTransaction object via the EJBContext using the getUserTransaction() method in EJB transaction management?\n" ,
                " Bean-managed transactions \n" ,
                " Container-managed transactions\n" ,
                " Both A & B\n" ,
                " None of the above",1,Questions.DIFFICULTY_MEDIUM, Category.JAVA
        );
        addQuestion(q20);
        Questions q21  = new Questions("In RMI Architecture which layer Intercepts method calls made by the client/redirects these calls to a remote RMI service?\n",
                " Stub & Skeleton Layer\n" ,
                "Application Layer\n" ,
                " Remote Reference Layer\n" ,
                " Transport Layer",1,Questions.DIFFICULTY_HARD, Category.JAVA
        );
        addQuestion(q21);
        Questions q22  = new Questions("Which attribute specifies a JSP page that should process any exceptions thrown but not caught in the current page?\n",
                " The ErrorPage Attribute \n" ,
                " The IsErrorPage Attribute\n",
                " Both A & B\n" ,
                " None of the above",1,Questions.DIFFICULTY_EASY, Category.JAVA
        );
        addQuestion(q22);
        Questions q23  = new Questions(" Which method is used for retrieving streams of both ASCII and Unicode characters is new in the JDBC 2.0 core API?\n",
                " getCharacterStream \n" ,
                " getBinaryStream\n" ,
                " getAsciiStream\n" ,
                " getUnicodeStream",1,Questions.DIFFICULTY_MEDIUM, Category.JAVA
        );
        addQuestion(q23);
        Questions q24  = new Questions(" In Servlet Terminology what provides runtime environment for JavaEE (j2ee) applications. It performs many operations that are given below:\n" +
                "\n" +
                "1. Life Cycle Management\n" +
                "2. Multithreaded support\n" +
                "3. Object Pooling\n" +
                "4. Security etc",
                " Server\n" ,
                " Webserver\n" ,
                " Container\n" ,
                " Application Server",3,Questions.DIFFICULTY_HARD, Category.JAVA
        );
        addQuestion(q24);
        Questions q25  = new Questions("Abbreviate the term UDA?\n",
                " Unified Data Access\n" ,
                " Universal Data Access \n" ,
                " Universal Digital Access\n" ,
                " Uniform Data Access",2,Questions.DIFFICULTY_EASY, Category.JAVA
        );
        addQuestion(q25);
        Questions q26  = new Questions("Which methods are provided by the PrintStream class?\n",
                " Read data to another stream\n" ,
                " Write data to another stream \n" ,
                " Read data to same stream\n" ,
                " Write data to same stream",2,Questions.DIFFICULTY_MEDIUM, Category.JAVA
        );
        addQuestion(q26);
        Questions q27  = new Questions("Which is irrecoverable?\n",
                " Error \n" ,
                " Checked Exception\\n" ,
                " Unchecked Exception\n" ,
                " Both B & C",1,Questions.DIFFICULTY_HARD, Category.JAVA
        );
        addQuestion(q27);
        Questions q28  = new Questions("How many types of constructor are defined in the StringTokenizer class?\n",
                " 2\n" ,
                " 3\n" ,
                " 4\n" ,
                " 5",3,Questions.DIFFICULTY_EASY, Category.JAVA
        );
        addQuestion(q28);
        Questions q29 = new Questions("Mutual exclusive and inter-thread communication are which type of Synchorization?\n",
                " Thread Synchronization \n" ,
                " Process Synchronization\n" ,
                " Object Synchronization\n" ,
                "d None of the above",1,Questions.DIFFICULTY_MEDIUM, Category.JAVA
        );
        addQuestion(q29);
        Questions q30  = new Questions("Dalvik Virtual Machine (DVM) actually uses core features of\n" ,
                "Windows\n" ,
                "Mac\n" ,
                "Linux\n" ,
                "Contiki",3,Questions.DIFFICULTY_EASY, Category.ANDROID
        );
        addQuestion(q30);
        Questions q31  = new Questions(" A type of service provided by android that allows sharing and publishing of data to other applications is\n" ,
                "View System\n" ,
                "Content Providers\n" ,
                "Activity Manager\n" ,
                "Notifications Manager",2,Questions.DIFFICULTY_HARD, Category.ANDROID
        );
        addQuestion(q31);
        Questions q32  = new Questions("Android library that provides access to UI pre-built elements such as buttons, lists, views etc. is\n",
                "android.text\n" ,
                "android.os\n" ,
                "android.view\n" ,
                "android.webkit",4,Questions.DIFFICULTY_MEDIUM, Category.ANDROID
        );
        addQuestion(q32);
        Questions q33  = new Questions("A type of service provided by android that shows messages and alerts to user is\n",
                "Content Providers\n" ,
                "View System\n" ,
                "Notifications Manager\n" ,
                "Activity Manager",3,Questions.DIFFICULTY_EASY, Category.ANDROID
        );
        addQuestion(q33);
        Questions q34  = new Questions("Which i s not a component of APK file?","Resources","All components of APK","Native libraries","Dalvik executables",2,Questions.DIFFICULTY_HARD, Category.ANDROID
        );
        addQuestion(q34);
        Questions q35  = new Questions(" Activity diagram, use case diagram, collaboration diagram and sequence diagram are considered as types of\n" ,
                "non-behavioral diagrams\n" ,
                "non structural diagrams\n" ,
                "structural diagrams\n" ,
                "behavioral diagrams",4,Questions.DIFFICULTY_MEDIUM, Category.ANDROID
        );
        addQuestion(q35);
        Questions q36  = new Questions("Kind of diagrams which are used to show interactions between series of messages are classified as\n",
                "activity diagrams\n" ,
                "state chart diagrams\n" ,
                "collaboration diagrams\n" ,
                "object lifeline diagrams",3,Questions.DIFFICULTY_EASY, Category.ANDROID
        );
        addQuestion(q36);
        Questions q37  = new Questions("Diagrams which are used to distribute files, libraries and tables across topology of hardware are called\n" ,
                "\n" +
                "deployment diagrams\n" ,
                "use case diagrams\n" ,
                "sequence diagrams\n" ,
                "collaboration diagrams",1,Questions.DIFFICULTY_HARD, Category.ANDROID
        );
        addQuestion(q37);
        Questions q38  = new Questions("Dynamic aspects related to a system are shown with help of\n" +
                "\n" ,
                "sequence diagrams\n" ,
                "interaction diagrams\n",
                "deployment diagrams\n" ,
                "use case diagrams",2,Questions.DIFFICULTY_MEDIUM, Category.ANDROID
        );
        addQuestion(q38);
        Questions q39  = new Questions("When did Google purchase Android","2007","2005","2010","2008",2,Questions.DIFFICULTY_EASY, Category.ANDROID
        );
        addQuestion(q39);
        Questions q40 = new Questions("What is the output of this C code?\n" +
                "\n" +
                "    #include <stdio.h>\n" +
                "    int main()\n" +
                "    {\n" +
                "        char buf[12];\n" +
                "        stderr = stdin;\n" +
                "        fscanf(stderr, \"%s\", buf);\n" +
                "        printf(\"%s\\n\", buf);\n" +
                "    }\n" ,
                " Compilation error\n" ,
                " Undefined behaviour\n" ,
                " Whatever user types\n" ,
                " None of the mentioned",3,Questions.DIFFICULTY_MEDIUM, Category.C
        );
        addQuestion(q40);

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
