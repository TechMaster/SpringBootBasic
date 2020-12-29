package vn.techmaster.bmi;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import vn.techmaster.bmi.service.MoneyConverter;

@SpringBootTest
public class MoneyConverterTests {
	@Autowired
	private MoneyConverter moneyConverter;

  @Test
	void testMoneyConverter() {
		moneyConverter.parseExchangeRate();
		assertEquals(true, true);
	}
}
