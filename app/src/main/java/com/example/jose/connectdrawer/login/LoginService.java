package com.example.jose.connectdrawer.login;

import com.example.jose.connectdrawer.cliente.Cliente;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface LoginService {
    @GET("retornaMac/{macAddress}")
    Call<String> retornaMac(@Path("macAddress") String macAddress);
}
