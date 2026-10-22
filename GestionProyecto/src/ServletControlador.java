import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ServletControlador")
public class ServletControlador extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // 1. Recibir los datos enviados desde el formulario HTML
        String nombre = request.getParameter("nombre");
        String descripcion = request.getParameter("descripcion");

        // 2. Crear el objeto Modelo con los datos recibidos
        Modelo nuevoUsuario = new Modelo();
        nuevoUsuario.setNombre(nombre);
        nuevoUsuario.setDescripcion(descripcion);

        // 3. Llamar al DAO para guardar en MySQL (phpMyAdmin)
        ModeloDAO dao = new ModeloDAO();
        boolean guardado = dao.insertar(nuevoUsuario);

        // 4. Redireccionar o mostrar mensaje
        if (guardado) {
            System.out.println("¡Registro guardado con éxito en phpMyAdmin!");
            response.sendRedirect("index.html?status=exito");
        } else {
            System.out.println("Error al guardar el registro.");
            response.sendRedirect("index.html?status=error");
        }
    }
}