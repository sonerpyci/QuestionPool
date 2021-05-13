package tr.edu.yildiz.payci.soner.DAL;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import tr.edu.yildiz.payci.soner.model.Person;

public class DbHelper extends SQLiteOpenHelper {

    private static final SqlBuilder _sqlBuilder = new SqlBuilder();

    //<editor-fold desc="Table Names">
    private static final String DATABASE_NAME = "QuestionPool";
    private static final int DATABASE_VERSION = 3;
    //</editor-fold>

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
    private static final String KEY_QUESTION_MEDIA_QUESTIONS_FK = "questionId";
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
            put(KEY_AVATAR, "BLOB");
            put(KEY_EMAIL, "TEXT");
            put(KEY_PHONE, "TEXT");
            put(KEY_DATE_OF_BIRTH, "TEXT");
        }};

        HashMap<String, String> createQuestionTableHashMap = new HashMap<String, String>() {{
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

        HashMap<String, String> createQuestionMediasTableHashMap = new HashMap<String, String>() {{
            put(KEY_QUESTION_MEDIA_ID, "INTEGER PRIMARY KEY");
            put(KEY_QUESTION_MEDIA_QUESTIONS_FK, "INTEGER REFERENCES " + TABLE_QUESTIONS);
            put(KEY_QUESTION_MEDIA_CONTENT, "BLOB");
            put(KEY_QUESTION_MEDIA_TYPE, "TEXT");
        }};

        HashMap<String, String> createExamsTableHashMap = new HashMap<String, String>() {{
            put(KEY_EXAM_ID, "INTEGER PRIMARY KEY");
            put(KEY_EXAM_USER_ID, "INTEGER REFERENCES " + TABLE_USERS);
            put(KEY_EXAM_NAME, "TEXT");
            put(KEY_EXAM_DIFFICULTY, "INT");
            put(KEY_EXAM_MIN_DURATION, "INT");
            put(KEY_EXAM_MAX_DURATION, "INT");
        }};

        HashMap<String, String> createExamsQuestionsTableHashMap = new HashMap<String, String>() {{
            put(KEY_QUESTIONS_EXAMS_EXAM_ID, "INTEGER REFERENCES " + TABLE_EXAMS);
            put(KEY_QUESTIONS_EXAMS_QUESTION_ID, "INTEGER REFERENCES " + TABLE_QUESTIONS);
            put(KEY_EXAM_DIFFICULTY, "REAL");
        }};

        String CREATE_USERS_TABLE_SQL = _sqlBuilder.BuildCreateTableCommand(TABLE_USERS, createUserTableHashMap);
        String CREATE_QUESTIONS_TABLE_SQL = _sqlBuilder.BuildCreateTableCommand(TABLE_QUESTIONS, createQuestionTableHashMap);
        String CREATE_QUESTION_MEDIAS_TABLE_SQL = _sqlBuilder.BuildCreateTableCommand(TABLE_QUESTION_MEDIAS, createQuestionMediasTableHashMap);
        String CREATE_EXAMS_TABLE_SQL = _sqlBuilder.BuildCreateTableCommand(TABLE_EXAMS, createExamsTableHashMap);
        String CREATE_QUESTIONS_TO_EXAMS_TABLE_SQL = _sqlBuilder.BuildCreateTableCommand(TABLE_QUESTIONS_TO_EXAMS, createExamsQuestionsTableHashMap);



        db.execSQL(CREATE_USERS_TABLE_SQL);
        db.execSQL(CREATE_QUESTIONS_TABLE_SQL);
        db.execSQL(CREATE_QUESTION_MEDIAS_TABLE_SQL);
        db.execSQL(CREATE_EXAMS_TABLE_SQL);
        db.execSQL(CREATE_QUESTIONS_TO_EXAMS_TABLE_SQL);
    }

    // Called when the database needs to be upgraded.
    // This method will only be called if a database already exists on disk with the same DATABASE_NAME,
    // but the DATABASE_VERSION is different than the version of the database that exists on disk.
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion != newVersion) {
            // Simplest implementation is to drop all old tables and recreate them
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_QUESTIONS_TO_EXAMS);
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_QUESTION_MEDIAS);
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_QUESTIONS);
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_EXAMS);
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);

            onCreate(db);
        }
    }

    public List<Person> getUsers() {
        List<Person> users = new ArrayList<Person>();

        try {
            ArrayList<String> selectColumns = new ArrayList<String>() {{ add("*"); }};

            SQLiteDatabase db = this.getReadableDatabase();
            String myFormat = "dd/MM/yyyy"; //In which you need put here
            SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

            String sql = _sqlBuilder.BuildSelectCommand(TABLE_USERS, selectColumns, null);
            Cursor c = db.rawQuery(sql, null);

            if (c.moveToFirst()){
                do {
                    int userId = c.getInt(0);
                    String userFirstName = c.getString(1);
                    String userLastName = c.getString(2);
                    String userUsername = c.getString(3);
                    String userPassword = c.getString(4);
                    byte[] userAvatar = c.getBlob(5);
                    String userEmail = c.getString(6);
                    String userPhone = c.getString(7);
                    Date userDateOfBirth = new SimpleDateFormat("dd/MM/yyyy", Locale.US).parse(c.getString(8));

                    /*String avatarPath = userUsername+"_avatar.jpg";
                    File file = new File(avatarPath);
                    FileOutputStream avatarFile = new FileOutputStream(file);
                    avatarFile.write(userAvatar);*/
                    users.add(new Person(userId, userFirstName, userLastName, userEmail, userPassword, userAvatar, userPhone, userDateOfBirth));
                } while(c.moveToNext());
            }
            c.close();

            return users;
        } catch (Exception e) {
            e.printStackTrace();
            return users;
        }
    }

    public Person insertUser(Person person) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.US);

            ArrayList<String> createUserTableHashMap = new ArrayList<String>() {{
                add(KEY_FIRST_NAME);
                add(KEY_LAST_NAME);
                add(KEY_USERNAME);
                add(KEY_PASSWORD);
                add(KEY_EMAIL);
                add(KEY_PHONE);
                add(KEY_DATE_OF_BIRTH);
                add(KEY_AVATAR);
            }};

            String insertUserSql = _sqlBuilder.BuildInsertCommand(TABLE_USERS, createUserTableHashMap);

            SQLiteDatabase db = this.getWritableDatabase();
            db.beginTransaction();
            SQLiteStatement insertStmt = db.compileStatement(insertUserSql);

            insertStmt.clearBindings();

            insertStmt.bindString(1, person.getFirstName());
            insertStmt.bindString(2, person.getLastName());
            insertStmt.bindString(3, person.getUsername());
            insertStmt.bindString(4, person.getPassword());
            insertStmt.bindString(5, person.getEmail());
            insertStmt.bindString(6, person.getPhone());
            insertStmt.bindString(7, sdf.format(person.getBirthDate()));
            insertStmt.bindBlob(8, person.getAvatar());

            long userId = insertStmt.executeInsert();
            db.setTransactionSuccessful();
            db.endTransaction();

            person.setId(userId);

            return person;
        } catch (Exception e) {
            e.printStackTrace();
            return person;
        }
    }

    public boolean saveBytes(byte[] bytes, int id){

        boolean ret = false;
        SQLiteDatabase db = getWritableDatabase();


        try{

            String sql   =   "INSERT INTO IMAGES "
                    + " ( IMAGE_ID"
                    + ", IMAGE_BLOB"
                    + " ) VALUES(?,?)";

            SQLiteStatement insertStmt      =   db.compileStatement(sql);
            insertStmt.clearBindings();
            insertStmt.bindLong(1, id);
            insertStmt.bindBlob(2, bytes);
            insertStmt.executeInsert();

            db.setTransactionSuccessful();
            db.endTransaction();

            ret = true;
        }catch(Exception e){
            e.printStackTrace();
            ret = false;
        }

        return ret;
    }

    /*public byte[] getBytes( int id) throws Exception {

        byte[] ret = null;

        try {

            String selectQuery = "SELECT  I.IMAGE_BLOB "
                    + "         FROM IMAGES I WHERE I.IMAGE_ID = ?";

            SQLiteDatabase db = this.getReadableDatabase();
            Cursor c = db.rawQuery(selectQuery,new String[]{String.valueOf(id)});


            if (!c.isClosed() && c.moveToFirst() && c.getCount() > 0) {

                if (c.getBlob(c.getColumnIndex("IMAGE_BLOB")) != null)
                {
                    ret = c.getBlob(c.getColumnIndex("IMAGE_BLOB"));

                }
                c.close();
                if (db != null && db.isOpen())
                    db.close();
            }
            System.gc();
        } catch (Exception e) {
            System.gc();
            throw e;

        }
    }*/


    public boolean checkIfUserExists(String email) {
        try {
            ArrayList<String> selectColumns = new ArrayList<String>() {{ add(KEY_EMAIL); }};
            HashMap<String, String> whereParams = new HashMap<String, String>() {{
                put(KEY_EMAIL, email);
            }};

            SQLiteDatabase db = this.getReadableDatabase();

            String sql = _sqlBuilder.BuildSelectCommand(TABLE_USERS, selectColumns, whereParams);
            Cursor c = db.rawQuery(sql, null);

            if (!(c.moveToFirst()) || c.getCount() == 0){
                c.close();
                return false; // user not exists
            }
            c.close();
            return true; // user exists
        } catch (Exception e) {
            e.printStackTrace();
            // maybe any undhandled exception with email etc...
            // return true to prevent signup operation with non-clear data.
            return true;
        }
    }

    public boolean checkUsernameAndPassword(String username, String password) {
        List<Person> users = new ArrayList<Person>();
        try {
            ArrayList<String> selectColumns = new ArrayList<String>() {{ add("*"); }};
            HashMap<String, String> whereParams = new HashMap<String, String>() {{
                put(KEY_USERNAME, username);
                put(KEY_PASSWORD, password);
            }};

            SQLiteDatabase db = this.getReadableDatabase();
            String myFormat = "dd/MM/yyyy"; //In which you need put here


            String sql = _sqlBuilder.BuildSelectCommand(TABLE_USERS, selectColumns, whereParams);
            Cursor c = db.rawQuery(sql, null);


            if (!(c.moveToFirst()) || c.getCount() == 0){
                c.close();
                return false; // user not exists
            }
            c.close(); // user exists
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false; // user not exists
        }
    }

}