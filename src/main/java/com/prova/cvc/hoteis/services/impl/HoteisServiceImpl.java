package com.prova.cvc.hoteis.services.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.validator.GenericValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.prova.cvc.dtos.responses.ApiResponseDTO;
import com.prova.cvc.dtos.responses.HotelResponseDTO;
import com.prova.cvc.dtos.responses.PriceDetailResponseDTO;
import com.prova.cvc.dtos.responses.RoomResponseDTO;
import com.prova.cvc.hoteis.enums.CityIdsEnum;
import com.prova.cvc.hoteis.services.HoteisService;
import com.prova.cvc.hoteis.utils.HoteisServiceUtils;
import com.prova.cvc.models.Category;
import com.prova.cvc.models.Hotel;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class HoteisServiceImpl implements HoteisService {

    private final String URL_CIDADES = "https://cvcbackendhotel.herokuapp.com/hotels/avail/";
    private final String URL_HOTEIS = "https://cvcbackendhotel.herokuapp.com/hotels/";    
    private final BigDecimal VALOR_COMISSAO = new BigDecimal(0.7);
	@Autowired private RestTemplate restTemplate;

    @Override
	public ResponseEntity<ApiResponseDTO> getHoteisCidade(
			String cityId,
			String checkInDate,
			String checkOutDate,
			String numberOfAdults,
			String numberOfChildren,
			String hotelId) {
    	
		ApiResponseDTO respostaRequest = new ApiResponseDTO();
		ResponseEntity<ApiResponseDTO> respostaEntidade;
		
		if(!CityIdsEnum.checaExistencia(cityId)) {
		
			respostaRequest.setMessage("Erro: Cidade não encontrada");
			return ResponseEntity.status(404).body(respostaRequest);
		}
		
		if(!HoteisServiceUtils.validaRequest(cityId, numberOfAdults, numberOfChildren, hotelId, checkInDate, checkOutDate)) {
			respostaRequest.setMessage("Erro: Verifique os parametros da requisição");
			return ResponseEntity.status(400).body(respostaRequest);
		}
		
		String uri = ((hotelId != null) ? this.URL_HOTEIS + hotelId 
										: this.URL_CIDADES + cityId);
		
		try {
	    	BigDecimal numOfAdults = BigDecimal.valueOf(Long.valueOf(numberOfAdults));
	    	BigDecimal numOfChildren = BigDecimal.valueOf(Long.valueOf(numberOfChildren));		
			BigDecimal numDiarias = BigDecimal.valueOf(HoteisServiceUtils.calcularData(checkInDate, checkOutDate));
			List<Hotel>  resposta = this.buscaDados(uri);
			
			if(resposta.size() == 0) {
				respostaRequest.setMessage("Nenhum dado foi retornado, revise os parametros da busca");
				respostaEntidade = ResponseEntity.status(204).body(respostaRequest);
				return respostaEntidade;				
			}
			
			List<HotelResponseDTO> respostaLista = resposta.parallelStream().map(
					hotel -> {
						HotelResponseDTO hotelResposta = new HotelResponseDTO();
						hotelResposta.setId(hotel.getId());
						hotelResposta.setCity(hotel.getCityName());
						hotelResposta.setName(hotel.getName());
						
						List<RoomResponseDTO> quartosResposta = hotel.getRooms().stream().map(
								quarto -> {
									RoomResponseDTO quartoResposta = new RoomResponseDTO();
									
									quartoResposta.setId(quarto.getRoomID());
									quartoResposta.setCategory(new Category(quarto.getCategoryName()));
									
									BigDecimal totalAdulto = HoteisServiceUtils.calculaValorDiarias(quarto.getPrice().getAdult(), numOfAdults);
									
							        totalAdulto = totalAdulto.divide(VALOR_COMISSAO, RoundingMode.HALF_EVEN);
									
									BigDecimal totalCrianca =  HoteisServiceUtils.calculaValorDiarias(quarto.getPrice().getChild(), numOfChildren);
							        
									totalCrianca = totalCrianca.divide(VALOR_COMISSAO, RoundingMode.HALF_EVEN);
									
									quartoResposta.setPriceDetail(
											new PriceDetailResponseDTO(totalAdulto, totalCrianca));
									
									quartoResposta.setTotalPrice(HoteisServiceUtils.calculaValorTotal(quartoResposta.getPriceDetail(), numDiarias));
									
									return quartoResposta;
								}).collect(Collectors.toList());
						
						hotelResposta.setRooms(quartosResposta);
						return hotelResposta;
					}).collect(Collectors.toList());
			
			respostaRequest.setMessage("Processo executado com sucesso!");
			respostaRequest.setContent(respostaLista);
			
			respostaEntidade = ResponseEntity.status(200).body(respostaRequest);
			return respostaEntidade;
		
		} catch (ParseException|IllegalArgumentException e) {
			e.printStackTrace();
			respostaRequest.setMessage("Erro: Verifique se os valores da consulta estão corretos");
			respostaEntidade = ResponseEntity.status(400).body(respostaRequest);
			return respostaEntidade;
		
		} catch (HttpStatusCodeException e) {
			e.printStackTrace();
			respostaRequest.setMessage("Erro: erro durante a request HTTP para buscar hoteis, código: " + e.getStatusCode().toString());
			respostaEntidade =  ResponseEntity.status(500).body(respostaRequest);
			return respostaEntidade;
			
		} catch (RuntimeException e) {
			e.printStackTrace();
			respostaRequest.setMessage("Erro: Erro desconhecido durante o processamento");
			respostaEntidade =  ResponseEntity.status(500).body(respostaRequest);
			return respostaEntidade;
			
		}
			catch (JsonProcessingException e) {
			e.printStackTrace();
			respostaRequest.setMessage("Erro: Erro na resposta do Broker");
			respostaEntidade =  ResponseEntity.status(502).body(respostaRequest);
			return respostaEntidade;	
		}
		
	}
    
    public String executaHttp(String uri) {
    	return this.restTemplate.getForObject(uri, String.class);
    }
    
    @Cacheable(cacheNames = "uri", key = "#uri")	
	public List<Hotel> buscaDados(String uri) throws JsonMappingException, JsonProcessingException {

        String resposta = executaHttp(uri);
		ObjectMapper objMap = new ObjectMapper();
		return (!resposta.equals("") ? objMap.readValue(resposta, new TypeReference<List<Hotel>>(){})
									 : List.of());
	}
}
