package mx.edu.utez.sigev.model.DAO;

import mx.edu.utez.sigev.model.BeanColor;
import mx.edu.utez.sigev.utils.MysqlConector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DaoColor implements DaoRepository {
    private Connection con;
    private PreparedStatement pstm;
    private ResultSet rs;

    @Override
    public List findAll() {
        List<BeanColor> listaBeanColor = new ArrayList<>();
        try {
            String query = "SELECT * FROM color";
            con = MysqlConector.connect();
            pstm = con.prepareStatement(query);
            rs = pstm.executeQuery();

            while (rs.next()) {
                BeanColor beanColor = new BeanColor();
                beanColor.setIdColor(rs.getInt("idColor"));
                beanColor.setNombreColor(rs.getString("nombreColor"));
                beanColor.setCodigo(rs.getString("codigo"));
                listaBeanColor.add(beanColor);
            }
        } catch (SQLException e) {
            System.err.println("Error en el método findAll() - DaoColor -> " + e.getMessage());
        } finally {
            cerrarConexiones("findAll");
        }
        return listaBeanColor;
    }

    @Override
    public Object findOne(int id) {
        BeanColor beanColor = new BeanColor();
        try {
            String query = "SELECT * FROM color WHERE idColor = ?";
            con = MysqlConector.connect();
            pstm = con.prepareStatement(query);
            pstm.setInt(1, id);
            rs = pstm.executeQuery();
            if (rs.next()) {
                beanColor.setIdColor(rs.getInt("idColor"));
                beanColor.setNombreColor(rs.getString("nombreColor"));
                beanColor.setCodigo(rs.getString("codigo"));
            }
        } catch (SQLException e) {
            System.err.println("Error en el método findOne() - DaoColor -> " + e.getMessage());
        } finally {
            cerrarConexiones("findOne");
        }
        return beanColor;
    }

    @Override
    public boolean update(int id, Object object) {
        boolean modificado = false;
        BeanColor color = (BeanColor) object;
        try {
            String query = "UPDATE color SET nombreColor = ?, codigo = ? WHERE idColor = ?";
            con = MysqlConector.connect();
            pstm = con.prepareStatement(query);
            pstm.setString(1, color.getNombreColor());
            pstm.setString(2, color.getCodigo());
            pstm.setInt(3, color.getIdColor());
            modificado = pstm.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error en el método update() - DaoColor -> " + e.getMessage());
        } finally {
            cerrarConexiones("update");
        }
        return modificado;
    }

    @Override
    public boolean delete(int id) {
        boolean eliminado = false;
        try {
            String query = "DELETE FROM color WHERE idColor = ?";

            con = MysqlConector.connect();
            pstm = con.prepareStatement(query);
            pstm.setInt(1, id);
            eliminado = pstm.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error en el método delete() - DaoColor -> " + e.getMessage());
        } finally {
            cerrarConexiones("delete");
        }
        return eliminado;
    }

    @Override
    public boolean insert(Object object) {
        BeanColor color = (BeanColor) object;
        boolean registrado = false;
        try {
            String query = "INSERT INTO color (nombreColor, codigo) values(?,?)";

            con = MysqlConector.connect();
            pstm = con.prepareStatement(query);
            pstm.setString(1, color.getNombreColor());
            pstm.setString(2, color.getCodigo());
            registrado = pstm.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error en el método insert() - DaoColor -> " + e.getMessage());
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
            System.err.println("Error al cerrar conexiones - DaoColor - en el método " + metodo + " -> " + e.getMessage());
        }
    }
}
