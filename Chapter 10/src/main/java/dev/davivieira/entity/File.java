package dev.davivieira.entity;

import jakarta.persistence.*;

@Entity
public class File {

    @Id
    private String id;

    private String name;

    @Lob
    @Column(length = 20971520)
    private byte[] content;

    public File() {}

    public File(String id, String name, byte[] content) {
        this.id = id;
        this.name = name;
        this.content = content;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }
}
