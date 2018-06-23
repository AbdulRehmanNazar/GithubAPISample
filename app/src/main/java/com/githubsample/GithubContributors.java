package com.githubsample;

import com.google.gson.annotations.SerializedName;

/**
 * Created by abdulrehman
 */

public class GithubContributors
{
	@SerializedName("login")
	private String name = "";
	@SerializedName("avatar_url")
	private String imgURL = "";
	@SerializedName("contributions")
	private String contributions = "";

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getImgURL()
	{
		return imgURL;
	}

	public void setImgURL(String imgURL)
	{
		this.imgURL = imgURL;
	}

	public String getContributions()
	{
		return contributions;
	}

	public void setContributions(String contributions)
	{
		this.contributions = contributions;
	}
}
