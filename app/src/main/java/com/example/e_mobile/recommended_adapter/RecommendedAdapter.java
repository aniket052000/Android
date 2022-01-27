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

import com.example.e_mobile.recommendRetro.RecommendEntity;

import java.util.List;

public class RecommendedAdapter extends RecyclerView.Adapter<RecommendedAdapter.ViewHolder> {
    private final List<RecommendEntity> recommendedDataList;
    private final RecommendedDataInterface recommendedDataInterface;

    public RecommendedAdapter(List<RecommendEntity> recommendedDataList, RecommendedDataInterface recommendedDataInterface) {
        this.recommendedDataInterface = recommendedDataInterface;
        this.recommendedDataList = recommendedDataList;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        RecommendEntity recommendEntity=recommendedDataList.get(position);
        holder.name.setText(recommendEntity.getProductName()+"");
        holder.price.setText(recommendEntity.getMerchant().getPrice()+"");
        Glide.with(holder.imgProduct.getContext()).load(recommendEntity.getImage()).placeholder(R.drawable.ic_baseline_person).into(holder.imgProduct);
        holder.rootview.setOnClickListener(view -> {
            recommendedDataInterface.onUserClick(recommendEntity,view, holder.getAdapterPosition());
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
        void onUserClick(RecommendEntity recommendEntity,View view,int position);
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
