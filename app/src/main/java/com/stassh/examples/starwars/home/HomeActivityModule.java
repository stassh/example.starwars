package com.stassh.examples.starwars.home;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Stanislav Shabalin on 18/09/2017
 * Copyrights (c) 2017. All rights reserved.
 * Last modified 18/09/2017 14:39
 * email:slalom2001@gmail.com
 */

@Module
public class HomeActivityModule {
  private final HomeActivity homeActivity;

  public HomeActivityModule(HomeActivity homeActivity) {
    this.homeActivity = homeActivity;
  }

  @Provides
  @HomeActivityScope
  public HomeActivity homeActivity() {
    return homeActivity;
  }
}
