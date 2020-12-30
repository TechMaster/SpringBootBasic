package vn.techmaster.money.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CurrencyRate {
  @JsonProperty("Currency")
  private String name;

  @JsonProperty("AlphabeticCode")
  private String code;
  private float rate; // Quy đổi 1 USD sang được bao nhiêu

  

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public float getRate() {
    return rate;
  }

  public void setRate(float rate) {
    this.rate = rate;
  }

  @Override
  public String toString() {
    return "CurrencyRate [code=" + code + ", currency=" + name + ", rate=" + rate + "]";
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
