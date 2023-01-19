package com.example.todoapp.repository;

//import com.example.todoapp.ToDoAppApplication;
import com.example.todoapp.entity.ToDo;
//import org.hibernate.dialect.MariaDBDialect;
import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ToDoRepo extends JpaRepository<ToDo, Integer> {


}
