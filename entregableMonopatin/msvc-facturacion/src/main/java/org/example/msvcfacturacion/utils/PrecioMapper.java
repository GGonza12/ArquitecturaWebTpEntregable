package org.example.msvcfacturacion.utils;

import lombok.NoArgsConstructor;
import org.example.msvcfacturacion.dto.PrecioDTO;
import org.example.msvcfacturacion.model.Precio;
import org.springframework.stereotype.Component;

@NoArgsConstructor
@Component
public class PrecioMapper {
    public PrecioDTO toDTO(Precio p) {
        return new PrecioDTO(p.getPrecio(),p.getPrecioPenalizacion(),p.getFechaVigencia());
    }

    public Precio toEntity(PrecioDTO dto) {
        return new Precio(dto.getPrecio(),dto.getPrecioPenalizacion(),dto.getFechaVigencia());
    }

    public void update(PrecioDTO dto, Precio c) {
        c.setPrecioPenalizacion(c.getPrecioPenalizacion());
        c.setFechaVigencia(c.getFechaVigencia());
        c.setPrecio(c.getPrecio());
    }


}
