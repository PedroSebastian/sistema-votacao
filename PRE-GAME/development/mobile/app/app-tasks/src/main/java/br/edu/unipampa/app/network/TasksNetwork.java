package br.edu.unipampa.app.network;

import java.util.List;

import br.edu.unipampa.app.model.Task;
import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface TasksNetwork {

    @GET("tarefas")
    Observable<List<Task>> getTasks();

    @GET("tarefas/{id}")
    Observable<Task> getTaskBy(@Path("id") Long id);

    @POST("tarefas")
    Observable<Task> create(@Body Task task);

}
