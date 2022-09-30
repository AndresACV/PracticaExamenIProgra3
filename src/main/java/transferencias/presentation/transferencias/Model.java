package transferencias.presentation.transferencias;

import transferencias.logic.Cuenta;

import java.util.ArrayList;
import java.util.List;
import java.util.Observer;

public class Model extends java.util.Observable {
    List<Cuenta> cuentasOrigen;
    List<Cuenta> cuentasDestino;

    public Model() {
        this.setCuentasOrigen(new ArrayList<Cuenta>());
        this.setCuentasDestino(new ArrayList<Cuenta>());
    }

    public List<Cuenta> getCuentasOrigen() { return cuentasOrigen; }
    public void setCuentasOrigen(List<Cuenta> cuentasOrigen) { this.cuentasOrigen = cuentasOrigen; }

    public List<Cuenta> getCuentasDestino() { return cuentasDestino; }
    public void setCuentasDestino(List<Cuenta> cuentasDestino) { this.cuentasDestino = cuentasDestino; }

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