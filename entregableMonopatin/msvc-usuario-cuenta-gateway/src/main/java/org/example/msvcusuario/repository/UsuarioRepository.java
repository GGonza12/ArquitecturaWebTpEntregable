package org.example.msvcusuario.repository;

import org.example.msvcusuario.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    @Query(value = """
        SELECT DISTINCT uc2.usuario_id
        FROM usuario_cuentas uc1
        JOIN usuario_cuentas uc2 ON uc1.cuenta_id = uc2.cuenta_id
        WHERE uc1.usuario_id = :idUsuario
          AND uc2.usuario_id <> :idUsuario
    """, nativeQuery = true)
    List<Long> obtenerUsuariosRelacionados(Long idUsuario);

}
