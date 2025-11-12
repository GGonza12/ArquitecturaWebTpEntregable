package org.example.msvcmonopatin.repository;

import org.example.msvcmonopatin.model.Monopatin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MonopatinRepository extends JpaRepository<Monopatin,Long> {
    //Calculo para sacar una lista de los monopatines mas cercanos
    //estado 2 seria monopatin libre
    @Query(value = """
    SELECT *
    FROM monopatin
    WHERE estado = 2
      AND ST_Distance_Sphere(
            POINT(:lon, :lat),
            POINT(longitud, latitud)
        ) <= :radioKm * 1000
    ORDER BY ST_Distance_Sphere(
            POINT(:lon, :lat),
            POINT(longitud, latitud)
        )
    LIMIT :cantidad
""", nativeQuery = true)
    List<Monopatin> findMonopatinesCercanos(
            @Param("lat") double latitud,
            @Param("lon") double longitud,
            @Param("radioKm") double radioKm,
            @Param("cantidad") int cantidad);




}
