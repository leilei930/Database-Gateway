package com.example.demo.service;
import com.example.demo.DemoApplication;
import com.example.demo.model.RouteStop;
import com.example.demo.model.RouteStopData;
import com.example.demo.model.StopData;
import com.example.demo.repository.RouteStopRepository;
import com.example.demo.repository.StopRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.tomcat.util.json.JSONParser;
import org.bson.json.JsonObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.stereotype.Service;
import org.apache.logging.log4j.LogManager;

import java.io.Console;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class ApiService {
    @Autowired
    private RouteStopRepository routeStopRepository;
    @Autowired
    private StopRepository stopRepository;

    private static final Logger logger = DemoApplication.logger;
    private final ObjectMapper objectMapper = new ObjectMapper();
    public void fetchRouteData() throws Exception {
        String url = "https://data.etabus.gov.hk/v1/transport/kmb/route-stop";
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

        try {
            JsonNode rootNode = objectMapper.readTree(response.getBody());
            JsonNode dataNode = rootNode.path("data");

            List<RouteStopData> routeStops = new ArrayList<>();
            int route = 0;
            if (dataNode.isArray()) {
                for (JsonNode node : dataNode) {
                    RouteStopData routeStop = objectMapper.treeToValue(node, RouteStopData.class);
                    routeStops.add(routeStop);
                    route++;
                }
            }
            if(routeStopRepository.count() > 0){
                routeStopRepository.deleteAll();
                logger.info("Deleted existing record");
            }
            routeStopRepository.saveAll(routeStops);
            logger.info(route + "Route have been inserted to DB successfully");
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Insert route failed");
        }

    }

    public void fetchStopData() throws Exception {
        String url = "https://data.etabus.gov.hk/v1/transport/kmb/stop";
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

        try {
            JsonNode rootNode = objectMapper.readTree(response.getBody());
            JsonNode dataNode = rootNode.path("data");

            List<StopData> stops = new ArrayList<>();
            int route = 0;
            if (dataNode.isArray()) {
                for (JsonNode node : dataNode) {
                    StopData routeStop = objectMapper.treeToValue(node, StopData.class);
                    stops.add(routeStop);
                    route++;
                }
            }
            if(stopRepository.count() > 0){
                stopRepository.deleteAll();
                logger.info("Deleted existing record");
            }
            stopRepository.saveAll(stops);
            logger.info(route + "Stop data have been inserted to DB successfully");
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Insert data failed");
        }

    }

    public List<StopData> findRouteStopsByRoute(String route) {
        List<RouteStopData> routeStops = routeStopRepository.findByRoute(route);
        List<String> stopByRoute =  routeStops.stream().map(RouteStopData::getStop).toList();

        List<StopData> stopData = new ArrayList<>();
        for (String stop: stopByRoute){
             stopData.add(stopRepository.findByStop(stop));
        }
        return stopData;
    }


}
