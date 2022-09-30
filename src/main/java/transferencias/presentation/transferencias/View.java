package transferencias.presentation.transferencias;

import transferencias.presentation.cuentas.TableModel;

import javax.swing.*;
import java.util.Observable;
import java.util.Observer;

public class View implements Observer {
    transferencias.presentation.transferencias.Controller controller;
    transferencias.presentation.transferencias.Model model;

    private JPanel panel;
    private JLabel nombreLabel1;
    private JTextField nombreField1;
    private JButton BuscarButton1;
    private JLabel origenLabel;
    private JTable origenTable;
    private JLabel nombreLabel2;
    private JTextField nombreField2;
    private JButton BuscarButton2;
    private JTable destinoTable;
    private JLabel montoLabel;
    private JTextField montoField;
    private JButton transferirButton;

    public JPanel getPanel() {
        return panel;
    }

    public void setController(transferencias.presentation.transferencias.Controller controller) { this.controller = controller; }
    public void setModel(transferencias.presentation.transferencias.Model model) {
        this.model = model;
        model.addObserver(this);
    }

    @Override
    public void update(Observable updatedModel, Object parametros) {
        int[] cols = {transferencias.presentation.transferencias.TableModel.NUMERO, transferencias.presentation.transferencias.TableModel.NOMBRE, transferencias.presentation.transferencias.TableModel.SALDO};
        origenTable.setModel(new TableModel(cols, model.getCuentasOrigen()));
        origenTable.setRowHeight(30);
        destinoTable.setModel(new TableModel(cols, model.getCuentasDestino()));
        destinoTable.setRowHeight(30);
        this.panel.revalidate();
    }
}
