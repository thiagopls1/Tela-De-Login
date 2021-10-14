package com.example.loginscreen

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [User::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
        abstract fun userDao(): UserDao
}