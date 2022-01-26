package com.example.e_mobile.recommended_adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.e_mobile.R;
import com.example.e_mobile.recommended_model.Recommended_Model;

import java.util.List;

public class RecommendedAdapter extends RecyclerView.Adapter<RecommendedAdapter.ViewHolder> {
    private final List<Recommended_Model> recommendedDataList;
    private final RecommendedDataInterface recommendedDataInterface;

    public RecommendedAdapter(List<Recommended_Model> recommendedDataList, RecommendedDataInterface recommendedDataInterface) {
        this.recommendedDataInterface = recommendedDataInterface;
        this.recommendedDataList = recommendedDataList;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Recommended_Model recommended_model=recommendedDataList.get(position);
        holder.name.setText(recommended_model.getName()+"");
        holder.price.setText(recommended_model.getPrice()+"");
        Glide.with(holder.imgProduct.getContext()).load(recommended_model.getImageUrl()).placeholder(R.drawable.ic_baseline_person).into(holder.imgProduct);
        holder.rootview.setOnClickListener(view -> {
            recommendedDataInterface.onUserClick(recommended_model,view, holder.getAdapterPosition());
        });
    }

    @Override
    public int getItemCount() {
        return recommendedDataList.size();
    }

    @NonNull
    @Override
    public RecommendedAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recommended_home, parent, false);
        return new RecommendedAdapter.ViewHolder(view);
    }
    public interface RecommendedDataInterface{
        void onUserClick(Recommended_Model recommended_model,View view,int position);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView name;
        private final TextView price;
        private final View rootview;
        private final ImageView imgProduct;

        public ViewHolder(View view){
            super(view);
            rootview=view;
            name=view.findViewById(R.id.name1);
            price=view.findViewById(R.id.price1);
            imgProduct=view.findViewById(R.id.img_product1);
        }
    }
}
