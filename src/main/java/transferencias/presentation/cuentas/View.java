package transferencias.presentation.cuentas;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Objects;
import java.util.Observable;
import java.util.Observer;

public class View implements Observer {

    Controller controller;
    Model model;
    private JPanel panel;

    private JLabel nombreLabel;
    private JTextField nombreField;
    private JButton buscarButton;
    private JButton agregarButton;
    private JButton eliminarButton;
    private JTable cuentasTable;

    public View() {
        buscarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.buscar(nombreField.getText());
            }
        });
        eliminarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if(!Objects.equals(nombreField.getText(), "")){
                        controller.eliminar(nombreField.getText());
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(panel, "Sucursal no existe","ERROR",JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        cuentasTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int row = cuentasTable.getSelectedRow();
                    controller.editar(row);
                }
            }
        });
        agregarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.preAgregar();
            }
        });
    }

    public JPanel getPanel() {
        return panel;
    }

    public void setController(Controller controller) { this.controller = controller; }
    public void setModel(Model model) {
        this.model = model;
        model.addObserver(this);
    }

    @Override
    public void update(Observable updatedModel, Object parametros) {
        int[] cols = {TableModel.NUMERO, TableModel.NOMBRE, TableModel.SALDO, TableModel.TIPO};
        cuentasTable.setModel(new TableModel(cols, model.getCuentas()));
        cuentasTable.setRowHeight(30);
        this.panel.revalidate();
    }
}
