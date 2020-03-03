package com.prueba.retrofitfinal.VolleyRetrofit;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.prueba.retrofitfinal.PruebaApiInternetFunciona.AdapterApi;
import com.prueba.retrofitfinal.PruebaApiInternetFunciona.Model.Post;
import com.prueba.retrofitfinal.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class VolleyRetroPrincipal extends AppCompatActivity {

    AdapterApi adapterApi;
    RecyclerView recyclerView;
    List<Post> postList;

    RequestQueue requestQueue;
    JsonObjectRequest jsonObjectRequest;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal_recycler);

        recyclerView=findViewById(R.id.recyclerView2);

        requestQueue = Volley.newRequestQueue(this);


        postList=new ArrayList<>();



        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        llamarServicio();
    }




    private void llamarServicio(){

        String ruta="https://jsonplaceholder.typicode.com/posts";

        jsonObjectRequest= new JsonObjectRequest(Request.Method.GET, ruta, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                Post postLi=null;

                JSONArray jsonArray=response.optJSONArray("");


                try {

                    for (int i=0;i<jsonArray.length();i++){
                        postLi=new Post();
                        JSONObject jsonObject=null;

                        jsonObject=jsonArray.getJSONObject(i);

                        postLi.setUserId(jsonObject.optInt("userId"));
                        postLi.setId(jsonObject.optInt("id"));
                        postLi.setTitle(jsonObject.optString("title"));
                        postLi.setBody(jsonObject.optString("body"));

                        postList.add(postLi);



                    }

                    adapterApi=new AdapterApi(postList);
                    recyclerView.setAdapter(adapterApi);


                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(VolleyRetroPrincipal.this, "No hubo conexion papu", Toast.LENGTH_SHORT).show();

                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(VolleyRetroPrincipal.this, "No funciono" + error.toString(), Toast.LENGTH_SHORT).show();
                Log.e( "no",error.toString());

            }
        });

        requestQueue.add(jsonObjectRequest);

    }
}