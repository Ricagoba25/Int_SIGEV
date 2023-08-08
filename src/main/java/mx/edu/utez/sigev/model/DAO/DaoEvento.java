package mx.edu.utez.sigev.model.DAO;

import mx.edu.utez.sigev.model.BeanDireccion;
import mx.edu.utez.sigev.model.BeanEstado;
import mx.edu.utez.sigev.model.BeanEvento;
import mx.edu.utez.sigev.utils.MysqlConector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DaoEvento implements DaoRepository {
    private Connection con;
    private PreparedStatement pstm;
    private ResultSet rs;

    @Override
    public List findAll() {
        List<BeanEvento> listaBeanEvento = new ArrayList<>();
        try {
            String query = "SELECT * FROM evento";
            con = MysqlConector.connect();
            pstm = con.prepareStatement(query);
            rs = pstm.executeQuery();

            while (rs.next()) {

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

                BeanEvento beanEvento = new BeanEvento();
                beanEvento.setNombreEvento(rs.getString("nombreEvento"));
                beanEvento.setDescripcion(rs.getString("descripcion"));
                beanEvento.setFecha(rs.getDate("fecha"));
                beanEvento.setEstatusEvento(rs.getInt("estatusEvento"));
                beanEvento.setDireccion(beanDireccion);

                listaBeanEvento.add(beanEvento);
            }
        } catch (SQLException e) {
            System.err.println("Error en el método findAll() - DaoColor -> " + e.getMessage());
        } finally {
            cerrarConexiones("findAll");
        }
        return listaBeanEvento;
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
                beanEvento.setFecha(rs.getDate("fecha"));
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
            String query = "UPDATE evento SET nombreEvento = ?, descripcion = ?, fecha = ?, estatusEvento = ?, direccion ?  WHERE idEvento = ?";
            con = MysqlConector.connect();
            pstm = con.prepareStatement(query);

            pstm.setString(1, evento.getNombreEvento());
            pstm.setString(2, evento.getDescripcion());
            pstm.setString(3, evento.getFecha().toString());
            pstm.setInt(4, evento.getEstatusEvento());
            pstm.setInt(5, evento.getDireccion().getIdDireccion());

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
            String query = "DELETE FROM evento WHERE idevento = ?";

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
        BeanEvento evento = (BeanEvento) object;
        boolean registrado = false;
        try {
            String query = "INSERT INTO evento (nombreEvento, descripcion, fecha, estatusEvento, direccion_idDirecion) values(?,?,?,?,?,?)";

            con = MysqlConector.connect();
            pstm = con.prepareStatement(query);
            pstm.setString(1, evento.getNombreEvento());
            pstm.setString(2, evento.getDescripcion());
            pstm.setString(3, evento.getFecha().toString());
            pstm.setInt(4, evento.getEstatusEvento());
            pstm.setInt(5, evento.getDireccion().getIdDireccion());

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
