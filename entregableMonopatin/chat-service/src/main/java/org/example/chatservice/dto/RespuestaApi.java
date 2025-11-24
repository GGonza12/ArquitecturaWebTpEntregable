package org.example.chatservice.dto;

public record RespuestaApi<T>(boolean ok, String mensaje, T datos) {}
