package com.prova.cvc.hoteis.enums;

import lombok.Getter;

@Getter
public enum CityIdsEnum {
	PORTO_SEGURO("1032"),
	RIO_DE_JANEIRO("7110"),
	SAO_PAULO("9626");
	
	
	private String valorCidade;
	CityIdsEnum(String valor ){
		valorCidade = valor;
	}
	
	public static Boolean checaExistencia(String valor) {
		for(CityIdsEnum enumItem: CityIdsEnum.values()) {
			if (valor.equals(enumItem.valorCidade)) return true;
		}
		return false;
	}
}
