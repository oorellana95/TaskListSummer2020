package com.geekhubs.hwtasksListv2.application.domain.services;

import com.geekhubs.hwtasksListv2.application.domain.entities.UserEntity;
import com.geekhubs.hwtasksListv2.application.domain.factories.TaskEntityFactory;
import com.geekhubs.hwtasksListv2.application.domain.factories.UserEntityFactory;
import com.geekhubs.hwtasksListv2.application.domain.models.TaskModel;
import com.geekhubs.hwtasksListv2.application.domain.models.UserModel;
import com.geekhubs.hwtasksListv2.application.domain.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class UserServiceImplementation implements UserService{
    private final UserRepository userRepository;
    private final TaskEntityFactory taskFactory;
    private final UserEntityFactory userEntityFactory;

    @Autowired
    public UserServiceImplementation(UserRepository userRepository, TaskEntityFactory taskFactory, UserEntityFactory userEntityFactory) {
        this.userRepository = userRepository;
        this.taskFactory = taskFactory;
        this.userEntityFactory = userEntityFactory;
    }

    @Override
    public List<UserModel> findAll() {
        List<UserEntity> entities = this.userRepository.findAll();
        List<UserModel> models = entities.stream().map(object -> {
            return userEntityFactory.createModel(object);
        }).collect(Collectors.toList());

        return models;
    }

    @Override
    public UserModel findById(
            long id) { UserEntity entity = this.userRepository.findById(id)
                .orElseThrow(RuntimeException::new);
            UserModel model = userEntityFactory.createModel(entity);
            Iterable<TaskModel> taskModels = entity.getTasks().stream()
                .filter(task -> task.isEnabled())
                .map(task -> taskFactory.createModel(task))
                .collect(Collectors.toList());

            model.setTasks(taskModels);
        return model;
    }

}
