package br.edu.unipampa.app.ui.main;

import java.util.List;

import br.edu.unipampa.app.model.Task;

public interface MainViewInterface {

    void showLoading();
    void hideLoading();
    void displayTasks(List<Task> tasks);

}
