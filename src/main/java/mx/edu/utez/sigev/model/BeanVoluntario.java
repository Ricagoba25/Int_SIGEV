package mx.edu.utez.sigev.model;

import java.io.Serializable;

public class BeanVoluntario implements Serializable {
        private String curp;
        private BeanPersona persona;
        private BeanUsuario usuario;

        // Constructor vacío
        public BeanVoluntario() {
        }

        // Constructor con todos los atributos
        public BeanVoluntario(String curp, BeanPersona persona, BeanUsuario usuario) {
            this.curp = curp;
            this.persona = persona;
            this.usuario = usuario;
        }

        // Getters y Setters
        public String getCurp() {
            return curp;
        }

        public void setCurp(String curp) {
            this.curp = curp;
        }

        public BeanPersona getPersona() {
            return persona;
        }

        public void setPersona(BeanPersona persona) {
            this.persona = persona;
        }

        public BeanUsuario getUsuario() {
            return usuario;
        }

        public void setUsuario(BeanUsuario usuario) {
            this.usuario = usuario;
        }
    }