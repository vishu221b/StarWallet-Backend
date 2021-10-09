package com.StarWallet.StarWallet.model.response;

import lombok.*;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class GenericResponseDTO {

    private String message;
    private String status;

}
