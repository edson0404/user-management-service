/*
package com.blackswan.test.task;

import com.blackswan.test.user.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;


@RunWith(SpringRunner.class)
@DataJpaTest
public class TaskRepositoryTest {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private TestEntityManager entityManager;


    @Before
    public void createUsers() {
        entityManager.persist(new User("janed", "Jane", "Doe"));
        entityManager.persist(new User("johnd", "John", "Doe"));
        createTasks();
    }

    private void createTasks() {
        Task janeTasks = new Task(,new TaskItem("TaskJane", "TaskJane Description", new Date()));
        entityManager.persist(janeTasks);
        Task johnTasks = new Task(johnUserId);
        johnTasks.addTask(new TaskItem("TaskJohn", "TaskJohn Description", new Date()));
        entityManager.persist(johnTasks);
    }

    @Test
    public void testCreateTask() {
        taskRepository.findTaskByUserId(0).forEach(task -> {
            assertThat(task).isNotNull();
            assertThat(task.getUserId()).isEqualTo(janeUserId);
        });
    }

    */
/*@Test
    public void testUpdateTask() {
        //Update task description
        taskRepository.findTaskByUserId(johnUserId).forEach(task -> {
            assertThat(task).isNotNull();
            task.getTaskItems().forEach(taskItem -> {
                taskItem.setDescription("Updated Task Description");
                taskRepository.save(task);
            });
        });

        //Assert Updated task description
        taskRepository.findTaskByUserId(johnUserId).forEach(task -> {
            task.getTaskItems().forEach(taskItem -> {
                taskItem.getDescription().equalsIgnoreCase("Updated Task Description");
            });
        });
    }

    @Test
    public void testDeleteTask() {
        //Remove taskItem
        taskRepository.findTaskByUserId(johnUserId).forEach(task -> {
            task.getTaskItems().removeIf(taskItem -> taskItem.getId() == taskItem.getId());
            taskRepository.save(task);
        });

        //Assert Removal of taskItem
        taskRepository.findTaskByUserId(johnUserId).forEach(task -> {
            assertThat(task.getTaskItems()).isEmpty();
        });
    }*//*


}
*/
