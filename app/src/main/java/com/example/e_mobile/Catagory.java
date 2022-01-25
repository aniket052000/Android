package com.example.e_mobile;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.e_mobile.cart_adapter.CartAdapter;
import com.example.e_mobile.cart_model.CartModel;
import com.example.e_mobile.catagory_adapter.CatagoryAdapter;
import com.example.e_mobile.catagory_model.CatagoryModel;

import java.util.ArrayList;
import java.util.List;

public class Catagory extends Fragment implements CatagoryAdapter.CatagoryDataInterface {


    public Catagory() {

    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_catagory, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        List<CatagoryModel> catagoryModels = new ArrayList<>();
        generateUserData(catagoryModels);
        RecyclerView recyclerView = view.findViewById(R.id.cartrecycler);
        CatagoryAdapter catagoryAdapter = new CatagoryAdapter(catagoryModels, Catagory.this);
        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
        recyclerView.setAdapter(catagoryAdapter);
    }

    private void generateUserData(List<CatagoryModel> catagoryModelList) {

        catagoryModelList.add(new CatagoryModel("Employee 1", "https://fortmyersradon.com/wp-content/uploads/2019/12/dummy-user-img-1.png", 100));
        catagoryModelList.add(new CatagoryModel("Employee 2", "https://d2cax41o7ahm5l.cloudfront.net/mi/speaker-photo/appliedmicrobiology-minl-2018-Rucha-Ridhorkar-62007-7135.png", 101));
        catagoryModelList.add(new CatagoryModel("Employee 3", "https://img.icons8.com/bubbles/2x/user-male.png", 102));
        catagoryModelList.add(new CatagoryModel("Employee 4", "https://cdn4.iconfinder.com/data/icons/small-n-flat/24/user-alt-512.png", 103));
        catagoryModelList.add(new CatagoryModel("Employee 5", "https://toppng.com/uploads/preview/user-font-awesome-nuevo-usuario-icono-11563566658mjtfvilgcs.png", 104));
        catagoryModelList.add(new CatagoryModel("Employee 6", "https://image.flaticon.com/icons/png/512/149/149071.png", 105));
        catagoryModelList.add(new CatagoryModel("Employee 7", "https://www.winhelponline.com/blog/wp-content/uploads/2017/12/user.png", 106));
        catagoryModelList.add(new CatagoryModel("Employee 8", "https://avatarfiles.alphacoders.com/791/79102.png", 107));
        catagoryModelList.add(new CatagoryModel("Employee 9", "https://i.pinimg.com/originals/7c/c7/a6/7cc7a630624d20f7797cb4c8e93c09c1.png", 108));
        catagoryModelList.add(new CatagoryModel("Employee 10", "https://cdn1.iconfinder.com/data/icons/avatar-2-2/512/Casual_Man_2-512.png", 109));
        catagoryModelList.add(new CatagoryModel("Employee 11", "https://img.icons8.com/plasticine/2x/user.png", 110));
        catagoryModelList.add(new CatagoryModel("Employee 12", "https://img.pngio.com/user-account-google-account-start-up-computer-icons-others-user-account-png-800_848.png", 111));
        catagoryModelList.add(new CatagoryModel("Employee 13", "https://spng.subpng.com/20180331/pke/kisspng-computer-icons-user-profile-female-avatar-user-5abff415c1e818.9933493415225293017943.jpg", 112));
        catagoryModelList.add(new CatagoryModel("Employee 14", "https://top-madagascar.com/assets/images/admin/user-admin.png", 113));

    }

    @Override
    public void onUserClick(CatagoryModel catagoryModel) {

    }
}