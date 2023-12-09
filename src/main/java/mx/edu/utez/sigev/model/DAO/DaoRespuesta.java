package mx.edu.utez.sigev.model.DAO;

import mx.edu.utez.sigev.model.*;
import mx.edu.utez.sigev.utils.MysqlConector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DaoRespuesta implements DaoRepository {
    private Connection con;
    private PreparedStatement pstm;
    private ResultSet rs;

    @Override
    public List findAll() {
        return null;
    }

    public List respuestasPorVoluntarioEvaluacion(int idVoluntarioEvaluacion) {
        List<BeanRespuesta> listaRespuestas = new ArrayList<>();
        try {
            String query = "SELECT * FROM respuesta r " +
                    "join pregunta p on p.idPregunta = r.pregunta_idPregunta " +
                    "join evaluacion e on e.idEvaluacion = p.evaluacion_idEvaluacion " +
                    "join organizacion o on e.organizacion_idOrganizacion = o.idOrganizacion " +
                    "WHERE r.voluntario_evaluacion_idVoluntarioEvaluacion = ?";

            con = MysqlConector.connect();
            pstm = con.prepareStatement(query);
            pstm.setInt(1, idVoluntarioEvaluacion);
            rs = pstm.executeQuery();

            while (rs.next()) {
                BeanOrganizacion beanOrganizacion = new BeanOrganizacion();
                beanOrganizacion.setIdOrganizacion(rs.getInt("idOrganizacion"));

                BeanEvaluacion beanEvaluacion = new BeanEvaluacion();
                beanEvaluacion.setIdEvaluacion(rs.getInt("idEvaluacion"));
                beanEvaluacion.setNombreEvaluacion(rs.getString("nombreEvaluacion"));
                beanEvaluacion.setOrganizacion(beanOrganizacion);

                BeanPregunta beanPregunta = new BeanPregunta();
                beanPregunta.setIdPregunta(rs.getInt("idPregunta"));
                beanPregunta.setTextoPregunta(rs.getString("textoPregunta"));
                beanPregunta.setEvaluacion(beanEvaluacion);

                BeanVoluntarioEvaluacion beanVoluntarioEvaluacion = new BeanVoluntarioEvaluacion();
                beanVoluntarioEvaluacion.setIdVoluntarioEvaluacion(rs.getInt("voluntario_evaluacion_idVoluntarioEvaluacion"));

                BeanRespuesta beanRespuesta = new BeanRespuesta();
                beanRespuesta.setIdRespuesta(rs.getInt("idRespuesta"));
                beanRespuesta.setTextoRespuesta(rs.getString("textoRespuesta"));
                beanRespuesta.setPregunta(beanPregunta);
                beanRespuesta.setVoluntarioEvaluacion(beanVoluntarioEvaluacion);

                listaRespuestas.add(beanRespuesta);
            }
        } catch (SQLException e) {
            System.err.println("Error en el método respuestasPorVoluntarioEvaluacion() - DaoRespuesta -> " + e.getMessage());
        } finally {
            cerrarConexiones("respuestasPorVoluntarioEvaluacion");
        }
        return listaRespuestas;
    }

    @Override
    public Object findOne(int id) {
        return null;
    }

    @Override
    public boolean update(int id, Object object) {
        return false;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }

    @Override
    public boolean insert(Object object) {
        BeanRespuesta respuesta = (BeanRespuesta) object;
        boolean registrado = false;
        try {
            String query = "INSERT INTO respuesta (textoRespuesta, pregunta_idPregunta, voluntario_evaluacion_idVoluntarioEvaluacion) values(?,?,?)";

            con = MysqlConector.connect();
            pstm = con.prepareStatement(query);
            pstm.setString(1, respuesta.getTextoRespuesta());
            pstm.setInt(2, respuesta.getPregunta().getIdPregunta());
            pstm.setInt(3, respuesta.getVoluntarioEvaluacion().getIdVoluntarioEvaluacion());
            registrado = pstm.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error en el método insert() - DaoRespuesta -> " + e.getMessage());
        } finally {
            cerrarConexiones("insert");
        }
        return registrado;
    }

    private void cerrarConexiones(String metodo) {
        try {
            if (rs != null) {
                rs.close();
            }
            if (pstm != null) {
                pstm.close();
            }
            if (con != null) {
                con.close();
            }
        } catch (SQLException e) {
            System.err.println("Error al cerrar conexiones - DaoRespuesta - en el método " + metodo + " -> " + e.getMessage());
        }
    }
}
