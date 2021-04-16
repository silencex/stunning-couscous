package btc.prediction;

import java.time.LocalDate;
import java.util.List;

public class Application {
	
	
    public static void main(String[] args) {
    	LocalDate from = LocalDate.now().minusDays(7);
    	LocalDate to = LocalDate.now().minusDays(1);

		try {
			List<Double> data = CoindeskService.getHistoricalData(from, to);
			LinearRegression lr = new LinearRegression(data);
			System.out.println(String.format("%.3f", lr.predict(data.size())));
		} catch (Exception e) {
			System.out.println("Unable to fetch BTC prices");
		}
	}
}
