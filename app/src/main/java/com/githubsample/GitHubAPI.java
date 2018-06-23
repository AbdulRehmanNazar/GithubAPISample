package com.githubsample;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import rx.Observable;


/**
 * Created by abdulrehman
 */

public interface GitHubAPI
{
	String BASE_URL = "https://api.github.com/";

	// https://api.github.com/repos/ruby/ruby/contributors
	@GET("repos/ruby/ruby/contributors")
	Observable<List<GithubContributors>> getRepoContributer();
}
