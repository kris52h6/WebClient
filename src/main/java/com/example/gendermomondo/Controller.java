package com.example.gendermomondo;

import com.example.gendermomondo.dtos.CombinedDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    @GetMapping("/name-info")
    public CombinedDTO nameInfo(@RequestParam String name) {
        APITester apiTester = new APITester();
        return apiTester.getCombinedInfo(name);
    }
}
