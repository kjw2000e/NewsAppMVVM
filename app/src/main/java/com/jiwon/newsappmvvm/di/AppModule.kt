package com.jiwon.newsappmvvm.di

import android.content.Context
import androidx.room.Room
import com.jiwon.newsappmvvm.api.NewsAPI
import com.jiwon.newsappmvvm.api.RetrofitInstance
import com.jiwon.newsappmvvm.db.ArticleDatabase
import com.jiwon.newsappmvvm.util.Constants.Companion.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    // okhttp
    @Singleton
    @Provides
    fun provideOkhttp(): OkHttpClient {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.BODY)
        return OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .build()
    }

    // retrofit
    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun providesNewsApi(retrofit: Retrofit): NewsAPI{
        return retrofit.create(NewsAPI::class.java)
    }


    //room
    @Singleton
    @Provides
    fun providesNewsDatabase(@ApplicationContext context: Context): ArticleDatabase =
        Room.databaseBuilder(
            context.applicationContext,
            ArticleDatabase::class.java,
            "articleDatabase.db"
        ).build()
}