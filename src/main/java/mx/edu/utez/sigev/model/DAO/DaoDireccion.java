package mx.edu.utez.sigev.model.DAO;

import mx.edu.utez.sigev.model.BeanDireccion;
import mx.edu.utez.sigev.model.BeanEstado;
import mx.edu.utez.sigev.utils.MysqlConector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DaoDireccion implements DaoRepository {
    private Connection con;
    private PreparedStatement pstm;
    private ResultSet rs;

    @Override
    public List findAll() {
        List<BeanDireccion> listaBeanDireccion = new ArrayList<>();
        try {
            String query = "SELECT * FROM Direccion d join Estado e on d.estado_idEstado = e.idEstado";
            con = MysqlConector.connect();
            pstm = con.prepareStatement(query);
            rs = pstm.executeQuery();

            while (rs.next()) {


                BeanEstado beanEstado = new BeanEstado();
                beanEstado.setIdEstado(rs.getInt("idEstado"));
                beanEstado.setNombre(rs.getString("nombre"));

                BeanDireccion beanDireccion = new BeanDireccion();

                beanDireccion.setIdDireccion(rs.getInt("idDireccion"));
                beanDireccion.setCalle(rs.getString("calle"));
                beanDireccion.setColonia(rs.getString("Colonia"));
                beanDireccion.setMunicipio(rs.getString("municipio"));
                beanDireccion.setNoExterior(rs.getString("noExterior"));
                beanDireccion.setNoInterior(rs.getString("noInterior"));

                //Seteas un objeto sobre otro
                beanDireccion.setEstado(beanEstado);

                listaBeanDireccion.add(beanDireccion);
            }
        } catch (SQLException e) {
            System.err.println("Error en el método findAll() - DaoDireccion -> " + e.getMessage());
        } finally {
            cerrarConexiones("findAll");
        }
        return listaBeanDireccion;
    }

    @Override
    public Object findOne(int id) {
        BeanDireccion beanDireccion = new BeanDireccion();
        try {
            String query = "SELECT * FROM direccion d join Estado e  WHERE d.idDireccion = ?";
            con = MysqlConector.connect();
            pstm = con.prepareStatement(query);
            pstm.setInt(1, id);
            rs = pstm.executeQuery();
            if (rs.next()) {

                BeanEstado beanEstado = new BeanEstado();
                beanEstado.setIdEstado(rs.getInt("idEstado"));
                beanEstado.setNombre(rs.getString("nombre"));


                beanDireccion.setIdDireccion(rs.getInt("idDireccion"));
                beanDireccion.setCalle(rs.getString("calle"));
                beanDireccion.setColonia(rs.getString("colonia"));
                beanDireccion.setMunicipio(rs.getString("municipio"));
                beanDireccion.setNoExterior(rs.getString("noExterior"));
                beanDireccion.setNoInterior(rs.getString("noInterior"));
                beanDireccion.setEstado(beanEstado);

            }
        } catch (SQLException e) {
            System.err.println("Error en el método findOne() - DaoColor -> " + e.getMessage());
        } finally {
            cerrarConexiones("findOne");
        }
        return beanDireccion;
    }

    @Override
    public boolean update(int id, Object object) {
        boolean modificado = false;
        BeanDireccion direccion = (BeanDireccion) object;
        try {
            String query = "UPDATE  direccion SET calle = ?, colonia = ?, municipio = ?, noExterior = ?, noInterior = ?, estado_idEstado = ?";
            con = MysqlConector.connect();
            pstm = con.prepareStatement(query);
            pstm.setString(1, direccion.getCalle());
            pstm.setString(2, direccion.getColonia());
            pstm.setString(3, direccion.getMunicipio());
            pstm.setString(4,direccion.getNoExterior());
            pstm.setString(5,direccion.getNoExterior());
            pstm.setInt(6,direccion.getEstado().getIdEstado());
            modificado = pstm.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error en el método update() - DaoDireccion -> " + e.getMessage());
        } finally {
            cerrarConexiones("update");
        }
        return modificado;
    }

    @Override
    public boolean delete(int id) {
        boolean eliminado = false;
        try {
            String query = "DELETE FROM direccion WHERE estado_idEstado = ?";

            con = MysqlConector.connect();
            pstm = con.prepareStatement(query);
            pstm.setInt(1, id);
            eliminado = pstm.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error en el método delete() - DaoDireccion -> " + e.getMessage());
        } finally {
            cerrarConexiones("delete");
        }
        return eliminado;
    }

    @Override
    public boolean insert(Object object) {
        BeanDireccion direccion = (BeanDireccion) object;
        boolean registrado = false;
        try {
            String query = "INSERT INTO direcion (calle, colonia, municipio, noExterior, noInterior, estado_idEstado) values(?,?,?,?,?,?)";

            con = MysqlConector.connect();
            pstm = con.prepareStatement(query);
            pstm.setString(1, direccion.getCalle());
            pstm.setString(2, direccion.getColonia());
            pstm.setString(3, direccion.getMunicipio());
            pstm.setString(4, direccion.getNoExterior());
            pstm.setString(5, direccion.getNoInterior());
            pstm.setInt(6, direccion.getEstado().getIdEstado());
            registrado = pstm.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error en el método insert() - DaoDireccion -> " + e.getMessage());
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
            System.err.println("Error al cerrar conexiones - DaoDireccion - en el método " + metodo + " -> " + e.getMessage());
        }
    }
}
