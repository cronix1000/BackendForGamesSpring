package com.baas.backend.controller;

public class SaveResponse {

    public SaveResponse(String response) {
        saveData = response;
    }

    private String saveData;

    public String getSaveData() {
        return saveData;
    }
    // constructor, getters
}
