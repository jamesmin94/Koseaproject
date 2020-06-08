
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
    * South ������ �߰��� Componet��
    */
   JPanel p = new JPanel();
   String[] comboName = { "  ALL  ", "  ID  ", " name ", " address " };

   JComboBox combo = new JComboBox(comboName);
   JTextField jtf = new JTextField(20);
   JButton Serach = new JButton("Search");

   UserDefaultJTableDAO dao = new UserDefaultJTableDAO();

   /**
    * ȭ�鱸�� �� �̺�Ʈ���
    */
   public MenuJTabaleExam() {

      super("Sunshine State Tourism Attraction Explorer");

      // �޴��������� �޴��� �߰�
      m.add(insert);
      m.add(update);
      m.add(delete);
      m.add(quit);
      // �޴��� �޴��ٿ� �߰�
      mb.add(m);

      // �����쿡 �޴��� ����
      setJMenuBar(mb);

      // South����
      p.setBackground(Color.BLUE);
      p.add(combo);
      p.add(jtf);
      p.add(Serach);

      add(jsp, "Center");
      add(p, "South");

      setSize(1200, 400);
      setVisible(true);

      super.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

      // �̺�Ʈ���
      insert.addActionListener(this);
      update.addActionListener(this);
      delete.addActionListener(this);
      quit.addActionListener(this);
      Serach.addActionListener(this);

      // ��緹�ڵ带 �˻��Ͽ� DefaultTableModle�� �ø���
      dao.userSelectAll(dt);

      // ù���� ����.
      if (dt.getRowCount() > 0)
         jt.setRowSelectionInterval(0, 0);

   }// �����ڳ�


   /**
    * ����/����/����/�˻������ ����ϴ� �޼ҵ�
    */

   public void actionPerformed(ActionEvent e) {
      if (e.getSource() == insert) {// ���� �޴������� Ŭ��
         new UserJDailogGUI(this, "Registration");

      } else if (e.getSource() == update) {// ���� �޴������� Ŭ��
         new UserJDailogGUI(this, "Edit");

      } else if (e.getSource() == delete) {// ���� �޴������� Ŭ��
         // ���� Jtable�� ���õ� ��� ���� ���� ���´�.
         int row = jt.getSelectedRow();
         System.out.println("Choosed Raw : " + row);

         Object obj = jt.getValueAt(row, 0);// �� ���� �ش��ϴ� value
         System.out.println("Value : " + obj);

         if (dao.userDelete(obj.toString()) > 0) {
            UserJDailogGUI.messageBox(this, "Record is deleted");

            // ����Ʈ ����
            dao.userSelectAll(dt);
            if (dt.getRowCount() > 0)
               jt.setRowSelectionInterval(0, 0);

         } else {
            UserJDailogGUI.messageBox(this, "Record is not deleted");
         }

      } else if (e.getSource() == quit) {// ���� �޴������� Ŭ��
         System.exit(0);

      } 

      else if (e.getSource() == Serach) {// �˻� ��ư Ŭ��
         // JComboBox�� ���õ� value ��������
         String fieldName = combo.getSelectedItem().toString();
         System.out.println("Field Name " + fieldName);
          
         if (fieldName.trim().equals("ALL")) {// ��ü�˻�
            dao.userSelectAll(dt);
            if (dt.getRowCount() > 0)
               jt.setRowSelectionInterval(0, 0);
         } else {
            if (jtf.getText().trim().equals("")) {
               UserJDailogGUI.messageBox(this, "Please write word whatever you want");
               jtf.requestFocus();
            } else {// �˻�� �Է��������
               dao.getUserSearch(dt, fieldName, jtf.getText());
               if (dt.getRowCount() > 0)
                  jt.setRowSelectionInterval(0, 0);
            }
         }
      }

   }// actionPerformed()----------

}