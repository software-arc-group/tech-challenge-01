package br.com.soat8.techchallenge.client.controller.DTO;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class CustomerRequest {

    private String emailAddress;

    private String name;

    private String cpf;
}
