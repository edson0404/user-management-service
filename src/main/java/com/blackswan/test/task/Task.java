
package com.blackswan.test.task;

import com.blackswan.test.user.User;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Task {

//    private final User user;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<TaskItem> taskItems = new ArrayList<TaskItem>();
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /*public Task(final User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }*/

    public List<TaskItem> getTaskItems() {
        return taskItems;
    }

    public void addTask(TaskItem taskItem) {
        taskItems.add(taskItem);
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
    }
}
