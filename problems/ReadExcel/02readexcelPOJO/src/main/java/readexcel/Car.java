package readexcel;
import com.poiji.annotation.ExcelCell;

public class Car {
  @ExcelCell(0)
  private String model;

  @ExcelCell(1)
  private String manufacter;

  @ExcelCell(2)
  private int price;

  @ExcelCell(3)
  private int sales;

  @ExcelCell(4)
  private String photo;

  @Override
  public String toString() {
    return "Car [manufacter=" + manufacter + ", model=" + model + ", photo=" + photo + ", price=" + price + ", sales="
        + sales + "]";
  }
}