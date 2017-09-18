package com.stassh.examples.starwars.home;

import android.content.Context;
import dagger.Module;
import dagger.Provides;
import java.io.File;
import java.util.Timer;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import okhttp3.logging.HttpLoggingInterceptor.Level;
import okhttp3.logging.HttpLoggingInterceptor.Logger;
import timber.log.Timber;

/**
 * Created by Stanislav Shabalin on 18/09/2017
 * Copyrights (c) 2017. All rights reserved.
 * Last modified 18/09/2017 13:53
 * email:slalom2001@gmail.com
 */


@Module(includes = ContextModule.class)
public class NetworkModule {

  @Provides
  @StarwarsApplicationScope
  public HttpLoggingInterceptor loggingInterceptor() {
    HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor(new Logger() {
      @Override
      public void log(String message) {
        Timber.i(message);
      }
    });
    interceptor.setLevel(Level.BASIC);
    return interceptor;
  }

  @Provides
  @StarwarsApplicationScope
  public File file(@ApplicationContext Context context) {
    return new File(context.getCacheDir(), "okhttp_cache");
  }

  @Provides
  @StarwarsApplicationScope
  public Cache cache(File file) {
    return new Cache(file, 10 * 1000 * 1000);
  }

  @Provides
  @StarwarsApplicationScope
  public OkHttpClient okHttpClient(HttpLoggingInterceptor interceptor, Cache cache) {
    return new OkHttpClient.Builder()
        .addInterceptor(interceptor)
        .cache(cache)
        .build();
  }
}
