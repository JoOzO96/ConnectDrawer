package com.example.jose.connectdrawer.cidade;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Jose on 25/05/2017.
 */

public interface CidadeService {
    @GET("listaCidade")
    Call<List<Cidade>> listCidade();
}
