package com.example.e_mobile.orderHistory_adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.e_mobile.R;
import com.example.e_mobile.orderHistory_model.OrderHistoryModel;
import com.example.e_mobile.orderhistoryRetro.OrderItemsEntity;

import java.util.List;

public class OrderHistoryItemsAdapter extends RecyclerView.Adapter<OrderHistoryItemsAdapter.Viewholder> {

    private final List<OrderItemsEntity> orderItemsEntities;
    private final OrderHistoryItemsInterface orderHistoryItemsInterface;

    public OrderHistoryItemsAdapter(List<OrderItemsEntity> orderItemsEntities , OrderHistoryItemsInterface orderHistoryItemsInterface) {
        this.orderItemsEntities = orderItemsEntities;
        this.orderHistoryItemsInterface=orderHistoryItemsInterface;
    }

    @Override
    public void onBindViewHolder(@NonNull OrderHistoryItemsAdapter.Viewholder holder, int position) {


        OrderItemsEntity orderHistoryItems=orderItemsEntities.get(position);

        holder.productID.setText(orderHistoryItems.getProductsList().get(position).getProductId());
        holder.grandTotal.setText(String.valueOf(orderHistoryItems.getProductsList().get(position).getTotal()));

//        holder.rootview.setOnClickListener(view -> {
//            OrderHistoryDataInterface.onUserClick(orderHistoryModel);
//        });
        holder.rootview.setOnClickListener(view -> {
            orderHistoryItemsInterface.onUserClick(orderHistoryItems);
        });



    }

    @Override
    public int getItemCount() {
        return orderItemsEntities.size();
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.orderhistory_recycler, parent, false);
        return new OrderHistoryItemsAdapter.Viewholder(view);
    }

    public interface OrderHistoryItemsInterface{
        void onUserClick(OrderItemsEntity orderItemsEntity);
    }
    public static class Viewholder extends RecyclerView.ViewHolder{
        private final TextView productID;
        private final TextView grandTotal;
//        private final TextView text1;
        private final View rootview;

        public Viewholder(View view){
            super(view);
            rootview=view;
            productID=view.findViewById(R.id.OrdID);
            grandTotal=view.findViewById(R.id.Tamt);
//            text1=view.findViewById(R.id.textID);


        }
    }
}
