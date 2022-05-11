package com.github.hypfvieh.sandbox.hibernate.data;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;

@Entity
public class SimpleEntry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(columnDefinition = "CLOB")
    @Lob
    private String description;

    public long getId() {
        return id;
    }

    public void setId(long _id) {
        id = _id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String _description) {
        description = _description;
    }

    @Override
    public String toString() {
        return "SimpleEntry [id=" + id + ", description=" + description + "]";
    }

}
