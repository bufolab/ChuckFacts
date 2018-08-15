package com.bufolab.android.chuckfacts.dagger

import android.content.Context
import com.bufolab.android.chuckfacts.BuildConfig
import com.bufolab.android.chuckfacts.data.ChuckFactService
import com.bufolab.android.chuckfacts.data.LocalRepository
import com.bufolab.android.chuckfacts.data.Repository
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by Bufolab on 14/08/2018.
 */
@Module(includes = [AppModule::class])
class DataModule {

    @Provides
    fun  serviceApi(retrofit:Retrofit): ChuckFactService {
        return retrofit.create(ChuckFactService::class.java)
    }


    @Provides
    fun  retrofit(client: OkHttpClient,
                  gsonConverterFactory:GsonConverterFactory):Retrofit{

        return Retrofit.Builder()
                .baseUrl("https://api.chucknorris.io/jokes/")
                .client(client)
                .addConverterFactory(gsonConverterFactory)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
                .build()
    }

    @Provides
    fun  gson():Gson{
        return GsonBuilder()
                //.registerTypeAdapter(QoDCategoriesResponse.Contents::class.java, QoDCategoriesDesarializator())
                .create()
    }

    @Provides
    fun  gsonConverterFactory(gson: Gson):GsonConverterFactory{
        return GsonConverterFactory.create(gson)
    }

    @Provides
    fun okHttpClient(): OkHttpClient {
        val logging = HttpLoggingInterceptor()
        if(BuildConfig.DEBUG) {
            logging.level = HttpLoggingInterceptor.Level.BODY
        }else{
            logging.level = HttpLoggingInterceptor.Level.NONE
        }

        return OkHttpClient.Builder().addInterceptor(logging).build()
    }

    @Provides
    fun repository(context: Context):Repository{
            return LocalRepository(context)
     }

}