package com.stassh.examples.starwars.home;

import com.stassh.examples.starwars.network.StarwarsService;
import dagger.Component;

/**
 * Created by Stanislav Shabalin on 18/09/2017
 * Copyrights (c) 2017. All rights reserved.
 * Last modified 18/09/2017 14:24
 * email:slalom2001@gmail.com
 */

@StarwarsApplicationScope
@Component(modules = {StarwarsServiceModule.class, ActivityModule.class, ContextModule.class})
public interface StarwarsApplicationComponent {

  StarwarsService getStarwarsService();
}
