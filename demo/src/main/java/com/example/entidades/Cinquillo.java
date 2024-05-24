package com.example.entidades;

public class Cinquillo {

    private String id;
    private String escaleras;

    public Cinquillo() {}

    public Cinquillo(String id, String escaleras) {
        this.id = id;
        this.escaleras = escaleras;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEscaleras() {
        return escaleras;
    }

    public void setEscaleras(String escaleras) {
        this.escaleras = escaleras;
    }
}