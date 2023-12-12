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
        List<BeanVoluntario> listaVoluntarios = new ArrayList<>();
        try {
            String query = "SELECT * FROM voluntario v " +
                    "join persona p on v.persona_idPersona = p.idPersona " +
                    "join usuario u on u.idUsuario = p.usuario_idUsuario " +
                    "join rol r on u.rol_idRol = r.idRol ";

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

                listaVoluntarios.add(beanVoluntario);
            }
        } catch (SQLException e) {
            System.err.println("Error en el método findAll() - DaoVoluntario -> " + e.getMessage());
        } finally {
            cerrarConexiones("findAll");
        }
        return listaVoluntarios;
    }

    public List voluntariosPostuladosPorId(int idVoluntario) {
        List<BeanVoluntarioEvaluacion> listaVoluntarios = new ArrayList<>();
        try {


            String query =
                    "SELECT * FROM voluntario v" +
                            "join persona p on v.persona_idPersona = p.idPersona" +
                            "JOIN voluntario_evento vx ON v.idVoluntario = vx.voluntario_idVoluntario" +
                            "JOIN evento ev ON ev.idEvento = vx.evento_idEvento" +
                            "JOIN direccion di ON ev.direccion_idDireccion = di.idDireccion" +
                            "WHERE v.idVoluntario = ?";

            con = MysqlConector.connect();
            pstm = con.prepareStatement(query);
            pstm.setInt(1, idVoluntario);
            rs = pstm.executeQuery();

            while (rs.next()) {

                BeanEventoVoluntario beanEventoVoluntario = new BeanEventoVoluntario();
                beanEventoVoluntario.setVoluntario_idVoluntario(rs.getInt("idVoluntario"));
                beanEventoVoluntario.setEvento_idEvento(rs.getInt("idEvento"));
                beanEventoVoluntario.setEstatusSolicitud(rs.getInt("estatusSolicitud"));

                BeanPersona beanPersona = new BeanPersona();
                beanPersona.setIdPersona(rs.getInt("idPersona"));
                beanPersona.setNombrePersona(rs.getString("nombrePersona"));
                beanPersona.setPrimerApellido(rs.getString("primerApellido"));
                beanPersona.setSegundoApellido(rs.getString("segundoApellido"));

                BeanVoluntario beanVoluntario = new BeanVoluntario();
                beanVoluntario.setIdVoluntario(rs.getInt("idVoluntario"));
                beanVoluntario.setCurp(rs.getString("curp"));
                beanVoluntario.setEstatusVoluntario(rs.getInt("estatusVoluntario"));
                beanVoluntario.setPersona(beanPersona);

                BeanEvento beanEvento = new BeanEvento(rs.getInt("idEvento"));
                BeanOrganizacion beanOrganizacion = new BeanOrganizacion(rs.getInt("idOrganizacion"));


//                listaVoluntarios.add(beanVoluntarioEvaluacion);

            }
        } catch (SQLException e) {
            System.err.println("Error en el método voluntariosPorEstatus() - DaoVoluntario -> " + e.getMessage());
        } finally {
            cerrarConexiones("voluntariosPorEstatus");
        }
        return listaVoluntarios;
    }

    public List voluntariosPorOrganizacion(int idOrganizacion) {
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
                    "join organizacion o on eoe.organizacion_idOrganizacion = o.idOrganizacion " +
                    "WHERE o.idOrganizacion = ?";

            con = MysqlConector.connect();
            pstm = con.prepareStatement(query);
            pstm.setInt(1, idOrganizacion);
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
                beanEvento.setNombreEvento(rs.getString("nombreEvento"));
                BeanOrganizacion beanOrganizacion = new BeanOrganizacion(rs.getInt("idOrganizacion"));
                BeanEvaluacion beanEvaluacion = new BeanEvaluacion();
                beanEvaluacion.setIdEvaluacion(rs.getInt("idEvaluacion"));
                beanEvaluacion.setNombreEvaluacion(rs.getString("nombreEvaluacion"));
                List<BeanPregunta> preguntas = new DaoPregunta().preguntasPorEvaluacion(rs.getInt("idEvaluacion"));
                beanEvaluacion.setPreguntas(preguntas);
                List<BeanRespuesta> respuestas = new DaoRespuesta().respuestasPorVoluntarioEvaluacion(rs.getInt("idVoluntarioEvaluacion"));
                beanEvaluacion.setRespuestas(respuestas);

                BeanEvaluacionOrganizacionEvento beanEvaluacionOrganizacionEvento = new BeanEvaluacionOrganizacionEvento();
                beanEvaluacionOrganizacionEvento.setIdEvaluacionOrganizacionEvento(rs.getInt("idEvaluacionOrganizacionEvento"));
                beanEvaluacionOrganizacionEvento.setEvaluacion(beanEvaluacion);
                beanEvaluacionOrganizacionEvento.setOrganizacion(beanOrganizacion);
                beanEvaluacionOrganizacionEvento.setEvento(beanEvento);


                BeanVoluntarioEvaluacion beanVoluntarioEvaluacion = new BeanVoluntarioEvaluacion();
                beanVoluntarioEvaluacion.setEstatusVoluntarioEvaluacion(rs.getInt("estatusVoluntarioEvaluacion"));
                beanVoluntarioEvaluacion.setIdVoluntarioEvaluacion(rs.getInt("idVoluntarioEvaluacion"));
                beanVoluntarioEvaluacion.setVoluntario(beanVoluntario);
                beanVoluntarioEvaluacion.setEvaluacionOrganizacionEvento(beanEvaluacionOrganizacionEvento);
                listaVoluntarios.add(beanVoluntarioEvaluacion);
            }
        } catch (SQLException e) {
            System.err.println("Error en el método voluntariosPorOrganizacion() - DaoVoluntario -> " + e.getMessage());
        } finally {
            cerrarConexiones("voluntariosPorOrganizacion");
        }
        return listaVoluntarios;
    }

    public List voluntariosPorEstatus(int idOrganizacion, int estatus) {
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
                    "join organizacion o on eoe.organizacion_idOrganizacion = o.idOrganizacion " +
                    "WHERE ve.estatusVoluntarioEvaluacion = ? AND o.idOrganizacion = ?";

            con = MysqlConector.connect();
            pstm = con.prepareStatement(query);
            pstm.setInt(1, estatus);
            pstm.setInt(2, idOrganizacion);
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
                beanEvento.setNombreEvento(rs.getString("nombreEvento"));
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

    public List voluntariosPorEvento(int idVoluntario, int estatus) {
        List<BeanVoluntarioEvaluacion> listaVoluntarios = new ArrayList<>();
        try {
            String query = "SELECT * FROM voluntario v " +
                    "join persona p on v.persona_idPersona = p.idPersona " +
                    "join usuario u on u.idUsuario = p.usuario_idUsuario " +
                    "join rol r on u.rol_idRol = r.idRol " +
                    "join voluntario_evaluacion ve on v.idVoluntario = ve.voluntario_idVoluntario " +
                    "join evaluacion_organizacion_evento eoe on ve.evaluacion_organizacion_evento_idEvaluacionOrganizacionEvento = eoe.idEvaluacionOrganizacionEvento " +
                    "join evento eve on eve.idEvento = eoe.evento_idEvento " +
                    "join evaluacion e on e.idEvaluacion = eoe.evaluacion_idEvaluacion " +
                    "join organizacion o on eoe.organizacion_idOrganizacion = o.idOrganizacion " +
                    "JOIN direccion d on d.idDireccion = eve.direccion_idDireccion " +
                    "JOIN estado es ON es.idEstado = d.estado_idEstado " +
                    "WHERE v.idVoluntario = ? AND ve.estatusVoluntarioEvaluacion = ?;";

            con = MysqlConector.connect();
            pstm = con.prepareStatement(query);
            pstm.setInt(1, idVoluntario);
            pstm.setInt(2, estatus);
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

                BeanEstado beanEstado = new BeanEstado();
                beanEstado.setIdEstado(rs.getInt("idEstado"));
                beanEstado.setNombre(rs.getString("nombre"));

                BeanDireccion beanDireccion = new BeanDireccion();
                beanDireccion.setIdDireccion(rs.getInt("idDireccion"));
                beanDireccion.setCalle(rs.getString("calle"));
                beanDireccion.setColonia(rs.getString("colonia"));
                beanDireccion.setMunicipio(rs.getString("municipio"));
                beanDireccion.setNoExterior(rs.getString("noExterior"));
                beanDireccion.setNoInterior(rs.getString("noInterior"));
                beanDireccion.setEstado(beanEstado);

                BeanEvento beanEvento = new BeanEvento();
                beanEvento.setIdEvento(rs.getInt("idEvento"));
                beanEvento.setNombreEvento(rs.getString("nombreEvento"));
                beanEvento.setDescripcion(rs.getString("descripcion"));
                beanEvento.setFecha(rs.getString("fecha"));
                beanEvento.setEstatusEvento(rs.getInt("estatusEvento"));
                beanEvento.setDireccion(beanDireccion);

                BeanOrganizacion beanOrganizacion = new BeanOrganizacion(rs.getInt("idOrganizacion"));
                BeanEvaluacion beanEvaluacion = new BeanEvaluacion(rs.getInt("idEvaluacion"));

                BeanEvaluacionOrganizacionEvento beanEvaluacionOrganizacionEvento = new BeanEvaluacionOrganizacionEvento();
                beanEvaluacionOrganizacionEvento.setIdEvaluacionOrganizacionEvento(rs.getInt("idEvaluacionOrganizacionEvento"));
                beanEvaluacionOrganizacionEvento.setEvaluacion(beanEvaluacion);
                beanEvaluacionOrganizacionEvento.setOrganizacion(beanOrganizacion);
                beanEvaluacionOrganizacionEvento.setEvento(beanEvento);


                BeanVoluntarioEvaluacion beanVoluntarioEvaluacion = new BeanVoluntarioEvaluacion();
                beanVoluntarioEvaluacion.setIdVoluntarioEvaluacion(rs.getInt("idVoluntarioEvaluacion"));
                beanVoluntarioEvaluacion.setVoluntario(beanVoluntario);
                beanVoluntarioEvaluacion.setEvaluacionOrganizacionEvento(beanEvaluacionOrganizacionEvento);
                listaVoluntarios.add(beanVoluntarioEvaluacion);
            }
        } catch (SQLException e) {
            System.err.println("Error en el método voluntariosPorEvento() - DaoVoluntario -> " + e.getMessage());
        } finally {
            cerrarConexiones("voluntariosPorEvento");
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
            String query = "INSERT INTO voluntario (curp, persona_idPersona,estatusVoluntario) values(?,?,?)";

            con = MysqlConector.connect();
            pstm = con.prepareStatement(query);
            pstm.setString(1, voluntario.getCurp());
            pstm.setInt(2, voluntario.getPersona().getIdPersona());
            pstm.setInt(3, 2);
            registrado = pstm.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error en el método insert() - DaoVoluntario -> " + e.getMessage());
        } finally {
            cerrarConexiones("insert");
        }
        return registrado;
    }

    public boolean postularEvento(int idVoluntario, int idEvaluacionOrganizacionEvento) {
        boolean postulado = false;
        try {
            String query = "INSERT INTO voluntario_evaluacion (voluntario_idVoluntario, evaluacion_organizacion_evento_idEvaluacionOrganizacionEvento,estatusVoluntarioEvaluacion) VALUES (?,?,?)";

            con = MysqlConector.connect();
            pstm = con.prepareStatement(query);
            pstm.setInt(1, idVoluntario);
            pstm.setInt(2, idEvaluacionOrganizacionEvento);
            pstm.setInt(3, 1);
            postulado = pstm.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error en el método postularEvento() - DaoVoluntario -> " + e.getMessage());
        } finally {
            cerrarConexiones("postularEvento");
        }
        return postulado;
    }

    public boolean cancelarPostulacion(int idVoluntario, int idEvaluacionOrganizacionEvento) {
        boolean cancelado = false;
        try {
            String query = "DELETE FROM voluntario_evaluacion WHERE voluntario_idVoluntario = ? AND evaluacion_organizacion_evento_idEvaluacionOrganizacionEvento = ?";

            con = MysqlConector.connect();
            pstm = con.prepareStatement(query);
            pstm.setInt(1, idVoluntario);
            pstm.setInt(2, idEvaluacionOrganizacionEvento);
            cancelado = pstm.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error en el método cancelarPostulacion() - DaoVoluntario -> " + e.getMessage());
        } finally {
            cerrarConexiones("cancelarPostulacion");
        }
        return cancelado;
    }

    public boolean bloquearVoluntario(int idVoluntario) {
        boolean bloqueado = false;
        try {
            String query = "UPDATE voluntario SET estatusVoluntario = 3 WHERE idVoluntario = ?";

            con = MysqlConector.connect();
            pstm = con.prepareStatement(query);
            pstm.setInt(1, idVoluntario);
            bloqueado = pstm.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error en el método bloquearVoluntario() - DaoVoluntario -> " + e.getMessage());
        } finally {
            cerrarConexiones("bloquearVoluntario");
        }
        return bloqueado;
    }

    public boolean aceptarRechazarVoluntario(int idVoluntario, int idEvaluacionOrganizacionEvento, int estatus) {
        boolean aceptadoRechazado = false;
        try {
            String query = "UPDATE voluntario_evaluacion SET estatusVoluntarioEvaluacion = ? WHERE voluntario_idVoluntario = ? AND evaluacion_organizacion_evento_idEvaluacionOrganizacionEvento = ?";

            con = MysqlConector.connect();
            pstm = con.prepareStatement(query);
            pstm.setInt(1, estatus);
            pstm.setInt(2, idVoluntario);
            pstm.setInt(3, idEvaluacionOrganizacionEvento);

            aceptadoRechazado = pstm.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error en el método aceptarRechazarVoluntario() - DaoVoluntario -> " + e.getMessage());
        } finally {
            cerrarConexiones("aceptarRechazarVoluntario");
        }
        return aceptadoRechazado;
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