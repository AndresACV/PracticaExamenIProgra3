package transferencias.presentation.transferencias;

import transferencias.Application;
import transferencias.presentation.cuentas.TableModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
    public View() {
        BuscarButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.buscarOrigen(nombreField1.getText());
            }
        });
        BuscarButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.buscarDestino(nombreField2.getText());
            }
        });
        transferirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clean();
                if(validate()) {
                    model.getCuentasOrigen().get(origenTable.getSelectedRow()).setSaldo(model.getCuentasOrigen().get(origenTable.getSelectedRow()).getSaldo() - Integer.parseInt(montoField.getText()));
                    model.getCuentasDestino().get(destinoTable.getSelectedRow()).setSaldo(model.getCuentasDestino().get(destinoTable.getSelectedRow()).getSaldo() + Integer.parseInt(montoField.getText())) ;
                    panel.updateUI();
                }
            }
        });
    }

    public JPanel getPanel() {
        return panel;
    }

    public void setController(transferencias.presentation.transferencias.Controller controller) { this.controller = controller; }
    public void setModel(transferencias.presentation.transferencias.Model model) {
        this.model = model;
        model.addObserver(this);
    }

    private boolean validate() {
        boolean valid = true;
        String mensajeError = "";

        if(origenTable.getSelectedRowCount() != 1  || destinoTable.getSelectedRowCount() != 1) {
            valid = false;
            origenTable.setBorder(Application.BORDER_ERROR);
            destinoTable.setBorder(Application.BORDER_ERROR);
            mensajeError += "Debe seleccionar una cuenta de cada tipo para realizar la transaccion. ";
        }
        if(montoField.getText().isEmpty()) {
            valid = false;
            montoField.setBorder(Application.BORDER_ERROR);
            mensajeError += "Debe llenar el campo monto. ";
        } else if(!montoField.getText().matches("[0-9]+")) {
            valid = false;
            montoField.setBorder(Application.BORDER_ERROR);
            mensajeError += "El monto debe ser un numero positivo. ";
        }else if(model.getCuentasOrigen().get(origenTable.getSelectedRow()).getSaldo() - Integer.parseInt(montoField.getText()) <= -1) {
            valid = false;
            montoField.setBorder(Application.BORDER_ERROR);
            mensajeError += "El monto no puede ser mayor al saldo de la cuenta seleccionada. ";
        }
        if(!mensajeError.equals("")){
            JOptionPane.showMessageDialog(panel, mensajeError,"ERROR",JOptionPane.ERROR_MESSAGE);
        }
        return valid;
    }

    public void clean() {
        transferirButton.setBorder(null);
        montoField.setBorder(null);
        origenTable.setBorder(null);
        destinoTable.setBorder(null);
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
