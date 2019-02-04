package com.blackswan.test.controller;

import com.blackswan.test.model.Task;
import com.blackswan.test.repository.TaskRepository;
import com.blackswan.test.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Created by EdsonvanWyk on 02/02/19.
 */

@RestController
public class TaskController {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("${spring.data.rest.base-path}/user/{userId}/task")
    public Page<Task> getAllTasksForUserId(@PathVariable(value = "userId") Long userId, Pageable pageable) {
        return taskRepository.findByUserId(userId, pageable);
    }

    @GetMapping("${spring.data.rest.base-path}/user/{userId}/task/{taskId}")
    public Task getTaskByIdAndUserId(@PathVariable(value = "userId") Long userId,
                                           @PathVariable(value = "taskId") Long taskId){
        //TODO: check first if present
        return taskRepository.findByIdAndUserId(taskId,userId).get();
    }

    @PostMapping("${spring.data.rest.base-path}/user/{userId}/task")
    public Task createTask(@PathVariable(value = "userId") Long userId,
                           @Valid @RequestBody Task task) {
        return userRepository.findById(userId).map(user -> {
            task.setUser(user);
            return taskRepository.save(task);
        }).orElseThrow(() -> new ResourceNotFoundException("UserId " + userId + " not found"));
    }

    @PutMapping("${spring.data.rest.base-path}/user/{userId}/task/{taskId}")
    public Task updateTask(@PathVariable(value = "userId") Long userId,
                           @PathVariable(value = "taskId") Long taskId,
                           @Valid @RequestBody Task taskRequest) {
        if (!userRepository.existsById(userId))
            throw new ResourceNotFoundException("UserId " + userId + " not found");

        return taskRepository.findById(taskId).map(task -> {
            task.setDescription(taskRequest.getDescription() != null ? taskRequest.getDescription() : task.getDescription());
            task.setName(taskRequest.getName() != null ? taskRequest.getName() : task.getName());
            task.setDate(taskRequest.getDate() != null ? taskRequest.getDate() : task.getDate());
            return taskRepository.save(task);
        }).orElseThrow(() -> new ResourceNotFoundException("TaskId " + taskId + "not found"));
    }

    @DeleteMapping("${spring.data.rest.base-path}/user/{userId}/task/{taskId}")
    public ResponseEntity<?> deleteTask(@PathVariable(value = "userId") Long userId,
                                        @PathVariable(value = "taskId") Long taskId) {
        return taskRepository.findByIdAndUserId(taskId, userId).map(task -> {
            taskRepository.delete(task);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("Task not found with UserId " + userId + " and taskId " + taskId));
    }
}
