package br.edu.unipampa.app.ui.main;

import android.util.Log;

import java.util.List;

import br.edu.unipampa.app.model.Task;
import br.edu.unipampa.app.network.NetworkClient;
import br.edu.unipampa.app.network.TasksNetwork;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class MainPresenter implements MainPresenterInterface {

    MainViewInterface mainViewInterface;
    private String TAG = "Main Presenter";

    public MainPresenter(MainViewInterface mainViewInterface) {
        this.mainViewInterface = mainViewInterface;
    }

    @Override
    public void getTasks() {
        this.getObservable().subscribeWith(this.getObserver());
    }

    public Observable<List<Task>> getObservable() {
        return NetworkClient.getRetrofit().create(TasksNetwork.class)
                .getTasks()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public DisposableObserver<List<Task>> getObserver() {
        return new DisposableObserver<List<Task>>() {
            @Override
            public void onNext(List<Task> tasks) {
                Log.d("Result", tasks.toString());
                mainViewInterface.displayTasks(tasks);
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "Error: " + e);
            }

            @Override
            public void onComplete() {
                mainViewInterface.hideLoading();
            }
        };
    }

}
