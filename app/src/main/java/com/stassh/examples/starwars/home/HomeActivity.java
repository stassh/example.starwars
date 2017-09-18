package com.stassh.examples.starwars.home;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;
import com.stassh.examples.starwars.R;
import com.stassh.examples.starwars.StarwarsApplication;
import com.stassh.examples.starwars.model.Persons;
import com.stassh.examples.starwars.network.StarwarsService;
import javax.inject.Inject;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends AppCompatActivity {

  ListView listView;
  Call<Persons> personsCall;

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

    personsCall = starwarsService.getPersons();

    personsCall.enqueue(new Callback<Persons>() {
      @Override
      public void onResponse(Call<Persons> call, Response<Persons> response) {
        adapter.swapData(response.body().getPersons());
      }

      @Override
      public void onFailure(Call<Persons> call, Throwable t) {
        Toast.makeText(HomeActivity.this, "Error getting persons " + t.getMessage(), Toast.LENGTH_LONG).show();
      }
    });

  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
    if (personsCall == null)
      return;
    personsCall.cancel();
  }
}
