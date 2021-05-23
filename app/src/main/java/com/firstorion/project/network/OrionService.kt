package com.firstorion.project.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import java.util.concurrent.TimeUnit

private const val BASE_URL = "https://jsonplaceholder.typicode.com/"

interface OrionService {

    @GET("posts")
    suspend fun getAllPostsFromAllUsers(): List<PostNetworkEntity>

    @GET("users/{userId}")
    suspend fun getUserWithId(@Path("userId") userId: Int): UserNetworkEntity

    @POST("posts")
    suspend fun uploadPost(userId: Int, title: String, body: String): PostNetworkEntity

}

private val okHttpClient = HttpLoggingInterceptor().run {
    level = HttpLoggingInterceptor.Level.BODY
    val builder = OkHttpClient.Builder()
    builder.connectTimeout(5, TimeUnit.MINUTES) // connect timeout
        .writeTimeout(5, TimeUnit.MINUTES) // write timeout
        .readTimeout(5, TimeUnit.MINUTES) // read timeout
    builder.addInterceptor(this).build()
}

private val retrofit =
    Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .baseUrl(BASE_URL)
        .build()

object OrionApi {
    val orionService: OrionService by lazy {
        retrofit.create(OrionService::class.java)
    }
}
