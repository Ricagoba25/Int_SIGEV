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

public class DaoPersona {

    Connection conn;
    PreparedStatement ps;
    ResultSet rs;
    MysqlConector mysqlConector = new MysqlConector();

    private final String CREATE_PERSONA = "INSERT INTO persona(nombrePersona, primerApellido, segundoApellido) values(?,?,?)";



    public boolean insert(BeanPersona agregarpersona) {
        boolean rest1 = false;
        // System.out.println("Agregar Voluntario " + agregarvoluntario.toString()); Muestra las propiedades del objeto
        try {
            conn = mysqlConector.connect();
            String query = CREATE_PERSONA;
            ps = conn.prepareStatement(query);

            ps.setString(1, agregarpersona.getNombrePersona());
            ps.setString(2, agregarpersona.getPrimerApellido());
            ps.setString(3, agregarpersona.getSegundoApellido());

            if (ps.executeUpdate() > 0){
                rest1 = true;
                BeanPersona beanPersona = new BeanPersona();
                beanPersona.setIdPersona(rs.getInt("idPersona"));
                beanPersona.setNombrePersona(rs.getString("nombre"));
                beanPersona.setPrimerApellido(rs.getString("primerApellido"));
                beanPersona.setSegundoApellido(rs.getString("segundoApellido"));
                System.out.println("Se Registro");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);

        }
        System.out.println("valor restdao" + rest1);
        return rest1;
    }


}
