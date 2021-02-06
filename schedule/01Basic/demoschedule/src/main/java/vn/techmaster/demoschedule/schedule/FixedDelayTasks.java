package vn.techmaster.demoschedule.schedule;

import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@EnableScheduling
@ConditionalOnExpression("false")
public class FixedDelayTasks {
  @Scheduled(fixedDelay = 1000)
  public void scheduleFixedDelayTask() {
    System.out.println("Fixed delay task - " + System.currentTimeMillis() / 1000);
  } 
}
