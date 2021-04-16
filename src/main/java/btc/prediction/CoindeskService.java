package btc.prediction;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.google.gson.Gson;

public class CoindeskService {
	
	private static class HistoricalResponse {
		public Map<String, String> bpi;
		public String disclaimer;
	}
	
	private static String historicalUrl = "https://api.coindesk.com/v1/bpi/historical/close.json?start=%s&end=%s";

	public static List<Double> getHistoricalData(LocalDate from, LocalDate to) throws URISyntaxException, IOException, InterruptedException {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		
		HttpClient client = HttpClient.newHttpClient();
    	HttpRequest request = HttpRequest.newBuilder(new URI(String.format(historicalUrl, from.format(formatter), to.format(formatter)))).build();
  	
    	HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
    	HistoricalResponse hs = new Gson().fromJson(response.body(), HistoricalResponse.class);
    	
    	return hs.bpi.values().stream().map(v -> Double.parseDouble(v)).collect(Collectors.toList());
	}
}
