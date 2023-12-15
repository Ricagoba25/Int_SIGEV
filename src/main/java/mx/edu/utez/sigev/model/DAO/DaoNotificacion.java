package mx.edu.utez.sigev.model.DAO;

import mx.edu.utez.sigev.model.BeanNotificacion;
import mx.edu.utez.sigev.utils.MysqlConector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DaoNotificacion implements DaoRepository {
    private Connection con;
    private PreparedStatement pstm;
    private ResultSet rs;

    @Override
    public List findAll() {
        List<BeanNotificacion> listaBeanNotificacion = new ArrayList<>();
        try {
            String query = "SELECT * FROM notificacion";
            con = MysqlConector.connect();
            pstm = con.prepareStatement(query);
            rs = pstm.executeQuery();

            while (rs.next()) {
                BeanNotificacion beanNotificacion = new BeanNotificacion();
                beanNotificacion.setIdNotificacion(rs.getInt("idNotificacion"));
                beanNotificacion.setMensaje(rs.getString("mensaje"));
                beanNotificacion.setEstatusNotificacion(rs.getInt("estatusNotificacion"));

                listaBeanNotificacion.add(beanNotificacion);
            }
        } catch (SQLException e) {
            System.err.println("Error en el método findAll() - DaoNotificacion -> " + e.getMessage());
        } finally {
            cerrarConexiones("findAll");
        }
        return listaBeanNotificacion;
    }

    @Override
    public Object findOne(int id) {
        BeanNotificacion beanNotificacion = new BeanNotificacion();
        try {
            String query = "SELECT * FROM notificacion WHERE idNotificacion = ?";
            con = MysqlConector.connect();
            pstm = con.prepareStatement(query);
            pstm.setInt(1, id);
            rs = pstm.executeQuery();
            if (rs.next()) {
                beanNotificacion.setIdNotificacion(rs.getInt("idNotificacion"));
                beanNotificacion.setMensaje(rs.getString("mensaje"));
                beanNotificacion.setEstatusNotificacion(rs.getInt("estatusNotificacion"));

            }
        } catch (SQLException e) {
            System.err.println("Error en el método findOne() - DaoNotificacion -> " + e.getMessage());
        } finally {
            cerrarConexiones("findOne");
        }
        return beanNotificacion;
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
        return false;
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
            System.err.println("Error al cerrar conexiones - DaoNotificacion - en el método " + metodo + " -> " + e.getMessage());
        }
    }
}
