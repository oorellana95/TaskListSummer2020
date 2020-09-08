package com.geekhubs.hwtasksListv2.application.domain.factories;

import com.geekhubs.hwtasksListv2.application.domain.entities.UserEntity;
import com.geekhubs.hwtasksListv2.application.domain.models.UserModel;
import org.springframework.stereotype.Component;

@Component
public class UserEntityFactory {
    public UserModel createModel (UserEntity entity){
       return new UserModel(entity.getId(),entity.getName(),entity.getLastname(),entity.getEmail(), entity.isEnabled());
    }
}
