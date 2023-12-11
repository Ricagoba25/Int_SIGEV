package mx.edu.utez.sigev.model.DAO;

import mx.edu.utez.sigev.model.BeanEvaluacion;
import mx.edu.utez.sigev.model.BeanOrganizacion;
import mx.edu.utez.sigev.utils.MysqlConector;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DaoEvaluacion implements DaoRepository {
    private Connection con;
    private PreparedStatement pstm;
    private ResultSet rs;

    @Override
    public List findAll() {
        return null;
    }

    public List<BeanEvaluacion> evaluacionesPorOrganizacion(int idOrganizacion) {
        List<BeanEvaluacion> listaEvaluaciones = new ArrayList<>();
        try {
            String query = "SELECT * FROM evaluacion e join organizacion o " +
                    "on e.organizacion_idOrganizacion = o.idOrganizacion WHERE o.idOrganizacion = ?";

            con = MysqlConector.connect();
            pstm = con.prepareStatement(query);
            pstm.setInt(1, idOrganizacion);
            rs = pstm.executeQuery();

            while (rs.next()) {
                BeanOrganizacion beanOrganizacion = new BeanOrganizacion();
                beanOrganizacion.setIdOrganizacion(rs.getInt("idOrganizacion"));
                beanOrganizacion.setRfc(rs.getString("rfc"));
                beanOrganizacion.setNombreOrganizacion(rs.getString("nombreOrganizacion"));

                BeanEvaluacion beanEvaluacion = new BeanEvaluacion();
                beanEvaluacion.setIdEvaluacion(rs.getInt("idEvaluacion"));
                beanEvaluacion.setNombreEvaluacion(rs.getString("nombreEvaluacion"));
                beanEvaluacion.setEstatusEvaluacion(rs.getInt("estatusEvaluacion"));

                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String dateInString = rs.getString("fechaRegistro");
                Date date = formatter.parse(dateInString);
                beanEvaluacion.setFechaRegistro(date);
                beanEvaluacion.setOrganizacion(beanOrganizacion);

                listaEvaluaciones.add(beanEvaluacion);
            }
        } catch (SQLException e) {
            System.err.println("Error en el método evaluacionesPorOrganizacion() - DaoEvaluacion -> " + e.getMessage());
        } catch (ParseException e) {
            throw new RuntimeException(e);
        } finally {
            cerrarConexiones("evaluacionesPorOrganizacion");
        }
        return listaEvaluaciones;
    }

    public List<BeanEvaluacion> evaluacionesActivas(int idOrganizacion) {
        List<BeanEvaluacion> listaEvaluaciones = new ArrayList<>();
        try {
            String query = "SELECT * FROM evaluacion e join organizacion o " +
                    "on e.organizacion_idOrganizacion = o.idOrganizacion " +
                    "WHERE e.estatusEvaluacion = 1 AND o.idOrganizacion = ?";

            con = MysqlConector.connect();
            pstm = con.prepareStatement(query);
            pstm.setInt(1, idOrganizacion);
            rs = pstm.executeQuery();

            while (rs.next()) {
                BeanOrganizacion beanOrganizacion = new BeanOrganizacion();
                beanOrganizacion.setIdOrganizacion(rs.getInt("idOrganizacion"));
                beanOrganizacion.setNombreOrganizacion(rs.getString("nombreOrganizacion"));

                BeanEvaluacion beanEvaluacion = new BeanEvaluacion();
                beanEvaluacion.setIdEvaluacion(rs.getInt("idEvaluacion"));
                beanEvaluacion.setNombreEvaluacion(rs.getString("nombreEvaluacion"));
                beanEvaluacion.setFechaRegistro(rs.getDate("fechaRegistro"));
                beanEvaluacion.setOrganizacion(beanOrganizacion);

                listaEvaluaciones.add(beanEvaluacion);
            }
        } catch (SQLException e) {
            System.err.println("Error en el método evaluacionesActivas() - DaoEvaluacion -> " + e.getMessage());
        } finally {
            cerrarConexiones("evaluacionesActivas");
        }
        return listaEvaluaciones;
    }

    @Override
    public Object findOne(int id) {
        return null;
    }

    @Override
    public boolean update(int id, Object object) {
        boolean modificado = false;
        BeanEvaluacion evaluacion = (BeanEvaluacion) object;
        try {
            String query = "UPDATE evaluacion SET nombreEvaluacion = ? WHERE idEvaluacion = ?";
            con = MysqlConector.connect();
            pstm = con.prepareStatement(query);

            pstm.setString(1, evaluacion.getNombreEvaluacion());
            pstm.setInt(2, id);

            modificado = pstm.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error en el método update() - DaoEvaluacion -> " + e.getMessage());
        } finally {
            cerrarConexiones("update");
        }
        return modificado;
    }

    @Override
    public boolean delete(int id) {
        boolean eliminado = false;
        try {
            String query = "DELETE FROM evaluacion WHERE idEvaluacion = ?";

            con = MysqlConector.connect();
            pstm = con.prepareStatement(query);
            pstm.setInt(1, id);
            eliminado = pstm.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error en el método delete() - DaoEvaluacion -> " + e.getMessage());
        } finally {
            cerrarConexiones("delete");
        }
        return eliminado;
    }

    public boolean changeStatus(int id, int estatus) {
        boolean modificado = false;
        try {
            String query = "UPDATE evaluacion SET estatusEvaluacion = ? WHERE idEvaluacion = ?";

            con = MysqlConector.connect();
            pstm = con.prepareStatement(query);
            pstm.setInt(1, estatus);
            pstm.setInt(2, id);
            modificado = pstm.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error en el método changeStatus() - DaoEvaluacion -> " + e.getMessage());
        } finally {
            cerrarConexiones("changeStatus");
        }
        return modificado;
    }

    @Override
    public boolean insert(Object object) {
        return false;
    }

    public int registrar(Object object) {
        BeanEvaluacion evaluacion = (BeanEvaluacion) object;
        boolean registrado;
        int idRegistro = 0;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String fecha = sdf.format(evaluacion.getFechaRegistro());
        try {
            String query = "INSERT INTO evaluacion (nombreEvaluacion, fechaRegistro, organizacion_idOrganizacion) values(?,?,?)";

            con = MysqlConector.connect();
            pstm = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            pstm.setString(1, evaluacion.getNombreEvaluacion());
            pstm.setObject(2, fecha);
            pstm.setInt(3, evaluacion.getOrganizacion().getIdOrganizacion());

            registrado = pstm.executeUpdate() > 0;

            if (registrado) {
                rs = pstm.getGeneratedKeys();

                System.out.println("getGenerateKEy: " + rs);
                if (rs.next()) {
                    idRegistro = rs.getInt(1);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error en el método registrar() - DaoEvaluacion -> " + e.getMessage());
        } finally {
            cerrarConexiones("registrar");
        }
        return idRegistro;
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
            System.err.println("Error al cerrar conexiones - DaoEvaluacion - en el método " + metodo + " -> " + e.getMessage());
        }
    }
}
