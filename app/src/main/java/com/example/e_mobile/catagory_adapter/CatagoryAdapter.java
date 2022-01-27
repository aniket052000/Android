package com.example.e_mobile.catagory_adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.e_mobile.R;
import com.example.e_mobile.catagory_model.CatagoryModel;

import java.util.List;

public class CatagoryAdapter extends RecyclerView.Adapter<CatagoryAdapter.Viewholder> {


    private final List<CatagoryModel> catagoryModelList;
    private final CatagoryDataInterface catagoryDataInterface;


    public CatagoryAdapter(List<CatagoryModel> catagoryModelList,CatagoryDataInterface catagoryDataInterface){
        this.catagoryDataInterface=catagoryDataInterface;
        this.catagoryModelList=catagoryModelList;
    }
    public interface CatagoryDataInterface{
        void onUserClick(CatagoryModel catagoryModel);
    }
    @Override
    public void onBindViewHolder(@NonNull CatagoryAdapter.Viewholder holder, int position) {
        CatagoryModel catagoryModel=catagoryModelList.get(position);
        holder.name.setText(catagoryModel.getName()+"");
        holder.price.setText(catagoryModel.getPrice()+"");
        Glide.with(holder.imgProduct.getContext()).load(catagoryModel.getImgUrl()).placeholder(R.drawable.ic_baseline_person).into(holder.imgProduct);
        holder.rootview.setOnClickListener(view -> {
            catagoryDataInterface.onUserClick(catagoryModel);
        });
    }
    @NonNull
    @Override
    public CatagoryAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.catagory_recycler, parent, false);
        return new CatagoryAdapter.Viewholder(view);
    }

    @Override
    public int getItemCount() {
        return catagoryModelList.size();
    }

    public static class Viewholder extends RecyclerView.ViewHolder{

        private final TextView name;
        private final TextView price;
        private final ImageView imgProduct;
        private final View rootview;

        public Viewholder(View view){
            super(view);
            rootview=view;
            name=view.findViewById(R.id.CatName);
            price=view.findViewById(R.id.CatPrice);
            imgProduct=view.findViewById(R.id.CatImage);
        }
    }
}
