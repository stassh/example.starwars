package com.stassh.examples.starwars.network;

import com.stassh.examples.starwars.model.ModelList;
import com.stassh.examples.starwars.model.Person;
import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Stanislav Shabalin on 18/09/2017
 * Copyrights (c) 2017. All rights reserved.
 * Last modified 18/09/2017 13:39
 * email:slalom2001@gmail.com
 */

public interface StarwarsService {

  @GET("people/")
  Observable<ModelList<Person>> getPersons();
}
