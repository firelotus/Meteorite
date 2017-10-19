package com.firelotus.meteoritelibrary.tools;

/**
 * Created by firelotus on 2017/10/15.
 */

public class MNovateResponse<T> {
    private String error;

    private T results;

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

    @Override
    public String toString() {
        return "NovateResponse2{" +
                "error='" + error + '\'' +
                ", results=" + results +
                '}';
    }
}
