package mx.edu.utez.sigev.model.DAO;

import mx.edu.utez.sigev.model.Rol;
import mx.edu.utez.sigev.model.Usuario;
import mx.edu.utez.sigev.utils.MysqlConector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDao implements DaoRepository {
    private Connection con;
    private List<Usuario> listaUsuario;
    private Usuario usr;
    private boolean resp;

    //Contructor donde inicializamos la conexion a la BD
    public UsuarioDao() {
        this.con = new MysqlConector().connect();
        this.listaUsuario = new ArrayList<>();
        this.usr = new Usuario();
        this.resp = false;
    }

    @Override
    public List findAll() {
        try {
            PreparedStatement stmt = con.prepareStatement("select * from usuario");
            ResultSet res = stmt.executeQuery();
            /*while (res.next()) {
                usr.setIdUsuario(res.getInt("id"));
                usr.set(res.getString("nombre"));
                usr.setApellido1(res.getString("apellido"));
                usr.setApellido2(res.getString("apellido2"));
                usr.setSexo(res.getString("sexo"));
                usr.setFecha_nacimiento(res.getDate("fecha_nacimiento"));
                usr.setCorreo(res.getString("correo"));
                usr.setContra(res.getString("contra"));
                usr.setRol((Rol) (res.getObject("rol")));
                usr.setUltimo_login(res.getDate("ultimo_login"));
                listaUsuario.add(usr);
            }*/
            res.close();
            con.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listaUsuario;
    }

    @Override

    public Object findOne(int id) {
        return null;
    }

    public Object findOne(String correo, String contra) {
        try {

           /* SELECT users.*, rol.nombre_rol from users join rol on users.rol = rol.id
            where correo ="manuel@gmail.com" AND contra = SHA2("abc123",256) */

            String QUERY = "SELECT * FROM usuario u join rol r on u.rol_idRol = r.idRol WHERE correo = ? AND contrasena = sha2(?,256)";

            PreparedStatement stmt =
                    con.prepareStatement(QUERY);
            stmt.setString(1, correo);
            stmt.setString(2, contra);

            System.out.println(stmt);


            ResultSet res = stmt.executeQuery();
            if (res.next()) {
                usr.setIdUsuario(res.getInt("idUsuario"));
                usr.setCorreo(res.getString("correo"));
                usr.setContrasena(res.getString("contrasena"));
                usr.setTelefono(res.getString("telefono"));
                usr.setEstatusUsuario(res.getInt("estatusUsuario"));
                Rol rol = new Rol();
                rol.setIdRol(res.getInt("idRol"));
                rol.setNombreRol(res.getString("nombreRol"));
                usr.setRol(rol);



            }
            res.close();
            con.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return usr;
    }

    @Override
    public boolean update(int id, Object object) {
        return false;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }

    @Override
    public boolean insert(Object object) {
        return false;
    }
}
