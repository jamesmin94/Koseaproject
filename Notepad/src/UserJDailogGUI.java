
//UserJDailogGUI.java
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class UserJDailogGUI extends JDialog implements ActionListener {

	JPanel pw = new JPanel(new GridLayout(4, 1));
	JPanel pc = new JPanel(new GridLayout(4, 1));
	JPanel ps = new JPanel();

	JLabel lable_Id = new JLabel("ID");
	JLabel lable_Name = new JLabel("Name");
	JLabel lable_SnsAddress = new JLabel("Sns Address");
	JLabel lable_Addr = new JLabel("Address");

	JTextField id = new JTextField();
	JTextField name = new JTextField();
	JTextField SNSADDRESS = new JTextField();
	JTextField address = new JTextField();

	JButton confirm;
	JButton reset = new JButton("Cancel");

	MenuJTabaleExam me;

	JPanel idCkP = new JPanel(new BorderLayout());
	JButton idCkBtn = new JButton("IDCheck");
    
	
	JPanel namechk = new JPanel(new BorderLayout());
	JButton namechk1 = new JButton("Namecheck");
	
	
	UserDefaultJTableDAO dao = new UserDefaultJTableDAO();
    
	
	
	
	public UserJDailogGUI(MenuJTabaleExam me, String index) {
	
		super(me, "Dialouge");
		this.me = me;
		if (index.equals("Registration")) {
			confirm = new JButton(index);
		} else {
			confirm = new JButton("Edit");

			// text�ڽ��� ���õ� ���ڵ��� ���� �ֱ�
			int row = me.jt.getSelectedRow();// ���õ� ��
			id.setText(me.jt.getValueAt(row, 0).toString());
			name.setText(me.jt.getValueAt(row, 1).toString());
			SNSADDRESS.setText(me.jt.getValueAt(row, 2).toString());
			address.setText(me.jt.getValueAt(row, 3).toString());

			// id text�ڽ� ��Ȱ��
			id.setEditable(false);

			// IDCheck��ư ��Ȱ��ȭ
			idCkBtn.setEnabled(false);
		}

		// Label�߰��κ�
		pw.add(lable_Id);// ID
		pw.add(lable_Name);// �̸�
		pw.add(lable_SnsAddress);// ����
		pw.add(lable_Addr);// �ּ�

		idCkP.add(id, "Center");
		idCkP.add(idCkBtn, "East");
        
		
		
		// TextField �߰�
		pc.add(idCkP);
		pc.add(name);
		pc.add(SNSADDRESS);
		pc.add(address);

		ps.add(confirm);
		ps.add(reset); 

		add(pw, "West");
		add(pc, "Center");
		add(ps, "South");

		setSize(300, 250);
		setVisible(true);

		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

		// �̺�Ʈ���
		confirm.addActionListener(this); // ����/���� �̺�Ʈ���
		reset.addActionListener(this); // ��� �̺�Ʈ���
		idCkBtn.addActionListener(this);// ID�ߺ�üũ �̺�Ʈ ���

	}// �����ڳ�

	/**
	 * ����/����/���� ��ɿ� ���� �κ�
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		String btnLabel = e.getActionCommand();// �̺�Ʈ��ü ���� Label ��������

		if (btnLabel.equals("Registration")) {
			
			
			if(name.getText().trim().equals("") && SNSADDRESS.getText().trim().equals("")&& address.getText().trim().equals("")){
				messageBox(this, "Please write their Name, SNSADDRESS, and physical address. If you were not know their SNS address or physical address just write null");  
				setDefaultCloseOperation(EXIT_ON_CLOSE);
				
		       	}   
			
			if(SNSADDRESS.getText().trim().equals("")&& address.getText().trim().equals("")){messageBox(this, "Please write their SNSADDRESS, and physical address. If you were not know their SNS address or physical address just write null");  
			setDefaultCloseOperation(EXIT_ON_CLOSE);
			};
			
			
			if(SNSADDRESS.getText().trim().equals("")){
				messageBox(this, "Please write their snsaddress if you were not know their snsaddress just write null");
				setDefaultCloseOperation(EXIT_ON_CLOSE);
				
				
			}
			
			
			
			else if(address.getText().trim().equals("")){
				messageBox(this, "please write thier address in address field if you were not know their physical address field just write null or whatever you want");
				setDefaultCloseOperation(EXIT_ON_CLOSE);
				
				
			}
			
			
			
			/////�޸��� �ȳ����� 
			
			
			
			if (dao.userListInsert(this) > 0) {// ���Լ���
				messageBox(this, "Your experience has been entered our record! Thank you for your help!");
				dispose();// JDialog â�ݱ�

				dao.userSelectAll(me.dt);// ��緹�ڵ尡���ͼ� DefaultTableModel�� �ø���

				if (me.dt.getRowCount() > 0)
					me.jt.setRowSelectionInterval(0, 0);// ù��° �� ����

			} else if (name.getText().trim().equals("")) {// ���Խ���
				messageBox(this,
						"Sorry your experience has not been entered our record... plese check your name field");}
			
			}
			
			
			
			
			
			

		 else if (btnLabel.equals("Edit")) {

			if (dao.userUpdate(this) > 0) {
				messageBox(this, "Modification completed.");
				dispose();
				dao.userSelectAll(me.dt);
				if (me.dt.getRowCount() > 0)
					me.jt.setRowSelectionInterval(0, 0);

			} else {
				messageBox(this, "Modification uncompleted");
			}

		} else if (btnLabel.equals("Cancel")) {
			dispose();

		} else if (btnLabel.equals("IDCheck")) {
			// id�ؽ�Ʈ�ڽ��� �� ������ �޼��� ��� ������ DB�����Ѵ�.
			if (id.getText().trim().equals("")) {
				messageBox(this, "Please write your ID on the textfield");
				id.requestFocus();// ��Ŀ���̵�
			} else {

				if (dao.getIdByCheck(id.getText())) { // �ߺ��ƴϴ�.(��밡��)
					messageBox(this, id.getText() + " could use it.");
				} else { // �ߺ��̴�.
					messageBox(this, id.getText() + " could not use it.");

					id.setText("");// text�ڽ������
					id.requestFocus();// Ŀ������
				}

			}

		}

	}// actionPerformed��

	/**
	 * �޽����ڽ� ����ִ� �޼ҵ� �ۼ�
	 */
	public static void messageBox(Object obj, String message) {
		JOptionPane.showMessageDialog((Component) obj, message);
	}
}