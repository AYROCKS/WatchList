package com.aystudio.watchlist.data.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.aystudio.watchlist.data.local.AppDatabase
import com.aystudio.watchlist.data.local.Dao
import com.aystudio.watchlist.data.remote.ApiService
import com.aystudio.watchlist.data.repository.DatabaseRepositoryImp
import com.aystudio.watchlist.data.repository.RepositoryImp
import com.aystudio.watchlist.domain.repository.DatabaseRepository
import com.aystudio.watchlist.domain.repository.Repository
import com.aystudio.watchlist.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providesLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    }


    @Provides
    @Singleton
    fun providesOkHttpClient(loggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder().addInterceptor(loggingInterceptor).build()
    }

    @Provides
    @Singleton
    fun providesApiServices(okHttpClient: OkHttpClient): ApiService {
        return Retrofit.Builder().baseUrl(Constants.BASE_URL).client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create()).build()
            .create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun providesRepositoryImp(apiService: ApiService): Repository {
        return RepositoryImp(apiService)
    }


    @Provides
    @Singleton
    fun providesRoomDatabase(@ApplicationContext context: Context) : AppDatabase{
        return Room.databaseBuilder(context, AppDatabase::class.java, "movies_database").build()
    }


    @Provides
    @Singleton
    fun providesMovieDao(database: AppDatabase) : Dao{
        return database.movieDao()
    }

    @Provides
    @Singleton
    fun providesDatabaseRepository(moviesDao: Dao): DatabaseRepository{
        return DatabaseRepositoryImp(moviesDao)
    }


}