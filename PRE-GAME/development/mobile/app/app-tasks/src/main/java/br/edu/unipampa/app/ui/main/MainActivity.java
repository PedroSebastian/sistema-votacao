package br.edu.unipampa.app.ui.main;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;

import java.util.ArrayList;
import java.util.List;

import br.edu.unipampa.app.R;
import br.edu.unipampa.app.adapters.TaskAdapter;
import br.edu.unipampa.app.model.Task;
import butterknife.BindView;

public class MainActivity extends AppCompatActivity implements MainViewInterface {

    private static final String TAG = "MainActivity";

    @BindView(R.id.fab_update)
    FloatingActionButton floatingActionButton;

    SwipeMenuListView listView;

    MainPresenter mainPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar appToolbar = (Toolbar) findViewById(R.id.app_toolbar);
        setSupportActionBar(appToolbar);

        this.listView = (SwipeMenuListView) findViewById(R.id.app_list_view);

        this.setupMVP();
        this.getTaskList();
    }

    private void setupMVP() {
        this.mainPresenter = new MainPresenter(this);
    }

    private void getTaskList() {
        this.mainPresenter.getTasks();
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void displayTasks(List<Task> tasks) {
        TaskAdapter adapter = new TaskAdapter(this, (ArrayList<Task>) tasks);
        this.listView.setAdapter(adapter);


        SwipeMenuCreator creator = new SwipeMenuCreator() {

            @Override
            public void create(SwipeMenu menu) {
                // create "open" item
                SwipeMenuItem concludeItem = new SwipeMenuItem(
                        getApplicationContext());
                // set item background
                concludeItem.setBackground(new ColorDrawable(Color.rgb(34, 200,
                        218)));
                // set item width
                concludeItem.setWidth(170);
                // set item title
                concludeItem.setIcon(R.drawable.ic_check_white_24dp);
                // add to menu
                menu.addMenuItem(concludeItem);

                // create "delete" item
                SwipeMenuItem editItem = new SwipeMenuItem(
                        getApplicationContext());
                // set item background
                editItem.setBackground(new ColorDrawable(Color.rgb(216,
                        27, 96)));
                // set item width
                editItem.setWidth(170);
                // set a icon
                editItem.setIcon(R.drawable.ic_edit_white_24dp);
                // add to menu
                menu.addMenuItem(editItem);
            }
        };

        listView.setMenuCreator(creator);

        listView.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
                switch (index) {
                    case 0:
                        Log.d(TAG, "onMenuItemClick: clicked item " + index);
                        break;
                    case 1:
                        Log.d(TAG, "onMenuItemClick: clicked item " + index);
                        break;
                }
                // false : close the menu; true : not close the menu
                return false;
            }
        });
    }

    public void updateTaskList(View view) {
        this.mainPresenter.getTasks();
    }

}
