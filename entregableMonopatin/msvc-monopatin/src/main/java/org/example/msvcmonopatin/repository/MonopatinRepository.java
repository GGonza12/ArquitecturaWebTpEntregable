package org.example.msvcmonopatin.repository;

import jakarta.transaction.Transactional;
import org.example.msvcmonopatin.dto.MonopatinDTO;
import org.example.msvcmonopatin.model.EstadoMonopatin;
import org.example.msvcmonopatin.model.Monopatin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MonopatinRepository extends JpaRepository<Monopatin,Long> {
   /* @Modifying
    @Transactional
    @Query("UPDATE Monopatin m SET m.estado = :nuevoEstado WHERE m.id = :id")
    int actualizarEstado(@Param("id") Long id, @Param("nuevoEstado") EstadoMonopatin nuevoEstado);
    */
    //Calculo para sacar una lista de los monopatines mas cercanos
    @Query(value = """

            SELECT *, ST_Distance_Sphere(
        POINT(:lon, :lat),
        POINT(longitud, latitud)
    ) / 1000 AS distancia
    FROM monopatin
    WHERE estado = 'ESTADO_LIBRE'
    HAVING distancia <= :radio
    ORDER BY distancia ASC
    LIMIT :cantidad
    """, nativeQuery = true)
    List<Monopatin> findMonopatinesCercanos(
            @Param("lat") double latitud,
            @Param("lon") double longitud,
            @Param("radio") double radioKm,
            @Param("cantidad") int cantidad);




    }

