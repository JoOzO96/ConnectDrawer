package com.example.jose.connectdrawer.NotaFiscal;

import com.example.jose.connectdrawer.ControleCodigo.ControleCodigo;
import com.example.jose.connectdrawer.Pedido.Pedido;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Jose on 21/06/2017.
 */

public interface NotaFiscalService {
    @GET("notafiscal")
    Call<ControleCodigo> notafiscal();
}
