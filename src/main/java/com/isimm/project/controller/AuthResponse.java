package com.isimm.project.controller;

import lombok.AllArgsConstructor;
import lombok.Data;
@AllArgsConstructor
@Data
public class AuthResponse {
    private String message;
    private String role;
}
