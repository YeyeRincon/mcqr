package com.mc.mcqr.configuration

import com.mc.mcqr.models.BookResponse
import com.mc.mcqr.models.LoginRequest
import com.mc.mcqr.models.LoginResponse
import retrofit2.Call
import retrofit2.http.*

interface ApiService {

    @POST(Paths.LOGIN_URL)
    fun login(@Body request: LoginRequest): Call<LoginResponse>

  //  @GET(Paths.BOOKS_URL)
  //  fun getBooks(): Call<List<Book>>
 // @GET(Paths.BOOKS_URL)
  //fun getBooks(t@Path("ad_user_id") ad_user_id: In, @Headers("Authorization") String token): Call<Book>
  //Call<Book> getBooks(@Header("Authorization") String token);
  @GET(Paths.BOOKS_URL)
  fun getBooks(@Header("Authorization") token: String): Call<List<BookResponse>>


}