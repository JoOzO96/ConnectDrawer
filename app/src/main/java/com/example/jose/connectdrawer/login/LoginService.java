package com.example.jose.connectdrawer.login;

import com.example.jose.connectdrawer.cliente.Cliente;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by Jose on 18/05/2017.
 */

public interface LoginService {
    @GET("mac/{macAddress}")
    Call<String> retornaMac(@Path("macAddress") String macAddress);
}
