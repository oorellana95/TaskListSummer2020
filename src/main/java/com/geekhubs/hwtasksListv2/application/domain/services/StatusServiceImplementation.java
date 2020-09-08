package com.geekhubs.hwtasksListv2.application.domain.services;


import com.geekhubs.hwtasksListv2.application.domain.entities.StatusEntity;
import com.geekhubs.hwtasksListv2.application.domain.models.StatusModel;
import com.geekhubs.hwtasksListv2.application.domain.repositories.StatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class StatusServiceImplementation implements StatusService{
    private final StatusRepository statusRepository;

    @Autowired
    public StatusServiceImplementation(StatusRepository statusRepository) {
        this.statusRepository = statusRepository;
    }

    @Override
    public List<StatusModel> findAll() {
        List<StatusEntity> entities = this.statusRepository.findAll();
        List<StatusModel> models = entities.stream().map(object -> {
            return statusEntityToModel(object);
        }).collect(Collectors.toList());

        return models;
    }

    @Override
    public StatusModel findById(
            long id) {
        StatusEntity entity = this.statusRepository.findById(id)
                .orElseThrow(RuntimeException::new);
        StatusModel model = statusEntityToModel(entity);
        return model;
    }

    private StatusModel statusEntityToModel(StatusEntity entity){
        StatusModel model = new StatusModel();
        model.setId(entity.getId());
        model.setName(entity.getName());
        model.setDescription(entity.getDescription());
        model.setEnabled(entity.isEnabled());
        return model;
    }
}
