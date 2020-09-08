package com.geekhubs.hwtasksListv2.application.domain.services;

import com.geekhubs.hwtasksListv2.application.domain.models.UserModel;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UserService {
    @Transactional(readOnly = true)
    List<UserModel> findAll();

    @Transactional(readOnly = true)
    UserModel findById(long userId);
}
