package com.example.jose.connectdrawer.PedidoProduto;

import com.example.jose.connectdrawer.Pedido.Pedido;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Jose on 11/07/2017.
 */

public interface PedidoProdutoService{
        @GET("listaPedidoProduto")
        Call<List<PedidoProduto>> listPedidoProduto();
}
