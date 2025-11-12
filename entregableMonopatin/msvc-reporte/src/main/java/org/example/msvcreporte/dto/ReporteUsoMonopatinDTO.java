package org.example.msvcreporte.dto;

import lombok.Data;

import java.util.concurrent.TimeUnit;

@Data
public class ReporteUsoMonopatinDTO {
    private double totalKm;
    private long totalTiempoMs;

    public long getTotalTiempoMinutos() {
        return TimeUnit.MILLISECONDS.toMinutes(totalTiempoMs);
    }
}
