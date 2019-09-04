package com.example.jose.connectdrawer.Emitente;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface EmiteConfiguraService {
    @GET("emiteconfigura/{codemitente}")
    Call<EmiteConfigura> emiteconfigura(@Path("codemitente") String codemitente);
}
