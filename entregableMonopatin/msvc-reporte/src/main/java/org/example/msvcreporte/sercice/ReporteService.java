package org.example.msvcreporte.sercice;


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
        // Obtener ranking de msvc-viaje
        List<UsuarioViajeDTO> ranking = viajeClient.obtenerRankingUsuariosPorPeriodo(fechaInicio, fechaFin);

        // Extraer IDs
        List<Long> ids = ranking.stream().map(UsuarioViajeDTO::getIdUsuario).toList();

        // Obtener datos de usuarios
        List<UsuarioDTO> usuarios = usuarioClient.obtenerUsuariosPorIds(ids);

        //  Combinar resultados
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
                }).sorted(
                Comparator
                        .comparing((UsuarioRankingDTO u) -> String.valueOf(u.getRol()), Comparator.nullsLast(String::compareTo))
                        .thenComparing(Comparator.comparingLong(UsuarioRankingDTO::getCantidadViajes).reversed())
        ).toList();
    }
    // 4.H
    public ReporteUsoMonopatinTiempo obtenerUsoUsuarios(
            Long idUsuarioPrincipal, String fechaInicio, String fechaFin, boolean incluirRelacionados) {

        // Obtener usuarios a considerar
        List<Long> idsUsuarios = incluirRelacionados
                ? usuarioClient.obtenerUsuariosRelacionados(idUsuarioPrincipal)
                : List.of(idUsuarioPrincipal);

        // Para cada usuario obtener su uso en msvc-viaje
        List<UsoUsuarioDTO> usos = idsUsuarios.stream()
                .map(id -> {
                    ReporteUsoMonopatinDTO uso = viajeClient.calcularUso(List.of(id), fechaInicio, fechaFin);
                    UsuarioDTO user = usuarioClient.obtenerUsuarioPorId(id);

                    return new UsoUsuarioDTO(
                            user.getId(),
                            user.getNombre(),
                            user.getApellido(),
                            user.getRol(),
                            uso != null ? uso.getTotalKm() : 0.0,
                            uso != null ? uso.getTotalTiempoMinutos() : 0L
                    );
                })
                .toList();

        // Totales globales
        double totalKm = usos.stream().mapToDouble(UsoUsuarioDTO::getKmRecorridos).sum();
        long totalMin = usos.stream().mapToLong(UsoUsuarioDTO::getTiempoMinutos).sum();

        return new ReporteUsoMonopatinTiempo(
                idUsuarioPrincipal,
                fechaInicio,
                fechaFin,
                totalKm,
                totalMin,
                usos
        );
    }






}
