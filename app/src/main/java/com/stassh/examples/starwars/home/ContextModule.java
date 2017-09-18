package com.stassh.examples.starwars.home;

import android.content.Context;
import dagger.Module;
import dagger.Provides;

/**
 * Created by Stanislav Shabalin on 18/09/2017
 * Copyrights (c) 2017. All rights reserved.
 * Last modified 18/09/2017 14:03
 * email:slalom2001@gmail.com
 */

@Module
public class ContextModule {

  private final Context context;

  public ContextModule(Context context) {
    this.context = context.getApplicationContext();
  }

  @Provides
  @StarwarsApplicationScope
  @ApplicationContext
  public Context context() {
    return context;
  }

}
