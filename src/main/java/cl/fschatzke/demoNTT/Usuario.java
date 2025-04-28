package cl.fschatzke.demoNTT;
import jakarta.persistence.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
public class Usuario {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private String nombre;
  
    @Column(unique = true)
    private String correo;
    private String contraseña;
    private String[] paistelefono;
    private String[] ciudadtelefono;
    private String[] telefono;
    private Timestamp modificado;
    private Timestamp ultimologin;
    private String token;
  
    public Long getId() {
      return id;
    }
  
    public void setId(Long id) {
      this.id = id;
    }
  
    public String getNombre() {
      return nombre;
    }
  
    public void setNombre(String nombre) {
      this.nombre = nombre;
    }
  
    public String getCorreo() {
      return correo;
    }
  
    public void setCorreo(String correo) {
      this.correo = correo;
    }
  
    public String getContraseña() {
      return contraseña;
    }
  
    public void setContraseña(String contraseña) {
      this.contraseña = contraseña;
    }
  
    public String[] getPaistelefono() {
      return paistelefono;
    }
  
    public void setPaistelefono(String[] paistelefono) {
      this.paistelefono = paistelefono;
    }
  
    public String[] getCiudadtelefono() {
      return ciudadtelefono;
    }
  
    public void setCiudadtelefono(String[] ciudadtelefono) {
      this.ciudadtelefono = ciudadtelefono;
    }
  
    public String[] getTelefono() {
      return telefono;
    }
  
    public void setTelefono(String[] telefono) {
      this.telefono =telefono;
    }

    public Timestamp getModificado() {
        return modificado;
      }
    
    public void setModiificado(Timestamp timestamp) {
        this.modificado =timestamp;
    }

    public Timestamp getUltimologin() {
        return ultimologin;
      }
    
    public void setUltimologin(Timestamp ultimologin) {
        this.ultimologin =ultimologin;
    }

    public String getToken() {
      return token;
    }
  
    public void setToken(String token) {
      this.token = token;
    }
  
}
