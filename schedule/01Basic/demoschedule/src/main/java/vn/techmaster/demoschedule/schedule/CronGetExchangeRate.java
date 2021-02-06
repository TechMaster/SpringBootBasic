package vn.techmaster.demoschedule.schedule;

import java.sql.Timestamp;

import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@EnableScheduling
@ConditionalOnExpression("true")
public class CronGetExchangeRate {
  https://api.exchangeratesapi.io/latest
  http://data.fixer.io/api/latest?access_key=36cafa879f0e2f761021d4398d5bd306&format=1
  https://www.baeldung.com/spring-5-webclient
}
