package com.example.jasdipc.mafiapartygame.Models;


public class Member {
    private String name;
    private String endpoint;

    public Member(String name, String endpoint) {
        this.name = name;
        this.endpoint = endpoint;
    }

    public String getName() {
        return name;
    }

    public String getEndpoint() {
        return endpoint;
    }
}
