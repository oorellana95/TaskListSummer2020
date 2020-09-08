package com.geekhubs.hwtasksListv2.application.domain.services;

import com.geekhubs.hwtasksListv2.application.domain.models.StatusModel;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface StatusService {
    @Transactional(readOnly = true)
    List<StatusModel> findAll();

    @Transactional(readOnly = true)
    StatusModel findById(long statusId);
}
