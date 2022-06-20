package controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.nio.charset.Charset;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.prova.cvc.hoteis.controllers.HoteisController;
import com.prova.cvc.hoteis.services.HoteisService;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { HoteisController.class })
@WebMvcTest(HoteisController.class)
public class HoteisControllerTest {
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private HoteisService hoteisServices;
	@Autowired
	private WebApplicationContext webApplicationContext;

	public static final String PATH = "http://localhost:8080/api/v1/hoteis";

	public final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(),
			MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

	@BeforeEach
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	@Test
	public void getHoteisCidadeControllerTest() throws Exception {
		MvcResult result = this.mockMvc
				.perform(MockMvcRequestBuilders.get(PATH + 
						"?cityId=1032&checkInDate=07/07/2021&checkOutDate=15/07/2021&numberOfAdults=2&numberOfChildren=0")
						.contentType(APPLICATION_JSON_UTF8))
						.andDo(MockMvcResultHandlers.print())
						.andReturn();
		
		Integer statusCode = result.getResponse().getStatus();
		assertEquals(200, statusCode);
	}
	
	@Test
	public void getHoteisHotelControllerTest() throws Exception {
		MvcResult result = this.mockMvc
				.perform(MockMvcRequestBuilders.get(PATH + 
						"?cityId=1032&checkInDate=07/07/2021&checkOutDate=15/07/2021&numberOfAdults=2&numberOfChildren=0&hotelId=1")
						.contentType(APPLICATION_JSON_UTF8))
						.andDo(MockMvcResultHandlers.print())
						.andReturn();
		
		Integer statusCode = result.getResponse().getStatus();
		assertEquals(200, statusCode);
	}
	
	@Test
	public void getHoteisHotelMissingParamTest() throws Exception {
		MvcResult result = this.mockMvc
				.perform(MockMvcRequestBuilders.get(PATH + 
						"?checkInDate=07/07/2021&checkOutDate=15/07/2021&numberOfAdults=2&numberOfChildren=0&hotelId=1")
						.contentType(APPLICATION_JSON_UTF8))
						.andDo(MockMvcResultHandlers.print())
						.andReturn();
		
		Integer statusCode = result.getResponse().getStatus();
		assertEquals(400, statusCode);
	}
}
