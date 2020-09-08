package com.geekhubs.hwtasksListv2.application.domain.repositories;

import com.geekhubs.hwtasksListv2.application.domain.entities.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface TaskRepository extends JpaRepository<TaskEntity, Long> {

    @Query(value="select e from TaskEntity e where e.enabled = true")
    List<TaskEntity> findAll ();

    @Query(value="select e from TaskEntity e where e.enabled = true and (e.title like %:keyword% or e.user.name like %:keyword% or e.user.lastname like %:keyword%)")
    List<TaskEntity> findByKeyword (@Param("keyword") String keyword);

    @Query(value="select e from TaskEntity e where e.enabled = true and e.user.id = :id")
    List<TaskEntity> findByUserId (@Param("id") long idUser);

    @Query(value="select e from TaskEntity e where e.enabled = true and e.user.id = :id and e.title like %:keyword%")
    List<TaskEntity> findByUserIdAndKeyword (@Param("id") long idUser, @Param("keyword") String keyword);
}
