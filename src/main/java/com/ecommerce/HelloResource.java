package com.ecommerce;

import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/test-db")
public class HelloResource {

    @PersistenceContext(unitName = "ecommercePU")
    private EntityManager entityManager;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Transactional
    public String testDatabase() {
        try {
            // Crear la tabla si no existe
            entityManager
                    .createNativeQuery(
                            "CREATE TABLE IF NOT EXISTS test_table (id SERIAL PRIMARY KEY, name VARCHAR(50))")
                    .executeUpdate();

            // Insertar un registro de prueba
            entityManager.createNativeQuery("INSERT INTO test_table (name) VALUES ('Dato de prueba')").executeUpdate();

            // Verificar que se insertó correctamente
            Query query = entityManager.createNativeQuery("SELECT COUNT(*) FROM test_table");
            Long count = ((Number) query.getSingleResult()).longValue();

            // Eliminar la tabla
            entityManager.createNativeQuery("DROP TABLE test_table").executeUpdate();

            return "Operación completada. Registros insertados antes de eliminar: " + count;
        } catch (Exception e) {
            return "Error ejecutando la prueba en la base de datos: " + e.getMessage();
        }
    }
}
