package tr.edu.yildiz.payci.soner.helpers;

import android.view.View;

import tr.edu.yildiz.payci.soner.model.Question;

public interface OnItemClickListener {
    void onQuestionItemClick(View v, Question item);
    boolean onQuestionItemLongClick(View v, Question item);
}
