package vn.techmaster.money;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import vn.techmaster.money.model.CurrencyRate;
import vn.techmaster.money.model.ExchangeRate;
import vn.techmaster.money.service.MoneyConverter;

@SpringBootTest
public class MoneyConverterTests {
	@Autowired
	private MoneyConverter moneyConverter;

	@Test
	@DisplayName("Kiểm tra hàm parseExchangeRate")
	void testMoneyConverter() {
		List<ExchangeRate> exchageRates = moneyConverter.parseExchangeRate();
		assertThat(exchageRates).hasSizeGreaterThan(10);
		
		assertThat(exchageRates).filteredOn(exchangeRate -> exchangeRate.getCode().contains("VND")).hasSize(1);

		assertThat(exchageRates).anySatisfy(exchangeRate -> {
			assertThat(exchangeRate.getCode()).isEqualTo("KRW");
			assertThat(exchangeRate.getRate()).isGreaterThan(800);		
		});

		List<String> currencyCodes = exchageRates.stream().map(ExchangeRate::getCode).collect(Collectors.toList());
		assertThat(currencyCodes).contains("VND", "USD", "JPY", "KRW");
	}

	@Test
	void testGetExchangeRate() {
		float rate = moneyConverter.getExchangeRate("VND");
		assertThat(rate > 20000).isTrue();
	}

	@Test
	void testGetExchangeRateOfWrongCurrency() {
		float rate = moneyConverter.getExchangeRate("XYZ");
		assertThat(rate).isEqualTo(0.0f);
	}

	@Test
	void testGetExchangeRateOfUSD() {
		float rate = moneyConverter.getExchangeRate("USD");
		assertThat(rate).isEqualTo(1.0f);
	}

	//-------
	@Test
	void testLoadCurrencyCodeAndGetExchangeRate() {
		HashMap<String, CurrencyRate> mapCurrencyRates = moneyConverter.getMapCurrencyRates();
		assertThat(mapCurrencyRates).hasSizeGreaterThan(10);
		assertThat(mapCurrencyRates.get("JPY")).extracting("code").hasToString("JPY");
		assertThat(mapCurrencyRates.get("KRW")).extracting("name").hasToString("South Korean Won");
	}
}