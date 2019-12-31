package com.rgv.web;

import com.rgv.exception.RecordNotFoundException;
import com.rgv.model.TaskEntity;
import com.rgv.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/tasks")
public class TaskController {
  @Autowired
  TaskService service;

  @GetMapping
  public ResponseEntity<List<TaskEntity>> getAllEmployees() {
    List<TaskEntity> list = service.getAllTasks();

    return new ResponseEntity<List<TaskEntity>>(list, new HttpHeaders(), HttpStatus.OK);
  }

  @GetMapping("/{id}")
  public ResponseEntity<TaskEntity> getEmployeeById(@PathVariable("id") Long id)
      throws RecordNotFoundException {
    TaskEntity entity = service.getTaskById(id);

    return new ResponseEntity<TaskEntity>(entity, new HttpHeaders(), HttpStatus.OK);
  }

  @PostMapping
  public ResponseEntity<TaskEntity> createOrUpdateEmployee(@RequestBody TaskEntity employee)
      throws RecordNotFoundException {
    TaskEntity updated = service.createOrUpdateTask(employee);
    return new ResponseEntity<TaskEntity>(updated, new HttpHeaders(), HttpStatus.OK);
  }

  @DeleteMapping("/{id}")
  public HttpStatus deleteEmployeeById(@PathVariable("id") Long id)
      throws RecordNotFoundException {
    service.deleteTaskById(id);
    return HttpStatus.FORBIDDEN;
  }

}
