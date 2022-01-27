package com.example.e_mobile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;


import com.example.e_mobile.product_adapter.ProductAdapter;
import com.example.e_mobile.productRetro.ProductEntity;

import java.util.ArrayList;
import java.util.List;

public class Product extends AppCompatActivity implements ProductAdapter.ProductDataInterface {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        List<ProductEntity> productEntities =new ArrayList<>();
        generateUserData(productEntities);


//        List<ProductModel> productModelList = new ArrayList<>();
//        generateProductData(productModelList, "all");

        //Log.d("ABBBBBBBBBBBBBBBBBBBBBB", "Hello ....... "+ p);

        RecyclerView recyclerView=findViewById(R.id.productrecycler);
        ProductAdapter productAdapter =new ProductAdapter(productEntities,  Product.this);
        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
        recyclerView.setAdapter(productAdapter);


    }


//    private void generateProductData(List<ProductModel> productModelList , String catname)
//    {
//
//        Retrofit retrofit = RetrofitBuilder.getInstance();
//
//        ProductAPI productAPI = retrofit.create(ProductAPI.class);
//        Call<List<ProductModel>> users = productAPI.getProductByCategory();
//
//        Log.d("ABCCCCCCCCCCDFSSFDFFF", "Before Enque");
//
//
//        users.enqueue(new Callback<List<ProductModel>>() {
//            @Override
//            public void onResponse(Call<List<ProductModel>> call, Response<List<ProductModel>> response) {
//                Log.d("ABC", "Able to fetch data");
//                List<ProductModel> productModelList = response.body();
//            }
//
//            @Override
//            public void onFailure(Call<List<ProductModel>> call, Throwable t) {
//                Toast.makeText(Product.this, "Not abble", Toast.LENGTH_SHORT).show();
//                Log.d("ABCDDDDDDDDDDDDDDDDDDDD", t.getMessage());
//            }
//        });
//    }


    private void generateUserData(List<ProductEntity> productEntityList) {

        productEntityList.add(new ProductEntity("1", 100, "Call of Duty Ghosts", "Game", 0L, "Infinity Ward", "Xbox One", "Single, Multi, Online Multi", "Action", "Activician", null, "Create-A-Soldier system for avatar customization", "CoD: Ghosts tells the story of Logan and Hesh, two jarheads who find themselves part of the last stand of the USA against invaders from the South American Federation after the SAF nukes the US from orbit. ... Once Logan and Hesh – and their dog Riley – join the Ghosts, things begin to move into high gear.", "https://rukminim1.flixcart.com/image/832/832/kkbh8cw0/physical-game/r/e/m/infinity-ward-call-of-duty-ghosts-graphic-game-xbox-one-original-imafzzqmw5afvjej.jpeg?q=70" ));//        productModelList.add(new ProductModel("Employee 2", 101));
        productEntityList.add(new ProductEntity("2", 100, "James Bond 007 (Blood Stone)", "Game", 0L, "Blood stone", "Xbox 360", "Single, Multi, Online Multi", "Action", "Activician", null, "Create-A-Soldier system for avatar customization", "CoD: Ghosts tells the story of Logan and Hesh, two jarheads who find themselves part of the last stand of the USA against invaders from the South American Federation after the SAF nukes the US from orbit. ... Once Logan and Hesh – and their dog Riley – join the Ghosts, things begin to move into high gear.", "https://rukminim1.flixcart.com/image/832/832/kkbh8cw0/physical-game/r/5/9/blood-stone-james-bond-007-graphic-game-xbox-360-original-imafzzqer5rhzheh.jpeg?q=70" ));
        productEntityList.add(new ProductEntity("3", 100, "Black Rose Valkyrie Sp. Japanese Version (PS4)", "Game", 0L, "Ultimate Evil", "Xbox 360", "Single, Multi, Online Multi", "RPG", "Blizzard", null, "Create-A-Soldier system for avatar customization", "CoD: Ghosts tells the story of Logan and Hesh, two jarheads who find themselves part of the last stand of the USA against invaders from the South American Federation after the SAF nukes the US from orbit. ... Once Logan and Hesh – and their dog Riley – join the Ghosts, things begin to move into high gear.", "https://rukminim1.flixcart.com/image/832/832/kirr24w0-0/physical-game/r/m/z/ultimate-evil-edition-black-rose-valkyrie-special-edition-original-imafyhd7zncnqyg8.jpeg?q=70" ));
        productEntityList.add(new ProductEntity("4", 100, "Ashes Cricket (for PS4)", "Game", 0L, "Standard", "PS4", "Single, Multi, Online Multi", "Sports", "Koach", null, "Create-A-Soldier system for avatar customization", "CoD: Ghosts tells the story of Logan and Hesh, two jarheads who find themselves part of the last stand of the USA against invaders from the South American Federation after the SAF nukes the US from orbit. ... Once Logan and Hesh – and their dog Riley – join the Ghosts, things begin to move into high gear.", "https://rukminim1.flixcart.com/image/832/832/j8uiljk0/physical-game/t/f/z/standard-edition-ashes-cricket-full-game-ps4-original-imaeysfjybhwxda9.jpeg?q=70" ));
        productEntityList.add(new ProductEntity("5", 100, "Gran Turismo 7 (Standard) (for PS4)", "Game", 0L, "Standard", "PS4", "Single, Multi, Online Multi", "Racing", "Blizzard", null, "Create-A-Soldier system for avatar customization", "CoD: Ghosts tells the story of Logan and Hesh, two jarheads who find themselves part of the last stand of the USA against invaders from the South American Federation after the SAF nukes the US from orbit. ... Once Logan and Hesh – and their dog Riley – join the Ghosts, things begin to move into high gear.", "https://rukminim1.flixcart.com/image/832/832/kwb07m80/physical-game/h/r/j/yes-standard-edition-ps4-gran-turismo-7-standard-ed-full-game-original-imag9yd5gkfzkpus.jpeg?q=70" ));
        productEntityList.add(new ProductEntity("6", 100, "Diablo III : Reaper of Souls (Ultimate Evil Edition)", "Game", 0L, " Game & Exp Pack", "PS4", "Single, Multi, Online Multi", "RPG", "Sony", null, "Create-A-Soldier system for avatar customization", "CoD: Ghosts tells the story of Logan and Hesh, two jarheads who find themselves part of the last stand of the USA against invaders from the South American Federation after the SAF nukes the US from orbit. ... Once Logan and Hesh – and their dog Riley – join the Ghosts, things begin to move into high gear.", "https://rukminim1.flixcart.com/image/832/832/physical-game/3/v/z/ps4-ultimate-evil-edition-game-and-expansion-pack-diablo-iii-original-imae37ekvqv77gbz.jpeg?q=70" ));
        productEntityList.add(new ProductEntity("7", 100, "Pillars Eternity", "Game", 0L, "Complete", "PS4", "Single, Multi, Online Multi", "RPG", "505 Games", null, "Create-A-Soldier system for avatar customization", "CoD: Ghosts tells the story of Logan and Hesh, two jarheads who find themselves part of the last stand of the USA against invaders from the South American Federation after the SAF nukes the US from orbit. ... Once Logan and Hesh – and their dog Riley – join the Ghosts, things begin to move into high gear.", "https://rukminim1.flixcart.com/image/832/832/j5mrxjk0/physical-game/h/v/e/complete-edition-pillars-of-eternity-game-and-map-pack-ps4-original-imaewa3evygh8zvj.jpeg?q=70" ));
        productEntityList.add(new ProductEntity("8", 100, "The Witcher 3 : Wild Hunt(PS4)", "Game", 0L, "Standard", "PS4", "Single, Multi, Online Multi", "Action", "505 Games", null, "Create-A-Soldier system for avatar customization", "CoD: Ghosts tells the story of Logan and Hesh, two jarheads who find themselves part of the last stand of the USA against invaders from the South American Federation after the SAF nukes the US from orbit. ... Once Logan and Hesh – and their dog Riley – join the Ghosts, things begin to move into high gear.", "https://rukminim1.flixcart.com/image/832/832/physical-game/b/g/c/ps4-the-witcher-3-wild-hunt-original-imaejtzhsbnjg2jh.jpeg?q=70" ));
        productEntityList.add(new ProductEntity("9", 100, "Marvel's Avengers", "Game", 0L, "Standard", "PS4", "Single, Multi, Online Multi", "Action Adv", "Sony", null, "Create-A-Soldier system for avatar customization", "CoD: Ghosts tells the story of Logan and Hesh, two jarheads who find themselves part of the last stand of the USA against invaders from the South American Federation after the SAF nukes the US from orbit. ... Once Logan and Hesh – and their dog Riley – join the Ghosts, things begin to move into high gear.", "https://rukminim1.flixcart.com/image/832/832/k6zda4w0/physical-game/x/z/z/standard-marvel-s-avengers-full-game-ps4-original-imafpbedz4dqty47.jpeg?q=70" ));

//        productModelList.add(new ProductModel("Employee 3", 102));
//        productModelList.add(new ProductModel("Employee 4", 103));
//        productModelList.add(new ProductModel("Employee 5", 104));
//        productModelList.add(new ProductModel("Employee 6", 105));
//        productModelList.add(new ProductModel("Employee 7", 106));
//        productModelList.add(new ProductModel("Employee 8", 107));
//        productModelList.add(new ProductModel("Employee 9", 108));
//        productModelList.add(new ProductModel("Employee 10", 109));
//        productModelList.add(new ProductModel("Employee 11", 110));
//        productModelList.add(new ProductModel("Employee 12", 111));
//        productModelList.add(new ProductModel("Employee 13", 112));
//        productModelList.add(new ProductModel("Employee 14", 113));
    }

    @Override
    public void onUserClick(ProductEntity productEntity) {

        Toast.makeText(this, "Image Clicked for" + productEntity.getProductID(), Toast.LENGTH_SHORT).show();

        // Ahiya p ni value set karvani

        Intent intent = new Intent(this, ProductFullView.class);
        startActivity(intent);
    }
}