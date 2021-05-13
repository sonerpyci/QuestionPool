package tr.edu.yildiz.payci.soner.DAL;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.HashMap;

public class DbHelper extends SQLiteOpenHelper {

    private static final SqlBuilder _sqlBuilder = new SqlBuilder();

    // Database Info
    private static final String DATABASE_NAME = "QuestionPool";
    private static final int DATABASE_VERSION = 3;

    //<editor-fold desc="Table Names">
    private static final String TABLE_USERS = "Users";
    private static final String TABLE_QUESTIONS = "Questions";
    private static final String TABLE_QUESTION_MEDIAS = "QuestionMedias";
    private static final String TABLE_EXAMS = "Exams";
    private static final String TABLE_QUESTIONS_TO_EXAMS = "QuestionsExams";
    //</editor-fold>

    //<editor-fold desc="Users Table Columns">
    private static final String KEY_USER_ID = "id";
    private static final String KEY_FIRST_NAME = "firstName";
    private static final String KEY_LAST_NAME = "lastName";
    private static final String KEY_USERNAME = "username";
    private static final String KEY_PASSWORD = "password";
    private static final String KEY_AVATAR = "avatar";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_PHONE = "phone";
    private static final String KEY_DATE_OF_BIRTH = "birthDate";
    //</editor-fold>


    //<editor-fold desc="Questions Table Columns">
    private static final String KEY_QUESTION_ID = "id";
    private static final String KEY_QUESTION_USER_FK = "userId";
    private static final String KEY_QUESTION_TEXT = "text";
    private static final String KEY_QUESTION_A = "a";
    private static final String KEY_QUESTION_B = "b";
    private static final String KEY_QUESTION_C = "c";
    private static final String KEY_QUESTION_D = "d";
    private static final String KEY_QUESTION_E = "e";
    private static final String KEY_QUESTION_CORRECT_ANSWER = "correctAnswer";
    //</editor-fold>

    //<editor-fold desc="QuestionMedias Table Columns">
    private static final String KEY_QUESTION_MEDIA_ID = "id";
    private static final String KEY_QUESTION_MEDIA_CONTENT = "content";
    private static final String KEY_QUESTION_MEDIA_TYPE = "contentType";
    private static final String KEY_QUESTION_MEDIA_FK = "questionId";
    //</editor-fold>

    //<editor-fold desc="Exams Table Columns">
    private static final String KEY_EXAM_ID = "id";
    private static final String KEY_EXAM_USER_ID = "userId";
    private static final String KEY_EXAM_NAME = "name";
    private static final String KEY_EXAM_DIFFICULTY = "difficulty";
    private static final String KEY_EXAM_MIN_DURATION = "minDuration";
    private static final String KEY_EXAM_MAX_DURATION = "maxDuration";
    //</editor-fold>

    //<editor-fold desc="ExamQuestions Table Columns">
    private static final String KEY_QUESTIONS_EXAMS_EXAM_ID = "examId";
    private static final String KEY_QUESTIONS_EXAMS_QUESTION_ID = "questionId";
    private static final String KEY_QUESTIONS_EXAMS_POINT = "questionPoint";
    //</editor-fold>

    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Called when the database connection is being configured.
    // Configure database settings for things like foreign key support, write-ahead logging, etc.
    @Override
    public void onConfigure(SQLiteDatabase db) {
        super.onConfigure(db);
        db.setForeignKeyConstraintsEnabled(true);
    }

    // Called when the database is created for the FIRST time.
    // If a database already exists on disk with the same DATABASE_NAME, this method will NOT be called.
    @Override
    public void onCreate(SQLiteDatabase db) {
        /*String CREATE_POSTS_TABLE = "CREATE TABLE " + TABLE_POSTS +
                "(" +
                KEY_POST_ID + " INTEGER PRIMARY KEY," + // Define a primary key
                KEY_POST_USER_ID_FK + " INTEGER REFERENCES " + TABLE_USERS + "," + // Define a foreign key
                KEY_POST_TEXT + " TEXT" +
                ")";*/

        /*String CREATE_USERS_TABLE = "CREATE TABLE " + TABLE_USERS +
            "(" +
                KEY_USER_ID + " INTEGER PRIMARY KEY," +
                KEY_FIRST_NAME + " TEXT," +
                KEY_LAST_NAME + " TEXT," +
                KEY_USERNAME + " TEXT," +
                KEY_PASSWORD + " TEXT," +
                KEY_AVATAR + " TEXT," +
                KEY_EMAIL + " TEXT," +
                KEY_PHONE + " TEXT," +
                KEY_DATE_OF_BIRTH + " TEXT" +
            ")";*/

        HashMap<String, String> createUserTableHashMap = new HashMap<String, String>() {{
            put(KEY_USER_ID, "INTEGER PRIMARY KEY");
            put(KEY_FIRST_NAME, "TEXT");
            put(KEY_LAST_NAME, "TEXT");
            put(KEY_USERNAME, "TEXT");
            put(KEY_PASSWORD, "TEXT");
            put(KEY_AVATAR, "TEXT");
            put(KEY_EMAIL, "TEXT");
            put(KEY_PHONE, "TEXT");
            put(KEY_DATE_OF_BIRTH, "TEXT");
        }};

        HashMap<String, String> createQuestionTableHashmap = new HashMap<String, String>() {{
            put(KEY_QUESTION_ID, "INTEGER PRIMARY KEY");
            put(KEY_QUESTION_USER_FK, "INTEGER REFERENCES " + TABLE_USERS);
            put(KEY_QUESTION_TEXT, "TEXT");
            put(KEY_QUESTION_A, "TEXT");
            put(KEY_QUESTION_B, "TEXT");
            put(KEY_QUESTION_C, "TEXT");
            put(KEY_QUESTION_D, "TEXT");
            put(KEY_QUESTION_E, "TEXT");
            put(KEY_QUESTION_CORRECT_ANSWER, "TEXT");
        }};

        String CREATE_USERS_TABLE_SQL = _sqlBuilder.BuildCreateTableCommand(TABLE_USERS, createUserTableHashMap);
        String CREATE_QUESTIONS_TABLE_SQL = _sqlBuilder.BuildCreateTableCommand(TABLE_QUESTIONS, createQuestionTableHashmap);





        db.execSQL(CREATE_USERS_TABLE_SQL);
        db.execSQL(CREATE_QUESTIONS_TABLE_SQL);
    }

    // Called when the database needs to be upgraded.
    // This method will only be called if a database already exists on disk with the same DATABASE_NAME,
    // but the DATABASE_VERSION is different than the version of the database that exists on disk.
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion != newVersion) {
            // Simplest implementation is to drop all old tables and recreate them
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_QUESTIONS);

            onCreate(db);
        }
    }
}