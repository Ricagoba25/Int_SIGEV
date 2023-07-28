package mx.edu.utez.sigev.model.DAO;

import mx.edu.utez.sigev.model.BeanVoluntario;
import mx.edu.utez.sigev.utils.MysqlConector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DaoVoluntario {

    Connection conn;
    PreparedStatement ps;
    ResultSet rs;
    MysqlConector mysqlConector = new MysqlConector();
    private static final String INSERT_PERSONA = "INSERT INTO persona (nombrePersona, primerApellido, segundoApellido) VALUES (?, ?, ?)";
    private static final String INSERT_USUARIO = "INSERT INTO usuario (correo, contrasena, telefono) VALUES (?, ?, ?)";
    private static final String INSERT_VOLUNTARIO = "INSERT INTO voluntario (curp) VALUES (?)";

    public void insertarVoluntario(BeanVoluntario voluntario) throws SQLException {
        Connection connPersona = null;
        Connection connUsuario = null;
        try {
            connPersona = mysqlConector.connect();
            connUsuario = mysqlConector.connect();
            connPersona.setAutoCommit(false); // Iniciar transacción
            connUsuario.setAutoCommit(false); // Iniciar transacción

            // Insertar en tabla "persona"
            try (PreparedStatement stmtPersona = connPersona.prepareStatement(INSERT_PERSONA)) {
                System.out.println(" datos 1 " + stmtPersona);
                stmtPersona.setString(1, voluntario.getPersona().getNombrePersona());
                stmtPersona.setString(2, voluntario.getPersona().getPrimerApellido());
                stmtPersona.setString(3, voluntario.getPersona().getSegundoApellido());
                stmtPersona.executeUpdate();
                System.out.println(" datos " + stmtPersona);
            }

            // Obtener el ID generado en tabla "persona"
            int idPersonaGenerado;
            try (PreparedStatement stmtLastId = conn.prepareStatement("SELECT LAST_INSERT_ID()")) {
                try (ResultSet rs = stmtLastId.executeQuery()) {
                    if (rs.next()) {
                        idPersonaGenerado = rs.getInt(1);
                    } else {
                        throw new SQLException("No se pudo obtener el ID generado en tabla 'persona'.");
                    }
                }
            }

            // Insertar en tabla "usuario"
            try (PreparedStatement stmtUsuario = conn.prepareStatement(INSERT_USUARIO)) {
                stmtUsuario.setString(1, voluntario.getUsuario().getCorreo());
                stmtUsuario.setString(2, voluntario.getUsuario().getContrasena());
                stmtUsuario.setString(2, voluntario.getUsuario().getTelefono());
                stmtUsuario.executeUpdate();
            }

            // Obtener el ID generado en tabla "usuario"
            int idUsuarioGenerado;
            try (PreparedStatement stmtLastId = conn.prepareStatement("SELECT LAST_INSERT_ID()")) {
                try (ResultSet rs = stmtLastId.executeQuery()) {
                    if (rs.next()) {
                        idUsuarioGenerado = rs.getInt(1);
                    } else {
                        throw new SQLException("No se pudo obtener el ID generado en tabla 'usuario'.");
                    }
                }
            }

            // Insertar en tabla "voluntario"
            try (PreparedStatement stmtVoluntario = conn.prepareStatement(INSERT_VOLUNTARIO)) {
                stmtVoluntario.setString(1, voluntario.getCurp());
                stmtVoluntario.setInt(2, idPersonaGenerado);
                stmtVoluntario.setInt(3, idUsuarioGenerado);
                stmtVoluntario.executeUpdate();
            }

            conn.commit(); // Confirmar transacción
        } catch (SQLException e) {
            if (conn != null) {
                conn.rollback(); // Hacer rollback en caso de error
            }
            throw e;
        } finally {
            if (conn != null) {
                conn.setAutoCommit(true); // Restaurar el autocommit a su valor por defecto
                conn.close();
            }
        }
    }
}