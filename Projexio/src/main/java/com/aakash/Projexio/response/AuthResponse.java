package com.aakash.Projexio.response;

import lombok.*;

@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthResponse {
    private String jwt;
    private String message;


}
