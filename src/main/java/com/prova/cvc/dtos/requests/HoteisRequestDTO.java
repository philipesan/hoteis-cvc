package com.prova.cvc.dtos.requests;

import java.util.ArrayList;

import com.prova.cvc.models.Hotel;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HoteisRequestDTO {
	private ArrayList<Hotel> content;
}
