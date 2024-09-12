package br.com.soat8.techchallenge.client.controller.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CustomerRequest {

    private String name;

    private String emailAddress;

    private String cpf;
}
