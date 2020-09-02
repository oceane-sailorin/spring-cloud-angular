package com.bcc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;

import java.util.List;

import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.rules.SpringClassRule;
import org.springframework.test.context.junit4.rules.SpringMethodRule;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;


public abstract class DefaultTest {

	
    @Autowired
    protected ObjectMapper json;

    @Autowired
    private WebApplicationContext context;
    
    protected MockMvc mockMvc;
    
    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context)
                                 .apply(springSecurity())
                                 .build();
    }

    @ClassRule
    public static final SpringClassRule springClassRule = new SpringClassRule();
    @Rule
    public final SpringMethodRule springMethodRule = new SpringMethodRule();

    protected <T> List<T> readList(MvcResult result, Class<T> clazz) throws java.io.IOException {
        return json.readValue(result.getResponse()
                                    .getContentAsString(), json.getTypeFactory()
                                                               .constructCollectionType(List.class, clazz));
    }

    protected <T> T read(MvcResult result, Class<T> clazz) throws java.io.IOException {
        return json.readValue(result.getResponse()
                                    .getContentAsString(), clazz);
    }

    public static ResultMatcher emptyContent() {
        return result -> assertThat(result.getResponse()
                                          .getContentAsByteArray()).isNullOrEmpty();
    }
	

}
