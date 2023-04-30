package roque;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

public class INV extends JFrame {

	
	

	ImageIcon bg = new ImageIcon (new ImageIcon("INVBG.png").getImage().getScaledInstance(1050, 500, Image.SCALE_SMOOTH));
	JLabel img = new JLabel();


	JLabel[] TXTLABEL = new JLabel[8];  
	JTextField[] INPUT = new JTextField[8];
	JButton[] B1 = new JButton[3];  
	JButton[] B2 = new JButton[3];  
	
	
	 
	String[] btn = {
			"Stock In",
			"Add",
			"Edit",
			"Delete",
			"Save",
			"Cancel"
	};
	
	 
	String[] Row = new String [] {
		"Code",
		"Item Name",
		"Item Description",
		"Price",
		"Size",
		"Stocks",
		"Re-Order Point",
		"Remarks",
	};
	
	 
	String[][] Column = new String [][] {
		{"", "", "", "", "", "", "", ""},
		{"", "", "", "", "", "", "", ""},
		{"", "", "", "", "", "", "", ""},
		{"", "", "", "", "", "", "", ""},
		{"", "", "", "", "", "", "", ""},
		{"", "", "", "", "", "", "", ""},
		{"", "", "", "", "", "", "", ""},
		{"", "", "", "", "", "", "", ""},
	};
	
	 
	DefaultTableModel Model = new DefaultTableModel(Column, Row){
	    @Override
	    public boolean isCellEditable(int row, int column) {
	        return false;
	    }
	};
	
	
	JTable Table = new JTable(Model);
	JScrollPane Scroll = new JScrollPane(Table);
	JPanel Panel = new JPanel(new BorderLayout());
	Vector<String> r = new Vector<String>();
	
	int Code = 1; 
	
	INV(){
		
		
		this.setSize(1050, 500);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setTitle("Inventory");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		

		img.setIcon(bg);
		img.setFocusable(false);
		img.setBounds(0,0,1050,500);
		
		int y = 50; 
		for (int i = 0; i < TXTLABEL.length; i++) {
			TXTLABEL[i] = new JLabel(Row[i] + ":");
			TXTLABEL[i].setBounds(40, y, 200, 30);
			TXTLABEL[i].setFont(new Font("Arial", Font.BOLD, 16));
			TXTLABEL[i].setForeground(Color.BLACK);
			add(TXTLABEL[i]);
			
			INPUT[i] = new JTextField();
			INPUT[i].setBounds(190, y, 250, 25);
			INPUT[i].setFont(new Font("Arial", Font.PLAIN, 14));
			INPUT[i].setForeground(Color.black);
			INPUT[i].setBorder(BorderFactory.createLineBorder(Color.black, 1));
			INPUT[i].setEditable(false);
			
			add(INPUT[i]);
			
			y += 30;
		}
		
		int y1 = 70; 
		for (int i = 0; i < B1.length; i++) {
			B1[i] = new JButton(btn[i]);
			B2[i] = new JButton(btn[i+3]);
			B1[i].setBounds(y1, 305, 100, 50);
			B2[i].setBounds(y1, 360, 100, 50);
			
			B1[i].setFont(new Font("Arial", Font.PLAIN, 14));
			B1[i].setForeground(Color.black);
			B2[i].setFont(new Font("Arial", Font.PLAIN, 14));
			B2[i].setForeground(Color.black);
			B1[i].setFocusable(false);
			B2[i].setFocusable(false);
			B1[i].setBackground(Color.white);
			B2[i].setBackground(Color.white);
			B1[i].setOpaque(true);
			B2[i].setOpaque(true);
			B1[i].setEnabled(true);
			B2[i].setEnabled(true);
			y1 += 110;
			add(B1[i]);
			add(B2[i]);
		}
		
		JLabel InvTxt = new JLabel();  
		InvTxt = new JLabel("INVENTORY FORM");
		InvTxt.setBounds(130, 10, 300, 30);
		InvTxt.setFont(new Font("Arial", Font.BOLD, 18));
		InvTxt.setForeground(Color.BLACK);
		
		Table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
		    @Override
		    public void valueChanged(ListSelectionEvent e) {
		        if (B2[1].isEnabled() == false && B2[2].isEnabled() == false) {
		            int selectedRow = Table.getSelectedRow();
		            if (selectedRow >= 0) {
		                for (int i = 0; i < Model.getColumnCount(); i++) {
		                	INPUT[i].setText((String) Table.getValueAt(selectedRow, i));
		                }
		            }
		        }
		    }
		});
		
		int STOCKBRACKET = 250;  
		
		B1[0].addMouseListener(new java.awt.event.MouseAdapter() {
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		    	B1[0].setBackground(Color.BLUE);
		    	B1[0].setForeground(Color.WHITE);
		    	B1[0].setFont(new Font("Arial", Font.BOLD, 16));
		    }

		    public void mouseExited(java.awt.event.MouseEvent evt) {
		    	B1[0].setBackground(UIManager.getColor("control"));
		    	B1[0].setFont(new Font("Arial", Font.PLAIN, 14));
		    	B1[0].setForeground(Color.BLACK);
		    }
		});
		B1[0].addActionListener(new ActionListener() {  
			public void actionPerformed(ActionEvent e) {
				int selectedRow = Table.getSelectedRow();  
				if (selectedRow >= 0) {  
					
					 
					String Conf = JOptionPane.showInputDialog(null, "Enter new stock count", "Stock Count", JOptionPane.INFORMATION_MESSAGE);
					
					if (Conf.isBlank()) {}  
					else {  
						Table.setValueAt(Conf, selectedRow, 5);  
						
						if(!Conf.matches("[0-9]+")) {  
							Table.setValueAt("", selectedRow, 7);
						} else if (Integer.parseInt(Conf) > STOCKBRACKET) {
							Table.setValueAt("High Stock", selectedRow, 7);
						} else {
							Table.setValueAt("Low Stock", selectedRow, 7);
						}
						
						for (int i = 0; i < Model.getColumnCount(); i++) {  
							INPUT[i].setText((String) Table.getValueAt(selectedRow, i));
			            }         
					}
				} else {  
					JOptionPane.showMessageDialog(null, "Select an Item first", "No Item selected", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		
		B1[1].addMouseListener(new java.awt.event.MouseAdapter() {
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		    	B1[1].setBackground(Color.GREEN);
		    	B1[1].setForeground(Color.BLACK);
		    	B1[1].setFont(new Font("Arial", Font.BOLD, 16));
		    }

		    public void mouseExited(java.awt.event.MouseEvent evt) {
		    	B1[1].setBackground(UIManager.getColor("control"));
		    	B1[1].setFont(new Font("Arial", Font.PLAIN, 14));
		    	B1[1].setForeground(Color.BLACK);
		    }
		});
		
		B1[1].addActionListener(new ActionListener() {  
			public void actionPerformed(ActionEvent e) {
				
				INPUT[0].setBounds(190, 50, 60, 25);		 
				B1[0].setEnabled(false);	B2[1].setEnabled(true);
				B1[1].setEnabled(false);	B2[2].setEnabled(true);	
				B1[2].setEnabled(false);
				B2[0].setEnabled(false);
				
				 
				for (int i =0; i < INPUT.length; i++) {
					if (i == 0) {
						INPUT[0].setText("  " + String.format("%05d",Code));
						INPUT[0].setOpaque(false);
						INPUT[0].setBorder(null);
					} else if (i > 0 && i < 7){
						INPUT[i].setEditable(true);
						INPUT[i].setText("");
					} else if (i == 7){
						INPUT[i].setEditable(false);
						INPUT[i].setText("");
					}
				}
				
				B2[1].addMouseListener(new java.awt.event.MouseAdapter() {
				    public void mouseEntered(java.awt.event.MouseEvent evt) {
				    	B2[1].setBackground(Color.BLACK);
				    	B2[1].setForeground(Color.GREEN);
				    	B2[1].setFont(new Font("Arial", Font.BOLD, 16));
				    }

				    public void mouseExited(java.awt.event.MouseEvent evt) {
				    	B2[1].setBackground(UIManager.getColor("control"));
				    	B2[1].setFont(new Font("Arial", Font.PLAIN, 14));
				    	B2[1].setForeground(Color.BLACK);
				    }
				});
				
				B2[1].addActionListener(new ActionListener() {  
					public void actionPerformed(ActionEvent e) {
						
						 
						for (int i =0; i < INPUT.length-1; i++) { 
							if (i == 0) {
								r.add(String.format("%05d", Code));
								Code += 1;
							} else {
								r.add(INPUT[i].getText());
							}	
						}
						
						 
						 
						if(INPUT[5].getText().isBlank() || INPUT[5].getText().matches("[0-9]")) {
							r.add("");
						} else if (Integer.parseInt(INPUT[5].getText()) > STOCKBRACKET) {
							r.add("High Stock");
						} else {
							r.add("Low Stock");
						}				
						
						Model.addRow(r);
						r = new Vector<String>();
						
					
						for (int i =0; i < INPUT.length-1; i++) {
							INPUT[i].setEditable(false);
							INPUT[i].setText("");
						}		 
						B1[0].setEnabled(true);
						B1[1].setEnabled(true);
						B1[2].setEnabled(true);
						B2[0].setEnabled(true);
						B2[1].setEnabled(false);
						B2[1].setEnabled(false);
						B2[2].setEnabled(false);
						B2[1].removeActionListener(B2[1].getActionListeners()[0]);
						B2[2].removeActionListener(B2[2].getActionListeners()[0]);
					}
				});
				B2[2].addMouseListener(new java.awt.event.MouseAdapter() {
				    public void mouseEntered(java.awt.event.MouseEvent evt) {
				    	B2[2].setBackground(Color.BLACK);
				    	B2[2].setForeground(Color.RED);
				    	B2[2].setFont(new Font("Arial", Font.BOLD, 16));
				    }
				    public void mouseExited(java.awt.event.MouseEvent evt) {
				    	B2[2].setBackground(UIManager.getColor("control"));
				    	B2[2].setFont(new Font("Arial", Font.PLAIN, 14));
				    	B2[2].setForeground(Color.BLACK);	
				    }
				});
				B2[2].addActionListener(new ActionListener() {  
					public void actionPerformed(ActionEvent e) {		 
						int selectedRow = Table.getSelectedRow();
						if (selectedRow >= 0) {  
							for (int i = 0; i < Model.getColumnCount(); i++) {
								INPUT[i].setText((String) Table.getValueAt(selectedRow, i));
								INPUT[i].setEditable(false);
				            }
						} else {
							for (int i =0; i < INPUT.length-1; i++) {
								INPUT[i].setEditable(false);
								INPUT[i].setText("");
							}
						}
						B1[0].setEnabled(true);
						B1[1].setEnabled(true);
						B1[2].setEnabled(true);
						B2[0].setEnabled(true);
						B2[1].setEnabled(false);
						B2[2].setEnabled(false);
						B2[1].removeActionListener(B2[1].getActionListeners()[0]);
						B2[2].removeActionListener(B2[2].getActionListeners()[0]);
					}
				});
			}
		});
					B1[2].addMouseListener(new java.awt.event.MouseAdapter() {
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		    		B1[2].setBackground(Color.GRAY);
		    		B1[2].setForeground(Color.BLACK);
		    		B1[2].setFont(new Font("Arial", Font.BOLD, 16));
		    }

		    public void mouseExited(java.awt.event.MouseEvent evt) {
		    	B1[2].setBackground(UIManager.getColor("control"));
		    	B1[2].setFont(new Font("Arial", Font.PLAIN, 14));
		    	B1[2].setForeground(Color.BLACK);
		    }
		});
					B1[2].addActionListener(new ActionListener() {  
		    public void actionPerformed(ActionEvent e) {
		        
		    	 
				 
		    	int selectedRow = Table.getSelectedRow();
		        if (selectedRow >= 0) {  
		            for (int i = 0; i < Model.getColumnCount(); i++) {
		            	INPUT[i].setText((String) Table.getValueAt(selectedRow, i));
		            }
		            
		             
		            for (int i =0; i < INPUT.length; i++) {
						if (i == 0) {
						} else if (i > 0 && i < 7){
							INPUT[i].setEditable(true);
						} else if (i == 7){
							INPUT[i].setEditable(false);
						}
					}
			        
		             
		            B1[0].setEnabled(false);
		            B1[1].setEnabled(false);
		            B1[2].setEnabled(false);
		            B2[0].setEnabled(false);
		            B2[1].setEnabled(true);
		            B2[2].setEnabled(true);
					
		            B2[1].addActionListener(new ActionListener() {  
						public void actionPerformed(ActionEvent e) {
							 
							Table.setValueAt(INPUT[1].getText(), selectedRow, 1);
							Table.setValueAt(INPUT[2].getText(), selectedRow, 2);
							Table.setValueAt(INPUT[3].getText(), selectedRow, 3);
							Table.setValueAt(INPUT[4].getText(), selectedRow, 4);
							Table.setValueAt(INPUT[5].getText(), selectedRow, 5);
							Table.setValueAt(INPUT[6].getText(), selectedRow, 6);
							

							 
							 
							if(INPUT[5].getText().isBlank() || !INPUT[5].getText().matches("[0-9]+")) {
								Table.setValueAt("", selectedRow, 7);
							} else if (Integer.parseInt(INPUT[5].getText()) > STOCKBRACKET) {
								Table.setValueAt("High Stock", selectedRow, 7);
							} else {
								Table.setValueAt("Low Stock", selectedRow, 7);
							}
							
							for (int i =0; i < INPUT.length; i++) {
								INPUT[i].setEditable(false);
							}
							
							 
							B1[0].setEnabled(true);
							B1[1].setEnabled(true);
							B1[2].setEnabled(true);
							B2[0].setEnabled(true);
							B2[1].setEnabled(false);
							B2[2].setEnabled(false);
							B2[1].removeActionListener(B2[1].getActionListeners()[0]);
							B2[2].removeActionListener(B2[2].getActionListeners()[0]);
						}
					});
					
		            B2[2].addMouseListener(new java.awt.event.MouseAdapter() {
		    		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		    		    	 B2[2].setBackground(Color.BLACK);
		    		    	 B2[2].setForeground(Color.RED);
		    		    	 B2[2].setFont(new Font("Arial", Font.BOLD, 16));
		    		    }

		    		    public void mouseExited(java.awt.event.MouseEvent evt) {
		    		    	 B2[2].setBackground(UIManager.getColor("control"));
		    		    	 B2[2].setFont(new Font("Arial", Font.PLAIN, 14));
		    		    	 B2[2].setForeground(Color.black);
		    		    }
		    		});
		            
		            B2[2].addActionListener(new ActionListener() {  
						public void actionPerformed(ActionEvent e) {
							
							 
							int selectedRow = Table.getSelectedRow();
					        if (selectedRow >= 0) { 
					            for (int i = 0; i < Model.getColumnCount(); i++) {
					            	INPUT[i].setText((String) Table.getValueAt(selectedRow, i));
					            }
					        }
							
					         
					        for (int i =0; i < INPUT.length; i++) {
					        	INPUT[i].setEditable(false);
							}
					        
					       
					        B1[0].setEnabled(true);
					        B1[1].setEnabled(true);
							B1[2].setEnabled(true);
							B2[0].setEnabled(true);
							B2[1].setEnabled(false);
							B2[2].setEnabled(false);
							B2[1].removeActionListener(B2[1].getActionListeners()[0]);
							B2[2].removeActionListener(B2[2].getActionListeners()[0]);
						}
					});
		        } else {
		        	JOptionPane.showMessageDialog(null, "Select an Item first", "No Item selected", JOptionPane.WARNING_MESSAGE);
		        }
		    }
		});
		B2[0].addMouseListener(new java.awt.event.MouseAdapter() {
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		    	B2[0].setBackground(Color.RED);
		    	B2[0].setForeground(Color.BLACK);
		    	B2[0].setFont(new Font("Arial", Font.BOLD, 16));
		    }

		    public void mouseExited(java.awt.event.MouseEvent evt) {
		    	B2[0].setBackground(UIManager.getColor("control"));
		    	B2[0].setFont(new Font("Arial", Font.PLAIN, 14));
		    	B2[0].setForeground(Color.BLACK);
		    }
		});
		B2[0].addActionListener(new ActionListener() {  
			public void actionPerformed(ActionEvent e) {
			    int selectedRow = Table.getSelectedRow();
			    if (selectedRow >= 0) {
			    	int Conf = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this item?", "Delete Item", JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE);
			        if (Conf == JOptionPane.YES_OPTION) {
			        	Model.removeRow(selectedRow);  
				        for (int i = 0; i < INPUT.length; i++) {  
				        	INPUT[i].setText("");
				        }
			        }
			    } else {
			        JOptionPane.showMessageDialog(null, "Select an Item first", "No Item selected", JOptionPane.WARNING_MESSAGE);
			    }
			}
		});
		
		Table.getTableHeader().setEnabled(false);  
		
		 
		DefaultTableCellRenderer Center = new DefaultTableCellRenderer(); 
		Center.setHorizontalAlignment(JLabel.CENTER); 
		
		for (int i = 0; i < Row.length; i++) {
		    Table.getColumnModel().getColumn(i).setCellRenderer(Center);
		}
		
		 
		for (int i = 7; i >= 0; i--) {
			Model.removeRow(i);
		}
		
		Table.setRowHeight(20);  
		
		 
		TableColumnModel Colm = Table.getColumnModel();
		int[] Wid = {
			70, 120, 150, 70, 90, 70, 120, 120 
		};
		
		for (int i = 0; i < Wid.length; i++) {
			Colm.getColumn(i).setPreferredWidth(Wid[i]);
		}
		
		for (int i = 0; i < B1.length; i++) {
		
		}
		
	
		B2[1].setEnabled(false);
		B2[2].setEnabled(false);
		Panel.setBounds(445, 20, 585, 370);
		Panel.setOpaque(false);
		Panel.add(Scroll);		
		this.add(Panel);
		this.add(InvTxt);
		this.add(img);
		setLayout(null);
		setVisible(true);
		
		
	}
}