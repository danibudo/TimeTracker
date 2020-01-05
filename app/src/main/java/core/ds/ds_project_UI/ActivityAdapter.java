package core.ds.ds_project_UI;

import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import core.ds.ds_project.Activity;
import core.ds.ds_project.R;

public class ActivityAdapter extends RecyclerView.Adapter {

    private ArrayList<ActivityItem> activities;

    public ActivityAdapter(ArrayList<ActivityItem> activities) {
        this.activities = activities;
    }


    @Override
    public int getItemViewType(int position) {

        if(activities.get(position).isProject()) {
            return 0;
        }
        return 1;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view;

        if (viewType == 0) { //Project
            view = layoutInflater.inflate(R.layout.project_item, parent, false);
            return new ProjectViewHolder(view);
        }

        view = layoutInflater.inflate(R.layout.task_item, parent, false);
        return new TaskViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        if(activities.get(position).isProject()) {

            ProjectViewHolder projectViewHolder = (ProjectViewHolder) holder;

            projectViewHolder.projectName.setText(activities.get(position).getName());
            projectViewHolder.projectTotalTime.setText(activities.get(position).getDuration());

        } else {

            TaskViewHolder taskViewHolder = (TaskViewHolder) holder;

            taskViewHolder.taskName.setText(activities.get(position).getName());
            taskViewHolder.taskTotalTime.setText(activities.get(position).getDuration());
        }
    }

    @Override
    public int getItemCount() {
        return activities.size();
    }

    class ProjectViewHolder extends RecyclerView.ViewHolder {

        TextView projectName;
        TextView projectTotalTime;
        ImageView projectIcon;
        ImageButton projectButton;

        public ProjectViewHolder(@NonNull View itemView) {
            super(itemView);

            projectName = itemView.findViewById(R.id.projectName);
            projectTotalTime = itemView.findViewById(R.id.projectTotalTime);
            projectIcon = itemView.findViewById(R.id.projectIcon);
            projectButton = itemView.findViewById(R.id.projectButton);
        }
    }

    class TaskViewHolder extends RecyclerView.ViewHolder {

        TextView taskName;
        TextView taskTotalTime;
        ImageView taskIcon;
        ImageButton taskButton;
        Switch taskSwitch;

        public TaskViewHolder(@NonNull View itemView) {
            super(itemView);

            taskName = itemView.findViewById(R.id.taskName);
            taskTotalTime = itemView.findViewById(R.id.taskTotalTime);
            taskIcon = itemView.findViewById(R.id.taskIcon);
            taskButton = itemView.findViewById(R.id.taskButton);
            taskSwitch = itemView.findViewById(R.id.taskSwitch);

        }
    }
}
