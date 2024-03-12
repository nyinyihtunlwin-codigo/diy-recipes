package projects.nyinyihtunlwin.common.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import projects.nyinyihtunlwin.common.base.UseCaseExecutor
import projects.nyinyihtunlwin.common.base.UseCaseExecutorProvider
import projects.nyinyihtunlwin.common.event.GlobalEvent
import projects.nyinyihtunlwin.common.event.GlobalEventImpl
@Module
@InstallIn(SingletonComponent::class)
object UtilityModule {
    @Singleton
    @Provides
    fun providesUseCaseExecutorProvider(): UseCaseExecutorProvider = ::UseCaseExecutor

    @Singleton
    @Provides
    fun providesGlobalEvent(): GlobalEvent = GlobalEventImpl()
}