package ru.netology.springboot;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Container;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ApplicationTests {
   private static final String ENDPOINT = "/profile";

    @Autowired
    TestRestTemplate restTemplate;
    @Container
    private static final GenericContainer<?> myDevApp = new GenericContainer<>("devapp")
            .withExposedPorts(8080);
    @Container
    private static final GenericContainer<?> myProdApp = new GenericContainer<>("prodapp")
            .withExposedPorts(8081);

    @BeforeAll
    public static void setUp() {
        myDevApp.start();
        myProdApp.start();
    }

    @Test
    void contextLoadsDevApp() {
        final String expected = "Current profile is dev";
        ResponseEntity<String> forEntity = restTemplate.getForEntity("http://localhost:" + myDevApp.getMappedPort(8080) + ENDPOINT, String.class);
        System.out.println(forEntity.getBody());
        Assertions.assertEquals(expected, forEntity.getBody());
    }

    @Test
    void contextLoadsProdApp() {
        final String expected = "Current profile is production";
        ResponseEntity<String> forEntityTwo = restTemplate.getForEntity("http://localhost:" + myProdApp.getMappedPort(8081) + ENDPOINT, String.class);
        System.out.println(forEntityTwo.getBody());
        Assertions.assertEquals(expected, forEntityTwo.getBody());
    }
}