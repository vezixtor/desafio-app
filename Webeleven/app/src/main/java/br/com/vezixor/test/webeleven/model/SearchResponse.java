package br.com.vezixor.test.webeleven.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Everson on 4/13/2018.
 */

public class SearchResponse {
    private Integer resultCount;
    private List<MusicTrack> results = new ArrayList<>();

    public Integer getResultCount() {
        return resultCount;
    }

    public void setResultCount(Integer resultCount) {
        this.resultCount = resultCount;
    }

    public List<MusicTrack> getResults() {
        return results;
    }

    public void setResults(List<MusicTrack> results) {
        this.results = results;
    }
}
