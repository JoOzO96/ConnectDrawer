package com.example.jose.connectdrawer.Emitente;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface EmitenteService {
    @GET("emitente/{codemitente}")
    Call<Emitente> emitente(@Path("codemitente") String codemitente);
}
