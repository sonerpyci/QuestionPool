package tr.edu.yildiz.payci.soner.helpers;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import tr.edu.yildiz.payci.soner.R;
import tr.edu.yildiz.payci.soner.model.Question;

public class RecyclerQuestionsAdapter extends RecyclerView.Adapter<RecyclerQuestionsAdapter.MyViewHolder> implements OnItemClickListener {
    private List<Question> mListenerList;
    private final OnItemClickListener listener;

    Context mContext;
    LayoutInflater inflater;

    public RecyclerQuestionsAdapter(List<Question> mListenerList, Context mContext, OnItemClickListener listener) {
        this.mListenerList = mListenerList;
        this.mContext = mContext;
        this.listener = listener;
        inflater = LayoutInflater.from(mContext);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_view_item, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        holder.question_text.setText(mListenerList.get(position).getText());
        holder.question_number.setText(String.valueOf(position));
        // get a get b get c get question fields.

        holder.bind(mListenerList.get(position), listener);
    }

    @Override
    public int getItemCount() {
        return mListenerList.size();
    }

    @Override
    public void onQuestionItemClick(Question item) {

    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView question_text;
        TextView question_number;
        ImageView im_image;

        public MyViewHolder(View view) {
            super(view);
            question_text = (TextView) view.findViewById(R.id.question_text);
            question_number = (TextView) view.findViewById(R.id.question_number);
        }

        public void bind(final Question item, final OnItemClickListener listener) {

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    listener.onQuestionItemClick(item);
                }
            });
        }
    }
}