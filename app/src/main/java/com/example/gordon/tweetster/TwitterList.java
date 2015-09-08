package com.example.gordon.tweetster;


import com.google.gson.annotations.SerializedName;
import com.twitter.sdk.android.core.models.User;

public class TwitterList {
    @SerializedName("slug")
    private String slug;
    @SerializedName("name")
    private String name;
    @SerializedName("created_at")
    private String created_at;
    @SerializedName("uri")
    private String uri;
    @SerializedName("subscriber_count")
    private String subscriber_count;
    @SerializedName("id_str")
    private String id_str;
    @SerializedName("member_count")
    private String member_count;
    @SerializedName("mode")
    private String mode;
    @SerializedName("id")
    private String id;
    @SerializedName("full_name")
    private String full_name;
    @SerializedName("description")
    private String description;
    @SerializedName("user")
    private User user;
    @SerializedName("following")
    private String following;

    public TwitterList(String slug, String name, String created_at, String uri, String subscriber_count, String id_str, String member_count, String mode, String id, String full_name, String description, User user, String following) {
        this.slug = slug;
        this.name = name;
        this.created_at = created_at;
        this.uri = uri;
        this.subscriber_count = subscriber_count;
        this.id_str = id_str;
        this.member_count = member_count;
        this.mode = mode;
        this.id = id;
        this.full_name = full_name;
        this.description = description;
        this.user = user;
        this.following = following;
    }

    public String getName() {
        return name;
    }
}
