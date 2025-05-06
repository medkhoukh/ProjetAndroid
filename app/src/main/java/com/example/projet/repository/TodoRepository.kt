package com.example.projet.repository

import com.example.projet.api.TodoApi
import com.example.projet.model.Todo

class TodoRepository(private val api: TodoApi) {
    suspend fun getTodos(): List<Todo> = api.getTodos()
    
    suspend fun getTodo(id: Int): Todo = api.getTodo(id)
    
    suspend fun createTodo(todo: Todo): Todo = api.createTodo(todo)
    
    suspend fun updateTodo(id: Int, todo: Todo): Todo = api.updateTodo(id, todo)
    
    suspend fun deleteTodo(id: Int) = api.deleteTodo(id)
} 