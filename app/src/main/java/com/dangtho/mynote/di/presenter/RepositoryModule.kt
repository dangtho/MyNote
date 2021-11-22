package com.dangtho.mynote.di.presenter

import com.dangtho.mynote.data.Repository.MainRepository
import com.dangtho.mynote.data.api.ApiHelper
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {
}