package com.example.jose.connectdrawer.Pedido;

import com.example.jose.connectdrawer.cidade.Cidade;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Jose on 21/06/2017.
 */

public interface PedidoService {
    @GET("listaPedido")
    Call<List<Pedido>> listPedido();
}
