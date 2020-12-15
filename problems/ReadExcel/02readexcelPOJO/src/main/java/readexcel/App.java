package readexcel;

import java.io.File;
import java.util.List;

import com.poiji.bind.Poiji;
import com.poiji.option.PoijiOptions;

public class App {

    public static void main(String[] args) {
        String excelFilePath = "/Users/techmaster/Desktop/topcar.xlsx";

        PoijiOptions options = PoijiOptions.PoijiOptionsBuilder.settings().addListDelimiter(";").build();

        try {
            List<Car> cars = Poiji.fromExcel(new File(excelFilePath), Car.class, options);
            for (Car car : cars) {
                System.out.println(car);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}