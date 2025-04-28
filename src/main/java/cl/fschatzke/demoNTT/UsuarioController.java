package cl.fschatzke.demoNTT;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/users")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

    @GetMapping(path="/login", params = {"email"}, produces = { "application/json" })
    public String login(String email) {
        Usuario userLogged=usuarioService.getUserByEmail(email);

        if (userLogged==null){
            return new JSONObject().put("Mensaje", "El correo no está registrado").toString();
            // throw new CustomException("Usuario no encontrado");
        }else{
            usuarioService.updateLastLogin(userLogged);

            return new JSONObject().put("token", userLogged.getToken()).toString();
        }
    }

    @GetMapping(path="/all", produces = { "application/json" })
    public @ResponseBody String getArrayAsJson() {
        return usuarioService.getArrayOfUser(usuarioService.getAllUsers()) ;
    }

    @PostMapping(path="/add", produces = { "application/json" })
    public ResponseEntity<String>addUser(@RequestBody Usuario user) {
        Usuario userUpdated = usuarioService.saveUser(user);

        if(userUpdated!=null){
            return new ResponseEntity<>(new JSONObject().put("token", userUpdated.getToken()).toString(),HttpStatus.CREATED);
            
        }else{
            return new ResponseEntity<>(new JSONObject().put("mensaje", "El correo ya está registrado").toString(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(path="/update", produces = { "application/json" })
    public ResponseEntity<String>updateUser(@RequestBody Usuario user) {
        Usuario userUpdated = usuarioService.updateUser(user);

        if(userUpdated!=null){
            return new ResponseEntity<>(new JSONObject().put("mensaje", "Usuario actualizado").toString(),HttpStatus.OK);
            
        }else{
            return new ResponseEntity<>(new JSONObject().put("mensaje", "El correo no está registrado").toString(), HttpStatus.NOT_FOUND);
        }
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    public static class CustomException extends RuntimeException {
        public CustomException(String message) {
            super(message);
        }
    }

//   @Bean
//   public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//       http
//           .csrf(csrf -> csrf.disable())
//           .authorizeHttpRequests((authz) -> 
//               authz.anyRequest().permitAll()
//           )
//           .httpBasic(withDefaults())
//           .formLogin((form) -> 
//               form.loginPage("/login").permitAll()
//           )
//           .logout((logout) -> logout.permitAll());

//       return http.build();
//   }

}
