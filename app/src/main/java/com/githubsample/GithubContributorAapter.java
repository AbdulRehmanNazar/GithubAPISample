package com.githubsample;

import java.util.List;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.githubsample.databinding.ContributorItemBinding;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by abdulrehman
 */

public class GithubContributorAapter extends RecyclerView.Adapter {
    private List<GithubContributors> items;
    Context context;

    public GithubContributorAapter(Context context, List<GithubContributors> items) {
        this.context = context;
        this.items = items;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ContributorItemBinding binding;

        public ViewHolder(ContributorItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ContributorItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.contributor_item, parent, false);
        return new GithubContributorAapter.ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        final GithubContributors item = items.get(position);
        if (holder instanceof ViewHolder) {
            ((ViewHolder) holder).binding.name.setText(context.getString(R.string.contributor_adapter_developer_name) + " " + item.getName());
            ((ViewHolder) holder).binding.contributions.setText(context.getString(R.string.contributor_adapter_total_commits) + " " + item.getContributions());
            Picasso.with(context).load(item.getImgURL()).into(((ViewHolder) holder).binding.avatar);

        }

        holder.itemView.setOnClickListener(view -> {
            Toast.makeText(context, context.getString(R.string.contributor_adapter_developer_name) + " " + item.getName() + " " + context.getString(R.string.contributor_adapter_total_commits) + " : " + item.getContributions(), Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void setData(List<GithubContributors> dataList) {
        this.items = dataList;
        notifyDataSetChanged();
    }

}
