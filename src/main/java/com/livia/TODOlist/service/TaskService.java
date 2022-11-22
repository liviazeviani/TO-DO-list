package com.livia.TODOlist.service;

import com.livia.TODOlist.model.Task;
import com.livia.TODOlist.repository.TaskRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@AllArgsConstructor
public class TaskService {


    private TaskRepository taskRepository;

    public Task createTask (Task task){
        return taskRepository.save(task);
    }

    public List<Task> listAllTasks(){
        return taskRepository.findAll();
    }

    public ResponseEntity<Task> findById(Long id){
        return taskRepository.findById(id)
                .map(task -> ResponseEntity.ok().body(task))
                .orElse(ResponseEntity.notFound().build());
    }

    public ResponseEntity<Task> updateTaskById(Task task, Long id){
        return taskRepository.findById(id)
                .map(taskUpdate -> {
                    taskUpdate.setTitle(task.getTitle());
                    taskUpdate.setDetails(task.getDetails());
                    taskUpdate.setDeadLine(task.getDeadLine());
                    Task updated = taskRepository.save(taskUpdate);
                    return ResponseEntity.ok().body(updated);
                }).orElse(ResponseEntity.badRequest().build());
    }

    public ResponseEntity<Object> deleteBYId (Long id){
        return taskRepository.findById(id)
                .map(taskDelete ->{
                    taskRepository.deleteById(id);
                return  ResponseEntity.noContent().build();
                }).orElse(ResponseEntity.badRequest().build());
    }

}
