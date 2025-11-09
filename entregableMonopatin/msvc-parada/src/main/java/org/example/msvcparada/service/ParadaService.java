package org.example.msvcparada.service;

import org.example.msvcparada.dto.ParadaDTO;
import org.example.msvcparada.model.Parada;
import org.example.msvcparada.repository.ParadaRepository;
import org.example.msvcparada.utils.ParadaMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParadaService {
    private final ParadaRepository paradaRepository;
    private final ParadaMapper paradaMapper;

    public ParadaService(ParadaRepository paradaRepository, ParadaMapper paradaMapper) {
        this.paradaRepository = paradaRepository;
        this.paradaMapper = paradaMapper;
    }

    public void createParada(ParadaDTO parada){
        Parada p = this.paradaMapper.toEntity(parada);
        this.paradaRepository.save(p);
    }

    public ParadaDTO getParadaById(Long id){
        return paradaMapper.toDTO(this.paradaRepository.findById(id).orElseThrow());
    }

    public List<ParadaDTO> getParadas(){
        return this.paradaRepository.findAll().stream().map(this.paradaMapper::toDTO).toList();
    }

    public void deleteParada(Long id){
        this.paradaRepository.deleteById(id);
    }

    public void updateParada(ParadaDTO paradaDTO,long id){
        Parada p = this.paradaRepository.findById(id).orElseThrow();
        this.paradaMapper.update(paradaDTO,p);
        this.paradaRepository.save(p);
    }





}
