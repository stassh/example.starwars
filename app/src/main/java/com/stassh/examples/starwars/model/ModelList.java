package com.stassh.examples.starwars.model;

/**
 * Created by Stanislav Shabalin on 18/09/2017
 * Copyrights (c) 2017. All rights reserved.
 * Last modified 18/09/2017 12:30
 * email:slalom2001@gmail.com
 */

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class ModelList<T> {

  @SerializedName("count")
  @Expose
  private Integer count;
  @SerializedName("next")
  @Expose
  private String next;
  @SerializedName("previous")
  @Expose
  private Object previous;

  @SerializedName("results")
  @Expose
  private List<T> persons = null;

  public Integer getCount() {
    return count;
  }

  public void setCount(Integer count) {
    this.count = count;
  }

  public String getNext() {
    return next;
  }

  public void setNext(String next) {
    this.next = next;
  }

  public Object getPrevious() {
    return previous;
  }

  public void setPrevious(Object previous) {
    this.previous = previous;
  }

  public List<T> getPersons() {
    return persons;
  }

  public void setPersons(List<T> persons) {
    this.persons = persons;
  }
}
