package com.example.peter.project2;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.peter.project2.Models.Friend;
import com.example.peter.project2.RetrofitAPI.APIClient;
import com.example.peter.project2.RetrofitAPI.UserAPI;
import com.example.peter.project2.Service.SaveLocal;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddFriendAdapter extends RecyclerView.Adapter {
    private ArrayList<MemberData> mMember;
    private Context context;
    private UserAPI userAPI;

    public AddFriendAdapter(ArrayList<MemberData> mMember, Context context) {
        this.mMember = mMember;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Context context = viewGroup.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View addfriendView = inflater.inflate(R.layout.view_1_o_add_friend, viewGroup, false);

        ViewHolder viewHolder = new ViewHolder(addfriendView);

        return viewHolder;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private View itemview;
        public TextView username;
        public TextView time;
        public CircleImageView imgStory;
        public Button btnAddFriend;

        public ViewHolder(View itemView) {
            super(itemView);

            itemview = itemView;
            username = itemView.findViewById(R.id.username);
            time = itemView.findViewById(R.id.time);
            imgStory = itemview.findViewById(R.id.imgStory);
            btnAddFriend = itemView.findViewById(R.id.btn_add_Friend);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int i) {
        ((ViewHolder) holder).username.setText(mMember.get(i).getName());
        ((ViewHolder) holder).time.setText(mMember.get(i).getTime() + "");
        if (mMember.get(i).getUrlAvatar() != null) {
            Picasso.get().load(mMember.get(i).getUrlAvatar()).into(((ViewHolder) holder).imgStory);

        }
        // xử lý nút thêm bạn
        ((ViewHolder) holder).btnAddFriend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Friend friend = new Friend();
                friend.set_id(mMember.get(i).getId());
                friend.setName(mMember.get(i).getName());
                friend.setUrlAvatar(mMember.get(i).getUrlAvatar());
                friend.setEmailUser(SaveLocal.getUserNameFromLocal(context));
                // ADD friend
                addFriend(friend);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mMember.size();
    }

    private void addFriend(Friend friend) {
        userAPI = APIClient.getClient().create(UserAPI.class);
        Call<List<MemberData>> call = userAPI.addFriend(friend);
        call.enqueue(new Callback<List<MemberData>>() {
            @Override
            public void onResponse(Call<List<MemberData>> call, Response<List<MemberData>> response) {
            }

            @Override
            public void onFailure(Call<List<MemberData>> call, Throwable t) {

            }
        });
        Toast.makeText(context, "Đã thêm bạn" , Toast.LENGTH_SHORT).show();

    }
}