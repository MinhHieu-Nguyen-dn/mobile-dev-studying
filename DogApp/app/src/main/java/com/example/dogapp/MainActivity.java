
package com.example.dogapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.dogapp.model.DogBreed;
import com.example.dogapp.viewmodel.DogsApi;
import com.example.dogapp.viewmodel.DogsApiService;

import java.util.ArrayList;
import java.util.List;

import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory;
import io.reactivex.rxjava3.core.Single;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private DogsApiService apiService;
    private RecyclerView rvDogs;
    public List<DogBreed> dogList;
    public dogAdapter dogAdapter;
    public ArrayList<String> name = new ArrayList<>();
    private SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rvDogs = findViewById(R.id.rv_dogs);

        rvDogs.setLayoutManager(new GridLayoutManager(this, 2));
        dogList = new ArrayList<>();

        dogAdapter =new dogAdapter(dogList, getApplicationContext());

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://raw.githubusercontent.com")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build();

        DogsApi dogsApi = retrofit.create(DogsApi.class);
        Call<List<DogBreed>> call = dogsApi.getDogs();
        call.enqueue(new Callback<List<DogBreed>>() {
            Boolean like = false;
            @Override
            public void onResponse(Call<List<DogBreed>> call, Response<List<DogBreed>> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(MainActivity.this, response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }
                List<DogBreed> dogList = response.body();
                dogAdapter = new dogAdapter(dogList, MainActivity.this);
                rvDogs.setAdapter(dogAdapter);
            }

            @Override
            public void onFailure(Call<List<DogBreed>> call, Throwable t) {
                Log.d("ERROR", "onError: "+t.getMessage());
            }
        });

    }
    @Override
    public boolean onCreateOptionsMenu (Menu menu){
        getMenuInflater().inflate(R.menu.search_menu, menu);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setMaxWidth(Integer.MAX_VALUE);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                dogAdapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                dogAdapter.getFilter().filter(newText);
                return false;
            }
        });
        return true;
    }
}