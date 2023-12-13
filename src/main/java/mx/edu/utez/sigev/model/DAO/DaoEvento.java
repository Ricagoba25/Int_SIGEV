package mx.edu.utez.sigev.model.DAO;

import mx.edu.utez.sigev.model.*;
import mx.edu.utez.sigev.utils.MysqlConector;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DaoEvento implements DaoRepository {
    private Connection con;
    private PreparedStatement pstm;
    private ResultSet rs;

    @Override
    public List findAll() {
        List<BeanEvaluacionOrganizacionEvento> listaEventos = new ArrayList<>();
        try {
            String query = "SELECT es.idEstado, es.nombre, d.idDireccion, d.calle, d.colonia, d.municipio, d.noExterior, d.noInterior, " +
                    "eve.idEvento, eve.nombreEvento, eve.descripcion, eve.fecha, eve.estatusEvento, eva.idEvaluacion, eva.nombreEvaluacion, " +
                    "o.idOrganizacion, o.rfc, o.nombreOrganizacion, o.razonSocial, o.estatusOrganizacion, eoe.idEvaluacionOrganizacionEvento " +
                    "FROM evaluacion_organizacion_evento eoe " +
                    "JOIN evaluacion eva on eva.idEvaluacion = eoe.evaluacion_idEvaluacion " +
                    "JOIN organizacion o on eoe.organizacion_idOrganizacion = o.idOrganizacion " +
                    "JOIN evento eve on eve.idEvento = eoe.evento_idEvento " +
                    "JOIN direccion d on d.idDireccion = eve.direccion_idDireccion " +
                    "JOIN estado es ON es.idEstado = d.estado_idEstado";

            con = MysqlConector.connect();
            pstm = con.prepareStatement(query);
            rs = pstm.executeQuery();

            while (rs.next()) {
                BeanEvaluacion beanEvaluacion = new BeanEvaluacion();
                beanEvaluacion.setIdEvaluacion(rs.getInt("idEvaluacion"));
                beanEvaluacion.setNombreEvaluacion(rs.getString("nombreEvaluacion"));

                BeanOrganizacion beanOrganizacion = new BeanOrganizacion();
                beanOrganizacion.setIdOrganizacion(rs.getInt("idOrganizacion"));
                beanOrganizacion.setRfc(rs.getString("rfc"));
                beanOrganizacion.setNombreOrganizacion(rs.getString("nombreOrganizacion"));
                beanOrganizacion.setRazonSocial(rs.getString("razonSocial"));
                beanOrganizacion.setEstatusOrganizacion(rs.getInt("estatusOrganizacion"));

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

                BeanEvaluacionOrganizacionEvento beanEvaluacionOrganizacionEvento = new BeanEvaluacionOrganizacionEvento();
                beanEvaluacionOrganizacionEvento.setIdEvaluacionOrganizacionEvento(rs.getInt("idEvaluacionOrganizacionEvento"));
                beanEvaluacionOrganizacionEvento.setOrganizacion(beanOrganizacion);
                beanEvaluacionOrganizacionEvento.setEvaluacion(beanEvaluacion);
                beanEvaluacionOrganizacionEvento.setEvento(beanEvento);
                listaEventos.add(beanEvaluacionOrganizacionEvento);
            }
        } catch (SQLException e) {
            System.err.println("Error en el método findAll() - DaoEvento -> " + e.getMessage());
        } finally {
            cerrarConexiones("findAll");
        }
        return listaEventos;
    }

    public List eventosDisponibles(int idVoluntario) {
        List<BeanEvaluacionOrganizacionEvento> listaEventos = new ArrayList<>();
        try {
            String query = "SELECT es.idEstado,es.nombre, d.idDireccion, d.calle, d.colonia, d.municipio, d.noExterior, d.noInterior, eve.idEvento, " +
                    "eve.nombreEvento, eve.descripcion,  eve.fecha, eve.estatusEvento, eva.idEvaluacion, eva.nombreEvaluacion, o.idOrganizacion, " +
                    "o.rfc, o.nombreOrganizacion, o.razonSocial, o.estatusOrganizacion, eoe.idEvaluacionOrganizacionEvento, ve.voluntario_idVoluntario," +
                    "ve.estatusVoluntarioEvaluacion " +
                    "FROM voluntario_evaluacion ve " +
                    "RIGHT JOIN evaluacion_organizacion_evento eoe ON eoe.idEvaluacionOrganizacionEvento = ve.evaluacion_organizacion_evento_idEvaluacionOrganizacionEvento " +
                    "JOIN evaluacion eva on eva.idEvaluacion = eoe.evaluacion_idEvaluacion " +
                    "JOIN organizacion o on eoe.organizacion_idOrganizacion = o.idOrganizacion " +
                    "JOIN evento eve on eve.idEvento = eoe.evento_idEvento " +
                    "JOIN direccion d on d.idDireccion = eve.direccion_idDireccion " +
                    "JOIN estado es ON es.idEstado = d.estado_idEstado " +
                    "WHERE eve.estatusEvento = 2 AND ve.voluntario_idVoluntario IS NULL AND eoe.idEvaluacionOrganizacionEvento NOT IN " +
                    "(SELECT evaluacion_organizacion_evento_idEvaluacionOrganizacionEvento FROM voluntario_evaluacion WHERE voluntario_idVoluntario = ?)";

            con = MysqlConector.connect();
            pstm = con.prepareStatement(query);
            pstm.setInt(1, idVoluntario);
            rs = pstm.executeQuery();

            while (rs.next()) {
                BeanEvaluacion beanEvaluacion = new BeanEvaluacion();
                beanEvaluacion.setIdEvaluacion(rs.getInt("idEvaluacion"));
                beanEvaluacion.setNombreEvaluacion(rs.getString("nombreEvaluacion"));
                List<BeanPregunta> preguntas = new DaoPregunta().preguntasPorEvaluacion(rs.getInt("idEvaluacion"));
                beanEvaluacion.setPreguntas(preguntas);

                BeanOrganizacion beanOrganizacion = new BeanOrganizacion();
                beanOrganizacion.setIdOrganizacion(rs.getInt("idOrganizacion"));
                beanOrganizacion.setNombreOrganizacion(rs.getString("nombreOrganizacion"));

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

                BeanEvaluacionOrganizacionEvento beanEvaluacionOrganizacionEvento = new BeanEvaluacionOrganizacionEvento();
                beanEvaluacionOrganizacionEvento.setIdEvaluacionOrganizacionEvento(rs.getInt("idEvaluacionOrganizacionEvento"));
                beanEvaluacionOrganizacionEvento.setOrganizacion(beanOrganizacion);
                beanEvaluacionOrganizacionEvento.setEvaluacion(beanEvaluacion);
                beanEvaluacionOrganizacionEvento.setEvento(beanEvento);
                listaEventos.add(beanEvaluacionOrganizacionEvento);
            }
        } catch (SQLException e) {
            System.err.println("Error en el método eventosDisponibles() - DaoEvento -> " + e.getMessage());
        } finally {
            cerrarConexiones("eventosDisponibles");
        }
        return listaEventos;
    }

    public List eventosPostulado(int idVoluntario) {
        List<BeanEvaluacionOrganizacionEvento> listaEventos = new ArrayList<>();
        try {
            String query = "SELECT es.idEstado,es.nombre, d.idDireccion, d.calle, d.colonia, d.municipio, d.noExterior, d.noInterior, eve.idEvento, " +
                    "eve.nombreEvento, eve.descripcion,  eve.fecha, eve.estatusEvento, eva.idEvaluacion, eva.nombreEvaluacion, o.idOrganizacion, " +
                    "o.rfc, o.nombreOrganizacion, o.razonSocial, o.estatusOrganizacion, eoe.idEvaluacionOrganizacionEvento, ve.voluntario_idVoluntario," +
                    "ve.estatusVoluntarioEvaluacion " +
                    "FROM evaluacion_organizacion_evento eoe " +
                    "JOIN evaluacion eva on eva.idEvaluacion = eoe.evaluacion_idEvaluacion " +
                    "JOIN organizacion o on eoe.organizacion_idOrganizacion = o.idOrganizacion " +
                    "JOIN evento eve on eve.idEvento = eoe.evento_idEvento " +
                    "JOIN direccion d on d.idDireccion = eve.direccion_idDireccion " +
                    "JOIN estado es ON es.idEstado = d.estado_idEstado " +
                    "JOIN voluntario_evaluacion ve on eoe.idEvaluacionOrganizacionEvento = ve.evaluacion_organizacion_evento_idEvaluacionOrganizacionEvento\n" +
                    "WHERE ve.voluntario_idVoluntario = ? AND ve.estatusVoluntarioEvaluacion = 2 ;";

            con = MysqlConector.connect();
            pstm = con.prepareStatement(query);
            pstm.setInt(1, idVoluntario);
            rs = pstm.executeQuery();

            while (rs.next()) {
                BeanEvaluacion beanEvaluacion = new BeanEvaluacion();
                beanEvaluacion.setIdEvaluacion(rs.getInt("idEvaluacion"));
                beanEvaluacion.setNombreEvaluacion(rs.getString("nombreEvaluacion"));

                BeanOrganizacion beanOrganizacion = new BeanOrganizacion();
                beanOrganizacion.setIdOrganizacion(rs.getInt("idOrganizacion"));
                beanOrganizacion.setRfc(rs.getString("rfc"));
                beanOrganizacion.setNombreOrganizacion(rs.getString("nombreOrganizacion"));
                beanOrganizacion.setRazonSocial(rs.getString("razonSocial"));
                beanOrganizacion.setEstatusOrganizacion(rs.getInt("estatusOrganizacion"));

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

                BeanEvaluacionOrganizacionEvento beanEvaluacionOrganizacionEvento = new BeanEvaluacionOrganizacionEvento();
                beanEvaluacionOrganizacionEvento.setIdEvaluacionOrganizacionEvento(rs.getInt("idEvaluacionOrganizacionEvento"));
                beanEvaluacionOrganizacionEvento.setOrganizacion(beanOrganizacion);
                beanEvaluacionOrganizacionEvento.setEvaluacion(beanEvaluacion);
                beanEvaluacionOrganizacionEvento.setEvento(beanEvento);
                listaEventos.add(beanEvaluacionOrganizacionEvento);
            }
        } catch (SQLException e) {
            System.err.println("Error en el método eventosPorEstatus() - DaoEvento -> " + e.getMessage());
        } finally {
            cerrarConexiones("eventosPorEstatus");
        }
        return listaEventos;
    }

    public List eventosPorEstatus(int idVoluntario, int estatus) {
        List<BeanEvaluacionOrganizacionEvento> listaEventos = new ArrayList<>();
        try {
            String query = "SELECT es.idEstado,es.nombre, d.idDireccion, d.calle, d.colonia, d.municipio, d.noExterior, d.noInterior, eve.idEvento, " +
                    "eve.nombreEvento, eve.descripcion,  eve.fecha, eve.estatusEvento, eva.idEvaluacion, eva.nombreEvaluacion, o.idOrganizacion, " +
                    "o.rfc, o.nombreOrganizacion, o.razonSocial, o.estatusOrganizacion, eoe.idEvaluacionOrganizacionEvento, ve.idVoluntarioEvaluacion, " +
                    "FROM evaluacion_organizacion_evento eoe " +
                    "JOIN evaluacion eva on eva.idEvaluacion = eoe.evaluacion_idEvaluacion " +
                    "JOIN organizacion o on eoe.organizacion_idOrganizacion = o.idOrganizacion " +
                    "JOIN evento eve on eve.idEvento = eoe.evento_idEvento " +
                    "JOIN direccion d on d.idDireccion = eve.direccion_idDireccion " +
                    "JOIN estado es ON es.idEstado = d.estado_idEstado " +
                    "JOIN voluntario_evaluacion ve on eoe.idEvaluacionOrganizacionEvento = ve.evaluacion_organizacion_evento_idEvaluacionOrganizacionEvento " +
                    "WHERE ve.voluntario_idVoluntario != ? AND ve.estatusVoluntarioEvaluacion = ?";

            con = MysqlConector.connect();
            pstm = con.prepareStatement(query);
            pstm.setInt(1, idVoluntario);
            pstm.setInt(2, estatus);
            rs = pstm.executeQuery();

            while (rs.next()) {
                BeanEvaluacion beanEvaluacion = new BeanEvaluacion();
                beanEvaluacion.setIdEvaluacion(rs.getInt("idEvaluacion"));
                beanEvaluacion.setNombreEvaluacion(rs.getString("nombreEvaluacion"));

                BeanOrganizacion beanOrganizacion = new BeanOrganizacion();
                beanOrganizacion.setIdOrganizacion(rs.getInt("idOrganizacion"));
                beanOrganizacion.setRfc(rs.getString("rfc"));
                beanOrganizacion.setNombreOrganizacion(rs.getString("nombreOrganizacion"));
                beanOrganizacion.setRazonSocial(rs.getString("razonSocial"));
                beanOrganizacion.setEstatusOrganizacion(rs.getInt("estatusOrganizacion"));

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

                BeanEvaluacionOrganizacionEvento beanEvaluacionOrganizacionEvento = new BeanEvaluacionOrganizacionEvento();
                beanEvaluacionOrganizacionEvento.setIdEvaluacionOrganizacionEvento(rs.getInt("idEvaluacionOrganizacionEvento"));
                beanEvaluacionOrganizacionEvento.setOrganizacion(beanOrganizacion);
                beanEvaluacionOrganizacionEvento.setEvaluacion(beanEvaluacion);
                beanEvaluacionOrganizacionEvento.setEvento(beanEvento);
                listaEventos.add(beanEvaluacionOrganizacionEvento);
            }
        } catch (SQLException e) {
            System.err.println("Error en el método eventosPorEstatus() - DaoEvento -> " + e.getMessage());
        } finally {
            cerrarConexiones("eventosPorEstatus");
        }
        return listaEventos;
    }

    public List eventosPorOrganizacion(int idOrganizacion) {
        List<BeanEvaluacionOrganizacionEvento> listaEventos = new ArrayList<>();
        try {
            String query = "SELECT es.idEstado, es.nombre, d.idDireccion, d.calle, d.colonia, d.municipio, d.noExterior, d.noInterior, " +
                    "eve.idEvento, eve.nombreEvento, eve.descripcion, eve.fecha, eve.estatusEvento, eva.idEvaluacion, eva.nombreEvaluacion, " +
                    "o.idOrganizacion, o.rfc, o.nombreOrganizacion, o.razonSocial, o.estatusOrganizacion, eoe.idEvaluacionOrganizacionEvento " +
                    "FROM evaluacion_organizacion_evento eoe " +
                    "JOIN evaluacion eva on eva.idEvaluacion = eoe.evaluacion_idEvaluacion " +
                    "JOIN organizacion o on eoe.organizacion_idOrganizacion = o.idOrganizacion " +
                    "JOIN evento eve on eve.idEvento = eoe.evento_idEvento " +
                    "JOIN direccion d on d.idDireccion = eve.direccion_idDireccion " +
                    "JOIN estado es ON es.idEstado = d.estado_idEstado WHERE o.idOrganizacion = ?";

            con = MysqlConector.connect();
            pstm = con.prepareStatement(query);
            pstm.setInt(1, idOrganizacion);
            rs = pstm.executeQuery();

            while (rs.next()) {
                BeanEvaluacion beanEvaluacion = new BeanEvaluacion();
                beanEvaluacion.setIdEvaluacion(rs.getInt("idEvaluacion"));
                beanEvaluacion.setNombreEvaluacion(rs.getString("nombreEvaluacion"));

                BeanOrganizacion beanOrganizacion = new BeanOrganizacion();
                beanOrganizacion.setIdOrganizacion(rs.getInt("idOrganizacion"));
                beanOrganizacion.setRfc(rs.getString("rfc"));
                beanOrganizacion.setNombreOrganizacion(rs.getString("nombreOrganizacion"));
                beanOrganizacion.setRazonSocial(rs.getString("razonSocial"));
                beanOrganizacion.setEstatusOrganizacion(rs.getInt("estatusOrganizacion"));

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

                BeanEvaluacionOrganizacionEvento beanEvaluacionOrganizacionEvento = new BeanEvaluacionOrganizacionEvento();
                beanEvaluacionOrganizacionEvento.setIdEvaluacionOrganizacionEvento(rs.getInt("idEvaluacionOrganizacionEvento"));
                beanEvaluacionOrganizacionEvento.setOrganizacion(beanOrganizacion);
                beanEvaluacionOrganizacionEvento.setEvaluacion(beanEvaluacion);
                beanEvaluacionOrganizacionEvento.setEvento(beanEvento);
                listaEventos.add(beanEvaluacionOrganizacionEvento);
            }
        } catch (SQLException e) {
            System.err.println("Error en el método eventosPorOrganizacion() - DaoEvento -> " + e.getMessage());
        } finally {
            cerrarConexiones("eventosPorOrganizacion");
        }
        return listaEventos;
    }

    @Override
    public Object findOne(int id) {
        BeanEvento beanEvento = new BeanEvento();
        try {
            String query = "SELECT * FROM evento WHERE idEvento = ?";
            con = MysqlConector.connect();
            pstm = con.prepareStatement(query);
            pstm.setInt(1, id);
            rs = pstm.executeQuery();
            if (rs.next()) {
                BeanEstado beanEstado = new BeanEstado();
                beanEstado.setIdEstado(rs.getInt("idEstado"));
                beanEstado.setNombre(rs.getString("nombre"));

                BeanDireccion beanDireccion = new BeanDireccion();
                beanDireccion.setCalle(rs.getString("calle"));
                beanDireccion.setColonia(rs.getString("colonia"));
                beanDireccion.setMunicipio(rs.getString("municipio"));
                beanDireccion.setNoExterior(rs.getString("noExterior"));
                beanDireccion.setNoInterior(rs.getString("noInterior"));
                beanDireccion.setEstado(beanEstado);

                beanEvento.setNombreEvento(rs.getString("nombreEvento"));
                beanEvento.setDescripcion(rs.getString("descripcion"));
                beanEvento.setFecha(rs.getString("fecha"));
                beanEvento.setEstatusEvento(rs.getInt("estatusEvento"));
                beanEvento.setDireccion(beanDireccion);
            }
        } catch (SQLException e) {
            System.err.println("Error en el método findOne() - DaoEvento -> " + e.getMessage());
        } finally {
            cerrarConexiones("findOne");
        }
        return beanEvento;
    }

    @Override
    public boolean update(int id, Object object) {
        boolean modificado = false;
        BeanEvento evento = (BeanEvento) object;
        try {
            String query = "UPDATE evento SET nombreEvento = ?, descripcion = ?, fecha = ? WHERE idEvento = ?";
            con = MysqlConector.connect();
            pstm = con.prepareStatement(query);

            pstm.setString(1, evento.getNombreEvento());
            pstm.setString(2, evento.getDescripcion());
            pstm.setString(3, evento.getFecha());
            pstm.setInt(4, evento.getIdEvento());

            modificado = pstm.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error en el método update() - DaoEvento -> " + e.getMessage());
        } finally {
            cerrarConexiones("update");
        }
        return modificado;
    }

    @Override
    public boolean delete(int id) {
        boolean eliminado = false;
        try {
            String query = "DELETE FROM evento WHERE idEvento = ?";

            con = MysqlConector.connect();
            pstm = con.prepareStatement(query);
            pstm.setInt(1, id);
            eliminado = pstm.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error en el método delete() - DaoEvento -> " + e.getMessage());
        } finally {
            cerrarConexiones("delete");
        }
        return eliminado;
    }

    public boolean changeStatus(int id, int estatus) {
        boolean modificado = false;
        try {
            String query = "UPDATE evento SET estatusEvento = ? WHERE idEvento = ?";

            con = MysqlConector.connect();
            pstm = con.prepareStatement(query);
            pstm.setInt(1, estatus);
            pstm.setInt(2, id);
            modificado = pstm.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error en el método changeStatus() - DaoEvento -> " + e.getMessage());
        } finally {
            cerrarConexiones("changeStatus");
        }
        return modificado;
    }

    public boolean postularse(int id, int estatusSolicitud) {
        boolean postulado = false;

        try {
            String query = "UPDATE evento SET estatusEvento = ? WHERE idEvento = ?";

            con = MysqlConector.connect();
            pstm = con.prepareStatement(query);
            pstm.setInt(1, estatusSolicitud);
            pstm.setInt(2, id);
            postulado = pstm.executeUpdate() > 0;


        } catch (SQLException e) {
            System.err.println("Error en el método postularse() - daoEvento -> " + e.getMessage());
        } finally {
            cerrarConexiones("postularse");
        }
        return postulado;
    }

    @Override
    public boolean insert(Object object) {
        return false;
    }

    public int registrar(Object object) {
        BeanEvento evento = (BeanEvento) object;
        boolean registrado = false;
        int idRegistro = 0;
        try {
            String query = "INSERT INTO evento (nombreEvento, descripcion, fecha, estatusEvento, direccion_idDireccion) values(?,?,?,?,?)";

            con = MysqlConector.connect();
            pstm = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            pstm.setString(1, evento.getNombreEvento());
            pstm.setString(2, evento.getDescripcion());
            pstm.setString(3, evento.getFecha());
            pstm.setInt(4, evento.getEstatusEvento());
            pstm.setInt(5, evento.getDireccion().getIdDireccion());

            registrado = pstm.executeUpdate() > 0;

            if (registrado) {
                rs = pstm.getGeneratedKeys();

                if (rs.next()) {
                    idRegistro = rs.getInt(1);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error en el método registrar() - DaoEvento -> " + e.getMessage());
        } finally {
            cerrarConexiones("registrar");
        }
        return idRegistro;
    }

    public boolean registrarEvaluacionOrganizacionEvento(BeanEvaluacion evaluacion, BeanOrganizacion organizacion, BeanEvento evento) {
        boolean registrado = false;
        try {
            String query = "INSERT INTO evaluacion_organizacion_evento (evaluacion_idEvaluacion, organizacion_idOrganizacion, evento_idEvento) values(?,?,?)";

            con = MysqlConector.connect();
            pstm = con.prepareStatement(query);
            pstm.setInt(1, evaluacion.getIdEvaluacion());
            pstm.setInt(2, organizacion.getIdOrganizacion());
            pstm.setInt(3, evento.getIdEvento());

            registrado = pstm.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error en el método registrarEvaluacionOrganizacionEvento() - DaoEvento -> " + e.getMessage());
        } finally {
            cerrarConexiones("registrarEvaluacionOrganizacionEvento");
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
            System.err.println("Error al cerrar conexiones - DaoEvento - en el método " + metodo + " -> " + e.getMessage());
        }
    }
}
