package tr.edu.yildiz.payci.soner;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.shapes.Shape;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.PhoneNumberFormattingTextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Pattern;

import tr.edu.yildiz.payci.soner.model.Person;
import tr.edu.yildiz.payci.soner.model.UserBase;

public class SignUpActivity extends AppCompatActivity {
    final Integer PICK_PHOTO_FOR_AVATAR = 1;
    final Calendar myCalendar = Calendar.getInstance();
    final Pattern EMAIL_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
    //UserBase userBase;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        //userBase = ((ArrayList<UserBase>) getIntent().getSerializableExtra("userBases")).get(0);

        defineListeners();
    }

    private void updateLabel() {
        EditText birthDateField = (EditText) findViewById(R.id.birthDate);
        String myFormat = "dd/MM/yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        birthDateField.setText(sdf.format(myCalendar.getTime()));
    }

    public void defineListeners() {
        EditText phoneField = (EditText) findViewById(R.id.phoneTxt);
        phoneField.addTextChangedListener(new PhoneNumberFormattingTextWatcher());

        EditText birthDateField = (EditText) findViewById(R.id.birthDate);
        ImageView avatarBox = (ImageView) findViewById(R.id.avatarBox);
        DatePickerDialog.OnDateSetListener date = (view, year, monthOfYear, dayOfMonth) -> {
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, monthOfYear);
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateLabel();
        };

        birthDateField.setOnClickListener((v) -> {
            new DatePickerDialog(SignUpActivity.this, date, myCalendar
                    .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                    myCalendar.get(Calendar.DAY_OF_MONTH)).show();
        });

        avatarBox.setOnClickListener((v) -> {
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(
                    Intent.createChooser(intent, "Complete action using"),
                    PICK_PHOTO_FOR_AVATAR);
        });

        Button btnSignUp = (Button) findViewById(R.id.signUpBtn);
        btnSignUp.setOnClickListener((v) -> {

            EditText firstName = (EditText) findViewById(R.id.firstNameTxt);
            EditText lastName = (EditText) findViewById(R.id.lastNameTxt);
            EditText email = (EditText) findViewById(R.id.emailTxt);
            EditText phone = (EditText) findViewById(R.id.phoneTxt);
            EditText birthDate = (EditText) findViewById(R.id.birthDate);
            EditText password = (EditText) findViewById(R.id.passTxt);
            EditText passwordRe = (EditText) findViewById(R.id.passReTxt);

            validateInputForm(firstName, lastName, email, phone, birthDate, password, passwordRe);
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_PHOTO_FOR_AVATAR && resultCode == Activity.RESULT_OK) {
            if (data == null) {
                Toast.makeText(SignUpActivity.this, "Dosya Okunamadı.", Toast.LENGTH_SHORT).show();
                return;
            }
            try {
                InputStream inputStream = this.getContentResolver().openInputStream(data.getData());
                byte[] bytes = readAllBytes(inputStream);


            } catch (IOException e) {
                Toast.makeText(SignUpActivity.this, "Sistem Belirtilen Dosyayı Bulamıyor.", Toast.LENGTH_SHORT).show();
            }
            //Now you can do whatever you want with your inpustream, save it as file, upload to a server, decode a bitmap...


        }
    }

    public void signUp(EditText firstName, EditText lastName, EditText email, EditText phone, EditText birthDate, EditText password) throws ParseException {
        // hash password
        //String hashedPassword = UserBase.convertStringToSHA256(password.getText().toString()) ;

        // convert birthDate to Date
        //Date birthDateAsDate = new SimpleDateFormat("dd/MM/yyyy", Locale.US).parse(birthDate.getText().toString());

        //Person person = new Person(firstName.getText().toString(), lastName.getText().toString(), email.getText().toString(), hashedPassword, phone.getText().toString(), birthDateAsDate);



    }


    public boolean validateInputForm (EditText firstName, EditText lastName, EditText email, EditText phone, EditText birthDate, EditText password, EditText passwordRe) {

        try {
            if (firstName.getText().toString().length() < 2 || lastName.getText().toString().length() < 2)
                throw new Exception("Firstname and Lastname length cannot be less than 2");


            if ( !( firstName.getText().toString().matches("[a-zA-Z]+( +[a-zA-Z]+)*") &&
                    lastName.getText().toString().matches("[a-zA-Z]+( +[a-zA-Z]+)*") ))
                throw new Exception("Firstname and Lastname cannot contain Numbers or Special Chars");



            if (!password.getText().toString().equals(passwordRe.getText().toString()))
                throw new Exception("Passwords should be matched.");


            if (!email.getText().toString().matches(String.valueOf(EMAIL_REGEX)))
                throw new Exception("Incorrect E-mail Pattern.");

            return true;
        } catch (Exception E) {
            Toast.makeText(SignUpActivity.this, E.getMessage(), Toast.LENGTH_SHORT).show();
            return false;
        }
    }


    public static byte[] readAllBytes(InputStream inputStream) throws IOException {
        final int bufLen = 4 * 0x400; // 4KB
        byte[] buf = new byte[bufLen];
        int readLen;
        IOException exception = null;

        try {
            try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
                while ((readLen = inputStream.read(buf, 0, bufLen)) != -1)
                    outputStream.write(buf, 0, readLen);

                return outputStream.toByteArray();
            }
        } catch (IOException e) {
            exception = e;
            throw e;
        } finally {
            if (exception == null) inputStream.close();
            else try {
                inputStream.close();
            } catch (IOException e) {
                exception.addSuppressed(e);
            }
        }
    }


}