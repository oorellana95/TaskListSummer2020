package com.geekhubs.hwtasksListv2.application.domain.models;

import com.geekhubs.hwtasksListv2.application.domain.entities.TaskEntity;

public class StatusModel {
    private Long id;
    private String name;
    private String description;
    private Iterable<TaskModel> tasks;
    private String referenceIcon;
    private boolean enabled;

    public StatusModel(){
    }

    public StatusModel(Long id, String name, String description, String referenceIcon, boolean enabled) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.referenceIcon = referenceIcon;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getReferenceIcon() {
        return referenceIcon;
    }
    public void setReferenceIcon(String referenceIcon) {
        this.referenceIcon = referenceIcon;
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
        return name;
    }
}
