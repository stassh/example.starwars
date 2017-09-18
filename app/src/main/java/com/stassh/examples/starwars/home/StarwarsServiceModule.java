package com.stassh.examples.starwars.home;

import android.net.Network;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.stassh.examples.starwars.network.StarwarsService;
import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Stanislav Shabalin on 18/09/2017
 * Copyrights (c) 2017. All rights reserved.
 * Last modified 18/09/2017 13:47
 * email:slalom2001@gmail.com
 */

@Module(includes = NetworkModule.class)
public class StarwarsServiceModule {

  @Provides
  @StarwarsApplicationScope
  public StarwarsService starwarsService(Retrofit starwarsRetrofit) {
    return starwarsRetrofit.create(StarwarsService.class);
  }

  @Provides
  @StarwarsApplicationScope
  public Gson gson() {
    GsonBuilder gsonBuilder = new GsonBuilder();
    return gsonBuilder.create();
  }

  @Provides
  @StarwarsApplicationScope
  public Retrofit retrofit(OkHttpClient okHttpClient, Gson gson) {
    return new Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create(gson))
        .client(okHttpClient)
        .baseUrl("https://swapi.co/api/")
        .build();
  }
}
