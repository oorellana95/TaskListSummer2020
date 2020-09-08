package com.geekhubs.hwtasksListv2.application.domain.entities;
import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "status")
public class StatusEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private String referenceIcon;
    private boolean enabled;

    @OneToMany(mappedBy = "status")
    private Set<TaskEntity> tasks;

    protected StatusEntity() {}

    public StatusEntity(Long id, String name, String description, String referenceIcon, boolean enabled) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.tasks = new HashSet<TaskEntity>();
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

    public Set<TaskEntity> getTasks() {
        return new HashSet<TaskEntity>(this.tasks);
    }
    public void setTasks(Set<TaskEntity> tasks) {
        this.tasks = tasks;
    }

    public String getReferenceIcon() {
        return referenceIcon;
    }
    public void setReferenceIcon(String referenceIcon) {
        this.referenceIcon = referenceIcon;
    }

    public boolean isEnabled() { return enabled; }
    public void setEnabled(boolean enabled) { this.enabled = enabled; }
}
