package com.geekhubs.hwtasksListv2.application.domain.services;

import com.geekhubs.hwtasksListv2.application.domain.models.TaskModel;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface TaskService {
    @Transactional(readOnly = true)
    List<TaskModel> findAll();

    @Transactional(readOnly = true)
    List<TaskModel> findByKeyword(String keyword);

    @Transactional(readOnly = true)
    TaskModel findById(long id);

    @Transactional(readOnly = true)
    List<TaskModel> findByUserId(long id);

    @Transactional(readOnly = true)
    List<TaskModel> findByUserIdAndKeyword(long id, String keyword);

    void addTask(TaskModel taskModel);

    void changeTaskData(TaskModel taskModel);

    void deleteTask(long taskId);
}
