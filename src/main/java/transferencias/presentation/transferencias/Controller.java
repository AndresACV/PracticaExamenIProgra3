package transferencias.presentation.transferencias;

import transferencias.logic.Cuenta;
import transferencias.logic.Service;

import java.util.List;

public class Controller {
    transferencias.presentation.transferencias.View view;
    transferencias.presentation.transferencias.Model model;

    public Controller(transferencias.presentation.transferencias.View view, transferencias.presentation.transferencias.Model model) {
        model.setCuentasOrigen(Service.instance().cuentasSearchOrigen(""));
        model.setCuentasDestino(Service.instance().cuentasSearchDestino(""));
        this.view = view;
        this.model = model;
        view.setController(this);
        view.setModel(model);
    }

    public void buscarOrigen(String filtro){
        List<Cuenta> rows = Service.instance().cuentasSearchOrigen(filtro);
        model.setCuentasOrigen(rows);
        model.commit();
    }

    public void buscarDestino(String filtro){
        List<Cuenta> rows = Service.instance().cuentasSearchDestino(filtro);
        model.setCuentasDestino(rows);
        model.commit();
    }
}
