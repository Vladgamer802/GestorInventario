package DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import model.Categoria;
import database.PostgreConnection;

public class CategoriaDAO {

    public boolean insert(Categoria categoria) {
        String sql = "INSERT INTO categoria (nombre) VALUES (?)";

        try (Connection conn = PostgreConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, categoria.getNombre());

            int filas = ps.executeUpdate();
            return filas > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean update(Categoria categoria) {
        String sql = "UPDATE categoria SET nombre = ? WHERE id = ?";

        try (Connection conn = PostgreConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, categoria.getNombre());
            ps.setInt(2, categoria.getId());

            int filas = ps.executeUpdate();
            return filas > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean delete(int id) {
        String sql = "DELETE FROM categoria WHERE id = ?";

        try (Connection conn = PostgreConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);

            int filas = ps.executeUpdate();
            return filas > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Categoria findById(int id) {
        String sql = "SELECT * FROM categoria WHERE id = ?";

        try (Connection conn = PostgreConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Categoria categoria = new Categoria();
                categoria.setId(rs.getInt("id"));
                categoria.setNombre(rs.getString("nombre"));
                return categoria;
            }

            return null;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Categoria> findAll() {
        String sql = "SELECT * FROM categoria";
        List<Categoria> categorias = new ArrayList<>();

        try (Connection conn = PostgreConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Categoria categoria = new Categoria();
                categoria.setId(rs.getInt("id"));
                categoria.setNombre(rs.getString("nombre"));
                categorias.add(categoria);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return categorias;
    }
}
