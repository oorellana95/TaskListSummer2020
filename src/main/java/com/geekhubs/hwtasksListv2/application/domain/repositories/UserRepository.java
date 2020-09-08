package com.geekhubs.hwtasksListv2.application.domain.repositories;

import com.geekhubs.hwtasksListv2.application.domain.entities.TaskEntity;
import com.geekhubs.hwtasksListv2.application.domain.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    @Query(value="select e from UserEntity e where e.enabled = true")
    List<UserEntity> findAll ();

}