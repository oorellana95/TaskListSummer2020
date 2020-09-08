package com.geekhubs.hwtasksListv2.application.domain.factories;

import com.geekhubs.hwtasksListv2.application.domain.entities.TaskEntity;
import com.geekhubs.hwtasksListv2.application.domain.models.TaskModel;
import com.geekhubs.hwtasksListv2.application.domain.repositories.StatusRepository;
import com.geekhubs.hwtasksListv2.application.domain.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TaskEntityFactory {
    private final UserRepository userRepository;
    private final StatusRepository statusRepository;
    private final UserEntityFactory userEntityFactory;
    private final StatusEntityFactory statusEntityFactory;

    @Autowired
    public TaskEntityFactory(UserRepository userRepository, StatusRepository statusRepository, UserEntityFactory userEntityFactory, StatusEntityFactory statusEntityFactory) {
        this.userRepository = userRepository;
        this.statusRepository = statusRepository;
        this.userEntityFactory = userEntityFactory;
        this.statusEntityFactory = statusEntityFactory;
    }

    public TaskEntity createEntity(TaskEntity entity, TaskModel source){
        entity.setId(source.getId());
        entity.setTitle(source.getTitle());
        entity.setDescription(source.getDescription());
        entity.setCreatedAt(source.getCreatedAt());
        entity.setEnabled(source.isEnabled());

        entity.setUser(this.userRepository.findById(source.getUser().getId())
                .orElseThrow(RuntimeException::new));
        entity.setStatus(this.statusRepository.findById(source.getStatus().getId())
                .orElseThrow(RuntimeException::new));

        return entity;
    }

    public TaskModel createModel(TaskEntity entity){
        return new TaskModel(entity.getId(),entity.getTitle(),entity.getDescription(),entity.getCreatedAt(), userEntityFactory.createModel(entity.getUser()),statusEntityFactory.createModel(entity.getStatus()),entity.isEnabled());
    }
}
