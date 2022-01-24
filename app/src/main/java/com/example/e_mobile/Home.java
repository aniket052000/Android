package com.example.e_mobile;

import android.os.Bundle;
import androidx.annotation.NonNull;

import com.example.e_mobile.recommended_adapter.RecommendedAdapter;
import com.example.e_mobile.recommended_model.Recommended_Model;
import com.example.e_mobile.slider_adapter.SliderAdapter;
import com.example.e_mobile.slider_model.SliderModel;
import com.smarteist.autoimageslider.SliderView;
import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class Home extends Fragment implements RecommendedAdapter.RecommendedDataInterface {
    String url1 = "https://www.geeksforgeeks.org/wp-content/uploads/gfg_200X200-1.png";
    String url2 = "https://qphs.fs.quoracdn.net/main-qimg-8e203d34a6a56345f86f1a92570557ba.webp";
    String url3 = "https://bizzbucket.co/wp-content/uploads/2020/08/Life-in-The-Metro-Blog-Title-22.png";

    public Home() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ArrayList<SliderModel> sliderDataArrayList = new ArrayList<>();

        // initializing the slider view.
        SliderView sliderView = view.findViewById(R.id.slider);

        // adding the urls inside array list
        sliderDataArrayList.add(new SliderModel(url1));
        sliderDataArrayList.add(new SliderModel(url2));
        sliderDataArrayList.add(new SliderModel(url3));

        // passing this array list inside our adapter class.
        SliderAdapter adapter = new SliderAdapter(view.getContext(), sliderDataArrayList);

        // below method is used to set auto cycle direction in left to
        // right direction you can change according to requirement.
        sliderView.setAutoCycleDirection(SliderView.LAYOUT_DIRECTION_LTR);

        // below method is used to
        // setadapter to sliderview.
        sliderView.setSliderAdapter(adapter);

        // below method is use to set
        // scroll time in seconds.
        sliderView.setScrollTimeInSec(3);

        // to set it scrollable automatically
        // we use below method.
        sliderView.setAutoCycle(true);

        // to start autocycle below method is used.
        sliderView.startAutoCycle();


        List<Recommended_Model> recommended_models=new ArrayList<>();
        generateUserData(recommended_models);
        RecyclerView recyclerView=view.findViewById(R.id.recycler1);
        RecommendedAdapter recommendedAdapter=new RecommendedAdapter(recommended_models,Home.this);
        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
        recyclerView.setAdapter(recommendedAdapter);
    }
    private void generateUserData(List<Recommended_Model> recommendDataList) {
        recommendDataList.add(new Recommended_Model("Employee 1", 100,"https://fortmyersradon.com/wp-content/uploads/2019/12/dummy-user-img-1.png"));
        recommendDataList.add(new Recommended_Model("Employee 2", 101,"https://d2cax41o7ahm5l.cloudfront.net/mi/speaker-photo/appliedmicrobiology-minl-2018-Rucha-Ridhorkar-62007-7135.png"));
        recommendDataList.add(new Recommended_Model("Employee 3", 102,"https://img.icons8.com/bubbles/2x/user-male.png"));
        recommendDataList.add(new Recommended_Model("Employee 4", 103,"https://cdn4.iconfinder.com/data/icons/small-n-flat/24/user-alt-512.png"));
        recommendDataList.add(new Recommended_Model("Employee 5", 104,"https://toppng.com/uploads/preview/user-font-awesome-nuevo-usuario-icono-11563566658mjtfvilgcs.png"));
        recommendDataList.add(new Recommended_Model("Employee 6", 105,"https://image.flaticon.com/icons/png/512/149/149071.png"));
        recommendDataList.add(new Recommended_Model("Employee 7", 106,"https://www.winhelponline.com/blog/wp-content/uploads/2017/12/user.png"));
        recommendDataList.add(new Recommended_Model("Employee 8", 107,"https://avatarfiles.alphacoders.com/791/79102.png"));
        recommendDataList.add(new Recommended_Model("Employee 9", 108,"https://i.pinimg.com/originals/7c/c7/a6/7cc7a630624d20f7797cb4c8e93c09c1.png"));
        recommendDataList.add(new Recommended_Model("Employee 10", 109,"https://cdn1.iconfinder.com/data/icons/avatar-2-2/512/Casual_Man_2-512.png"));
        recommendDataList.add(new Recommended_Model("Employee 11", 110,"https://img.icons8.com/plasticine/2x/user.png"));
        recommendDataList.add(new Recommended_Model("Employee 12", 111,"https://img.pngio.com/user-account-google-account-start-up-computer-icons-others-user-account-png-800_848.png"));
        recommendDataList.add(new Recommended_Model("Employee 13", 112,"https://spng.subpng.com/20180331/pke/kisspng-computer-icons-user-profile-female-avatar-user-5abff415c1e818.9933493415225293017943.jpg"));
        recommendDataList.add(new Recommended_Model("Employee 14", 113,"https://top-madagascar.com/assets/images/admin/user-admin.png"));}

    @Override
    public void onUserClick(Recommended_Model recommended_model) {

    }
}