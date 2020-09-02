package com.bcc;

import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;


@Sql(scripts = { "/initTest.sql" })
@SpringBootTest
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = AppConfig.class)
@AutoConfigureMockMvc
public class KeywordTest {

	 @Test
	    public void shouldGetKeyword() throws Exception {
	        //KeywordEntity entity = KeywordRepository.findById(1); 
		 String hello = "Hello";
	        
		 //this.mockMvc.perform(requestBuilder)(hello.contains("Hell"));
	                                            
	    }
}
