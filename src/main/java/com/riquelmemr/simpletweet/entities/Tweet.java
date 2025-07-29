package com.riquelmemr.simpletweet.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_tweet")
public class Tweet extends Item {
    private String content;

    @ManyToOne(optional = false)
    @JoinColumn(name = "author_id")
    private User author;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
