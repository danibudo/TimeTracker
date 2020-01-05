package core.ds.ds_project_UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;

import java.util.ArrayList;
import java.util.List;

import core.ds.ds_project.Activity;
import core.ds.ds_project.Project;
import core.ds.ds_project.R;
import core.ds.ds_project.TaskImpl;

public class MainActivity extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener, CreateProjectDialog.ProjectDialogListener {

    private RecyclerView recyclerView;
    private ActivityAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<ActivityItem> activities;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();
        activities = intent.getParcelableArrayListExtra("Activity items");

        createExampleList();
        createRecycledView();

        
    }


    public void createExampleList() {
        activities = new ArrayList<>();
        activities.add(new ActivityItem(new Project(null,"P1")));

        activities.add(new ActivityItem(new Project(null,"P1")));



        activities.add(new ActivityItem(new Project(null,"P2")));
        activities.add(new ActivityItem(new Project(null,"P3")));
        activities.add(new ActivityItem(new TaskImpl(null,"T1")));
    }

    public void createRecycledView() {
        recyclerView = findViewById(R.id.mainProjectsRecyclerView);
        recyclerView.setHasFixedSize(false);
        layoutManager = new LinearLayoutManager(this);
        adapter = new ActivityAdapter(activities);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new ActivityAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {

                Intent intent = new Intent(getBaseContext(),MainActivity.class);
                intent.putParcelableArrayListExtra("projectItems", (ArrayList<? extends Parcelable>) activities.get(position).getActivityItems());

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    public void showProjectPopup(final View v) {
        PopupMenu menu = new PopupMenu(this, v);
        MenuInflater inflater = menu.getMenuInflater();
        inflater.inflate(R.menu.project_menu, menu.getMenu());
        menu.show();
    }
    public void showAddActivityPopup(final View v) {
        PopupMenu menu = new PopupMenu(this, v);
        menu.setOnMenuItemClickListener(this);
        MenuInflater inflater = menu.getMenuInflater();
        inflater.inflate(R.menu.add_activity_menu, menu.getMenu());

        menu.show();
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.createTask:
                return true;
            case R.id.createProject:
                openDialog();
            default:
                return false;
        }
    }

    public void openDialog() {
        CreateProjectDialog projectDialog = new CreateProjectDialog();
        projectDialog.show(getSupportFragmentManager(),"projectDialog");
    }

    @Override
    public void createProject(String projectName, String projectDescription) {

        activities.add(new ActivityItem(new Project(null, projectName))); //Falta la descripcion!!

    }

}