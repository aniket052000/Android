package com.example.e_mobile.cart_adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.e_mobile.R;
import com.example.e_mobile.cart_model.CartModel;
import com.example.e_mobile.recommended_adapter.RecommendedAdapter;
import com.example.e_mobile.recommended_model.Recommended_Model;

import java.text.BreakIterator;
import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {
    int count=0;
    private final List<CartModel> cartModelList;
    private final CartDataInterface cartDataInterface;


    public CartAdapter(List<CartModel> cartModelList,CartDataInterface cartDataInterface){
        this.cartDataInterface=cartDataInterface;
        this.cartModelList=cartModelList;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CartModel cartModel=cartModelList.get(position);
        holder.name.setText(cartModel.getName()+"");
        holder.price.setText(cartModel.getPrice()+"");
        holder.quantity.setText(cartModel.getQuantity()+"");
        Glide.with(holder.imgProduct.getContext()).load(cartModel.getImageUrl()).placeholder(R.drawable.ic_baseline_person).into(holder.imgProduct);
        holder.rootview.setOnClickListener(view -> {
            cartDataInterface.onUserClick(cartModel);
        });



        holder.inc.setOnClickListener(v -> {
            count++;
            holder.dis.setText(""+count);
        });

        holder.dec.setOnClickListener(v -> {
            count--;
            if(count<0)
                count=0;
            holder.dis.setText(""+count);
        });
    }
    @NonNull
    @Override
    public CartAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_recycler, parent, false);
        return new CartAdapter.ViewHolder(view);
    }

    public interface CartDataInterface{
        void onUserClick(CartModel cartModel);
    }

    @Override
    public int getItemCount() {
        return cartModelList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView name;
        private final TextView price;
        private final TextView quantity;
        private final View rootview;
        private final ImageView imgProduct;
        private final Button inc;
        private final Button dec;
        private final TextView dis;

        public ViewHolder(View view){
            super(view);
            rootview=view;
            name=view.findViewById(R.id.Cname);
            price=view.findViewById(R.id.Cprice);
            imgProduct=view.findViewById(R.id.Cimg_product);
            quantity=view.findViewById(R.id.Cquantity);
            inc=view.findViewById(R.id.increase);
            dec=view.findViewById(R.id.decrease);
            dis=view.findViewById(R.id.Cquantity);
        }
    }
}
