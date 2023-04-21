package com.example.sqlite_demo_04.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sqlite_demo_04.R;
import com.example.sqlite_demo_04.model.Item;

import java.util.ArrayList;
import java.util.List;

public class RecycleViewAdapter extends RecyclerView.Adapter<RecycleViewAdapter.HomeViewHolder>{
    private List<Item> list;
    private ItemListener itemListener;
    public void setItemListener(ItemListener itemListener) {
        this.itemListener = itemListener;
    }

    public RecycleViewAdapter() {
        list=new ArrayList<>();
    }
    public RecycleViewAdapter(List<Item> list) {
        this.list = list;
    }

    public void setList(List<Item> list) {
        this.list = list;
        notifyDataSetChanged();
    }
    public Item getItem(int position){
        return list.get(position);
    }
    @NonNull
    @Override
    public HomeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item,parent,false);
        return new HomeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeViewHolder holder, int position) {
        Item item= list.get(position);
        holder.name.setText(item.getName());
        holder.author.setText(item.getAuthor());
        holder.range.setText(item.getRange());
        holder.oop.setText(item.getOop());
        holder.rating.setRating(item.getRating());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class HomeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView name,author,range,oop;
        private RatingBar rating;
        public HomeViewHolder(@NonNull View view) {
            super(view);
            name=view.findViewById(R.id.tvName);
            author=view.findViewById(R.id.tvAuthor);
            range=view.findViewById(R.id.tvRange);
            oop=view.findViewById(R.id.tvOop);
            rating= view.findViewById(R.id.tvRating);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if(itemListener != null){
                itemListener.onItemClick(view,getAdapterPosition());
            }
        }
    }
    public interface ItemListener{
        void onItemClick(View view,int position);
    }
}
