package com.geekhubs.hwtasksListv2.application.domain.services;

import com.geekhubs.hwtasksListv2.application.domain.entities.TaskEntity;
import com.geekhubs.hwtasksListv2.application.domain.factories.TaskEntityFactory;
import com.geekhubs.hwtasksListv2.application.domain.models.StatusModel;
import com.geekhubs.hwtasksListv2.application.domain.models.TaskModel;
import com.geekhubs.hwtasksListv2.application.domain.models.UserModel;
import com.geekhubs.hwtasksListv2.application.domain.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)

public class TaskServiceImplementation implements TaskService{
    private final TaskRepository taskRepository;
    private final TaskEntityFactory taskFactory;

    @Autowired
    public TaskServiceImplementation(TaskRepository taskRepository, TaskEntityFactory taskFactory) {
        this.taskRepository = taskRepository;
        this.taskFactory = taskFactory;
    }

    @Override
    public List<TaskModel> findAll(){
        return getListModels(this.taskRepository.findAll());
    }

    @Override
    public List<TaskModel> findByKeyword(String keyword){
        return getListModels(this.taskRepository.findByKeyword(keyword));
    }

    @Override
    public List<TaskModel> findByUserId(long id){
        return getListModels(this.taskRepository.findByUserId(id));
    }

    @Override
    public List<TaskModel> findByUserIdAndKeyword(long id, String keyword){
        return getListModels(this.taskRepository.findByUserIdAndKeyword(id, keyword));
    }

    private List<TaskModel> getListModels(List<TaskEntity> entities){
        List<TaskModel> models = entities.stream().map(entity -> {
            return taskFactory.createModel(entity);
        }).collect(Collectors.toList());
        return models;
    }


    @Override
    public TaskModel findById(
            long id) {
        TaskEntity entity = this.taskRepository.findById(id)
                .orElseThrow(RuntimeException::new);
        return taskFactory.createModel(entity);
    }

    @Override
    public void addTask(TaskModel source)
    {
        TaskEntity entity = this.taskFactory.createEntity(new TaskEntity(), source);
        this.taskRepository.saveAndFlush(entity);
    }

    @Override
    public void changeTaskData(TaskModel source){
        TaskEntity entity = this.taskRepository.findById(source.getId())
                .orElseThrow(RuntimeException::new);

        this.taskRepository.saveAndFlush(taskFactory.createEntity(entity, source));
    }

    @Override
    public void deleteTask(long id) {
        TaskEntity entity = this.taskRepository.findById(id)
                .orElseThrow(RuntimeException::new);
        entity.setEnabled(false);
        this.taskRepository.saveAndFlush(entity);
    }
}
