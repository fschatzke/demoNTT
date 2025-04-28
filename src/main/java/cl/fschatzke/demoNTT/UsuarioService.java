package cl.fschatzke.demoNTT;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.json.JSONArray;
import org.json.JSONObject;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository userRepository;

    public List<Usuario> getAllUsers() {
        return userRepository.findAll();
    }

    public Usuario saveUser(Usuario user) {
        Usuario response=userRepository.findByCorreo(user.getCorreo());
        
        if(response!=null){
            return null;
        }else{
            user.setToken(getJWTToken(user.getCorreo()));
            return userRepository.save(user);
        }
    }

    public Usuario updateUser(Usuario user) {
        Usuario response=userRepository.findByCorreo(user.getCorreo());
        
        if(response!=null){
            response.setNombre(user.getNombre());
            response.setContraseña(user.getContraseña());
            response.setModiificado(getTimeStamp());

            return userRepository.save(response);
        }else{
            return null;
        }
    }

    public Usuario getUserByEmail(String email) {
        Usuario response = userRepository.findByCorreo(email);
        if(response!=null){
            response.setToken(getJWTToken(email));
        }
        return response;
    }

    public Timestamp getTimeStamp(){
        Date currentDate = new Date(); 
        return new Timestamp(currentDate.getTime()); 
    }

    public String getArrayOfUser(List<Usuario> users) {
        JSONArray userObjectArray = new JSONArray();

        for (Usuario item : users) {
            JSONObject userObject = new JSONObject();
            JSONArray phoneArray = new JSONArray();

            String[] countryPhone=item.getPaistelefono();
            String[] cityPhone=item.getCiudadtelefono();
            String[] phone=item.getTelefono();

            for (int i = 0; i < countryPhone.length; i++) {
                JSONObject phoneObject = new JSONObject();

                phoneObject.put("codigoPais", countryPhone[i]);
                phoneObject.put("codigoCiudad", cityPhone[i]);
                phoneObject.put("numero", phone[i]);
                phoneArray.put(phoneObject);
            }

            userObject.put("id", item.getId());
            userObject.put("nombre", item.getNombre());
            userObject.put("correo", item.getCorreo());
            userObject.put("contraseña", item.getContraseña());
            userObject.put("telefonos", phoneArray);

            userObjectArray.put(userObject);
        }

        return userObjectArray.toString();
    }

    private String getJWTToken(String username) {
        String secretKey = "5367566859703373367639792F423F452848284D6251655468576D5A71347437";

            
		return "Bearer " + secretKey;
    }

    // @ExceptionHandler(DataIntegrityViolationException.class)
    // public ResponseEntity<?> dataIntegrityViolantionException(final DataIntegrityViolationException e) {
    //     String mostSpecificCauseMessage = e.getMostSpecificCause().getMessage();
    //     if (e.getCause().getCause() instanceof SqlIntegrityConstraintViolationException) {
    //         return new ResponseEntity<>(mostSpecificCauseMessage, HttpStatus.CONFLICT);
    //     }
    //     return new ResponseEntity<>("Unknown Data Integrity Violation Exception with message : " +mostSpecificCauseMessage, HttpStatus.CONFLICT);
    // }    
}