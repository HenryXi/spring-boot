package com.henryxi.unit;

import org.springframework.stereotype.Service;

@Service
public class SimpleService {
    public String serviceMethod() {
        return "this is service method";
    }
}
