package com.riquelmemr.simpletweet.entities;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;
import java.util.UUID;

@MappedSuperclass
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID pk;

    @UpdateTimestamp
    private Date modifiedTime;

    @CreationTimestamp
    private Date creationTime;

    public UUID getPk() {
        return pk;
    }

    public void setPk(UUID pk) {
        this.pk = pk;
    }

    public Date getModifiedTime() {
        return modifiedTime;
    }

    public void setModifiedTime(Date modifiedTime) {
        this.modifiedTime = modifiedTime;
    }

    public Date getCreationTime() {
        return creationTime;
    }
}
