package com.riquelmemr.simpletweet.model;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_like")
public class Like extends Item {
    @ManyToOne(optional = false)
    @JoinColumn(name = "owner_id")
    private User owner;

    @ManyToOne(optional = false)
    @JoinColumn(name = "tweet_id")
    private Tweet tweet;

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public Tweet getTweet() {
        return tweet;
    }

    public void setTweet(Tweet tweet) {
        this.tweet = tweet;
    }
}
