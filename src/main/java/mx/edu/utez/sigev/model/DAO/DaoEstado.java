package mx.edu.utez.sigev.model.DAO;

import mx.edu.utez.sigev.model.BeanEstado;
import mx.edu.utez.sigev.utils.MysqlConector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DaoEstado implements DaoRepository {
    private Connection con;
    private PreparedStatement pstm;
    private ResultSet rs;

    @Override
    public List findAll() {
        List<BeanEstado> listaBeanEstado = new ArrayList<>();
        try {
            String query = "SELECT * FROM estado";
            con = MysqlConector.connect();
            pstm = con.prepareStatement(query);
            rs = pstm.executeQuery();

            while (rs.next()) {
                BeanEstado beanEstado = new BeanEstado();

                beanEstado.setIdEstado(rs.getInt("idEstado"));
                beanEstado.setNombre(rs.getString("nombre"));
                listaBeanEstado.add(beanEstado);
            }
        } catch (SQLException e) {
            System.err.println("Error en el método findAll() - DaoEstado -> " + e.getMessage());
        } finally {
            cerrarConexiones("findAll");
        }
        return listaBeanEstado;
    }

    @Override
    public Object findOne(int id) {
        BeanEstado beanEstado = new BeanEstado();
        try {
            String query = "SELECT * FROM estado WHERE idEstado = ?";
            con = MysqlConector.connect();
            pstm = con.prepareStatement(query);
            pstm.setInt(1, id);
            rs = pstm.executeQuery();
            if (rs.next()) {
                beanEstado.setIdEstado(rs.getInt("idEstado"));
                beanEstado.setNombre(rs.getString("nombre"));

            }
        } catch (SQLException e) {
            System.err.println("Error en el método findOne() - DaoEstado -> " + e.getMessage());
        } finally {
            cerrarConexiones("findOne");
        }
        return beanEstado;
    }

    @Override
    public boolean update(int id, Object object) {return false;}

    @Override
    public boolean delete(int id) {return false;}

    @Override
    public boolean insert(Object object) {return false;}

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
            System.err.println("Error al cerrar conexiones - DaoColor - en el método " + metodo + " -> " + e.getMessage());
        }
    }
}
