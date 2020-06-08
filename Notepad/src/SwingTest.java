import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;

class SwingTest extends JFrame implements ActionListener {
	String filePath, fileName;
	JButton exitB, connectB,Disney;
	JTextArea ta;
	MenuPane mp;

	public SwingTest() {
		Disney = new JButton("Connect with WaltDisney World");
		exitB = new JButton("Exit");
		connectB = new JButton("Connect with Florida");

		ta = new JTextArea();

		JScrollPane sp = new JScrollPane(ta);

		mp = new MenuPane();
		this.setJMenuBar(mp);
		JPanel p = new JPanel();
		p.add(Disney);
		p.add(exitB);
		p.add(connectB);
		this.getContentPane().add("South", p);
		this.getContentPane().add("Center", sp);
		// this.add("South",p);
		// this.add("Center",sp);
		this.setTitle("Florida`s Journey Note");
		this.setBounds(200, 200, 500, 500);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		mp.exitM.addActionListener(this);
		mp.newM.addActionListener(this);
		mp.cutM.addActionListener(this);
		mp.copyM.addActionListener(this);
		mp.pasteM.addActionListener(this);
		mp.openM.addActionListener(this);
		mp.saveM.addActionListener(this);
		mp.TripAdvisorM.addActionListener(this);
		Disney.addActionListener(this);
		exitB.addActionListener(this);
		connectB.addActionListener(this);

	}

	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == Disney){
			try {
				Process p = Runtime.getRuntime().exec(
						"C:\\Program Files (x86)\\Google\\Chrome\\Application\\chrome.exe \"http://disneyworld.disney.go.com/\"");
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
		
		if (e.getSource() == connectB) {
			try {
				Process p = Runtime.getRuntime().exec(
						"C:\\Program Files (x86)\\Google\\Chrome\\Application\\chrome.exe \"http://www.visitflorida.com/en-us.html\"");
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
		if (e.getSource() == exitB || e.getSource() == mp.exitM) {
			// System.exit(0);
			if (JOptionPane.showConfirmDialog(this, "Do you want to exit this notepad?", "Exit Menu",
					JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE) == JOptionPane.YES_OPTION)
				System.exit(0);
		} else if (e.getSource() == mp.newM)
			ta.setText(
					"Welcome to the Sunshine State! We always welcome you!\nAfter searching for information from the Attractions section, you can write\ninformation about Florida whatever you want!\nEnjoy Sunshine State!"); //new 버튼 누르면 나오는 메소드  																																																			// 메세지
		else if (e.getSource() == mp.cutM)
			ta.cut();
		else if (e.getSource() == mp.copyM)
			ta.copy();
		else if (e.getSource() == mp.pasteM)
			ta.paste();
		else if (e.getSource() == mp.openM) {
			openDialog(); // 다이얼 로그 띄우기
			fileRead(); // 파일 입력 스트림
		} else if (e.getSource() == mp.saveM) {
			saveDialog();
			fileWrite();
		}else if(e.getSource() == mp.TripAdvisorM){
			try {
				Process p = Runtime.getRuntime().exec(
						"C:\\Program Files (x86)\\Google\\Chrome\\Application\\chrome.exe \"https://www.tripadvisor.com/ShowForum-g28930-i18-Florida.html\"");
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

	public void saveDialog() {
		FileDialog fd = new FileDialog(this, "Save", FileDialog.SAVE);
		fd.setVisible(true);
		filePath = fd.getDirectory();// 파일경로
		fileName = fd.getFile();// 파일이름
		System.out.println(filePath + fileName);
	}

	public void fileWrite() {
		try {
			PrintWriter pw = new PrintWriter(new FileWriter(filePath + fileName));
			String data = null;
			data = ta.getText();
			pw.write(data);
			pw.close();
		} catch (IOException io) {
			io.printStackTrace();
		}
	}

	public void openDialog() {
		FileDialog fd = new FileDialog(this, "Open", FileDialog.LOAD);
		fd.setVisible(true);
		filePath = fd.getDirectory();// 파일경로
		fileName = fd.getFile();// 파일이름
		System.out.println(filePath + fileName);
	}

	
	
	
	
	
	public void fileRead() {
		ta.setText("");
		try {
			BufferedReader br = new BufferedReader(new FileReader(filePath + fileName));
			// BufferedReader br = new BufferedReader(new FileReader(new
			// File(filePath,fileName));
			String data = null;
			while ((data = br.readLine()) != null) {
				// ta.setText(data);
				ta.append(data);
			}
			br.close();
		} catch (IOException io) {
			io.printStackTrace();
		}

	}

	public static void main(String[] args) {
		new SwingTest();

	}
}