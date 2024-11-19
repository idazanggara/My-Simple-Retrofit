package com.enigma.mysimpleretrofit.network

import com.enigma.mysimpleretrofit.BuildConfig
import com.enigma.mysimpleretrofit.network.api.ApiService
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory


abstract class RetrofitInstance {
    companion object{
        // gson dan moshi sama, ini sebagai contoh aja ya
        // unutk gson buat get all user
        fun getRetrofit() = Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL) // Menggunakan BASE_URL dari BuildConfig
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        // moshi untuk get single user
        private val moshi: Moshi by lazy {
            Moshi.Builder()
                .add(KotlinJsonAdapterFactory()) // Tambahkan ini dan Harus diinisialisasi dengan tanda kurung ()
                .build()
        }
        fun getRetrofitMoshi() = Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL) // Menggunakan BASE_URL dari BuildConfig
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()

        // bisa ini atau
        // fun gerApiServiceFun(): piService = getRetrofit().create(ApiService::class.java)
        // ini sama aja, tinggal butuh memasukkan parameter enggak? kalau enggak bisa pakai variable aja
        val apiService: ApiService by lazy {
            getRetrofit().create(ApiService::class.java)
        }

        val apiServiceMoshi: ApiService by lazy {
            getRetrofitMoshi().create(ApiService::class.java)
        }
    }
}