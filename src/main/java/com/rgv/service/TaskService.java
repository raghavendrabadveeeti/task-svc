package com.rgv.service;

import com.rgv.exception.RecordNotFoundException;
import com.rgv.model.EmployeeEntity;
import com.rgv.model.TaskEntity;
import com.rgv.repository.EmployeeRepository;
import com.rgv.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

  @Autowired
  TaskRepository repository;

  public List<TaskEntity> getAllTasks() {
    List<TaskEntity> tasks = repository.findAll();

    if (tasks.size() > 0) {
      return tasks;
    } else {
      return new ArrayList<TaskEntity>();
    }
  }

  public TaskEntity getTaskById(Long id) throws RecordNotFoundException {
    Optional<TaskEntity> task = repository.findById(id);

    if (task.isPresent()) {
      return task.get();
    } else {
      throw new RecordNotFoundException("No task record exist for given id");
    }
  }

  public TaskEntity createOrUpdateTask(TaskEntity entity) throws RecordNotFoundException {
/*    Optional<TaskEntity> task = repository.findById(entity.getId());

    if (task.isPresent()) {
      TaskEntity newEntity = task.get();
      newEntity.setId(entity.getId());
      newEntity.setName(entity.getName());
      newEntity.setDescription(entity.getDescription());

      newEntity = repository.save(newEntity);

      return newEntity;
    } else {*/
      entity = repository.save(entity);

      return entity;

  }

  public void deleteTaskById(Long id) throws RecordNotFoundException {
    Optional<TaskEntity> task = repository.findById(id);

    if (task.isPresent()) {
      repository.deleteById(id);
    } else {
      throw new RecordNotFoundException("No task record exist for given id");
    }
  }
}
