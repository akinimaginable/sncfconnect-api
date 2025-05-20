package org.etrange.sncfconnect.application;

import org.etrange.multimodule.service.MyService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class DemoApplicationTest {

    @Autowired
    private MyService myService;



    @Test
    public void contextLoads() {
        assertThat(myService.message()).isNotNull();
    }

}
