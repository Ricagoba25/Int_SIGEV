package mx.edu.utez.sigev.model.DAO;

import mx.edu.utez.sigev.model.BeanEvaluacion;
import mx.edu.utez.sigev.model.BeanOrganizacion;
import mx.edu.utez.sigev.model.BeanPregunta;
import mx.edu.utez.sigev.utils.MysqlConector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DaoPregunta implements DaoRepository {
    private Connection con;
    private PreparedStatement pstm;
    private ResultSet rs;

    @Override
    public List findAll() {
        return null;
    }

    public List preguntasPorEvaluacion(int idEvaluacion) {
        List<BeanPregunta> listaPreguntas = new ArrayList<>();
        try {
            String query = "SELECT * FROM pregunta p " +
                    "join evaluacion e on e.idEvaluacion = p.evaluacion_idEvaluacion " +
                    "join organizacion o on e.organizacion_idOrganizacion = o.idOrganizacion " +
                    "WHERE e.idEvaluacion = ?";

            con = MysqlConector.connect();
            pstm = con.prepareStatement(query);
            pstm.setInt(1, idEvaluacion);
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

                listaPreguntas.add(beanPregunta);
            }
        } catch (SQLException e) {
            System.err.println("Error en el método preguntasPorEvaluacion() - DaoPregunta -> " + e.getMessage());
        } finally {
            cerrarConexiones("preguntasPorEvaluacion");
        }
        return listaPreguntas;
    }

    @Override
    public Object findOne(int id) {
        return null;
    }

    @Override
    public boolean update(int id, Object object) {
        boolean modificado = false;
        BeanPregunta pregunta = (BeanPregunta) object;
        try {
            String query = "UPDATE pregunta SET textoPregunta = ? WHERE idPregunta = ?";
            con = MysqlConector.connect();
            pstm = con.prepareStatement(query);

            pstm.setString(1, pregunta.getTextoPregunta());
            pstm.setInt(2, pregunta.getIdPregunta());

            modificado = pstm.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error en el método update() - DaoPregunta -> " + e.getMessage());
        } finally {
            cerrarConexiones("update");
        }
        return modificado;
    }

    @Override
    public boolean delete(int id) {
        boolean eliminado = false;
        try {
            String query = "DELETE FROM pregunta WHERE idPregunta = ?";

            con = MysqlConector.connect();
            pstm = con.prepareStatement(query);
            pstm.setInt(1, id);
            eliminado = pstm.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error en el método delete() - DaoPregunta -> " + e.getMessage());
        } finally {
            cerrarConexiones("delete");
        }
        return eliminado;
    }

    @Override
    public boolean insert(Object object) {
        BeanPregunta pregunta = (BeanPregunta) object;
        System.out.println("textoPregunta " + pregunta.getTextoPregunta());
        System.out.println("evaluacion_idEvaluacion " + pregunta.getEvaluacion().getIdEvaluacion());
        boolean registrado = false;
        try {
            String query = "INSERT INTO pregunta (textoPregunta, evaluacion_idEvaluacion) values(?,?)";

            con = MysqlConector.connect();
            pstm = con.prepareStatement(query);
            pstm.setString(1, pregunta.getTextoPregunta());
            pstm.setInt(2, pregunta.getEvaluacion().getIdEvaluacion());
            registrado = pstm.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error en el método insert() - DaoPregunta -> " + e.getMessage());
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
            System.err.println("Error al cerrar conexiones - DaoPregunta - en el método " + metodo + " -> " + e.getMessage());
        }
    }
}
