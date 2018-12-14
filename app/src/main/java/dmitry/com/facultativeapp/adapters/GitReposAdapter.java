package dmitry.com.facultativeapp.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import dmitry.com.facultativeapp.Model.GitHubRepo;
import dmitry.com.facultativeapp.R;

public class GitReposAdapter extends RecyclerView.Adapter<GitReposAdapter.RepoViewHolder> {

    //Адаптер котрый отвечает за отрисовку реплзиториев с гита

    private List<GitHubRepo> gitHubRepoList;

    public GitReposAdapter(List<GitHubRepo> gitHubRepoList) {
        this.gitHubRepoList = gitHubRepoList;
    }

    public class RepoViewHolder extends RecyclerView.ViewHolder {
        TextView nameTV;
        public RepoViewHolder(View itemView) {
            super(itemView);
            nameTV = itemView.findViewById(R.id.itemRepoNameTV);
        }
    }


    @NonNull
    @Override
    public RepoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_repo, parent, false);
        return new RepoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RepoViewHolder holder, int position) {
        holder.nameTV.setText(gitHubRepoList.get(position).getName());
    }

    @Override
    public int getItemCount() {
        if (gitHubRepoList != null) {
            return gitHubRepoList.size();
        } else {
            return 0;
        }
    }
}
