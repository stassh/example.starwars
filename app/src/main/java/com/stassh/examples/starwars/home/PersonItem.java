package com.stassh.examples.starwars.home;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.widget.FrameLayout;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import com.stassh.examples.starwars.R;
import com.stassh.examples.starwars.model.Person;

/**
 * Created by Stanislav Shabalin on 18/09/2017
 * Copyrights (c) 2017. All rights reserved.
 * Last modified 18/09/2017 15:40
 * email:slalom2001@gmail.com
 */


public class PersonItem extends CardView {
  TextView name;

  public void set(Person person) {
    name.setText(person.getName());
  }

  public PersonItem(@NonNull Context context) {
    super(context);
    inflate(context, R.layout.home_list_item, this);
    bind();
  }

  private void bind() {
    name = (TextView) this.findViewById(R.id.person_name);
  }
}
