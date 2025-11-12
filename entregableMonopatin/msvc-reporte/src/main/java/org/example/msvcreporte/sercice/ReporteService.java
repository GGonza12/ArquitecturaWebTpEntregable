package org.example.msvcreporte.sercice;

import lombok.RequiredArgsConstructor;
import org.example.msvcreporte.client.FacturacionClient;
import org.example.msvcreporte.client.MonopatinClient;
import org.example.msvcreporte.client.UsuarioClient;
import org.example.msvcreporte.client.ViajeClient;
import org.example.msvcreporte.dto.*;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class ReporteService {

    private final ViajeClient viajeClient;
    private final FacturacionClient facturacionClient;
    private final MonopatinClient  monopatinClient;
    private final UsuarioClient  usuarioClient;

    public ReporteService(ViajeClient viajeClient, FacturacionClient facturacionClient, MonopatinClient monopatinClient, UsuarioClient usuarioClient) {
        this.viajeClient = viajeClient;
        this.facturacionClient = facturacionClient;
        this.monopatinClient = monopatinClient;
        this.usuarioClient = usuarioClient;
    }

    public List<ReporteKmMonopatinDTO> obtenerKmPorMonopatin() {
        return viajeClient.obtenerKmPorMonopatin();
    }

    public List<ReporteKmMonopatinPausaDTO> obtenerKmYTiempoPausasPorMonopatin() {
        return viajeClient.obtenerKmYTiempoPausasPorMonopatin();
    }

    public double obtenerTotalFacturado(int anio, int mesInicio, int mesFin) {
        return facturacionClient.obtenerTotalFacturado(anio, mesInicio, mesFin).getBody();
    }

    public List<ReporteCantidadViajesMonopatinDTO> obtenerCantidadViajesPorMonopatin(int year, Long cantidadMinima) {
        return viajeClient.obtenerMonopatinesFrecuentes(year,cantidadMinima);
    }

    public List<ReporteMonopatinDTO> obtenerMonopatinesCerca(double lat,double lon,double radioKm,int cant){
        return monopatinClient.getCerca(lat,lon,radioKm,cant);

    }

    public List<UsuarioRankingDTO> obtenerUsuariosMasActivos(String fechaInicio, String fechaFin) {
        // 1. Obtener ranking básico de msvc-viaje
        List<UsuarioViajeDTO> ranking = viajeClient.obtenerRankingUsuariosPorPeriodo(fechaInicio, fechaFin);

        // 2. Extraer IDs
        List<Long> ids = ranking.stream().map(UsuarioViajeDTO::getIdUsuario).toList();

        // 3. Obtener datos de usuarios
        List<UsuarioDTO> usuarios = usuarioClient.obtenerUsuariosPorIds(ids);

        // 4. Combinar resultados
        return ranking.stream().map(r -> {
                    UsuarioDTO u = usuarios.stream()
                            .filter(x -> x.getId().equals(r.getIdUsuario()))
                            .findFirst()
                            .orElse(null);
                    return new UsuarioRankingDTO(
                            r.getIdUsuario(),
                            u != null ? u.getNombre() : "Desconocido",
                            u != null ? u.getApellido() : "",
                            u != null ? u.getRol() : null,
                            r.getCantidadViajes()
                    );
                }).sorted(Comparator.comparingLong(UsuarioRankingDTO::getCantidadViajes).reversed())
                .toList();
    }


}
