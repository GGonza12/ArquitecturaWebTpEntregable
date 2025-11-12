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

        public double calcularTotalFacturado(int year, int mesInicio, int mesFin) {
            // validar meses
            if (mesInicio < 1 || mesInicio > 12 || mesFin < 1 || mesFin > 12) {
                throw new IllegalArgumentException("mesInicio/mesFin deben estar entre 1 y 12");
            }

            // construir rango usando YearMonth
            YearMonth ymStart = YearMonth.of(year, mesInicio);
            YearMonth ymEnd = YearMonth.of(year, mesFin);
            // inicio
            var startLdt = ymStart.atDay(1).atStartOfDay();
            // fin
            var endLdt = ymEnd.atEndOfMonth().atTime(23, 59, 59, 999_000_000);

            Timestamp desde = Timestamp.valueOf(startLdt);
            Timestamp hasta = Timestamp.valueOf(endLdt);

            List<ViajeDTO> viajes = viajeClient.obtenerViajesEntreFechas(desde, hasta);

            double total = 0.0;
            for (ViajeDTO v : viajes) {
                Date fechaInicioDate = v.getFechaInicio();
                if (fechaInicioDate == null) continue;

                Timestamp inicioTs = new Timestamp(fechaInicioDate.getTime());


                Timestamp finTs;
                if (v.getFechaFin() != null) {
                    finTs = new Timestamp(v.getFechaFin().getTime());
                } else {
                    finTs = new Timestamp(System.currentTimeMillis());
                }

                // obtener precio aplicable en la fecha de inicio
                Optional<Precio> precioOpt = precioRepository.findLatestPrecio(inicioTs);

                Precio price = precioOpt.get();

                long minutos = calcularDuracion(inicioTs, finTs);
                if (minutos <= 0) continue;

                total += price.getPrecio() * minutos;
            }

            return total;
        }

        private long calcularDuracion(Timestamp inicio, Timestamp fin) {
            if (inicio == null || fin == null) return 0;
            if (fin.before(inicio)) return 0;
            return java.time.Duration.between(inicio.toInstant(), fin.toInstant()).toMinutes();
        }

}
