package br.com.vezixor.test.webeleven.service;

import br.com.vezixor.test.webeleven.model.SearchResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Everson on 4/13/2018.
 *
 * https://itunes.apple.com/search?term=aerosmith&entity=musicTrack
 */

public interface ItunesService {
    @GET("search?entity=musicTrack")
    Call<SearchResponse> search(@Query("term") String term);
}
