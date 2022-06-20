package com.prova.cvc.hoteis.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.prova.cvc.dtos.responses.ApiResponseDTO;
import com.prova.cvc.hoteis.services.HoteisService;

@RestController
@RequestMapping(path="api/v1/hoteis")
public class HoteisController {
	
	@Autowired
	private HoteisService hoteisService;

	@GetMapping
	public ResponseEntity<ApiResponseDTO> getHoteis(
			@RequestParam String cityId,
			@RequestParam String checkInDate,
			@RequestParam String checkOutDate,
			@RequestParam String numberOfAdults,
			@RequestParam String numberOfChildren,
			@RequestParam(required = false) String hotelId) {
			return hoteisService.getHoteisCidade(cityId, checkInDate, checkOutDate, numberOfAdults, numberOfChildren, hotelId);
		
	}
}
