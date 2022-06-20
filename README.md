# Teste CVC 
## _Pessoa Desenvolvedora Java Pleno_
## API de Busca de Hoteis por Estado

Tecnologias Usadas
- Spring Boot
- RestTemplate
- Lombok
- JUnit
- Mockito
- Apache Commons
- Apache Validator
 
## Features

- Buscar por Cidade
- Buscar por Hotel

# _Endpoints_

## Get - http://localhost:8080/api/v1/hoteis

### Parametros Obrigatórios

- cityId (Inteiro)
- checkInDate (DD/MM/AAAA)
- checkOutDate (DD/MM/AAAA)
- numberOfAdults (Inteiro)
- numberOfChildren (Inteiro)
### Parametros Opcionais
- hotelId (Inteiro)

# Exemplo de Resposta

```sh
{
    "message": "Processo executado com sucesso!",
    "content": [
        {
            "id": 1,
            "name": "Hotel Teste 1",
            "city": "Porto Seguro",
            "rooms": [
                {
                    "id": 0,
                    "category": {
                        "name": "Standard"
                    },
                    "totalPrice": 31372.32,
                    "priceDetail": {
                        "pricePerDayAdult": 3921.54,
                        "pricePerDayChildren": 0.00
                    }
                }
            ]
        }
    ]
}
```
# Códigos de Erro
### 400: Erro nos parâmetros da requisição
### 404: Cidade não existente
#### Cidades possíveis
- PORTO SEGURO: 1032
- SAO PAULO: 7110
- RIO DE JANEIRO: 9626
### 500: Erro de execução, é necessário avaliar os logs
### 502: Erro na resposta do Broker, JSON Inválido


