package com.muazzeznihalbahadir.muzikoneriuygulamasi.di

import android.annotation.SuppressLint
import android.content.Context
import android.media.AudioAttributes
import com.google.android.exoplayer2.C
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.google.android.exoplayer2.util.Util
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ServiceComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ServiceScoped

@Module
@InstallIn(ServiceComponent::class)
object ServiceModule {

    @SuppressLint("WrongConstant")
    @ServiceScoped
    @Provides
    fun provideAudioAttributes(): AudioAttributes? = AudioAttributes.Builder()
        .setContentType(C.CONTENT_TYPE_MUSIC)
        .setUsage(C.USAGE_MEDIA)
        .build()

    @ServiceScoped
    @Provides
    fun provideExoPlayer(
        @ApplicationContext context: Context,
        audioAttributes: AudioAttributes //nasıl kullanacağını bul
    ) = ExoPlayer.Builder(context).build().apply {
        setAudioAttributes(this.audioAttributes,true)//this kısmına gelecek şeyi bul fun dakini kullan
        setHandleAudioBecomingNoisy(true)
    }

    @ServiceScoped
    @Provides
    fun provideDataSourceFactory(
        @ApplicationContext context: Context
    ) = DefaultDataSourceFactory(context, Util.getUserAgent(context,"Petrichor Music"))


}