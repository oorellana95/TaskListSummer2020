package com.geekhubs.hwtasksListv2.application.domain.repositories;

import com.geekhubs.hwtasksListv2.application.domain.entities.StatusEntity;
import com.geekhubs.hwtasksListv2.application.domain.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StatusRepository extends JpaRepository<StatusEntity, Long> {
    @Query(value="select e from StatusEntity e where e.enabled = true")
    List<StatusEntity> findAll ();
}
