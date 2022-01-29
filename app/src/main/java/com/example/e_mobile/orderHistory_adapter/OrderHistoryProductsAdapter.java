package com.example.e_mobile.orderHistory_adapter;

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
import com.example.e_mobile.orderhistoryRetro.ProductsOrdersList;

import java.util.List;
//extends RecyclerView.Adapter<OrderHistoryAdapter.ViewholderOrederHistory

public class OrderHistoryProductsAdapter extends RecyclerView.Adapter<OrderHistoryProductsAdapter.ViewHolderOrderHistory>{

    private final List<ProductsOrdersList> productsOrdersLists;
    private final OrderHistoryProductsDataInterface orderHistoryProductsDataInterface;

    public OrderHistoryProductsAdapter(List<ProductsOrdersList> productsOrdersLists, OrderHistoryProductsDataInterface orderHistoryProductsDataInterface) {
        this.productsOrdersLists = productsOrdersLists;
        this.orderHistoryProductsDataInterface = orderHistoryProductsDataInterface;
    }

    @NonNull
    @Override
    public OrderHistoryProductsAdapter.ViewHolderOrderHistory onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.order_history_products_recycler, parent, false);
        return new ViewHolderOrderHistory(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderHistoryProductsAdapter.ViewHolderOrderHistory holder, int position) {
        ProductsOrdersList productsOrdersList=productsOrdersLists.get(position);
        holder.productName.setText(productsOrdersList.getProductName()+"");
        holder.price.setText(productsOrdersList.getPrice()+"");
        holder.quantity.setText(String.valueOf(productsOrdersList.getQuantity()));
        Glide.with(holder.image.getContext()).load(productsOrdersList.getImage()).placeholder(R.drawable.ic_baseline_person).into(holder.image);

        holder.rootview.setOnClickListener(view -> {
            orderHistoryProductsDataInterface.onUserClick(productsOrdersList);
        });

    }

    @Override
    public int getItemCount() {
        return productsOrdersLists.size();
    }
    public interface OrderHistoryProductsDataInterface{

        void onUserClick(ProductsOrdersList productsOrdersList);
    }

    public static class ViewHolderOrderHistory extends RecyclerView.ViewHolder{
        private final ImageView image;
        private final TextView productName;
        private final TextView price;
        private final TextView quantity;

        private final View rootview;

        public ViewHolderOrderHistory(View view){
            super(view);
            rootview=view;
            image=view.findViewById(R.id.ProImageHistory);
            productName=view.findViewById(R.id.ProNameHistory);
            price=view.findViewById(R.id.proPriceHistory);
            quantity=view.findViewById(R.id.proQuantityHistory);

        }
    }
}
