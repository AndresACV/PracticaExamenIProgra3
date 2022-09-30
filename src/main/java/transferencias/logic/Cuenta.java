package transferencias.logic;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlID;
import transferencias.Application;

@XmlAccessorType(XmlAccessType.FIELD)
public class Cuenta {
    @XmlID
    String numero;
    String nombre;
    Double saldo;
    String tipo;

    public Cuenta(String numero, String nombre, Double saldo, String tipo) {
        this.numero = numero;
        this.nombre = nombre;
        this.saldo = saldo;
        this.tipo = tipo;
    }

    public Cuenta() { this("","",0.0, Application.ORIGEN); }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cuenta cuenta)) return false;

        return numero.equals(cuenta.numero);
    }

    public String getNumero() { return numero; }
    public void setNumero(String numero) { this.numero = numero; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public Double getSaldo() { return saldo; }
    public void setSaldo(Double saldo) { this.saldo = saldo; }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }
}
