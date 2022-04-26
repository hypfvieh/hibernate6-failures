package com.github.hypfvieh.sandbox.hibernate.data;

import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class ContentEntry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    private String content;

    public long getId() {
        return id;
    }

    public void setId(long _id) {
        id = _id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String _content) {
        content = _content;
    }

    @Override
    public String toString() {
        return "ContentEntry [id=" + id + ", content=" + content + "]";
    }

    @Override
    public int hashCode() {
        return Objects.hash(content, id);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ContentEntry)) {
            return false;
        }
        ContentEntry other = (ContentEntry) obj;
        return Objects.equals(content, other.content) && id == other.id;
    }
    
}
