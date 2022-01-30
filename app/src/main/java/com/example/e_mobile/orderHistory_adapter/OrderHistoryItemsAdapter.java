package com.example.e_mobile.orderHistory_adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.e_mobile.R;

import com.example.e_mobile.orderhistory.OrderListItem;
import com.example.e_mobile.orderhistory.ProductsListItem;


import java.util.List;

public class OrderHistoryItemsAdapter extends RecyclerView.Adapter<OrderHistoryItemsAdapter.Viewholder> {

    private final List<OrderListItem> orderListItems;
    private final OrderListItemInterface orderListItemInterface;

    public OrderHistoryItemsAdapter(List<OrderListItem> orderListItems, OrderListItemInterface orderListItemInterface) {
        this.orderListItems = orderListItems;
        this.orderListItemInterface = orderListItemInterface;
    }

    @Override
    public void onBindViewHolder(@NonNull OrderHistoryItemsAdapter.Viewholder holder, int position) {


        OrderListItem orderListItem = orderListItems.get(position);

        holder.productID.setText(orderListItem.getOrderId());
        holder.grandTotal.setText(String.valueOf(orderListItem.getGrandTotal()));

//        holder.rootview.setOnClickListener(view -> {
//            OrderHistoryDataInterface.onUserClick(orderHistoryModel);
//        });
        holder.rootview.setOnClickListener(view -> {
            orderListItems.
        });



    }

    @Override
    public int getItemCount() {
        return orderListItems.size();
    }

    public interface OrderListItemInterface{
        void onUserClick(List<OrderListItem> orderListItem);
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.orderhistory_recycler, parent, false);
        return new OrderHistoryItemsAdapter.Viewholder(view);
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
