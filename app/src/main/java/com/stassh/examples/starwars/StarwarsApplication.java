package com.stassh.examples.starwars;

import android.app.Activity;
import android.app.Application;
import com.stassh.examples.starwars.home.ContextModule;
import com.stassh.examples.starwars.home.DaggerStarwarsApplicationComponent;
import com.stassh.examples.starwars.home.StarwarsApplicationComponent;
import timber.log.Timber;
import timber.log.Timber.DebugTree;


/**
 * Created by Stanislav Shabalin on 18/09/2017
 * Copyrights (c) 2017. All rights reserved.
 * Last modified 18/09/2017 14:53
 * email:slalom2001@gmail.com
 */


public class StarwarsApplication extends Application {
  private StarwarsApplicationComponent component;

  /**
   *
   * @param activity
   * @return
   */
  public static StarwarsApplication get(Activity activity) {
    return (StarwarsApplication) activity.getApplication();
  }

  @Override
  public void onCreate() {
    super.onCreate();
    Timber.plant(new Timber.DebugTree());

    component = DaggerStarwarsApplicationComponent.builder()
        .contextModule(new ContextModule(this))
        .build();
  }

  public StarwarsApplicationComponent component() {
    return component;
  }
}
