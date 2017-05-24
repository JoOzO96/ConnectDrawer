package com.example.jose.connectdrawer.cliente;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Jose on 18/05/2017.
 */

public interface ClienteService {
    @GET("listaCliente")
    Call<List<Cliente>> listCliente();
}
