package com.henryxi.unit;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-all.xml")
public class SimpleServiceTest {
    @Autowired
    private SimpleService simpleService;

    @Test
    public void serviceMethod() throws Exception {
        Assert.assertEquals("this is service method", simpleService.serviceMethod());
    }
}