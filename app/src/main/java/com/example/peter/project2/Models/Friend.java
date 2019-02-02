package com.example.peter.project2.Models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Friend  implements Serializable {
    @SerializedName("_id")
    String _id;
    @SerializedName("name")
    String name;
    @SerializedName("imgUrl")
    String urlAvatar;

    public Friend() {
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrlAvatar() {
        return urlAvatar;
    }

    public void setUrlAvatar(String urlAvatar) {
        this.urlAvatar = urlAvatar;
    }
}
