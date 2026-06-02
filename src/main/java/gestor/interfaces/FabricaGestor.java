package gestor.interfaces;

import gestor.model.Bug;
import gestor.model.Funcionalidad;
import gestor.model.Proyecto;
import gestor.model.Tarea;
import gestor.model.UsuarioReal;
import java.util.Date;

public interface FabricaGestor {

    void setMediador(Mediador mediador);

    Tarea crearTarea(String titulo, String prioridad, Date fechaLimite);

    Bug crearBug(String titulo, String prioridad, Date fechaLimite,
                 String descripcionError, String severidad);

    Funcionalidad crearFuncionalidad(String titulo, String prioridad, Date fechaLimite,
                                     String descripcion);

    Proyecto crearProyecto(String nombre, String descripcion);

    UsuarioReal crearUsuario(String nombre, String rol);
}