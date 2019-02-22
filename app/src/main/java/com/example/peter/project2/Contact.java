package com.example.peter.project2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


import com.example.peter.project2.RetrofitAPI.APIClient;
import com.example.peter.project2.RetrofitAPI.UserAPI;
import com.example.peter.project2.Service.SaveLocal;


import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Contact extends AppCompatActivity {
    Button btn_back_contact;
    RecyclerView recyclerView;
    MemberAdapter adapter;
    ArrayList<MemberData> memberData;
    UserAPI userAPI;
    String userName;
    LinearLayoutManager linearLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        btn_back_contact = findViewById(R.id.btn_back_contact);
        recyclerView = findViewById(R.id.Rcv_contact);
        // get userName from local
        userName = SaveLocal.getUserNameFromLocal(this);
        //get listContacts
        getListContacts();

        btn_back_contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


    }

    private void getListContacts() {
        userAPI = APIClient.getClient().create(UserAPI.class);
        Call<List<MemberData>> call = userAPI.listFriend(this.userName);
        call.enqueue(new Callback<List<MemberData>>() {
            @Override
            public void onResponse(Call<List<MemberData>> call, Response<List<MemberData>> response) {
                memberData = (ArrayList<MemberData>) response.body();
                Toast.makeText(Contact.this, "size " + memberData.size(), Toast.LENGTH_SHORT).show();
                if (memberData != null) {
                    setUpRecyleView();
                }
            }

            @Override
            public void onFailure(Call<List<MemberData>> call, Throwable t) {

            }
        });
    }

    private void setUpRecyleView() {
        adapter = new MemberAdapter(memberData, this);

        linearLayoutManager = new LinearLayoutManager(this);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(linearLayoutManager);
    }

}
