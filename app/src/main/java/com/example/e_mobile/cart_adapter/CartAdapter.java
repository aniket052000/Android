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

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {

    private final List<CartModel> cartModelList;
    private final CartDataInterface cartDataInterface;

    public CartAdapter(List<CartModel> cartModelList,
                       CartDataInterface cartDataInterface) {
        this.cartModelList = cartModelList;
        this.cartDataInterface = cartDataInterface;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        CartModel cartModel = cartModelList.get(position);

        holder.name.setText(cartModel.getName());
        holder.quantity.setText(String.valueOf(cartModel.getQuantity()));

        double totalItemPrice = cartModel.getPrice() * cartModel.getQuantity();
        holder.price.setText("₹" + totalItemPrice);

        Glide.with(holder.imgProduct.getContext())
                .load(cartModel.getImageUrl())
                .placeholder(R.drawable.ic_baseline_person)
                .into(holder.imgProduct);

        holder.rootview.setOnClickListener(view ->
                cartDataInterface.onUserClick(cartModel, view, holder.getAdapterPosition())
        );

        // 🔥 Increase Quantity
        holder.inc.setOnClickListener(v -> {
            int newQty = cartModel.getQuantity() + 1;
            cartModel.setQuantity(newQty);
            notifyItemChanged(holder.getAdapterPosition());
            cartDataInterface.onCartUpdated(getTotalCartValue());
        });

        // 🔥 Decrease Quantity
        holder.dec.setOnClickListener(v -> {
            int newQty = cartModel.getQuantity() - 1;

            if (newQty <= 0) {
                int pos = holder.getAdapterPosition();
                cartModelList.remove(pos);
                notifyItemRemoved(pos);
            } else {
                cartModel.setQuantity(newQty);
                notifyItemChanged(holder.getAdapterPosition());
            }

            cartDataInterface.onCartUpdated(getTotalCartValue());
        });
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cart_recycler, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return cartModelList.size();
    }

    // 🔥 NEW: Calculate total cart value
    public double getTotalCartValue() {
        double total = 0;
        for (CartModel item : cartModelList) {
            total += item.getPrice() * item.getQuantity();
        }
        return total;
    }

    public interface CartDataInterface {
        void onUserClick(CartModel cartModel, View view, int position);

        // 🔥 NEW callback for total update
        void onCartUpdated(double totalAmount);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView name;
        private final TextView price;
        private final TextView quantity;
        private final View rootview;
        private final ImageView imgProduct;
        private final Button inc;
        private final Button dec;

        public ViewHolder(View view) {
            super(view);

            rootview = view;
            name = view.findViewById(R.id.Cname);
            price = view.findViewById(R.id.Cprice);
            imgProduct = view.findViewById(R.id.Cimg_product);
            quantity = view.findViewById(R.id.Cquantity);
            inc = view.findViewById(R.id.increase);
            dec = view.findViewById(R.id.decrease);
        }
    }
}
