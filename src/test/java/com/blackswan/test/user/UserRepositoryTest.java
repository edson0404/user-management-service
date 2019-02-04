package com.blackswan.test.user;

import com.blackswan.test.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    private UserRepository userRepository;

    private User jane;
    private User john;

    @Before
    public void createUsers() {
        jane = new User("janed", "Jane", "Doe");
        entityManager.persist(jane);
        john = new User("johnd", "John", "Doe");
        entityManager.persist(john);
    }

    @Test
    public void testFindAllUsers() {
        assertThat(userRepository.findAll()).containsAnyOf(jane);
    }

    @Test
    public void testFindUserByID() {
        userRepository.findById(john.getId()).ifPresent(user -> {
            assertThat(user).isEqualTo(john);
        });
    }

    @Test
    public void testUpdateUser() {
        //Update userName for jane
        userRepository.findById(jane.getId()).ifPresent(user -> {
            user.setUserName("janeDUpdate");
            userRepository.save(user);
        });

        //Assert userName update for jane
        userRepository.findById(jane.getId()).ifPresent(user -> {
            assertThat(user.getUserName()).isEqualToIgnoringCase("janeDUpdate");
        });
    }

    @Test
    public void testDeleteUser() {
        //Find user and delete
        Optional<User> user = userRepository.findById(john.getId());
        if (user.isPresent())
            userRepository.delete(user.get());
        else
            fail("User not found");

        //assert deletion of user
        assertThat(userRepository.findById(john.getId()).isPresent()).isFalse();
    }
}

