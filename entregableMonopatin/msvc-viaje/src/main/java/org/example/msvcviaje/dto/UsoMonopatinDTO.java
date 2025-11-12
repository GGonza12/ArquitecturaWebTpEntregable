package org.example.msvcviaje.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.concurrent.TimeUnit;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsoMonopatinDTO {
    private double totalKm;
    private long totalTiempoMs;

    public long getTotalTiempoMinutos() {
        return TimeUnit.MILLISECONDS.toMinutes(totalTiempoMs);
    }
}
