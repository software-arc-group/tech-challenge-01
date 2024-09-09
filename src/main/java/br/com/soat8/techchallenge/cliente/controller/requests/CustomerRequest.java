package br.com.soat8.techchallenge.cliente.controller.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CustomerRequest {
    private String id;

    private String name;

    private String emailAddress;

    private String cpf;
}
