package com.mercadolivre.simian;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootContextLoader;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.cloud.gcp.data.datastore.core.DatastoreTemplate;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(classes = SimianApplication.class, loader = SpringBootContextLoader.class)
public class CucumberSteps {

    @Autowired
    protected DatastoreTemplate datastoreTemplate;

    @Autowired
    protected TestRestTemplate template;

    @Value("http://localhost")
    protected String host;

    @Value("/simian")
    protected String basePath;

    @LocalServerPort
    protected Long port;

}
