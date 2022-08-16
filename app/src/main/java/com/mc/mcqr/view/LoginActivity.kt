package com.mc.mcqr.view

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.mc.mcqr.R
import com.mc.mcqr.configuration.ApiClient
import com.mc.mcqr.configuration.SessionManager
import com.mc.mcqr.models.BookResponse
import com.mc.mcqr.models.LoginRequest
import com.mc.mcqr.models.LoginResponse
import retrofit2.Call
import retrofit2.Callback

import retrofit2.Response

class LoginActivity : AppCompatActivity() {


    private lateinit var sessionManager: SessionManager
    private lateinit var apiClient: ApiClient

    lateinit var bookList: MutableList<BookResponse>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_activity)

        apiClient = ApiClient()
        sessionManager = SessionManager(this)

        var authToken = "";
        var id = 0;

        // Login
        apiClient.getApiService(this)
            .login(LoginRequest(username = "pruebas", password = 123456))
            .enqueue(object : Callback<LoginResponse> {
                override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                    Log.e("main", "login error")
                }

                override fun onResponse(
                    call: Call<LoginResponse>,
                    response: Response<LoginResponse>
                ) {
                    val loginResponse = response.body()
                    authToken = loginResponse?.token.toString();
                    id = 1000003;
                    //Log.d("main", "response code : " + response.code())
                    //Log.d("main", "Authorization: " + authToken)

                    if (response.code() == 200 && authToken != "") {
                        Log.d("main", "Login is successful!")

                      val token2: String? = "Bearer " + sessionManager.fetchAuthToken()

                        getBookList(id, token2.toString())
                    } else {
                        Log.e("main", "login is not allowed")
                    }
                }

            })
    }

    fun getBookList(id: Int, token2: String) {
        apiClient.getApiService(this).getBooks(token = token2)

            .enqueue(object : Callback<List<BookResponse>> {
                override fun onFailure(call: Call<List<BookResponse>>, t: Throwable) {
                    Log.e("Id usuario", id.toString())
                    Log.e("main", "Se revento  XD")
                    Log.e("Main", "Token -> " + token2)
                }
                override fun onResponse(
                    call: Call<List<BookResponse>>,
                    response: Response<List<BookResponse>>
                ) {
                    if (response.code() == 200) {
                        Log.d("main", "book list received")
                        bookList = (response.body() as MutableList<BookResponse>?)!!
                        Log.d("main", bookList.toString())
                    } else {
                       Log.e("Main", "Token ->  " + token2)
                        Log.e("main", "Aquí voy XD " + response.code())
                    }
                }
            })
    }

}

