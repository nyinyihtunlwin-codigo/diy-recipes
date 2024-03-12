package projects.nyinyihtunlwin.common.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import projects.nyinyihtunlwin.common.qualifier.ComputationDispatcher
import projects.nyinyihtunlwin.common.qualifier.IoDispatcher
import projects.nyinyihtunlwin.common.qualifier.UIDispatcher

@Module
@InstallIn(SingletonComponent::class)
object DispatcherModule {
    @ComputationDispatcher
    @Provides
    fun providesDefaultDispatcher(): CoroutineDispatcher = Dispatchers.Default

    @IoDispatcher
    @Provides
    fun providesIoDispatcher(): CoroutineDispatcher = Dispatchers.IO

    @UIDispatcher
    @Provides
    fun providesMainDispatcher(): CoroutineDispatcher = Dispatchers.Main
}