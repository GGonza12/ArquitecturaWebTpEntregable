package org.example.msvcfacturacion.repository;

import org.example.msvcfacturacion.model.Precio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.Optional;
import java.sql.Timestamp;

public interface PrecioRepository extends JpaRepository<Precio, Long> {

    @Query(value = "SELECT * FROM precio p WHERE p.fecha_vigencia <= :fecha ORDER BY p.fecha_vigencia DESC LIMIT 1",
           nativeQuery = true)
    Optional<Precio> findLatestPrecio(@Param("fecha") Timestamp fecha);

    @Query("select p from Precio p where p.fechaVigencia < ?1 order by p.fechaVigencia desc limit 1")
    Precio findActualPrice(java.sql.Timestamp fecha);

    Precio findByFechaVigenciaBefore(Timestamp fecha);


}
