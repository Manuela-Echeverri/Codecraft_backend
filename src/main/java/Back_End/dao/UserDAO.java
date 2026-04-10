package Back_End.dao;

import config.Connection_db;
import Back_End.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {

    // LOGIN
    public User login(String username, String password) {

        String sql = "SELECT * FROM users WHERE username=? AND password=? AND status=1";

        System.out.println("INTENTO DE LOGIN");
        System.out.println("USERNAME INGRESADO: [" + username + "]");
        System.out.println("PASSWORD INGRESADO: [" + password + "]");

        try (java.sql.Connection conn = Connection_db.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, username);
            stmt.setString(2, password);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {

                System.out.println("LOGIN EXITOSO - USUARIO ENCONTRADO");

                return new User(
                        rs.getInt("user_id"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("email"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("role"),
                        rs.getInt("status"),
                        rs.getString("created_at")
                );

            } else {
                System.out.println("LOGIN FALLIDO - NO SE ENCONTRÓ USUARIO");
            }

        } catch (Exception e) {
            System.out.println("💥 ERROR EN LOGIN");
            e.printStackTrace();
        }

        return null;
    }

    // OBTENER USUARIOS
    public List<User> getAllUsers() {
        List<User> list = new ArrayList<>();

        try (java.sql.Connection conn = Connection_db.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM users")) {

            while (rs.next()) {
                list.add(new User(
                        rs.getInt("user_id"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("email"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("role"),
                        rs.getInt("status"),
                        rs.getString("created_at")
                ));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    // CREAR USUARIO
    public void createUser(User user) {

        String sql = "INSERT INTO users (first_name,last_name,email,username,password,role,status) VALUES (?,?,?,?,?,?,?)";

        try (java.sql.Connection conn = Connection_db.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, user.getFirstName());
            stmt.setString(2, user.getLastName());
            stmt.setString(3, user.getEmail());
            stmt.setString(4, user.getUsername());
            stmt.setString(5, user.getPassword());
            stmt.setString(6, user.getRole());
            stmt.setInt(7, 1);

            stmt.executeUpdate();

            System.out.println("USUARIO CREADO");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ELIMINAR USUARIO
    public void deleteUser(int id) {

        try (java.sql.Connection conn = Connection_db.getConnection();
             PreparedStatement stmt = conn.prepareStatement("DELETE FROM users WHERE user_id=?")) {

            stmt.setInt(1, id);
            stmt.executeUpdate();

            System.out.println("🗑USUARIO ELIMINADO ID: " + id);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateUser(int id, User user){

        String sql = "UPDATE users SET first_name=?, last_name=?, email=?, username=?, password=?, role=? WHERE user_id=?";

        try(Connection conn = Connection_db.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)){

            stmt.setString(1, user.getFirstName());
            stmt.setString(2, user.getLastName());
            stmt.setString(3, user.getEmail());
            stmt.setString(4, user.getUsername());
            stmt.setString(5, user.getPassword());
            stmt.setString(6, user.getRole());
            stmt.setInt(7, id);

            stmt.executeUpdate();

            System.out.println("Usuario actualizado correctamente");

        } catch(Exception e){
            e.printStackTrace();
        }
    }

}