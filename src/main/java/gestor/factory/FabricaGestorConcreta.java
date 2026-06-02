package gestor.factory;

import gestor.interfaces.FabricaGestor;
import gestor.interfaces.Mediador;
import gestor.model.Bug;
import gestor.model.Funcionalidad;
import gestor.model.Proyecto;
import gestor.model.Tarea;
import gestor.model.UsuarioReal;
import java.util.Date;

public class FabricaGestorConcreta implements FabricaGestor {

    private Mediador mediador;

    @Override
    public void setMediador(Mediador mediador) {
        this.mediador = mediador;
        System.out.println("[Factory] Mediador configurado en la fábrica");
    }

    @Override
    public Tarea crearTarea(String titulo, String prioridad, Date fechaLimite) {
        Tarea tarea = new Tarea(titulo, prioridad, fechaLimite);
        System.out.println("[Factory] Tarea creada: " + tarea);
        return tarea;
    }

    @Override
    public Bug crearBug(String titulo, String prioridad, Date fechaLimite,
                        String descripcionError, String severidad) {
        Bug bug = new Bug(titulo, prioridad, fechaLimite, descripcionError, severidad);
        System.out.println("[Factory] Bug creado: " + bug);
        return bug;
    }

    @Override
    public Funcionalidad crearFuncionalidad(String titulo, String prioridad, Date fechaLimite,
                                            String descripcion) {
        Funcionalidad funcionalidad = new Funcionalidad(titulo, prioridad, fechaLimite, descripcion);
        System.out.println("[Factory] Funcionalidad creada: " + funcionalidad);
        return funcionalidad;
    }

    @Override
    public Proyecto crearProyecto(String nombre, String descripcion) {
        Proyecto proyecto = new Proyecto(nombre, descripcion);
        System.out.println("[Factory] Proyecto creado: " + proyecto);
        return proyecto;
    }

    @Override
    public UsuarioReal crearUsuario(String nombre, String rol) {
        UsuarioReal usuario = new UsuarioReal(nombre, rol);
        System.out.println("[Factory] Usuario creado: " + usuario);
        return usuario;
    }
}