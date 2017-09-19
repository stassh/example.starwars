package com.stassh.examples.starwars.home;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;
import com.stassh.examples.starwars.R;
import com.stassh.examples.starwars.StarwarsApplication;
import com.stassh.examples.starwars.network.StarwarsService;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import javax.inject.Inject;

public class HomeActivity extends AppCompatActivity {

  ListView listView;
  CompositeDisposable disposables = new CompositeDisposable();

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

    listView.setAdapter(adapter);

    disposables.add(starwarsService.getPersons().subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(
            next->adapter.swapData(next.getPersons()),
            error -> Toast.makeText(HomeActivity.this, "Error getting list " + error.getMessage(), Toast.LENGTH_LONG).show()
        ));
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
    if (disposables == null)
      return;
    disposables.dispose();
  }
}
