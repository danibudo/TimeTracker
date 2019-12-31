package core.ds.ds_project_UI;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import core.ds.ds_project.R;

public class CreateProjectDialog extends AppCompatDialogFragment {

    private EditText editTextProjectName;
    private EditText editTextDescription;
    private ProjectDialogListener listener;
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.create_project_dialog,null);

        builder.setView(view)
                .setTitle("Create new Project")
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String projectName = editTextProjectName.getText().toString();
                        String description = editTextDescription.getText().toString();
                        listener.createProject(projectName,description);
                    }
                });

        editTextProjectName = view.findViewById(R.id.edit_projectName);
        editTextDescription = view.findViewById(R.id.edit_description);

        return builder.create();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        try {
            listener = (ProjectDialogListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + "must implement ProjectDialogListener");
        }
    }

    public interface ProjectDialogListener{

        void createProject(String projectName, String projectDescription);
    }

}
