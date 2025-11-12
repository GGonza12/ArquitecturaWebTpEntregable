package org.example.msvcviaje.repository;

import org.example.msvcviaje.dto.UsuarioViajeDTO;
import org.example.msvcviaje.model.Pausa;
import org.example.msvcviaje.model.Viaje;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.Update;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.sql.Timestamp;
import java.util.List;

@Repository
public interface ViajeRepository  extends MongoRepository<Viaje,String> {


    @Query("{ '_id': ?0 }")
    @Update("{ '$push': { 'pausas': ?1 } }")
    void agregarPausa(String idViaje, Pausa pausa);

    List<Viaje> findByFechaInicioBetween(Timestamp desde, Timestamp hasta);

    @Aggregation(pipeline = {
            "{ $match: { fechaInicio: { $gte: ?0, $lte: ?1 } } }",
            "{ $group: { _id: '$idUsuario', cantidadViajes: { $sum: 1 } } }",
            "{ $project: { _id: 0, idUsuario: '$_id', cantidadViajes: 1 } }"
    })
    List<UsuarioViajeDTO> obtenerRankingUsuariosPorPeriodo(Date fechaInicio, Date fechaFin);
}
