package com.githubsample;

import android.app.ProgressDialog;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.githubsample.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by abdulrehman
 */


public class MainActivity extends AppCompatActivity {
    private ProgressDialog pDialog;
    GithubContributorAapter adapter;
    List<GithubContributors> dataList;
    ActivityMainBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        pDialog = new ProgressDialog(MainActivity.this);
        pDialog.setMessage(getString(R.string.main_activity_getting_data));
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(true);
        pDialog.show();

        dataList = new ArrayList<>();
        binding.githubRepoUsersList.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        adapter = new GithubContributorAapter(MainActivity.this, dataList);
        binding.githubRepoUsersList.setAdapter(adapter);
        getGithubUsers();

    }

    public void getGithubUsers() {
        Retrofit retrofit = new Retrofit.Builder().addCallAdapterFactory(RxJavaCallAdapterFactory.create()).addConverterFactory(GsonConverterFactory.create()).baseUrl(GitHubAPI.BASE_URL).build();
        GitHubAPI gitHubAPIService = retrofit.create(GitHubAPI.class);
        gitHubAPIService.getRepoContributer().subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(githubContributors -> {
            pDialog.dismiss();
            adapter.setData(githubContributors);
        }, throwable -> {
            pDialog.dismiss();
            Toast.makeText(MainActivity.this, throwable.getMessage(), Toast.LENGTH_SHORT).show();
        });
    }
}
