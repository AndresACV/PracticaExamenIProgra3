package transferencias.presentation.cuenta;

import transferencias.Application;
import transferencias.logic.Cuenta;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

public class View implements Observer {

    Controller controller;
    Model model;
    private JPanel panel;
    private JLabel numeroLabel;
    private JLabel nombreLabel;
    private JLabel saldoLabel;
    private JTextField numeroField;
    private JTextField nombreField;
    private JTextField saldoField;
    private JButton guardarButton;
    private JButton cancelarButton;
    private JLabel tipoLabel;
    private JComboBox tipoCheck;

    public View() {
        guardarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clean();
                if (validate()) {
                    try {
                        controller.guardar(take());
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(panel, "El numero de cuenta debe ser unico","ERROR",JOptionPane.ERROR_MESSAGE);
                        numeroLabel.setBorder(Application.BORDER_ERROR);
                    }
                }
            }
        });
        cancelarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.hide();
            }
        });
    }

    public JPanel getPanel() {
        return panel;
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

    public void setModel(Model model) {
        this.model = model;
        model.addObserver(this);
    }

    @Override
    public void update(Observable updatedModel, Object parametros) {
        Cuenta current = model.getCurrent();
        this.numeroField.setEnabled(model.getModo() == Application.MODO_AGREGAR);
        this.numeroField.setText(current.getNumero());
        this.nombreField.setText(current.getNombre());

        if(String.valueOf(current.getSaldo()).equals("0.0")){ saldoField.setText(""); }
        else { saldoField.setText(String.valueOf(current.getSaldo())); }

        this.panel.validate();
    }

    public void clean() {
        numeroLabel.setBorder(null);
        nombreLabel.setBorder(null);
        saldoLabel.setBorder(null);
        tipoLabel.setBorder(null);
    }

    public Cuenta take() {
        Cuenta c = new Cuenta();
        c.setNumero(numeroField.getText());
        c.setNombre(nombreField.getText());
        c.setSaldo(Double.parseDouble(saldoField.getText()));
        c.setTipo(tipoCheck.getSelectedItem().toString());
        return c;
    }

    private boolean validate() {
        boolean valid = true;
        String mensajeError = "";
        int concatenaciones = 0;

        if (numeroField.getText().isEmpty()) {
            valid = false;
            numeroLabel.setBorder(Application.BORDER_ERROR);
            mensajeError += "Numero de cuenta requerida. ";
        } else if(!numeroField.getText().matches("[0-9]+")){
            valid = false;
            numeroLabel.setBorder(Application.BORDER_ERROR);
            mensajeError += "Numero de cuenta debe ser numerico. ";
        }

        if (nombreField.getText().isEmpty()) {
            valid = false;
            nombreLabel.setBorder(Application.BORDER_ERROR);
            mensajeError += "Nombre del propietario de la cuenta requerido. ";
        } else if(!nombreField.getText().matches("^[a-z\\sA-Z]+$")){
            valid = false;
            nombreLabel.setBorder(Application.BORDER_ERROR);
            mensajeError += "Nombre del propietario de la cuenta  debe ser alfabético. ";
        }

        if (saldoField.getText().isEmpty()) {
            valid = false;
            saldoLabel.setBorder(Application.BORDER_ERROR);
            mensajeError += "Saldo de la cuenta requerido. ";
        } else if(!saldoField.getText().matches("^[0-9]+\\.?[0-9]*$")){
            valid = false;
            saldoLabel.setBorder(Application.BORDER_ERROR);
            mensajeError += "Saldo de la cuenta debe ser numerico. ";
        }

        if(!mensajeError.equals("")){
            JOptionPane.showMessageDialog(panel, mensajeError,"ERROR",JOptionPane.ERROR_MESSAGE);
        }

        return valid;
    }
}
