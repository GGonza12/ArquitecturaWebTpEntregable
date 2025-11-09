package org.example.msvcmonopatin.service;

import org.example.msvcmonopatin.dto.MonopatinDTO;
import org.example.msvcmonopatin.model.EstadoMonopatin;
import org.example.msvcmonopatin.model.Monopatin;
import org.example.msvcmonopatin.repository.MonopatinRepository;
import org.example.msvcmonopatin.utils.MonopatinMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MonopatinService {
    private final MonopatinRepository monopatinRepository;
    private final MonopatinMapper monopatinMapper;

    public MonopatinService(MonopatinRepository monopatinRepository, MonopatinMapper monopatinMapper) {
        this.monopatinRepository = monopatinRepository;
        this.monopatinMapper = monopatinMapper;
    }

    public void createMonopatin(MonopatinDTO monopatinDTO) {
        Monopatin monopatin = monopatinMapper.toEntity(monopatinDTO);
        monopatinRepository.save(monopatin);
    }

    public MonopatinDTO getMonopatinById(long id) {
        Monopatin monopatin = monopatinRepository.findById(id).orElseThrow();
        return  monopatinMapper.toDTO(monopatin);
    }

    public List<MonopatinDTO> getAllMonopatines() {
        return monopatinRepository.findAll().stream().map(this.monopatinMapper::toDTO).toList();
    }

    public void deleteMonopatinById(long id) {
        monopatinRepository.deleteById(id);
    }

    public void updateMonopatin(MonopatinDTO monopatinDTO, long id) {
        Monopatin m =  monopatinRepository.findById(id).orElseThrow();
        this.monopatinMapper.update(monopatinDTO, m);
        monopatinRepository.save(m);
    }

    public void setMonopatinMantenimiento(long id) {
        Monopatin monopatin = monopatinRepository.findById(id).orElseThrow();
        monopatin.setEstado(EstadoMonopatin.ESTADO_MANTENIMIENTO);
        monopatinRepository.save(monopatin);
    }

    public void setMonopatinLibre(long id){
        Monopatin m = monopatinRepository.findById(id).orElseThrow();
        m.setEstado(EstadoMonopatin.ESTADO_LIBRE);
        monopatinRepository.save(m);
    }

    public void setMonopatinEnUso(long id){
        Monopatin m = monopatinRepository.findById(id).orElseThrow();
        m.setEstado(EstadoMonopatin.ESTADO_EN_USO);
        monopatinRepository.save(m);
    }

    public List<MonopatinDTO> getMonopatinesCerca(double lat, double lon,double radio,int cant) {
        return this.monopatinRepository.findMonopatinesCercanos(lat,lon,radio,cant).stream().map(this.monopatinMapper::toDTO).toList();
    }




}
