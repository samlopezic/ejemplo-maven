package com.devopsusach2020;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import com.devopsusach2020.rest.RestData;

@SpringBootTest
@AutoConfigureMockMvc
class DevOpsUsach2020ApplicationTests {

	@Autowired
	private MockMvc mvc;

	@Test
	void contextLoads(ApplicationContext context) {
		assertThat(context).isNotNull();
	}

	@Test
	void noEncuentraPais() throws Exception{		
		this.mvc.perform(get("/estadoPais?pais=")).andExpect(status().isNotFound());
	}

	@Test
	void mensajePrueba() throws Exception{
		RestData r = new RestData();
		assertThat(r.getData("Hola Mundo")).isNotNull();	
	}


	@Test
	void datosPais() throws Exception{
		RestData r = new RestData();
		assertThat(r.getTotalPais("Chile")).isNotNull();		
	}

}
