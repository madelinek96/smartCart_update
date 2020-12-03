package com.example.foodquiz;

import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.io.IOException;

public class SpoonacularAPI extends AppCompatActivity {
    OkHttpClient client = new OkHttpClient();

    Request request = new Request.Builder()
            .url("https://spoonacular-recipe-food-nutrition-v1.p.rapidapi.com/recipes/extract?url=http%3A%2F%2Fwww.melskitchencafe.com%2Fthe-best-fudgy-brownies%2F")
            .get()
            .addHeader("x-rapidapi-key", "0ac7da84f0msh1cdf13a65a90ae1p1095bbjsnd4b7e301979e")
            .addHeader("x-rapidapi-host", "spoonacular-recipe-food-nutrition-v1.p.rapidapi.com")
            .build();



    Response response = client.newCall(request).execute();


    public SpoonacularAPI() throws IOException {
    }
}
