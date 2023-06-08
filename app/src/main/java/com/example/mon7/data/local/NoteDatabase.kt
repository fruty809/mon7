package com.example.mon7.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.mon7.data.local.NoteDao
import com.example.mon7.data.model.NoteEntity

@Database(entities = [NoteEntity::class], version = 1)
abstract class NoteDatabase: RoomDatabase(){
    abstract fun noteDao(): NoteDao
}