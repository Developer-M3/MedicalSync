package com.mthree.medicalsync.di

import android.content.Context
import com.mthree.medicalsync.analytics.ControlAnalyticsHelper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ControlAnalyticsHelperModule {

    @Provides
    @Singleton
    fun provideAnalyticsHelper(
        @ApplicationContext context: Context,
    ): ControlAnalyticsHelper {
        return ControlAnalyticsHelper(context = context)
    }
}
