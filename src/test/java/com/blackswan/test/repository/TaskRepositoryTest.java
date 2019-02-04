package com.blackswan.test.repository;

import com.blackswan.test.model.Task;
import com.blackswan.test.model.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

/**
 * Created by EdsonvanWyk on 02/02/19.
 */

@RunWith(SpringRunner.class)
@DataJpaTest
public class TaskRepositoryTest {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UserRepository userRepository;

    private Long janeUserId;
    private Long johnUserId;

    @Before
    public void createUsers() {
        janeUserId = (Long) entityManager.persistAndGetId(new User("janed", "Jane", "Doe"));
        johnUserId = (Long) entityManager.persistAndGetId(new User("johnd", "John", "Doe"));
        createTasks();
    }

    private void createTasks() {
        Task janeTasks = new Task("TaskJane", "TaskJane Description", new Date());
        userRepository.findById(janeUserId).ifPresent(user -> {
            janeTasks.setUser(user);
        });
        entityManager.persist(janeTasks);
        Task johnTasks = new Task("TaskJohn", "TaskJohn Description", new Date());
        userRepository.findById(johnUserId).ifPresent(user -> {
            johnTasks.setUser(user);
        });
        entityManager.persist(johnTasks);
    }

    @Test
    public void testCreateTask() {

        taskRepository.findByUserId(janeUserId, null).forEach(task -> {
            assertThat(task).isNotNull();
            assertThat(task.getUser().getId()).isEqualTo(janeUserId);
        });
    }

    @Test
    public void testUpdateTask() {
        //Update task description
        //Reuse variable as the assumption that the only/first created task will have the same ID as johnUserId
        taskRepository.findById(johnUserId).map(task -> {
            assertThat(task).isNotNull();
            task.setDescription("Updated Task Description");
            taskRepository.save(task);
            return task.getDescription();
        });

        //Assert Updated task description
        taskRepository.findById(johnUserId).map(task -> {
            assertThat(task.getDescription()).isEqualToIgnoringCase("Updated Task Description");
            return task.getDescription();
        });
    }

    @Test
    public void testDeleteTask() {
        //Remove taskItem
        taskRepository.findByUserId(johnUserId, null).forEach(task -> {
            taskRepository.delete(task);
        });

        //Assert Removal of taskItem
        taskRepository.findByUserId(johnUserId, null).forEach(task -> {
            assertThat(task).isNull();
        });
    }
}
