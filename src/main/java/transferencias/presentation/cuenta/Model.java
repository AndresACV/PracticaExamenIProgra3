package transferencias.presentation.cuenta;

import transferencias.logic.Cuenta;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class Model extends Observable {

    List<Cuenta> cuentas;
    Cuenta current;
    int modo;

    public Model() {
        this.setCuentas(new ArrayList<>());
    }

    public List<Cuenta> getCuentas() { return cuentas; }
    public void setCuentas(List<Cuenta> cuentas) { this.cuentas = cuentas; }

    public int getModo() {
        return modo;
    }
    public void setModo(int modo) {
        this.modo = modo;
    }

    public Cuenta getCurrent() {
        return current;
    }
    public void setCurrent(Cuenta current) {
        this.current = current;
    }

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