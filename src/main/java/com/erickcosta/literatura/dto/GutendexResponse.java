package com.erickcosta.literatura.dto;

import java.util.List;

public class GutendexResponse {
    private List<GutendexBook> results;

    public List<GutendexBook> getResults() {
        return results;
    }

    public void setResults(List<GutendexBook> results) {
        this.results = results;
    }
}
