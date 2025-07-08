package com.erickcosta.literatura.dto;

import java.util.List;

public class GutendexBook {
    private String title;
    private List<GutendexAuthor> authors;
    private List<String> languages;
    private Integer download_count;

    public String getTitle() {
        return title;
    }

    public List<GutendexAuthor> getAuthors() {
        return authors;
    }

    public List<String> getLanguages() {
        return languages;
    }

    public Integer getDownload_count() {
        return download_count;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthors(List<GutendexAuthor> authors) {
        this.authors = authors;
    }

    public void setLanguages(List<String> languages) {
        this.languages = languages;
    }

    public void setDownload_count(Integer download_count) {
        this.download_count = download_count;
    }
}
