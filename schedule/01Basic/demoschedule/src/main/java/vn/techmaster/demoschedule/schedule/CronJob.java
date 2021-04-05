package vn.techmaster.demoschedule.schedule;

import java.sql.Timestamp;

import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@EnableScheduling
@ConditionalOnExpression("false")
public class CronJob {
  /*
   second minute hour dayOfMonth month day of week
   1      *      *    *           *     ?

  1 * * * * MON-FRI  --> Giây thứ 1, cách nhau 1 phút
  2021-02-05 22:29:01.004
  2021-02-05 22:30:01.004
  2021-02-05 22:31:01.004

  * * * * * MON-FRI --> Every second

  1-3 * * * * MON-FRI --> Giây thứ 1, 2, 3 trong mỗi phút thì in ra
  */

  // */5 * * * * ? --> Every 5 seconds
  @Scheduled(cron = "*/5 * * * * ?")
  public void cronJobTask() {
    Timestamp timestamp = new Timestamp(System.currentTimeMillis());
    System.out.println(timestamp);
  } 
}
