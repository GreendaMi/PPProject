package com.greendami.server

import android.util.Log
import com.orhanobut.logger.Logger
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import top.greendami.ppproject.ppproject.BuildConfig
import java.util.concurrent.TimeUnit


/**
 * Created by GreendaMi on 2017/8/15.
 */
class ServerUtil {
    companion object {
        @JvmField
        var retrofit = Retrofit.Builder()
                .baseUrl("")
                .addConverterFactory(GsonConverterFactory.create())
                .client(getOkHttpClient())
                .build()

        @JvmField
        var retrofitService = retrofit.create(Server::class.java)


        private fun getOkHttpClient(): OkHttpClient {
            if (!BuildConfig.DEBUG) {
                return OkHttpClient().newBuilder().connectTimeout(30, TimeUnit.SECONDS).readTimeout(30, TimeUnit.SECONDS).build()
            }
            //日志显示级别
            val level = HttpLoggingInterceptor.Level.BODY
            //新建log拦截器
            val loggingInterceptor = HttpLoggingInterceptor(HttpLoggingInterceptor.Logger { message ->

                if (message.startsWith("{")) {
                    //打印返回数据
                    Logger.json(message)
                } else {
                    if (message.startsWith("--> POST") || message.startsWith("--> GET")) {
                        //打印请求数据
                        Logger.d(message)
                    } else {
                        Log.d("TAg", message)
                    }
                }
            })
            loggingInterceptor.level = level
            //定制OkHttp
            val httpClientBuilder = OkHttpClient.Builder()
            //OkHttp进行添加拦截器loggingInterceptor
            httpClientBuilder.addInterceptor(loggingInterceptor).connectTimeout(30, TimeUnit.SECONDS).readTimeout(30, TimeUnit.SECONDS)
            return httpClientBuilder.build()
        }
    }


}

interface Server {
//    @GET("learnManagementAction!myStudy.action")
//    fun myStudy(@Query("userId") userId: String): Call<entity_base_obj<T>>
//
//
//    @FormUrlEncoded
//    @POST("visitJobNoteAction!insertWorkNoteRaiseFlag.action")
//    fun insertWorkNoteRaiseFlag(@Field("userId") userId: String, @Field("type") type: String, @Field("title") title: String, @Field("time") time: String, @Field("address") address: String, @Field("persons") persons: String, @Field("summary") summary: String, @Field("publishtype") publishtype: String): Call<entity_base_obj<T>>

}

