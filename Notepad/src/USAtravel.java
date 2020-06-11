/// 메인 메뉴
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.*;

public class USAtravel {
   
   

   
   
   
   
   
   public USAtravel(){

      // 프레임 생성
      JFrame frm = new JFrame("Welcome to the Sunshine state!");

      // 프레임 크기 설정
      frm.setSize(576, 456);

      // 프레임을 화면 가운데에 배치
      frm.setLocationRelativeTo(null);

      // 프레임을 닫았을 때 메모리에서 제거되도록 설정
      frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

      // 레이아웃 설정
      frm.getContentPane().setLayout(null);
      
      
      
      // 버튼 생성
      JButton btn1 = new JButton("Attractions");
      JButton btn2 = new JButton("Attractions");
      JButton btn3 = new JButton("Journey`s Note");
      JButton btnNewButton_3 = new JButton("Ferre Park");
      
      
      
      
      
      
      
      
      //btn2.setIcon(defaultIcon);
      btnNewButton_3.setIcon(new ImageIcon("C:\\Users\\user\\git\\Koseaproject\\Notepad\\img\\florida.png"));
      
      // ★ 버튼 위치와 크기 설정
      btn1.setBounds(32, 326, 146, 35);
      btn2.setBounds(210, 326, 146, 35);
      btn3.setBounds(388, 326, 134, 35);
      btnNewButton_3.setBounds(32, 36, 490, 259); //이미지 사이즈 490 259
      
      
      
      
      
      
      
      // ★ 프레임에다가 버튼 추가
      frm.getContentPane().add(btn1);
      //frm.getContentPane().add(btn2);
      frm.getContentPane().add(btn3);
      frm.getContentPane().add(btnNewButton_3);
      
      
      
      
      //Label 설정 
      JLabel lbl = new JLabel();
      lbl.setBounds(32, 371, 497, 36);
      //라벨 사이즈와 택스트 조정 
      lbl.setText("Visit Florida! during travel in Florida. You can visit anywhere!");
      lbl.setBackground(Color.BLUE);
      frm.getContentPane().add(lbl);
      lbl.setFont(new Font("Arial Rounded MT Bold", Font.BOLD,15));
      
      
      // 프레임이 보이도록 설정
      frm.setVisible(true);
            
      
      
      
      
      
      
      
      
      
      btn1.addActionListener(new ActionListener() {
      
      @Override
      public void actionPerformed(ActionEvent e) {
         MenuJTabaleExam mt = new MenuJTabaleExam();
         mt.setVisible(true);
         
         
      }
   });
      
      btnNewButton_3.addActionListener(new ActionListener(){

		@Override
		public void actionPerformed(ActionEvent e) {
			
			if(e.getSource() == btnNewButton_3){
				try {
					Process p = Runtime.getRuntime().exec(
							"C:\\Program Files (x86)\\Google\\Chrome\\Application\\chrome.exe \"https://www.instagram.com/visitflorida/?hl=ko/\"");
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
		}
    	  
    	  
    	  
    	  
    	  
    	  
      });
      
    
      
      
      
      
      btn3.addActionListener(new ActionListener() {
      
      @Override
      public void actionPerformed(ActionEvent e) {
         SwingTest t = new SwingTest(); 
         t.setVisible(true);
         
         
         
      }
   });

   



} 






public static void main(String[] args) {
   USAtravel us1 = new USAtravel();  
   
   
   
}










}