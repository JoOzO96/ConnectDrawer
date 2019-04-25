package com.example.jose.connectdrawer.cliente;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Jose on 18/05/2017.
 */

public interface ClienteService {
    @GET("cliente/{macAddress}")
    Call<List<Cliente>> listCliente(@Path("macAddress") String macAddress);
}
