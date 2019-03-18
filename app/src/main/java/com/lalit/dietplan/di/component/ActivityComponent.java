

package com.lalit.dietplan.di.component;

import com.lalit.dietplan.di.PerActivity;
import com.lalit.dietplan.di.module.ActivityModule;
import com.lalit.dietplan.ui.Main.MainActivity;

import dagger.Component;


@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    void inject(MainActivity mainActivity);


}
