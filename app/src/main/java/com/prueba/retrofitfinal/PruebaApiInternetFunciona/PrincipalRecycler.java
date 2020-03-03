package com.prueba.retrofitfinal.PruebaApiInternetFunciona;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.prueba.retrofitfinal.PruebaApiInternetFunciona.Interfaces.JsonPlacesHolderApi;
import com.prueba.retrofitfinal.PruebaApiInternetFunciona.Model.Post;
import com.prueba.retrofitfinal.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PrincipalRecycler extends AppCompatActivity {

    AdapterApi adapterApi;
    RecyclerView recyclerView;
    List<Post> postList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal_recycler);

        postList=new ArrayList<>();



        recyclerView=findViewById(R.id.recyclerView2);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        llamarServicio();
    }




    private void llamarServicio(){

        //Primer paso
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        //Segundo paso
        JsonPlacesHolderApi jsonPlacesHolderApi = retrofit.create(JsonPlacesHolderApi.class);

        //Tercer paso
        final Call<List<Post>> post = jsonPlacesHolderApi.getPost();

        post.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {

                if (response.isSuccessful()){
                    postList = response.body();
                    adapterApi=new AdapterApi(postList);
                    recyclerView.setAdapter(adapterApi);
                    adapterApi.notifyDataSetChanged();
                    Log.e("Bien",response.message());

                }

            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {

            }
        });

    }
}
