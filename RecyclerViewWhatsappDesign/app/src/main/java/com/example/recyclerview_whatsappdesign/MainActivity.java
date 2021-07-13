package com.example.recyclerview_whatsappdesign;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    List<ModelClass> userList;
    Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initData();
        initRecyclerView();

    }

    private void initRecyclerView() {
        recyclerView = findViewById(R.id.recyclerView);
        linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    private void initData() {
        userList=new ArrayList<>();
        userList.add(new ModelClass(R.drawable.naruto_x_sasuke,"Mausam","08:59AM","Hello, Where are you?","------------------------------------"));
        userList.add(new ModelClass(R.drawable.obito_wallpaper,"Rohit","08:30PM","Coding for money","------------------------------------"));
        userList.add(new ModelClass(R.drawable.rock_lee,"Abhimanyu","10:32AM","Is it exam today?","------------------------------------"));
        userList.add(new ModelClass(R.drawable.kuramas_previous,"Ashutosh","11:00PM","Earth need a better leader","------------------------------------"));
        userList.add(new ModelClass(R.drawable.naruto_x_sasuke,"Touka","04:32AM","Always think you are the best","------------------------------------"));
        userList.add(new ModelClass(R.drawable.obito_wallpaper,"Ken","04:30PM","Why so serious?","------------------------------------"));
        userList.add(new ModelClass(R.drawable.rock_lee,"Light","05:45PM","The wolrd is rotten","------------------------------------"));
        userList.add(new ModelClass(R.drawable.kuramas_previous,"Intezar","03:23Am","Let's go to Dubai !","------------------------------------"));
    }
}




