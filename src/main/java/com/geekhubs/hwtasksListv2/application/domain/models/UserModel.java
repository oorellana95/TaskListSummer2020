package com.geekhubs.hwtasksListv2.application.domain.models;

import com.geekhubs.hwtasksListv2.application.domain.entities.TaskEntity;

import java.util.Set;

public class UserModel {
    private Long id;
    private String name;
    private String lastname;
    private String email;
    private boolean enabled;
    private Iterable<TaskModel> tasks;

    public UserModel(){};

    public UserModel(Long id, String name, String lastname, String email, boolean enabled) {
        this.id = id;
        this.name = name;
        this.lastname = lastname;
        this.email = email;
        this.enabled = enabled;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Iterable<TaskModel> getTasks() {
        return tasks;
    }

    public void setTasks(Iterable<TaskModel> tasks) {
        this.tasks = tasks;
    }

    @Override
    public String toString() {
        return name + ' ' + lastname;
    }
}
