package com.example.e_mobile.orderHistory_adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.e_mobile.R;
import com.example.e_mobile.orderHistory_model.OrderHistoryModel;

import java.util.List;

public class OrderHistoryAdapter extends RecyclerView.Adapter<OrderHistoryAdapter.Viewholder> {

    private final List<OrderHistoryModel> orderHistoryModelList;
    private final OrderHistoryDataInterface orderHistoryDataInterface;

    public OrderHistoryAdapter(List<OrderHistoryModel> orderHistoryModelList , OrderHistoryDataInterface orderHistoryDataInterface) {
        this.orderHistoryModelList = orderHistoryModelList;
        this.orderHistoryDataInterface=orderHistoryDataInterface;
    }

    @Override
    public void onBindViewHolder(@NonNull OrderHistoryAdapter.Viewholder holder, int position) {
        OrderHistoryModel orderHistoryModel=orderHistoryModelList.get(position);
        holder.productID.setText(orderHistoryModel.getProductID()+"");
        holder.grandTotal.setText(orderHistoryModel.getGrandTotal()+"");

//        holder.rootview.setOnClickListener(view -> {
//            OrderHistoryDataInterface.onUserClick(orderHistoryModel);
//        });
        holder.rootview.setOnClickListener(view -> {
            orderHistoryDataInterface.onUserClick(orderHistoryModel);
        });

    }

    @Override
    public int getItemCount() {
        return orderHistoryModelList.size();
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.orderhistory_recycler, parent, false);
        return new OrderHistoryAdapter.Viewholder(view);
    }

    public interface OrderHistoryDataInterface{
        void onUserClick(OrderHistoryModel orderHistoryModel);
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
