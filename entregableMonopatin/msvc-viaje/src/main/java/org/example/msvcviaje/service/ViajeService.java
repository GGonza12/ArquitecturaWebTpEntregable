package org.example.msvcviaje.service;

import org.example.msvcviaje.dto.ViajeDTO;
import org.example.msvcviaje.model.Pausa;
import org.example.msvcviaje.model.Viaje;
import org.example.msvcviaje.repository.ViajeRepository;
import org.example.msvcviaje.utils.ViajeMapper;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.Duration;
import java.util.List;

@Service
public class ViajeService {
    private final ViajeRepository viajeRepository;
    private final ViajeMapper viajeMapper;

    public ViajeService(ViajeRepository viajeRepository, ViajeMapper viajeMapper) {
        this.viajeRepository = viajeRepository;
        this.viajeMapper = viajeMapper;
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

}
