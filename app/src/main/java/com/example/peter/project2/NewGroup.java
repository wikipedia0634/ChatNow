package com.example.peter.project2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class NewGroup extends AppCompatActivity {
    Button btn_back_newgroup;
    RecyclerView recyclerView;
    MemberAdapter adapter;
    ArrayList<MemberData> memberData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_group);
        btn_back_newgroup = findViewById(R.id.btn_back_newgroup);

        btn_back_newgroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        recyclerView = findViewById(R.id.Rcv_newgroup);

        memberData = new ArrayList<MemberData>();
        for (int i=0; i <= 20; i++){
            memberData.add(new MemberData("Group " + i, 1 + i++));
        }

        adapter = new MemberAdapter(memberData,this);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(linearLayoutManager);
    }
}
