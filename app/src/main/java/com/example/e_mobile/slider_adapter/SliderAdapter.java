package com.example.e_mobile.slider_adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.e_mobile.R;
import com.example.e_mobile.slider_model.SliderModel;
import com.smarteist.autoimageslider.SliderViewAdapter;

import java.util.List;

public class SliderAdapter extends SliderViewAdapter<SliderAdapter.SliderAdapterViewHolder> {

    private final List<SliderModel> mSliderItems;

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
    }

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
