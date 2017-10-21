package com.firelotus.meteoritelibrary.tools;

import java.util.List;

/**
 * Created by firelotus on 2017/10/15.
 */

public class MNovateResponse<T> {
    private String error;

    private T results;

    private List<String> category;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public T getResults() {
        return results;
    }

    public void setResults(T results) {
        this.results = results;
    }

    public List<String> getCategory() {
        return category;
    }

    public void setCategory(List<String> category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "MNovateResponse{" +
                "error='" + error + '\'' +
                ", results=" + results +
                ", category=" + category +
                '}';
    }
}
