package com.stassh.examples.starwars.home;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import com.squareup.picasso.Picasso;
import com.stassh.examples.starwars.model.Person;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.inject.Inject;

/**
 * Created by Stanislav Shabalin on 18/09/2017
 * Copyrights (c) 2017. All rights reserved.
 * Last modified 18/09/2017 12:44
 * email:slalom2001@gmail.com
 */

public class PersonsAdapter extends BaseAdapter {

  private final List<Person> personsList = new ArrayList<>(0);
  private final Context context;
  //private final Picasso picasso;

  @Inject
  public PersonsAdapter(HomeActivity context) {
    this.context = context;
    //this.picasso = picasso;
  }

  @Override
  public int getCount() {
    return personsList.size();
  }

  @Override
  public Person getItem(int i) {
    return personsList.get(i);
  }

  @Override
  public long getItemId(int i) {
    return personsList.indexOf(i);
  }

  @Override
  public View getView(int i, View view, ViewGroup viewGroup) {
    PersonItem item;
    if (view == null) {
      item = new PersonItem(context);
    } else {
      item = PersonItem.class.cast(view);
    }

    item.set(personsList.get(i));

    return item;
  }

  public void swapData(Collection<Person> persons) {
    personsList.clear();
    if (persons != null) {
      personsList.addAll(persons);
    }
    notifyDataSetChanged();
  }
}
