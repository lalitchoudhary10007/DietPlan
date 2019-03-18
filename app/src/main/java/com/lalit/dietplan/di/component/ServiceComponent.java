

package com.lalit.dietplan.di.component;

import com.lalit.dietplan.di.PerService;
import com.lalit.dietplan.di.module.ServiceModule;

import dagger.Component;


@PerService
@Component(dependencies = ApplicationComponent.class, modules = ServiceModule.class)
public interface ServiceComponent {

//    void inject(SyncService service);

}
