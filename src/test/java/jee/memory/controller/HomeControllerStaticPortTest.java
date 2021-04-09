package jee.memory.controller;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@Slf4j
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class HomeControllerStaticPortTest {
    @Autowired
    TestRestTemplate testRestTemplate;

    //@Test
    // You enable it to run it by itself - sometimes it conflict with random port tests when run maven
    public void homeControllerTest() throws Exception {
        assertThat(this.testRestTemplate.getForObject("http://localhost:8080/", String.class))
                .contains("Hello this is our first project for memory data model web application!");
        log.info ("<><><><> " + this.testRestTemplate.getForObject("http://localhost:8080/", String.class));
    }
}