package DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import model.Producto;
import database.PostgreConnection;

public class ProductoDAO {

    public boolean insert(Producto producto){
        String sql = "INSERT INTO producto (nombre, descripcion, unidades, stock, categoria_id) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = PostgreConnection.getConnection() ){
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, producto.getNombre());
            ps.setString(2, producto.getDescripcion());
            ps.setInt(3, producto.getUnidades());
            ps.setInt(4, producto.getStock());
            ps.setInt(5, producto.getCategoria_id());

            int filas = ps.executeUpdate();
            return filas > 0;

        } catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }

    public boolean update(Producto producto){
        String sql = "UPDATE producto SET nombre = ?, descripcion = ?, unidades = ?, stock = ?, categoria_id = ? WHERE id = ?";

        try (Connection conn = PostgreConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, producto.getNombre());
                ps.setString(2, producto.getDescripcion());
                ps.setInt(3, producto.getUnidades());
                ps.setInt(4, producto.getStock());
                ps.setInt(5, producto.getCategoria_id());
                ps.setInt(6, producto.getId());

                int filas = ps.executeUpdate();
                return filas > 0;

        } catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }

    public boolean delete(int id){
        String sql = "DELETE FROM producto WHERE id = ?";

        try (Connection conn = PostgreConnection.getConnection();
        PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);

            int filas = ps.executeUpdate();
            return filas > 0;

        } catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }

    public Producto findById(int id) {
        String sql = "SELECT * FROM producto WHERE id = ?";

        try (Connection conn = PostgreConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()){
                Producto producto = new Producto();
                producto.setId(rs.getInt("id"));
                producto.setNombre(rs.getString("nombre"));
                producto.setDescripcion(rs.getString("descripcion"));
                producto.setUnidades(rs.getInt("unidades"));
                producto.setStock(rs.getInt("stock"));
                producto.setCategoria_id(rs.getInt("categoria_id"));
                return producto;
            }
            return null;

        } catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    public List<Producto> findAll(){
        String sql = "SELECT * FROM producto";
        List<Producto> productos = new ArrayList<>();

        try (Connection conn = PostgreConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Producto producto = new Producto();
                    producto.setId(rs.getInt("id"));
                    producto.setNombre(rs.getString("nombre"));
                    producto.setDescripcion(rs.getString("descripcion"));
                    producto.setUnidades(rs.getInt("unidades"));
                    producto.setStock(rs.getInt("stock"));
                    producto.setCategoria_id(rs.getInt("categoria_id"));

                    productos.add(producto);
                }

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return productos;
    }
}
