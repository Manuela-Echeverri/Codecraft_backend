package Back_End.controller;

import Back_End.dao.UserDAO;
import Back_End.model.User;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/users")
public class UserController {

    UserDAO userDAO = new UserDAO(); // 🔥 IMPORTANTE

    // LOGIN
    @PostMapping("/login")
    public User login(@RequestBody User user){
        return userDAO.login(user.getUsername(), user.getPassword());
    }

    // LISTAR USUARIOS
    @GetMapping
    public List<User> getUsers(){
        return userDAO.getAllUsers();
    }

    // CREAR USUARIO
    @PostMapping
    public void createUser(@RequestBody User user){
        userDAO.createUser(user);
    }

    // ELIMINAR
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable int id){
        userDAO.deleteUser(id);
    }

    // ACTUALIZAR
    @PutMapping("/{id}")
    public void updateUser(@PathVariable int id, @RequestBody User user){
        userDAO.updateUser(id, user);
    }

}