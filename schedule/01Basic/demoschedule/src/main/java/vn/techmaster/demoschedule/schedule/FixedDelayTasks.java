package vn.techmaster.demoschedule.schedule;

import java.util.Random;

import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@EnableScheduling
@ConditionalOnExpression("false")
public class FixedDelayTasks {
  private long timeOfLastTask = System.currentTimeMillis();

  @Scheduled(fixedDelay = 1000)
  public void scheduleFixedDelayTask() throws InterruptedException {
    long delay = System.currentTimeMillis() - timeOfLastTask;
    System.out.println("Fixed delay task - " + delay);

    Random random = new Random();
    int sleepTime = random.nextInt(5) * 1000;
    Thread.sleep(sleepTime); //Giả lập thời gian thực hiện task khác nhau


    timeOfLastTask = System.currentTimeMillis();
  } 
}
