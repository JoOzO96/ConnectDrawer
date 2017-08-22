package com.example.jose.connectdrawer.Produto;

import com.example.jose.connectdrawer.PedidoProduto.PedidoProduto;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Jose on 31/07/2017.
 */

public interface ProdutoService {
    @GET("listaProduto")
    Call<List<Produto>> listaProduto();
}
