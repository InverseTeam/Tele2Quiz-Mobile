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

public class RecyclerViewArticlesAdapter extends RecyclerView.Adapter<RecyclerViewArticlesAdapter.ViewHolder> implements RecyclerViewInterface{

    private List<ResponseModelClass> list;
    private Context context;
    private final RecyclerViewInterface recyclerViewInterface;

    public RecyclerViewArticlesAdapter(List<ResponseModelClass> list, Context context, RecyclerViewInterface recyclerViewInterface) {
        this.list = list;
        this.context = context;
        this.recyclerViewInterface = recyclerViewInterface;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_articles, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String desc = list.get(position).getContent();
        String s = (desc.substring(0, Math.min(100, desc.length() - 1))) + "...";
        holder.title.setText(list.get(position).getTitle());
        holder.content.setText(s);
        holder.category.setText(list.get(position).getCategory().getName());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public void onItemClick(int position) {

    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView title, content, category;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.textArticlesItemTitle);
            content = itemView.findViewById(R.id.textArticlesItemContent);
            category = itemView.findViewById(R.id.textArticlesItemCategory);
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
