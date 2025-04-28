package cl.fschatzke.demoNTT;
// import cl.fschatzke.testWorkNTT.Usuario;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    Usuario findByCorreo(String correo);
}
