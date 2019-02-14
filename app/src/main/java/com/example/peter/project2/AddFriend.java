package com.example.peter.project2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.example.peter.project2.R;
import com.example.peter.project2.RetrofitAPI.APIClient;
import com.example.peter.project2.RetrofitAPI.UserAPI;
import com.example.peter.project2.Service.SaveLocal;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddFriend extends AppCompatActivity {
    Button btn_back_add;
    RecyclerView recyclerView_add;
    AddFriendAdapter adapter_add;
    ArrayList<MemberData> memberData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_friend);
        btn_back_add = findViewById(R.id.btn_back_add_friend);
        recyclerView_add = findViewById(R.id.Rcv_add);

        memberData = new ArrayList<MemberData>();
        for (int i=0; i <= 20; i++){
            memberData.add(new MemberData("Add " + i, 1 + i++));
        }

        adapter_add = new AddFriendAdapter(memberData,getApplicationContext());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);

        recyclerView_add.setAdapter(adapter_add);
        recyclerView_add.setLayoutManager(linearLayoutManager);

        btn_back_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

}
