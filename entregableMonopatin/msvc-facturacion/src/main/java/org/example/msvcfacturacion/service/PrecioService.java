package org.example.msvcfacturacion.service;

import org.example.msvcfacturacion.dto.PrecioDTO;
import org.example.msvcfacturacion.dto.ViajeDTO;
import org.example.msvcfacturacion.model.Precio;
import org.example.msvcfacturacion.repository.PrecioRepository;
import org.example.msvcfacturacion.utils.PrecioMapper;
import org.example.msvcfacturacion.utils.ViajeClient;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

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

        public double calcularTotalFacturado(int year, int mesInicio, int mesFin) {
            LocalDateTime start = LocalDateTime.of(year, mesInicio, 1, 0, 0);
            LocalDateTime end = LocalDateTime.of(year, mesFin, 31, 23, 59);
            Timestamp desde = Timestamp.valueOf(start);
            Timestamp hasta = Timestamp.valueOf(end);

            List<ViajeDTO> viajes = viajeClient.obtenerViajesEntreFechas(desde, hasta);

            double total = 0;
            for (ViajeDTO v : viajes) {
                Precio price = precioRepository.findBySinceBefore(v.getFechaInicio());
                total += price.getPrecio() * calcularDuracion(v.getFechaInicio(), v.getFechaFin());
            }

            return total;
        }

        private long calcularDuracion(Timestamp inicio, Timestamp fin) {
            return Duration.between(inicio.toInstant(), fin.toInstant()).toMinutes();
        }

}
