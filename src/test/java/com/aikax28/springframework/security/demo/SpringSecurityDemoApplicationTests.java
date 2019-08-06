package com.aikax28.springframework.security.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.ConfigFileApplicationContextInitializer;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = SpringSecurityDemoApplication.class, initializers = ConfigFileApplicationContextInitializer.class)
public class SpringSecurityDemoApplicationTests {

    @Test
    public void contextLoads() {
    }

}
