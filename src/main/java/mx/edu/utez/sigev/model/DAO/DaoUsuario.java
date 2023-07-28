package mx.edu.utez.sigev.model.DAO;

import mx.edu.utez.sigev.model.BeanPersona;
import mx.edu.utez.sigev.model.BeanUsuario;
import mx.edu.utez.sigev.model.BeanVoluntario;
import mx.edu.utez.sigev.utils.MysqlConector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DaoUsuario {

    Connection conn;
    PreparedStatement ps;
    ResultSet rs;
    MysqlConector mysqlConector = new MysqlConector();



    private final String CREATE_USUARIO = "INSERT INTO usuario(correo, contrasena, telefono) values(?,?,?)";



    public boolean insert(BeanUsuario agregarusuario) {
        boolean rest2 = false;

        try {
            conn = mysqlConector.connect();
            String query = CREATE_USUARIO;
            ps = conn.prepareStatement(query);

            ps.setString(1, agregarusuario.getCorreo());
            ps.setString(2, agregarusuario.getTelefono());
            ps.setString(3, agregarusuario.getContrasena());

            if (ps.executeUpdate() > 0){
                rest2 = true;
                System.out.println("Se Registro");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);

        }
        System.out.println("valor restdao" + rest2);
        return rest2;
    }



}
