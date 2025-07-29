package com.riquelmemr.simpletweet.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_role")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public enum Values {
        ADMIN(1),
        BASIC(2);

        final int id;

        Values(int id) {
            this.id = id;
        }

        public int getId() {
            return id;
        }
    }
}
