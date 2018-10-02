package com.example.shanay.quizapp2;

public class Questions  {
    public static  final String DIFFICULTY_EASY = "Easy";
    public static  final String DIFFICULTY_MEDIUM = "Medium";
    public static  final String DIFFICULTY_HARD = "Hard";

   // private int id;
    private String question;
    private String option1;
    private String option2;
    private String option3;
    private String option4;
    private int ansNr;
    private String difficulty;
    //private int category_id;

    public Questions()
    {}

    public Questions(String question, String option1, String option2, String option3, String option4,
                     int ansNr,String difficulty)
    {
        this.question = question;
        this.option1 = option1;
        this.option2 = option2;
        this.option3 = option3;
        this.option4 = option4;
        this.ansNr = ansNr;
        this.difficulty = difficulty;
        //this.category_id = category_id;
    }

   /* public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
*/
    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getOption1() {
        return option1;
    }

    public void setOption1(String option1) {
        this.option1 = option1;
    }

    public String getOption2() {
        return option2;
    }

    public void setOption2(String option2) {
        this.option2 = option2;
    }

    public String getOption3() {
        return option3;
    }

    public void setOption3(String option3) {
        this.option3 = option3;
    }

    public String getOption4() {
        return option4;
    }

    public void setOption4(String option4) {
        this.option4 = option4;
    }

    public int getAnsNr() {
        return ansNr;
    }

    public void setAnsNr(int ansNr) {
        this.ansNr = ansNr;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

   /* public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }*/

    public static String[] getAllDifficultyLevels()
    {
        return new  String[]{
                DIFFICULTY_EASY,
                DIFFICULTY_MEDIUM,
                DIFFICULTY_HARD
    };

    }

}
