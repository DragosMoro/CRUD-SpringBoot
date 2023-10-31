package org.example.model;

import java.io.Serializable;

public class Entity<ID> {
    private ID id;


    public ID getId() {
        return id;
    }

    public void setId(ID id) {
        this.id = id;
    }
}
