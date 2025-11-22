package org.example.msvcfacturacion.service;

import org.example.msvcfacturacion.dto.PrecioDTO;
import org.example.msvcfacturacion.dto.ViajeDTO;
import org.example.msvcfacturacion.model.Precio;
import org.example.msvcfacturacion.repository.PrecioRepository;
import org.example.msvcfacturacion.utils.PrecioMapper;
import org.example.msvcfacturacion.utils.ViajeClient;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PrecioService {
        private final PrecioRepository precioRepository;
        private final PrecioMapper precioMapper;
        private final ViajeClient viajeClient;

        public PrecioService(PrecioRepository precioRepository, PrecioMapper precioMapper, ViajeClient viajeClient) {
            this.precioRepository = precioRepository;
            this.precioMapper = precioMapper;
            this.viajeClient = viajeClient;
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

        public void finalizarViaje(String id){
            ViajeDTO dto = this.viajeClient.obtenerViaje(id);
            long minutosViaje = dto.getMinutosPausa();
            Precio p = this.precioRepository.findActualPrice(Timestamp.valueOf(LocalDateTime.now()));
            if(minutosViaje>=15){
                this.viajeClient.finalizarViaje(id,p.getPrecioPenalizacion());
            }
            else {
                this.viajeClient.finalizarViaje(id,p.getPrecio());
            }
        }

    public void finalizarViajeCompleto(String id,double latitud, double longitud){
        ViajeDTO dto = this.viajeClient.obtenerViaje(id);
        long minutosViaje = dto.getMinutosPausa();
        Precio p = this.precioRepository.findActualPrice(Timestamp.valueOf(LocalDateTime.now()));
        if(minutosViaje>=15){
            this.viajeClient.finalizarViajeCompleto(id,p.getPrecioPenalizacion(), latitud, longitud);
        }
        else {
            this.viajeClient.finalizarViajeCompleto(id,p.getPrecio(),latitud, longitud);
        }
    }



        private long calcularDuracion(Timestamp inicio, Timestamp fin) {
            if (inicio == null || fin == null) return 0;
            if (fin.before(inicio)) return 0;
            return java.time.Duration.between(inicio.toInstant(), fin.toInstant()).toMinutes();
        }

}
