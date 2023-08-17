package mx.edu.utez.sigev.model.DAO;

import mx.edu.utez.sigev.model.*;
import mx.edu.utez.sigev.utils.MysqlConector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DaoVoluntario implements DaoRepository {

    private Connection con;
    private PreparedStatement pstm;
    private ResultSet rs;
    MysqlConector mysqlConector = new MysqlConector();

    @Override
    public List findAll() {
        List<BeanVoluntarioEvaluacion> listaVoluntarios = new ArrayList<>();
        try {
            String query = "SELECT * FROM voluntario v " +
                    "join persona p on v.persona_idPersona = p.idPersona " +
                    "join usuario u on u.idUsuario = p.usuario_idUsuario " +
                    "join rol r on u.rol_idRol = r.idRol " +
                    "join voluntario_evaluacion ve on v.idVoluntario = ve.voluntario_idVoluntario " +
                    "join evaluacion_organizacion_evento eoe on ve.evaluacion_organizacion_evento_idEvaluacionOrganizacionEvento = eoe.idEvaluacionOrganizacionEvento " +
                    "join evento e2 on e2.idEvento = eoe.evento_idEvento " +
                    "join evaluacion e on e.idEvaluacion = eoe.evaluacion_idEvaluacion " +
                    "join organizacion o on eoe.organizacion_idOrganizacion = o.idOrganizacion ";

            con = MysqlConector.connect();
            pstm = con.prepareStatement(query);
            rs = pstm.executeQuery();

            while (rs.next()) {
                BeanRol beanRol = new BeanRol();
                beanRol.setIdRol(rs.getInt("idRol"));
                beanRol.setNombreRol(rs.getString("nombreRol"));

                BeanUsuario beanUsuario = new BeanUsuario();
                beanUsuario.setIdUsuario(rs.getInt("idUsuario"));
                beanUsuario.setCorreo(rs.getString("correo"));
                beanUsuario.setContrasena(rs.getString("contrasena"));
                beanUsuario.setTelefono(rs.getString("telefono"));
                beanUsuario.setRol(beanRol);

                BeanPersona beanPersona = new BeanPersona();
                beanPersona.setIdPersona(rs.getInt("idPersona"));
                beanPersona.setNombrePersona(rs.getString("nombrePersona"));
                beanPersona.setPrimerApellido(rs.getString("primerApellido"));
                beanPersona.setSegundoApellido(rs.getString("segundoApellido"));
                beanPersona.setUsuario(beanUsuario);

                BeanVoluntario beanVoluntario = new BeanVoluntario();
                beanVoluntario.setIdVoluntario(rs.getInt("idVoluntario"));
                beanVoluntario.setCurp(rs.getString("curp"));
                beanVoluntario.setEstatusVoluntario(rs.getInt("estatusVoluntario"));
                beanVoluntario.setPersona(beanPersona);

                BeanEvento beanEvento = new BeanEvento(rs.getInt("idEvento"));
                BeanOrganizacion beanOrganizacion = new BeanOrganizacion(rs.getInt("idOrganizacion"));
                BeanEvaluacion beanEvaluacion = new BeanEvaluacion(rs.getInt("idEvaluacion"));

                BeanEvaluacionOrganizacionEvento beanEvaluacionOrganizacionEvento = new BeanEvaluacionOrganizacionEvento();
                beanEvaluacionOrganizacionEvento.setEvaluacion(beanEvaluacion);
                beanEvaluacionOrganizacionEvento.setOrganizacion(beanOrganizacion);
                beanEvaluacionOrganizacionEvento.setEvento(beanEvento);


                BeanVoluntarioEvaluacion beanVoluntarioEvaluacion = new BeanVoluntarioEvaluacion();
                beanVoluntarioEvaluacion.setVoluntario(beanVoluntario);
                beanVoluntarioEvaluacion.setEvaluacionOrganizacionEvento(beanEvaluacionOrganizacionEvento);
                listaVoluntarios.add(beanVoluntarioEvaluacion);
            }
        } catch (SQLException e) {
            System.err.println("Error en el método findAll() - DaoVoluntario -> " + e.getMessage());
        } finally {
            cerrarConexiones("findAll");
        }
        return listaVoluntarios;
    }

    public List voluntariosPorEstatus(int estatus) {
        List<BeanVoluntarioEvaluacion> listaVoluntarios = new ArrayList<>();
        try {
            String query = "SELECT * FROM voluntario v " +
                    "join persona p on v.persona_idPersona = p.idPersona " +
                    "join usuario u on u.idUsuario = p.usuario_idUsuario " +
                    "join rol r on u.rol_idRol = r.idRol " +
                    "join voluntario_evaluacion ve on v.idVoluntario = ve.voluntario_idVoluntario " +
                    "join evaluacion_organizacion_evento eoe on ve.evaluacion_organizacion_evento_idEvaluacionOrganizacionEvento = eoe.idEvaluacionOrganizacionEvento " +
                    "join evento e2 on e2.idEvento = eoe.evento_idEvento " +
                    "join evaluacion e on e.idEvaluacion = eoe.evaluacion_idEvaluacion " +
                    "join organizacion o on eoe.organizacion_idOrganizacion = o.idOrganizacion WHERE ve.estatusVoluntarioEvaluacion = ?";

            con = MysqlConector.connect();
            pstm = con.prepareStatement(query);
            pstm.setInt(1, estatus);
            rs = pstm.executeQuery();

            while (rs.next()) {
                BeanRol beanRol = new BeanRol();
                beanRol.setIdRol(rs.getInt("idRol"));
                beanRol.setNombreRol(rs.getString("nombreRol"));

                BeanUsuario beanUsuario = new BeanUsuario();
                beanUsuario.setIdUsuario(rs.getInt("idUsuario"));
                beanUsuario.setCorreo(rs.getString("correo"));
                beanUsuario.setContrasena(rs.getString("contrasena"));
                beanUsuario.setTelefono(rs.getString("telefono"));
                beanUsuario.setRol(beanRol);

                BeanPersona beanPersona = new BeanPersona();
                beanPersona.setIdPersona(rs.getInt("idPersona"));
                beanPersona.setNombrePersona(rs.getString("nombrePersona"));
                beanPersona.setPrimerApellido(rs.getString("primerApellido"));
                beanPersona.setSegundoApellido(rs.getString("segundoApellido"));
                beanPersona.setUsuario(beanUsuario);

                BeanVoluntario beanVoluntario = new BeanVoluntario();
                beanVoluntario.setIdVoluntario(rs.getInt("idVoluntario"));
                beanVoluntario.setCurp(rs.getString("curp"));
                beanVoluntario.setEstatusVoluntario(rs.getInt("estatusVoluntario"));
                beanVoluntario.setPersona(beanPersona);

                BeanEvento beanEvento = new BeanEvento(rs.getInt("idEvento"));
                BeanOrganizacion beanOrganizacion = new BeanOrganizacion(rs.getInt("idOrganizacion"));
                BeanEvaluacion beanEvaluacion = new BeanEvaluacion(rs.getInt("idEvaluacion"));

                BeanEvaluacionOrganizacionEvento beanEvaluacionOrganizacionEvento = new BeanEvaluacionOrganizacionEvento();
                beanEvaluacionOrganizacionEvento.setEvaluacion(beanEvaluacion);
                beanEvaluacionOrganizacionEvento.setOrganizacion(beanOrganizacion);
                beanEvaluacionOrganizacionEvento.setEvento(beanEvento);


                BeanVoluntarioEvaluacion beanVoluntarioEvaluacion = new BeanVoluntarioEvaluacion();
                beanVoluntarioEvaluacion.setVoluntario(beanVoluntario);
                beanVoluntarioEvaluacion.setEvaluacionOrganizacionEvento(beanEvaluacionOrganizacionEvento);
                listaVoluntarios.add(beanVoluntarioEvaluacion);
            }
        } catch (SQLException e) {
            System.err.println("Error en el método voluntariosPorEstatus() - DaoVoluntario -> " + e.getMessage());
        } finally {
            cerrarConexiones("voluntariosPorEstatus");
        }
        return listaVoluntarios;
    }

    @Override
    public Object findOne(int id) {
        BeanVoluntario beanVoluntario = new BeanVoluntario();
        try {
            String query = "SELECT * FROM voluntario v " +
                    "join persona p on v.persona_idPersona = p.idPersona " +
                    "join usuario u on u.idUsuario = p.usuario_idUsuario " +
                    "join rol r on u.rol_idRol = r.idRol " +
                    "WHERE u.idUsuario = ?";

            con = MysqlConector.connect();
            pstm = con.prepareStatement(query);
            pstm.setInt(1, id);
            rs = pstm.executeQuery();
            if (rs.next()) {
                BeanRol beanRol = new BeanRol();
                beanRol.setIdRol(rs.getInt("idRol"));
                beanRol.setNombreRol(rs.getString("nombreRol"));

                BeanUsuario beanUsuario = new BeanUsuario();
                beanUsuario.setIdUsuario(rs.getInt("idUsuario"));
                beanUsuario.setCorreo(rs.getString("correo"));
                beanUsuario.setContrasena(rs.getString("contrasena"));
                beanUsuario.setTelefono(rs.getString("telefono"));
                beanUsuario.setRol(beanRol);

                BeanPersona beanPersona = new BeanPersona();
                beanPersona.setIdPersona(rs.getInt("idPersona"));
                beanPersona.setNombrePersona(rs.getString("nombrePersona"));
                beanPersona.setPrimerApellido(rs.getString("primerApellido"));
                beanPersona.setSegundoApellido(rs.getString("segundoApellido"));
                beanPersona.setUsuario(beanUsuario);

                beanVoluntario.setIdVoluntario(rs.getInt("idVoluntario"));
                beanVoluntario.setCurp(rs.getString("curp"));
                beanVoluntario.setEstatusVoluntario(rs.getInt("estatusVoluntario"));
                beanVoluntario.setPersona(beanPersona);
            }
        } catch (SQLException e) {
            System.err.println("Error en el método findOne() - DaoVoluntario -> " + e.getMessage());
        } finally {
            cerrarConexiones("findOne");
        }
        return beanVoluntario;
    }

    @Override
    public boolean update(int id, Object object) {
        boolean modificado = false;
        BeanVoluntario voluntario = (BeanVoluntario) object;
        try {
            String query = "UPDATE voluntario SET curp = ? WHERE idVoluntario = ?";
            con = MysqlConector.connect();
            pstm = con.prepareStatement(query);
            pstm.setString(1, voluntario.getCurp());
            pstm.setInt(2, voluntario.getIdVoluntario());
            modificado = pstm.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error en el método update() - DaoVoluntario -> " + e.getMessage());
        } finally {
            cerrarConexiones("update");
        }
        return modificado;
    }

    @Override
    public boolean delete(int id) {
        boolean eliminado = false;
        try {
            String query = "UPDATE voluntario SET estatusVoluntario = ?  WHERE idVoluntario = ?";

            con = MysqlConector.connect();
            pstm = con.prepareStatement(query);
            pstm.setInt(1, 0);
            pstm.setInt(2, id);
            eliminado = pstm.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error en el método delete() - DaoVoluntario -> " + e.getMessage());
        } finally {
            cerrarConexiones("delete");
        }
        return eliminado;
    }

    @Override
    public boolean insert(Object object) {
        BeanVoluntario voluntario = (BeanVoluntario) object;
        boolean registrado = false;
        try {
            String query = "INSERT INTO voluntario (curp, persona_idPersona) values(?,?)";

            con = MysqlConector.connect();
            pstm = con.prepareStatement(query);
            pstm.setString(1, voluntario.getCurp());
            pstm.setInt(2, voluntario.getPersona().getIdPersona());
            registrado = pstm.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error en el método insert() - DaoVoluntario -> " + e.getMessage());
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
            System.err.println("Error al cerrar conexiones - DaoVoluntario - en el método " + metodo + " -> " + e.getMessage());
        }
    }
}