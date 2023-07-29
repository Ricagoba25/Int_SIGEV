package mx.edu.utez.sigev.model.DAO;

import mx.edu.utez.sigev.model.BeanPersona;
import mx.edu.utez.sigev.model.BeanRol;
import mx.edu.utez.sigev.model.BeanUsuario;
import mx.edu.utez.sigev.utils.MysqlConector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class DaoPersona implements DaoRepository {
    private Connection con;
    private PreparedStatement pstm;
    private ResultSet rs;

    @Override
    public List findAll() {
        return null;
    }


    @Override
    public Object findOne(int id) {
        BeanPersona beanPersona = new BeanPersona();
        try {
            String query = "SELECT * FROM persona p " +
                    "join usuario u on u.idUsuario = p.usuario_idUsuario " +
                    "join rol r on u.rol_idRol = r.idRol " +
                    "WHERE idUsuario = ?";

            con = MysqlConector.connect();
            pstm = con.prepareStatement(query);
            pstm.setInt(1, id);
            rs = pstm.executeQuery();
            if (rs.next()) {
                BeanUsuario beanUsuario = new BeanUsuario();
                beanUsuario.setIdUsuario(rs.getInt("idUsuario"));
                beanUsuario.setCorreo(rs.getString("correo"));
                beanUsuario.setContrasena(rs.getString("contrasena"));
                beanUsuario.setTelefono(rs.getString("telefono"));

                BeanRol beanRol = new BeanRol();
                beanRol.setIdRol(rs.getInt("idRol"));
                beanRol.setNombreRol(rs.getString("nombreRol"));
                beanUsuario.setRol(beanRol);

                beanPersona.setIdPersona(rs.getInt("idPersona"));
                beanPersona.setNombrePersona(rs.getString("nombrePersona"));
                beanPersona.setPrimerApellido(rs.getString("primerApellido"));
                beanPersona.setSegundoApellido(rs.getString("segundoApellido"));
                beanPersona.setUsuario(beanUsuario);
            }
        } catch (SQLException e) {
            System.err.println("Error en el método findOne() - DaoPersona -> " + e.getMessage());
        } finally {
            cerrarConexiones("findOne");
        }
        return beanPersona;
    }

    @Override
    public boolean update(int id, Object object) {
        boolean modificado = false;
        BeanPersona persona = (BeanPersona) object;
        try {
            String query = "UPDATE persona SET nombrePersona = ?, primerApellido = ?, segundoApellido = ? WHERE idPersona = ?";
            con = MysqlConector.connect();
            pstm = con.prepareStatement(query);
            pstm.setString(1, persona.getNombrePersona());
            pstm.setString(2, persona.getPrimerApellido());
            pstm.setString(3, persona.getSegundoApellido());
            pstm.setInt(4, persona.getIdPersona());
            modificado = pstm.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error en el método update() - DaoPersona -> " + e.getMessage());
        } finally {
            cerrarConexiones("update");
        }
        return modificado;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }

    @Override
    public boolean insert(Object object) {
        BeanPersona persona = (BeanPersona) object;
        boolean registrado = false;
        try {
            String query = "INSERT INTO persona (nombrePersona, primerApellido, segundoApellido, usuario_idUsuario) values(?,?,?,?)";

            con = MysqlConector.connect();
            pstm = con.prepareStatement(query);
            pstm.setString(1, persona.getNombrePersona());
            pstm.setString(2, persona.getPrimerApellido());
            pstm.setString(3, persona.getSegundoApellido());
            pstm.setInt(4, persona.getUsuario().getIdUsuario());
            registrado = pstm.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error en el método insert() - DaoPersona -> " + e.getMessage());
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
            System.err.println("Error al cerrar conexiones - DaoPersona - en el método " + metodo + " -> " + e.getMessage());
        }
    }
}
