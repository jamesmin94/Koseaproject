import javax.swing.*;
import javax.swing.JMenuBar;

class MenuPane extends JMenuBar {
	JMenu fileM, editM, TourismInformationM;
	JMenuItem newM, openM, saveM, exitM, cutM, copyM, pasteM, ConnectM, TripAdvisorM;

	public MenuPane() {
		fileM = new JMenu("File");
		editM = new JMenu("Edit");
		TourismInformationM = new JMenu("More Tourism Information about FL"); //Tourism Information 메뉴 추가함   
		newM = new JMenuItem("Greeting Message");
		openM = new JMenuItem("Open");
		saveM = new JMenuItem("Save");
		exitM = new JMenuItem("Exit");
		ConnectM = new JMenuItem("Connect");
		cutM = new JMenuItem("Cut");
		copyM = new JMenuItem("Copy");
		pasteM = new JMenuItem("Paste");  
		TripAdvisorM = new JMenuItem("TripAdvisor Florida Section");
		
		fileM.add(newM);
		fileM.add(openM);
		fileM.add(saveM);
		fileM.add(exitM);
		editM.add(cutM);
		editM.add(copyM);
		editM.add(pasteM);
		TourismInformationM.add(TripAdvisorM); //Menu바 에드   
		
		this.add(fileM);
		this.add(editM);
		this.add(TourismInformationM);
	}

}
