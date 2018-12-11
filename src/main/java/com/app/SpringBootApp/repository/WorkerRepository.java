package com.app.SpringBootApp.repository;

import com.app.SpringBootApp.domain.Worker;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkerRepository extends JpaRepository<Worker, Long> {

    Worker findOneById (@NonNull Long id);

}
