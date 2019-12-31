package core.ds.ds_project_UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Adapter;
import android.widget.PopupMenu;

import java.util.ArrayList;

import core.ds.ds_project.Project;
import core.ds.ds_project.R;
import core.ds.ds_project_UI.ProjectAdapter;
import core.ds.ds_project_UI.ProjectItem;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<ProjectItem> projectList = new ArrayList<>();
        projectList.add(new ProjectItem(new Project(null,"P1")));
        projectList.add(new ProjectItem(new Project(null,"P2")));
        projectList.add(new ProjectItem(new Project(null,"P3")));

        recyclerView = findViewById(R.id.mainProjectsRecyclerView);
        recyclerView.setHasFixedSize(false);
        layoutManager = new LinearLayoutManager(this);
        adapter = new ProjectAdapter(projectList);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
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
        MenuInflater inflater = menu.getMenuInflater();
        inflater.inflate(R.menu.add_activity_menu, menu.getMenu());
        menu.show();
    }
}
