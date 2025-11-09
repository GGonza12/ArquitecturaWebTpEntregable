package org.example.msvcfacturacion.repository;

import org.example.msvcfacturacion.model.Precio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.sql.Timestamp;

public interface PrecioRepository extends JpaRepository<Precio, Long> {

    @Query("select p from precio p where p.fechaVigencia < ?1 order by p.fechaVigencia desc limit 1")
    Precio findActualPrice(Timestamp since);


}
