package com.astalos.locationregistry.presentation

import android.app.Application
import com.astalos.locationregistry.di.components.ApplicationComponent
import com.astalos.locationregistry.di.components.DaggerApplicationComponent
import com.astalos.locationregistry.di.modules.ApplicationModule

/**
 * @author Tomasz Czura on 9/4/18.
 */
class RegistryApplication : Application() {
    val appComponent: ApplicationComponent by lazy {
        DaggerApplicationComponent
                .builder()
                .applicationModule(ApplicationModule(this))
                .build()
    }

    override fun onCreate() {
        super.onCreate()
        appComponent.inject(this)
    }
}