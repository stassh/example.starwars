package com.stassh.examples.starwars.home;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ListView;
import android.widget.Toast;
import com.stassh.examples.starwars.R;
import com.stassh.examples.starwars.StarwarsApplication;
import com.stassh.examples.starwars.model.ModelList;
import com.stassh.examples.starwars.model.Person;
import com.stassh.examples.starwars.network.StarwarsService;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import javax.inject.Inject;

public class HomeActivity extends AppCompatActivity {

  ListView listView;
  View footerView;
  private boolean isLoading = false;
  CompositeDisposable disposables = new CompositeDisposable();
  private String nextUrl;

  @Inject
  PersonsAdapter adapter;

  @Inject
  StarwarsService starwarsService;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.home_activity);

    HomeActivityComponent component = DaggerHomeActivityComponent.builder()
        .homeActivityModule(new HomeActivityModule(this))
        .starwarsApplicationComponent(StarwarsApplication.get(this).component())
        .build();

    component.injectHomeActivity(this);

    listView = (ListView) this.findViewById(R.id.home_person_list);
    footerView = LayoutInflater.from(getApplicationContext()).inflate(R.layout.home_list_item_loading, null);

    listView.addFooterView(footerView, null, false);
    listView.setAdapter(adapter);

    listView.setOnItemClickListener((adapterView, view, i, l) -> {
      Person item = (Person) adapterView.getItemAtPosition(i);
    });

    listView.setOnScrollListener(new OnScrollListener() {
      @Override
      public void onScrollStateChanged(AbsListView absListView, int i) {
        //no need to override
      }

      @Override
      public void onScroll(AbsListView absListView, int firstVisibleItem, int visibleItems, int totalItems) {
        if (firstVisibleItem + visibleItems == totalItems && totalItems != 0 && !isLoading) {
          addItems();
        }
      }
    });

    disposables.add(starwarsService.getPersons().subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(
            this::fillAdapter,
            this::showError
        ));
  }

  private void showError(Throwable error) {
    Toast.makeText(HomeActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
  }

  private void fillAdapter(ModelList<Person> next) {
    nextUrl = next.getNext();
    adapter.swapData(next.getPersons());
    listView.removeFooterView(footerView);
    isLoading = false;
  }

  private void addItems() {
    if (nextUrl == null || nextUrl.isEmpty())
      return;
    isLoading = true;
    listView.addFooterView(footerView, null, false);

    disposables.add(starwarsService.get(nextUrl)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(
            this::fillAdapter,
            this::showError));
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
    if (disposables == null)
      return;
    disposables.dispose();
  }
}
