
package com.blackswan.test.task;

import com.blackswan.test.user.User;
import com.blackswan.test.user.User.UserId;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Task {

    private final UserId userId;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<TaskItem> taskItems = new ArrayList<TaskItem>();

    public Task(final UserId userId) {
        this.userId = userId;
    }

    public List<TaskItem> getTaskItems() {
        return taskItems;
    }

    public void addTask(TaskItem taskItem) {
        taskItems.add(taskItem);
    }

    public UserId getUserId() {
        return userId;
    }

    public Long getId() {
        return id;
    }

    //static to be instantiated outside of the enclosing class.
    @Entity
    public static class TaskItem {

        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private Long id;

        private String name;
        private String description;
        private Date date;

        public TaskItem(String name, String description, Date date) {
            this.name = name;
            this.description = description;
            this.date = date;
        }

        public String getName() {
            return name;
        }

        public String getDescription() {
            return description;
        }

        public Date getDate() {
            return date;
        }

        public Long getId(){
            return id;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public void setDate(Date date) {
            this.date = date;
        }
    }
}
