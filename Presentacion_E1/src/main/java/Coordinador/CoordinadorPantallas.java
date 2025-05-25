package Coordinador;

import Presentacion.MapadeMesas;
import Presentacion.PantallaDeshabilitarHabilitarMesero;
import Presentacion.PantallaGestionMeseros;
import Presentacion.PantallaMenu;
import Presentacion.RegistrarReservacion;
import Presentacion.PantallaCancelarReservacion;
import BO.ReservacionBO;
import GenerarReportes.MenuReportesReservacion;
import GenerarReportes.ReporteReservaciones;
import GestionMeseros.PantallaRegistrarMesero;
import Persistencia.ReservacionDAO;
import Interfaces.IReservacionBO;
import Presentacion.PantallaModificarMesero;
import Roles.MenuRolesRestaurante;
import Presentacion.AdministrarMesas;
import Presentacion.HistorialReservaciones;

public class CoordinadorPantallas {
    private static CoordinadorPantallas instancia;
    private IReservacionBO controlReservacion;
    private MapadeMesas mapaDeMesas;
   private MenuRolesRestaurante menuRoles;
   private String rolSeleccionado;



    private CoordinadorPantallas() {
        mapaDeMesas = new MapadeMesas();
        controlReservacion = new ReservacionBO(new ReservacionDAO());
    }

    public static CoordinadorPantallas getInstance() {
        if (instancia == null) {
            instancia = new CoordinadorPantallas();
        }
        return instancia;
    }
        public void mostrarMenuRoles() {
        menuRoles = null;
        resetRolSeleccionado();
        if (menuRoles == null) {
            menuRoles = new MenuRolesRestaurante();
        }
        menuRoles.setVisible(true);
    }
       public void resetRolSeleccionado() {
        this.rolSeleccionado = null;
    }

    public MenuRolesRestaurante getMenuRoles() {
        return menuRoles;
    }

    public void setMenuRoles(MenuRolesRestaurante menuRoles) {
        this.menuRoles = menuRoles;
    }

    public String getRolSeleccionado() {
        return rolSeleccionado;
    }

    public void setRolSeleccionado(String rolSeleccionado) {
        this.rolSeleccionado = rolSeleccionado;
    }
        
    public static CoordinadorPantallas getInstancia() {
        return instancia;
    }

    public static void setInstancia(CoordinadorPantallas instancia) {
        CoordinadorPantallas.instancia = instancia;
    }

    
    
    // — Getter para la BO
    public IReservacionBO getControlReservacion() {
        return controlReservacion;
    }

    public MapadeMesas getMapaDeMesas() {
        return mapaDeMesas;
    }

    public void mostrarMapaMesas() {
        MapadeMesas m = new MapadeMesas();
        m.setLocationRelativeTo(null);
        m.setVisible(true);
    }

    public void mostrarRegistroReservacion() {
        RegistrarReservacion r = new RegistrarReservacion();
        r.setLocationRelativeTo(null);
        r.setVisible(true);
    }

    public void mostrarMenu() {
        PantallaMenu a = new PantallaMenu();
        a.setLocationRelativeTo(null);
        a.setVisible(true);
    }

    // CRUD de meseros
    public void mostrarGestionMeseros() {
        PantallaGestionMeseros g = new PantallaGestionMeseros();
        g.setLocationRelativeTo(null);
        g.setVisible(true);
    }

    public void mostrarAgregarMesero() {
        PantallaRegistrarMesero rm = new PantallaRegistrarMesero();
        rm.setLocationRelativeTo(null);
        rm.setVisible(true);
    }
    public void mostrarEliminarMesero(){
        PantallaDeshabilitarHabilitarMesero em = new PantallaDeshabilitarHabilitarMesero();
        em.setLocationRelativeTo(null);
        em.setVisible(true);
    
    }
    public void mostrarModificarMesero(){
        PantallaModificarMesero m = new PantallaModificarMesero();
        m.setLocationRelativeTo(null);
        m.setVisible(true);
    
    }
    
    //Pantallas de Reportes
    public void mostrarMenuReportes(){
        MenuReportesReservacion rr = new MenuReportesReservacion();
        rr.setLocationRelativeTo(null);
        rr.setVisible(true);
    }
    public void mostrarReportesReservacion(){
        ReporteReservaciones rp = new ReporteReservaciones();
        rp.setLocationRelativeTo(null);
        rp.setVisible(true);
    }

    public void mostrarCancelarReservacion() {
        // Usa el controlReservacion interno
        PantallaCancelarReservacion pcr =
            new PantallaCancelarReservacion(getControlReservacion());
        pcr.setLocationRelativeTo(null);
        pcr.setVisible(true);
    }
    
    public void mostrarAdministrarMesas(){
        AdministrarMesas am = new AdministrarMesas();
        am.setLocationRelativeTo(null);
        am.setVisible(true);
    }
    
    public void mostrarHistorialReservaciones(){
        HistorialReservaciones hr = new HistorialReservaciones();
        hr.setLocationRelativeTo(null);
        hr.setVisible(true);
    }
    
}
