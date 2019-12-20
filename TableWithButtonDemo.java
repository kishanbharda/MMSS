import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;

public class TableWithButtonDemo
{
	public JFrame frame=new JFrame("table Demo");
	public String[] columnNames={"String","Integer","Float",""};
	public Object[][] data={{"Dummy",new Integer(12),new Float(12.15),"Consulter"}}
	public TableModel model=new DefaultTableModel(data,columnNames);
}