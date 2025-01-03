package org.cartify.ecommerce.di

import android.app.Application
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import com.google.firebase.messaging.FirebaseMessaging
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.tasks.await
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.cartify.ecommerce.BuildConfig
import org.cartify.ecommerce.common.Constants.JWT_TOKEN
import org.cartify.ecommerce.common.Constants.SHARED_PREF_NAME
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    fun provideSharedPreference(app:Application):SharedPreferences{
        return app.getSharedPreferences(SHARED_PREF_NAME,MODE_PRIVATE)
    }

    @Provides
    fun provideOkHttpClient(sharedPreferences: SharedPreferences):OkHttpClient{
        val httpInterceptor=HttpLoggingInterceptor().apply {
            this.level=HttpLoggingInterceptor.Level.BODY
        }
        return OkHttpClient.Builder()
             .addInterceptor{
                 val key=sharedPreferences.getString(JWT_TOKEN,"")
                 val newRequest=it.request().newBuilder().header(
                     name="Authorization",
                     value = "Bearer $key"
                 ).build()
                 it.proceed(newRequest)
             }
            .addInterceptor(httpInterceptor)
            .connectTimeout(3, TimeUnit.SECONDS)
            .readTimeout(20, TimeUnit.SECONDS)
            .writeTimeout(25, TimeUnit.SECONDS)
            .build()
    }

    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient):Retrofit{
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(okHttpClient)
            .build()
    }

}