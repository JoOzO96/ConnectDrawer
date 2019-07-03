package com.example.jose.connectdrawer.ConsultaSefaz;

import com.example.jose.connectdrawer.cliente.Cliente;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Jose on 18/05/2017.
 */

public interface InfCadastroService {
    @GET("clientesefaz/{cnpj}")
    Call<List<InfCadastro>> listInfCadastro(@Path("cnpj") String cnpj);
}
