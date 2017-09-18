package com.stassh.examples.starwars.home;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import javax.inject.Scope;

/**
 * Created by Stanislav Shabalin on 18/09/2017
 * Copyrights (c) 2017. All rights reserved.
 * Last modified 18/09/2017 13:48
 * email:slalom2001@gmail.com
 */

@Scope
@Retention(RetentionPolicy.CLASS)
public @interface StarwarsApplicationScope {
}
