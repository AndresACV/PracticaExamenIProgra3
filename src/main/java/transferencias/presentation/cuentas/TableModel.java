package transferencias.presentation.cuentas;

import transferencias.logic.Cuenta;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class TableModel extends AbstractTableModel implements javax.swing.table.TableModel {

    String[] colNames = new String[4];
    public static final int NUMERO=0;
    public static final int NOMBRE=1;
    public static final int SALDO=2;
    public static  final int TIPO=3;

    List<Cuenta> rows;
    int[] cols;

    private void initColNames(){
        colNames[NUMERO]= "Cta";
        colNames[NOMBRE]= "Nombre";
        colNames[SALDO]= "Saldo";
        colNames[TIPO]= "Tipo";
    }

    public TableModel(int[] cols, List<Cuenta> rows){
        initColNames();
        this.cols=cols;
        this.rows=rows;
    }

    public int getColumnCount() {
        return cols.length;
    }
    public String getColumnName(int col){
        return colNames[cols[col]];
    }
    public int getRowCount() {
        return rows.size();
    }
    public Class<?> getColumnClass(int col){ return super.getColumnClass(col); }

    public Object getValueAt(int row, int col) {
        Cuenta cuenta = rows.get(row);
        switch (cols[col]){
            case NUMERO: return cuenta.getNumero();
            case NOMBRE: return cuenta.getNombre();
            case SALDO: return cuenta.getSaldo();
            case TIPO: return cuenta.getTipo();
            default: return "";
        }
    }
}