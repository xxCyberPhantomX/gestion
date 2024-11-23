package servicio;

import modelo.computadoras;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ComputadoraDAO {
    private final String URL = "jdbc:mysql://localhost:3307/tienda_computadoras";
    private final String USER = "root"; // Cambia si tienes otro usuario
    private final String PASSWORD = ""; // Agrega tu contraseña si aplica

    public Connection conectar() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public void insertar(computadoras computadora) throws SQLException {
        String sql = "INSERT INTO computadoras (nombre, precio, cantidad, marca) VALUES (?, ?, ?, ?)";
        try (Connection conn = conectar(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, computadora.getNombre());
            ps.setDouble(2, computadora.getPrecio());
            ps.setInt(3, computadora.getCantidad());
            ps.setString(4, computadora.getMarca());
            ps.executeUpdate();
        }
    }

    public List<computadoras> listar() throws SQLException {
        List<computadoras> computadoras = new ArrayList<>();
        String sql = "SELECT * FROM computadoras";
        try (Connection conn = conectar(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                computadoras computadora = new computadoras(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getDouble("precio"),
                        rs.getInt("cantidad"),
                        rs.getString("marca")
                );
                computadoras.add(computadora);
            }
        }
        return computadoras;
    }

    public void actualizar(computadoras computadora) throws SQLException {
        String sql = "UPDATE computadoras SET nombre = ?, precio = ?, cantidad = ?, marca = ? WHERE id = ?";
        try (Connection conn = conectar(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, computadora.getNombre());
            ps.setDouble(2, computadora.getPrecio());
            ps.setInt(3, computadora.getCantidad());
            ps.setString(4, computadora.getMarca());
            ps.setInt(5, computadora.getId());
            ps.executeUpdate();
        }
    }

    public void eliminar(int id) throws SQLException {
        String sql = "DELETE FROM computadoras WHERE id = ?";
        try (Connection conn = conectar(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }
}
