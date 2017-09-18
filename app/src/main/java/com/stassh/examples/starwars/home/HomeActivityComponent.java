package com.stassh.examples.starwars.home;

import dagger.Component;

/**
 * Created by Stanislav Shabalin on 18/09/2017
 * Copyrights (c) 2017. All rights reserved.
 * Last modified 18/09/2017 14:41
 * email:slalom2001@gmail.com
 */


@HomeActivityScope
@Component(modules = HomeActivityModule.class, dependencies = StarwarsApplicationComponent.class)
public interface HomeActivityComponent {
  void injectHomeActivity(HomeActivity homeActivity);
}
