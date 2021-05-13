package tr.edu.yildiz.payci.soner;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import tr.edu.yildiz.payci.soner.model.Person;
import tr.edu.yildiz.payci.soner.model.UserBase;

public class MainActivity extends AppCompatActivity
{

    EditText username;
    EditText password;
    TextView textMessage;
    Button btnSignIn;
    Button btnSignUp;
    UserBase userBase;
    Integer attempt;

    private void cleanTextBoxes()
    {
        username.setText("");
        password.setText("");
    }

    private boolean checkPerson()
    {
        for (Person aPerson : userBase.GetUserList())
        {
            if ( username.getText().toString().equals(aPerson.getUsername()) &&
                 UserBase.convertStringToSHA256(password.getText().toString()).equals(aPerson.getPassword()))
            {
                return true;
            }
        }
        return false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        defineVariables();
        defineListeners();
    }


    public void defineVariables() {
        attempt = 0;
        userBase = new UserBase();
        username = (EditText) findViewById(R.id.usernameTxt);
        password = (EditText) findViewById(R.id.passwordTxt);
        btnSignIn = (Button) findViewById(R.id.btnSignIn);
        btnSignUp = (Button) findViewById(R.id.btnSignUp);
        textMessage = (TextView) findViewById(R.id.LoginMsgTxt);
    }


    public void defineListeners() {
        btnSignIn.setOnClickListener((v -> {
            //Intent intent = new Intent(MainActivity.this, SignUpActivity.class);
            //this.startActivity(intent);
            if (checkPerson()) {
                attempt = 0;
                textMessage.setText("Credentials that passed are true!");
            } else {
                attempt += 1;
                textMessage.setText(String.format( "Wrong username/password. You have {%d}" , 3-attempt));
                if (attempt >= 3)  {
                    btnSignIn.setEnabled(false);
                    Toast.makeText(MainActivity.this, "Wrong Credentials passed three times in a row. Signin feature Disabled.", Toast.LENGTH_SHORT).show();
                }
            }

        }));

        btnSignUp.setOnClickListener((v -> {
            Intent intent = new Intent(MainActivity.this, SignUpActivity.class);

            ArrayList<UserBase> userBases = new ArrayList<UserBase>();
            userBases.add(userBase);
            intent.putExtra("userBases", userBases);
            this.startActivity(intent);
        }));

    }
}