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
import android.widget.Button;
import android.widget.TextView;

import com.example.e_mobile.cart_adapter.CartAdapter;
import com.example.e_mobile.cart_model.CartModel;
import com.example.e_mobile.recommended_adapter.RecommendedAdapter;
import com.example.e_mobile.recommended_model.Recommended_Model;

import java.util.ArrayList;
import java.util.List;

public class Cart extends Fragment implements CartAdapter.CartDataInterface{

//    int count=0;

    public Cart() {

    }




    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_cart, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        List<CartModel> cartModels=new ArrayList<>();
        generateUserData(cartModels);
        RecyclerView recyclerView=view.findViewById(R.id.cartrecycler);
        CartAdapter cartAdapter=new CartAdapter(cartModels,  Cart.this);
        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
        recyclerView.setAdapter(cartAdapter);



//        Button inc=view.findViewById(R.id.increase);
//        Button dec=view.findViewById(R.id.decrease);
//        TextView dis=view.findViewById(R.id.Cquantity);

//        inc.setOnClickListener(v -> {
//            count++;
//            dis.setText(""+count);
//        });
//
//        dec.setOnClickListener(v -> {
//            count--;
//            if(count<0)
//                count=0;
//            dis.setText(""+count);
//        });


    }

    private void generateUserData(List<CartModel> cartModelList) {

        cartModelList.add(new CartModel("Employee 1", 100,1,"https://fortmyersradon.com/wp-content/uploads/2019/12/dummy-user-img-1.png"));
        cartModelList.add(new CartModel("Employee 2", 101,2,"https://d2cax41o7ahm5l.cloudfront.net/mi/speaker-photo/appliedmicrobiology-minl-2018-Rucha-Ridhorkar-62007-7135.png"));
        cartModelList.add(new CartModel("Employee 3", 102,3,"https://img.icons8.com/bubbles/2x/user-male.png"));
        cartModelList.add(new CartModel("Employee 4", 103,4,"https://cdn4.iconfinder.com/data/icons/small-n-flat/24/user-alt-512.png"));
        cartModelList.add(new CartModel("Employee 5", 104,5,"https://toppng.com/uploads/preview/user-font-awesome-nuevo-usuario-icono-11563566658mjtfvilgcs.png"));
        cartModelList.add(new CartModel("Employee 6", 105,6,"https://image.flaticon.com/icons/png/512/149/149071.png"));
        cartModelList.add(new CartModel("Employee 7", 106,7,"https://www.winhelponline.com/blog/wp-content/uploads/2017/12/user.png"));
        cartModelList.add(new CartModel("Employee 8", 107,8,"https://avatarfiles.alphacoders.com/791/79102.png"));
        cartModelList.add(new CartModel("Employee 9", 108,9,"https://i.pinimg.com/originals/7c/c7/a6/7cc7a630624d20f7797cb4c8e93c09c1.png"));
        cartModelList.add(new CartModel("Employee 10", 109,10,"https://cdn1.iconfinder.com/data/icons/avatar-2-2/512/Casual_Man_2-512.png"));
        cartModelList.add(new CartModel("Employee 11", 110,11,"https://img.icons8.com/plasticine/2x/user.png"));
        cartModelList.add(new CartModel("Employee 12", 111,12,"https://img.pngio.com/user-account-google-account-start-up-computer-icons-others-user-account-png-800_848.png"));
        cartModelList.add(new CartModel("Employee 13", 112,13,"https://spng.subpng.com/20180331/pke/kisspng-computer-icons-user-profile-female-avatar-user-5abff415c1e818.9933493415225293017943.jpg"));
        cartModelList.add(new CartModel("Employee 14", 113,14,"https://top-madagascar.com/assets/images/admin/user-admin.png"));
    }

    @Override
    public void onUserClick(CartModel cartModel) {
    }
}
