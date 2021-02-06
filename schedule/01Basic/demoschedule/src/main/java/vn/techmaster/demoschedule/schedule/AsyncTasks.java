package vn.techmaster.demoschedule.schedule;

import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@EnableAsync
@EnableScheduling
@ConditionalOnExpression("false")
@Component
public class AsyncTasks {
  @Async
  @Scheduled(fixedRate = 1000)
  public void scheduleFixedRateTaskAsync() throws InterruptedException {
    System.out.println("Fixed rate task async - " + System.currentTimeMillis() / 1000);
    Thread.sleep(500);
  }
}
