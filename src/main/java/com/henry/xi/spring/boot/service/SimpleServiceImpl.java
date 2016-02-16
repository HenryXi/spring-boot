package com.henry.xi.spring.boot.service;

import com.henry.xi.spring.boot.annotation.RequestLimit;
import org.springframework.stereotype.Service;

/**
 * Created by admin on 2016/2/2.
 */
@Service
public class SimpleServiceImpl {

    @RequestLimit(count=100,time=60000)
    public String service(String name){
        return "hello, ".concat(name);
    }
}
