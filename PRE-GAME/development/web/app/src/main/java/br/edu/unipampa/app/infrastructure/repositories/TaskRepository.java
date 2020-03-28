package br.edu.unipampa.app.infrastructure.repositories;

import br.edu.unipampa.app.domain.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

}
