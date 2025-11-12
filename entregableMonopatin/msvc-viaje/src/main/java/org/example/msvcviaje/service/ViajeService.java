package org.example.msvcviaje.service;

import org.example.msvcviaje.dto.*;
import org.example.msvcviaje.model.Pausa;
import org.example.msvcviaje.model.Viaje;
import org.example.msvcviaje.repository.ViajeRepository;
import org.example.msvcviaje.utils.ViajeMapper;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;

import org.springframework.stereotype.Service;

import java.util.Date;

import java.sql.Timestamp;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;

@Service
public class ViajeService {
    private final ViajeRepository viajeRepository;
    private final ViajeMapper viajeMapper;
    private final MongoTemplate mongoTemplate;

    public ViajeService(ViajeRepository viajeRepository, ViajeMapper viajeMapper, MongoTemplate mongoTemplate) {
        this.viajeRepository = viajeRepository;
        this.viajeMapper = viajeMapper;
        this.mongoTemplate = mongoTemplate;
    }

    public void crearViajeConDTO(ViajeDTO viajeDTO) {
        Viaje viaje = viajeMapper.toEntity(viajeDTO);
        viajeRepository.save(viaje);
    }

    public void iniciarViaje(Long idUsuario,Long idMonopatin){
        Viaje v = new Viaje(idUsuario,idMonopatin);
        viajeRepository.save(v);
    }

    public ViajeDTO findByID(String idViaje) {
        Viaje v = this.viajeRepository.findById(idViaje).orElseThrow();
        return viajeMapper.toDTO(v);
    }

    public List<ViajeDTO> findAll() {
        return this.viajeRepository.findAll().stream().map(this.viajeMapper::toDTO).toList();
    }

    public void update(ViajeDTO viajeDTO,String idViaje) {
        Viaje v = this.viajeRepository.findById(idViaje).orElseThrow();
        this.viajeMapper.update(viajeDTO,v);
        this.viajeRepository.save(v);
    }

    public void delete(String idViaje) {
        this.viajeRepository.deleteById(idViaje);
    }

    public void agregarPausa(String idViaje,Pausa p){
        this.viajeRepository.agregarPausa(idViaje,p);
    }

    public boolean tieneMasDe15MinutosDePausa(String idViaje) {
        Viaje viaje = viajeRepository.findById(idViaje)
                .orElseThrow(() -> new RuntimeException("Viaje no encontrado"));

        long minutosTotales = viaje.getPausas().stream()
                .filter(p -> p.getFechaInicio() != null && p.getFechaFin() != null)
                .mapToLong(p -> {
                    return Duration.between(
                            p.getFechaInicio().toInstant(),
                            p.getFechaFin().toInstant()
                    ).toMinutes();
                })
                .sum();

        return minutosTotales > 15;
    }

    public List<ViajeDTO> obtenerViajesEntreFechas(Timestamp desde, Timestamp hasta) {
        List<Viaje> viajes = viajeRepository.findByFechaInicioBetween(desde, hasta);
        return  viajes.stream().map(this.viajeMapper::toDTO).toList();
    }


    public long calcularTiempoTotalPausas(String idViaje) {
        // Obtener el viaje por id
        Viaje viaje = viajeRepository.findById(idViaje)
                .orElseThrow(() -> new RuntimeException("Viaje no encontrado"));

        List<Pausa> pausas = viaje.getPausas();
        long tiempoTotal = 0;

        // Iterar sobre cada pausa y sumar el tiempo en minutos
        for (Pausa pausa : pausas) {
            if (pausa.getFechaFin() != null && pausa.getFechaInicio() != null) {
                long diffInMillis = pausa.getFechaFin().getTime() - pausa.getFechaInicio().getTime();
                long diffInMinutes = TimeUnit.MILLISECONDS.toMinutes(diffInMillis);
                tiempoTotal += diffInMinutes;
            }
        }

        return tiempoTotal;
    }


    public long obtenerDuracionViajeEnMinutos(String idViaje) {
        Viaje viaje = viajeRepository.findById(idViaje)
                .orElseThrow(() -> new RuntimeException("Viaje no encontrado"));

        if (viaje.getFechaInicio() == null || viaje.getFechaFin() == null) {
            throw new RuntimeException("El viaje aún no ha finalizado");
        }

        return Duration.between(
                viaje.getFechaInicio().toInstant(),
                viaje.getFechaFin().toInstant()
        ).toMinutes();
    }

    public List<ReporteKmMonopatinDTO> obtenerKmRecorridosPorMonopatin() {

        Aggregation aggregation = newAggregation(
                group("idMonopatin")
                        .sum("kmRecorridos").as("kmRecorridos"),
                Aggregation.project("kmRecorridos")
                        .and("_id").as("idMonopatin")
        );

        return mongoTemplate.aggregate(aggregation, "viajes", ReporteKmMonopatinDTO.class)
                .getMappedResults();
    }

    public List<ReporteKmMonopatinPausaDTO> obtenerKmYTiempoPausasPorMonopatin() {
        List<Viaje> viajes = viajeRepository.findAll();

        // Agrupar los viajes por idMonopatin
        Map<Long, List<Viaje>> viajesPorMonopatin = viajes.stream()
                .collect(Collectors.groupingBy(Viaje::getIdMonopatin));

        List<ReporteKmMonopatinPausaDTO> reportes = new ArrayList<>();

        for (Map.Entry<Long, List<Viaje>> entry : viajesPorMonopatin.entrySet()) {
            Long idMonopatin = entry.getKey();
            List<Viaje> listaViajes = entry.getValue();

            float totalKm = 0;
            long totalMinutosPausa = 0;

            // Recorro los viajes del monopatín
            for (Viaje v : listaViajes) {
                totalKm += v.getKmRecorridos();

                // Sumo las pausas de ese viaje
                if (v.getPausas() != null) {
                    for (Pausa p : v.getPausas()) {
                        if (p.getFechaInicio() != null && p.getFechaFin() != null) {
                            long diffMillis = p.getFechaFin().getTime() - p.getFechaInicio().getTime();
                            long diffMinutos = TimeUnit.MILLISECONDS.toMinutes(diffMillis);
                            totalMinutosPausa += diffMinutos;
                        }
                    }
                }
            }


            ReporteKmMonopatinPausaDTO dto = new ReporteKmMonopatinPausaDTO(
                    idMonopatin,
                    totalKm,
                    (int) totalMinutosPausa
            );

            reportes.add(dto);
        }

        return reportes;
    }

    public List<CantidadViajesMonopatinDTO> obtenerMonopatinesConMasDeXViajes(int year, long cantidadMinima) {
        List<Viaje> viajes = viajeRepository.findAll();

        return viajes.stream()
                .filter(v -> {
                    Calendar cal = Calendar.getInstance();
                    cal.setTime(v.getFechaInicio());
                    return cal.get(Calendar.YEAR) == year;
                })
                .collect(Collectors.groupingBy(Viaje::getIdMonopatin, Collectors.counting()))
                .entrySet().stream()
                .filter(e -> e.getValue() >= cantidadMinima)
                .map(e -> new CantidadViajesMonopatinDTO(e.getKey(), e.getValue()))
                .toList();
    }

    public List<UsuarioViajeDTO> obtenerRankingUsuariosPorPeriodo(Date fechaInicio, Date fechaFin) {
        return viajeRepository.obtenerRankingUsuariosPorPeriodo(fechaInicio, fechaFin);
    }
    //4.H
    public UsoMonopatinDTO calcularUso(List<Long> idsUsuarios, Date fechaInicio, Date fechaFin) {
        return viajeRepository.calcularUsoPorUsuariosYFechas(idsUsuarios, fechaInicio, fechaFin);
    }




}
