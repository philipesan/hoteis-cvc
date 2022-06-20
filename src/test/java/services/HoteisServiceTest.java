package services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.prova.cvc.dtos.responses.ApiResponseDTO;
import com.prova.cvc.hoteis.services.impl.HoteisServiceImpl;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class HoteisServiceTest {
	
		@InjectMocks
		private HoteisServiceImpl hoteisService;
		
		@Mock
		private RestTemplate restTemplate;
		String mockListaHoteis;
		String mockHotel;
		String brokerError;
		
		@BeforeEach
		void setup() {
			//DataMocks dataMocks = new DataMocks();
			mockListaHoteis = DataMocks.createMockLista();
			mockHotel = DataMocks.createMockHotel();
			brokerError = DataMocks.createBrokerError();

		}
		
		@Test
		void testeComHotel() throws JsonMappingException, JsonProcessingException {
		    when(restTemplate
		    		.getForObject("https://cvcbackendhotel.herokuapp.com/hotels/1", String.class))
		    .thenReturn(this.mockHotel);
		    
		    ResponseEntity<ApiResponseDTO> resposta = this.hoteisService.getHoteisCidade(
		    		"1032", "07/07/2021",
		    		"15/07/2021", "2",
		    		"0", "1");
		    
		    assertEquals(200, resposta.getStatusCode().value());
		}
		
		@Test
		void testeComCidade() throws JsonMappingException, JsonProcessingException {
		    when(restTemplate
		    		.getForObject("https://cvcbackendhotel.herokuapp.com/hotels/avail/1032", String.class))
		    .thenReturn(this.mockListaHoteis);
		    
		    ResponseEntity<ApiResponseDTO> resposta = this.hoteisService.getHoteisCidade(
		    		"1032", "07/07/2021",
		    		"15/07/2021", "2",
		    		"0", null);
		    
		    assertEquals(200, resposta.getStatusCode().value());
		}
		
		@Test
		void testeComCidadeErrada() {

		    ResponseEntity<ApiResponseDTO> resposta = this.hoteisService.getHoteisCidade(
		    		"1234", "07/07/2021",
		    		"15/07/2021", "2",
		    		"0", null);
		    
		    assertEquals(404, resposta.getStatusCode().value());
		}
		
		@Test
		void testeParseError() {

		    ResponseEntity<ApiResponseDTO> resposta = this.hoteisService.getHoteisCidade(
		    		"1032", "07/07/2021",
		    		"DATA ERRADA", "2",
		    		"0", null);
		    
		    assertEquals(400, resposta.getStatusCode().value());
		}
		
		@Test
		void testeBrokerError() throws JsonMappingException, JsonProcessingException {
		    when(restTemplate
		    		.getForObject("https://cvcbackendhotel.herokuapp.com/hotels/avail/1032", String.class))
		    .thenReturn(this.brokerError);
		    
		    ResponseEntity<ApiResponseDTO> resposta = this.hoteisService.getHoteisCidade(
		    		"1032", "07/07/2021",
		    		"15/07/2021", "2",
		    		"0", null);
		    
		   assertEquals(502, resposta.getStatusCode().value());
		}
		
		
		@Test
		void testeSemResultados() throws JsonMappingException, JsonProcessingException {
		    when(restTemplate
		    		.getForObject("https://cvcbackendhotel.herokuapp.com/hotels/9999", String.class))
		    .thenReturn(new String());
		    
		    ResponseEntity<ApiResponseDTO> resposta = this.hoteisService.getHoteisCidade(
		    		"1032", "07/07/2021",
		    		"15/07/2021", "2",
		    		"0", "9999");
		    
		   assertEquals(204, resposta.getStatusCode().value());
		}
		
}
