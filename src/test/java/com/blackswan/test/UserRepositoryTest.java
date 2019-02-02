package com.blackswan.test;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRepositoryTest {

    static Logger log = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    List<Long> entityid = new ArrayList<>();
    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    private UserRepository userRepository;

    @Before
    public void createUsers() {
        persistEntity(new User("janed", "Jane", "Doe"));
        persistEntity(new User("johnd", "John", "Doe"));
    }

    private void persistEntity(User user) {
        entityid.add((Long) entityManager.persistAndGetId(user));
    }

    @Test
    public void testFindAllUsers() {
        List<User> findAllUsers = (List<User>) userRepository.findAll();
        log.info(String.valueOf(findAllUsers));
        assertThat(findAllUsers).size().isEqualTo(entityid.size());
    }

    //TODO: use lambda
    @Test
    public void testFindUserByID() {
        Optional<User> user = userRepository.findById(entityid.get(0));
        assert (user.isPresent());

    }

    //TODO: use lambda
    @Test
    public void testUpdateUser() {
        User user = userRepository.findById(entityid.get(0)).get();
        user.setUserName("janeDUpdate");
        userRepository.save(user);
        assertThat(user.getUserName()).isEqualToIgnoringCase("janeDUpdate");
    }

    //TODO: use lambda
    @Test
    public void testDeleteUser() {
        User user = userRepository.findById(entityid.get(0)).get();
        userRepository.delete(user);
        assertThat(userRepository.findById(entityid.get(0)).isPresent()).isEqualTo(false);
    }
}
