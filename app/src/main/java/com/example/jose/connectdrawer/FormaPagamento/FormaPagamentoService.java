package com.example.jose.connectdrawer.FormaPagamento;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Jose on 23/06/2017.
 */

public interface FormaPagamentoService {
    @GET("listaFormaPagamento")
    Call<List<FormaPagamento>> listFormaPagamento();

}
