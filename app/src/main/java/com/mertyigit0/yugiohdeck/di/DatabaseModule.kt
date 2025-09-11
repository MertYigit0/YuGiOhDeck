package com.mertyigit0.yugiohdeck.di

import android.content.Context
import androidx.room.Room
import com.mertyigit0.yugiohdeck.data.local.AppDatabase
import com.mertyigit0.yugiohdeck.data.local.CardDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "mydeck_db"
        ).build()
    }

    @Provides
    fun provideCardDao(db: AppDatabase): CardDao = db.cardDao()
}
