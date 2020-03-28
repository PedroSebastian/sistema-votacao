package br.edu.unipampa.app.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import br.edu.unipampa.app.R;
import br.edu.unipampa.app.model.Task;

public class TaskAdapter extends ArrayAdapter<Task> {

    public TaskAdapter(Context context, ArrayList<Task> tasks) {
        super(context, 0, tasks);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Task task = this.getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(this.getContext()).inflate(R.layout.item_task, parent, false);
        }

        TextView textViewTitle = (TextView) convertView.findViewById(R.id.task_title);

        textViewTitle.setText(task.getTitle());

        return convertView;
    }

}
