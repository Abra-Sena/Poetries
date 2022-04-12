package com.emissa.apps.poetries.di

import android.content.Context
import androidx.room.Room
import com.emissa.apps.poetries.database.PoemDao
import com.emissa.apps.poetries.database.PoemDatabaseRepository
import com.emissa.apps.poetries.database.PoemDatabaseRepositoryImpl
import com.emissa.apps.poetries.database.PoetryDatabase
import com.emissa.apps.poetries.network.PoetryRepository
import com.emissa.apps.poetries.network.PoetryRepositoryImpl
import com.emissa.apps.poetries.network.PoetryService
import com.emissa.apps.poetries.utils.DataConverter
import com.emissa.apps.poetries.viewmodel.PoetryViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val networkModule = module {
    fun provideLoggingInterceptor() : HttpLoggingInterceptor =
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

    fun provideOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .build()

    fun provideNetworkService(okHttpClient: OkHttpClient): PoetryService =
        Retrofit.Builder()
            .baseUrl(PoetryService.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create(PoetryService::class.java)

    fun providePoetryRepo(networkService: PoetryService) : PoetryRepository =
        PoetryRepositoryImpl(networkService)


    single { provideLoggingInterceptor() }
    single { provideNetworkService(get()) }
    single { provideOkHttpClient(get()) }
    single { providePoetryRepo(get()) }
}

val applicationModule = module {
    // provide here database, dao and database repository
    fun providePoetryDatabase(context: Context) : PoetryDatabase =
        Room.databaseBuilder(
            context,
            PoetryDatabase::class.java,
            "poem-db"
        )
            .addTypeConverter(DataConverter())
            .build()

    fun providePoemDao(poetryDatabase: PoetryDatabase) : PoemDao = poetryDatabase.getPoemDao()

    fun provideDatabaseRepository(databaseDao: PoemDao) : PoemDatabaseRepository =
        PoemDatabaseRepositoryImpl(databaseDao)

    single { providePoetryDatabase(androidContext()) }
    single { providePoemDao(get()) }
    single { provideDatabaseRepository(get()) }
}

val viewModelModule = module {
    // provide the view model
    viewModel { PoetryViewModel(get(), get())}
}
