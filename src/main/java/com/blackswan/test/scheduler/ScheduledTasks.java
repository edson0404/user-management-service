package com.blackswan.test.scheduler;

import com.blackswan.test.repository.TaskRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;

@Component
@Slf4j
public class ScheduledTasks {

    @Autowired
    TaskRepository taskRepository;

    @Scheduled(fixedRate = 10000)
    @Transactional
    public void cleanOutdatedData() {
        taskRepository.deleteByDateBefore(new Timestamp(System.currentTimeMillis())).forEach(task -> {
            log.info("update the task to done");
        });
    }

}
