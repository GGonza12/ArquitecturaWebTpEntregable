package org.example.msvcfacturacion.service;

import org.example.msvcfacturacion.dto.PrecioDTO;
import org.example.msvcfacturacion.model.Precio;
import org.example.msvcfacturacion.repository.PrecioRepository;
import org.example.msvcfacturacion.utils.PrecioMapper;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Service
public class PrecioService {
        private final PrecioRepository precioRepository;
        private final PrecioMapper precioMapper;

        public PrecioService(PrecioRepository precioRepository, PrecioMapper precioMapper) {
            this.precioRepository = precioRepository;
            this.precioMapper = precioMapper;
        }

        public void savePrecio(PrecioDTO precioDTO) {
            this.precioRepository.save(precioMapper.toEntity(precioDTO));
        }

        public double getPrecio(){
            Precio p = this.precioRepository.findActualPrice(Timestamp.valueOf(LocalDateTime.now()));
            return p.getPrecio();
        }

        public void actualizarPrecioPenalizacion(Long id, double precioNuevo){
            Precio p = this.precioRepository.findById(id).orElseThrow();
            p.setPrecioPenalizacion(precioNuevo);
            this.precioRepository.save(p);
        }

}
