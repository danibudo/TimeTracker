package core.ds.ds_project_UI;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import core.ds.ds_project.Project;
import core.ds.ds_project.R;
import core.ds.ds_project.Text;

public class ProjectAdapter extends RecyclerView.Adapter<ProjectAdapter.ProjectViewHolder> {

    private ArrayList<ProjectItem> projectList;
    public ProjectAdapter(ArrayList<ProjectItem> projectList) {
        this.projectList = projectList;
    }

    @Override
    public ProjectViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.project_item, parent, false);
        ProjectViewHolder holder = new ProjectViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final ProjectViewHolder holder, final int position) {
        ProjectItem currentItem = projectList.get(position);
        holder.projectName.setText(currentItem.getName());
        holder.projectTotalTime.setText(currentItem.getTime());
    }

    @Override
    public int getItemCount() {
        return projectList.size();
    }

    public static class ProjectViewHolder extends RecyclerView.ViewHolder {
        public TextView projectName;
        public TextView projectTotalTime;

        public ProjectViewHolder(final View itemView) {
            super(itemView);
            projectName = itemView.findViewById(R.id.projectName);
            projectTotalTime = itemView.findViewById(R.id.totalTime);
        }
    }


}
