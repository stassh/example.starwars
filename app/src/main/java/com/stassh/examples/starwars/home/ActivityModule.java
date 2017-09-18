package com.stassh.examples.starwars.home;

import android.app.Activity;
import android.content.Context;
import dagger.Module;
import dagger.Provides;
import javax.inject.Named;

/**
 * Created by Stanislav Shabalin on 18/09/2017
 * Copyrights (c) 2017. All rights reserved.
 * Last modified 18/09/2017 15:02
 * email:slalom2001@gmail.com
 */

@Module
public class ActivityModule {
  private final Activity context;

  public ActivityModule(Activity context) {
    this.context = context;
  }

  @Provides
  @StarwarsApplicationScope
  @Named("activity_context")
  public Context context() {
    return context;
  }
}
