package transferencias.data;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;
import transferencias.Application;
import transferencias.logic.Cuenta;

import java.util.ArrayList;
import java.util.List;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Data {
    private List<Cuenta> cuentas;

    public List<Cuenta> getCuentas() { return cuentas; }
    public void setCuentas(List<Cuenta> cuentas) { this.cuentas = cuentas; }

    public Data() {
        cuentas = new ArrayList<>();
//        cuentas.add(new Cuenta("1234", "Juan Perez", 1000.0, Application.ORIGEN));
//        cuentas.add(new Cuenta("5678", "Maria Lopez", 2000.0, Application.ORIGEN));
//        cuentas.add(new Cuenta("9012", "Pedro Gomez", 3000.0, Application.DESTINO));
//        cuentas.add(new Cuenta("3456", "Ana Martinez", 4000.0, Application.DESTINO));
//        cuentas.add(new Cuenta("7890", "Jose Rodriguez", 5000.0, Application.DESTINO));
    }
}
