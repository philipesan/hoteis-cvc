package services;

import java.math.BigDecimal;
import java.util.ArrayList;

import com.prova.cvc.models.Hotel;
import com.prova.cvc.models.PriceDetail;
import com.prova.cvc.models.Room;

public class DataMocks {
	public static String createMockLista() {
		return "    [{\r\n"
				+ "        \"id\": 1,\r\n"
				+ "        \"name\": \"Hotel Teste 1\",\r\n"
				+ "        \"cityCode\": 1032,\r\n"
				+ "        \"cityName\": \"Porto Seguro\",\r\n"
				+ "        \"rooms\": [\r\n"
				+ "            {\r\n"
				+ "                \"roomID\": 0,\r\n"
				+ "                \"categoryName\": \"Standard\",\r\n"
				+ "                \"price\": {\r\n"
				+ "                    \"adult\": 1372.54,\r\n"
				+ "                    \"child\": 848.61\r\n"
				+ "                }\r\n"
				+ "            }\r\n"
				+ "        ]\r\n"
				+ "    },\r\n"
				+ "    {\r\n"
				+ "        \"id\": 4,\r\n"
				+ "        \"name\": \"Hotel Teste 4\",\r\n"
				+ "        \"cityCode\": 1032,\r\n"
				+ "        \"cityName\": \"Porto Seguro\",\r\n"
				+ "        \"rooms\": [\r\n"
				+ "            {\r\n"
				+ "                \"roomID\": 0,\r\n"
				+ "                \"categoryName\": \"Standard\",\r\n"
				+ "                \"price\": {\r\n"
				+ "                    \"adult\": 341.76,\r\n"
				+ "                    \"child\": 782.14\r\n"
				+ "                }\r\n"
				+ "            },\r\n"
				+ "            {\r\n"
				+ "                \"roomID\": 1,\r\n"
				+ "                \"categoryName\": \"Luxo\",\r\n"
				+ "                \"price\": {\r\n"
				+ "                    \"adult\": 483.02,\r\n"
				+ "                    \"child\": 591.33\r\n"
				+ "                }\r\n"
				+ "            }\r\n"
				+ "        ]\r\n"
				+ "    },\r\n"
				+ "    {\r\n"
				+ "        \"id\": 7,\r\n"
				+ "        \"name\": \"Hotel Teste 7\",\r\n"
				+ "        \"cityCode\": 1032,\r\n"
				+ "        \"cityName\": \"Porto Seguro\",\r\n"
				+ "        \"rooms\": [\r\n"
				+ "            {\r\n"
				+ "                \"roomID\": 0,\r\n"
				+ "                \"categoryName\": \"Standard\",\r\n"
				+ "                \"price\": {\r\n"
				+ "                    \"adult\": 493.15,\r\n"
				+ "                    \"child\": 650.02\r\n"
				+ "                }\r\n"
				+ "            },\r\n"
				+ "            {\r\n"
				+ "                \"roomID\": 1,\r\n"
				+ "                \"categoryName\": \"Luxo\",\r\n"
				+ "                \"price\": {\r\n"
				+ "                    \"adult\": 824.63,\r\n"
				+ "                    \"child\": 231.65\r\n"
				+ "                }\r\n"
				+ "            }\r\n"
				+ "        ]\r\n"
				+ "    }]";
	}
	public static String createMockHotel() {
		return "    [{\r\n"
				+ "        \"id\": 1,\r\n"
				+ "        \"name\": \"Hotel Teste 1\",\r\n"
				+ "        \"cityCode\": 1032,\r\n"
				+ "        \"cityName\": \"Porto Seguro\",\r\n"
				+ "        \"rooms\": [\r\n"
				+ "            {\r\n"
				+ "                \"roomID\": 0,\r\n"
				+ "                \"categoryName\": \"Standard\",\r\n"
				+ "                \"price\": {\r\n"
				+ "                    \"adult\": 1372.54,\r\n"
				+ "                    \"child\": 848.61\r\n"
				+ "                }\r\n"
				+ "            }\r\n"
				+ "        ]\r\n"
				+ "    }]";
	}
	
	public static String createBrokerError() {
		return "    [{\r\n"
				+ "        \"id\": 111118.61\r\n"
				+ "                }\r\n"
				+ "            }\r\n"
				+ "        ]\r\n"
				+ "    }]";
	}
	

}
