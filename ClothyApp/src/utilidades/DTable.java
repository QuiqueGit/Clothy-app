package utilidades;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author Quique
 */
public class DTable extends JTable{
    
    @Override
    public Component prepareRenderer(TableCellRenderer renderer, int rowIndex, int columnIndex){
        Component component = super.prepareRenderer(renderer, rowIndex, columnIndex);
        component.setBackground(Color.WHITE);
        component.setBackground(Color.BLACK);
        if ((int.class.equals(this.getColumnClass(columnIndex)))){
            int val = Integer.parseInt(getValueAt(rowIndex, columnIndex).toString());
            if (val == 0) {
                component.setBackground(Color.RED);
                component.setForeground(Color.BLACK);
            }
        }
        return component;
    }
    
}
