package com.example.peter.project2;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.peter.project2.Service.SaveLocal;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class MemberAdapter extends RecyclerView.Adapter {
    private ArrayList<MemberData> mMember;
    private Context context;

    public MemberAdapter(ArrayList<MemberData> mMember, Context context){
        this.mMember = mMember;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Context context = viewGroup.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View memberView = inflater.inflate(R.layout.view_1_o, viewGroup, false);

        ViewHolder viewHolder = new ViewHolder(memberView);

        return viewHolder;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private View itemview;
        public TextView username;
        public TextView time;
        public CircleImageView imgStory;
        public ViewHolder(View itemView){
            super(itemView);

            itemview = itemView;
            username = itemView.findViewById(R.id.username);
            time = itemView.findViewById(R.id.time);
            imgStory=itemview.findViewById(R.id.imgStory);

            itemview.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    chatWith(username.getText().toString());
                }
            });
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int i) {
        ((ViewHolder)holder).username.setText(mMember.get(i).getName());
        ((ViewHolder)holder).time.setText(mMember.get(i).getTime()+"");
        if(mMember.get(i).getUrlAvatar()!=null){
            Picasso.get().load(mMember.get(i).getUrlAvatar()).into( ((ViewHolder)holder).imgStory);

        }

    }

    @Override
    public int getItemCount() {
        return mMember.size();
    }
    private void chatWith(String name){
        Intent i = new Intent(context,Chat.class);
        i.putExtra("username", SaveLocal.getUserNameFromLocal(context));
        context.startActivity(i);



    }
}
