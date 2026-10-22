import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ModeloDAO {

    // Inserción (Create)
    public boolean insertar(Modelo obj) {
        String sql = "INSERT INTO usuarios (nombre, descripcion) VALUES (?, ?)";
        try (Connection con = Conexion.conectar(); PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setString(1, obj.getNombre());
            pst.setString(2, obj.getDescripcion());
            pst.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Error al insertar: " + e.getMessage());
            return false;
        }
    }

    // Consulta (Read)
    public List<Modelo> listar() {
        List<Modelo> lista = new ArrayList<>();
        String sql = "SELECT * FROM usuarios";
        try (Connection con = Conexion.conectar(); Statement st = con.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                Modelo obj = new Modelo(rs.getInt("id"), rs.getString("nombre"), rs.getString("descripcion"));
                lista.add(obj);
            }
        } catch (SQLException e) {
            System.out.println("Error al consultar: " + e.getMessage());
        }
        return lista;
    }

    // Actualización (Update)
    public boolean actualizar(Modelo obj) {
        String sql = "UPDATE usuarios SET nombre = ?, descripcion = ? WHERE id = ?";
        try (Connection con = Conexion.conectar(); PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setString(1, obj.getNombre());
            pst.setString(2, obj.getDescripcion());
            pst.setInt(3, obj.getId());
            pst.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Error al actualizar: " + e.getMessage());
            return false;
        }
    }

    // Eliminación (Delete)
    public boolean eliminar(int id) {
        String sql = "DELETE FROM usuarios WHERE id = ?";
        try (Connection con = Conexion.conectar(); PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setInt(1, id);
            pst.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Error al eliminar: " + e.getMessage());
            return false;
        }
    }
}