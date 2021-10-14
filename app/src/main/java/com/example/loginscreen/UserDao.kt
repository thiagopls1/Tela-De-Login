package com.example.loginscreen

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface UserDao {

    @Query("SELECT * FROM User")
    fun getAll(): List<User>

    @Query("SELECT * FROM User WHERE username IN (:loggedUsername) ")
    fun getUserById(loggedUsername: String): List<User>

    @Query("SELECT username FROM User")
    fun getAllUsers(): List<String>

    @Query("SELECT email FROM User")
    fun getAllEmails(): List<String>

    @Insert
    fun insertAll(vararg user: User)

}