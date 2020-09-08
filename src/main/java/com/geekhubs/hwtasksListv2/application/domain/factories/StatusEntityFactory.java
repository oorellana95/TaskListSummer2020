package com.geekhubs.hwtasksListv2.application.domain.factories;


import com.geekhubs.hwtasksListv2.application.domain.entities.StatusEntity;
import com.geekhubs.hwtasksListv2.application.domain.models.StatusModel;
import org.springframework.stereotype.Component;

@Component
public class StatusEntityFactory {
    public StatusModel createModel (StatusEntity entity){
        return new StatusModel(entity.getId(), entity.getName(), entity.getDescription(), entity.getReferenceIcon(), entity.isEnabled());
    }
}
