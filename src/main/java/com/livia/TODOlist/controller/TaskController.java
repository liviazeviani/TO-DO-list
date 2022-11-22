package com.livia.TODOlist.controller;

import com.livia.TODOlist.model.Task;
import com.livia.TODOlist.service.TaskService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor
public class TaskController {

    TaskService taskService;

    @ApiOperation(value = "Creating a new task")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Task was successfully created"),
            @ApiResponse(code = 500, message = "Oh no, there was an error creating the task")
    })
    @PostMapping("/tasks")
    @ResponseStatus(HttpStatus.CREATED)
    public Task createtask(@RequestBody Task task){
        return taskService.createTask(task);
    }

    @ApiOperation(value = "Listing all the tasks")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Tasks were successfully listed"),
            @ApiResponse(code = 500, message = "Oh no, there was an error listing the tasks")
    })
    @GetMapping("/tasks")
    @ResponseStatus(HttpStatus.OK)
    public List<Task> getAllTasks(){
        return taskService.listAllTasks();
    }

    @ApiOperation(value = "Getting the task by id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Task was successfully found"),
            @ApiResponse(code = 404, message = "Oh no, there was an error finding the task")
    })
    @GetMapping("/tasks/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Task> getById(@PathVariable (value = "id") Long id){
        return taskService.findById(id);
    }

    @ApiOperation(value = "Updating a task")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Task was successfully updated"),
            @ApiResponse(code = 400, message = "Oh no, there was an error updating the task")
    })
    @PutMapping("/tasks/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Task> updateTaskById(@PathVariable (value = "id") Long id, @RequestBody Task task){
        return taskService.updateTaskById(task, id);
    }

    @ApiOperation(value = "Deleting a task")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Task was successfully deleted"),
            @ApiResponse(code = 400, message = "Oh no, there was an error deleting the task")
    })
    @DeleteMapping("/tasks/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Object> deleteById(@PathVariable (value = "id") Long id){
        return taskService.deleteBYId(id);
    }

}
