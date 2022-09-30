package transferencias.presentation.cuentas;

import transferencias.logic.Cuenta;

import java.util.ArrayList;
import java.util.List;
import java.util.Observer;

public class Model extends java.util.Observable {
    List<Cuenta> cuentas;

    public Model() {
        this.setCuentas(new ArrayList<Cuenta>());
    }

    public List<Cuenta> getCuentas() { return cuentas; }
    public void setCuentas(List<Cuenta> cuentas) { this.cuentas = cuentas; }

    @Override
    public void addObserver(Observer o) {
        super.addObserver(o);
        commit();
    }

    public void commit(){
        setChanged();
        notifyObservers(null);
    }
}