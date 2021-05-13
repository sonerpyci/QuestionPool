package tr.edu.yildiz.payci.soner.DAL;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelper extends SQLiteOpenHelper {
    // Database Info
    private static final String DATABASE_NAME = "QuestionPool";
    private static final int DATABASE_VERSION = 2;

    // Table Names
    private static final String TABLE_USERS = "Users";
    private static final String TABLE_QUESTIONS = "Questions";
    private static final String TABLE_QUESTION_MEDIAS = "QuestionMedias";
    private static final String TABLE_EXAMS = "Exams";
    private static final String TABLE_QUESTIONS_TO_EXAMS = "QuestionsExams";

    // Users Table Columns
    private static final String KEY_USER_ID = "id";
    private static final String KEY_FIRST_NAME = "firstName";
    private static final String KEY_LAST_NAME = "lastName";
    private static final String KEY_USERNAME = "username";
    private static final String KEY_PASSWORD = "password";
    private static final String KEY_AVATAR = "avatar";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_PHONE = "phone";
    private static final String KEY_DATE_OF_BIRTH = "birthDate";

    // Questions Table Columns
    private static final String KEY_QUESTION_ID = "id";
    private static final String KEY_QUESTION_USER_FK = "userId";
    private static final String KEY_QUESTION_TEXT = "text";
    private static final String KEY_QUESTION_A = "a";
    private static final String KEY_QUESTION_B = "b";
    private static final String KEY_QUESTION_C = "c";
    private static final String KEY_QUESTION_D = "d";
    private static final String KEY_QUESTION_E = "e";
    private static final String CORRECT_ANSWER = "correctAnswer";

    // QuestionMedias Table Columns
    private static final String KEY_QUESTION_MEDIA_ID = "id";
    private static final String KEY_QUESTION_MEDIA_CONTENT = "content";
    private static final String KEY_QUESTION_MEDIA_FK = "questionId";

    // Exams Table Columns
    private static final String KEY_EXAM_ID = "id";
    private static final String KEY_EXAM_NAME = "name";
    private static final String KEY_EXAM_DIFFICULTY = "difficulty";
    private static final String KEY_EXAM_MIN_DURATION = "minDuration";
    private static final String KEY_EXAM_MAX_DURATION = "maxDuration";


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
                ")";

        String CREATE_USERS_TABLE = "CREATE TABLE " + TABLE_USERS +
                "(" +
                KEY_USER_ID + " INTEGER PRIMARY KEY," +
                KEY_USER_NAME + " TEXT," +
                KEY_USER_PROFILE_PICTURE_URL + " TEXT" +
                ")";
        */

        String CREATE_USERS_TABLE = "CREATE TABLE " + TABLE_USERS +
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
            ")";

        //db.execSQL(CREATE_POSTS_TABLE);
        db.execSQL(CREATE_USERS_TABLE);
    }

    // Called when the database needs to be upgraded.
    // This method will only be called if a database already exists on disk with the same DATABASE_NAME,
    // but the DATABASE_VERSION is different than the version of the database that exists on disk.
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion != newVersion) {
            // Simplest implementation is to drop all old tables and recreate them
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_POSTS);
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
            onCreate(db);
        }
    }
}