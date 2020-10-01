package nl.shriekingprophets

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class ShriekingProphetsApp : Application(){

    override fun onCreate() {
        super.onCreate()

        Timber.plant(Timber.DebugTree())
    }
}