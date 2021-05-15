package tr.edu.yildiz.payci.soner;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.BaseTransientBottomBar;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
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
    ImageButton btnAddQuestion;
    RecyclerQuestionsAdapter adapter;
    ArrayList<Question> questions;
    LayoutInflater inflater;

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
        this.inflater = inflater;
        int resId = R.layout.activity_question;
        switch (fragmentType) {
            case 0:
                resId = R.layout.activity_question;
                break;
            case 1:
                resId = R.layout.activity_exam;
                break;
            case 2:
                resId = R.layout.activity_exam;
                break;
        }


        View view = (ViewGroup)inflater.inflate(resId, container, false);
        //((ViewPager) container).addView(view, 0);
        return view;
        /*return (ViewGroup) inflater.inflate(
                R.layout.fragment_screen_slide_page, container, false);*/
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (fragmentType == 0){
            btnAddQuestion = (ImageButton) view.findViewById(R.id.add_new_question_btn);
            btnAddQuestion.setOnClickListener((v -> {
                createQuestionPopup(view);
             }));


            rv = (RecyclerView) view.findViewById(R.id.question_recycler_view);

            questions = new ArrayList<Question>();
            questions.add(new Question("Gandalf Miğfer Dibi savaşından önce kaçıncı günden bahsetmektedir?"));
            questions.add(new Question("Yukarıdaki soruda bahsi geçen an geldiğinde, hangi yöne bakılmalıdır?"));
            questions.add(new Question("İlk soruda bahsi geçen gün içerisinde, ilgili yöne ne zaman bakılmalıdır?"));
            questions.add(new Question("Babam böyle pasta yapmayı nerden öğrendi?"));
            questions.add(new Question("Ödev nedir? Nasıl yapılmaz?"));

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

    public void createQuestionPopup(View view) {
        View popupView = inflater.inflate(R.layout.popup_add_edit_question, null);

        // create the popup window
        int width = LinearLayout.LayoutParams.WRAP_CONTENT;
        int height = LinearLayout.LayoutParams.WRAP_CONTENT;
        boolean focusable = true; // lets taps outside the popup also dismiss it
        final PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);
        popupWindow.setOutsideTouchable(true);
        // show the popup window
        // which view you pass in doesn't matter, it is only used for the window tolken
        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);
        view.setAlpha((float) 0.1);

        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                view.setAlpha((float) 1);
            }
        });
        ImageButton btnPoupAddQuestion = popupView.findViewById(R.id.add_question_btn);

        btnPoupAddQuestion.setOnClickListener((v2 -> {

            // TODO : Create Question and Insert it to Db.



        }));

        // dismiss the popup window when touched
        popupView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                view.setAlpha((float) 1);
                popupWindow.dismiss();

                return true;
            }
        });

    }



    public void addNewQuestionOnClick() {

    }


}