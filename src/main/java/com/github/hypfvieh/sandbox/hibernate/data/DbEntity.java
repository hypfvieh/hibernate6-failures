package com.github.hypfvieh.sandbox.hibernate.data;

import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
public class DbEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    @OneToOne
    private ContentEntry content;

    public long getId() {
        return id;
    }

    public void setId(long _id) {
        id = _id;
    }

    public ContentEntry getContent() {
        return content;
    }

    public void setContent(ContentEntry _value) {
        content = _value;
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
        if (!(obj instanceof DbEntity)) {
            return false;
        }
        DbEntity other = (DbEntity) obj;
        return Objects.equals(content, other.content) && id == other.id;
    }

    @Override
    public String toString() {
        return "DbEntity [id=" + id + ", content=" + content + "]";
    }
    
}
