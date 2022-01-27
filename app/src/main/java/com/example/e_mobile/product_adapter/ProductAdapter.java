package com.example.e_mobile.product_adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.e_mobile.R;
import com.example.e_mobile.productRetro.ProductEntity;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.Viewholder> {

    private final List<ProductEntity> productEntityList;
    private final ProductDataInterface productDataInterface;

    public ProductAdapter(List<ProductEntity> productEntityList, ProductDataInterface productDataInterface) {
        this.productEntityList = productEntityList;
        this.productDataInterface=productDataInterface;
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
        ProductEntity productEntity = productEntityList.get(position);

//        holder.productID.setText(productModel.getProductID()+"");
//        holder.grandTotal.setText(productModel.getGrandTotal()+"");

        holder.productName.setText(productEntity.getProductName());
        holder.attribute1.setText(productEntity.getAttribute1());
        holder.attribute2.setText(productEntity.getAttribute2());
        holder.attribute3.setText(productEntity.getAttribute5());
        holder.attribute4.setText(productEntity.getAttribute4());

        Glide.with(holder.itemView)
                .load(productEntity.getImage())
                .fitCenter()
                .into(holder.productImage);

//        holder.rootview.setOnClickListener(view -> {
//            OrderHistoryDataInterface.onUserClick(orderHistoryModel);
//        });
        holder.rootview.setOnClickListener(view -> {
            productDataInterface.onUserClick(productEntity);
        });

    }

    @Override
    public int getItemCount() {
        return productEntityList.size();
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.product_recycler, parent, false);
        return new Viewholder(view);
    }

    public interface ProductDataInterface{
        void onUserClick(ProductEntity productEntity);
    }
    public static class Viewholder extends RecyclerView.ViewHolder{

        private final TextView productName;
        private final TextView attribute1;
        private final TextView attribute2;
        private final TextView attribute3;
        private final TextView attribute4;

        private final ImageView productImage;


        //        private final TextView grandTotal;
//          private final TextView productID
//        private final TextView text1;
        private final View rootview;

        public Viewholder(View view){
            super(view);
            rootview=view;
//            productID=view.findViewById(R.id.OrdID);
//            grandTotal=view.findViewById(R.id.Tamt);

            productName = view.findViewById(R.id.product_name);
            attribute1 = view.findViewById(R.id.Attribute1);
            attribute2 = view.findViewById(R.id.Attribute2);
            attribute3 = view.findViewById(R.id.Attribute3);
            attribute4 = view.findViewById(R.id.Attribute4);
            productImage = itemView.findViewById(R.id.product_image);

//            text1=view.findViewById(R.id.textID);


        }
    }
}
