package btc.prediction;

import java.util.List;

public class LinearRegression {
	
	private double slope;
	private double intercept;

	public LinearRegression(List<Double> values) {
		double sumX = 0;
		double sumY = 0;
		for (int i = 0; i < values.size(); ++i) {
			sumX += i;
			sumY += values.get(i);
		}
		
		double xbar = sumX / values.size();
		double ybar = sumY / values.size();
		
		double xxbar = 0;
		double xybar = 0;
		
		for (int i = 0; i < values.size(); ++i) {
			xxbar += (i - xbar) * (i - xbar);
			xybar += (i - xbar) * (values.get(i) - ybar);
		}
		
		slope = xybar / xxbar;
		intercept = ybar - slope * xbar;
	}
	
	public Double predict(int i) {
		return slope * i + intercept;
	}
}
