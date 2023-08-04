package com.example.tele2quizz.models;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tele2quizz.R;
import com.example.tele2quizz.interfaces.RecyclerViewInterface;

import java.util.List;

public class RecyclerViewTestsAdapter extends RecyclerView.Adapter<RecyclerViewTestsAdapter.ViewHolder> implements RecyclerViewInterface{

    private List<ResponseModelClass> list;
    private Context context;
    private final RecyclerViewInterface recyclerViewInterface;

    public RecyclerViewTestsAdapter(List<ResponseModelClass> list, Context context, RecyclerViewInterface recyclerViewInterface) {
        this.list = list;
        this.context = context;
        this.recyclerViewInterface = recyclerViewInterface;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_events, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.title.setText(list.get(position).getName());
        holder.description.setText(list.get(position).getDescription());
        holder.textCategoryEvents.setText(list.get(position).getCategory().getName());
        holder.textAttemptEvents.setText("1 попытка");
        holder.textCountEvents.setText(list.get(position).getQuestionPoints() + " баллов");
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public void onItemClick(int position) {

    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView title, description, textCategoryEvents, textAttemptEvents, textCountEvents;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.textTitleEvents);
            description = itemView.findViewById(R.id.textDescriptionEvents);
            textCategoryEvents = itemView.findViewById(R.id.textCategoryEvents);
            textAttemptEvents = itemView.findViewById(R.id.textAttemptEvents);
            textCountEvents = itemView.findViewById(R.id.textCountEvents);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (recyclerViewInterface != null){
                        int pos = getAdapterPosition();
                        if (pos != RecyclerView.NO_POSITION){
                            recyclerViewInterface.onItemClick(pos);
                        }
                    }
                }
            });
        }
    }

}
