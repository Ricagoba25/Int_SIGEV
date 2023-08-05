package mx.edu.utez.sigev.model.DAO;

import mx.edu.utez.sigev.model.BeanOrganizacion;
import mx.edu.utez.sigev.utils.MysqlConector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DaoOrganizacion implements DaoRepository {
    private Connection con;
    private PreparedStatement pstm;
    private ResultSet rs;

    @Override
    public List findAll() {
        List<BeanOrganizacion> listaBeanOrganizacion = new ArrayList<>();
        try {
            String query = "SELECT * FROM organizacion o " +
                    "join color c on c.idColor = o.color_idColor " +
                    "join direccion d on d.idDireccion = o.direccion_idDireccion " +
                    "join estado e on e.idEstado = d.estado_idEstado " +
                    "join usuario u on o.usuario_idUsuario = u.idUsuario";
            con = MysqlConector.connect();
            pstm = con.prepareStatement(query);
            rs = pstm.executeQuery();

            while (rs.next()) {
                BeanOrganizacion beanOrganizacion = new BeanOrganizacion();
                beanOrganizacion.setIdOrganizacion(rs.getInt("idOrganizacion"));
                beanOrganizacion.setRfc(rs.getString("rfc"));
                beanOrganizacion.setNombreOrganizacion(rs.getString("nombreOrganizacion"));
                beanOrganizacion.setRazonSocial(rs.getString("razonSocial"));
                beanOrganizacion.setImagenLogotipo(rs.getString("imagenLogotipo"));
                beanOrganizacion.setEstatusOrganizacion(rs.getInt("estatusOrganizacion"));
                listaBeanOrganizacion.add(beanOrganizacion);
            }
        } catch (SQLException e) {
            System.err.println("Error en el método findAll() - DaoOrganizacion -> " + e.getMessage());
        } finally {
            cerrarConexiones("findAll");
        }
        return listaBeanOrganizacion;
    }

    @Override
    public Object findOne(int id) {
        BeanOrganizacion beanOrganizacion = new BeanOrganizacion();
        try {
            String query = "SELECT * FROM organizacion o " +
                    "join color c on c.idColor = o.color_idColor " +
                    "join direccion d on d.idDireccion = o.direccion_idDireccion " +
                    "join estado e on e.idEstado = d.estado_idEstado " +
                    "join usuario u on o.usuario_idUsuario = u.idUsuario " +
                    "WHERE idUsuario = ?";
            con = MysqlConector.connect();
            pstm = con.prepareStatement(query);
            pstm.setInt(1, id);
            rs = pstm.executeQuery();
            if (rs.next()) {
                beanOrganizacion.setIdOrganizacion(rs.getInt("idOrganizacion"));
                beanOrganizacion.setNombreOrganizacion(rs.getString("nombreOrganizacion"));
            }
        } catch (SQLException e) {
            System.err.println("Error en el método findOne() - DaoOrganizacion -> " + e.getMessage());
        } finally {
            cerrarConexiones("findOne");
        }
        return beanOrganizacion;
    }

    @Override
    public boolean update(int id, Object object) {
        boolean modificado = false;
        BeanOrganizacion organizacion = (BeanOrganizacion) object;
        try {
            String query = "UPDATE organizacion SET nombreOrganizacion = ?, codigo = ? WHERE idOrganizacion = ?";
            con = MysqlConector.connect();
            pstm = con.prepareStatement(query);
            pstm.setString(1, organizacion.getNombreOrganizacion());
            pstm.setInt(3, organizacion.getIdOrganizacion());
            modificado = pstm.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error en el método update() - DaoOrganizacion -> " + e.getMessage());
        } finally {
            cerrarConexiones("update");
        }
        return modificado;
    }

    @Override
    public boolean delete(int id) {
        boolean eliminado = false;
        try {
            String query = "DELETE FROM organizacion WHERE idOrganizacion = ?";

            con = MysqlConector.connect();
            pstm = con.prepareStatement(query);
            pstm.setInt(1, id);
            eliminado = pstm.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error en el método delete() - DaoOrganizacion -> " + e.getMessage());
        } finally {
            cerrarConexiones("delete");
        }
        return eliminado;
    }

    @Override
    public boolean insert(Object object) {
        BeanOrganizacion organizacion = (BeanOrganizacion) object;
        boolean registrado = false;
        try {
            String query = "INSERT INTO organizacion (nombreOrganizacion, codigo) values(?,?)";

            con = MysqlConector.connect();
            pstm = con.prepareStatement(query);
            pstm.setString(1, organizacion.getNombreOrganizacion());
            registrado = pstm.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error en el método insert() - DaoOrganizacion -> " + e.getMessage());
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
            System.err.println("Error al cerrar conexiones - DaoOrganizacion - en el método " + metodo + " -> " + e.getMessage());
        }
    }
}