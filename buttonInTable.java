import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.UIManager;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

public class buttonInTable extends JFrame
{
	public buttonInTable()
	{
		super("JButton in Table");
		DefaultTableModel dm=new DefaultTableModel();
		dm.setDataVector(new Object[][]{{"button1","foo"},{"button2","bar"}},new Object[]{"button","String"});
		
		JTable table=new JTable(dm);
		table.getColumn("Button").setCellRenderer(new ButtonRenderer());
		table.getColumn("Button").setCellEditor(new ButtonEditor(new  JCheckBox()));
		JScrollPane scroll=new JScrollPane(table);
		getContentPane().add(scroll);
		setSize(400,100);
		setVisible(true);
	}
	
	public static void main(String args[])
	{
		buttonInTable frame=new buttonInTable();
		frame.addWindowListener(new WindowAdapter(){
			public void WindowClosing(WindowEvent e)
			{
				System.exit(0);
			}
		});
	}
}

class ButtonRenderer extends JButton implements TableCellRenderer
{
	public ButtonRenderer()
	{
		setOpaque(true);
	}
	
	public Component getTableCellRendererComponent(JTable table,Object value,boolean isSelected,boolean hasFocus,int row,int column)
	{
		if(isSelected)
		{
			setForeground(table.setSelectionForeground());
			setBackground(table.getSelectionBackground());
		}
		else
		{
			setForeground(table.getForeground());
			setBackground(UIManager.getColor("Button.background"));
		}
		
		setText((value==null)?"":value.toString());
	}
	
}

class ButtonEditor extends DefaultCellEditor
{
	public JButton button;
	public String label;
	public boolean isPushed;
	
	public ButtonEditor(JCheckBox checkBox)
	{
		super(checkBox);
		button =new JButton();
		button.setOpaque(true);
		button.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				fireEditingStopped();
			}
		});
	}
	
	public Component getTableCellRendererComponent(JTable table,Object value,boolean isSelected,int row,int column)
	{
		if(isSelected)
		{
			button.setForeground(table.getSelectionForeground());
			button.setBackground(table.getSelectionBackground());
			
		}
		else
		{
			button.setForeground(table.getForeground());
			button.setBackground(table.getBackground());
		}
		
		label=(value==null)?"":value.toString();
		button.setText(lable);
		isPushed=true;
		return button;
	}
	
	public Object getCellEditorValue()
	{
		if(isPushed)
		{
			JOptionPane.showMessageDialog(button,label+" : Ouch !");
		}
		isPushed=false;
		return new String(label);
	}
	
	public boolean stopCellEditig()
	{
		isPushed=false;
		return super.stopCellEditig();
	}
	
	public void fireEditingStopped()
	{
		super.fireEditingStopped();
	}
}