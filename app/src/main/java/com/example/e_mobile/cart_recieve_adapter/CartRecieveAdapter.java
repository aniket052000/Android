package com.example.e_mobile.cart_recieve_adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.e_mobile.ProductFullView;
import com.example.e_mobile.R;
import com.example.e_mobile.RetrofitInterfaces.CartInterface;
import com.example.e_mobile.addtocartRetro.AddToCartEntity;
import com.example.e_mobile.builder.BuilderCart;
import com.example.e_mobile.cartRetro.CartProducts;
import com.example.e_mobile.cartRetro.CartQuantityChecker;
import com.example.e_mobile.cartRetro.CartRecieveEntity;
import com.example.e_mobile.cart_adapter.CartAdapter;
import com.example.e_mobile.cart_model.CartModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class CartRecieveAdapter extends RecyclerView.Adapter<CartRecieveAdapter.ViewHolder> {


    private final List<CartProducts> mcartProducts;
    private final CartDataInterface mcartDataInterface;
    String email;
    private Context context;
    String productId;
    double Total;
    Integer bol;
    Integer count;

    public CartRecieveAdapter(List<CartProducts> mcartProducts, CartDataInterface mcartDataInterface, Context context) {
        this.mcartProducts = mcartProducts;
        this.mcartDataInterface = mcartDataInterface;
        this.context = context;
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
        Total += cartProducts.getPrice()*cartProducts.getQuantity();
        holder.quantity.setText(String.valueOf(cartProducts.getQuantity()));
        Glide.with(holder.imgProduct.getContext()).load(cartProducts.getImage()).placeholder(R.drawable.ic_baseline_person).into(holder.imgProduct);
        holder.rootview.setOnClickListener(view -> {
            mcartDataInterface.onUserClick(cartProducts);
        });


        count = 0;
        holder.inc.setOnClickListener(v -> {


            SharedPreferences sharedPreferences = context.getSharedPreferences("com.example.e_mobile", Context.MODE_PRIVATE);
            email = sharedPreferences.getString("email", "Default");

            productId = cartProducts.getProductId();

           Integer ans = CartQuantityAPI(productId, cartProducts.getMerchantId(),cartProducts.getQuantity()+1);

           if(ans==1)
           {
               count++;
           }
           else
           {
               Toast.makeText(context, "Stock Not Available", Toast.LENGTH_SHORT).show();
           }
            holder.dis.setText("" + count);

        });

        holder.dec.setOnClickListener(v -> {

            count--;
            if(count > 0)
            {
                count--;
            }
            else
            {
                count =0;
            }

            holder.dis.setText("" + count);
        });

//        SharedPreferences sharedPreferences = context.getSharedPreferences("com.example.e_mobile", Context.MODE_PRIVATE);
//        email = sharedPreferences.getString("email", "Default");


//        SharedPreferences sharedPreferences= context.getSharedPreferences("com.example.e_mobile", Context.MODE_PRIVATE);
//        SharedPreferences.Editor editor=sharedPreferences.edit();
//        editor.putString("grTotal",Total+"");

//        SharedPreferences sharedPreferences=getSharedPreferences("com.example.e_mobile", Context.MODE_PRIVATE);
//
//        SharedPreferences.Editor editor=sharedPreferences.edit();
    }



    public interface CartDataInterface {
        void onUserClick(CartProducts cartProducts);
    }

    @Override
    public int getItemCount() {
        return mcartProducts.size();
    }

    private Integer CartQuantityAPI(String productId , String merchantId , int quantity){

        CartQuantityChecker cartQuantityChecker = new CartQuantityChecker(productId, merchantId,quantity);
        Retrofit retrofit = BuilderCart.getInstance();
        CartInterface cartInterface = retrofit.create(CartInterface.class);

        Call<Integer> cartInterfaceCall = cartInterface.postLogGetQty(cartQuantityChecker);
        cartInterfaceCall.enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {

                bol = response.body().intValue();
            }

            @Override
            public void onFailure(Call<Integer> call, Throwable t) {
                Toast.makeText(context, "Counter Not Work", Toast.LENGTH_SHORT).show();
            }
        });


        Log.d("AAAAAASDCSDFD","DSSDSDSDSD");
        return bol;


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
