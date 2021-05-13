package tr.edu.yildiz.payci.soner;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import tr.edu.yildiz.payci.soner.model.Person;
import tr.edu.yildiz.payci.soner.model.UserBase;
import tr.edu.yildiz.payci.soner.DAL.DbHelper;

public class MainActivity extends AppCompatActivity
{
    DbHelper dbHelper;
    EditText username;
    EditText password;
    TextView textMessage;
    Button btnSignIn;
    Button btnSignUp;
    Integer attempt;

    private void cleanTextBoxes()
    {
        username.setText("");
        password.setText("");
    }

    private boolean checkPerson()
    {
        return dbHelper.checkUsernameAndPassword(username.getText().toString(), UserBase.convertStringToSHA256(password.getText().toString()));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        dbHelper = new DbHelper(this);

        defineVariables();
        defineListeners();
    }

    public void defineVariables() {
        attempt = 0;
        username = (EditText) findViewById(R.id.usernameTxt);
        password = (EditText) findViewById(R.id.passwordTxt);
        btnSignIn = (Button) findViewById(R.id.btnSignIn);
        btnSignUp = (Button) findViewById(R.id.btnSignUp);
    }


    public void defineListeners() {
        btnSignIn.setOnClickListener((v -> {
            //Intent intent = new Intent(MainActivity.this, SignUpActivity.class);
            //this.startActivity(intent);
            if (checkPerson()) {
                attempt = 0;
                Toast.makeText(MainActivity.this, String.format( "Logged In Successfully!" , 3-attempt), Toast.LENGTH_SHORT).show();
            } else {
                attempt += 1;
                //textMessage.setText(String.format( "Wrong username/password. You have {%d}" , 3-attempt));
                Toast.makeText(MainActivity.this, String.format( "Wrong username/password. You have {%d} Attempts." , 3-attempt), Toast.LENGTH_SHORT).show();
                if (attempt >= 3)  {
                    btnSignIn.setEnabled(false);
                    Toast.makeText(MainActivity.this, "Wrong Credentials passed three times in a row. Signin feature Disabled.", Toast.LENGTH_SHORT).show();
                }
            }
        }));

        btnSignUp.setOnClickListener((v -> {
            Intent intent = new Intent(MainActivity.this, SignUpActivity.class);

            //userBases.add(userBase);
            //intent.putExtra("userBases", userBases);
            this.startActivity(intent);
        }));

    }
}