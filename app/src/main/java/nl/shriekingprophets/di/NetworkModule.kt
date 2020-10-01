package nl.shriekingprophets.di


import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import nl.shriekingprophets.services.ApiService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

@Module
@InstallIn(ApplicationComponent::class)
object NetworkModule {

    @Provides
    fun retrofit(moshiJsonParser: Moshi, okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .baseUrl("https://shrieking-prophets.azurewebsites.net/")
        .client(okHttpClient)
        .addConverterFactory(MoshiConverterFactory.create(moshiJsonParser))
        .build()


    @Provides
    fun okHttp() = OkHttpClient.Builder().addInterceptor(HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }).build()


    @Provides
    fun moshi(): Moshi = Moshi.Builder()
        .add(InstantAdapter())
        .build()

    @Provides
    fun apiService(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)
}