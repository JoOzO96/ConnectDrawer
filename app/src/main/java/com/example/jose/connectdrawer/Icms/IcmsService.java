package com.example.jose.connectdrawer.Icms;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface IcmsService {
        @GET("icms")
        Call<List<Icms>> listIcms();
}
