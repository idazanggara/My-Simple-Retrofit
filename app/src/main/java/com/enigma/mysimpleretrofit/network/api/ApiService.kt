package com.enigma.mysimpleretrofit.network.api

import com.enigma.mysimpleretrofit.network.response.BaseResponse
import com.enigma.mysimpleretrofit.network.response.BaseResponseMoshi
import com.enigma.mysimpleretrofit.network.response.DataItem
import com.google.gson.JsonObject
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface ApiService {
    @GET("/api/users?page=2")
    suspend fun getUser(): Response<BaseResponse>

    @GET("/api/users/{id}")
    suspend fun getUserByID(@Path("id") id: String): Response<BaseResponseMoshi>

    @PUT("/api/users/{id}")
    suspend fun updateUser(@Path("id") id: String, @Body body: JsonObject): Response<JsonObject>

    @DELETE("/api/users/{id}")
    suspend fun deleteUser(@Path("id") id: String): Response<JsonObject>

    @POST("/api/users")
    suspend fun createUser(@Body body: JsonObject): Response<JsonObject>
}