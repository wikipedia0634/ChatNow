package com.example.peter.project2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.Toast;

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
    ArrayList<MemberData> friendSearch= new ArrayList();
    UserAPI userAPI;
    String emailUser;
    SearchView simpleSearchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_friend);
        btn_back_add = findViewById(R.id.btn_back_add_friend);
        recyclerView_add = findViewById(R.id.Rcv_add);
        simpleSearchView = findViewById(R.id.simpleSearchView); // inititate a search view
//        memberData = new ArrayList<MemberData>();
//        for (int i = 0; i <= 20; i++) {
//            memberData.add(new MemberData("Add " + i, 1 + i++));
//        }

//        adapter_add = new AddFriendAdapter(memberData, getApplicationContext());
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
//
//        recyclerView_add.setAdapter(adapter_add);
//        recyclerView_add.setLayoutManager(linearLayoutManager);
        //get listUser
        getListUser();
        // searchFriend
        searchFriend();
        btn_back_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void getListUser() {
        userAPI = APIClient.getClient().create(UserAPI.class);
        Call<List<MemberData>> call = userAPI.listUser();
        call.enqueue(new Callback<List<MemberData>>() {
            @Override
            public void onResponse(Call<List<MemberData>> call, Response<List<MemberData>> response) {
                memberData = (ArrayList<MemberData>) response.body();
                friendSearch=memberData;
                if (memberData != null) {
                    //remove user in array
                    removeCurrentUserInArray();
                    setUpRecyleView();
                }
            }

            @Override
            public void onFailure(Call<List<MemberData>> call, Throwable t) {

            }
        });
    }

    private void setUpRecyleView() {
        adapter_add = new AddFriendAdapter(friendSearch, getApplicationContext());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);

        recyclerView_add.setAdapter(adapter_add);
        recyclerView_add.setLayoutManager(linearLayoutManager);
    }

    private void removeCurrentUserInArray() {
        //getEmailUser
        emailUser = SaveLocal.getUserNameFromLocal(AddFriend.this);
        for (int i = 0; i < friendSearch.size(); i++) {
            if (friendSearch.get(i).getEmail().equalsIgnoreCase(emailUser)) {
                friendSearch.remove(i);
                break;
            }
        }
    }
    private void searchFriend(){
      simpleSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
          @Override
          public boolean onQueryTextSubmit(String s) {
              return false;
          }

          @Override
          public boolean onQueryTextChange(String s) {
              Toast.makeText(AddFriend.this, "dang thay doi ne", Toast.LENGTH_SHORT).show();
              return false;
          }
      });
    }
    private void filterList(String name){
        for(MemberData fr : memberData){
            if(fr.getName().contains(name)){
                friendSearch.add(fr);
            }
        }
    }
}
