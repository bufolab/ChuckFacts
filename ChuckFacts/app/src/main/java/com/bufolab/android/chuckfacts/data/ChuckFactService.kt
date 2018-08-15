package com.bufolab.android.chuckfacts.data

import com.bufolab.android.chuckfacts.BuildConfig
import com.bufolab.android.chuckfacts.data.response.ChuckFactResponse
import com.google.gson.GsonBuilder
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query


/**
 * Created by Bufolab on 12/08/2018.
 */
interface ChuckFactService  {

    companion object Factory{
        //root urls
        //val URL_PUBLIC:String = "http://quotes.rest/"
        val URL_PUBLIC:String = "https://api.chucknorris.io/jokes/"

        fun  create(): ChuckFactService {

            val logging = HttpLoggingInterceptor()
            if(BuildConfig.DEBUG) {
                logging.level = HttpLoggingInterceptor.Level.BODY
            }else{
                logging.level = HttpLoggingInterceptor.Level.NONE
            }

            val client = OkHttpClient.Builder().addInterceptor(logging).build()
            val gson = GsonBuilder()
                    //.registerTypeAdapter(QoDCategoriesResponse.Contents::class.java, QoDCategoriesDesarializator())
                    .create()

            val retrofit = Retrofit.Builder()
                    .baseUrl(URL_PUBLIC)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
                    .build()

            return retrofit.create(ChuckFactService::class.java)
        }
    }

    @GET("random")
    fun getRandomJoke(): Observable<ChuckFactResponse>

    @GET("random")
    fun getRandomJoke(@Query("category") category:String): Observable<ChuckFactResponse>

    @GET("categories")
    fun getCategories(): Observable<List<String>>
}