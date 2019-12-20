import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.Date.*;
import java.util.Calendar.*;
import java.text.*;
import org.jdatepicker.*;
import org.jdatepicker.graphics.*;
import org.jdatepicker.impl.*;
import org.jdatepicker.util.*;
import javax.swing.table.*;
import java.sql.*;

import javax.swing.DefaultCellEditor;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.table.TableCellRenderer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.HashMap;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.*;
import net.sf.jasperreports.engine.util.*;

public class home implements ActionListener
{
	JFrame hf;
	Font f;
	static Connection con;
	int i=1;
	JMenuBar mb;
	JMenu mnHome,mnSell,mnTransaction,mnReport,mnClient,mnCompany;
	JMenuItem miLogout,miExit,miHome,miChangePassword;
	JMenuItem miToCustomized,miToRetailer;
	JMenu mnCategory,mnItem,mnStock;
	JMenuItem miAddCategory,miRemoveCategory,miUpdateCategory;
	JMenuItem miAddItem,miRemoveItem,miUpdateItem;
	JMenuItem miAddStock,miRemoveStock,miUpdateStock;
	JMenuItem miViewStock,miViewCategory,miViewItem;
	JMenuItem miViewCompany,miAddCompany,miRemoveCompany,miUpdateCompany;
	JMenuItem miStock,miItem,miCategory,miCompany,miClient,miRetailerSales,miCustomizedSales;
	JMenuItem miAddClient,miRemoveClient,miUpdateClient,miViewClient;
	
	JPanel panelSearchResult;
	JPanel panelChangePassword;
	JPanel panelSellCustoized,panelSellRetailer;
	JPanel panelAddCategory,panelRemoveCategory,panelUpdateCategory,panelViewCategory;
	JPanel panelAddItem,panelRemoveItem,panelUpdateItem,panelViewItem;
	JPanel panelAddStock,panelViewStock,panelRemoveStock,panelUpdateStock;
	JPanel panelPurchasedReport,panelSelledReport;
	JPanel panelAddClient,panelRemoveClient,panelUpdateClient,panelViewClient;
	JPanel panelAddCompany,panelRemoveCompany,panelUpdateCompany,panelViewCompany;
	
	//-------------------------------------------------------------changePassword
	JPasswordField pfOldPassword,pfNewPassword,pfConfirmPassword;
	JButton btnChangePasswordCancel,btnChangePassword;
	//------------------------------------------------------------
	//-------------------------------------------------------------sellToRetailer
	JTextField txtSTRCustomerName,txtSTRCity,txtSTRAge,txtSTRContactNo;
	JComboBox cbbSTRItemName,cbbSTRCategory,cbbSTRCompany;
	JTextField txtSTRQuantity;
	JButton btnRetailerAddToCart,btnCancelOrder,btnSubmitOrder;
	JRadioButton rbSTRUnit,rbSTRPackage;
	static JPanel panelRCart;
	static DefaultTableModel modelRBill;
	static JTable tableRBill;
	static JScrollPane spRBill;
	static JLabel lblRCGrandTotal,lblRCAmount ;
	//--------------------------------------------------------------
	//--------------------------------------------------------------sellToCustomized
	JComboBox cbbSTCClientName,cbbSTCCity;
	JComboBox cbbSTCItemName,cbbSTCCategory,cbbSTCCompany;
	JTextField txtSTCQuantity;
	JButton btnCustomizerAddToCart,btnCancelCOrder,btnSubmitCOrder;
	JRadioButton rbSTCUnit,rbSTCPackage;
	JPanel panelCCart;
	
	//--------------------------------------------------------------
	//------------------------------------------------------------addCategory
	JTextField txtACCategory;
	JButton btnAddToCategory,btnAddCategoryCancel;
	//------------------------------------------------------------
	//-----------------------------------------------------------removeCategory
	JComboBox cbbRCCategory;
	JButton btnRemoveCategoryCancel,btnRemoveCategory;
	//-----------------------------------------------------------
	//-----------------------------------------------------------updateCategory
	JComboBox cbbUCCategory;
	JTextField txtUCCategory;
	JButton btnUpdateCategoryCancel,btnUpdateCategory;
	//-----------------------------------------------------------
	//----------------------------------------------------------ViewCategory
	JTextField txtCategorySearch;
	JLabel lblCatagorySearch;
	JScrollPane spViewCategory;
	DefaultTableModel modelCategory;
	JTable tableCategory;
	JButton btnCategoryPrint;
	//--------------------------------------------------------
	
	//-----------------------------------------------------------addItem
	JTextField txtAISrNo,txtAIItemName,txtAIPrice,txtAIUnitInPackage;
	JTextArea txtAIDetail;
	UtilDateModel modelAIMnfDate,modelAIExpDate;
	JDatePanelImpl dpAIMnfDate,dpAIExpDate;
	JDatePickerImpl AIMnfDate,AIExpDate;
	Properties pAIMnfDate,pAIExpDate;
	JComboBox cbbAICompany,cbbAICategory;
	JRadioButton rbAIPerUnit,rbAIPerPackage;
	JButton btnAddItemCancel,btnAddItem;
	//-----------------------------------------------------------
	
	//-----------------------------------------------------------removeItem
	JComboBox cbbRIItemName,cbbRICategory,cbbRICompany;
	JTextArea txtRIReason;
	JButton btnRemoveItemCancel,btnRemoveItem;
	//-----------------------------------------------------------
	//-----------------------------------------------------------updateItem
	JComboBox cbbUIItemName,cbbUISCategory,cbbUISCompany;
	JButton btnUISelect;
	JTextField txtUISrNo,txtUIItemName,txtUIDetail,txtUIPrice,txtUIUnitInPackage,txtUICategory,txtUICompany;
	JRadioButton rbUIPerUnit,rbUIPerPackage;
	UtilDateModel modelUIMnfDate,modelUIExpDate;
	JDatePanelImpl dpUIMnfDate,dpUIExpDate;
	JDatePickerImpl UIMnfDate,UIExpDate;
	Properties pUIMnfDate,pUIExpDate;
	JComboBox cbbUICompanyName,cbbUICategory;
	JButton btnUpdateItemCancel,btnUpdateItem;
	//-----------------------------------------------------------
	//------------------------------------------------------------viewItem
	JScrollPane spViewItem;
	JTextField txtItemSearch;
	JLabel lblItemSearch;
	DefaultTableModel modelItem;
	JTable tableItem;
	JButton btnItemPrint;
	//-----------------------------------------------------------
	
	//------------------------------------------------------------addStock method component
	JComboBox cbbASCategory,cbbASCompanyName,cbbASItemName;
	JTextField txtASQuantity,txtASLocation,txtASSPrice;
	JLabel lblU,lblP;
	JButton btnAddToStock,btnAddStockCancel,btnASSelect;
	JRadioButton rbASUnit,rbASPackage,rbASPPackage,rbASPUnit;	
	JLabel lblASUnitInPackage;
	//------------------------------------------------------------
	
	//------------------------------------------------------------removeStock method component
	JComboBox cbbRSCategory,cbbRSItemName,cbbRSCompanyName;
	JButton btnRemoveStockCancel,btnRemoveStock;
	//-------------------------------------------------------------
	
	//-------------------------------------------------------------updateStock method component
	JComboBox cbbUSCategory,cbbUSItemName,cbbUSCompany;
	JTextField txtUSLocation,txtUSCStockInUnit,txtUSCStockInPackage,txtUSNewStockInUnit,txtUSNewStockInPackage,txtUSCUnitPrice,txtUSNUnitPrice,txtUSCPackagePrice,txtUSNPackagePrice;
	JButton btnUpdateStockCancel,btnUpdateStock,btnUSSelect;
	JLabel lblUnit;
	//------------------------------------------------------------
	//-----------------------------------------------------------viewStock
	JScrollPane spViewStock;
	DefaultTableModel modelStock;
	JTable tableStcock;
	JTextField txtStockSearch;
	JLabel lblStockSearch;
	JButton btnStockPrint;
	//-----------------------------------------------------------
	

	
	//--------------------------------------------------------------addClient method component
	JTextField txtACLName,txtACLCity,txtACLContactNo,txtACLEmail;
	JTextArea txtACLAddress;
	JButton btnAddClientCancel,btnAddClient;
	//--------------------------------------------------------------
	
	//--------------------------------------------------------------removeClient method component
	JComboBox cbbRCLClient;
	JTextArea txtRCLReason;
	JButton btnRemoveClientCancel,btnRemoveClient;
	//--------------------------------------------------------------
	
	//--------------------------------------------------------------updateClient method component
	JComboBox cbbUCLClient;
	JTextField txtUCLName,txtUCLCity,txtUCLContactNo,txtUCLEmail;
	JTextArea txtUCLAddress;
	JButton btnUpdateClientCancel,btnUpdateClient;
	//--------------------------------------------------------------
	
	//--------------------------------------------------------------viewClient method component
	JScrollPane spViewClient;
	JTextField txtClientSearch;
	JLabel lblClientSearch;
	DefaultTableModel modelClient;
	JTable tableClient;
	JButton btnClientPrint;
	//--------------------------------------------------------------
	
	//--------------------------------------------------------------addCompany method component
	JTextField txtACLicenceNo,txtACCompanyName,txtACCity,txtACContactPerson,txtACContactNo,txtACEmail;
	JTextArea txtACDetails,txtACAddress;
	JButton btnAddCompany,btnAddCompanyCancel;
	//--------------------------------------------------------------
	
	//--------------------------------------------------------------removeCompany method component
	JComboBox cbbRCCompanyName;
	JTextArea txtRCNote;
	JButton btnRemoveCompanyCancel,btnRemoveCompany;
	//--------------------------------------------------------------
	
	//--------------------------------------------------------------updateCompany method component
	JComboBox cbbUCCompanyName;
	JTextField txtUCLicenceNo,txtUCCompanyName,txtUCCity,txtUCContactPerson,txtUCContactNo,txtUCEmail;
	JTextArea txtUCDetails,txtUCAddress;
	JButton btnUpdateCompanyCancel,btnUpdateCompany;
	//--------------------------------------------------------------
	
	//--------------------------------------------------------------viewCompany method component
	JScrollPane spViewCompany;
	JTextField txtCompanySearch;
	JLabel lblCompanySearch;
	DefaultTableModel modelCompany;
	JTable tableCompany;
	JButton  btnCompanyPrint;
	//--------------------------------------------------------------
	
	//--------------------------------------------------------------------report
	JPanel panelRetailerSales,panelCustomizedSales;
	DefaultTableModel modelRetailerSales,modelCutomizedSales;
	JTable tableRetailerSales,tableCustomizedSales;
	JScrollPane spRetailerSales,spCustomizedSales;
	
	JTextField txtRSSearch,txtCSSearch;
	JButton btnRetailerSalesPrint,btnCustomizedSalesPrint;
	//---------------------------------------------------------------------
	
	
	public home()
	{
		//-------------------------------------------------------frame layout
		try
		{
			con=DriverManager.getConnection("jdbc:ucanaccess://database\\MMSS.accdb");
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(hf,"Sorry !!! Database is not found...!");
		}
		
		
		
		hf=new JFrame("MEERA MEDICAL STORE SYSTEM");
		hf.setExtendedState(JFrame.MAXIMIZED_BOTH);
		hf.setLayout(null);
		hf.setDefaultCloseOperation(hf.EXIT_ON_CLOSE);
		
		
		//-------------------------------------------------------
		f=new Font("Baskerville Old Face",Font.PLAIN,20);
		//--------------------------------------------------------Image icon
		ImageIcon icon=new ImageIcon(new ImageIcon("icon/logo.png").getImage().getScaledInstance(1150,100,Image.SCALE_SMOOTH));
		JLabel lblIcon=new JLabel(icon);
		lblIcon.setBounds(100,10,1150,100);
		hf.add(lblIcon);
		//----------------------------------------------------------
		//----------------------------------------------------------dateLabel
		
		SimpleDateFormat df=new SimpleDateFormat("E dd.MM.yyyy");
		JLabel lblDate=new JLabel("Date : "+df.format(new java.util.Date()));
		lblDate.setHorizontalAlignment(JLabel.LEFT);
		lblDate.setFont(new Font("Times new Roman",Font.BOLD,20));
		lblDate.setForeground(Color.BLUE);
		lblDate.setBounds(100,650,300,30);
		
		hf.add(lblDate);
			
		panelSearchResult=new JPanel();
		panelSearchResult.setLayout(null);
		panelSearchResult.setVisible(true);
		panelSearchResult.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		panelSearchResult.setBounds(100,130,1150,500);
			
		ImageIcon icon2=new ImageIcon(new ImageIcon("icon/Picture2.png").getImage().getScaledInstance(1200,500,Image.SCALE_DEFAULT));
		JLabel lblIcon2=new JLabel();
		lblIcon2.setIcon(icon2);
		lblIcon2.setBounds(0,0,1200,500);
		panelSearchResult.add(lblIcon2);
		
		hf.add(panelSearchResult);
		
		
		
		//------------------------------------------------------------------
		
		
		
		//-----------------------------------------------------menu bar
	
		mb=new JMenuBar();
		
		mnHome=new JMenu("HOME");
			mnHome.setMnemonic(KeyEvent.VK_H);
			miHome=new JMenuItem("Home");
				miHome.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H,ActionEvent.CTRL_MASK));
				miHome.addActionListener(this);
			miLogout=new JMenuItem("Logout");	
				miLogout.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O,ActionEvent.CTRL_MASK));
				miLogout.addActionListener(this);
			miExit=new JMenuItem("Exit");
				miExit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E,ActionEvent.CTRL_MASK));
				miExit.addActionListener(this);
			miChangePassword=new JMenuItem("Change Password");
				miChangePassword.addActionListener(this);
			
			mnHome.add(miHome);
			mnHome.addSeparator();
			mnHome.add(miChangePassword);
			mnHome.add(miLogout);
			mnHome.add(miExit);
			
		mnSell=new JMenu("SALE");
			mnSell.setMnemonic(KeyEvent.VK_S);
			miToCustomized=new JMenuItem("To CUSTOMIZER");
			miToCustomized.addActionListener(this);
			miToRetailer=new JMenuItem("To RETAILER	");
			miToRetailer.addActionListener(this);
			
			mnSell.add(miToCustomized);
			mnSell.add(miToRetailer);
		
		mnTransaction=new JMenu("TRANSACTION");
		mnTransaction.setMnemonic(KeyEvent.VK_T);
			mnCategory=new JMenu("CATEGORY");
				miAddCategory=new JMenuItem("ADD CATEGORY");
					miAddCategory.addActionListener(this);
				miRemoveCategory=new JMenuItem("REMOVE CATEGORY");
					miRemoveCategory.addActionListener(this);
				miUpdateCategory=new JMenuItem("UPDATE CATEGORY");
					miUpdateCategory.addActionListener(this);				
			mnItem=new JMenu("ITEM");
				miAddItem=new JMenuItem("ADD ITEM");
					miAddItem.addActionListener(this);
				miRemoveItem=new JMenuItem("REMOVE ITEM");
					miRemoveItem.addActionListener(this);
				miUpdateItem=new JMenuItem("UPDATE ITEM");
					miUpdateItem.addActionListener(this);
			mnStock=new JMenu("STOCK");
				miAddStock=new JMenuItem("ADD STOCK");
					miAddStock.addActionListener(this);
				miRemoveStock=new JMenuItem("REMOVE STOCK");
					miRemoveStock.addActionListener(this);
				miUpdateStock=new JMenuItem("UPDATE STOCK");
					miUpdateStock.addActionListener(this);
			
			miViewCategory=new JMenuItem("VIEW CATEGORY");
			miViewCategory.addActionListener(this);
			
			miViewItem=new JMenuItem("VIEW ITEM");
			miViewItem.addActionListener(this);
			
			miViewStock=new JMenuItem("VIEW STOCK");
			miViewStock.addActionListener(this);
			
			
		
		mnTransaction.add(miViewCategory);
		mnTransaction.add(mnCategory);
			mnCategory.add(miAddCategory);
			mnCategory.add(miRemoveCategory);
			mnCategory.add(miUpdateCategory);
		mnTransaction.addSeparator();
		
		mnTransaction.add(miViewItem);
		mnTransaction.add(mnItem);
			mnItem.add(miAddItem);
			mnItem.add(miRemoveItem);
			mnItem.add(miUpdateItem);
		mnTransaction.addSeparator();
		
		mnTransaction.add(miViewStock);
		mnTransaction.add(mnStock);
			mnStock.add(miAddStock);
			mnStock.add(miRemoveStock);
			mnStock.add(miUpdateStock);
		
	
	
		mnReport=new JMenu("REPORT");
		mnReport.setMnemonic(KeyEvent.VK_R);
			miStock=new JMenuItem("STOCK");
				miStock.addActionListener(this);
			miItem=new JMenuItem("ITEM");
				miItem.addActionListener(this);
			miCategory=new JMenuItem("CATEGORY");
				miCategory.addActionListener(this);
			miCompany=new JMenuItem("COMPANY");
				miCompany.addActionListener(this);
			miClient=new JMenuItem("CLIENT");
				miClient.addActionListener(this);
			miRetailerSales=new JMenuItem("RETAILER SALES");
				miRetailerSales.addActionListener(this);
			miCustomizedSales=new JMenuItem("CUTOMIZED SALES");
				miCustomizedSales.addActionListener(this);
				
			mnReport.add(miStock);
			mnReport.add(miItem);
			mnReport.add(miCategory);
			mnReport.add(miCompany);
			mnReport.add(miClient);
			mnReport.add(miRetailerSales);
			mnReport.add(miCustomizedSales);
		
		mnClient=new JMenu("CLIENT");
		mnClient.setMnemonic(KeyEvent.VK_C);
			miAddClient=new JMenuItem("ADD CLIENT");
				miAddClient.addActionListener(this);
			miRemoveClient=new JMenuItem("REMOVE CLIENT");
				miRemoveClient.addActionListener(this);
			miUpdateClient=new JMenuItem("UPDATE CLIENT");
				miUpdateClient.addActionListener(this);
			miViewClient=new JMenuItem("VIEW CLIENT");
				miViewClient.addActionListener(this);
			
			mnClient.add(miAddClient);
			mnClient.add(miRemoveClient);
			mnClient.add(miUpdateClient);
			mnClient.addSeparator();
			mnClient.add(miViewClient);
			
		
		mnCompany=new JMenu("COMPANY");
		mnCompany.setMnemonic(KeyEvent.VK_P);
			miAddCompany=new JMenuItem("ADD COMPANY");
				miAddCompany.addActionListener(this);
			miRemoveCompany=new JMenuItem("REMOVE COMPANY");
				miRemoveCompany.addActionListener(this);
			miUpdateCompany=new JMenuItem("UPDATE COMPANY");
				miUpdateCompany.addActionListener(this);
			miViewCompany=new JMenuItem("VIEW COMPANY");
				miViewCompany.addActionListener(this);			
			
			mnCompany.add(miAddCompany);
			mnCompany.add(miRemoveCompany);
			mnCompany.add(miUpdateCompany);
			mnCompany.addSeparator();
			mnCompany.add(miViewCompany); 	
		//---------------------------------------------------------------------------
		
		
		
		//----------------------------------------------------implementation of menu bar
		
		mb.add(mnHome);
		mb.add(mnSell);
		mb.add(mnTransaction);
		mb.add(mnReport);
		mb.add(mnClient);
		mb.add(mnCompany);
		
		hf.setJMenuBar(mb);
		//-----------------------------------------------------------------------------
		
		changePassword();
		
		sellToCustomized();
		sellToRetailer();
		
		viewCategory();
		addCategory();
		removeCategory();
		updateCategory();
		
		viewItem();
		addItem();
		removeItem();
		updateItem();
		
		addStock();
		viewStock();
		removeStock();
		updateStock();
		
		addClient();
		removeClient();
		updateClient();
		viewClient();
		
		addCompany();
		removeCompany();
		updateCompany();
		viewCompany();
			
		retailerSalesReport();
		customizedSalesReport();
		
		hf.setVisible(true);
	
	}
	
	public void changePassword()
	{
		JLabel lblOldPassword,lblNewPassword,lblConfirmPassword;
		
		panelChangePassword=new JPanel();
		panelChangePassword.setLayout(null);
		panelChangePassword.setVisible(false);
		panelChangePassword.setBounds(100,150,1150,500);		
		panelChangePassword.setBorder(BorderFactory.createTitledBorder("Change Password Here"));
		
		lblOldPassword=new JLabel("Enter Old Password");
		lblOldPassword.setFont(f);
		pfOldPassword=new JPasswordField();
		
		lblNewPassword=new JLabel("Enter New Password");
		lblNewPassword.setFont(f);
		pfNewPassword=new JPasswordField();
		
		lblConfirmPassword=new JLabel("Re-Enter New Password");
		lblConfirmPassword.setFont(f);
		pfConfirmPassword=new JPasswordField();
		
		btnChangePasswordCancel=new JButton("Cancel");
		btnChangePasswordCancel.addActionListener(this);
		
		btnChangePassword=new JButton("Change Password");
		btnChangePassword.addActionListener(this);
		
		lblOldPassword.setBounds(250,50,230,30);
		pfOldPassword.setBounds(500,50,250,30);
		lblNewPassword.setBounds(250,100,230,30);
		pfNewPassword.setBounds(500,100,250,30);
		lblConfirmPassword.setBounds(250,150,230,30);
		pfConfirmPassword.setBounds(500,150,250,30);
		
		btnChangePasswordCancel.setBounds(370,200,100,30);
		btnChangePassword.setBounds(500,200,150,30);
		
		panelChangePassword.add(lblOldPassword);
		panelChangePassword.add(pfOldPassword);
		panelChangePassword.add(lblNewPassword);
		panelChangePassword.add(pfNewPassword);
		panelChangePassword.add(lblConfirmPassword);
		panelChangePassword.add(pfConfirmPassword);
		
		panelChangePassword.add(btnChangePasswordCancel);
		panelChangePassword.add(btnChangePassword);

		hf.add(panelChangePassword);
		
	}
	
	public void sellToCustomized()
	{
		panelSellCustoized=new JPanel();
		panelSellCustoized.setVisible(false);
		panelSellCustoized.setLayout(null);
		panelSellCustoized.setBounds(100,150,1150,500);
		panelSellCustoized.setBorder(BorderFactory.createTitledBorder("Sale To Customized Here"));
		
		panelCCart=new JPanel();
		panelCCart.setVisible(true);
		panelCCart.setLayout(null);
		panelCCart.setBounds(20,150,1100,340);
		panelCCart.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		panelSellCustoized.add(panelCCart);
		
		JPanel panelCustomerInfo=new JPanel();
		panelCustomerInfo.setVisible(true);
		panelCustomerInfo.setLayout(null);
		panelCustomerInfo.setBounds(10,20,1130,50);
		panelCustomerInfo.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		panelSellCustoized.add(panelCustomerInfo);
		
		JLabel lblClientName,lblCity,lblSelectItem,lblCategory,lblCompany,lblQuantity;
		
	
		lblClientName=new JLabel("Client Name");
		lblClientName.setFont(f);
		cbbSTCClientName=new JComboBox();
		cbbSTCClientName.setFont(f);
		cbbSTCClientName.addItemListener(new ItemListener(){
			public void itemStateChanged(ItemEvent ie)
			{
				cbbSTCCity.removeAllItems();
				try
				{
					PreparedStatement pst=con.prepareStatement("Select CITY from CLIENT where CLIENT_NAME=?");
					pst.setString(1,(String)cbbSTCClientName.getSelectedItem());
					ResultSet res=pst.executeQuery();
					while(res.next())
					{
						cbbSTCCity.addItem(res.getString("CITY"));
					}
				}
				catch(Exception e)
				{
					JOptionPane.showMessageDialog(hf,"Sorry !!! There are some error occured...!");
				}
			}
		});
		
		lblCity=new JLabel("City");
		lblCity.setFont(f);
		lblCity.setHorizontalAlignment(JLabel.RIGHT);
		cbbSTCCity=new JComboBox();
		cbbSTCCity.setFont(f);
		
		lblSelectItem=new JLabel("Select Item");
		lblSelectItem.setFont(f);
		cbbSTCItemName=new JComboBox();
		cbbSTCItemName.setFont(f);
		
		lblCategory=new JLabel("Category");
		lblCategory.setFont(f);
		lblCategory.setHorizontalAlignment(JLabel.RIGHT);
		cbbSTCCategory=new JComboBox();
		cbbSTCCategory.setFont(f);
		
		cbbSTCCategory.addItemListener(new ItemListener(){
			public void itemStateChanged(ItemEvent ie)
			{
				cbbSTCItemName.removeAllItems();
				try
				{
					PreparedStatement pst=con.prepareStatement("Select ITEM_NAME from STOCK where CATEGORY=?");
					pst.setString(1,(String)cbbSTCCategory.getSelectedItem());
					ResultSet rs=pst.executeQuery();
					
					while(rs.next())
					{
						cbbSTCItemName.addItem(rs.getString("ITEM_NAME"));
					}
					pst.close();
					
				}
				catch(Exception e)
				{
					JOptionPane.showMessageDialog(hf,"Sorry !!! There are some error occured...!");
				}
				
			}
		});
		
		cbbSTCItemName.addItemListener(new ItemListener(){
			public void itemStateChanged(ItemEvent ie)
			{
				cbbSTCCompany.removeAllItems();
				try
				{
					PreparedStatement pst=con.prepareStatement("Select COMPANY,QUANTITY_IN_PACKAGE from STOCK where ITEM_NAME=? and CATEGORY=?");
					pst.setString(1,(String)cbbSTCItemName.getSelectedItem());
					pst.setString(2,(String)cbbSTCCategory.getSelectedItem());
					ResultSet rs=pst.executeQuery();
					
					while(rs.next())
					{
						cbbSTCCompany.addItem(rs.getString("COMPANY"));
						rbSTCPackage.setEnabled(true);
					}
					pst.close();
					
				}
				catch(Exception e)
				{
					JOptionPane.showMessageDialog(hf,"Sorry !!! There are some error occured...!");
				}
				
			}
		});
		
		lblCompany=new JLabel("Company");
		lblCompany.setFont(f);
		lblCompany.setHorizontalAlignment(JLabel.RIGHT);
		cbbSTCCompany=new JComboBox();
		cbbSTCCompany.setFont(f);
		
		lblQuantity=new JLabel("Quantity");
		lblQuantity.setFont(f);
		lblQuantity.setHorizontalAlignment(JLabel.RIGHT);
		txtSTCQuantity=new JTextField(10);
		txtSTCQuantity.setFont(f);
		txtSTCQuantity.addKeyListener(new KeyAdapter(){
			public void keyTyped(KeyEvent ke)
			{
				char c=ke.getKeyChar();
				if(!Character.isDigit(c))
				{
					ke.consume();
				}
			}
		});
		txtSTCQuantity.addFocusListener(new FocusAdapter(){
			public void focusGained(FocusEvent fe)
			{
				try
				{
					PreparedStatement pst=con.prepareStatement("Select QUANTITY_IN_PACKAGE from STOCK where ITEM_NAME=? and CATEGORY=? and COMPANY=?");
					pst.setString(1,(String)cbbSTCItemName.getSelectedItem());
					pst.setString(2,(String)cbbSTCCategory.getSelectedItem());
					pst.setString(3,(String)cbbSTCCompany.getSelectedItem());
					ResultSet rs=pst.executeQuery();
					while(rs.next())
					{
					if(rs.getInt("QUANTITY_IN_PACKAGE")==0)
					{
						rbSTCPackage.setSelected(false);
						rbSTCUnit.setSelected(true);
						rbSTCPackage.setEnabled(false);
						
					}
				
					}
				}
				catch(Exception e)
				{
					System.out.println(e);
				}
			}
		});
		
		rbSTCPackage=new JRadioButton("Package");
		rbSTCUnit=new JRadioButton("Unit");
		ButtonGroup bgSTC=new ButtonGroup();
		bgSTC.add(rbSTCPackage);
		bgSTC.add(rbSTCUnit);
		
		btnCustomizerAddToCart=new JButton("Add");
		btnCustomizerAddToCart.addActionListener(this);
		
		lblClientName.setBounds(20,10,150,30);
		cbbSTCClientName.setBounds(180,10,250,30);
		lblCity.setBounds(450,10,50,30);
		cbbSTCCity.setBounds(550,10,200,30);
		
		lblCategory.setBounds(20,75,100,30);
		cbbSTCCategory.setBounds(130,75,250,30);
		lblSelectItem.setBounds(390,75,100,30);
		cbbSTCItemName.setBounds(500,75,200,30);
		lblCompany.setBounds(710,75,100,30);
		cbbSTCCompany.setBounds(820,75,250,30);
		
		lblQuantity.setBounds(20,115,100,30);
		txtSTCQuantity.setBounds(130,115,100,30);
		rbSTCPackage.setBounds(240,115,80,30);
		rbSTCUnit.setBounds(330,115,50,30);
		
		btnCustomizerAddToCart.setBounds(1000,115,70,30);
		
		panelCustomerInfo.add(lblClientName);
		panelCustomerInfo.add(cbbSTCClientName);
		panelCustomerInfo.add(lblCity);
		panelCustomerInfo.add(cbbSTCCity);
		
		panelSellCustoized.add(lblSelectItem);
		panelSellCustoized.add(cbbSTCItemName);
		panelSellCustoized.add(lblCategory);
		panelSellCustoized.add(cbbSTCCategory);
		panelSellCustoized.add(lblCompany);
		panelSellCustoized.add(cbbSTCCompany);
		panelSellCustoized.add(lblQuantity);
		panelSellCustoized.add(txtSTCQuantity);
		panelSellCustoized.add(rbSTCPackage);
		panelSellCustoized.add(rbSTCUnit);
		panelSellCustoized.add(btnCustomizerAddToCart);
		
		btnCancelCOrder=new JButton("Cancel Order");
		btnCancelCOrder.addActionListener(this);
		
		btnSubmitCOrder=new JButton("Submit Order");
		btnSubmitCOrder.addActionListener(this);
		btnCancelCOrder.setBounds(780,265,150,30);
		btnSubmitCOrder.setBounds(940,265,150,30);
		
		panelCCart.add(btnCancelCOrder);
		panelCCart.add(btnSubmitCOrder);
		
		hf.add(panelSellCustoized);
	}
	
	public void sellToRetailer()
	{
		panelSellRetailer=new JPanel();
		panelSellRetailer.setVisible(false);
		panelSellRetailer.setLayout(null);
		panelSellRetailer.setBounds(100,120,1150,520);
		panelSellRetailer.setBorder(BorderFactory.createTitledBorder("Sale To Retailer Here"));
		
		panelRCart=new JPanel();
		panelRCart.setVisible(true);
		panelRCart.setLayout(null);
		panelRCart.setBounds(20,200,1100,300);
		panelRCart.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		panelSellRetailer.add(panelRCart);
		
		JPanel panelCustomerInfo=new JPanel();
		panelCustomerInfo.setVisible(true);
		panelCustomerInfo.setLayout(null);
		panelCustomerInfo.setBounds(10,20,1130,100);
		panelCustomerInfo.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		panelSellRetailer.add(panelCustomerInfo);
		
		btnCancelOrder=new JButton("Cancel Order");
		btnSubmitOrder=new JButton("Submit Order");
		
		btnCancelOrder.addActionListener(this);
		btnSubmitOrder.addActionListener(this);
		
		btnCancelOrder.setBounds(780,265,150,30);
		btnSubmitOrder.setBounds(940,265,150,30);
		
		JLabel lblCutomerName,lblCity,lblAge,lblContactNo,lblSelectItem,lblCategory,lblCompany,lblQuantity;
			
		lblCutomerName=new JLabel("Customer Name");
		lblCutomerName.setFont(f);
		txtSTRCustomerName=new JTextField(20);
		txtSTRCustomerName.setFont(f);
		txtSTRCustomerName.addKeyListener(new KeyAdapter(){
			public void keyTyped(KeyEvent ke)
			{
				char c=ke.getKeyChar();
				if(Character.isDigit(c))
				{
					ke.consume();
				}
			}
		});
		
		lblCity=new JLabel("City");
		lblCity.setFont(f);
		txtSTRCity=new JTextField(20);
		txtSTRCity.setFont(f);
		txtSTRCity.addKeyListener(new KeyAdapter(){
			public void keyTyped(KeyEvent ke)
			{
				char c=ke.getKeyChar();
				if(Character.isDigit(c))
				{
					ke.consume();
				}
			}
		});
		
		lblAge=new JLabel("Age");
		lblAge.setFont(f);
		txtSTRAge=new JTextField(30);
		txtSTRAge.setFont(f);
		txtSTRAge.addKeyListener(new KeyAdapter(){
			public void keyTyped(KeyEvent ke)
			{
				char c=ke.getKeyChar();
				if(!Character.isDigit(c))
				{
					ke.consume();
				}
				
				if(!(txtSTRAge.getText().length()<=1))
				{
					ke.consume();
				}
			}
		});
		
		lblContactNo=new JLabel("Contact No");
		lblContactNo.setFont(f);
		txtSTRContactNo=new JTextField(10);
		txtSTRContactNo.setFont(f);
		txtSTRContactNo.addKeyListener(new KeyAdapter(){
			public void keyTyped(KeyEvent ke)
			{
				char c=ke.getKeyChar();
				if(!Character.isDigit(c))
				{
					ke.consume();
				}
				if(!(txtSTRContactNo.getText().length()<=9))
				{
					ke.consume();
				}
			}
		});
		
		lblSelectItem=new JLabel("Select Item");
		lblSelectItem.setFont(f);
		cbbSTRItemName=new JComboBox();
		cbbSTRItemName.setFont(f);
		
		lblCategory=new JLabel("Category");
		lblCategory.setFont(f);
		lblCategory.setHorizontalAlignment(JLabel.RIGHT);
		cbbSTRCategory=new JComboBox();
		cbbSTRCategory.setFont(f);
		
		lblCompany=new JLabel("Company");
		lblCompany.setFont(f);
		lblCompany.setHorizontalAlignment(JLabel.RIGHT);
		cbbSTRCompany=new JComboBox();
		cbbSTRCompany.setFont(f);
		
		lblQuantity=new JLabel("Quantity");
		lblQuantity.setFont(f);
		lblQuantity.setHorizontalAlignment(JLabel.CENTER);
		txtSTRQuantity=new JTextField(10);
		txtSTRQuantity.setFont(f);
		txtSTRQuantity.addKeyListener(new KeyAdapter(){
			public void keyTyped(KeyEvent ke)
			{
				char c=ke.getKeyChar();
				if(!Character.isDigit(c))
				{
					ke.consume();
				}
			}
		});
		txtSTRQuantity.addFocusListener(new FocusAdapter(){
			public void focusGained(FocusEvent fe)
			{
				try
				{
					PreparedStatement pst=con.prepareStatement("Select QUANTITY_IN_PACKAGE from STOCK where ITEM_NAME=? and CATEGORY=? and COMPANY=?");
					pst.setString(1,(String)cbbSTRItemName.getSelectedItem());
					pst.setString(2,(String)cbbSTRCategory.getSelectedItem());
					pst.setString(3,(String)cbbSTRCompany.getSelectedItem());
					ResultSet rs=pst.executeQuery();
					while(rs.next())
					{
					if(rs.getInt("QUANTITY_IN_PACKAGE")==0)
					{
						rbSTRPackage.setSelected(false);
						rbSTRUnit.setSelected(true);
						rbSTRPackage.setEnabled(false);
						
					}
				
					}
				}
				catch(Exception e)
				{
					System.out.println(e);
				}
			}
		});
		
		rbSTRPackage=new JRadioButton("Package");
		rbSTRUnit=new JRadioButton("Unit");
		ButtonGroup bgSTR=new ButtonGroup();
		bgSTR.add(rbSTRPackage);
		bgSTR.add(rbSTRUnit);
		
		cbbSTRCategory.addItemListener(new ItemListener(){
			public void itemStateChanged(ItemEvent ie)
			{
				cbbSTRItemName.removeAllItems();
				try
				{
					PreparedStatement pst=con.prepareStatement("Select ITEM_NAME from STOCK where CATEGORY=?");
					pst.setString(1,(String)cbbSTRCategory.getSelectedItem());
					ResultSet rs=pst.executeQuery();
					
					while(rs.next())
					{
						cbbSTRItemName.addItem(rs.getString("ITEM_NAME"));
					}
					pst.close();
					
				}
				catch(Exception e)
				{
					JOptionPane.showMessageDialog(hf,"Sorry !!! There are some error occured...!");
				}
				
			}
		});
		
		
		
		btnRetailerAddToCart=new JButton("Add");
		btnRetailerAddToCart.addActionListener(this);
		
		lblCutomerName.setBounds(20,10,150,30);
		txtSTRCustomerName.setBounds(180,10,250,30);
		lblCity.setBounds(450,10,150,30);
		txtSTRCity.setBounds(610,10,200,30);
		lblAge.setBounds(20,45,150,30);
		txtSTRAge.setBounds(180,45,50,30);
		lblContactNo.setBounds(450,45,150,30);
		txtSTRContactNo.setBounds(610,45,200,30);
		
		
		lblCategory.setBounds(20,130,100,30);
		cbbSTRCategory.setBounds(130,130,250,30);
		lblSelectItem.setBounds(390,130,100,30);
		cbbSTRItemName.setBounds(500,130,200,30);
		lblCompany.setBounds(710,130,100,30);
		cbbSTRCompany.setBounds(820,130,250,30);
		
		lblQuantity.setBounds(20,165,100,30);
		txtSTRQuantity.setBounds(130,165,100,30);
		rbSTRPackage.setBounds(240,165,80,30);
		rbSTRUnit.setBounds(330,165,100,30);
		
		
		
		btnRetailerAddToCart.setBounds(1000,165,70,30);
		
		panelCustomerInfo.add(lblCutomerName);
		panelCustomerInfo.add(txtSTRCustomerName);
		panelCustomerInfo.add(lblCity);
		panelCustomerInfo.add(txtSTRCity);
		panelCustomerInfo.add(lblAge);
		panelCustomerInfo.add(txtSTRAge);
		panelCustomerInfo.add(lblContactNo);
		panelCustomerInfo.add(txtSTRContactNo);
		
		panelSellRetailer.add(lblSelectItem);
		panelSellRetailer.add(cbbSTRItemName);
		panelSellRetailer.add(lblCategory);
		panelSellRetailer.add(cbbSTRCategory);
		panelSellRetailer.add(lblCompany);
		panelSellRetailer.add(cbbSTRCompany);
		panelSellRetailer.add(lblQuantity);
		panelSellRetailer.add(txtSTRQuantity);
		panelSellRetailer.add(rbSTRPackage);
		panelSellRetailer.add(rbSTRUnit);
		panelSellRetailer.add(btnRetailerAddToCart);
		
		panelRCart.add(btnCancelOrder);
		panelRCart.add(btnSubmitOrder);
		
		modelRBill=new DefaultTableModel();
		tableRBill=new JTable(modelRBill);
		spRBill=new JScrollPane(tableRBill);
		tableRBill.setRowHeight(24);
		
		modelRBill.addColumn("ITEM NAME");;
		modelRBill.addColumn("CATEGORY");
		modelRBill.addColumn("COMPANY");
		modelRBill.addColumn("QUANTITY");
		modelRBill.addColumn("QUANTITY TYPE");
		modelRBill.addColumn("PRICE");
		modelRBill.addColumn("TOTAL AMOUNT");
		modelRBill.addColumn("LOCATION");
	
		modelRBill.addColumn("DELETE");
		
		tableRBill.getColumn("DELETE").setCellRenderer(new ButtonRenderer());
		tableRBill.getColumn("DELETE").setCellEditor(new ButtonEditor(new JCheckBox()));
		
		
		tableRBill.setPreferredScrollableViewportSize(tableRBill.getPreferredSize());
	
		spRBill.setBounds(10,10,1080,250);
		panelRCart.add(spRBill);
		
		lblRCGrandTotal=new JLabel("Grand Total : ");
		lblRCGrandTotal.setFont(new Font("Bold",Font.BOLD,18));
		lblRCGrandTotal.setForeground(Color.RED);
		
		lblRCAmount=new JLabel();
		lblRCAmount.setFont(new Font("Bold",Font.BOLD,18));
		lblRCAmount.setForeground(Color.BLUE);
		
		lblRCGrandTotal.setBounds(10,260,150,50);
		lblRCAmount.setBounds(160,260,100,50);
		
		panelRCart.add(lblRCGrandTotal);
		panelRCart.add(lblRCAmount);
		
		cbbSTRItemName.addItemListener(new ItemListener(){
			public void itemStateChanged(ItemEvent ie)
			{
				cbbSTRCompany.removeAllItems();
				try
				{
					PreparedStatement pst=con.prepareStatement("Select COMPANY,QUANTITY_IN_PACKAGE from STOCK where ITEM_NAME=? and CATEGORY=?");
					pst.setString(1,(String)cbbSTRItemName.getSelectedItem());
					pst.setString(2,(String)cbbSTRCategory.getSelectedItem());
					ResultSet rs=pst.executeQuery();
					
					while(rs.next())
					{
						cbbSTRCompany.addItem(rs.getString("COMPANY"));
						rbSTRPackage.setEnabled(true);						
					}
					pst.close();
					
				}
				catch(Exception e)
				{
					JOptionPane.showMessageDialog(hf,"Sorry !!! There are some error occured...!");
				}
				
							
			}
		});
		
		hf.add(panelSellRetailer);
	}
	
	public void addCategory()
	{
		panelAddCategory=new JPanel();
		panelAddCategory.setVisible(false);
		panelAddCategory.setLayout(null);
		panelAddCategory.setBounds(100,150,1150,500);		
		panelAddCategory.setBorder(BorderFactory.createTitledBorder("Add Category Here"));
		
		JLabel lblCategory;
		
		lblCategory=new JLabel("Enter Category : ");
		lblCategory.setFont(f);
		txtACCategory=new JTextField(20);
		txtACCategory.setFont(f);
		txtACCategory.addKeyListener(new KeyAdapter(){
			public void keyTyped(KeyEvent ke)
			{
				char c=ke.getKeyChar();
				if(!Character.isLetter(c))
				{
					ke.consume();
				}
			}
		});
		
		btnAddToCategory=new JButton("Add to Category");
		btnAddCategoryCancel=new JButton("Cancel");
		
		
		btnAddToCategory.addActionListener(this);
		btnAddCategoryCancel.addActionListener(this);
		
		lblCategory.setBounds(10,20,200,30);
		lblCategory.setHorizontalAlignment(JLabel.CENTER);
		txtACCategory.setBounds(220,20,300,30);
		btnAddCategoryCancel.setBounds(220,60,100,30);
		btnAddToCategory.setBounds(370,60,150,30);
		
		panelAddCategory.add(lblCategory);
		panelAddCategory.add(txtACCategory);
		panelAddCategory.add(btnAddCategoryCancel);
		panelAddCategory.add(btnAddToCategory);
		
		panelAddCategory.add(spViewCategory);
		hf.add(panelAddCategory);
		
	}
		
	public void removeCategory()
	{		
		panelRemoveCategory=new JPanel();
		panelRemoveCategory.setVisible(false);
		panelRemoveCategory.setLayout(null);
		panelRemoveCategory.setBounds(100,150,1150,500);		
		panelRemoveCategory.setBorder(BorderFactory.createTitledBorder("Remove Category Here"));
		
		JLabel lblSelectCategory;
		lblSelectCategory=new JLabel("Select Category : ");
		lblSelectCategory.setHorizontalAlignment(JLabel.CENTER);
		lblSelectCategory.setFont(f);
		
		cbbRCCategory=new JComboBox();
		cbbRCCategory.setFont(f);
		
		
		btnRemoveCategoryCancel=new JButton("Cancel");
		btnRemoveCategoryCancel.addActionListener(this);
		btnRemoveCategory=new JButton("Remove Category");
		btnRemoveCategory.addActionListener(this);
		
		lblSelectCategory.setBounds(20,20,200,30);
		cbbRCCategory.setBounds(220,20,300,30);
		btnRemoveCategoryCancel.setBounds(220,60,100,30);
		btnRemoveCategory.setBounds(370,60,150,30);
		
		panelRemoveCategory.add(lblSelectCategory);
		panelRemoveCategory.add(cbbRCCategory);
		panelRemoveCategory.add(btnRemoveCategoryCancel);
		panelRemoveCategory.add(btnRemoveCategory);
				
		hf.add(panelRemoveCategory);
		
	}
	
	public void updateCategory()
	{
		
		panelUpdateCategory=new JPanel();
		panelUpdateCategory.setVisible(false);
		panelUpdateCategory.setLayout(null);
		panelUpdateCategory.setBounds(100,150,1150,500);		
		panelUpdateCategory.setBorder(BorderFactory.createTitledBorder("Update Category Here"));
		
		JLabel lblSelectCategory;
		lblSelectCategory=new JLabel("Select Category : ");
		lblSelectCategory.setHorizontalAlignment(JLabel.CENTER);
		lblSelectCategory.setFont(f);
		
		cbbUCCategory=new JComboBox();
		cbbUCCategory.setFont(f);
		cbbUCCategory.addItemListener(new ItemListener(){
															public void itemStateChanged(ItemEvent e)
															{
																txtUCCategory.setText((String)cbbUCCategory.getSelectedItem());
															}
			
		});
		
		txtUCCategory=new JTextField(20);
		txtUCCategory.setFont(f);
		txtUCCategory.setText((String)cbbUCCategory.getSelectedItem());
		txtUCCategory.addKeyListener(new KeyAdapter(){
			public void keyTyped(KeyEvent ke)
			{
				char c=ke.getKeyChar();
				if(!Character.isLetter(c))
				{
					ke.consume();
				}
			}
		});
		
		btnUpdateCategoryCancel=new JButton("Cancel");
		btnUpdateCategoryCancel.addActionListener(this);
		
		btnUpdateCategory=new JButton("Update Changes");
		btnUpdateCategory.addActionListener(this);
		
		lblSelectCategory.setBounds(20,20,200,30);
		cbbUCCategory.setBounds(220,20,300,30);
		txtUCCategory.setBounds(540,20,300,30);
		btnUpdateCategoryCancel.setBounds(220,60,100,30);
		btnUpdateCategory.setBounds(370,60,150,30);
		
		panelUpdateCategory.add(lblSelectCategory);
		panelUpdateCategory.add(cbbUCCategory);
		panelUpdateCategory.add(txtUCCategory);
		panelUpdateCategory.add(btnUpdateCategoryCancel);
		panelUpdateCategory.add(btnUpdateCategory);
		
		hf.add(panelUpdateCategory);
	}
	
	public void viewCategory()
	{
		panelViewCategory=new JPanel();
		panelViewCategory.setVisible(false);
		panelViewCategory.setLayout(null);
		panelViewCategory.setBounds(100,150,1150,500);		
		panelViewCategory.setBorder(BorderFactory.createTitledBorder("Availabe Category"));
		
		txtCategorySearch=new JTextField(20);
		txtCategorySearch.setFont(f);
		txtCategorySearch.addKeyListener(new KeyAdapter(){
			public void keyReleased(KeyEvent ke)
			{
				modelCategory.setRowCount(0);
			
				try
				{
					PreparedStatement pst=con.prepareStatement("Select * from CATEGORY where CATEGORY_NAME like '"+txtCategorySearch.getText()+"%' ");
					ResultSet rs=pst.executeQuery();
					int i=1;
					
					while(rs.next())
					{
						modelCategory.addRow(new Object[]{i,rs.getString("CATEGORY_NAME")});
						i++;
					}
					pst.close();
				}
				catch(Exception e)
				{
					JOptionPane.showMessageDialog(hf,"Sorry !!! There are some error occured...!");
				}
			}
		});
		
		lblCatagorySearch=new JLabel("Search Here :");
		lblCatagorySearch.setFont(f);
		
		txtCategorySearch.setBounds(400,30,700,30);
		lblCatagorySearch.setBounds(250,30,120,30);
		
		panelViewCategory.add(txtCategorySearch);
		panelViewCategory.add(lblCatagorySearch);
		
		btnCategoryPrint=new JButton("Print");
		btnCategoryPrint.addActionListener(this);
		
		btnCategoryPrint.setBounds(500,460,100,30);
		
		panelViewCategory.add(btnCategoryPrint);
		
		viewCategoryDB();
		
		hf.add(panelViewCategory);
	}
	
	public void viewCategoryDB()
	{
		modelCategory=new DefaultTableModel();
		tableCategory=new JTable(modelCategory);
		spViewCategory=new JScrollPane(tableCategory);
		tableCategory.setRowHeight(24);
		modelCategory.addColumn("INDEX");
		modelCategory.addColumn("NAME");
		tableCategory.setEnabled(false);
		
		try
		{
			
			PreparedStatement pst=con.prepareStatement("Select * from CATEGORY");
			ResultSet rs=pst.executeQuery();
			int i=1;
			while(rs.next())
			{
				String c=rs.getString(1);
				modelCategory.addRow(new Object[]{i,c});
				i++;
			}
			
			pst.close();
			
		
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(hf,"Sorry !!! There are some error occured...!");
		}
	
		
		spViewCategory.setBounds(50,100,1050,350);
		panelViewCategory.add(spViewCategory);
	}
	
	public void addItem()
	{
		panelAddItem=new JPanel();
		panelAddItem.setVisible(false);
		panelAddItem.setLayout(null);
		panelAddItem.setBounds(100,150,1150,500);		
		panelAddItem.setBorder(BorderFactory.createTitledBorder("Add Item Here"));
		
		JLabel lblSrNo,lblItemName,lblCompanyName,lblCategory,lblItemDetail,lblItemPrice,lblMFDate,lblExpireDate,lblUnitInPackage;
		
		lblSrNo=new JLabel("Batch No");
		lblSrNo.setFont(f);
		txtAISrNo=new JTextField(5);
		txtAISrNo.setFont(f);
		txtAISrNo.addKeyListener(new KeyAdapter(){
			public void keyTyped(KeyEvent ke)
			{
				char c=ke.getKeyChar();
				if(!Character.isDigit(c))
				{
					ke.consume();
				}
			}
		});
		
		lblItemName=new JLabel("Item Name");
		lblItemName.setFont(f);
		txtAIItemName=new JTextField(20);
		txtAIItemName.setFont(f);
		          
		 
		lblCategory=new JLabel("Category");
		lblCategory.setFont(f);
		cbbAICategory=new JComboBox();
		cbbAICategory.setFont(f);
		
		lblCompanyName=new JLabel("Company Name");
		lblCompanyName.setFont(f);
		cbbAICompany=new JComboBox();
		cbbAICompany.setFont(f);
		
		lblItemDetail=new JLabel("Detail");
		lblItemDetail.setFont(f);
		txtAIDetail=new JTextArea();
		txtAIDetail.setLineWrap(true);
		txtAIDetail.setFont(f);
		
	  	lblItemPrice=new JLabel("Item Price");
		lblItemPrice.setFont(f);
		txtAIPrice=new JTextField(5);
		txtAIPrice.setFont(f);
			
		txtAIPrice.addKeyListener(new KeyAdapter(){
			public void keyTyped(KeyEvent ke)
			{
				char c=ke.getKeyChar();
				if(!Character.isDigit(c))
				{
					ke.consume();
				}
			}
		});
		
		rbAIPerPackage=new JRadioButton("Per Package");
		rbAIPerUnit=new JRadioButton("Per Unit");
		ButtonGroup bg=new ButtonGroup();
		bg.add(rbAIPerPackage);
		bg.add(rbAIPerUnit);
		
		
		lblUnitInPackage=new JLabel("Unit In Package");
		lblUnitInPackage.setFont(f);
		txtAIUnitInPackage=new JTextField(5);
		txtAIUnitInPackage.setFont(f);
		txtAIUnitInPackage.addKeyListener(new KeyAdapter(){
			public void keyTyped(KeyEvent ke)
			{
				char c=ke.getKeyChar();
				if(!Character.isDigit(c))
				{
					ke.consume();
				}
			}
		});
		
		lblMFDate=new JLabel("Mnf.Date");
		lblMFDate.setFont(f);
		modelAIMnfDate=new UtilDateModel();
		pAIMnfDate=new Properties();
		pAIMnfDate.put("text.today","Today");
		pAIMnfDate.put("text.month","Month");
		pAIMnfDate.put("text.year","Year");		
		dpAIMnfDate=new JDatePanelImpl(modelAIMnfDate,pAIMnfDate);
		AIMnfDate=new JDatePickerImpl(dpAIMnfDate,new DateComponentFormatter());
				
		lblExpireDate=new JLabel("Expire Date");
		lblExpireDate.setFont(f);
		modelAIExpDate=new UtilDateModel();
		pAIExpDate=new Properties();
		pAIExpDate.put("text.today","Today");
		pAIExpDate.put("text.month","Month");
		pAIExpDate.put("text.year","Year");
		dpAIExpDate=new JDatePanelImpl(modelAIExpDate,pAIExpDate);
		AIExpDate=new JDatePickerImpl(dpAIExpDate,new DateComponentFormatter());
				
		btnAddItemCancel=new JButton("Cancel");
		btnAddItemCancel.addActionListener(this);
		btnAddItem=new JButton("Add Item");
		btnAddItem.addActionListener(this);
		
		lblSrNo.setBounds(150,50,150,30);
		txtAISrNo.setBounds(360,50,200,30);
		lblItemName.setBounds(650,50,150,30);
		txtAIItemName.setBounds(810,50,200,30);
		lblCompanyName.setBounds(150,100,150,30);
		cbbAICompany.setBounds(360,100,200,30);
		lblCategory.setBounds(650,100,150,30);
		cbbAICategory.setBounds(810,100,200,30);
		lblItemDetail.setBounds(150,150,150,30);
		txtAIDetail.setBounds(360,150,200,90);
		lblItemPrice.setBounds(650,150,150,30);
		txtAIPrice.setBounds(810,150,200,30);
		lblUnitInPackage.setBounds(150,280,150,30);
		txtAIUnitInPackage.setBounds(360,280,200,30);
		rbAIPerPackage.setBounds(810,180,100,30);
		rbAIPerUnit.setBounds(920,180,150,30);
		lblMFDate.setBounds(650,220,150,30);
		AIMnfDate.setBounds(810,220,200,30);
		lblExpireDate.setBounds(650,280,150,30);
		AIExpDate.setBounds(810,280,200,30);
		
		btnAddItemCancel.setBounds(30,450,100,30);
		btnAddItem.setBounds(920,450,150,30);
		
		panelAddItem.add(lblSrNo);
		panelAddItem.add(txtAISrNo);
		panelAddItem.add(lblItemName);
		panelAddItem.add(txtAIItemName);
		panelAddItem.add(lblCategory);
		panelAddItem.add(cbbAICategory);
		panelAddItem.add(lblCompanyName);
		panelAddItem.add(cbbAICompany);
		panelAddItem.add(lblItemDetail);
		panelAddItem.add(txtAIDetail);
		panelAddItem.add(lblItemPrice);
		panelAddItem.add(txtAIPrice);
		panelAddItem.add(rbAIPerPackage);
		panelAddItem.add(rbAIPerUnit);
		panelAddItem.add(lblUnitInPackage);
		panelAddItem.add(txtAIUnitInPackage);
		panelAddItem.add(lblMFDate);
		panelAddItem.add(AIMnfDate);
		panelAddItem.add(lblExpireDate);
		panelAddItem.add(AIExpDate);
		
		panelAddItem.add(btnAddItemCancel);
		panelAddItem.add(btnAddItem);
		
		hf.add(panelAddItem);
	}
	
	public void removeItem()
	{
		panelRemoveItem=new JPanel();
		panelRemoveItem.setVisible(false);
		panelRemoveItem.setLayout(null);
		panelRemoveItem.setBounds(100,150,1150,500);		
		panelRemoveItem.setBorder(BorderFactory.createTitledBorder("Remove Item Here"));
		
		JLabel lblSelectItem,lblReason,lblSelectCategory,lblSelectCompany;
		
		lblSelectCategory=new JLabel("Select Category");
		lblSelectCategory.setFont(f);
		cbbRICategory=new JComboBox();
		cbbRICategory.setFont(f);
		
		lblSelectCompany=new JLabel("Select Company");
		lblSelectCompany.setFont(f);
		cbbRICompany=new JComboBox();
		cbbRICompany.setFont(f);
				
		lblSelectItem=new JLabel("Select Item");
		lblSelectItem.setFont(f);
		cbbRIItemName=new JComboBox();
		cbbRIItemName.setFont(f);
		
		lblReason=new JLabel("Note ");
		lblReason.setFont(f);
		txtRIReason=new JTextArea();
		txtRIReason.setLineWrap(true);
		txtRIReason.setFont(new Font("Arial",Font.PLAIN,18));
		
		btnRemoveItemCancel=new JButton("Cancel");
		btnRemoveItem=new JButton("Remove Item");
		
		btnRemoveItemCancel.addActionListener(this);
		btnRemoveItem.addActionListener(this);
		
		cbbRICategory.addItemListener(new ItemListener(){
			public void itemStateChanged(ItemEvent ie)
			{
				cbbRIItemName.removeAllItems();
				try
				{
					PreparedStatement pst=con.prepareStatement("Select ITEM_NAME from ITEM where CATEGORY_NAME=?");
					pst.setString(1,(String)cbbRICategory.getSelectedItem());
					ResultSet rs=pst.executeQuery();
					
					while(rs.next())
					{
						cbbRIItemName.addItem(rs.getString("ITEM_NAME"));
					}
					pst.close();
					
				}
				catch(Exception e)
				{
					JOptionPane.showMessageDialog(hf,"Sorry !!! There are some error occured...!");
				}
				
			}
		});
		
		cbbRIItemName.addItemListener(new ItemListener(){
			public void itemStateChanged(ItemEvent ie)
			{
				cbbRICompany.removeAllItems();
				try
				{
					PreparedStatement pst=con.prepareStatement("Select COMPANY_NAME from ITEM where ITEM_NAME=? and CATEGORY_NAME=?");
					pst.setString(1,(String)cbbRIItemName.getSelectedItem());
					pst.setString(2,(String)cbbRICategory.getSelectedItem());
					ResultSet rs=pst.executeQuery();
					
					while(rs.next())
					{
						cbbRICompany.addItem(rs.getString("COMPANY_NAME"));
					}
					pst.close();
					
				}
				catch(Exception e)
				{
					JOptionPane.showMessageDialog(hf,"Sorry !!! There are some error occured...!");
				}
				
			}
		});
		
		lblSelectCategory.setBounds(150,50,150,30);
		cbbRICategory.setBounds(360,50,200,30);
		lblSelectItem.setBounds(650,50,150,30);
		cbbRIItemName.setBounds(810,50,200,30);
		lblSelectCompany.setBounds(150,100,150,30);
		cbbRICompany.setBounds(360,100,200,30);
		lblReason.setBounds(650,100,150,30);
		txtRIReason.setBounds(810,100,200,90);
		btnRemoveItemCancel.setBounds(400,200,100,30);
		btnRemoveItem.setBounds(510,200,150,30);
		
		panelRemoveItem.add(lblSelectCategory);
		panelRemoveItem.add(cbbRICategory);
		panelRemoveItem.add(lblSelectCompany);
		panelRemoveItem.add(cbbRICompany);
		panelRemoveItem.add(lblSelectItem);
		panelRemoveItem.add(cbbRIItemName);
		panelRemoveItem.add(lblReason);
		panelRemoveItem.add(txtRIReason);
		panelRemoveItem.add(btnRemoveItemCancel);
		panelRemoveItem.add(btnRemoveItem);
		
		hf.add(panelRemoveItem);
	}
	
	public void updateItem()
	{
		
		panelUpdateItem=new JPanel();
		panelUpdateItem.setVisible(false);
		panelUpdateItem.setLayout(null);
		panelUpdateItem.setBounds(100,150,1150,500);		
		panelUpdateItem.setBorder(BorderFactory.createTitledBorder("Update Item Here"));
		
		JLabel lblSelectItem,lblSelectCategory,lblSelectCompany,lblSrNo,lblItemName,lblCompanyName,lblCategory,lblItemDetail,lblItemPrice,lblMFDate,lblExpireDate,lblUnitInPackage;
				
		lblSelectItem=new JLabel("Select Item");
		lblSelectItem.setFont(f);
		cbbUIItemName=new JComboBox();
		cbbUIItemName.setFont(f);
	
		lblSelectCategory=new JLabel("Category");
		lblSelectCategory.setFont(f);
		lblSelectCategory.setHorizontalAlignment(JLabel.CENTER);
		cbbUISCategory=new JComboBox();
		cbbUISCategory.setFont(f);
		
		lblSelectCompany=new JLabel("Company");
		lblSelectCompany.setFont(f);
		lblSelectCompany.setHorizontalAlignment(JLabel.CENTER);
		cbbUISCompany=new JComboBox();
		cbbUISCompany.setFont(f);
		
		btnUISelect=new JButton("Select");
		btnUISelect.addActionListener(this);
		
		lblSrNo=new JLabel("Serial No");
		lblSrNo.setFont(f);
		txtUISrNo=new JTextField(5);
		txtUISrNo.setFont(f);
		
		lblItemName=new JLabel("Item Name");
		lblItemName.setFont(f);
		txtUIItemName=new JTextField(20);
		txtUIItemName.setFont(f);
		
		lblCompanyName=new JLabel("Company Name");
		lblCompanyName.setFont(f);
		txtUICompany=new JTextField(20);
		txtUICompany.setFont(f);
		txtUICompany.setEnabled(false);
		cbbUICompanyName=new JComboBox();
		cbbUICompanyName.setFont(f);
		
		lblCategory=new JLabel("Category");
		lblCategory.setFont(f);
		txtUICategory=new JTextField(20);
		txtUICategory.setFont(f);
		txtUICategory.setEnabled(false);
		cbbUICategory=new JComboBox();
		cbbUICategory.setFont(f);
		
		lblItemDetail=new JLabel("Detail");
		lblItemDetail.setFont(f);
		txtUIDetail=new JTextField(20);
		txtUIDetail.setFont(f);
		
		lblItemPrice=new JLabel("Price");
		lblItemPrice.setFont(f);
		txtUIPrice=new JTextField(5);
		txtUIPrice.setFont(f);
		txtUIPrice.addKeyListener(new KeyAdapter(){
			public void keyTyped(KeyEvent ke)
			{
				char c=ke.getKeyChar();
				if(!Character.isDigit(c))
				{
					ke.consume();
				}
			}
		});
		
		rbUIPerPackage=new JRadioButton("Per Package");
		rbUIPerUnit=new JRadioButton("Per Unit");
		ButtonGroup  bgUI=new ButtonGroup();
		bgUI.add(rbUIPerPackage);
		bgUI.add(rbUIPerUnit);
		
		lblUnitInPackage=new JLabel("Unit In Package");
		lblUnitInPackage.setFont(f);
		txtUIUnitInPackage=new JTextField(5);
		txtUIUnitInPackage.setFont(f);
		txtUIUnitInPackage.addKeyListener(new KeyAdapter(){
			public void keyTyped(KeyEvent ke)
			{
				char c=ke.getKeyChar();
				if(!Character.isDigit(c))
				{
					ke.consume();
				}
			}
		});
		
		lblMFDate=new JLabel("Mnf.Date");
		lblMFDate.setFont(f);
		modelUIMnfDate=new UtilDateModel();
		pUIMnfDate=new Properties();
		pUIMnfDate.put("text.today","Today");
		pUIMnfDate.put("text.month","Month");
		pUIMnfDate.put("text.year","Year");		
		dpUIMnfDate=new JDatePanelImpl(modelUIMnfDate,pUIMnfDate);
		UIMnfDate=new JDatePickerImpl(dpUIMnfDate,new DateComponentFormatter());
		
		lblExpireDate=new JLabel("Expire Date");
		lblExpireDate.setFont(f);
		modelUIExpDate=new UtilDateModel();
		pUIExpDate=new Properties();
		pUIExpDate.put("text.today","Today");
		pUIExpDate.put("text.month","Month");
		pUIExpDate.put("text.year","Year");
		dpUIExpDate=new JDatePanelImpl(modelUIExpDate,pUIExpDate);
		UIExpDate=new JDatePickerImpl(dpUIExpDate,new DateComponentFormatter());
		
		btnUpdateItemCancel=new JButton("Cancel");
		btnUpdateItem=new JButton("Update Changes");
		btnUpdateItemCancel.addActionListener(this);
		btnUpdateItem.addActionListener(this);
		
		lblSelectCategory.setBounds(50,20,100,30);
		cbbUISCategory.setBounds(150,20,200,30);
		lblSelectItem.setBounds(360,20,100,30);
		cbbUIItemName.setBounds(470,20,200,30);
		lblSelectCompany.setBounds(680,20,100,30);
		cbbUISCompany.setBounds(790,20,200,30);
		btnUISelect.setBounds(1000,20,90,30);
		
		lblSrNo.setBounds(150,70,150,30);
		txtUISrNo.setBounds(360,70,200,30);
		lblItemName.setBounds(650,70,150,30);
		txtUIItemName.setBounds(810,70,200,30);
		lblCompanyName.setBounds(150,120,150,30);
		txtUICompany.setBounds(360,120,200,30);
		cbbUICompanyName.setBounds(360,160,200,30);
		lblCategory.setBounds(650,120,150,30);
		txtUICategory.setBounds(810,120,200,30);
		cbbUICategory.setBounds(810,160,200,30);
		lblItemDetail.setBounds(150,210,150,30);
		txtUIDetail.setBounds(360,210,200,30);
		lblItemPrice.setBounds(650,210,150,30);
		txtUIPrice.setBounds(810,210,200,30);
		rbUIPerPackage.setBounds(810,240,100,30);
		rbUIPerUnit.setBounds(920,240,100,30);
		lblUnitInPackage.setBounds(150,270,150,30);
		txtUIUnitInPackage.setBounds(360,270,200,30);
		lblMFDate.setBounds(150,330,150,30);
		UIMnfDate.setBounds(360,330,200,30);
		lblExpireDate.setBounds(650,330,150,30);
		UIExpDate.setBounds(810,330,200,30);
		
		
		btnUpdateItemCancel.setBounds(30,450,100,30);
		btnUpdateItem.setBounds(920,450,150,30);
		
		panelUpdateItem.add(lblSelectItem);
		panelUpdateItem.add(cbbUIItemName);
		panelUpdateItem.add(lblSelectCategory);
		panelUpdateItem.add(cbbUISCategory);
		panelUpdateItem.add(lblSelectCompany);
		panelUpdateItem.add(cbbUISCompany);
		panelUpdateItem.add(btnUISelect);
		panelUpdateItem.add(lblSrNo);
		panelUpdateItem.add(txtUISrNo);
		panelUpdateItem.add(lblItemName);
		panelUpdateItem.add(txtUIItemName);
		panelUpdateItem.add(lblCompanyName);
		panelUpdateItem.add(txtUICompany);
		panelUpdateItem.add(cbbUICompanyName);
		panelUpdateItem.add(lblCategory);
		panelUpdateItem.add(txtUICategory);
		panelUpdateItem.add(cbbUICategory);
		panelUpdateItem.add(lblItemDetail);
		panelUpdateItem.add(txtUIDetail);
		panelUpdateItem.add(lblItemPrice);
		panelUpdateItem.add(txtUIPrice);
		panelUpdateItem.add(rbUIPerPackage);
		panelUpdateItem.add(rbUIPerUnit);
		panelUpdateItem.add(lblUnitInPackage);
		panelUpdateItem.add(txtUIUnitInPackage);
		panelUpdateItem.add(lblMFDate);
		panelUpdateItem.add(UIMnfDate);
		panelUpdateItem.add(lblExpireDate);
		panelUpdateItem.add(UIExpDate);
		
		panelUpdateItem.add(btnUpdateItemCancel);
		panelUpdateItem.add(btnUpdateItem);
		
		cbbUISCategory.addItemListener(new ItemListener(){
			public void itemStateChanged(ItemEvent ie)
			{
				cbbUIItemName.removeAllItems();
				try
				{
					PreparedStatement pst=con.prepareStatement("Select ITEM_NAME from ITEM where CATEGORY_NAME=?");
					pst.setString(1,(String)cbbUISCategory.getSelectedItem());
					ResultSet rs=pst.executeQuery();
					
					while(rs.next())
					{
						cbbUIItemName.addItem(rs.getString("ITEM_NAME"));
					}
					pst.close();
					
				}
				catch(Exception e)
				{
					JOptionPane.showMessageDialog(hf,"Sorry !!! There are some error occured...!");
				}
				
			}
		});
		
		cbbUIItemName.addItemListener(new ItemListener(){
			public void itemStateChanged(ItemEvent ie)
			{
				cbbUISCompany.removeAllItems();
				try
				{
					PreparedStatement pst=con.prepareStatement("Select COMPANY_NAME from ITEM where ITEM_NAME=? and CATEGORY_NAME=?");
					pst.setString(1,(String)cbbUIItemName.getSelectedItem());
					pst.setString(2,(String)cbbUISCategory.getSelectedItem());
					ResultSet rs=pst.executeQuery();
					
					while(rs.next())
					{
						cbbUISCompany.addItem(rs.getString("COMPANY_NAME"));
					}
					pst.close();
					
				}
				catch(Exception e)
				{
					JOptionPane.showMessageDialog(hf,"Sorry !!! There are some error occured...!");
				}
				
			}
		});
		
		hf.add(panelUpdateItem);
	}
	
	public void viewItem()
	{
		panelViewItem=new JPanel();
		panelViewItem.setVisible(false);
		panelViewItem.setLayout(null);
		panelViewItem.setBounds(100,150,1150,500);		
		panelViewItem.setBorder(BorderFactory.createTitledBorder("Availabe Item"));
		
		txtItemSearch=new JTextField(20);
		txtItemSearch.setFont(f);
		txtItemSearch.addKeyListener(new KeyAdapter(){
			public void keyReleased(KeyEvent ke)
			{
				modelItem.setRowCount(0);
			
				try
				{
					PreparedStatement pst=con.prepareStatement("Select * from ITEM where ITEM_NAME like '"+txtItemSearch.getText()+"%' or CATEGORY_NAME like '"+txtItemSearch.getText()+"%' or COMPANY_NAME like '"+txtItemSearch.getText()+"%' or SERIAL_NO like '"+txtItemSearch.getText()+"%'");
					ResultSet rs=pst.executeQuery();
					int i=1;
					
					while(rs.next())
					{
						modelItem.addRow(new Object[]{rs.getInt("SERIAL_NO"),rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getInt(5),rs.getInt(6),rs.getInt(7),rs.getDate(8),rs.getDate(9),});
						i++;
					}
					pst.close();
				}
				catch(Exception e)
				{
					JOptionPane.showMessageDialog(hf,"Sorry !!! There are some error occured...!");
				}
			}
		});
		
		lblItemSearch=new JLabel("Search Here : ");
		lblItemSearch.setFont(f);
		
		txtItemSearch.setBounds(400,30,700,30);
		lblItemSearch.setBounds(250,30,120,30);
		
		panelViewItem.add(txtItemSearch);
		panelViewItem.add(lblItemSearch);
		
		btnItemPrint=new JButton("Print");
		btnItemPrint.addActionListener(this);
		
		btnItemPrint.setBounds(500,460,100,30);
		
		panelViewItem.add(btnItemPrint);
		
		viewItemDB();
		hf.add(panelViewItem);
	}
	
	public void viewItemDB()
	{
		modelItem=new DefaultTableModel();
		tableItem=new JTable(modelItem);
		spViewItem=new JScrollPane(tableItem);
		tableItem.setRowHeight(24);
		tableItem.setEnabled(false);
		modelItem.addColumn("SERIAL NO");
		modelItem.addColumn("ITEM");
		modelItem.addColumn("COMPANY");
		modelItem.addColumn("CATEGORY");
		modelItem.addColumn("DESCRIPTION");
		modelItem.addColumn("UNIT PRICE");
		modelItem.addColumn("PACKAGE PRICE");
		modelItem.addColumn("UNIT IN PACKAGE");
		modelItem.addColumn("MFG. DATE");
		modelItem.addColumn("EXP. DATE");
		
		
		try
		{
			
			PreparedStatement pst=con.prepareStatement("Select * from ITEM");
			ResultSet rs=pst.executeQuery();
			
			while(rs.next())
			{
				modelItem.addRow(new Object[]{rs.getInt("SERIAL_NO"),rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getInt(5),rs.getInt(6),rs.getInt(7),rs.getDate(8),rs.getDate(9),});
			}
			
			pst.close();
			
		
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(hf,"Sorry !!! There are some error occured...!");
			
		}
		
		spViewItem.setBounds(50,100,1050,350);
		panelViewItem.add(spViewItem);
	}
	
	public void addStock()
	{	
		panelAddStock=new JPanel();
		panelAddStock.setVisible(false);
		panelAddStock.setLayout(null);
		panelAddStock.setBounds(100,150,1150,500);		
		panelAddStock.setBorder(BorderFactory.createTitledBorder("Add Stock Here"));

		JLabel lblItemName,lblCompanyName,lblCategory,lblQuantity,lblLocation;
		
		lblItemName=new JLabel("Item Name");
		lblItemName.setFont(f);
		cbbASItemName=new JComboBox();
		cbbASItemName.setFont(f);
		
		lblCompanyName=new JLabel("Company");
		lblCompanyName.setFont(f);
		cbbASCompanyName=new JComboBox();
		cbbASCompanyName.setFont(f);
		
		lblCategory=new JLabel("Category");
		lblCategory.setFont(f);
		cbbASCategory=new JComboBox();
		cbbASCategory.setFont(f);
			
		lblQuantity=new JLabel("Quantity");
		lblQuantity.setFont(f);
		txtASQuantity=new JTextField(5);
		txtASQuantity.setFont(f);
		txtASQuantity.addKeyListener(new KeyAdapter(){
			public void keyTyped(KeyEvent ke)
			{
				char c=ke.getKeyChar();
				if(!Character.isDigit(c))
				{
					ke.consume();
				}
			}
		});
		
		rbASUnit=new JRadioButton("UNIT");
		rbASPackage=new JRadioButton("PACKAGE");
		ButtonGroup bg=new ButtonGroup();
		bg.add(rbASUnit);
		bg.add(rbASPackage);
		
		lblLocation=new JLabel("Location");
		lblLocation.setFont(f);
		txtASLocation=new JTextField(5);
		txtASLocation.setFont(f);
		
		lblU=new JLabel();
		lblP=new JLabel();
		
		lblU.setFont(f);
		lblP.setFont(f);
		
		lblASUnitInPackage=new JLabel();
		lblASUnitInPackage.setFont(f);
		
		JLabel lblSellingPrice=new JLabel("Selling Price");
		lblSellingPrice.setFont(f);
		txtASSPrice=new JTextField();
		txtASSPrice.setFont(f);
		txtASSPrice.addKeyListener(new KeyAdapter(){
			public void keyTyped(KeyEvent ke)
			{
				char c=ke.getKeyChar();
				if(!Character.isDigit(c))
				{
					ke.consume();
				}
			}
		});
		
		btnASSelect=new JButton("Select");
		btnASSelect.addActionListener(this);
		
		
		
		rbASPPackage=new JRadioButton("Per Package");
		rbASPUnit=new JRadioButton("Per Unit");
		ButtonGroup bgp=new ButtonGroup();
		bgp.add(rbASPPackage);
		bgp.add(rbASPUnit);
		
		
		
		btnAddToStock=new JButton("Add To Stock");
		btnAddStockCancel=new JButton("Cancel");
		btnAddStockCancel.addActionListener(this);
		btnAddToStock.addActionListener(this);
		
		
		lblCategory.setBounds(50,50,100,30);
		cbbASCategory.setBounds(150,50,200,30);
		lblItemName.setBounds(360,50,100,30);
		cbbASItemName.setBounds(460,50,200,30);
		lblCompanyName.setBounds(670,50,100,30);
		cbbASCompanyName.setBounds(770,50,200,30);
		lblLocation.setBounds(50,150,100,30);
		txtASLocation.setBounds(150,150,200,30);
		lblQuantity.setBounds(450,150,100,30);
		txtASQuantity.setBounds(550,150,200,30);
		rbASUnit.setBounds(550,180,70,30);
		rbASPackage.setBounds(630,180,100,30);
		lblU.setBounds(50,250,300,30);
		lblP.setBounds(50,280,350,30);
		
		lblSellingPrice.setBounds(800,150,100,30);
		txtASSPrice.setBounds(950,150,100,30);
		rbASPPackage.setBounds(950,180,100,30);
		rbASPUnit.setBounds(950,210,100,30);
		btnASSelect.setBounds(980,50,100,30);
		
		lblASUnitInPackage.setBounds(50,320,250,30);
		
		btnAddStockCancel.setBounds(30,450,100,30);
						
		btnAddToStock.setBounds(920,450,150,30);
		
		panelAddStock.add(lblItemName);
		panelAddStock.add(cbbASItemName);
		panelAddStock.add(lblQuantity);
		panelAddStock.add(txtASQuantity);
		panelAddStock.add(lblCompanyName);
		panelAddStock.add(cbbASCompanyName);
		panelAddStock.add(lblLocation);
		panelAddStock.add(txtASLocation);
		panelAddStock.add(lblCategory);
		panelAddStock.add(cbbASCategory);
		panelAddStock.add(rbASUnit);
		panelAddStock.add(rbASPackage);
		panelAddStock.add(lblU);
		panelAddStock.add(lblP);
		panelAddStock.add(lblASUnitInPackage);
		panelAddStock.add(lblSellingPrice);
		panelAddStock.add(txtASSPrice);
		panelAddStock.add(rbASPPackage);
		panelAddStock.add(rbASPUnit);
		panelAddStock.add(btnASSelect);
		
		panelAddStock.add(btnAddStockCancel);
		panelAddStock.add(btnAddToStock);
		
		cbbASCategory.addItemListener(new ItemListener(){
			public void itemStateChanged(ItemEvent ie)
			{
				cbbASItemName.removeAllItems();
				try
				{
					PreparedStatement pst=con.prepareStatement("Select ITEM_NAME from ITEM where CATEGORY_NAME=?");
					pst.setString(1,(String)cbbASCategory.getSelectedItem());
					ResultSet rs=pst.executeQuery();
					
					while(rs.next())
					{
						cbbASItemName.addItem(rs.getString("ITEM_NAME"));
					}
					pst.close();
					
				}
				catch(Exception e)
				{
					JOptionPane.showMessageDialog(hf,"Sorry !!! There are some error occured...!");
				}
				
			}
		});
		
		cbbASItemName.addItemListener(new ItemListener(){
			public void itemStateChanged(ItemEvent ie)
			{
				cbbASCompanyName.removeAllItems();
				try
				{
					PreparedStatement pst=con.prepareStatement("Select COMPANY_NAME from ITEM where ITEM_NAME=? and CATEGORY_NAME=?");
					pst.setString(1,(String)cbbASItemName.getSelectedItem());
					pst.setString(2,(String)cbbASCategory.getSelectedItem());
					ResultSet rs=pst.executeQuery();
					
					while(rs.next())
					{
						cbbASCompanyName.addItem(rs.getString("COMPANY_NAME"));
					}
					pst.close();
					
				}
				catch(Exception e)
				{
					JOptionPane.showMessageDialog(hf,"Sorry !!! There are some error occured...!");
				}
				
			}
		});
		
		hf.add(panelAddStock);
	}
	
	public void removeStock()
	{		
		panelRemoveStock=new JPanel();
		panelRemoveStock.setVisible(false);
		panelRemoveStock.setLayout(null);
		panelRemoveStock.setBounds(100,150,1150,500);		
		panelRemoveStock.setBorder(BorderFactory.createTitledBorder("Remove Stock Here"));
		
		JLabel lblItemName,lblCompanyName,lblCategory,lblAll,lblRemoveStock;
		
		lblItemName=new JLabel("Item Name");
		lblItemName.setFont(f);
		cbbRSItemName=new JComboBox();
		cbbRSItemName.setFont(f);
		
		lblCompanyName=new JLabel("Company");
		lblCompanyName.setFont(f);
		cbbRSCompanyName=new JComboBox();
		cbbRSCompanyName.setFont(f);
		
		lblCategory=new JLabel("Category");
		lblCategory.setFont(f);
		cbbRSCategory=new JComboBox();
		cbbRSCategory.setFont(f);
		
		btnRemoveStockCancel=new JButton("Cancel");
		btnRemoveStock=new JButton("Remove Stock");
		
		lblCategory.setBounds(50,50,100,30);
		cbbRSCategory.setBounds(150,50,200,30);
		lblItemName.setBounds(410,50,100,30);
		cbbRSItemName.setBounds(510,50,200,30);
		lblCompanyName.setBounds(760,50,100,30);
		cbbRSCompanyName.setBounds(860,50,200,30);
		
		btnRemoveStockCancel.setBounds(400,150,100,30);
		btnRemoveStock.setBounds(510,150,150,30);
	
		btnRemoveStockCancel.addActionListener(this);
		btnRemoveStock.addActionListener(this);
		
		
		
		panelRemoveStock.add(lblCategory);
		panelRemoveStock.add(cbbRSCategory);
		panelRemoveStock.add(lblItemName);
		panelRemoveStock.add(cbbRSItemName);
		panelRemoveStock.add(lblCompanyName);
		panelRemoveStock.add(cbbRSCompanyName);
				
		panelRemoveStock.add(btnRemoveStockCancel);
		panelRemoveStock.add(btnRemoveStock);

		cbbRSCategory.addItemListener(new ItemListener(){
			public void itemStateChanged(ItemEvent ie)
			{
				cbbRSItemName.removeAllItems();
				try
				{
					PreparedStatement pst=con.prepareStatement("Select ITEM_NAME from STOCK where CATEGORY=?");
					pst.setString(1,(String)cbbRSCategory.getSelectedItem());
					ResultSet rs=pst.executeQuery();
					
					while(rs.next())
					{
						cbbRSItemName.addItem(rs.getString("ITEM_NAME"));
					}
					pst.close();
					
				}
				catch(Exception e)
				{
					JOptionPane.showMessageDialog(hf,"Sorry !!! There are some error occured...!");
				}
				
			}
		});
		
		cbbRSItemName.addItemListener(new ItemListener(){
			public void itemStateChanged(ItemEvent ie)
			{
				cbbRSCompanyName.removeAllItems();
				try
				{
					PreparedStatement pst=con.prepareStatement("Select COMPANY from STOCK where ITEM_NAME=? and CATEGORY=?");
					pst.setString(1,(String)cbbRSItemName.getSelectedItem());
					pst.setString(2,(String)cbbRSCategory.getSelectedItem());
					ResultSet rs=pst.executeQuery();
					
					while(rs.next())
					{
						cbbRSCompanyName.addItem(rs.getString("COMPANY"));
					}
					pst.close();
					
				}
				catch(Exception e)
				{
					JOptionPane.showMessageDialog(hf,"Sorry !!! There are some error occured...!");
				}
				
			}
		});
		
		hf.add(panelRemoveStock);
	}
	
	public void updateStock()
	{
		
		panelUpdateStock=new JPanel();
		panelUpdateStock.setVisible(false);
		panelUpdateStock.setLayout(null);
		panelUpdateStock.setBounds(100,150,1150,500);		
		panelUpdateStock.setBorder(BorderFactory.createTitledBorder("Update Stock Here"));
		
		JLabel lblItemName,lblCompanyName,lblCategory,lblLocation;
		
		lblItemName=new JLabel("Item Name");
		lblItemName.setFont(f);
		cbbUSItemName=new JComboBox();
		cbbUSItemName.setFont(f);
				
		lblCompanyName=new JLabel("Company");
		lblCompanyName.setFont(f);
		cbbUSCompany=new JComboBox();
		cbbUSCompany.setFont(f);
		
		lblCategory=new JLabel("Category");
		lblCategory.setFont(f);
		cbbUSCategory=new JComboBox();
		cbbUSCategory.setFont(f);
		
		btnUSSelect=new JButton("SELECT");
		btnUSSelect.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae)
			{
				fillUS();
			}
		});
		
		lblLocation=new JLabel("Location");
		lblLocation.setFont(f);
		txtUSLocation=new JTextField(5);
		txtUSLocation.setFont(f);
				
		JLabel lblCurrentStockInUnit=new JLabel("Current Stock in Unit");
		lblCurrentStockInUnit.setFont(f);
		txtUSCStockInUnit=new JTextField(10);
		txtUSCStockInUnit.setFont(f);
		txtUSCStockInUnit.setEnabled(false);
		
		JLabel lblNewStockUnit=new JLabel("Enter new Stock");
		lblNewStockUnit.setFont(f);
		txtUSNewStockInUnit=new JTextField(10);
		txtUSNewStockInUnit.setFont(f);
		
		JLabel lblCurrentStockInPackage=new JLabel("Current Stock in Package");
		lblCurrentStockInPackage.setFont(f);
		txtUSCStockInPackage=new JTextField(10);
		txtUSCStockInPackage.setFont(f);
		txtUSCStockInPackage.setEnabled(false);
		
		JLabel lblNewStockPackage=new JLabel("Enter new Stock");
		lblNewStockPackage.setFont(f);
		txtUSNewStockInPackage=new JTextField(10);
		txtUSNewStockInPackage.setFont(f);
		
		JLabel lblCUP=new JLabel("Current Selling Unit Price");
		lblCUP.setFont(f);
		txtUSCUnitPrice=new JTextField(10);
		txtUSCUnitPrice.setFont(f);
		txtUSCUnitPrice.setEnabled(false);
		
		JLabel lblNUP=new JLabel("Enter New Selling Price per Unit");
		lblNUP.setFont(f);
		txtUSNUnitPrice=new JTextField(10);
		txtUSNUnitPrice.setFont(f);
		
		JLabel lblCPP=new JLabel("Current Selling Package Price");
		lblCPP.setFont(f);
		txtUSCPackagePrice=new JTextField(10);
		txtUSCPackagePrice.setFont(f);
		txtUSCPackagePrice.setEnabled(false);
		
		JLabel lblNPP=new JLabel("Enter new Selling per Package");
		lblNPP.setFont(f);
		txtUSNPackagePrice=new JTextField(10);
		txtUSNPackagePrice.setFont(f);
		
		lblUnit=new JLabel();
		lblUnit.setFont(f);
		
		
		btnUpdateStockCancel=new JButton("Cancel");
		btnUpdateStock=new JButton("Update Stock");
		
		lblCategory.setBounds(50,50,100,30);
		cbbUSCategory.setBounds(150,50,200,30);
		lblItemName.setBounds(360,50,100,30);
		cbbUSItemName.setBounds(460,50,200,30);
		lblCompanyName.setBounds(670,50,100,30);
		cbbUSCompany.setBounds(770,50,200,30);
		btnUSSelect.setBounds(980,50,100,30);
		lblLocation.setBounds(250,100,150,30);
		txtUSLocation.setBounds(400,100,100,30);
		
		lblCurrentStockInUnit.setBounds(200,170,200,30);
		txtUSCStockInUnit.setBounds(410,170,100,30);
		lblNewStockUnit.setBounds(200,220,200,30);
		txtUSNewStockInUnit.setBounds(410,230,100,30);
		
		lblCurrentStockInPackage.setBounds(550,170,200,30);
		txtUSCStockInPackage.setBounds(760,170,100,30);
		lblNewStockPackage.setBounds(550,220,200,30);
		txtUSNewStockInPackage.setBounds(760,220,100,30);
		
		lblCUP.setBounds(100,300,300,30);
		txtUSCUnitPrice.setBounds(410,300,100,30);
		lblNUP.setBounds(100,350,300,30);
		txtUSNUnitPrice.setBounds(410,350,100,30);
		
		lblCPP.setBounds(550,300,300,30);
		txtUSCPackagePrice.setBounds(860,300,100,30);
		lblNPP.setBounds(550,350,300,30);
		txtUSNPackagePrice.setBounds(860,350,100,30);
		
		lblUnit.setBounds(550,100,200,30);
		
		btnUpdateStockCancel.setBounds(30,450,100,30);
		btnUpdateStockCancel.addActionListener(this);
				
		btnUpdateStock.setBounds(920,450,150,30);
		btnUpdateStock.addActionListener(this);
			
		panelUpdateStock.add(lblCategory);
		panelUpdateStock.add(cbbUSCategory);
		panelUpdateStock.add(lblItemName);
		panelUpdateStock.add(cbbUSItemName);
		panelUpdateStock.add(lblCompanyName);
		panelUpdateStock.add(cbbUSCompany);
		panelUpdateStock.add(lblLocation);
		panelUpdateStock.add(txtUSLocation);
		panelUpdateStock.add(btnUpdateStockCancel);
		panelUpdateStock.add(btnUpdateStock);
		panelUpdateStock.add(btnUSSelect);
		panelUpdateStock.add(lblCurrentStockInPackage);
		panelUpdateStock.add(txtUSCStockInPackage);
		panelUpdateStock.add(lblNewStockPackage);
		panelUpdateStock.add(txtUSNewStockInPackage);
		panelUpdateStock.add(lblCurrentStockInUnit);
		panelUpdateStock.add(txtUSCStockInUnit);
		panelUpdateStock.add(lblNewStockUnit);
		panelUpdateStock.add(txtUSNewStockInUnit);
		panelUpdateStock.add(lblCUP);
		panelUpdateStock.add(txtUSCUnitPrice);
		panelUpdateStock.add(lblNUP);
		panelUpdateStock.add(txtUSNUnitPrice);
		panelUpdateStock.add(lblCPP);
		panelUpdateStock.add(txtUSCPackagePrice);
		panelUpdateStock.add(lblNPP);
		panelUpdateStock.add(txtUSNPackagePrice);
		panelUpdateStock.add(lblUnit);
		
		cbbUSCategory.addItemListener(new ItemListener(){
			public void itemStateChanged(ItemEvent ie)
			{
				cbbUSItemName.removeAllItems();
				try
				{
					PreparedStatement pst=con.prepareStatement("Select ITEM_NAME from STOCK where CATEGORY=?");
					pst.setString(1,(String)cbbUSCategory.getSelectedItem());
					ResultSet rs=pst.executeQuery();
					
					while(rs.next())
					{
						cbbUSItemName.addItem(rs.getString("ITEM_NAME"));
					}
					pst.close();
					
				}
				catch(Exception e)
				{
					JOptionPane.showMessageDialog(hf,"Sorry !!! There are some error occured...!");
				}
				
			}
		});
		
		cbbUSItemName.addItemListener(new ItemListener(){
			public void itemStateChanged(ItemEvent ie)
			{
				cbbUSCompany.removeAllItems();
				try
				{
					PreparedStatement pst=con.prepareStatement("Select COMPANY from STOCK where ITEM_NAME=? and CATEGORY=?");
					pst.setString(1,(String)cbbUSItemName.getSelectedItem());
					pst.setString(2,(String)cbbUSCategory.getSelectedItem());
					ResultSet rs=pst.executeQuery();
					
					while(rs.next())
					{
						cbbUSCompany.addItem(rs.getString("COMPANY"));
					}
					pst.close();
					
				}
				catch(Exception e)
				{
					JOptionPane.showMessageDialog(hf,"Sorry !!! There are some error occured...!");
				}
				
			}
		});
			
		hf.add(panelUpdateStock);
	}
	
	public void viewStock()
	{
		panelViewStock=new JPanel();
		panelViewStock.setVisible(false);
		panelViewStock.setLayout(null);
		panelViewStock.setBounds(100,150,1150,500);		
		panelViewStock.setBorder(BorderFactory.createTitledBorder("Availabe Stock"));
		
		txtStockSearch=new JTextField(20);
		txtStockSearch.setFont(f);
		txtStockSearch.addKeyListener(new KeyAdapter(){
			public void keyReleased(KeyEvent ke)
			{
				modelStock.setRowCount(0);
			
				try
				{
					PreparedStatement pst=con.prepareStatement("Select * from STOCK where ITEM_NAME like '"+txtStockSearch.getText()+"%' or CATEGORY like '"+txtStockSearch.getText()+"%' or COMPANY like '"+txtStockSearch.getText()+"%' or LOCATION like '"+txtStockSearch.getText()+"%'");
					ResultSet rs=pst.executeQuery();
					int i=1;
					
					while(rs.next())
					{
						modelStock.addRow(new Object[]{rs.getString("ITEM_NAME"),rs.getString("CATEGORY"),rs.getString("COMPANY"),rs.getString("LOCATION"),rs.getInt("QUANTITY_IN_PACKAGE"),rs.getInt("QUANTITY_IN_UNIT"),rs.getInt("UNIT_SELLING_PRICE"),rs.getInt("PACKAGE_SELLING_PRICE")});
						i++;
					}
					pst.close();
				}
				catch(Exception e)
				{
					JOptionPane.showMessageDialog(hf,"Sorry !!! There are some error occured...!");
				}
			}
		});
		
		lblStockSearch=new JLabel("Search Here : ");
		lblStockSearch.setFont(f);
		
		txtStockSearch.setBounds(400,30,700,30);
		lblStockSearch.setBounds(250,30,120,30);
		
		panelViewStock.add(txtStockSearch);
		panelViewStock.add(lblStockSearch);
		
		btnStockPrint=new JButton("Print");
		btnStockPrint.addActionListener(this);
		
		btnStockPrint.setBounds(500,460,100,30);
		
		panelViewStock.add(btnStockPrint);
		
		viewStockDB();
		hf.add(panelViewStock);
	}
	
	public void viewStockDB()
	{
		modelStock=new DefaultTableModel();
		tableStcock=new JTable(modelStock);
		spViewStock=new JScrollPane(tableStcock);
		tableStcock.setRowHeight(24);
		tableStcock.setEnabled(false);
		modelStock.addColumn("ITEM NAME");
		modelStock.addColumn("CATEGORY");
		modelStock.addColumn("COMPANY");
		modelStock.addColumn("LOCATION");
		modelStock.addColumn("QUANTITY IN PACKAGE");
		modelStock.addColumn("QUANTITY IN UNIT");
		modelStock.addColumn("Selling Price per Unit");
		modelStock.addColumn("Selling Price per Package");
		
		try
		{
			
			PreparedStatement pst=con.prepareStatement("Select * from STOCK");
			ResultSet rs=pst.executeQuery();
			int i=1;
			while(rs.next())
			{
				modelStock.addRow(new Object[]{rs.getString("ITEM_NAME"),rs.getString("CATEGORY"),rs.getString("COMPANY"),rs.getString("LOCATION"),rs.getInt("QUANTITY_IN_PACKAGE"),rs.getInt("QUANTITY_IN_UNIT"),rs.getInt("UNIT_SELLING_PRICE"),rs.getInt("PACKAGE_SELLING_PRICE")});
			}
			
			pst.close();
			
		
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(hf,"Sorry !!! There are some error occured...!");
			
		}
		
		tableCategory.getTableHeader().setFont(new Font("Arial",Font.BOLD,20));
		spViewStock.setBounds(50,100,1050,350);
		panelViewStock.add(spViewStock);
	}
	
	public void addClient()
	{
		panelAddClient=new JPanel();
		panelAddClient.setVisible(false);
		panelAddClient.setLayout(null);
		panelAddClient.setBounds(100,150,1150,500);		
		panelAddClient.setBorder(BorderFactory.createTitledBorder("Add Client Here"));
		
		JLabel lblClientName,lblAddress,lblContactNo,lblEmail,lblCity;
		
		lblClientName=new JLabel("Client Name");
		lblClientName.setFont(f);
		txtACLName=new JTextField(20);
		txtACLName.setFont(f);
		txtACLName.addKeyListener(new KeyAdapter(){
			public void keyTyped(KeyEvent ke)
			{
				char c=ke.getKeyChar();
				if(Character.isDigit(c))
				{
					ke.consume();
				}
			}
		});
		
		
		lblCity=new JLabel("City");
		lblCity.setFont(f);
		txtACLCity=new JTextField(20);
		txtACLCity.setFont(f);
		txtACLCity.addKeyListener(new KeyAdapter(){
			public void keyTyped(KeyEvent ke)
			{
				char c=ke.getKeyChar();
				if(Character.isDigit(c))
				{
					ke.consume();
				}
			}
		});
		
		lblAddress=new JLabel("Address");
		lblAddress.setFont(f);
		txtACLAddress=new JTextArea();
		txtACLAddress.setLineWrap(true);
		txtACLAddress.setFont(f);
		
		lblContactNo=new JLabel("Contact No");
		lblContactNo.setFont(f);
		txtACLContactNo=new JTextField(10);
		txtACLContactNo.setFont(f);
		txtACLContactNo.addKeyListener(new KeyAdapter(){
			public void keyTyped(KeyEvent ke)
			{
				char c=ke.getKeyChar();
				if(!Character.isDigit(c))
				{
					ke.consume();
				}
				if(txtACLContactNo.getText().length()==10)
				{
					ke.consume();
				}
			}
		});
		
		lblEmail=new JLabel("Email");
		lblEmail.setFont(f);
		txtACLEmail=new JTextField(20);
		txtACLEmail.setFont(f);
		
		btnAddClientCancel=new JButton("Cancel");
		btnAddClientCancel.addActionListener(this);
		
		btnAddClient=new JButton("Add Client");
		btnAddClient.addActionListener(this);
		
		lblClientName.setBounds(150,50,150,30);
		txtACLName.setBounds(360,50,200,30);
		lblCity.setBounds(650,50,150,30);
		txtACLCity.setBounds(810,50,200,30);
		lblAddress.setBounds(150,100,150,30);
		txtACLAddress.setBounds(360,100,200,90);
		lblContactNo.setBounds(650,100,150,30);
		txtACLContactNo.setBounds(810,100,200,30);
		lblEmail.setBounds(150,200,150,30);
		txtACLEmail.setBounds(360,200,200,30);
		
		btnAddClientCancel.setBounds(30,450,100,30);
		btnAddClient.setBounds(970,450,150,30);
		
		panelAddClient.add(lblClientName);
		panelAddClient.add(txtACLName);
		panelAddClient.add(lblCity);
		panelAddClient.add(txtACLCity);
		panelAddClient.add(lblAddress);
		panelAddClient.add(txtACLAddress);
		panelAddClient.add(lblContactNo);
		panelAddClient.add(txtACLContactNo);
		panelAddClient.add(lblEmail);
		panelAddClient.add(txtACLEmail);
		
		panelAddClient.add(btnAddClientCancel);
		panelAddClient.add(btnAddClient);
		
		hf.add(panelAddClient);
	}
	
	public void removeClient()
	{
		
		panelRemoveClient=new JPanel();
		panelRemoveClient.setVisible(false);
		panelRemoveClient.setLayout(null);
		panelRemoveClient.setBounds(100,150,1150,500);		
		panelRemoveClient.setBorder(BorderFactory.createTitledBorder("Remove Client Here"));
				
		JLabel lblSelectClient,lblReason;
		
		lblSelectClient=new JLabel("Select Client");
		lblSelectClient.setFont(f);
		cbbRCLClient=new JComboBox();
		cbbRCLClient.setFont(f);
		
		lblReason=new JLabel("Reason");
		lblReason.setFont(f);
		txtRCLReason=new JTextArea();
		txtRCLReason.setLineWrap(true);
		txtRCLReason.setFont(f);
		
		btnRemoveClientCancel=new JButton("Cancel");
		btnRemoveClientCancel.addActionListener(this);
		
		btnRemoveClient=new JButton("Remove Client");
		btnRemoveClient.addActionListener(this);
		
		lblSelectClient.setBounds(20,20,200,30);
		cbbRCLClient.setBounds(220,20,300,30);
		lblReason.setBounds(20,60,200,30);
		txtRCLReason.setBounds(220,60,300,70);
		btnRemoveClientCancel.setBounds(220,150,100,30);
		btnRemoveClient.setBounds(370,150,150,30);
		
		panelRemoveClient.add(lblSelectClient);
		panelRemoveClient.add(cbbRCLClient);
		panelRemoveClient.add(lblReason);
		panelRemoveClient.add(txtRCLReason);
		panelRemoveClient.add(btnRemoveClientCancel);
		panelRemoveClient.add(btnRemoveClient);
		
		hf.add(panelRemoveClient);
	}
	
	public void updateClient()
	{
		panelUpdateClient=new JPanel();
		panelUpdateClient.setVisible(false);
		panelUpdateClient.setLayout(null);
		panelUpdateClient.setBounds(100,150,1150,500);		
		panelUpdateClient.setBorder(BorderFactory.createTitledBorder("Update Client Here"));
		
		
		JLabel lblClientName,lblAddress,lblContactNo,lblEmail,lblCity,lblSelectClient;
		
		lblSelectClient=new JLabel("Select Client");
		lblSelectClient.setFont(f);
		lblSelectClient.setHorizontalAlignment(JLabel.RIGHT);
		cbbUCLClient=new JComboBox();
		cbbUCLClient.setFont(f);
		cbbUCLClient.addItemListener(new ItemListener(){
			public void itemStateChanged(ItemEvent e)
			{
				fillUCL();
			}
		});
		
		lblClientName=new JLabel("Client Name");
		lblClientName.setFont(f);
		txtUCLName=new JTextField(20);
		txtUCLName.setFont(f);
		txtUCLName.addKeyListener(new KeyAdapter(){
			public void keyTyped(KeyEvent ke)
			{
				char c=ke.getKeyChar();
				if(Character.isDigit(c))
				{
					ke.consume();
				}
			}
		});
		
		lblCity=new JLabel("City");
		lblCity.setFont(f);
		txtUCLCity=new JTextField(20);
		txtUCLCity.setFont(f);
		txtUCLCity.addKeyListener(new KeyAdapter(){
			public void keyTyped(KeyEvent ke)
			{
				char c=ke.getKeyChar();
				if(Character.isDigit(c))
				{
					ke.consume();
				}
			}
		});
		
		lblAddress=new JLabel("Address");
		lblAddress.setFont(f);
		txtUCLAddress=new JTextArea();
		txtUCLAddress.setLineWrap(true);
		txtUCLAddress.setFont(f);
		
		lblContactNo=new JLabel("Contact No");
		lblContactNo.setFont(f);
		txtUCLContactNo=new JTextField(10);
		txtUCLContactNo.setFont(f);
		txtUCLContactNo.addKeyListener(new KeyAdapter(){
			public void keyTyped(KeyEvent ke)
			{
				char c=ke.getKeyChar();
				if(!Character.isDigit(c))
				{
					ke.consume();
				}
				if(txtUCLContactNo.getText().length()==10)
				{
					ke.consume();
				}
			}
		});
		
		lblEmail=new JLabel("Email");
		lblEmail.setFont(f);
		txtUCLEmail=new JTextField(20);
		txtUCLEmail.setFont(f);
		
		btnUpdateClientCancel=new JButton("Cancel");
		btnUpdateClientCancel.addActionListener(this);
		
		btnUpdateClient=new JButton("Update Client");
		btnUpdateClient.addActionListener(this);
		
		lblSelectClient.setBounds(100,20,150,30);
		cbbUCLClient.setBounds(260,20,300,30);
		
		lblClientName.setBounds(150,70,150,30);
		txtUCLName.setBounds(360,70,200,30);
		lblCity.setBounds(650,70,150,30);
		txtUCLCity.setBounds(810,70,200,30);
		lblAddress.setBounds(150,120,150,30);
		txtUCLAddress.setBounds(360,120,200,90);
		lblContactNo.setBounds(650,120,150,30);
		txtUCLContactNo.setBounds(810,120,200,30);
		lblEmail.setBounds(150,220,150,30);
		txtUCLEmail.setBounds(360,220,200,30);
		
		btnUpdateClientCancel.setBounds(30,450,100,30);
		btnUpdateClient.setBounds(970,450,150,30);
		
		panelUpdateClient.add(lblSelectClient);
		panelUpdateClient.add(cbbUCLClient);
		panelUpdateClient.add(lblClientName);
		panelUpdateClient.add(txtUCLName);
		panelUpdateClient.add(lblCity);
		panelUpdateClient.add(txtUCLCity);
		panelUpdateClient.add(lblAddress);
		panelUpdateClient.add(txtUCLAddress);
		panelUpdateClient.add(lblContactNo);
		panelUpdateClient.add(txtUCLContactNo);
		panelUpdateClient.add(lblEmail);
		panelUpdateClient.add(txtUCLEmail);
		
		panelUpdateClient.add(btnUpdateClientCancel);
		panelUpdateClient.add(btnUpdateClient);
		
		hf.add(panelUpdateClient);
	}
	
	public void viewClient()
	{
		panelViewClient=new JPanel();
		panelViewClient.setVisible(false);
		panelViewClient.setLayout(null);
		panelViewClient.setBounds(100,150,1150,500);		
		panelViewClient.setBorder(BorderFactory.createTitledBorder("Client"));
		
		txtClientSearch=new JTextField(20);
		txtClientSearch.setFont(f);
		txtClientSearch.addKeyListener(new KeyAdapter(){
			public void keyReleased(KeyEvent ke)
			{
				modelClient.setRowCount(0);
			
				try
				{
					PreparedStatement pst=con.prepareStatement("Select * from CLIENT where CLIENT_NAME like '"+txtClientSearch.getText()+"%' or CITY like '"+txtClientSearch.getText()+"%' ");
					ResultSet rs=pst.executeQuery();
						
					while(rs.next())
					{
						modelClient.addRow(new Object[]{rs.getString("CLIENT_NAME"),rs.getString("CITY"),rs.getString("ADDRESS"),rs.getString("CONTACT_NO"),rs.getString("EMAIL")});
					}
					pst.close();
				}
				catch(Exception e)
				{
					JOptionPane.showMessageDialog(hf,"Sorry !!! There are some error occured...!");
				}
			}
		});
		
		lblClientSearch=new JLabel("Search Here : ");
		lblClientSearch.setFont(f);
		
		txtClientSearch.setBounds(400,30,700,30);
		lblClientSearch.setBounds(250,30,120,30);
		
		panelViewClient.add(txtClientSearch);
		panelViewClient.add(lblClientSearch);
		
		btnClientPrint=new JButton("Print");
		btnClientPrint.addActionListener(this);
		
		btnClientPrint.setBounds(500,460,100,30);
		
		panelViewClient.add(btnClientPrint);
		
		viewClientDB();
		hf.add(panelViewClient);
	}
	
	public void viewClientDB()
	{
		modelClient=new DefaultTableModel();
		tableClient=new JTable(modelClient);
		spViewClient=new JScrollPane(tableClient);
		tableClient.setRowHeight(24);
		tableClient.setEnabled(false);
		modelClient.addColumn("CLIENT NAME");
		modelClient.addColumn("CITY");
		modelClient.addColumn("ADDRESS");
		modelClient.addColumn("CONTACT NO");
		modelClient.addColumn("EMAIL");
		
		
		try
		{
			
			PreparedStatement pst=con.prepareStatement("Select * from CLIENT");
			ResultSet rs=pst.executeQuery();
			
			while(rs.next())
			{
			
				modelClient.addRow(new Object[]{rs.getString("CLIENT_NAME"),rs.getString("CITY"),rs.getString("ADDRESS"),rs.getString("CONTACT_NO"),rs.getString("EMAIL")});
				
			}
			
			pst.close();
			
		
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(hf,"Sorry !!! There are some error occured...!");
			
		}
	
		tableCategory.getTableHeader().setFont(new Font("Arial",Font.BOLD,20));
		spViewClient.setBounds(50,100,1050,350);
		panelViewClient.add(spViewClient);
	}
	
	public void addCompany()
	{
		panelAddCompany=new JPanel();
		panelAddCompany.setVisible(false);
		panelAddCompany.setLayout(null);
		panelAddCompany.setBounds(100,150,1150,500);		
		panelAddCompany.setBorder(BorderFactory.createTitledBorder("Add Company Here"));
		
		JLabel lblLicenceNo,lblCompanyName,lblAddress,lblContactPerson,lblContactNo,lblEmail,lblDetails,lblCity;
		
		lblLicenceNo=new JLabel("Licence No");
		lblLicenceNo.setFont(f);
		txtACLicenceNo=new JTextField(10);
		txtACLicenceNo.setFont(f);
		
		lblCompanyName=new JLabel("Company Name");
		lblCompanyName.setFont(f);
		txtACCompanyName=new JTextField(10);
		txtACCompanyName.setFont(f);
		txtACCompanyName.addKeyListener(new KeyAdapter(){
			public void keyTyped(KeyEvent ke)
			{
				char c=ke.getKeyChar();
				if(Character.isDigit(c))
				{
					ke.consume();
				}
			}
		});
		
		lblDetails=new JLabel("Details");
		lblDetails.setFont(f);
		txtACDetails=new JTextArea();
		txtACDetails.setLineWrap(true);
		txtACDetails.setFont(f);
		
		lblCity=new JLabel("City");
		lblCity.setFont(f);
		txtACCity=new JTextField(20);
		txtACCity.setFont(f);
		txtACCity.addKeyListener(new KeyAdapter(){
			public void keyTyped(KeyEvent ke)
			{
				char c=ke.getKeyChar();
				if(Character.isDigit(c))
				{
					ke.consume();
				}
			}
		});
		
		lblAddress=new JLabel("Address");
		lblAddress.setFont(f);
		txtACAddress=new JTextArea();
		txtACAddress.setLineWrap(true);
		txtACAddress.setFont(f);
		
		lblContactPerson=new JLabel("Contact Person");
		lblContactPerson.setFont(f);
		txtACContactPerson=new JTextField(10);
		txtACContactPerson.setFont(f);
		txtACContactPerson.addKeyListener(new KeyAdapter(){
			public void keyTyped(KeyEvent ke)
			{
				char c=ke.getKeyChar();
				if(Character.isDigit(c))
				{
					ke.consume();
				}
			}
		});
		
		lblContactNo=new JLabel("Contact No");
		lblContactNo.setFont(f);
		txtACContactNo=new JTextField(10);
		txtACContactNo.setFont(f);
		txtACContactNo.addKeyListener(new KeyAdapter(){
			public void keyTyped(KeyEvent ke)
			{
				char c=ke.getKeyChar();
				if(!Character.isDigit(c))
				{
					ke.consume();
				}
				if(txtACContactNo.getText().length()==10)
				{
					ke.consume();
				}
			}
		});
		
		lblEmail=new JLabel("Email");
		lblEmail.setFont(f);
		txtACEmail=new JTextField(20);
		txtACEmail.setFont(f);
		
		btnAddCompanyCancel=new JButton("Cancel");
		btnAddCompanyCancel.addActionListener(this);
		
		btnAddCompany=new JButton("Add Company");
		btnAddCompany.addActionListener(this);
		
		lblLicenceNo.setBounds(150,50,150,30);
		txtACLicenceNo.setBounds(360,50,200,30);
		lblCompanyName.setBounds(650,50,150,30);
		txtACCompanyName.setBounds(810,50,200,30);
		lblDetails.setBounds(150,100,150,30);
		txtACDetails.setBounds(360,100,200,90);
		lblCity.setBounds(650,100,150,30);
		txtACCity.setBounds(810,100,200,30);
		lblAddress.setBounds(650,150,150,30);
		txtACAddress.setBounds(810,150,200,90);
		lblContactPerson.setBounds(150,200,150,30);
		txtACContactPerson.setBounds(360,200,200,30);
		lblContactNo.setBounds(650,250,150,30);
		txtACContactNo.setBounds(810,250,150,30);
		lblEmail.setBounds(150,250,150,30);
		txtACEmail.setBounds(360,250,200,30);
		
		btnAddCompanyCancel.setBounds(30,450,100,30);
		btnAddCompany.setBounds(970,450,150,30);
		
		panelAddCompany.add(lblLicenceNo);
		panelAddCompany.add(txtACLicenceNo);
		panelAddCompany.add(lblCompanyName);
		panelAddCompany.add(txtACCompanyName);
		panelAddCompany.add(lblDetails);
		panelAddCompany.add(txtACDetails);
		panelAddCompany.add(lblCity);
		panelAddCompany.add(txtACCity);
		panelAddCompany.add(lblAddress);
		panelAddCompany.add(txtACAddress);
		panelAddCompany.add(lblContactPerson);
		panelAddCompany.add(txtACContactPerson);
		panelAddCompany.add(lblContactNo);
		panelAddCompany.add(txtACContactNo);
		panelAddCompany.add(lblEmail);
		panelAddCompany.add(txtACEmail);
		panelAddCompany.add(btnAddCompanyCancel);
		panelAddCompany.add(btnAddCompany);
		
		panelAddCompany.add(btnAddCompanyCancel);
		panelAddCompany.add(btnAddCompany);
		
		hf.add(panelAddCompany);
	}
	
	public void removeCompany()
	{
		panelRemoveCompany=new JPanel();
		panelRemoveCompany.setVisible(false);
		panelRemoveCompany.setLayout(null);
		panelRemoveCompany.setBounds(100,150,1150,500);		
		panelRemoveCompany.setBorder(BorderFactory.createTitledBorder("Remove Company Here"));
		
		JLabel lblCompanyName,lblNote;
		
		lblCompanyName=new JLabel("Company Name");
		lblCompanyName.setFont(f);
		cbbRCCompanyName=new JComboBox();
		cbbRCCompanyName.setFont(f);
		
		lblNote=new JLabel("Note");
		lblNote.setFont(f);
		txtRCNote=new JTextArea();
		txtRCNote.setFont(f);
		txtRCNote.setLineWrap(true);
		
		btnRemoveCompanyCancel=new JButton("Cancel");
		btnRemoveCompanyCancel.addActionListener(this);
		
		btnRemoveCompany=new JButton("Remove Company");
		btnRemoveCompany.addActionListener(this);
		
		lblCompanyName.setBounds(50,50,150,30);
		cbbRCCompanyName.setBounds(260,50,200,30);
		lblNote.setBounds(550,50,50,30);
		txtRCNote.setBounds(600,50,200,90);
		
		btnRemoveCompanyCancel.setBounds(350,200,100,30);
		btnRemoveCompany.setBounds(470,200,150,30);
		
		panelRemoveCompany.add(lblCompanyName);
		panelRemoveCompany.add(cbbRCCompanyName);
		panelRemoveCompany.add(lblNote);
		panelRemoveCompany.add(txtRCNote);
		panelRemoveCompany.add(btnRemoveCompanyCancel);
		panelRemoveCompany.add(btnRemoveCompany);
		
		
		hf.add(panelRemoveCompany);
	}
	
	public void updateCompany()
	{
		panelUpdateCompany=new JPanel();
		panelUpdateCompany.setVisible(false);
		panelUpdateCompany.setLayout(null);
		panelUpdateCompany.setBounds(100,150,1150,500);		
		panelUpdateCompany.setBorder(BorderFactory.createTitledBorder("Update Company Here"));
		
		JLabel lblSelectCompany,lblLicenceNo,lblCompanyName,lblDetails,lblCity,lblAddress,lblContactPerson,lblContactNo,lblEmail;
		
		lblSelectCompany=new JLabel("Select Company");
		lblSelectCompany.setFont(f);
		cbbUCCompanyName=new JComboBox();
		cbbUCCompanyName.setFont(f);
		cbbUCCompanyName.addItemListener(new ItemListener(){
			public void itemStateChanged(ItemEvent e)
			{
				fillUC();
			}
		});
		
		
		lblLicenceNo=new JLabel("Licence No");
		lblLicenceNo.setFont(f);
		txtUCLicenceNo=new JTextField(10);
		txtUCLicenceNo.setFont(f);
		
		lblCompanyName=new JLabel("Company Name");
		lblCompanyName.setFont(f);
		txtUCCompanyName=new JTextField(20);
		txtUCCompanyName.setFont(f);
		txtUCCompanyName.addKeyListener(new KeyAdapter(){
			public void keyTyped(KeyEvent ke)
			{
				char c=ke.getKeyChar();
				if(Character.isDigit(c))
				{
					ke.consume();
				}
			}
		});
		
		lblDetails=new JLabel("Details");
		lblDetails.setFont(f);
		txtUCDetails=new JTextArea();
		txtUCDetails.setLineWrap(true);
		txtUCDetails.setFont(f);
		
		lblCity=new JLabel("City");
		lblCity.setFont(f);
		txtUCCity=new JTextField(20);
		txtUCCity.setFont(f);
		txtUCCity.addKeyListener(new KeyAdapter(){
			public void keyTyped(KeyEvent ke)
			{
				char c=ke.getKeyChar();
				if(Character.isDigit(c))
				{
					ke.consume();
				}
			}
		});
		
		lblAddress=new JLabel("Address");
		lblAddress.setFont(f);
		txtUCAddress=new JTextArea();
		txtUCAddress.setFont(f);
		txtUCAddress.setLineWrap(true);
		
		lblContactPerson=new JLabel("Contact Person");
		lblContactPerson.setFont(f);
		txtUCContactPerson=new JTextField(20);
		txtUCContactPerson.setFont(f);
		txtUCContactPerson.addKeyListener(new KeyAdapter(){
			public void keyTyped(KeyEvent ke)
			{
				char c=ke.getKeyChar();
				if(Character.isDigit(c))
				{
					ke.consume();
				}
			}
		});
		
		lblContactNo=new JLabel("Contact No");
		lblContactNo.setFont(f);
		txtUCContactNo=new JTextField(10);
		txtUCContactNo.setFont(f);
		txtUCContactNo.addKeyListener(new KeyAdapter(){
			public void keyTyped(KeyEvent ke)
			{
				char c=ke.getKeyChar();
				if(!Character.isDigit(c))
				{
					ke.consume();
				}
				if(txtUCContactNo.getText().length()==10)
				{
					ke.consume();
				}
			}
		});
		
		lblEmail=new JLabel("Email");
		lblEmail.setFont(f);
		txtUCEmail=new JTextField(20);
		txtUCEmail.setFont(f);
		
		btnUpdateCompanyCancel=new JButton("Cancel");
		btnUpdateCompanyCancel.addActionListener(this);
		
		btnUpdateCompany=new JButton("Update Company");
		btnUpdateCompany.addActionListener(this);
		
		lblSelectCompany.setBounds(150,20,200,30);
		cbbUCCompanyName.setBounds(350,20,300,30);
		
		lblLicenceNo.setBounds(150,100,150,30);
		txtUCLicenceNo.setBounds(360,100,200,30);
		lblCompanyName.setBounds(650,100,150,30);
		txtUCCompanyName.setBounds(810,100,200,30);
		lblDetails.setBounds(150,150,150,30);
		txtUCDetails.setBounds(360,150,200,90);
		lblCity.setBounds(650,150,150,30);
		txtUCCity.setBounds(810,150,200,30);
		lblAddress.setBounds(650,200,150,30);
		txtUCAddress.setBounds(810,200,200,90);
		lblContactPerson.setBounds(150,250,150,30);
		txtUCContactPerson.setBounds(360,250,200,30);
		lblContactNo.setBounds(650,300,150,30);
		txtUCContactNo.setBounds(810,300,200,30);
		lblEmail.setBounds(150,300,150,30);
		txtUCEmail.setBounds(360,300,200,30);
		
		btnUpdateCompanyCancel.setBounds(30,450,100,30);
		btnUpdateCompany.setBounds(970,450,150,30);
		
		panelUpdateCompany.add(lblSelectCompany);
		panelUpdateCompany.add(cbbUCCompanyName);
		panelUpdateCompany.add(lblLicenceNo);
		panelUpdateCompany.add(txtUCLicenceNo);
		panelUpdateCompany.add(lblCompanyName);
		panelUpdateCompany.add(txtUCCompanyName);
		panelUpdateCompany.add(lblDetails);
		panelUpdateCompany.add(txtUCDetails);
		panelUpdateCompany.add(lblCity);
		panelUpdateCompany.add(txtUCCity);
		panelUpdateCompany.add(lblAddress);
		panelUpdateCompany.add(txtUCAddress);
		panelUpdateCompany.add(lblContactPerson);
		panelUpdateCompany.add(txtUCContactPerson);
		panelUpdateCompany.add(lblContactNo);
		panelUpdateCompany.add(txtUCContactNo);
		panelUpdateCompany.add(lblEmail);
		panelUpdateCompany.add(txtUCEmail);
		
		panelUpdateCompany.add(btnUpdateCompanyCancel);
		panelUpdateCompany.add(btnUpdateCompany);
		
		hf.add(panelUpdateCompany);
	}
	
	public void viewCompany()
	{
		panelViewCompany=new JPanel();
		panelViewCompany.setVisible(false);
		panelViewCompany.setLayout(null);
		panelViewCompany.setBounds(100,150,1150,500);		
		panelViewCompany.setBorder(BorderFactory.createTitledBorder("Company"));
		
		txtCompanySearch=new JTextField(20);
		txtCompanySearch.setFont(f);
		txtCompanySearch.addKeyListener(new KeyAdapter(){
			public void keyReleased(KeyEvent ke)
			{
				modelCompany.setRowCount(0);
			
				try
				{
					PreparedStatement pst=con.prepareStatement("Select * from COMPANY where LICENCE_NO like '"+txtCompanySearch.getText()+"%' or COMPANY_NAME like '"+txtCompanySearch.getText()+"%' or DETAIL like '"+txtCompanySearch.getText()+"%'");
					ResultSet rs=pst.executeQuery();
						
					while(rs.next())
					{
						modelCompany.addRow(new Object[]{rs.getString(8),rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7)});
					}
					pst.close();
				}
				catch(Exception e)
				{
					JOptionPane.showMessageDialog(hf,"Sorry !!! There are some error occured...!");
				}
			}
		});
		
		lblCompanySearch=new JLabel("Search Here : ");
		lblCompanySearch.setFont(f);
		
		txtCompanySearch.setBounds(400,30,700,30);
		lblCompanySearch.setBounds(250,30,120,30);
		
		panelViewCompany.add(txtCompanySearch);
		panelViewCompany.add(lblCompanySearch);
		
		btnCompanyPrint=new JButton("Print");
		btnCompanyPrint.addActionListener(this);
		
		btnCompanyPrint.setBounds(500,460,100,30);
		
		panelViewCompany.add(btnCompanyPrint);
		
		viewCompanyDB();		
		hf.add(panelViewCompany);
	}
	
	public void viewCompanyDB()
	{
		modelCompany=new DefaultTableModel();
		tableCompany=new JTable(modelCompany);
		spViewCompany=new JScrollPane(tableCompany);
		tableCompany.setRowHeight(24);
		tableCompany.setEnabled(false);
		modelCompany.addColumn("LICENCE NO");
		modelCompany.addColumn("COMPANY NAME");
		modelCompany.addColumn("DETAIL");
		modelCompany.addColumn("CITY");
		modelCompany.addColumn("ADDRESS");
		modelCompany.addColumn("CONTACT PERSON");
		modelCompany.addColumn("CONTACT NO");
		modelCompany.addColumn("EMAIL");
		
		try
		{
			
			PreparedStatement pst=con.prepareStatement("Select * from COMPANY");
			ResultSet rs=pst.executeQuery();
			
			while(rs.next())
			{
				
				modelCompany.addRow(new Object[]{rs.getString(8),rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7)});
				
			}
			
			pst.close();
			
		
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(hf,"Sorry !!! There are some error occured...!");
			
		}
	
		spViewCompany.setBounds(50,100,1050,350);
		panelViewCompany.add(spViewCompany);
	}
	
	public void setAllFalse()
	{
		panelSearchResult.setVisible(false);
		
		panelChangePassword.setVisible(false);
		
		panelSellCustoized.setVisible(false);
		panelSellRetailer.setVisible(false);
		panelRCart.setVisible(false);
		panelRCart.setVisible(false);
		
		panelAddCategory.setVisible(false);
		panelRemoveCategory.setVisible(false);
		panelUpdateCategory.setVisible(false);
		panelViewCategory.setVisible(false);
		
		panelAddItem.setVisible(false);
		panelRemoveItem.setVisible(false);
		panelUpdateItem.setVisible(false);
		panelViewItem.setVisible(false);
		
		panelAddStock.setVisible(false);
		panelRemoveStock.setVisible(false);
		panelUpdateStock.setVisible(false);
		panelViewStock.setVisible(false);
		
		panelAddClient.setVisible(false);
		panelRemoveClient.setVisible(false);
		panelUpdateClient.setVisible(false);
		panelViewClient.setVisible(false);
		
		panelAddCompany.setVisible(false);
		panelRemoveCompany.setVisible(false);
		panelUpdateCompany.setVisible(false);
		panelViewCompany.setVisible(false);
		
		panelRetailerSales.setVisible(false);
		panelCustomizedSales.setVisible(false);
	}
	
	public void gotoHome()
	{
		panelSearchResult.setVisible(true);
	}
	
	public void addCategoryTODB()
	{
		if(txtACCategory.getText().equals(""))
		{
			JOptionPane.showMessageDialog(hf,"Please provide category name !!!");
			txtACCategory.requestFocus();
		}
		else
		{
		String category=txtACCategory.getText().trim();
		try
		{
			
			String query="Insert into CATEGORY values(?)";
			PreparedStatement pst=con.prepareStatement(query);
			pst.setString(1,category);
			int r=pst.executeUpdate();
			
			if(r==1)
			{
				JOptionPane op=new JOptionPane();
				op.showMessageDialog(hf,"Category successfully inserted...!");
				txtACCategory.setText("");
				txtACCategory.requestFocus();
				spViewCategory.setVisible(false);
				spViewCategory=null;
				viewCategoryDB();
				panelAddCategory.add(spViewCategory);
				
				
			}
			else
			{
					JOptionPane op=new JOptionPane();
					op.showMessageDialog(hf,"Else");
			}
			
			pst.close();
				
			
		}
		catch(Exception e)
		{
			JOptionPane op=new JOptionPane();
			op.showMessageDialog(hf,"Sorry!!!Category allready available!!!");
		}
		}
			
	}
	
	public void removeCategoryFromDB()
	{
		
		try
		{
			PreparedStatement pstCheck=con.prepareStatement("Select * from ITEM where CATEGORY_NAME=?");
			pstCheck.setString(1,(String)cbbRCCategory.getSelectedItem());
			ResultSet rsCheck=pstCheck.executeQuery();
			
			int itemFlag=0;
			while(rsCheck.next())
			{
				itemFlag++;
			}
			
			PreparedStatement pstCheckStock=con.prepareStatement("Select * from STOCK where CATEGORY=?");
			pstCheckStock.setString(1,(String)cbbRCCategory.getSelectedItem());
			ResultSet rsCheckStock=pstCheckStock.executeQuery();
			int stockFlag=0;
			
			while(rsCheckStock.next())
			{
				stockFlag++;
			}
			
			if(itemFlag>0 && stockFlag>0)
			{
				String msg="There are "+itemFlag+" items available in "+(String)cbbRCCategory.getSelectedItem()+" category.\n And "+stockFlag+" Items are available in current Stock.\n Do you want to remove it's related items also ?";
				int res=JOptionPane.showConfirmDialog(hf,msg,"Confirm",JOptionPane.YES_NO_OPTION);
			
				if(res==JOptionPane.YES_OPTION)
				{
					PreparedStatement pstIA=con.prepareStatement("Delete from ITEM where CATEGORY_NAME=?");
					pstIA.setString(1,(String)cbbRCCategory.getSelectedItem());
					int iRes=pstIA.executeUpdate();				
					
					PreparedStatement pstSA=con.prepareStatement("Delete from STOCK where CATEGORY=?");
					pstSA.setString(1,(String)cbbRCCategory.getSelectedItem());
					int sRes=pstSA.executeUpdate();
			
					PreparedStatement pst=con.prepareStatement("Delete from CATEGORY where CATEGORY_NAME=?");
					pst.setString(1,(String)cbbRCCategory.getSelectedItem());
					int r=pst.executeUpdate();
				
					if(r==1)
					{
						JOptionPane op=new JOptionPane();
						op.showMessageDialog(hf,"Category successfully Removed...!");
						spViewCategory.setVisible(false);
						spViewCategory=null;
						viewCategoryDB();
						panelRemoveCategory.add(spViewCategory);
						cbbRCCategory.removeItem(cbbRCCategory.getSelectedItem());
					}
					else
					{
						JOptionPane op=new JOptionPane();
						op.showMessageDialog(hf,"Removing Failed...!");
					}
				}
			}
			else if(itemFlag>0)
			{
				String msg="There are "+itemFlag+" items available in "+(String)cbbRCCategory.getSelectedItem()+" category.\n\n Do you want to remove it's related items also ?";
				int res=JOptionPane.showConfirmDialog(hf,msg,"Confirm",JOptionPane.YES_NO_OPTION);
				
				if(res==JOptionPane.YES_OPTION)
				{
					PreparedStatement pstIA=con.prepareStatement("Delete from ITEM where CATEGORY_NAME=?");
					pstIA.setString(1,(String)cbbRCCategory.getSelectedItem());
					int iRes=pstIA.executeUpdate();				
							
					PreparedStatement pst=con.prepareStatement("Delete from CATEGORY where CATEGORY_NAME=?");
					pst.setString(1,(String)cbbRCCategory.getSelectedItem());
					int r=pst.executeUpdate();
				
					if(r==1)
					{
						JOptionPane op=new JOptionPane();
						op.showMessageDialog(hf,"Category successfully Removed...!");
						spViewCategory.setVisible(false);
						spViewCategory=null;
						viewCategoryDB();
						panelRemoveCategory.add(spViewCategory);
						cbbRCCategory.removeItem(cbbRCCategory.getSelectedItem());
					}
					else
					{
						JOptionPane op=new JOptionPane();
						op.showMessageDialog(hf,"Removing Failed...!");
					}
				}
			}
			else if(stockFlag>0)
			{
				String msg="There are "+stockFlag+" Items are available in current Stock.\n Do you want to remove it's related items also ?";
				int res=JOptionPane.showConfirmDialog(hf,msg,"Confirm",JOptionPane.YES_NO_OPTION);
				if(res==JOptionPane.YES_OPTION)
				{
					PreparedStatement pstSA=con.prepareStatement("Delete from STOCK where CATEGORY=?");
					pstSA.setString(1,(String)cbbRCCategory.getSelectedItem());
					int sRes=pstSA.executeUpdate();
			
					PreparedStatement pst=con.prepareStatement("Delete from CATEGORY where CATEGORY_NAME=?");
					pst.setString(1,(String)cbbRCCategory.getSelectedItem());
					int r=pst.executeUpdate();
				
					if(r==1)
					{
						JOptionPane op=new JOptionPane();
						op.showMessageDialog(hf,"Category successfully Removed...!");
						spViewCategory.setVisible(false);
						spViewCategory=null;
						viewCategoryDB();
						panelRemoveCategory.add(spViewCategory);
						cbbRCCategory.removeItem(cbbRCCategory.getSelectedItem());
					}
					else
					{
						JOptionPane op=new JOptionPane();
						op.showMessageDialog(hf,"Removing Failed...!");
					}
				}
			}
			else
			{
				PreparedStatement pst=con.prepareStatement("Delete from CATEGORY where CATEGORY_NAME=?");
					pst.setString(1,(String)cbbRCCategory.getSelectedItem());
					int r=pst.executeUpdate();
				
					if(r==1)
					{
						JOptionPane op=new JOptionPane();
						op.showMessageDialog(hf,"Category successfully Removed...!");
						spViewCategory.setVisible(false);
						spViewCategory=null;
						viewCategoryDB();
						panelRemoveCategory.add(spViewCategory);
						cbbRCCategory.removeItem(cbbRCCategory.getSelectedItem());
					}
					else
					{
						JOptionPane op=new JOptionPane();
						op.showMessageDialog(hf,"Removing Failed...!");
					}
			}
			
	
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(hf,"Sorry !!! There are some error occured...!");
		}
		
	}
	
	public void updateCategoryToDB()
	{
		String cat=txtUCCategory.getText();
		try
		{
			
			PreparedStatement pst=con.prepareStatement("Update CATEGORY set CATEGORY_NAME=? where CATEGORY_NAME=?");		
			pst.setString(1,txtUCCategory.getText());
			pst.setString(2,(String)cbbUCCategory.getSelectedItem());
			int result=pst.executeUpdate();
			
			PreparedStatement pstUI=con.prepareStatement("Update ITEM set CATEGORY_NAME=? where CATEGORY_NAME=?");
			pstUI.setString(1,cat);
			pstUI.setString(2,(String)cbbUCCategory.getSelectedItem());
			int UIResult=pstUI.executeUpdate();
			
			PreparedStatement pstUS=con.prepareStatement("Update STOCK set CATEGORY=? where CATEGORY=?");
			pstUS.setString(1,cat);
			pstUS.setString(2,(String)cbbUCCategory.getSelectedItem());
			int USResult=pstUS.executeUpdate();
			
			if(result>0)
			{
				JOptionPane op=new JOptionPane();
				op.showMessageDialog(hf,"Category successfully updated...!");
				cbbUCCategory.removeItem(cbbUCCategory.getSelectedItem());
				cbbUCCategory.addItem(cat);
				spViewCategory.setVisible(false);
				spViewCategory=null;
				viewCategoryDB();
				panelUpdateCategory.add(spViewCategory);
				
			}
			else
			{
				JOptionPane op=new JOptionPane();
				op.showMessageDialog(hf,"failed");
			
			}
			
		}
		catch(Exception e)
		{
			JOptionPane op=new JOptionPane();
			op.showMessageDialog(hf,"Sorry!!!Some error occured.");
		}
	}
	
	public void addItemToDB()
	{
		float unit=0;
		float uPrice=0,pPrice=0;
		float price;
		if(txtAIUnitInPackage.getText().equals(""))
		{
			unit=0;
			uPrice=Integer.parseInt(txtAIPrice.getText());
		}
		else
		{
			price=Integer.parseInt(txtAIPrice.getText());
			unit=Integer.parseInt(txtAIUnitInPackage.getText());
			
			if(rbAIPerPackage.isSelected())
			{
				pPrice=price;
				uPrice=price/unit;
			}
		
			if(rbAIPerUnit.isSelected())
			{
				uPrice=price;
				pPrice=price*unit;
			}
		}
		
		java.sql.Date d=new java.sql.Date(modelAIMnfDate.getValue().getTime());
		java.sql.Date d2=new java.sql.Date(modelAIExpDate.getValue().getTime());
		DateFormat df=new SimpleDateFormat("dd/mm/yyyy");
		try
		{
			
			PreparedStatement pst=con.prepareStatement("Insert into ITEM values(?,?,?,?,?,?,?,?,?,?)");
			pst.setInt(10,Integer.parseInt(txtAISrNo.getText()));
			pst.setString(1,txtAIItemName.getText());
			pst.setString(2,(String)cbbAICompany.getSelectedItem());
			pst.setString(3,(String)cbbAICategory.getSelectedItem());
			pst.setString(4,txtAIDetail.getText());
			pst.setFloat(5,uPrice);
			pst.setFloat(6,pPrice);
			pst.setFloat(7,unit);
			pst.setDate(8,d);
			pst.setDate(9,d2);
			
			
			
			int result=pst.executeUpdate();
			if(result==0)
			{
				JOptionPane.showMessageDialog(hf,"Sorry some error occured...!");
			}
			else
			{
				JOptionPane.showMessageDialog(hf,"Item successfully inserted...!");
				txtAISrNo.setText("");
				txtAISrNo.requestFocus();
				txtAIItemName.setText("");
				cbbAICompany.setSelectedIndex(0);
				cbbAICategory.setSelectedIndex(0);
				txtAIDetail.setText("");
				txtAIPrice.setText("");
				txtAIUnitInPackage.setText("");
				rbAIPerPackage.setSelected(false);
				rbAIPerUnit.setSelected(false);
				modelAIExpDate.setValue(null);
				modelAIMnfDate.setValue(null);
			}
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(hf,"Batch is allready available...!");
		}
	}
	
	public void removeItemFromDB()
	{
		int flag=1;
		try
		{
			PreparedStatement pstCheck=con.prepareStatement("Select * from STOCK where ITEM_NAME=? and CATEGORY=? and COMPANY=?");
			pstCheck.setString(1,(String)cbbRIItemName.getSelectedItem());
			pstCheck.setString(2,(String)cbbRICategory.getSelectedItem());
			pstCheck.setString(3,(String)cbbRICompany.getSelectedItem());
			ResultSet rsCheck=pstCheck.executeQuery();
			
			if(rsCheck.next())
			{
				String msg="There are "+rsCheck.getInt("QUANTITY_IN_UNIT")+" unit available in stock.\nIt will also be removed from stock.\nAre you sure you want to remove ?";
				int checkRes=JOptionPane.showConfirmDialog(hf,msg,"Confirm",JOptionPane.YES_NO_OPTION);
				if(checkRes==JOptionPane.YES_OPTION)
				{
					PreparedStatement pstDS=con.prepareStatement("Delete from STOCK where ITEM_NAME=? and CATEGORY=? and COMPANY=?");
					pstDS.setString(1,(String)cbbRIItemName.getSelectedItem());
					pstDS.setString(2,(String)cbbRICategory.getSelectedItem());
					pstDS.setString(3,(String)cbbRICompany.getSelectedItem());
					int resDS=pstDS.executeUpdate();
					
				}
				else
				{
					flag=0;
				}
			}
			
			if(flag!=0)
			{
				PreparedStatement pst=con.prepareStatement("Delete from ITEM where ITEM_NAME=? AND COMPANY_NAME=? AND CATEGORY_NAME=?");
				pst.setString(1,(String)cbbRIItemName.getSelectedItem());
				pst.setString(2,(String)cbbRICompany.getSelectedItem());
				pst.setString(3,(String)cbbRICategory.getSelectedItem());
				int result=pst.executeUpdate();
				if(result==0)
				{
					JOptionPane.showMessageDialog(hf,"Some error occured!!!");
				}
				else	
				{
					JOptionPane.showMessageDialog(hf,"Item successfully Removed...!");
					cbbRIItemName.removeItem(cbbRIItemName.getSelectedItem());
				}	
			}	
			
		
		}
		catch(Exception e)
		{
			System.out.println(e);
			//JOptionPane.showMessageDialog(hf,"Sorry !!! There are some error occured...!");
		}
						
	}
	
	public void fillUI()
	{
		
		try
		{
			
			PreparedStatement pst=con.prepareStatement("Select * from ITEM where ITEM_NAME=? and CATEGORY_NAME=? and COMPANY_NAME=?");
			pst.setString(1,(String)cbbUIItemName.getSelectedItem());;
			pst.setString(2,(String)cbbUISCategory.getSelectedItem());
			pst.setString(3,(String)cbbUISCompany.getSelectedItem());
			ResultSet rs=pst.executeQuery();
			
			while(rs.next())
			{				
				txtUISrNo.setText(rs.getString(10));
				txtUIItemName.setText(rs.getString(1));
				txtUICompany.setText(rs.getString(2));
				txtUICategory.setText(rs.getString(3));
				txtUIDetail.setText(rs.getString(4));
				cbbUICategory.setSelectedItem(rs.getString("CATEGORY_NAME"));
				cbbUICompanyName.setSelectedItem(rs.getString("COMPANY_NAME"));
				if(rs.getInt(6)==0)
				{
					txtUIPrice.setText(""+rs.getInt(5));
					rbUIPerUnit.setSelected(true);
				}
				else
				{
					txtUIPrice.setText(""+rs.getInt(6));
					rbUIPerPackage.setSelected(true);
				}
				txtUIUnitInPackage.setText(rs.getString(7));
				modelUIMnfDate.setValue(rs.getDate(8));
				modelUIExpDate.setValue(rs.getDate(9));
				
				
				
			}
			pst.close();
			
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(hf,"Sorry !!! There are some error occured...!");
		}
	}
	
	public void updateItemToDB()
	{
		int unit=0;
		int price;
		int uPrice=0,pPrice=0;
		if(txtUIUnitInPackage.getText().equals("") || txtUIUnitInPackage.getText().equals(null))
		{
			unit=0;
			uPrice=Integer.parseInt(txtUIPrice.getText());
		}
		else
		{
			price=Integer.parseInt(txtUIPrice.getText());
			unit=Integer.parseInt(txtUIUnitInPackage.getText());
			
			if(rbUIPerPackage.isSelected())
			{
				pPrice=price;
				uPrice=price/unit;
			}
		
			if(rbUIPerUnit.isSelected())
			{
				uPrice=price;
				pPrice=price*unit;
			}
		}
		java.sql.Date d=new java.sql.Date(modelUIMnfDate.getValue().getTime());
		java.sql.Date d2=new java.sql.Date(modelUIExpDate.getValue().getTime());
		DateFormat df=new SimpleDateFormat("dd/mm/yyyy");
		try
		{
			
			PreparedStatement pst=con.prepareStatement("Update ITEM set SERIAL_NO=?,ITEM_NAME=?,COMPANY_NAME=?,CATEGORY_NAME=?,DETAIL=?,UNIT_PRICE=?,PACKAGE_PRICE=?,UNIT_IN_PACKAGE=?,MNF_DATE=?,EXP_DATE=? where ITEM_NAME=?");
			pst.setString(1,txtUISrNo.getText());
			pst.setString(2,txtUIItemName.getText());
			pst.setString(3,(String)cbbUICompanyName.getSelectedItem());
			pst.setString(4,(String)cbbUICategory.getSelectedItem());
			pst.setString(5,txtUIDetail.getText());
			pst.setInt(6,uPrice);
			pst.setInt(7,pPrice);
			pst.setInt(8,unit);
			pst.setDate(9,d);
			pst.setDate(10,d2);
			pst.setString(11,(String)cbbUIItemName.getSelectedItem());
			
			int result=pst.executeUpdate();
			
			if(result==0)
			{
				JOptionPane.showMessageDialog(hf,"Sorry some error occured...!");
			}
			else
			{
				JOptionPane.showMessageDialog(hf,"Item successfully updated...!");
				miUpdateItem.doClick();
			}
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(hf,"Sorry !!! There are some error occured...!");
		}
	}
	
	public void addCompanyToDB()
	{
		try
		{
			
			PreparedStatement pst=con.prepareStatement("Insert into COMPANY values (?,?,?,?,?,?,?,?)");
			pst.setString(8,txtACLicenceNo.getText());
			pst.setString(1,txtACCompanyName.getText());
			pst.setString(2,txtACDetails.getText());
			pst.setString(3,txtACCity.getText());
			pst.setString(4,txtACAddress.getText());
			pst.setString(5,txtACContactPerson.getText());
			pst.setString(6,txtACContactNo.getText());
			pst.setString(7,txtACEmail.getText());
			
			int result=pst.executeUpdate();
			
			if(result==0)
			{
				JOptionPane.showMessageDialog(hf,"Sorry,Some error occured...!");
			}
			else
			{
				JOptionPane.showMessageDialog(hf,"Company successfully inserted...!");
				txtACLicenceNo.setText("");
				txtACCompanyName.setText("");
				txtACCity.setText("");
				txtACAddress.setText("");
				txtACDetails.setText("");
				txtACContactPerson.setText("");
				txtACContactNo.setText("");
				txtACEmail.setText("");
				
				txtACLicenceNo.requestFocus();
			}
			
			
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(hf,"Sorry !!! There are some error occured...!");
		}
	}
	
	public void removeCompanyFromDB()
	{
		try
		{
			PreparedStatement pstCheck=con.prepareStatement("Select * from ITEM where COMPANY_NAME=?");
			pstCheck.setString(1,(String)cbbRCCompanyName.getSelectedItem());
			ResultSet rsCheck=pstCheck.executeQuery();
			
			int itemFlag=0;
			while(rsCheck.next())
			{
				itemFlag++;
			}
			
			PreparedStatement pstCheckStock=con.prepareStatement("Select * from STOCK where COMPANY=?");
			pstCheckStock.setString(1,(String)cbbRCCompanyName.getSelectedItem());
			ResultSet rsCheckStock=pstCheckStock.executeQuery();
			int stockFlag=0;
			
			while(rsCheckStock.next())
			{
				stockFlag++;
			}
			
			if(itemFlag>0 && stockFlag>0)
			{
				String msg="There are "+itemFlag+" items available in "+(String)cbbRCCompanyName.getSelectedItem()+" company.\n And "+stockFlag+" Items are available in current Stock.\nIts all also will be remove.\nAre you sure to remove ?";
				int res=JOptionPane.showConfirmDialog(hf,msg,"Confirm",JOptionPane.YES_NO_OPTION);
			
				if(res==JOptionPane.YES_OPTION)
				{
					PreparedStatement pstIA=con.prepareStatement("Delete from ITEM where COMPANY_NAME=?");
					pstIA.setString(1,(String)cbbRCCompanyName.getSelectedItem());
					int iRes=pstIA.executeUpdate();				
					
					PreparedStatement pstSA=con.prepareStatement("Delete from STOCK where COMPANY=?");
					pstSA.setString(1,(String)cbbRCCompanyName.getSelectedItem());
					int sRes=pstSA.executeUpdate();
			
					PreparedStatement pst=con.prepareStatement("Delete from COMPANY where COMPANY_NAME=?");
					pst.setString(1,(String)cbbRCCompanyName.getSelectedItem());
					int r=pst.executeUpdate();
				
					if(r==1)
					{
						JOptionPane op=new JOptionPane();
						op.showMessageDialog(hf,"Company successfully Removed...!");
						
						cbbRCCompanyName.removeItem(cbbRCCompanyName.getSelectedItem());
						
					}
					else
					{
						JOptionPane op=new JOptionPane();
						op.showMessageDialog(hf,"Removing Failed...!");
					}
				}
			}
			else if(itemFlag>0)
			{
				String msg="There are "+itemFlag+" items available in "+(String)cbbRCCompanyName.getSelectedItem()+" company.\nIts all items also will be removed.\nDo you want to remove ?";
				int res=JOptionPane.showConfirmDialog(hf,msg,"Confirm",JOptionPane.YES_NO_OPTION);
				
				if(res==JOptionPane.YES_OPTION)
				{
					PreparedStatement pstIA=con.prepareStatement("Delete from ITEM where COMPANY_NAME=?");
					pstIA.setString(1,(String)cbbRCCompanyName.getSelectedItem());
					int iRes=pstIA.executeUpdate();				
							
					PreparedStatement pst=con.prepareStatement("Delete from COMPANY where COMPANY_NAME=?");
					pst.setString(1,(String)cbbRCCompanyName.getSelectedItem());
					int r=pst.executeUpdate();
				
					if(r==1)
					{
						JOptionPane op=new JOptionPane();
						op.showMessageDialog(hf,"Company successfully Removed...!");
						
						cbbRCCompanyName.removeItem(cbbRCCompanyName.getSelectedItem());
					}
					else
					{
						JOptionPane op=new JOptionPane();
						op.showMessageDialog(hf,"Removing Failed...!");
					}
				}
			}
			else if(stockFlag>0)
			{
				String msg="There are "+stockFlag+" Items are available in current Stock.\nIts related all items also will be removed.\n Do you want to remove ?";
				int res=JOptionPane.showConfirmDialog(hf,msg,"Confirm",JOptionPane.YES_NO_OPTION);
				if(res==JOptionPane.YES_OPTION)
				{
					PreparedStatement pstSA=con.prepareStatement("Delete from STOCK where COMPANY=?");
					pstSA.setString(1,(String)cbbRCCompanyName.getSelectedItem());
					int sRes=pstSA.executeUpdate();
			
					PreparedStatement pst=con.prepareStatement("Delete from COMPANY where COMPANY_NAME=?");
					pst.setString(1,(String)cbbRCCompanyName.getSelectedItem());
					int r=pst.executeUpdate();
				
					if(r==1)
					{
						JOptionPane op=new JOptionPane();
						op.showMessageDialog(hf,"Company successfully Removed...!");
						
						cbbRCCompanyName.removeItem(cbbRCCompanyName.getSelectedItem());
					}
					else
					{
						JOptionPane op=new JOptionPane();
						op.showMessageDialog(hf,"Removing Failed...!");
					}
				}
			}
			else
			{
				PreparedStatement pst=con.prepareStatement("Delete from COMPANY where COMPANY_NAME=?");
					pst.setString(1,(String)cbbRCCompanyName.getSelectedItem());
					int r=pst.executeUpdate();
				
					if(r==1)
					{
						JOptionPane op=new JOptionPane();
						op.showMessageDialog(hf,"Company successfully Removed...!");
						
						cbbRCCompanyName.removeItem(cbbRCCompanyName.getSelectedItem());
					}
					else
					{
						JOptionPane op=new JOptionPane();
						op.showMessageDialog(hf,"Removing Failed...!");
					}
			}
			
	
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(hf,"Sorry !!! There are some error occured...!");
		}
		
		
	}
	
	public void fillUC()
	{
		String com=(String)cbbUCCompanyName.getSelectedItem();
		try
		{
			PreparedStatement pst=con.prepareStatement("Select * from COMPANY where COMPANY_NAME=?");
			pst.setString(1,com);
			ResultSet rs=pst.executeQuery();
			
			while(rs.next())
			{
				txtUCLicenceNo.setText(rs.getString(8));
				txtUCCompanyName.setText(rs.getString(1));
				txtUCDetails.setText(rs.getString(2));
				txtUCCity.setText(rs.getString(3));
				txtUCAddress.setText(rs.getString(4));
				txtUCContactPerson.setText(rs.getString(5));
				txtUCContactNo.setText(rs.getString(6));
				txtUCEmail.setText(rs.getString(7));
			}
			
			pst.close();
			
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(hf,"Sorry !!! There are some error occured...!");
		}
	}
	
	public void updateCompanyToDB()
	{
		String com=(String)cbbUCCompanyName.getSelectedItem();
		try
		{
			PreparedStatement pst=con.prepareStatement("Update COMPANY set LICENCE_NO=?,COMPANY_NAME=?,DETAIL=?,CITY=?,ADDRESS=?,CONTACT_PERSON=?,CONTACT_NO=?,EMAIL=? where COMPANY_NAME=?");
			pst.setString(1,txtUCLicenceNo.getText());
			pst.setString(2,txtUCCompanyName.getText());
			pst.setString(3,txtUCDetails.getText());
			pst.setString(4,txtUCCity.getText());
			pst.setString(5,txtUCAddress.getText());
			pst.setString(6,txtUCContactPerson.getText());
			pst.setString(7,txtUCContactNo.getText());
			pst.setString(8,txtUCEmail.getText());
			pst.setString(9,(String)cbbUCCompanyName.getSelectedItem());
			
			int result=pst.executeUpdate();
			
			PreparedStatement pstIA=con.prepareStatement("Update ITEM set COMPANY_NAME=? where COMPANY_NAME=?");
			pstIA.setString(1,txtUCCompanyName.getText());
			pstIA.setString(2,(String)cbbUCCompanyName.getSelectedItem());
			int resultAI=pstIA.executeUpdate();
			
			PreparedStatement pstSA=con.prepareStatement("Update STOCK set COMPANY=? where COMPANY=?");
			pstSA.setString(1,txtUCCompanyName.getText());
			pstSA.setString(2,(String)cbbUCCompanyName.getSelectedItem());
			int resultSA=pstSA.executeUpdate();
			
			
			
			if(result==0)
			{
				JOptionPane.showMessageDialog(hf,"Sorry!!!Some error occured.");
			}
			else
			{
				JOptionPane.showMessageDialog(hf,"Company successfully updated...!");
				miUpdateCompany.doClick();
			}
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(hf,"Sorry !!! There are some error occured...!");
		}
	}
	
	public void addClientToDB()
	{
		try
		{
			PreparedStatement pst=con.prepareStatement("Insert into CLIENT values (?,?,?,?,?)");
			pst.setString(5,txtACLName.getText());
			pst.setString(1,txtACLCity.getText());
			pst.setString(2,txtACLAddress.getText());
			pst.setString(3,txtACLContactNo.getText());
			pst.setString(4,txtACLEmail.getText());
			
			int result=pst.executeUpdate();
			
			if(result==0)
			{
				JOptionPane.showMessageDialog(hf,"Sorry!!!Some error occured.");
			}
			else
			{
				JOptionPane.showMessageDialog(hf,"Client successfully inserted...!");
				txtACLName.setText("");
				txtACLCity.setText("");
				txtACLAddress.setText("");
				txtACLContactNo.setText("");
				txtACLEmail.setText("");
			}
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(hf,"Sorry !!! There are some error occured...!");
		}
	}
	
	public void removeClientFromDB()
	{
		try
		{
			PreparedStatement pst=con.prepareStatement("Delete from CLIENT where CLIENT_NAME=?");
			pst.setString(1,(String)cbbRCLClient.getSelectedItem());
			int result=pst.executeUpdate();
			
			if(result==0)
			{
				JOptionPane.showMessageDialog(hf,"Sorry!!!Some error occured.");
			}
			else
			{
				JOptionPane.showMessageDialog(hf,"Client successfully removed...!");
				miRemoveClient.doClick();
			}
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(hf,"Sorry !!! There are some error occured...!");
		}
	}
	
	public void fillUCL()
	{
		String client=(String)cbbUCLClient.getSelectedItem();
		try
		{
			PreparedStatement pst=con.prepareStatement("Select * from CLIENT where CLIENT_NAME=?");
			pst.setString(1,client);
			
			ResultSet rs=pst.executeQuery();
			
			while(rs.next())
			{
				txtUCLName.setText(rs.getString(5));
				txtUCLCity.setText(rs.getString(1));
				txtUCLAddress.setText(rs.getString(2));
				txtUCLContactNo.setText(rs.getString(3));
				txtUCLEmail.setText(rs.getString(4));
			}
			
			pst.close();
			
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(hf,"Sorry !!! There are some error occured...!");
		}
	}
	
	public void updateClientToDB()
	{
		String client=(String)cbbUCLClient.getSelectedItem();
		try
		{
			PreparedStatement pst=con.prepareStatement("Update CLIENT set CLIENT_NAME=?,CITY=?,ADDRESS=?,CONTACT_NO=?,EMAIL=? where CLIENT_NAME=?");
			
			pst.setString(1,txtUCLName.getText());
			pst.setString(2,txtUCLCity.getText());
			pst.setString(3,txtUCLAddress.getText());
			pst.setString(4,txtUCLContactNo.getText());
			pst.setString(5,txtUCLEmail.getText());
			pst.setString(6,client);
			
			int result=pst.executeUpdate();
			
			if(result==0)
			{
				JOptionPane.showMessageDialog(hf,"Sorry!!!Some error occured.");
			}
			else
			{
				JOptionPane.showMessageDialog(hf,"Client successfully updated...!");
				miUpdateClient.doClick();
			}
		
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(hf,"Sorry !!! There are some error occured...!");
		}
	}	
	
	public void addStockToDB()
	{
		
		try
		{
			PreparedStatement pstCheck=con.prepareStatement("Select * from STOCK where ITEM_NAME=? and CATEGORY=? and COMPANY=?");
			pstCheck.setString(1,(String)cbbASItemName.getSelectedItem());
			pstCheck.setString(2,(String)cbbASCategory.getSelectedItem());
			pstCheck.setString(3,(String)cbbASCompanyName.getSelectedItem());
			ResultSet rsCheck=pstCheck.executeQuery();
			
			if(rsCheck.next())
			{
				JOptionPane.showMessageDialog(hf,"Item allready available in stock.\nPlease update stock...!");
			}
			else
			{
		
				int unit=0;
				int Qpackage=0;
				int unitInPackage=0;
				float packagePrice=0;
				float unitPrice=0;
				float txtValue=Float.parseFloat(txtASSPrice.getText());
				try
				{
					PreparedStatement pst=con.prepareStatement("Select UNIT_IN_PACKAGE from ITEM where ITEM_NAME=? and CATEGORY_NAME=? and COMPANY_NAME=?");
					pst.setString(1,(String)cbbASItemName.getSelectedItem());
					pst.setString(2,(String)cbbASCategory.getSelectedItem());
					pst.setString(3,(String)cbbASCompanyName.getSelectedItem());
					ResultSet rs=pst.executeQuery();
				
					while(rs.next())
					{
						unitInPackage=rs.getInt("UNIT_IN_PACKAGE");
					}
			
				}
				catch(Exception e)
				{
					JOptionPane.showMessageDialog(hf,"Sorry !!! There are some error occured...!");
				}
		
				if(rbASPackage.isSelected())
				{
					Qpackage=Integer.parseInt(txtASQuantity.getText());
					unit=Qpackage*unitInPackage;
				}
				if(rbASUnit.isSelected())
				{
					unit=Integer.parseInt(txtASQuantity.getText());	
					try{Qpackage=unit/unitInPackage;}catch(Exception e){Qpackage=0;}
				}
				if(rbASPPackage.isSelected())
				{
					packagePrice=txtValue;
					try{unitPrice=txtValue/unitInPackage;}catch(Exception e){unitPrice=0;}
				}
				if(rbASPUnit.isSelected())
				{
					unitPrice=txtValue;
					packagePrice=txtValue*unitInPackage;
				}
		
				try
				{	
					PreparedStatement pst=con.prepareStatement("Insert into STOCK values(?,?,?,?,?,?,?,?)");
					pst.setString(5,(String)cbbASItemName.getSelectedItem());
					pst.setString(1,(String)cbbASCategory.getSelectedItem());
					pst.setString(2,(String)cbbASCompanyName.getSelectedItem());
					pst.setString(3,txtASLocation.getText());
					pst.setInt(4,Qpackage);
					pst.setInt(6,unit);
					pst.setFloat(7,unitPrice);
					pst.setFloat(8,packagePrice);
					int result=pst.executeUpdate();
			
					if(result==0)
					{
						JOptionPane.showMessageDialog(hf,"Sorry some error occured...!");
					}
					else
					{
						JOptionPane.showMessageDialog(hf,"Stock successfully inserted...!");
						txtASLocation.setText("");
						txtASQuantity.setText("");
						txtASSPrice.setText("");
					}
			
			
				}	
				catch(Exception e)
				{	
					JOptionPane.showMessageDialog(hf,"Sorry !!! There are some error occured...!");
				}
			}
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(hf,"Sorry !!! There are some error occured...!");
		}
	}

	public void fillAS()
	{
		rbASPPackage.setEnabled(true);
		rbASPackage.setEnabled(true);
		try
		{
			PreparedStatement pst=con.prepareStatement("Select UNIT_PRICE,PACKAGE_PRICE,UNIT_IN_PACKAGE from ITEM where ITEM_NAME=? and CATEGORY_NAME=? and COMPANY_NAME=?");
			pst.setString(1,(String)cbbASItemName.getSelectedItem());
			pst.setString(2,(String)cbbASCategory.getSelectedItem());
			pst.setString(3,(String)cbbASCompanyName.getSelectedItem());
			
			ResultSet rs=pst.executeQuery();
			int flag=0;	
			while(rs.next())
			{
				
				if(!(rs.getInt("UNIT_PRICE")==0))
				{
					lblU.setText("Buying Price per unit : "+rs.getInt("UNIT_PRICE"));
					
					
				}
				if(!(rs.getInt("PACKAGE_PRICE")==0))
				{
					lblP.setText("Buying Price per package : "+rs.getInt("PACKAGE_PRICE"));
					flag++;
				}
				if(!(rs.getInt("UNIT_IN_PACKAGE")==0))
				{
					lblASUnitInPackage.setText("Unit in package : "+rs.getInt("UNIT_IN_PACKAGE"));
					flag++;
				}	
				if(flag==0)
				{
					rbASUnit.setSelected(true);
					rbASPackage.setEnabled(false);
					rbASPUnit.setSelected(true);
					rbASPPackage.setEnabled(false);
				}
				
			}
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(hf,"Sorry !!! There are some error occured...!");
		}
	}
	
	public void removeStockFromDB()
	{
		try
		{
			PreparedStatement pst=con.prepareStatement("Delete from STOCK where ITEM_NAME=? and CATEGORY=? and COMPANY=?");
			pst.setString(1,(String)cbbRSItemName.getSelectedItem());
			pst.setString(2,(String)cbbRSCategory.getSelectedItem());
			pst.setString(3,(String)cbbRSCompanyName.getSelectedItem());
			int result=pst.executeUpdate();
			
			if(result==0)
			{
				JOptionPane.showMessageDialog(hf,"Sorry some error occured...!");
			}
			else
			{
				JOptionPane.showMessageDialog(hf,"Stock successfully removed...!");
				miRemoveStock.doClick();
			}
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(hf,"Sorry !!! There are some error occured...!");
		}
	}
	
	public void fillUS()
	{
		try
		{
			PreparedStatement pst=con.prepareStatement("Select * from STOCK where ITEM_NAME=? and CATEGORY=? and COMPANY=?");
			pst.setString(1,(String)cbbUSItemName.getSelectedItem());
			pst.setString(2,(String)cbbUSCategory.getSelectedItem());
			pst.setString(3,(String)cbbUSCompany.getSelectedItem());
			
			ResultSet rs=pst.executeQuery();
			
			while(rs.next())
			{
				txtUSLocation.setText(rs.getString("LOCATION"));
				txtUSCStockInPackage.setText(""+rs.getInt("QUANTITY_IN_PACKAGE"));
				txtUSCStockInUnit.setText(""+rs.getInt("QUANTITY_IN_UNIT"));
				txtUSNewStockInPackage.setText(""+rs.getInt("QUANTITY_IN_PACKAGE"));
				txtUSNewStockInUnit.setText(""+rs.getInt("QUANTITY_IN_UNIT"));
				txtUSCUnitPrice.setText(""+rs.getInt("UNIT_SELLING_PRICE"));
				txtUSNUnitPrice.setText(""+rs.getInt("UNIT_SELLING_PRICE"));
				if(rs.getInt("QUANTITY_IN_PACKAGE")!=0)
				{
					txtUSCPackagePrice.setText(""+rs.getInt("PACKAGE_SELLING_PRICE"));
					txtUSNPackagePrice.setText(""+rs.getInt("PACKAGE_SELLING_PRICE"));
				}
				else
				{
					txtUSCPackagePrice.setEnabled(false);
					txtUSNPackagePrice.setEnabled(false);
				}
				
			}
			pst.close();
			
			PreparedStatement pstUnit=con.prepareStatement("Select UNIT_IN_PACKAGE from ITEM where ITEM_NAME=? and COMPANY_NAME=? and CATEGORY_NAME=?");
			
			pstUnit.setString(1,(String)cbbUSItemName.getSelectedItem());
			pstUnit.setString(2,(String)cbbUSCompany.getSelectedItem());
			pstUnit.setString(3,(String)cbbUSCategory.getSelectedItem());
			ResultSet rsUnit=pstUnit.executeQuery();
			while(rsUnit.next())
			{
				if(rsUnit.getInt("UNIT_IN_PACKAGE")==0)
				{
					lblUnit.setText("");
				}
				else
				{
					lblUnit.setText("Unit in package = "+rsUnit.getInt("UNIT_IN_PACKAGE"));
					
				}
			}
			
			pstUnit.close();

		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(hf,"Sorry !!! There are some error occured...!");
		}
	}
	
	public void updateStockToDB()
	{
		try
		{
			PreparedStatement pst=con.prepareStatement("Update STOCK set LOCATION=?,QUANTITY_IN_PACKAGE=?,QUANTITY_IN_UNIT=?,UNIT_SELLING_PRICE=?,PACKAGE_SELLING_PRICE=? where ITEM_NAME=? and CATEGORY=? and COMPANY=?");
			
			pst.setString(1,txtUSLocation.getText());
			pst.setInt(2,Integer.parseInt(txtUSNewStockInPackage.getText()));
			pst.setInt(3,Integer.parseInt(txtUSNewStockInUnit.getText()));
			pst.setInt(4,Integer.parseInt(txtUSNUnitPrice.getText()));;
			pst.setInt(5,Integer.parseInt(txtUSNPackagePrice.getText()));
			pst.setString(6,(String)cbbUSItemName.getSelectedItem());
			pst.setString(7,(String)cbbUSCategory.getSelectedItem());
			pst.setString(8,(String)cbbUSCompany.getSelectedItem());
			
			int result=pst.executeUpdate();
			
			if(result==0)
			{
				JOptionPane.showMessageDialog(hf,"Sorry some error occured...!");
			}
			else
			{
				JOptionPane.showMessageDialog(hf,"Stock successfully updated...!");
				miUpdateStock.doClick();
			}
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(hf,"Sorry !!! There are some error occured...!");
		}
	}
	
	public void fillCombo()
	{
		cbbSTCCategory.removeAllItems();
		cbbSTRCategory.removeAllItems();
		cbbRCCategory.removeAllItems();
		cbbUCCategory.removeAllItems();
		cbbAICategory.removeAllItems();
		cbbAICompany.removeAllItems();
		cbbRICategory.removeAllItems();
		cbbUISCategory.removeAllItems();
		cbbUICategory.removeAllItems();
		cbbUICompanyName.removeAllItems();
		cbbASCategory.removeAllItems();
		cbbRSCategory.removeAllItems();
		cbbUSCategory.removeAllItems();
		cbbRCCompanyName.removeAllItems();
		cbbUCCompanyName.removeAllItems();
		
		
		cbbSTCClientName.removeAllItems();		
		cbbRCLClient.removeAllItems();
		cbbUCLClient.removeAllItems();
		
		
		try
		{
			Statement st=con.createStatement();
			ResultSet rsCat=st.executeQuery("Select * from CATEGORY");
			ResultSet rsCom=st.executeQuery("Select COMPANY_NAME from COMPANY");
			ResultSet rsClient=st.executeQuery("Select CLIENT_NAME from CLIENT");
			
			ResultSet rsStockCategory=st.executeQuery("Select CATEGORY from STOCK group by CATEGORY");
			
			while(rsStockCategory.next())
			{
				cbbSTCCategory.addItem(rsStockCategory.getString("CATEGORY"));
				cbbSTRCategory.addItem(rsStockCategory.getString("CATEGORY"));	
				
				cbbRSCategory.addItem(rsStockCategory.getString("CATEGORY"));	
				cbbUSCategory.addItem(rsStockCategory.getString("CATEGORY"));	
				
			}
			
			while(rsCat.next())
			{
				
				cbbRCCategory.addItem(rsCat.getString("CATEGORY_NAME"));
				cbbUCCategory.addItem(rsCat.getString("CATEGORY_NAME"));
				cbbAICategory.addItem(rsCat.getString("CATEGORY_NAME"));
				cbbRICategory.addItem(rsCat.getString("CATEGORY_NAME"));
				cbbUISCategory.addItem(rsCat.getString("CATEGORY_NAME"));
				cbbUICategory.addItem(rsCat.getString("CATEGORY_NAME"));
				cbbASCategory.addItem(rsCat.getString("CATEGORY_NAME"));	
				
				
			}
			while(rsCom.next())
			{
				
				//cbbSTRCompany.addItem(rsCom.getString("COMPANY_NAME"));
				cbbAICompany.addItem(rsCom.getString("COMPANY_NAME"));
				cbbUICompanyName.addItem(rsCom.getString("COMPANY_NAME"));
				cbbRCCompanyName.addItem(rsCom.getString("COMPANY_NAME"));
				cbbUCCompanyName.addItem(rsCom.getString("COMPANY_NAME"));
			}
			
			while(rsClient.next())
			{
				cbbSTCClientName.addItem(rsClient.getString("CLIENT_NAME"));
				cbbRCLClient.addItem(rsClient.getString("CLIENT_NAME"));
				cbbUCLClient.addItem(rsClient.getString("CLIENT_NAME"));
			}
			
			rsCat.close();
			rsCom.close();
			rsClient.close();
			rsStockCategory.close();
			
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(hf,"Sorry !!! There are some error occured...!");
		}
	}
	
	public void createBill()
	{
		String billItem=(String)cbbSTRItemName.getSelectedItem();
		String billCategory=(String)cbbSTRCategory.getSelectedItem();
		String billCompany=(String)cbbSTRCompany.getSelectedItem();
		int billQuantity=Integer.parseInt(txtSTRQuantity.getText());
		String qttType="";
		int stockPrice=0;
		int totalAmount=0;
		String location="";
		int flag=0;
		
		PreparedStatement pstValueFromStock=null;
		ResultSet rsValueFromStock=null;
		
		if(rbSTRPackage.isSelected())
		{
			qttType="Package";
		}
		if(rbSTRUnit.isSelected())
		{
			qttType="Unit";
		}
		
		try
		{
			pstValueFromStock=con.prepareStatement("Select * from STOCK where ITEM_NAME=? and CATEGORY=? and COMPANY=?");
			pstValueFromStock.setString(1,billItem);
			pstValueFromStock.setString(2,billCategory);
			pstValueFromStock.setString(3,billCompany);
			rsValueFromStock=pstValueFromStock.executeQuery();
		
			rsValueFromStock.next();
			
					if(rbSTRPackage.isSelected())
					{
						stockPrice=rsValueFromStock.getInt("PACKAGE_SELLING_PRICE");
					}
					if(rbSTRUnit.isSelected() || rsValueFromStock.getInt("PACKAGE_SELLING_PRICE")==0)
					{
						stockPrice=rsValueFromStock.getInt("UNIT_SELLING_PRICE");
					}
					location=rsValueFromStock.getString("LOCATION");
					
			
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(hf,"Sorry !!! There are some error occured...!");
		}
		totalAmount=billQuantity*stockPrice;
		
		for(int r=0;r<modelRBill.getRowCount();r++)
		{
			if(((String)modelRBill.getValueAt(r,0)).equals((String)cbbSTRItemName.getSelectedItem()))
			{
				int q=Integer.parseInt(modelRBill.getValueAt(r,3).toString());
				q=q+billQuantity;
				
				try{
				if(rbSTRUnit.isSelected() || rsValueFromStock.getInt("QUANTITY_IN_PACKAGE")==0)
				{
					if(rsValueFromStock.getInt("QUANTITY_IN_UNIT")<q)
					{
						flag++;
						JOptionPane.showMessageDialog(hf,"Sorry !!! There are only "+rsValueFromStock.getInt("QUANTITY_IN_UNIT")+" unit available in the current stock...!");
						txtSTRQuantity.setText("");
						txtSTRQuantity.requestFocus();
						
					}
					else
					{
						flag++;
						modelRBill.setValueAt(q,r,3);
						//int p=Integer.parseInt((String)modelRBill.getValueAt(r,5));
						int ta=stockPrice*q;
						modelRBill.setValueAt(ta,r,6);
						
					}
				}
				else if(rbSTRPackage.isSelected())
				{
					if(rsValueFromStock.getInt("QUANTITY_IN_PACKAGE")==0 && rsValueFromStock.getInt("QUANTITY_IN_UNIT")>0)
					{
						flag++;
						JOptionPane.showMessageDialog(hf,"Sorry !!! The Stock is available in unit only...!");
						rbSTRUnit.setSelected(true);
						txtSTRQuantity.requestFocus();
					}
					else if(rsValueFromStock.getInt("QUANTITY_IN_PACKAGE")<q)
					{
						flag=flag+1;
						JOptionPane.showMessageDialog(hf,"There are only "+rsValueFromStock.getInt("QUANTITY_IN_PACKAGE")+" package available in the current stock...!");
						txtSTRQuantity.setText("");
						txtSTRQuantity.requestFocus();
						
					}
					else
					{
						modelRBill.setValueAt(q,r,3);
						//int p=Integer.parseInt((String)modelRBill.getValueAt(r,5));
						int ta=stockPrice*q;
						modelRBill.setValueAt(ta,r,6);
						flag++;
					}
					
				}
				else
				{
					modelRBill.setValueAt(q,r,3);
					//int p=Integer.parseInt((String)modelRBill.getValueAt(r,5));
					int ta=stockPrice*q;
					modelRBill.setValueAt(ta,r,6);
					flag++;
				}
				}
				catch(Exception e)
				{
					JOptionPane.showMessageDialog(hf,"Sorry !!! There are some error occured...!");
				}
				
			}
		}
		
		
		if(flag==0)
		{	
			try{
			if(rbSTRPackage.isSelected())
			{
				if(rsValueFromStock.getInt("QUANTITY_IN_PACKAGE")==0 && rsValueFromStock.getInt("QUANTITY_IN_UNIT")>0)
				{
					JOptionPane.showMessageDialog(hf,"Sorry !!! The Stock is available in only unit...!");
					txtSTRQuantity.requestFocus();
					rbSTRUnit.setSelected(true);
					qttType="Unit";
				
				}
				else if(billQuantity>rsValueFromStock.getInt("QUANTITY_IN_PACKAGE"))
				{
					JOptionPane.showMessageDialog(hf,"Sorry !!! There are only "+rsValueFromStock.getInt("QUANTITY_IN_PACKAGE")+" package is available in the current stock...");
					txtSTRQuantity.setText("");
					txtSTRQuantity.requestFocus();
				}
				else
				{
					modelRBill.addRow(new Object[]{billItem,billCategory,billCompany,billQuantity,qttType,stockPrice,totalAmount,location,"DELETE"});
				}
			}
			if(rbSTRUnit.isSelected() || rsValueFromStock.getInt("QUANTITY_IN_PACKAGE")==0)
			{
				if(billQuantity>rsValueFromStock.getInt("QUANTITY_IN_UNIT"))
				{
					JOptionPane.showMessageDialog(hf,"Sorry !!! There are only "+rsValueFromStock.getInt("QUANTITY_IN_UNIT")+" unit available in the current stock...!");
					txtSTRQuantity.setText("");
					txtSTRQuantity.requestFocus();
				}
				else
				{
					modelRBill.addRow(new Object[]{billItem,billCategory,billCompany,billQuantity,qttType,stockPrice,totalAmount,location,"DELETE"});
				}
			}
			}
			catch(Exception e)
			{
				JOptionPane.showMessageDialog(hf,"Sorry !!! There are some error occured...!");
			}
		
		}
		try
		{
			pstValueFromStock.close();
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(hf,"Sorry !!! There are some error occured...!");
		}
		
	}
	
	public void createCustomizedBill()
	{
		String billItem=(String)cbbSTCItemName.getSelectedItem();
		String billCategory=(String)cbbSTCCategory.getSelectedItem();
		String billCompany=(String)cbbSTCCompany.getSelectedItem();
		int billQuantity=Integer.parseInt(txtSTCQuantity.getText());
		String qttType="";
		int stockPrice=0;
		int totalAmount=0;
		String location="";
		int flag=0;
		
		PreparedStatement pstValueFromStock=null;
		ResultSet rsValueFromStock=null;
		
		if(rbSTCPackage.isSelected())
		{
			qttType="Package";
		}
		if(rbSTCUnit.isSelected())
		{
			qttType="Unit";
		}
		
		try
		{
			pstValueFromStock=con.prepareStatement("Select * from STOCK where ITEM_NAME=? and CATEGORY=? and COMPANY=?");
			pstValueFromStock.setString(1,billItem);
			pstValueFromStock.setString(2,billCategory);
			pstValueFromStock.setString(3,billCompany);
			rsValueFromStock=pstValueFromStock.executeQuery();
			
			rsValueFromStock.next();
			if(rbSTCPackage.isSelected() && rsValueFromStock.getInt("QUANTITY_IN_PACKAGE")==0)
			{
				JOptionPane.showMessageDialog(hf,"Note : The Quantity is in unit...");
				rbSTRUnit.setSelected(true);
			}
			
			
			
					if(rbSTCPackage.isSelected())
					{
						stockPrice=rsValueFromStock.getInt("PACKAGE_SELLING_PRICE");
					}
					if(rbSTCUnit.isSelected() || rsValueFromStock.getInt("PACKAGE_SELLING_PRICE")==0)
					{
						stockPrice=rsValueFromStock.getInt("UNIT_SELLING_PRICE");
					}
					location=rsValueFromStock.getString("LOCATION");
					
			
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(hf,"Sorry !!! There are some error occured...!");
		}
		totalAmount=billQuantity*stockPrice;
		
		for(int r=0;r<modelRBill.getRowCount();r++)
		{
			if(((String)modelRBill.getValueAt(r,0)).equals((String)cbbSTCItemName.getSelectedItem()))
			{
				int q=Integer.parseInt(modelRBill.getValueAt(r,3).toString());
				q=q+billQuantity;
				
				try{
				if(rbSTCUnit.isSelected() || rsValueFromStock.getInt("QUANTITY_IN_PACKAGE")==0)
				{
					if(rsValueFromStock.getInt("QUANTITY_IN_UNIT")<q)
					{
						JOptionPane.showMessageDialog(hf,"Sorry !!! There are only "+rsValueFromStock.getInt("QUANTITY_IN_UNIT")+" unit available in the current stock...!");
						txtSTCQuantity.setText("");
						txtSTCQuantity.requestFocus();
						flag++;
					}
					else
					{
						modelRBill.setValueAt(q,r,3);
						//int p=Integer.parseInt((String)modelRBill.getValueAt(r,5));
						int ta=stockPrice*q;
						modelRBill.setValueAt(ta,r,6);
						flag++;
					}
				}
				else if(rbSTCPackage.isSelected())
				{
					if(rsValueFromStock.getInt("QUANTITY_IN_PACKAGE")<q)
					{
						JOptionPane.showMessageDialog(hf,"There are only "+rsValueFromStock.getInt("QUANTITY_IN_PACKAGE")+" package available in the current stock...!");
						txtSTCQuantity.setText("");
						txtSTCQuantity.requestFocus();
						flag++;
					}
					else
					{
						modelRBill.setValueAt(q,r,3);
						//int p=Integer.parseInt((String)modelRBill.getValueAt(r,5));
						int ta=stockPrice*q;
						modelRBill.setValueAt(ta,r,6);
						flag++;
					}
					
				}
				else
				{
					modelRBill.setValueAt(q,r,3);
					//int p=Integer.parseInt((String)modelRBill.getValueAt(r,5));
					int ta=stockPrice*q;
					modelRBill.setValueAt(ta,r,6);
					flag++;
				}
				}
				catch(Exception e)
				{
					JOptionPane.showMessageDialog(hf,"Sorry !!! There are some error occured...!");
				}
				
			}
		}
		
		if(flag==0)
		{	
			try{
			if(rbSTCPackage.isSelected())
			{
				if(billQuantity>rsValueFromStock.getInt("QUANTITY_IN_PACKAGE"))
				{
					JOptionPane.showMessageDialog(hf,"Sorry !!! There are only "+rsValueFromStock.getInt("QUANTITY_IN_PACKAGE")+" package is available in the current stock...");
					txtSTCQuantity.setText("");
					txtSTCQuantity.requestFocus();
				}
				else
				{
					modelRBill.addRow(new Object[]{billItem,billCategory,billCompany,billQuantity,qttType,stockPrice,totalAmount,location,"DELETE"});
				}
			}
			if(rbSTCUnit.isSelected() || rsValueFromStock.getInt("QUANTITY_IN_PACKAGE")==0)
			{
				if(billQuantity>rsValueFromStock.getInt("QUANTITY_IN_UNIT"))
				{
					JOptionPane.showMessageDialog(hf,"Sorry !!! There are only "+rsValueFromStock.getInt("QUANTITY_IN_UNIT")+" unit available in the current stock...!");
					txtSTCQuantity.setText("");
					txtSTCQuantity.requestFocus();
				}
				else
				{
					modelRBill.addRow(new Object[]{billItem,billCategory,billCompany,billQuantity,qttType,stockPrice,totalAmount,location,"DELETE"});
				}
			}
			}
			catch(Exception e)
			{
				JOptionPane.showMessageDialog(hf,"Sorry !!! There are some error occured...!");
			}
		
		}
		try
		{
			pstValueFromStock.close();
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(hf,"Sorry !!! There are some error occured...!");
		}
		
	}
	
	public void retailerSalesReport()
	{
		panelRetailerSales=new JPanel();
		panelRetailerSales.setVisible(false);
		panelRetailerSales.setLayout(null);
		panelRetailerSales.setBounds(100,150,1150,500);		
		panelRetailerSales.setBorder(BorderFactory.createTitledBorder("Retailer Sales"));
		
		JLabel lblSearch=new JLabel("Search here : ");
		lblSearch.setFont(f);
		txtRSSearch=new JTextField();
		txtRSSearch.setFont(f);
		
		txtRSSearch.setBounds(400,30,700,30);
		lblSearch.setBounds(250,30,120,30);
		
		panelRetailerSales.add(lblSearch);
		panelRetailerSales.add(txtRSSearch);
		
		txtRSSearch.addKeyListener(new KeyAdapter(){
			public void keyReleased(KeyEvent ke)
			{
				modelRetailerSales.setRowCount(0);
			
				try
				{
					PreparedStatement pst=con.prepareStatement("Select * from BILL where BILL_NO like '"+txtRSSearch.getText()+"%' or BILL_DATE like '"+txtRSSearch.getText()+"%' or CUSTOMER_NAME like '"+txtRSSearch.getText()+"%' or ITEM_NAME like '"+txtRSSearch.getText()+"%' or CATEGORY_NAME like '"+txtRSSearch.getText()+"%' or COMPANY_NAME like '"+txtRSSearch.getText()+"%'");
					ResultSet rs=pst.executeQuery();
						
					while(rs.next())
					{
						modelRetailerSales.addRow(new Object[]{rs.getInt("BILL_NO"),rs.getDate("BILL_DATE"),rs.getString("CUSTOMER_NAME"),rs.getString("CUSTOMER_CITY"),rs.getInt("CUSTOMER_AGE"),rs.getString("CUSTOMER_CONTACT"),rs.getString("ITEM_NAME"),rs.getString("CATEGORY_NAME"),rs.getString("COMPANY_NAME"),rs.getInt("QUANTITY"),rs.getString("QUANTITY_TYPE"),rs.getInt("PRICE"),rs.getInt("TOTAL_AMOUNT"),rs.getString("LOCATION")});
					}
					pst.close();
				}
				catch(Exception e)
				{
					JOptionPane.showMessageDialog(hf,"Sorry !!! There are some error occured...!");
				}
			}
		});
		
		
		btnRetailerSalesPrint=new JButton("Print");
		btnRetailerSalesPrint.addActionListener(this);
		
		btnRetailerSalesPrint.setBounds(500,460,100,30);
		
		panelRetailerSales.add(btnRetailerSalesPrint);
		retailerSalesReportDB();
		hf.add(panelRetailerSales);
	}
	
	public void retailerSalesReportDB()
	{
		modelRetailerSales=new DefaultTableModel();
		tableRetailerSales=new JTable(modelRetailerSales);
		spRetailerSales=new JScrollPane(tableRetailerSales);
		tableRetailerSales.setRowHeight(24);
		tableRetailerSales.setEnabled(false);
		
		modelRetailerSales.addColumn("BILL NO");
		modelRetailerSales.addColumn("BILL_DATE");
		modelRetailerSales.addColumn("CUSTOMER NAME");
		modelRetailerSales.addColumn("CUSTOMER CITY");
		modelRetailerSales.addColumn("CUSTOMER AGE");
		modelRetailerSales.addColumn("CUTOMER CONTACT");
		modelRetailerSales.addColumn("ITEM NAME");
		modelRetailerSales.addColumn("CATEGORY");
		modelRetailerSales.addColumn("COMPANY");
		modelRetailerSales.addColumn("QUANTITY");
		modelRetailerSales.addColumn("UNIT/PACKAGE");
		modelRetailerSales.addColumn("PRICE");
		modelRetailerSales.addColumn("TOTAL AMOUNT");
		
		try
		{
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery("Select * from BILL");
			
			while(rs.next())
			{
				modelRetailerSales.addRow(new Object[]{rs.getInt("BILL_NO"),rs.getDate("BILL_DATE"),rs.getString("CUSTOMER_NAME"),rs.getString("CUSTOMER_CITY"),rs.getInt("CUSTOMER_AGE"),rs.getString("CUSTOMER_CONTACT"),rs.getString("ITEM_NAME"),rs.getString("CATEGORY_NAME"),rs.getString("COMPANY_NAME"),rs.getInt("QUANTITY"),rs.getString("QUANTITY_TYPE"),rs.getInt("PRICE"),rs.getInt("TOTAL_AMOUNT"),rs.getString("LOCATION")});
				
			}
			st.close();
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(hf,"Sorry !!! There are some error occured...!");
		}
		
		spRetailerSales.setBounds(50,100,1050,350);
		panelRetailerSales.add(spRetailerSales);
	}

	public void customizedSalesReport()
	{
		panelCustomizedSales=new JPanel();
		panelCustomizedSales.setVisible(false);
		panelCustomizedSales.setLayout(null);
		panelCustomizedSales.setBounds(100,150,1150,500);		
		panelCustomizedSales.setBorder(BorderFactory.createTitledBorder("Customized Sales Report"));
		
		JLabel lblSearch=new JLabel("Search here : ");
		lblSearch.setFont(f);
		txtCSSearch=new JTextField();
		txtCSSearch.setFont(f);
		
		txtCSSearch.setBounds(400,30,700,30);
		lblSearch.setBounds(250,30,120,30);
		
		panelCustomizedSales.add(lblSearch);
		panelCustomizedSales.add(txtCSSearch);
		
		txtCSSearch.addKeyListener(new KeyAdapter(){
			public void keyReleased(KeyEvent ke)
			{
				modelCutomizedSales.setRowCount(0);
			
				try
				{
					PreparedStatement pst=con.prepareStatement("Select * from CUSTOMIZE_BILL where BILL_NO like '"+txtCSSearch.getText()+"%' or BILL_DATE like '"+txtCSSearch.getText()+"%' or CUSTOMER_NAME like '"+txtCSSearch.getText()+"%' or ITEM_NAME like '"+txtCSSearch.getText()+"%' or CATEGORY_NAME like '"+txtCSSearch.getText()+"%' or COMPANY_NAME like '"+txtCSSearch.getText()+"%'");
					ResultSet rs=pst.executeQuery();
						
					while(rs.next())
					{
						modelCutomizedSales.addRow(new Object[]{rs.getInt("BILL_NO"),rs.getDate("BILL_DATE"),rs.getString("CUSTOMER_NAME"),rs.getString("CUSTOMER_CITY"),rs.getString("ITEM_NAME"),rs.getString("CATEGORY_NAME"),rs.getString("COMPANY_NAME"),rs.getInt("QUANTITY"),rs.getString("QUANTITY_TYPE"),rs.getInt("PRICE"),rs.getInt("TOTAL_AMOUNT"),rs.getString("LOCATION")});
					}
					pst.close();
				}
				catch(Exception e)
				{
					JOptionPane.showMessageDialog(hf,"Sorry !!! There are some error occured...!");
				}
			}
		});
		
		
		btnCustomizedSalesPrint=new JButton("Print");
		btnCustomizedSalesPrint.addActionListener(this);
		
		btnCustomizedSalesPrint.setBounds(500,460,100,30);
		
		panelCustomizedSales.add(btnCustomizedSalesPrint);
		customizedSalesReportDB();
		hf.add(panelCustomizedSales);
	}
	
	public void customizedSalesReportDB()
	{
		modelCutomizedSales=new DefaultTableModel();
		tableCustomizedSales=new JTable(modelCutomizedSales);
		spCustomizedSales=new JScrollPane(tableCustomizedSales);
		tableCustomizedSales.setRowHeight(24);
		tableCustomizedSales.setEnabled(false);
		
		modelCutomizedSales.addColumn("BILL NO");
		modelCutomizedSales.addColumn("BILL_DATE");
		modelCutomizedSales.addColumn("CUSTOMER NAME");
		modelCutomizedSales.addColumn("CUSTOMER CITY");
		modelCutomizedSales.addColumn("ITEM NAME");
		modelCutomizedSales.addColumn("CATEGORY");
		modelCutomizedSales.addColumn("COMPANY");
		modelCutomizedSales.addColumn("QUANTITY");
		modelCutomizedSales.addColumn("UNIT/PACKAGE");
		modelCutomizedSales.addColumn("PRICE");
		modelCutomizedSales.addColumn("TOTAL AMOUNT");
		
		try
		{
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery("Select * from CUSTOMIZE_BILL");
			
			while(rs.next())
			{
				modelCutomizedSales.addRow(new Object[]{rs.getInt("BILL_NO"),rs.getDate("BILL_DATE"),rs.getString("CUSTOMER_NAME"),rs.getString("CUSTOMER_CITY"),rs.getString("ITEM_NAME"),rs.getString("CATEGORY_NAME"),rs.getString("COMPANY_NAME"),rs.getInt("QUANTITY"),rs.getString("QUANTITY_TYPE"),rs.getInt("PRICE"),rs.getInt("TOTAL_AMOUNT"),rs.getString("LOCATION")});
				
			}
			st.close();
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(hf,"Sorry !!! There are some error occured...!");
		}
		
		spCustomizedSales.setBounds(50,100,1050,350);
		panelCustomizedSales.add(spCustomizedSales);
	}
	
	public void actionPerformed(ActionEvent ae)
	{
		if(ae.getSource()==miHome)
		{
			setAllFalse();
			gotoHome();
			
		}
		
		if(ae.getSource()==miLogout)
		{
			MMSS m=new MMSS();
			hf.dispose();
		}
		
		if(ae.getSource()==miExit)
		{
			System.exit(0);
		}
		
		if(ae.getSource()==miChangePassword)
		{
			setAllFalse();
			panelChangePassword.setVisible(true);
		}
		
		if(ae.getSource()==btnChangePasswordCancel)
		{
			setAllFalse();
			gotoHome();
		}
		
		if(ae.getSource()==btnChangePassword)
		{
				if(pfOldPassword.getText().equals("") || pfNewPassword.getText().equals("") || pfConfirmPassword.getText().equals(""))
				{
					JOptionPane.showMessageDialog(hf,"Please fill the value properly...!");
				}
				else
				{
					String oldDBPassword;
					try
					{
						PreparedStatement pst=con.prepareStatement("Select PASSWORD from VALID_USERS");
						ResultSet rs=pst.executeQuery();
						rs.next();
						if(!(pfOldPassword.getText().equals(rs.getString("PASSWORD"))))
						{
							JOptionPane.showMessageDialog(hf,"The old password is wrong...!");
						}
						else  if(!(pfNewPassword.getText().equals(pfConfirmPassword.getText())))
						{
							JOptionPane.showMessageDialog(hf,"Confirm password does not match...!");
						}
						else
						{
							PreparedStatement pstUpdate=con.prepareStatement("Update VALID_USERS set PASSWORD=?");
							pstUpdate.setString(1,pfConfirmPassword.getText());
							int res=pstUpdate.executeUpdate();
							
							if(res>0)
							{
								JOptionPane.showMessageDialog(hf,"Password successfully changed...!");
								miLogout.doClick();
							}
						}
						
						
					}
					catch(Exception e)
					{
						JOptionPane.showMessageDialog(hf,"Sorry !!! There are some error occured...!");
					}
				}
				
				
		}
			
		if(ae.getSource()==miToCustomized)
		{	
			setAllFalse();
			fillCombo();
			panelSellCustoized.setVisible(true);
			txtSTCQuantity.setText("");
			rbSTCPackage.setSelected(false);
			rbSTCUnit.setSelected(false);
			panelCCart.add(spRBill);
			//setBounds(20,150,1100,340);
			//modelRBill.setBounds()
			modelRBill.setRowCount(0);
			cbbSTCClientName.setEnabled(true);
			cbbSTCCity.setEnabled(true);
			panelCCart.add(lblRCGrandTotal);
			panelCCart.add(lblRCAmount);
			lblRCAmount.setText("");
			
			
		}
		
		if(ae.getSource()==btnCustomizerAddToCart)
		{
			if(txtSTCQuantity.getText().equals(""))
			{
				JOptionPane.showMessageDialog(hf,"Please provide quantity...!");
			}
			else if(!(rbSTCPackage.isSelected() || rbSTCUnit.isSelected()))
			{
				JOptionPane.showMessageDialog(hf,"Please select quantity type...!");
			}
			else
			{
				cbbSTCClientName.setEnabled(false);
				cbbSTCCity.setEnabled(false);
				createCustomizedBill();
				int granTotal=0;
				for(int i=0;i<modelRBill.getRowCount();i++)
				{
					int a=Integer.parseInt(modelRBill.getValueAt(i,6).toString());
					granTotal=granTotal+a;
					
				}
				lblRCAmount.setText(""+granTotal);
			}
		}
		
		if(ae.getSource()==btnCancelCOrder)
		{
			setAllFalse();
			gotoHome();
		}
		
		if(ae.getSource()==btnSubmitCOrder)
		{
			int packageQuantity=0;
			int unitQuantity=0;
			int flag=0;
			PreparedStatement pstUpdatePackage=null;
			PreparedStatement pstUpdateUnit=null;
			ResultSet getuip=null;
			try
			{
				//con.setAutoCommit(false);
				for(int r=0;r<modelRBill.getRowCount();r++)
				{
					
						PreparedStatement pst=con.prepareStatement("Insert into CUSTOMIZE_BILL (BILL_DATE,CUSTOMER_NAME,CUSTOMER_CITY,ITEM_NAME,CATEGORY_NAME,COMPANY_NAME,QUANTITY,QUANTITY_TYPE,PRICE,TOTAL_AMOUNT,LOCATION) values (?,?,?,?,?,?,?,?,?,?,?)");
						pst.setDate(1,new java.sql.Date(new java.util.Date().getTime()));
						pst.setString(2,(String)cbbSTCClientName.getSelectedItem());
						pst.setString(3,(String)cbbSTCCity.getSelectedItem());
						pst.setString(4,modelRBill.getValueAt(r,0).toString());
						pst.setString(5,modelRBill.getValueAt(r,1).toString());
						pst.setString(6,modelRBill.getValueAt(r,2).toString());
						pst.setInt(7,Integer.parseInt(modelRBill.getValueAt(r,3).toString()));
						pst.setString(8,modelRBill.getValueAt(r,4).toString());
						pst.setInt(9,Integer.parseInt(modelRBill.getValueAt(r,5).toString()));
						pst.setInt(10,Integer.parseInt(modelRBill.getValueAt(r,6).toString()));
						pst.setString(11,modelRBill.getValueAt(r,7).toString());
						int res=pst.executeUpdate();
						
						if(res>0)
						{
							
							flag++;
						}
						
						try
						{
							PreparedStatement pstUIP=con.prepareStatement("Select UNIT_IN_PACKAGE from ITEM where ITEM_NAME=? and CATEGORY_NAME=? and COMPANY_NAME=?");
							pstUIP.setString(1,modelRBill.getValueAt(r,0).toString());
							pstUIP.setString(2,modelRBill.getValueAt(r,1).toString());
							pstUIP.setString(3,modelRBill.getValueAt(r,2).toString());
							getuip=pstUIP.executeQuery();
							getuip.next();
						}
						catch(Exception e)
						{
							JOptionPane.showMessageDialog(hf,"Sorry !!! There are some error occured...!");
						}
						
						try
						{
							PreparedStatement pstGetQtt=con.prepareStatement("Select * from STOCK where ITEM_NAME=? and CATEGORY=? and COMPANY=?");
							pstGetQtt.setString(1,modelRBill.getValueAt(r,0).toString());
							pstGetQtt.setString(2,modelRBill.getValueAt(r,1).toString());
							pstGetQtt.setString(3,modelRBill.getValueAt(r,2).toString());
							ResultSet rsGetQtt=pstGetQtt.executeQuery();
							while(rsGetQtt.next())
							{
							packageQuantity=rsGetQtt.getInt("QUANTITY_IN_PACKAGE");
							unitQuantity=rsGetQtt.getInt("QUANTITY_IN_UNIT");
							}
							pstGetQtt.close();
						}
						catch(Exception e)
						{
							JOptionPane.showMessageDialog(hf,"Sorry !!! There are some error occured...!");
						}
						
						if((modelRBill.getValueAt(r,4).toString()).equals("Package") && getuip.getInt("UNIT_IN_PACKAGE")>0)
						{
							int orderedQuantity=Integer.parseInt(modelRBill.getValueAt(r,3).toString());
							int remailPackageQtt=packageQuantity-orderedQuantity;
							int uq=orderedQuantity*(getuip.getInt("UNIT_IN_PACKAGE"));
							int remailUnitQtt=unitQuantity-uq;
							pstUpdatePackage=con.prepareStatement("Update STOCK set QUANTITY_IN_PACKAGE=?,QUANTITY_IN_UNIT=? where ITEM_NAME=? and CATEGORY=? and COMPANY=?");
							pstUpdatePackage.setInt(1,remailPackageQtt);
							pstUpdatePackage.setInt(2,remailUnitQtt);
							pstUpdatePackage.setString(3,modelRBill.getValueAt(r,0).toString());;
							pstUpdatePackage.setString(4,modelRBill.getValueAt(r,1).toString());
							pstUpdatePackage.setString(5,modelRBill.getValueAt(r,2).toString());
							int resPackage=pstUpdatePackage.executeUpdate();
							
						}
						if((modelRBill.getValueAt(r,4).toString()).equals("Unit"))
						{
							int orderedQuantity=Integer.parseInt(modelRBill.getValueAt(r,3).toString());
							int remailPackageQtt=unitQuantity-orderedQuantity;
							int rp;
							try{rp=orderedQuantity/(getuip.getInt("UNIT_IN_PACKAGE"));}catch(Exception e){ rp=0;}
							int up=packageQuantity-rp;
							
							pstUpdateUnit=con.prepareStatement("Update STOCK set QUANTITY_IN_UNIT=?,QUANTITY_IN_PACKAGE=? where ITEM_NAME=? and CATEGORY=? and COMPANY=?");
							pstUpdateUnit.setInt(1,remailPackageQtt);
							pstUpdateUnit.setInt(2,up);
							pstUpdateUnit.setString(3,modelRBill.getValueAt(r,0).toString());
							pstUpdateUnit.setString(4,modelRBill.getValueAt(r,1).toString());
							pstUpdateUnit.setString(5,modelRBill.getValueAt(r,2).toString());
							int resUnit=pstUpdateUnit.executeUpdate();
							
						}
						pst.close();
				}
				if(flag>0)
				{
					JOptionPane.showMessageDialog(hf,"Order successfully...!");
					
					
					miToCustomized.doClick();
				}
				
			}
			catch(Exception e)
			{
				JOptionPane.showMessageDialog(hf,"Sorry !!! There are some error occured...!");
			}
		}
		
		if(ae.getSource()==miToRetailer)
		{
			setAllFalse();
			fillCombo();
			panelSellRetailer.setVisible(true);
			panelRCart.setVisible(true);
			panelRCart.add(spRBill);
			txtSTRCustomerName.setText("");
			txtSTRCustomerName.requestFocus();
			txtSTRCity.setText("");
			txtSTRAge.setText("");
			txtSTRContactNo.setText("");
			txtSTRQuantity.setText("");
			rbSTRPackage.setSelected(false);
			rbSTRUnit.setSelected(false);
			if(modelRBill.getRowCount()>0)
			{
				modelRBill.setRowCount(0);
			}
			txtSTRCustomerName.setEnabled(true);
			txtSTRCity.setEnabled(true);
			txtSTRAge.setEnabled(true);
			txtSTRContactNo.setEnabled(true);
			panelRCart.add(lblRCGrandTotal);
			panelRCart.add(lblRCAmount);
			lblRCAmount.setText("");
		}
		
		if(ae.getSource()==btnRetailerAddToCart)
		{
			if(txtSTRCustomerName.getText().equals("") || txtSTRCity.getText().equals("") || txtSTRAge.getText().equals("") || txtSTRContactNo.getText().equals("") || txtSTRQuantity.getText().equals(""))
			{
				JOptionPane.showMessageDialog(hf,"Please fill the value properly...!");
			}
			else if(!(rbSTRPackage.isSelected() || rbSTRUnit.isSelected()))
			{
				JOptionPane.showMessageDialog(hf,"Please fill the value  properly...!");
			}
			else if(cbbSTRItemName.getSelectedItem()==null)
			{
				JOptionPane.showMessageDialog(hf,"No item selected to sell...!");
			}
			else if(Integer.parseInt(txtSTRAge.getText())<18)
			{
				JOptionPane.showMessageDialog(hf,"Invalid Age...!");
			}
			else if(txtSTRContactNo.getText().length()<10)
			{
				JOptionPane.showMessageDialog(hf,"Please enter valid mobile no...!");
			}
			else
			{
				txtSTRCustomerName.setEnabled(false);
				txtSTRCity.setEnabled(false);
				txtSTRAge.setEnabled(false);
				txtSTRContactNo.setEnabled(false);
				createBill();
				int granTotal=0;
				for(int i=0;i<modelRBill.getRowCount();i++)
				{
					int a=Integer.parseInt(modelRBill.getValueAt(i,6).toString());
					granTotal=granTotal+a;
					
				}
				lblRCAmount.setText(""+granTotal);
			}	
				
		}
		
		if(ae.getSource()==btnCancelOrder)
		{
			setAllFalse();
			gotoHome();
		
			
		}
		
		if(ae.getSource()==btnSubmitOrder)
		{
			int packageQuantity=0;
			int unitQuantity=0;
			int flag=0;
			PreparedStatement pstUpdatePackage=null;
			PreparedStatement pstUpdateUnit=null;
			ResultSet getuip=null;
			
			try
			{
				//con.setAutoCommit(false);
				for(int r=0;r<modelRBill.getRowCount();r++)
				{
					
						PreparedStatement pst=con.prepareStatement("Insert into BILL (BILL_DATE,CUSTOMER_NAME,CUSTOMER_CITY,CUSTOMER_AGE,CUSTOMER_CONTACT,ITEM_NAME,CATEGORY_NAME,COMPANY_NAME,QUANTITY,QUANTITY_TYPE,PRICE,TOTAL_AMOUNT,LOCATION) values (?,?,?,?,?,?,?,?,?,?,?,?,?)");
						pst.setDate(1,new java.sql.Date(new java.util.Date().getTime()));
						pst.setString(2,txtSTRCustomerName.getText());
						pst.setString(3,txtSTRCity.getText());
						pst.setInt(4,Integer.parseInt(txtSTRAge.getText()));
						pst.setString(5,txtSTRContactNo.getText());
						pst.setString(6,modelRBill.getValueAt(r,0).toString());
						pst.setString(7,modelRBill.getValueAt(r,1).toString());
						pst.setString(8,modelRBill.getValueAt(r,2).toString());
						pst.setInt(9,Integer.parseInt(modelRBill.getValueAt(r,3).toString()));
						pst.setString(10,modelRBill.getValueAt(r,4).toString());
						pst.setInt(11,Integer.parseInt(modelRBill.getValueAt(r,5).toString()));
						pst.setInt(12,Integer.parseInt(modelRBill.getValueAt(r,6).toString()));
						pst.setString(13,modelRBill.getValueAt(r,7).toString());
						int res=pst.executeUpdate();
						
						if(res>0)
						{
							
							flag++;
						}
						
						try
						{
							PreparedStatement pstUIP=con.prepareStatement("Select * from ITEM where ITEM_NAME=? and CATEGORY_NAME=? and COMPANY_NAME=?");
							pstUIP.setString(1,modelRBill.getValueAt(r,0).toString());
							pstUIP.setString(2,modelRBill.getValueAt(r,1).toString());
							pstUIP.setString(3,modelRBill.getValueAt(r,2).toString());
							getuip=pstUIP.executeQuery();
							getuip.next();
						}
						catch(Exception e)
						{
							//JOptionPane.showMessageDialog(hf,"Sorry !!! There are some error occured...!");
							System.out.println(e);
						}
						
						try
						{
							PreparedStatement pstGetQtt=con.prepareStatement("Select * from STOCK where ITEM_NAME=? and CATEGORY=? and COMPANY=?");
							pstGetQtt.setString(1,modelRBill.getValueAt(r,0).toString());
							pstGetQtt.setString(2,modelRBill.getValueAt(r,1).toString());
							pstGetQtt.setString(3,modelRBill.getValueAt(r,2).toString());
							ResultSet rsGetQtt=pstGetQtt.executeQuery();
							while(rsGetQtt.next())
							{
							packageQuantity=rsGetQtt.getInt("QUANTITY_IN_PACKAGE");
							unitQuantity=rsGetQtt.getInt("QUANTITY_IN_UNIT");
							}
							pstGetQtt.close();
						}
						catch(Exception e)
						{
							JOptionPane.showMessageDialog(hf,"Sorry !!! There are some error occured...!");
						}
						
						if((modelRBill.getValueAt(r,4).toString()).equals("Package"))
						{
							int orderedQuantity=Integer.parseInt(modelRBill.getValueAt(r,3).toString());
							int remailPackageQtt=packageQuantity-orderedQuantity;
							int uq=orderedQuantity*(getuip.getInt("UNIT_IN_PACKAGE"));
							int remailUnitQtt=unitQuantity-uq;
							
							pstUpdatePackage=con.prepareStatement("Update STOCK set QUANTITY_IN_PACKAGE=?,QUANTITY_IN_UNIT=? where ITEM_NAME=? and CATEGORY=? and COMPANY=?");
							pstUpdatePackage.setInt(1,remailPackageQtt);
							pstUpdatePackage.setInt(2,remailUnitQtt);
							pstUpdatePackage.setString(3,modelRBill.getValueAt(r,0).toString());;
							pstUpdatePackage.setString(4,modelRBill.getValueAt(r,1).toString());
							pstUpdatePackage.setString(5,modelRBill.getValueAt(r,2).toString());
							int resPackage=pstUpdatePackage.executeUpdate();
							
						}
						if((modelRBill.getValueAt(r,4).toString()).equals("Unit"))
						{
							int orderedQuantity=Integer.parseInt(modelRBill.getValueAt(r,3).toString());
							int remailPackageQtt=unitQuantity-orderedQuantity;
							int rp;
							try{rp=orderedQuantity/(getuip.getInt("UNIT_IN_PACKAGE"));}catch(Exception e){ rp=0;}
							int up=packageQuantity-rp;
							pstUpdateUnit=con.prepareStatement("Update STOCK set QUANTITY_IN_UNIT=?,QUANTITY_IN_PACKAGE=? where ITEM_NAME=? and CATEGORY=? and COMPANY=?");
							pstUpdateUnit.setInt(1,remailPackageQtt);
							pstUpdateUnit.setInt(2,up);
							pstUpdateUnit.setString(3,modelRBill.getValueAt(r,0).toString());
							pstUpdateUnit.setString(4,modelRBill.getValueAt(r,1).toString());
							pstUpdateUnit.setString(5,modelRBill.getValueAt(r,2).toString());
							int resUnit=pstUpdateUnit.executeUpdate();
							
						}
						pst.close();
				}
				if(flag>0)
				{
					JOptionPane.showMessageDialog(hf,"Order successfully...!");
					
					
					miToRetailer.doClick();
				}
				
			}
			catch(Exception e)
			{
				//JOptionPane.showMessageDialog(hf,"Sorry !!! There are some error occured...!");
				System.out.println(e);
			}
		}
		
		if(ae.getSource()==btnCustomizerAddToCart)
		{
			
		}
			
		if(ae.getSource()==miViewCategory)
		{
			spViewCategory.setVisible(false);
			spViewCategory=null;
			setAllFalse();
			panelViewCategory.setVisible(true);
			viewCategoryDB();
			
		}
				
		if(ae.getSource()==miAddCategory)
		{
			setAllFalse();
			panelAddCategory.setVisible(true);
			txtACCategory.requestFocus();
			panelAddCategory.add(spViewCategory);
			
		}
		
		if(ae.getSource()==btnAddCategoryCancel)
		{
			setAllFalse();
			gotoHome();
			txtACCategory.setText(null);
		}
		
		if(ae.getSource()==btnAddToCategory)
		{
			addCategoryTODB();
		}
		
		if(ae.getSource()==miRemoveCategory)
		{
			fillCombo();
			setAllFalse();
			panelRemoveCategory.setVisible(true);
			panelRemoveCategory.add(spViewCategory);
		}
		
		if(ae.getSource()==btnRemoveCategoryCancel)
		{
			setAllFalse();
			gotoHome();
		}
		
		if(ae.getSource()==btnRemoveCategory)
		{
			String msg="Are you sure you want to remove "+(String)cbbRCCategory.getSelectedItem();
			int res=JOptionPane.showConfirmDialog(hf,msg,"Confirm",JOptionPane.YES_NO_OPTION);
			
			if(res==JOptionPane.YES_OPTION)
			{
				removeCategoryFromDB();		
			}
		}
		
		if(ae.getSource()==miUpdateCategory)
		{
			setAllFalse();
			fillCombo();
			panelUpdateCategory.setVisible(true);
			panelUpdateCategory.add(spViewCategory);
		}
		
		if(ae.getSource()==btnUpdateCategoryCancel)
		{
			setAllFalse();
			gotoHome();
		}
		
		if(ae.getSource()==btnUpdateCategory)
		{
			if(txtUCCategory.getText().equals(""))
			{
				JOptionPane.showMessageDialog(hf,"Please provide category name !!!");
			}
			else
			{
			String msg="Are you sure you want to update "+(String)cbbUCCategory.getSelectedItem();
			int res=JOptionPane.showConfirmDialog(hf,msg,"Confirm",JOptionPane.YES_NO_OPTION);
			
			if(res==JOptionPane.YES_OPTION)
			{
				updateCategoryToDB();
			}
			}
			
		}
		
		if(ae.getSource()==miViewItem)
		{
			spViewItem.setVisible(false);
			spViewItem=null;
			setAllFalse();
			panelViewItem.setVisible(true);
			viewItemDB();
		}
				
		if(ae.getSource()==miAddItem)
		{
			setAllFalse();
			fillCombo();
			panelAddItem.setVisible(true);
			txtAIItemName.requestFocus();
		}
		
		if(ae.getSource()==btnAddItemCancel)
		{
			setAllFalse();
			gotoHome();
			txtAISrNo.setText("");
			txtAIItemName.setText("");
			txtAIDetail.setText("");
			txtAIPrice.setText("");
			txtAIUnitInPackage.setText("");
			modelAIMnfDate.setValue(null);
			modelAIExpDate.setValue(null);
			
		}
		
		if(ae.getSource()==btnAddItem)
		{
			if (txtAISrNo.getText().equals("") || txtAIItemName.getText().equals("") || txtAIDetail.getText().equals("") || txtAIPrice.getText().equals("") || modelAIMnfDate.getValue()== null || modelAIExpDate.getValue()== null)
			{
				JOptionPane.showMessageDialog(hf,"Please fill the value properly...!!!");
			}
			else if(!(rbAIPerPackage.isSelected() || rbAIPerUnit.isSelected()))
			{
				JOptionPane.showMessageDialog(hf,"Please select price type...!!!");
			}
			else if(modelAIExpDate.getValue().before(modelAIMnfDate.getValue()))
			{
				JOptionPane.showMessageDialog(hf,"Please check the both date...!");
			}
			else if(rbAIPerPackage.isSelected())
			{
				if(txtAIUnitInPackage.getText().equals(""))
				{
					JOptionPane.showMessageDialog(hf,"Please provide unit in package...!!!");
				}
				else
				{
					addItemToDB();
				}
			}
			else
			{
				addItemToDB();
			}
			
		}
		
		if(ae.getSource()==miRemoveItem)
		{
			setAllFalse();
			fillCombo();
			panelRemoveItem.setVisible(true);
		}
		
		if(ae.getSource()==btnRemoveItemCancel)
		{
			setAllFalse();
			gotoHome();
		}
		
		if(ae.getSource()==btnRemoveItem)
		{
			if(cbbRIItemName.getSelectedItem()==null)
			{
				JOptionPane.showMessageDialog(hf,"Sorry no available item to remove...!");
			}
			else
			{
				String msg="Are you sure you want to remove "+(String)cbbRIItemName.getSelectedItem();
				int res=JOptionPane.showConfirmDialog(hf,msg,"Confirm",JOptionPane.YES_NO_OPTION);
			
				if(res==JOptionPane.YES_OPTION)
				{
					removeItemFromDB();
				}
			}
		}
		
		if(ae.getSource()==miUpdateItem)
		{
			setAllFalse();
			fillCombo();
			fillUI();
			panelUpdateItem.setVisible(true);
		}
		
		if(ae.getSource()==btnUISelect)
		{
			if(cbbUIItemName.getSelectedItem()==null)
			{
				JOptionPane.showMessageDialog(hf,"Sorry no available item...!");
			}
			else
			{
				fillUI();
			}
		}
		
		if(ae.getSource()==btnUpdateItemCancel)
		{
			setAllFalse();
			gotoHome();
		}
		
		if(ae.getSource()==btnUpdateItem)
		{
			
			if(cbbUIItemName.getSelectedItem()==null)
			{
				JOptionPane.showMessageDialog(hf,"Sorry no item selected to remove !!!");
			}
			else if(txtUISrNo.getText().equals("") || txtUIItemName.getText().equals("") || txtUIDetail.getText().equals("") || txtUIPrice.getText().equals("") || modelUIMnfDate.getValue()==null || modelUIExpDate.getValue()==null)
			{
				JOptionPane.showMessageDialog(hf,"Please fill the value properly...!");
			}
			else if(!(rbUIPerPackage.isSelected() || rbUIPerUnit.isSelected()))
			{
				JOptionPane.showMessageDialog(hf,"Please select price type...!!!");
			}
			else if(modelUIExpDate.getValue().before(modelUIMnfDate.getValue()))
			{
				JOptionPane.showMessageDialog(hf,"Please check the both date...!");
			}
			else if(rbUIPerPackage.isSelected() && txtUIUnitInPackage.getText().equals(""))
			{
				JOptionPane.showMessageDialog(hf,"Please provide unit in package...!!!");
			
			}
			else
			{
				String msg="Are you sure you want to update "+(String)cbbUIItemName.getSelectedItem();
				int res=JOptionPane.showConfirmDialog(hf,msg,"Confirm",JOptionPane.YES_NO_OPTION);
			
				if(res==JOptionPane.YES_OPTION)
				{
					updateItemToDB();
				}								
			}	
			
				
			
		}
		
		if(ae.getSource()==miAddStock)
		{
			setAllFalse();
			fillCombo();
			panelAddStock.setVisible(true);
		}
		
		if(ae.getSource()==btnASSelect)
		{
			if(cbbASItemName.getSelectedItem()==null)
			{
				JOptionPane.showMessageDialog(hf,"No Item selected to add stock...!");
			}
			else
			{	
				lblU.setText("");
				lblP.setText("");
				lblASUnitInPackage.setText("");
				fillAS();
			}
		}
		
		if(ae.getSource()==btnAddStockCancel)
		{
			setAllFalse();
			gotoHome();
			txtASLocation.setText("");
			txtASQuantity.setText("");
			txtASSPrice.setText("");
			rbASPackage.setSelected(false);
			rbASUnit.setSelected(false);
			rbASPPackage.setSelected(false);
			rbASPUnit.setSelected(false);
		}
		
		if(ae.getSource()==btnAddToStock)
		{
			if(cbbASItemName.getSelectedItem()==null)
			{
				JOptionPane.showMessageDialog(hf,"No item selected to add stock...!");
			}
			else if(txtASLocation.getText().equals("") || txtASQuantity.getText().equals("") || txtASSPrice.getText().equals(""))
			{
				JOptionPane.showMessageDialog(hf,"Please fill the value properly...!");
			}
			else if(!(rbASUnit.isSelected() || rbASPackage.isSelected() || rbASPPackage.isSelected() || rbASPUnit.isSelected()))
			{
				JOptionPane.showMessageDialog(hf,"Please fill the value properly...!");
			}
			else
			{
				addStockToDB();
			}
		}
		
		if(ae.getSource()==miRemoveStock)
		{
			setAllFalse();
			fillCombo();
			panelRemoveStock.setVisible(true);
		}
		
		if(ae.getSource()==btnRemoveStockCancel)
		{
			setAllFalse();
			gotoHome();
		}
		
		if(ae.getSource()==btnRemoveStock)
		{
			if(cbbRSItemName.getSelectedItem()==null)
			{
				JOptionPane.showMessageDialog(hf,"No item selected for remove stock...!");
			
			}
			else
			{
				String msg="Are you sure you want to remove "+(String)cbbRSItemName.getSelectedItem()+" from the stock ?";
				int res=JOptionPane.showConfirmDialog(hf,msg,"Confirm",JOptionPane.YES_NO_OPTION);
			
				if(res==JOptionPane.YES_OPTION)
				{
					removeStockFromDB();
				}	
			}
		}
		
		if(ae.getSource()==miViewStock)
		{	
			spViewStock.setVisible(false);
			spViewStock=null;
			setAllFalse();
			panelViewStock.setVisible(true);
			viewStockDB();
		}
			
		if(ae.getSource()==miUpdateStock)
		{	
			cbbUSCategory.removeAllItems();
			setAllFalse();
			try
			{
				
				PreparedStatement pst=con.prepareStatement("Select CATEGORY from STOCK group by CATEGORY");
				ResultSet rs=pst.executeQuery();
				while(rs.next())
				{
					cbbUSCategory.addItem(rs.getString("CATEGORY"));
				}
				pst.close();
			}
			catch(Exception e)
			{
				JOptionPane.showMessageDialog(hf,"Sorry !!! There are some error occured...!");
			}
			fillUS();
			panelUpdateStock.setVisible(true);
		}
		
		if(ae.getSource()==btnUpdateStockCancel)
		{
			setAllFalse();
			gotoHome();
		}
		
		if(ae.getSource()==btnUpdateStock)
		{
			if(cbbUSItemName.getSelectedItem()==null)
			{
				JOptionPane.showMessageDialog(hf,"No item selected for update stock...!");
			}
			else if(txtUSLocation.getText().equals(""))
			{
				JOptionPane.showMessageDialog(hf,"Please fill the value properly...!");
			}
			else
			{
				String msg="Are you sure you want to update "+(String)cbbUSItemName.getSelectedItem()+" in the stock ?";
				int res=JOptionPane.showConfirmDialog(hf,msg,"Confirm",JOptionPane.YES_NO_OPTION);
			
				if(res==JOptionPane.YES_OPTION)
				{
					updateStockToDB();
				}	
				
			}
		}
		
		
		
		if(ae.getSource()==miAddClient)
		{
			setAllFalse();
			panelAddClient.setVisible(true);
			txtACLName.requestFocus();
		}
		
		if(ae.getSource()==btnAddClientCancel)
		{
			setAllFalse();
			gotoHome();
			txtACLName.setText("");
			txtACLCity.setText("");
			txtACLAddress.setText("");
			txtACLContactNo.setText("");
			txtACLEmail.setText("");
		}
		
		if(ae.getSource()==btnAddClient)
		{
			if(txtACLName.getText().equals("") || txtACLCity.getText().equals("") || txtACLAddress.getText().equals("") || txtACLContactNo.getText().equals("") || txtACLEmail.getText().equals(""))
			{
				JOptionPane.showMessageDialog(hf,"Please fill the value properly...!");
			}
			else
			{
				addClientToDB();
			}
		}
		
		if(ae.getSource()==miRemoveClient)
		{
			setAllFalse();
			fillCombo();
			panelRemoveClient.setVisible(true);
		}
		
		if(ae.getSource()==btnRemoveClientCancel)
		{
			setAllFalse();
			gotoHome();
		}
		
		if(ae.getSource()==btnRemoveClient)
		{
			if(cbbRCLClient.getSelectedItem()==null)
			{
				JOptionPane.showMessageDialog(hf,"No client selected for remove...!");
			}
			else
			{
				String msg="Are you sure you want to remove "+(String)cbbRCLClient.getSelectedItem()+".\n It's related all details also will be removed.";
				int res=JOptionPane.showConfirmDialog(hf,msg,"Confirm",JOptionPane.YES_NO_OPTION);
			
				if(res==JOptionPane.YES_OPTION)
				{
					removeClientFromDB();
				}
			}
		}
		
		if(ae.getSource()==miUpdateClient)
		{
			setAllFalse();
			fillCombo();
			fillUCL();
			panelUpdateClient.setVisible(true);
		}
		
		if(ae.getSource()==btnUpdateClientCancel)
		{
			setAllFalse();
			gotoHome();
		}
		
		if(ae.getSource()==btnUpdateClient)
		{
			if(cbbUCLClient.getSelectedItem()==null)
			{
				JOptionPane.showMessageDialog(hf,"No client selected for update...!");
			}
			else if(txtUCLName.getText().equals("") || txtUCLCity.getText().equals("") || txtUCLAddress.getText().equals("") || txtUCLContactNo.getText().equals("") || txtUCLEmail.getText().equals(""))
			{	
				JOptionPane.showMessageDialog(hf,"Please fill the value properly...!");
			}
			else
			{
				String msg="Are you sure you want to update "+(String)cbbUCLClient.getSelectedItem()+" ?";
				int res=JOptionPane.showConfirmDialog(hf,msg,"Confirm",JOptionPane.YES_NO_OPTION);
			
				if(res==JOptionPane.YES_OPTION)
				{
					updateClientToDB();
				}
			}
		}
		
		if(ae.getSource()==miViewClient)
		{
			spViewClient.setVisible(false);
			spViewClient=null;
			setAllFalse();
			panelViewClient.setVisible(true);
			viewClientDB();
		}
				
		if(ae.getSource()==miAddCompany)
		{
			setAllFalse();
			panelAddCompany.setVisible(true);
			txtACLicenceNo.requestFocus();
		}
		
		if(ae.getSource()==btnAddCompanyCancel)
		{
			setAllFalse();
			gotoHome();
			txtACLicenceNo.setText("");
			txtACCompanyName.setText("");
			txtACDetails.setText("");
			txtACCity.setText("");
			txtACAddress.setText("");
			txtACContactPerson.setText("");
			txtACContactNo.setText("");
			txtACEmail.setText("");
		}
		
		if(ae.getSource()==btnAddCompany)
		{
			if(txtACLicenceNo.getText().equals("") || txtACCompanyName.getText().equals("") || txtACDetails.getText().equals("") || txtACCity.getText().equals("") || txtACAddress.getText().equals("") || txtACContactPerson.getText().equals("") || txtACContactNo.getText().equals("") || txtACEmail.getText().equals(""))
			{
				JOptionPane.showMessageDialog(hf,"Please fill the value properly...!");
			}
			else
			{
				addCompanyToDB();
			}
		}
		
		if(ae.getSource()==miRemoveCompany)
		{
			setAllFalse();
			fillCombo();
			panelRemoveCompany.setVisible(true);
		}
		
		if(ae.getSource()==btnRemoveCompanyCancel)
		{
			setAllFalse();
			gotoHome();
		}
		
		if(ae.getSource()==btnRemoveCompany)
		{
			if(cbbRCCompanyName.getSelectedItem()==null)
			{
				JOptionPane.showMessageDialog(hf,"Sorry !!! No Company selected for remove...!");
			}
			else
			{
				String msg="Are you sure you want to remove "+(String)cbbRCCompanyName.getSelectedItem()+" ?";
				int res=JOptionPane.showConfirmDialog(hf,msg,"Confirm",JOptionPane.YES_NO_OPTION);
			
				if(res==JOptionPane.YES_OPTION)
				{
					removeCompanyFromDB();
				}
			}
		}
		
		if(ae.getSource()==miUpdateCompany)
		{
			setAllFalse();
			fillCombo();
			fillUC();
			panelUpdateCompany.setVisible(true);
		}
		
		if(ae.getSource()==btnUpdateCompanyCancel)
		{
			setAllFalse();
			gotoHome();
			txtUCLicenceNo.setText("");
			txtUCCompanyName.setText("");
			txtUCDetails.setText("");
			txtUCCity.setText("");
			txtUCAddress.setText("");
			txtUCContactPerson.setText("");
			txtUCContactNo.setText("");
			txtUCEmail.setText("");
		}
		
		if(ae.getSource()==btnUpdateCompany)
		{
			if(cbbUCCompanyName.getSelectedItem()==null)
			{
				JOptionPane.showMessageDialog(hf,"No company select for update...!");
			}
			else if(txtUCLicenceNo.getText().equals("") || txtUCCompanyName.getText().equals("") || txtUCDetails.getText().equals("") || txtUCCity.getText().equals("") || txtUCAddress.getText().equals("") || txtUCContactPerson.getText().equals("") || txtUCContactNo.getText().equals("") || txtUCEmail.getText().equals(""))
			{
				JOptionPane.showMessageDialog(hf,"Please fill the value properly...!");
			}
			else
			{
				String msg="Are you sure you want to update "+(String)cbbUCCompanyName.getSelectedItem()+" ? ";
				int res=JOptionPane.showConfirmDialog(hf,msg,"Confirm",JOptionPane.YES_NO_OPTION);
				if(res==JOptionPane.YES_OPTION)
				{
					updateCompanyToDB();
				}
			}
		}
		
		if(ae.getSource()==miViewCompany)
		{
			spViewCompany.setVisible(false);
			spViewCompany=null;
			setAllFalse();
			panelViewCompany.setVisible(true);
			viewCompanyDB();
		}
		
		if(ae.getSource()==miRetailerSales)
		{
			spRetailerSales.setVisible(false);
			spRetailerSales=null;
			setAllFalse();
			panelRetailerSales.setVisible(true);
			retailerSalesReportDB();
		}
		
		if(ae.getSource()==btnRetailerSalesPrint)
		{
			MessageFormat header=new MessageFormat("Retailer Sales Report");
			MessageFormat footer=new MessageFormat("Page{0,number,integer}");
			try
			{
				tableRetailerSales.print(JTable.PrintMode.NORMAL,header,footer);
			}
			catch(Exception e)
			{
				JOptionPane.showMessageDialog(hf,"Sorry !!! There are some error occured...!");
			}
			
		}
		
		if(ae.getSource()==miCustomizedSales)
		{
			spCustomizedSales.setVisible(false);
			spCustomizedSales=null;
			setAllFalse();
			panelCustomizedSales.setVisible(true);
			customizedSalesReportDB();
		}
		
		if(ae.getSource()==btnCustomizedSalesPrint)
		{
			MessageFormat header=new MessageFormat("Customize Sales Report");
			MessageFormat footer=new MessageFormat("Page{0,number,integer}");
			try
			{
				tableCustomizedSales.print(JTable.PrintMode.NORMAL,header,footer);
			}
			catch(Exception e)
			{
				JOptionPane.showMessageDialog(hf,"Sorry !!! There are some error occured...!");
			}
		}
		
		if(ae.getSource()==miCategory)
		{
			spViewCategory.setVisible(false);
			spViewCategory=null;
			setAllFalse();
			panelViewCategory.setVisible(true);
			viewCategoryDB();
		}
		
		if(ae.getSource()==btnCategoryPrint)
		{
			MessageFormat header=new MessageFormat("Category Report");
			MessageFormat footer=new MessageFormat("Page{0,number,integer}");
			try
			{
				tableCategory.print(JTable.PrintMode.NORMAL,header,footer);
			}
			catch(Exception e)
			{
				JOptionPane.showMessageDialog(hf,"Sorry !!! There are some error occured...!");
			}
		}
		
		if(ae.getSource()==miItem)
		{
			spViewItem.setVisible(false);
			spViewItem=null;
			setAllFalse();
			panelViewItem.setVisible(true);
			viewItemDB();
		}
		
		if(ae.getSource()==btnItemPrint)
		{
			MessageFormat header=new MessageFormat("Item Report");
			MessageFormat footer=new MessageFormat("Page{0,number,integer}");
			try
			{
				tableItem.print(JTable.PrintMode.NORMAL,header,footer);
			}
			catch(Exception e)
			{
				JOptionPane.showMessageDialog(hf,"Sorry !!! There are some error occured...!");
			}
			
		}
		
		if(ae.getSource()==miStock)
		{
			spViewStock.setVisible(false);
			spViewStock=null;
			setAllFalse();
			panelViewStock.setVisible(true);
			viewStockDB();
		}
		
		if(ae.getSource()==btnStockPrint)
		{
			MessageFormat header=new MessageFormat("Stock Report");
			MessageFormat footer=new MessageFormat("Page{0,number,integer}");
			try
			{
				tableStcock.print(JTable.PrintMode.NORMAL,header,footer);
			}
			catch(Exception e)
			{
				JOptionPane.showMessageDialog(hf,"Sorry !!! There are some error occured...!");
			}
		}
		
		if(ae.getSource()==miCompany)
		{
			spViewCompany.setVisible(false);
			spViewCompany=null;
			setAllFalse();
			panelViewCompany.setVisible(true);
			viewCompanyDB();
		}

		if(ae.getSource()==btnCompanyPrint)
		{
			MessageFormat header=new MessageFormat("Company Report");
			MessageFormat footer=new MessageFormat("Page{0,number,integer}");
			try
			{
				tableCompany.print(JTable.PrintMode.NORMAL,header,footer);
			}
			catch(Exception e)
			{
				JOptionPane.showMessageDialog(hf,"Sorry !!! There are some error occured...!");
			}
		}
		
		if(ae.getSource()==miClient)
		{
			spViewClient.setVisible(false);
			spViewClient=null;
			setAllFalse();
			panelViewClient.setVisible(true);
			viewClientDB();
		}
		
		if(ae.getSource()==btnClientPrint)
		{
			MessageFormat header=new MessageFormat("Client Report");
			MessageFormat footer=new MessageFormat("Page{0,number,integer}");
			try
			{
				tableClient.print(JTable.PrintMode.NORMAL,header,footer);
			}
			catch(Exception e)
			{
				JOptionPane.showMessageDialog(hf,"Sorry !!! There are some error occured...!");
			}
		}
		
	}

	public static void main(String args[])
	{
		home h=new home();
	}
}


class ButtonRenderer extends JButton implements TableCellRenderer {

    public ButtonRenderer() {
        setOpaque(true);
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
            boolean isSelected, boolean hasFocus, int row, int column) {
        if (isSelected) {
		
          //  setForeground(table.getSelectionForeground());
          //  setBackground(table.getSelectionBackground());
						
			
        } else {
        //    setForeground(table.getForeground());
          //  setBackground(UIManager.getColor("Button.background"));
        }
        setText((value == null) ? "" : value.toString());
        return this;
    }
}

class ButtonEditor extends DefaultCellEditor {

    protected JButton button;
    private String label;
    private boolean isPushed;

    public ButtonEditor(JCheckBox checkBox) {
		
        super(checkBox);
		try{
        button = new JButton();
        button.setOpaque(true);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fireEditingStopped();
				
            }
        });
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null,"wait");
		}
    }

    //@Override
    public Component getTableCellEditorComponent(JTable table, Object value,
            boolean isSelected, int row, int column) {
				try{
				
				isSelected=true;
        if (isSelected) {
		
         //   button.setForeground(table.getSelectionForeground());
          //  button.setBackground(table.getSelectionBackground());
			
			
			int total=Integer.parseInt(home.modelRBill.getValueAt(row,6).toString());
			int currentTotal=Integer.parseInt(home.lblRCAmount.getText());
			currentTotal=currentTotal-total;
			home.lblRCAmount.setText(""+currentTotal);
			
			home.modelRBill.removeRow(row);
			
			
			
        } else {
          //  button.setForeground(table.getForeground());
          //  button.setBackground(table.getBackground());
        }
        label = (value == null) ? "" : value.toString();
        button.setText(label);
        isPushed = true;
       
				}
				catch(Exception e)
				{
					JOptionPane.showMessageDialog(null,"Asda");
				}
				 return button;
    }

   // @Override
    public Object getCellEditorValue() {
		try{
        if (isPushed) {
         //   JOptionPane.showMessageDialog(button, label + ": Ouch!");
		 
		 //JOptionPane.showMessageDialog(button,"ok");
        }
        isPushed = false;
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null,"Wain last");
		}
        return label;
    }

 //   @Override
    public boolean stopCellEditing() {
        isPushed = false;
        return super.stopCellEditing();
    }
}
