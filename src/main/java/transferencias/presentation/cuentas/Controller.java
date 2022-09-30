package transferencias.presentation.cuentas;

import transferencias.Application;
import transferencias.logic.Cuenta;
import transferencias.logic.Service;

import java.util.List;

public class Controller {
    View view;
    Model model;

    public Controller(View view, Model model) {
        model.setCuentas(Service.instance().cuentasSearchNombre(""));
        this.view = view;
        this.model = model;
        view.setController(this);
        view.setModel(model);
    }

    public void buscar(String filtro){
        List<Cuenta> rows = Service.instance().cuentasSearchNombre(filtro);
        model.setCuentas(rows);
        model.commit();
    }

    public void eliminar(String numero) throws Exception {
        List<Cuenta> rows = Service.instance().eliminarCuenta(numero);
        model.setCuentas(rows);
        this.buscar("");
        model.commit();
    }

    public void preAgregar(){
        Application.controllerCuenta.preAgregar();
    }

    public void editar(int row){
        String numero = model.getCuentas().get(row).getNumero();
        Cuenta c=null;
        try {
            c= Service.instance().cuentaGet(numero);
            Application.controllerCuenta.editar(c);
        } catch (Exception ex) {}
    }
}
