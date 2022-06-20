package com.prova.cvc.dtos.responses;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class HotelResponseDTO {
	private Integer id;
	private String city;
	private String name;
	private List<RoomResponseDTO> rooms;
}
