package tr.edu.yildiz.payci.soner;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

import tr.edu.yildiz.payci.soner.helpers.RecyclerQuestionsAdapter;
import tr.edu.yildiz.payci.soner.model.Question;

public class QuestionActivity extends AppCompatActivity {
    RecyclerView rv;
    RecyclerQuestionsAdapter adapter;
    ArrayList<Question> questions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
    }
}