package com.works.restcontrollers;

import com.works.utils.REnum;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@RequestMapping("/sales")
public class SalesRestController {
    @GetMapping("/list")
    public Map list(){
        Map<REnum, Object> hm = new LinkedHashMap();
        hm.put(REnum.status, true);
        hm.put(REnum.result, "sales list");
        return hm;
    }
}
