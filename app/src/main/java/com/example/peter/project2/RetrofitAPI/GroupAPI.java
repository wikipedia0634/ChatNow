package com.example.peter.project2.RetrofitAPI;

import com.example.peter.project2.MemberData;
import com.example.peter.project2.Models.Friend;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface GroupAPI {
    @POST("/createConversation")
    Call<MemberData> createConversation(@Body MemberData user);
    @GET("/listFriend/{userName}")
    Call<List<MemberData>> listFriend(@Path("userName") String email);
    @GET("/getAllUser")
    Call<List<MemberData>> listUser();
    @PUT("/addFriendByUserName")
    Call<List<MemberData>> addFriend(@Body Friend friend);
}
