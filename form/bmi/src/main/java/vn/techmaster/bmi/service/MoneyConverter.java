package vn.techmaster.bmi.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

@Service
public class MoneyConverter {

  /**
   * Đọc dữ liệu từ file JSON vào JsonNode masterNode
   */
  /*private void loadExchangeRateFromJSON() {

  }*/

  /**
   * Lấy tỷ giá chuyển đổi 1 USD sang currencyCode
   * @param currencyCode
   * @return
   */
  /*public float getExchangeRate(String currencyCode) {

  }*/
  public void parseExchangeRate() {
    try {
      File file = ResourceUtils.getFile("classpath:static/exchange_rate.json");
      FileReader reader = new FileReader(file);
      BufferedReader bufferedReader = new BufferedReader(reader);
      ObjectMapper objectMapper = new ObjectMapper();
      JsonNode masterNode = objectMapper.readTree(bufferedReader);
      JsonNode rate = masterNode.get("VND");
      System.out.println(rate.asText()); 
      /*`
      Iterator<Map.Entry<String, JsonNode>> iter = masterNode.fields();
      while (iter.hasNext()) {
         var node = iter.next(); 
         System.out.println(node.getKey() + " = " +  node.getValue()); 
      }*/

    } catch (FileNotFoundException e) {
      System.out.println(e);
    } catch (IOException e) {
      System.out.println(e);
    }
  }

  public void parseCurrencyCode() {

  }
}
