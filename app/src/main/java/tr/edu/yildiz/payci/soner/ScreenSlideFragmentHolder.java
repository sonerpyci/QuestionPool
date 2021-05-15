package tr.edu.yildiz.payci.soner;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;

import tr.edu.yildiz.payci.soner.DAL.DbHelper;
import tr.edu.yildiz.payci.soner.helpers.OnItemClickListener;
import tr.edu.yildiz.payci.soner.helpers.RecyclerQuestionsAdapter;
import tr.edu.yildiz.payci.soner.model.Question;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ScreenSlideFragmentHolder#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ScreenSlideFragmentHolder extends Fragment {


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String FRAGMENT_TYPE = "fragmentType";

    // TODO: Rename and change types of parameters
    private int fragmentType;
    DbHelper dbHelper;
    RecyclerView rv;
    RecyclerQuestionsAdapter adapter;
    ArrayList<Question> questions;


    public ScreenSlideFragmentHolder() {
        // Required empty public constructor
        this.dbHelper = new DbHelper(getContext());
    }

    public void setFragmentType(int fragmentType) {
        this.fragmentType = fragmentType;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param fragmentType Parameter 1.
     * @return A new instance of fragment ScreenSlideFragmentHolder.
     */
    // TODO: Rename and change types and number of parameters
    public static ScreenSlideFragmentHolder newInstance(int fragmentType) {
        ScreenSlideFragmentHolder fragment = new ScreenSlideFragmentHolder();
        fragment.setFragmentType(fragmentType);
        Bundle args = new Bundle();
        args.putInt(FRAGMENT_TYPE, fragmentType);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            fragmentType = getArguments().getInt(FRAGMENT_TYPE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        int resId = R.layout.activity_exam;
        switch (fragmentType) {
            case 0:
                resId = R.layout.activity_exam;
                break;
            case 1:
                resId = R.layout.activity_question;
                break;
            case 2:
                resId = R.layout.activity_question;
                break;
        }


        View view = (ViewGroup)inflater.inflate(resId, container, false);
        ((ViewPager) container).addView(view, 0);
        return view;
        /*return (ViewGroup) inflater.inflate(
                R.layout.fragment_screen_slide_page, container, false);*/
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (fragmentType > 0){
            rv = (RecyclerView) view.findViewById(R.id.question_recycler_view);

            questions = new ArrayList<Question>();
            questions.add(new Question("Android"));
            questions.add(new Question("Java"));
            questions.add(new Question("Python"));
            questions.add(new Question("MySQL"));
            questions.add(new Question("PHP"));

            rv.setItemAnimator(new DefaultItemAnimator());
            rv.setNestedScrollingEnabled(false);
            rv.setHasFixedSize(true);

            adapter = new RecyclerQuestionsAdapter(questions, getContext(), new OnItemClickListener() {

                // TODO : EDIT QUESTION
                @Override
                public void onQuestionItemClick(Question item) {
                    Toast.makeText(getContext(), String.format( "Soru : %s ?", item.getText()), Toast.LENGTH_SHORT).show();
                }

            });

            rv.setAdapter(adapter);
        }


    }
}