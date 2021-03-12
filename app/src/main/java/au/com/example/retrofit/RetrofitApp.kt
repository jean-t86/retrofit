package au.com.example.retrofit

import android.app.Application
import timber.log.Timber

class RetrofitApp : Application() {
    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}
