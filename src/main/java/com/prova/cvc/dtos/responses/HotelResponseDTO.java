package com.prova.cvc.dtos.responses;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.prova.cvc.models.Hotel;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class HotelResponseDTO extends Hotel {
	private String city;
	private String name;
	private List<RoomResponseDTO> rooms;
}
