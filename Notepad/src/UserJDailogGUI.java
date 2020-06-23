
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

			// text박스에 선택된 레코드의 정보 넣기
			int row = me.jt.getSelectedRow();// 선택된 행
			id.setText(me.jt.getValueAt(row, 0).toString());
			name.setText(me.jt.getValueAt(row, 1).toString());
			SNSADDRESS.setText(me.jt.getValueAt(row, 2).toString());
			address.setText(me.jt.getValueAt(row, 3).toString());

			// id text박스 비활성
			id.setEditable(false);

			// IDCheck버튼 비활성화
			idCkBtn.setEnabled(false);
		}

		// Label추가부분
		pw.add(lable_Id);// ID
		pw.add(lable_Name);// 이름
		pw.add(lable_SnsAddress);// 나이
		pw.add(lable_Addr);// 주소

		idCkP.add(id, "Center");
		idCkP.add(idCkBtn, "East");
        
		
		
		// TextField 추가
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

		// 이벤트등록
		confirm.addActionListener(this); // 가입/수정 이벤트등록
		reset.addActionListener(this); // 취소 이벤트등록
		idCkBtn.addActionListener(this);// ID중복체크 이벤트 등록

	}// 생성자끝

	/**
	 * 가입/수정/삭제 기능에 대한 부분
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		String btnLabel = e.getActionCommand();// 이벤트주체 대한 Label 가져오기

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
			
			
			
			/////메모장 안나오게 
			
			
			
			if (dao.userListInsert(this) > 0) {// 가입성공
				messageBox(this, "Your experience has been entered our record! Thank you for your help!");
				dispose();// JDialog 창닫기

				dao.userSelectAll(me.dt);// 모든레코드가져와서 DefaultTableModel에 올리기

				if (me.dt.getRowCount() > 0)
					me.jt.setRowSelectionInterval(0, 0);// 첫번째 행 선택

			} else if (name.getText().trim().equals("")) {// 가입실패
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
			// id텍스트박스에 값 없으면 메세지 출력 있으면 DB연동한다.
			if (id.getText().trim().equals("")) {
				messageBox(this, "Please write your ID on the textfield");
				id.requestFocus();// 포커스이동
			} else {

				if (dao.getIdByCheck(id.getText())) { // 중복아니다.(사용가능)
					messageBox(this, id.getText() + " could use it.");
				} else { // 중복이다.
					messageBox(this, id.getText() + " could not use it.");

					id.setText("");// text박스지우기
					id.requestFocus();// 커서놓기
				}

			}

		}

	}// actionPerformed끝

	/**
	 * 메시지박스 띄워주는 메소드 작성
	 */
	public static void messageBox(Object obj, String message) {
		JOptionPane.showMessageDialog((Component) obj, message);
	}
}