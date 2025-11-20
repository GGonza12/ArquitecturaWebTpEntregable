package org.example.msvcusuario.service;

import org.example.msvcusuario.dto.CuentaDTO;
import org.example.msvcusuario.model.Cuenta;
import org.example.msvcusuario.repository.CuentaRepository;
import org.example.msvcusuario.utils.CuentaMapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Component
public class CuentaService {
    private final CuentaRepository cuentaRepository;
    private final CuentaMapper cuentaMapper;

    public CuentaService(CuentaRepository cuentaRepository, CuentaMapper cuentaMapper) {
        this.cuentaRepository = cuentaRepository;
        this.cuentaMapper = cuentaMapper;
    }

    public void createConDTO(CuentaDTO dto){
        Cuenta c = new Cuenta(dto.getFondos(), dto.getPlan(), dto.getUsuarios());
        cuentaRepository.save(c);
    }

    public void crearCuenta(Long idUsuario){
        Cuenta c = new Cuenta(idUsuario);
        cuentaRepository.save(c);
    }

    public List<CuentaDTO> findAll(){
        return this.cuentaRepository.findAll().stream().map(this.cuentaMapper::toDTO).toList();
    }

    public CuentaDTO findById(long id){
        Cuenta cuenta = this.cuentaRepository.findById(id).orElseThrow();
        return this.cuentaMapper.toDTO(cuenta);
    }

    public void update(CuentaDTO dto,long id){
        Cuenta cuenta = this.cuentaRepository.findById(id).orElseThrow();
        this.cuentaMapper.update(dto, cuenta);
        this.cuentaRepository.save(cuenta);
    }

    public void delete(long id){
        this.cuentaRepository.deleteById(id);
    }

    public void desactivarCuenta(long id){
        Cuenta cuenta = this.cuentaRepository.findById(id).orElseThrow();
        cuenta.setDeshabilitada(true);
        this.cuentaRepository.save(cuenta);
    }

    public void agregarFondos(Long id,double fondos){
        Cuenta c = this.cuentaRepository.findById(id).orElseThrow();
        float total = (float) (c.getFondos() + fondos);
        c.setFondos(total);
        this.cuentaRepository.save(c);
    }



}
