package com.example.projet.api

import com.example.projet.model.Todo
import retrofit2.http.*

interface TodoApi {
    @GET("https://api/todos")
    suspend fun getTodos(): List<Todo>
    
    @GET("https://api/todos/{id}")
    suspend fun getTodo(@Path("id") id: Int): Todo
    
    @POST("https://api/todos")
    suspend fun createTodo(@Body todo: Todo): Todo
    
    @PUT("https://api/todos/{id}")
    suspend fun updateTodo(@Path("id") id: Int, @Body todo: Todo): Todo
    
    @DELETE("https://api/todos/{id}")
    suspend fun deleteTodo(@Path("id") id: Int)
} 