package com.example.demo.actuator;

import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.actuate.endpoint.web.annotation.WebEndpoint;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

/**
 * @author pdai
 */
@RestController("custom")
@WebEndpoint(id = "date")
public class CustomEndpointController {

    @ReadOperation
    public ResponseEntity<String> currentDate() {
        return ResponseEntity.ok(LocalDateTime.now().toString());
    }
}
