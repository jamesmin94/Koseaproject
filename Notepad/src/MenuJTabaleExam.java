
//MenuJTabaleExam.java
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class MenuJTabaleExam extends JFrame implements ActionListener {
   JMenu m = new JMenu("Managing");
   JMenuItem insert = new JMenuItem("Registrion");
   JMenuItem update = new JMenuItem("Edit");
   JMenuItem delete = new JMenuItem("Delete");
   JMenuItem quit = new JMenuItem("Exit");
   JMenuBar mb = new JMenuBar();

   String[] name = { "ID", "Name", "SNSADDRE", "Address" };

   DefaultTableModel dt = new DefaultTableModel(name, 0);
   JTable jt = new JTable(dt);
   JScrollPane jsp = new JScrollPane(jt);

   /*
    * South 영역에 추가할 Componet들
    */
   JPanel p = new JPanel();
   String[] comboName = { "  ALL  ", "  ID  ", " name ", " address " };

   JComboBox combo = new JComboBox(comboName);
   JTextField jtf = new JTextField(20);
   JButton Serach = new JButton("Search");

   UserDefaultJTableDAO dao = new UserDefaultJTableDAO();

   /**
    * 화면구성 및 이벤트등록
    */
   public MenuJTabaleExam() {

      super("Sunshine State Tourism Attraction Explorer");

      // 메뉴아이템을 메뉴에 추가
      m.add(insert);
      m.add(update);
      m.add(delete);
      m.add(quit);
      // 메뉴를 메뉴바에 추가
      mb.add(m);

      // 윈도우에 메뉴바 세팅
      setJMenuBar(mb);

      // South영역
      p.setBackground(Color.BLUE);
      p.add(combo);
      p.add(jtf);
      p.add(Serach);

      add(jsp, "Center");
      add(p, "South");

      setSize(1200, 400);
      setVisible(true);

      super.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

      // 이벤트등록
      insert.addActionListener(this);
      update.addActionListener(this);
      delete.addActionListener(this);
      quit.addActionListener(this);
      Serach.addActionListener(this);

      // 모든레코드를 검색하여 DefaultTableModle에 올리기
      dao.userSelectAll(dt);

      // 첫번행 선택.
      if (dt.getRowCount() > 0)
         jt.setRowSelectionInterval(0, 0);

   }// 생성자끝


   /**
    * 가입/수정/삭제/검색기능을 담당하는 메소드
    */

   public void actionPerformed(ActionEvent e) {
      if (e.getSource() == insert) {// 가입 메뉴아이템 클릭
         new UserJDailogGUI(this, "Registration");

      } else if (e.getSource() == update) {// 수정 메뉴아이템 클릭
         new UserJDailogGUI(this, "Edit");

      } else if (e.getSource() == delete) {// 삭제 메뉴아이템 클릭
         // 현재 Jtable의 선택된 행과 열의 값을 얻어온다.
         int row = jt.getSelectedRow();
         System.out.println("Choosed Raw : " + row);

         Object obj = jt.getValueAt(row, 0);// 행 열에 해당하는 value
         System.out.println("Value : " + obj);

         if (dao.userDelete(obj.toString()) > 0) {
            UserJDailogGUI.messageBox(this, "Record is deleted");

            // 리스트 갱신
            dao.userSelectAll(dt);
            if (dt.getRowCount() > 0)
               jt.setRowSelectionInterval(0, 0);

         } else {
            UserJDailogGUI.messageBox(this, "Record is not deleted");
         }

      } else if (e.getSource() == quit) {// 종료 메뉴아이템 클릭
         System.exit(0);

      } 

      else if (e.getSource() == Serach) {// 검색 버튼 클릭
         // JComboBox에 선택된 value 가져오기
         String fieldName = combo.getSelectedItem().toString();
         System.out.println("Field Name " + fieldName);
          
         if (fieldName.trim().equals("ALL")) {// 전체검색
            dao.userSelectAll(dt);
            if (dt.getRowCount() > 0)
               jt.setRowSelectionInterval(0, 0);
         } else {
            if (jtf.getText().trim().equals("")) {
               UserJDailogGUI.messageBox(this, "Please write word whatever you want");
               jtf.requestFocus();
            } else {// 검색어를 입력했을경우
               dao.getUserSearch(dt, fieldName, jtf.getText());
               if (dt.getRowCount() > 0)
                  jt.setRowSelectionInterval(0, 0);
            }
         }
      }

   }// actionPerformed()----------

}