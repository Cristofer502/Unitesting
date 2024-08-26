package org.adaschool.Weather.service;

import org.adaschool.Weather.data.WeatherApiResponse;
import org.adaschool.Weather.data.WeatherReport;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherReportService {

    private static final String API_KEY = "aeb07a35fc7d2fc788ab33e2211664a9"; // Agrego mi API key
    private static final String API_URL = "https://api.openweathermap.org/data/2.5/weather";

    private final RestTemplate restTemplate;
    public WeatherReportService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;

    }

    public WeatherReport getWeatherReport(double latitude, double longitude) {

        String url = API_URL + "?lat=" + latitude + "&lon=" + longitude + "&appid=" + API_KEY;

        WeatherApiResponse response = restTemplate.getForObject(url, WeatherApiResponse.class);

        // Verificar si la respuesta o el objeto 'Main' son nulos
        if (response == null || response.getMain() == null) {
            return null;
        }

        // Crear el reporte del clima
        WeatherReport report = new WeatherReport();
        WeatherApiResponse.Main main = response.getMain();
        report.setTemperature(main.getTemperature());
        report.setHumidity(main.getHumidity());

        return report;
    }
}
