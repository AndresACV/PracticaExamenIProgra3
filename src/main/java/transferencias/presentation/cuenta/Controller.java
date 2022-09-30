package transferencias.presentation.cuenta;

import transferencias.Application;
import transferencias.logic.Cuenta;
import transferencias.logic.Service;

import javax.swing.*;

public class Controller {

    View view;
    Model model;
    JDialog dialog;

    public Controller(View view, Model model) {
        model.setCuentas(Service.instance().getData().getCuentas());
        model.setCurrent(new Cuenta());
        this.view = view;
        this.model = model;
        view.setController(this);
        view.setModel(model);
    }

    public void preAgregar(){
        model.setModo(Application.MODO_AGREGAR);
        model.setCurrent(new Cuenta());
        model.commit();
        this.show();
    }

    public void show(){
        dialog = new JDialog(Application.window,"Cuenta", true);
        dialog.setSize(700,470);
        dialog.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        dialog.setContentPane(view.getPanel());
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
    }
    public void hide(){
        dialog.dispose();
        dialog.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        view.clean();
    }

    public void guardar(Cuenta c) throws Exception {
        switch (model.getModo()) {
            case Application.MODO_AGREGAR:
                Service.instance().agregarCuenta(c);
                model.setCurrent(new Cuenta());
                break;
            case Application.MODO_EDITAR:
                Service.instance().cuentaUpdate(c);
                model.setCurrent(c);
                break;
        }
        Application.controllerCuentas.buscar("");
        model.commit();
    }

    public void editar(Cuenta c){
        model.setModo(Application.MODO_EDITAR);
        model.setCurrent(c);
        model.commit();
        this.show();
    }
}
