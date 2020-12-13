package vn.techmaster.topcar.model;

public class Car {
  public String model;
  public String manufacter;
  public int price;
  public int sales;
  public String photo;

  @Override
  public String toString() {
    return "Car [manufacter=" + manufacter + ", model=" + model + ", photo=" + photo + ", price=" + price + ", sales="
        + sales + "]";
  }
}
