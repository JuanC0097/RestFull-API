package com.jcvq.app.rest.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jcvq.app.rest.model.Task;

public interface TodoRepository extends JpaRepository<Task, Long> {

		
}
