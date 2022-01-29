package com.example.e_mobile.cart_recieve_adapter;

import android.content.Context;
import android.content.SharedPreferences;
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
import com.example.e_mobile.cartRetro.CartProducts;
import com.example.e_mobile.cartRetro.CartRecieveEntity;
import com.example.e_mobile.cart_adapter.CartAdapter;
import com.example.e_mobile.cart_model.CartModel;

import java.util.List;

public class CartRecieveAdapter extends RecyclerView.Adapter<CartRecieveAdapter.ViewHolder> {

    int count = 0;
    private final List<CartProducts> mcartProducts;
    private final CartDataInterface mcartDataInterface;
    String email;
    private Context context;
    String productId;

    public CartRecieveAdapter(List<CartProducts> mcartProducts, CartDataInterface mcartDataInterface) {
        this.mcartProducts = mcartProducts;
        this.mcartDataInterface = mcartDataInterface;
        //this.context = context;
    }

//    public CartRecieveAdapter(List<CartProducts> mcartProducts, CartDataInterface mcartRecieveEntities) {
//        this.mcartProducts = mcartProducts;
//        this.mcartDataInterface = mcartRecieveEntities;
//    }

    @NonNull
    @Override
    public CartRecieveAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_recycler, parent, false);
        return new CartRecieveAdapter.ViewHolder(view);

    }



    @Override
    public void onBindViewHolder(@NonNull CartRecieveAdapter.ViewHolder holder, int position) {


        CartProducts cartProducts = mcartProducts.get(position);
        holder.name.setText(cartProducts.getProductName());
        holder.price.setText(String.valueOf(cartProducts.getPrice()));
        holder.quantity.setText(String.valueOf(cartProducts.getQuantity()));
        Glide.with(holder.imgProduct.getContext()).load(cartProducts.getImage()).placeholder(R.drawable.ic_baseline_person).into(holder.imgProduct);
        holder.rootview.setOnClickListener(view -> {

            mcartDataInterface.onUserClick(cartProducts);
        });


        holder.inc.setOnClickListener(v -> {


            //CartQuantityAPI(email, productId, cartProducts.getQuantity(), cartProducts.getMerchantId());


            count++;
            holder.dis.setText("" + count);
        });

        holder.dec.setOnClickListener(v -> {
            count--;
            if (count < 0)
                count = 0;
            holder.dis.setText("" + count);
        });
    }



    public interface CartDataInterface {
        void onUserClick(CartProducts cartProducts);
    }

    @Override
    public int getItemCount() {
        return mcartProducts.size();
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

        public ViewHolder(View view) {
            super(view);
            rootview = view;
            name = view.findViewById(R.id.Cname);
            price = view.findViewById(R.id.Cprice);
            imgProduct = view.findViewById(R.id.Cimg_product);
            quantity = view.findViewById(R.id.Cquantity);
            inc = view.findViewById(R.id.increase);
            dec = view.findViewById(R.id.decrease);
            dis = view.findViewById(R.id.Cquantity);
        }
    }
}
