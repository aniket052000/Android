package com.example.e_mobile;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;

//import com.example.e_mobile.productRetro.ProductFullView;
import com.example.e_mobile.RetrofitInterfaces.SliderInterface;
import com.example.e_mobile.builder.BuilderProduct;
import com.example.e_mobile.builder.BuilderSignup;
import com.example.e_mobile.productRetro.ProductEntity;
import com.example.e_mobile.recommended_adapter.RecommendedAdapter;
import com.example.e_mobile.recommended_model.Recommended_Model;
import com.example.e_mobile.signupRetro.Respentity;
import com.example.e_mobile.signupRetro.SignUp;
import com.example.e_mobile.slider_adapter.SliderAdapter;
import com.example.e_mobile.slider_model.SliderModel;
import com.smarteist.autoimageslider.SliderView;
import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public class Home extends Fragment implements RecommendedAdapter.RecommendedDataInterface {
    String url1 = "https://images.theconversation.com/files/2982/original/3600947113_fe7208d8a8_b.jpg?ixlib=rb-1.1.0&q=45&auto=format&w=926&fit=clip"; // Daru
    String url2 = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRQV4PnR4TldWDpedxe0GOT7bz1bU0VI0CbtA&usqp=CAU";   // Game
    String url3 = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTWaqxg8sKlxVsGdJ3UVvhoHThp43G8kJlpkg&usqp=CAU";  // Cigarattes
    String url4 = "https://i.ytimg.com/vi/rNzg-_lZZuw/maxresdefault.jpg";    // Food Packet
    String url5 = "https://image.shutterstock.com/image-vector/various-meds-pills-capsules-blisters-260nw-1409823341.jpg";   // Dava
    public String p;
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
        sliderDataArrayList.add(new SliderModel(url4));
        sliderDataArrayList.add(new SliderModel(url5));


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

        adapter.setOnItemClickListener(new SliderAdapter.OnItemClickListener() {

            @Override public void onItemClick(int position)
            {

//                String[] cname = {"drinks", "game", "cigarette", "food", "healthcare"};
                p = String.valueOf(position);

//                if (p.equals("0")) {
//                    //SliderApi("Drinks");
////                    Toast.makeText(SliderAdapter.this, "Please enter all details", Toast.LENGTH_SHORT).show();
//                    return;
//                }
//                if (p.equals("1")) {
//                    //SliderApi("Healthcare");
////                    Toast.makeText(SliderAdapter.this, "Please enter all details", Toast.LENGTH_SHORT).show();
//                    return;
//                }
//                if (p.equals("2")) {
//                    //SliderApi("Game");
////                    Toast.makeText(SliderAdapter.this, "Please enter all details", Toast.LENGTH_SHORT).show();
//                    return;
//                }
//
//                //List<ProductModel> productModelList = new ArrayList<>();
//
//                String s = cname[position];
//                List<ProductModel> productModelList = generateProductData(s);


//                if(p.equals("1"))
//                {
////                    String categoryName="";
//                    //ProductDto productDto=getProductDetailsByCategory(categoryName);
//
//
//                    // Builder logic for game product retrieval
//                    //ProductDto fun(int pos)
//
//                }
//                  ......
//                else if(p.equals("0"))
//                {
////                    Intent intent = new Intent(getContext(), DrinksShow.class);
////                    startActivity(intent);
//                }
//
////                Intent intent = new Intent(getContext(), GamesShow.class);
////                startActivity(intent);

                Toast.makeText(getContext(), p+"...", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getContext(), Product.class);
                startActivity(intent);

            }
        });


        List<Recommended_Model> recommended_models=new ArrayList<>();
        generateUserData(recommended_models);
        RecyclerView recyclerView=view.findViewById(R.id.recycler1);
        RecommendedAdapter recommendedAdapter=new RecommendedAdapter(recommended_models,Home.this);
        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
        recyclerView.setAdapter(recommendedAdapter);
    }

    public void SliderApi(String catName) {


        Log.d("ABBBBBBBBBBBBBBBBBBB", "SliderApi Call..........");
        Retrofit retrofit = BuilderProduct.getInstance();
//        SignupEntity signupEntity = new SignupEntity(name, email, password, address);
        SliderInterface sliderInterface = retrofit.create(SliderInterface.class);
        Call<List<ProductEntity>> productListCall = sliderInterface.postLog(catName);
        productListCall.enqueue(new Callback<List<ProductEntity>>() {
            @Override
            public void onResponse(Call<List<ProductEntity>> call, Response<List<ProductEntity>> response) {
//                if(response.body()==null){
//                    Toast.makeText(Dummy.this, "User mail is already registered", Toast.LENGTH_SHORT).show();
//                }
//                Toast.makeText(SignUp.this, "Signin Successful", Toast.LENGTH_SHORT).show();
//                Toast.makeText(SignUp.this, response.body().getStatus(), Toast.LENGTH_SHORT).show();
//                startActivity(new Intent(getApplicationContext(), Dummy.class));
                Toast.makeText(getContext(), "Everything is correct" , Toast.LENGTH_SHORT).show();

                Intent i=new Intent(getContext(), Product.class);
                startActivity(i);
            }


            @Override
            public void onFailure(Call<List<ProductEntity>> call, Throwable t) {
                Toast.makeText(getContext(), "Everything is wrong", Toast.LENGTH_SHORT).show();
//                Toast.makeText(SignUp.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
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
    public void onUserClick(Recommended_Model recommended_model,View view, int position) {
        startActivity(new Intent(getContext(), ProductFullView.class));

    }
}