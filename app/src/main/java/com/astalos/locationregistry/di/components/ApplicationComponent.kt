package com.astalos.locationregistry.di.components

import com.astalos.locationregistry.di.modules.ApplicationModule
import com.astalos.locationregistry.presentation.RegistryApplication
import com.astalos.locationregistry.presentation.view.fragments.LocationsFragment
import com.astalos.locationregistry.presentation.view.fragments.UsersFragment
import dagger.Component
import javax.inject.Singleton

/**
 * @author Tomasz Czura on 9/4/18.
 */
@Singleton
@Component(modules = [ ApplicationModule::class ])
interface ApplicationComponent {
    fun inject(application: RegistryApplication)
    fun inject(application: UsersFragment)
    fun inject(locationsFragment: LocationsFragment)
}