package transferencias;

import transferencias.logic.Cuenta;
import transferencias.presentation.cuentas.Model;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class Application {

    public static JFrame window;

    public static  final int  MODO_AGREGAR=0;
    public static  final int   MODO_EDITAR=1;

    public static final String ORIGEN = "ORIGEN";
    public static final String DESTINO = "DESTINO";

    public static Border BORDER_ERROR = BorderFactory.createMatteBorder(0, 0, 2, 0, Color.RED);

    public static transferencias.presentation.cuentas.Controller        controllerCuentas;
    public static transferencias.presentation.cuenta.Controller         controllerCuenta;
    public static transferencias.presentation.transferencias.Controller controllerTransferencias;
    public static transferencias.presentation.main.Controller           controllerMain;

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");}
        catch (Exception ignored) {};

        transferencias.presentation.main.Model modelMain= new transferencias.presentation.main.Model();
        transferencias.presentation.main.View viewMain = new transferencias.presentation.main.View();
        controllerMain = new transferencias.presentation.main.Controller(viewMain, modelMain);

        transferencias.presentation.cuentas.Model modelCuentas= new transferencias.presentation.cuentas.Model();
        transferencias.presentation.cuentas.View viewCuentas = new transferencias.presentation.cuentas.View();
        controllerCuentas = new transferencias.presentation.cuentas.Controller(viewCuentas, modelCuentas);

        transferencias.presentation.cuenta.Model modelCuenta= new transferencias.presentation.cuenta.Model();
        transferencias.presentation.cuenta.View viewCuenta = new transferencias.presentation.cuenta.View();
        controllerCuenta = new transferencias.presentation.cuenta.Controller(viewCuenta, modelCuenta);

        transferencias.presentation.transferencias.Model modelTransferencias= new transferencias.presentation.transferencias.Model();
        transferencias.presentation.transferencias.View viewTransferencias = new transferencias.presentation.transferencias.View();
        controllerTransferencias = new transferencias.presentation.transferencias.Controller(viewTransferencias, modelTransferencias);

        viewMain.getPanel().add("Cuentas", viewCuentas.getPanel());
        viewMain.getPanel().add("Transferencias", viewTransferencias.getPanel());

        window = new JFrame();
        window.setSize(1000,350);
        window.setExtendedState(JFrame.NORMAL);
        window.setLocationRelativeTo(null);
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        window.setTitle("SISTEMA DE CUENTAS BANCARIAS Y TRANSFERENCIAS");
        window.setVisible(true);
        controllerMain.show();
    }
}