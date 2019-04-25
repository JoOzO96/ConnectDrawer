package com.example.jose.connectdrawer.Vendedor;

import com.example.jose.connectdrawer.cliente.Cliente;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Jose on 23/06/2017.
 */

public interface VendedorService {
    @GET("vendedor")
    Call<List<Vendedor>> listVendedor();
}
