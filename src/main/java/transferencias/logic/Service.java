package transferencias.logic;

import transferencias.data.Data;
import transferencias.data.XmlPersister;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Service {

    private static Service instance;
    private static XmlPersister persister;
    private Data data;

    public static Service instance() {
        if (instance == null) {
            instance = new Service();
            persister = new XmlPersister("data.xml");
        }
        return instance;
    }

    private Service(){
        try{ data = XmlPersister.instance().load(); }
        catch(Exception e){ data = new Data(); }
    }

    public Data getData() {
        return data;
    }

    public void store(){
        try { XmlPersister.instance().store(data); }
        catch (Exception e) { throw new RuntimeException(e); }
    }

    public List<Cuenta> cuentasSearchNombre(String filtro) {
        return data.getCuentas().stream()
                .filter(e -> e.getNombre().contains(filtro))
                .sorted(Comparator.comparing(Cuenta::getNumero))
                .collect(Collectors.toList());
    }

    public List<Cuenta> cuentasSearchTipo(String filtro) {
        return data.getCuentas().stream()
                .filter(e -> e.getNombre().contains(filtro))
                .sorted(Comparator.comparing(Cuenta::getNumero))
                .collect(Collectors.toList());
    }

    public Cuenta cuentaGet(String numero) { return data.getCuentas().stream().filter(e -> e.getNumero().equals(numero)).findFirst().orElse(null); }

    public void agregarCuenta(Cuenta cuenta) throws Exception {
        Cuenta result = data.getCuentas().stream().filter(e -> e.getNumero().equals(cuenta.getNumero())).findFirst().orElse(null);
        if (result == null) {
            data.getCuentas().add(cuenta);
            this.store();
        } else {
            throw new Exception("Cuenta ya existe");
        }
    }

    public void cuentaUpdate(Cuenta cuenta) throws Exception {
        Cuenta result;
        try {
            result = this.cuentaGet(cuenta.getNumero());
            data.getCuentas().remove(result);
            data.getCuentas().add(cuenta);
            this.store();
        } catch (Exception e) {
            throw new Exception("Cuenta no existe");
        }
    }

    public List<Cuenta> eliminarCuenta(String numero) throws Exception {
        for (int i = 0; i < data.getCuentas().size(); i++) {
            if (Objects.equals(data.getCuentas().get(i).getNumero(), numero)) {
                data.getCuentas().remove(i);
                this.store();
                return data.getCuentas();
            }
        }
        throw new Exception("Empleado no existe");
    }
}
