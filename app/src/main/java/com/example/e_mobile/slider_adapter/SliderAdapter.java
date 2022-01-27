package com.example.e_mobile.slider_adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.e_mobile.Dummy;
import com.example.e_mobile.Product;
import com.example.e_mobile.R;
import com.example.e_mobile.RetrofitInterfaces.SignupInterface;
import com.example.e_mobile.RetrofitInterfaces.SliderInterface;
import com.example.e_mobile.builder.BuilderSignup;
import com.example.e_mobile.productRetro.ProductEntity;
import com.example.e_mobile.signupRetro.Respentity;
import com.example.e_mobile.signupRetro.SignUp;
import com.example.e_mobile.signupRetro.SignupEntity;
import com.example.e_mobile.slider_model.SliderModel;
import com.smarteist.autoimageslider.SliderViewAdapter;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class SliderAdapter extends SliderViewAdapter<SliderAdapter.SliderAdapterViewHolder> {

    private final List<SliderModel> mSliderItems;
    SliderAdapter.OnItemClickListener mListener;

    public SliderAdapter(Context context, List<SliderModel> mSliderItems) {
        this.mSliderItems = mSliderItems;
    }


    @Override
    public SliderAdapterViewHolder onCreateViewHolder(ViewGroup parent) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.slider_layout, null);
        return new SliderAdapterViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(SliderAdapterViewHolder viewHolder,final int position) {
        final SliderModel sliderItem = mSliderItems.get(position);

        // Glide is use to load image
        // from url in your imageview.
        Glide.with(viewHolder.itemView)
                .load(sliderItem.getImgUrl())
                .fitCenter()
                .into(viewHolder.imageViewBackground);
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (mListener != null) {
                    if (position != RecyclerView.NO_POSITION) {
                        mListener.onItemClick(position);
                    }
                }

            }
        });


    }

    public interface OnItemClickListener {
        void onItemClick(int position);}

    // lets create the set onclick method
    public void setOnItemClickListener(SliderAdapter.OnItemClickListener listener) {
        mListener = listener; }



    @Override
    public int getCount() {
       return mSliderItems.size();
    }
    static class SliderAdapterViewHolder extends SliderViewAdapter.ViewHolder {
        // Adapter class for initializing
        // the views of our slider view.
        private final View itemView;
        private final ImageView imageViewBackground;

        public SliderAdapterViewHolder(View itemView) {
            super(itemView);
            imageViewBackground = itemView.findViewById(R.id.myimage);
            this.itemView = itemView;
        }
    }
}
