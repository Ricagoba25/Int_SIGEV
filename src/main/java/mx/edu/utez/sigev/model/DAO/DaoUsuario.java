package mx.edu.utez.sigev.model.DAO;

import mx.edu.utez.sigev.model.BeanRol;
import mx.edu.utez.sigev.model.BeanUsuario;
import mx.edu.utez.sigev.utils.MysqlConector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DaoUsuario implements DaoRepository {
    private Connection con;
    private PreparedStatement pstm;
    private ResultSet rs;

    @Override
    public List findAll() {
        BeanUsuario beanUsuario = new BeanUsuario();





    }

    @Override
    public Object findOne(int id) {
        BeanUsuario beanUsuario = new BeanUsuario();
        try {
            String query = "SELECT * FROM usuario u join rol r on u.rol_idRol = r.idRol WHERE u.idUsuario = ?";
            con = MysqlConector.connect();
            pstm = con.prepareStatement(query);
            pstm.setInt(1, id);
            rs = pstm.executeQuery();
            if (rs.next()) {
                beanUsuario.setIdUsuario(rs.getInt("idUsuario"));
                beanUsuario.setCorreo(rs.getString("correo"));
                beanUsuario.setContrasena(rs.getString("contrasena"));
                beanUsuario.setTelefono(rs.getString("telefono"));
                beanUsuario.setEstatusUsuario(rs.getInt("estatusUsuario"));

                BeanRol beanRol = new BeanRol();
                beanRol.setIdRol(rs.getInt("idRol"));
                beanRol.setNombreRol(rs.getString("nombreRol"));
                beanUsuario.setRol(beanRol);
            }
        } catch (SQLException e) {
            System.err.println("Error en el método findOne() - DaoUsuario -> " + e.getMessage());
        } finally {
            cerrarConexiones("findOne");
        }
        return beanUsuario;
    }

    public Object findbyCorreo(String correo) {
        BeanUsuario beanUsuario = new BeanUsuario();
        try {
            String query = "SELECT * FROM usuario u join rol r on u.rol_idRol = r.idRol WHERE u.correo = ?";
            con = MysqlConector.connect();
            pstm = con.prepareStatement(query);
            pstm.setString(1, correo);
            rs = pstm.executeQuery();
            if (rs.next()) {
                beanUsuario.setIdUsuario(rs.getInt("idUsuario"));
                beanUsuario.setCorreo(rs.getString("correo"));
                beanUsuario.setContrasena(rs.getString("contrasena"));
                beanUsuario.setTelefono(rs.getString("telefono"));
                beanUsuario.setEstatusUsuario(rs.getInt("estatusUsuario"));

                BeanRol beanRol = new BeanRol();
                beanRol.setIdRol(rs.getInt("idRol"));
                beanRol.setNombreRol(rs.getString("nombreRol"));
                beanUsuario.setRol(beanRol);
            }
        } catch (SQLException e) {
            System.err.println("Error en el método findOne() - DaoUsuario -> " + e.getMessage());
        } finally {
            cerrarConexiones("findOne");
        }
        return beanUsuario;
    }

    public BeanUsuario iniciarSesion(String correo, String contrasena) {
        BeanUsuario beanUsuario = new BeanUsuario();
        try {
            String query = "SELECT * FROM usuario u join rol r on u.rol_idRol = r.idRol WHERE correo = ? AND contrasena = sha2(?,256)";
            con = MysqlConector.connect();
            pstm = con.prepareStatement(query);
            pstm.setString(1, correo);
            pstm.setString(2, contrasena);
            rs = pstm.executeQuery();
            if (rs.next()) {
                beanUsuario.setIdUsuario(rs.getInt("idUsuario"));
                beanUsuario.setCorreo(rs.getString("correo"));
                beanUsuario.setContrasena(rs.getString("contrasena"));
                beanUsuario.setTelefono(rs.getString("telefono"));
                beanUsuario.setEstatusUsuario(rs.getInt("estatusUsuario"));

                BeanRol beanRol = new BeanRol();
                beanRol.setIdRol(rs.getInt("idRol"));
                beanRol.setNombreRol(rs.getString("nombreRol"));
                beanUsuario.setRol(beanRol);
            }
        } catch (SQLException e) {
            System.err.println("Error en el método iniciarSesion() - DaoUsuario -> " + e.getMessage());
        } finally {
            cerrarConexiones("iniciarSesion");
        }
        return beanUsuario;
    }

    @Override
    public boolean update(int id, Object object) {
        boolean modificado = false;
        BeanUsuario usuario = (BeanUsuario) object;
        try {
            String query = "UPDATE usuario SET correo = ?, contrasena = sha2(?,256), telefono = ? WHERE idUsuario = ?";
            con = MysqlConector.connect();
            pstm = con.prepareStatement(query);
            pstm.setString(1, usuario.getCorreo());
            pstm.setString(2, usuario.getContrasena());
            pstm.setString(3, usuario.getTelefono());
            pstm.setInt(4, usuario.getIdUsuario());
            modificado = pstm.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error en el método update() - DaoUsuario -> " + e.getMessage());
        } finally {
            cerrarConexiones("update");
        }
        return modificado;
    }

    @Override
    public boolean delete(int id) {
        boolean eliminado = false;
        try {
            String query = "UPDATE usuario SET estatusUsuario = ? WHERE idUsuario = ?";

            con = MysqlConector.connect();
            pstm = con.prepareStatement(query);
            pstm.setInt(1, 0);
            pstm.setInt(2, id);
            eliminado = pstm.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error en el método delete() - DaoUsuario -> " + e.getMessage());
        } finally {
            cerrarConexiones("delete");
        }
        return eliminado;
    }

    @Override
    public boolean insert(Object object) {
        BeanUsuario usuario = (BeanUsuario) object;
        boolean registrado = false;
        try {
            String query = "INSERT INTO usuario (correo, contrasena, telefono, rol_idRol) values(?,?,?,?)";

            con = MysqlConector.connect();
            pstm = con.prepareStatement(query);
            pstm.setString(1, usuario.getCorreo());
            pstm.setString(2, usuario.getContrasena());
            pstm.setString(3, usuario.getTelefono());
            pstm.setInt(4, usuario.getRol().getIdRol());
            registrado = pstm.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error en el método insert() - DaoUsuario -> " + e.getMessage());
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
            System.err.println("Error al cerrar conexiones - DaoUsuario - en el método " + metodo + " -> " + e.getMessage());
        }
    }
}
