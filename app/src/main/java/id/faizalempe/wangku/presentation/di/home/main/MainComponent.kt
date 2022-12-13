package id.faizalempe.wangku.presentation.di.home.main

import dagger.Component
import id.faizalempe.wangku.di.scope.PerActivity
import id.faizalempe.wangku.di.component.ApplicationComponent
import id.faizalempe.wangku.presentation.screen.home.main.MainFragment

/**
 * @author Faizal Muhammad Priyowivowo (faizal.priyowibowo@dana.id)
 * @version MainComponent, v 0.1 12/12/22 18.52 by Faizal Muhammad Priyowivowo
 */
@PerActivity
@Component(
    dependencies = [ApplicationComponent::class],
    modules = [MainModule::class]
)
interface MainComponent {

    fun inject(mainFragment: MainFragment)

}