package com.example.testovoe3
import android.app.Application
import com.onesignal.OneSignal

const val ONESIGNAL_APP_ID = "94ce2e58-9888-4039-8fef-08e8ac5d71ca"

class ApplicationClass : Application() {
    override fun onCreate() {
        super.onCreate()

        // Logging set to help debug issues, remove before releasing your app.
        OneSignal.setLogLevel(OneSignal.LOG_LEVEL.VERBOSE, OneSignal.LOG_LEVEL.NONE)

        // OneSignal Initialization
        OneSignal.initWithContext(this)
        OneSignal.setAppId(ONESIGNAL_APP_ID)
    }
}