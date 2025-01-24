package com.example.demo.service;
import com.example.demo.model.RouteStop;
import com.example.demo.model.RouteStopData;
import com.example.demo.repository.RouteStopRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.tomcat.util.json.JSONParser;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.stereotype.Service;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.io.Console;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class ApiService {
    @Autowired
    private RouteStopRepository routeStopRepository;

    private static final Logger logger = LogManager.getLogger(ApiService.class);
    private final ObjectMapper objectMapper = new ObjectMapper();
    public void fetchRouteStopData() throws Exception {
        String url = "https://data.etabus.gov.hk/v1/transport/kmb/route-stop/1A/outbound/1";
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        System.out.println(response.getBody());

        try {
            JsonNode rootNode = objectMapper.readTree(response.getBody());
            JsonNode dataNode = rootNode.path("data");

            List<RouteStopData> routeStops = new ArrayList<>();
            if (dataNode.isArray()) {
                for (JsonNode node : dataNode) {
                    RouteStopData routeStop = objectMapper.treeToValue(node, RouteStopData.class);
                    routeStops.add(routeStop);
                }
            }
            routeStopRepository.saveAll(routeStops);
            logger.error("Route have been inserted to DB successfully");
        } catch (Exception e) {
            e.printStackTrace();
        }
//        if (response != null && response.getData() != null)  {
//            List<RoutlleStopData> routeStops = response.getData();
//            for (RouteStopData routeStop : routeStops) {
//                routeStopRepository.save(routeStop);
//            }
//        }
    }
}
