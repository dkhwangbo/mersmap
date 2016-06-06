
package mersmap;

import javax.swing.JFrame;

import com.teamdev.jxbrowser.chromium.Browser;
import com.teamdev.jxbrowser.chromium.swing.BrowserView;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTabbedPane;
import javax.swing.JDesktopPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class mainFrame extends JFrame {
	private JTextField todayDate;
	JButton btnRun;
	JButton btnRoute;
	JButton btnDeploy;
	Browser browser;
	BrowserView browserView;
	JTabbedPane tabbedPane;
	JDesktopPane desktopPane;
	JInternalFrame internalFrame;
	JPanel panel1;
	JPanel panel2;
	JPanel panel3;
	JPanel panel4;
	JTable table1;
	JTable table2;
	JTable table3;
	JTable table4;
	String path;
	LatLng l = new LatLng();
	readData r;
	
	public mainFrame() throws Exception{

		
		
		getContentPane().setLayout(null);
		getContentPane().setSize(910, 550);
		todayDate = new JTextField(new SimpleDateFormat("yyyy년 MM월 dd일 현황").format(new Date()));
		todayDate.setBounds(12, 10, 135, 21);
		getContentPane().add(todayDate);
		todayDate.setColumns(10);
		
		btnRun = new JButton("수집");
		btnRun.setBounds(160, 9, 97, 23);
		getContentPane().add(btnRun);
		btnRun.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				try {
					r = new readData();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				table1.setModel(r.getModel1());
				table2.setModel(r.getModel2());
				table3.setModel(r.getModel3());
				table4.setModel(r.getModel4());
				//createMap(path + "map.html");
				createMap("visualdive.cartodb.com/viz/ede3d8ac-1016-11e5-81a0-0e4fddd5de28/embed_map");
				System.out.println("Run");
				
				
			}
		});
		
		
		
		createPage1();
		createPage2();
		createPage3();
		createPage4();
		
		tabbedPane = new JTabbedPane();
		tabbedPane.addTab("메르스 확진환자 발생 병원", panel1);
		tabbedPane.addTab("메르스 확진환자 경유 병원", panel2);
		tabbedPane.addTab("환자별 정보", panel3);
		tabbedPane.addTab("국가지정 입원치료(격리)병상 운영병원", panel4);
		tabbedPane.setBounds(12, 41, 285, 400);
		getContentPane().add(tabbedPane);
		tabbedPane.addChangeListener(new ChangeListener(){

			@Override
			public void stateChanged(ChangeEvent e) {
				// TODO Auto-generated method stub
				JTabbedPane pane = (JTabbedPane) e.getSource();
				if(pane.getSelectedIndex() == 0 || pane.getSelectedIndex() == 1 || pane.getSelectedIndex() == 3)
					createMap("https://www.google.com/maps/d/embed?mid=z-eOnRajCGag.k6m1J1J2nJWw");
				if(pane.getSelectedIndex() == 2){
					createMap("infogr.am/-7848686191150806");
//					System.out.println("clicked!");
//					String name;
//					Vector v = new Vector();
//					for(int i=0 ; i<table1.getRowCount() ; i++){
//						name = table1.getValueAt(i, 1).toString();
//						v.add(name);
//						
//						try {
//							System.out.println(name);
//							browser.executeJavaScript("var myLatlng = new google.maps.LatLng("+l.getLatLongPositions(name)[0]+","+l.getLatLongPositions(name)[1]+");\n" +
//							           "var marker = new google.maps.Marker({\n" +
//							           "    position: myLatlng,\n" +
//							           "    map: map,\n" +
//							           "    title: 'Hello World!'\n" +
//							           "});");
//						} catch (Exception e1) {
//							// TODO Auto-generated catch block
//							//e1.printStackTrace();
//							System.out.println("예외");
//						}
//					}
//					System.out.println(table1.getValueAt(0, 1));
//					System.out.println(table1.getRowCount());
//					try {
//						
//						System.out.println();
//						browser.executeJavaScript("var myLatlng = new google.maps.LatLng("+l.getLatLongPositions("삼성서울병원")[0]+","+l.getLatLongPositions("삼성서울병원")[1]+");\n" +
//						           "var marker = new google.maps.Marker({\n" +
//						           "    position: myLatlng,\n" +
//						           "    map: map,\n" +
//						           "    title: 'Hello World!'\n" +
//						           "});");
//						browser.executeJavaScript("var myLatlng = new google.maps.LatLng("+l.getLatLongPositions("서울대학교")[0]+","+l.getLatLongPositions("서울대학교")[1]+");\n" +
//						           "var marker = new google.maps.Marker({\n" +
//						           "    position: myLatlng,\n" +
//						           "    map: map,\n" +
//						           "    title: 'Hello World!'\n" +
//						           "});");
//					} catch (Exception e1) {
//						// TODO Auto-generated catch block
//						e1.printStackTrace();
//					}
				}
						
				
			}
			
		});
		
		desktopPane = new JDesktopPane();
		desktopPane.setBounds(310, 10, 660, 485);
		getContentPane().add(desktopPane);
		internalFrame = new JInternalFrame("Mers Map", false, false, false, false);
		desktopPane.add(internalFrame);
		
		browser = new Browser();
		browserView = new BrowserView(browser);
		
		internalFrame.getContentPane().add(browserView);
		internalFrame.setVisible(true);
		internalFrame.setResizable(false);
		internalFrame.setClosable(false);
		
		try {
			internalFrame.setMaximum(true);
		} catch (PropertyVetoException e) {
			e.printStackTrace();
		}
		getContentPane().add(desktopPane);
		
		JButton btnNewButton = new JButton("메르스 확진환자 이동경로");
		btnNewButton.setBounds(22, 451, 270, 23);
		getContentPane().add(btnNewButton);
		btnNewButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				createMap("visualdive.cartodb.com/viz/fe277094-0b4c-11e5-ad52-0e0c41326911/embed_map");
			}
		});
		
		
		JButton btnNewButton_1 = new JButton("월드 메르스 리포트");
		btnNewButton_1.setBounds(22, 480, 270, 23);
		getContentPane().add(btnNewButton_1);
		btnNewButton_1.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				createMap("a.tiles.mapbox.com/v4/visualdive.mc9ledfg/page.html?access_token=pk.eyJ1IjoidmlzdWFsZGl2ZSIsImEiOiI5eG9uUGI0In0.zJaCdJfvf46XiiONB06CtA#4/47.43/113.03");
				System.out.println("메르스 발생 국가 데이터맵");				
			}
		});
		
		
		path = mainFrame.class.getResource("").getPath().replaceFirst("/", "");
//		createMap(path + "map.html");
		
	}

	public void createPage1() {
		panel1 = new JPanel();
		panel1.setLayout(new BorderLayout(1, 1));
		panel1.add(new JPanel(), BorderLayout.NORTH);
		panel1.add(new JPanel(), BorderLayout.SOUTH);
		panel1.add(new JPanel(), BorderLayout.EAST);
		panel1.add(new JPanel(), BorderLayout.WEST);
		
		//table1 = new JTable(r.getModel1());
		table1 = new JTable();
		table1.setMaximumSize(new Dimension(1000, 1000));
		table1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		JScrollPane jscrollpane = new JScrollPane(table1);
		panel1.add(new JPanel().add(jscrollpane), BorderLayout.CENTER);
	}
	
	public void createPage2(){
		panel2 = new JPanel();
		panel2.setLayout(new BorderLayout(1, 1));
		panel2.add(new JPanel(), BorderLayout.NORTH);
		panel2.add(new JPanel(), BorderLayout.SOUTH);
		panel2.add(new JPanel(), BorderLayout.EAST);
		panel2.add(new JPanel(), BorderLayout.WEST);
		
		table2 = new JTable();
		
		table2.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		JScrollPane jscrollpane = new JScrollPane(table2);
		panel2.add(new JPanel().add(jscrollpane), BorderLayout.CENTER);
	}
	 
	public void createPage3(){
		panel3 = new JPanel();
		panel3.setLayout(new BorderLayout(1, 1));
		panel3.add(new JPanel(), BorderLayout.NORTH);
		panel3.add(new JPanel(), BorderLayout.SOUTH);
		panel3.add(new JPanel(), BorderLayout.EAST);
		panel3.add(new JPanel(), BorderLayout.WEST);
		
		table3 = new JTable();
		table3.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		JScrollPane jscrollpane = new JScrollPane(table3);
		panel3.add(new JPanel().add(jscrollpane), BorderLayout.CENTER);
	}
	
	public void createPage4(){
		panel4 = new JPanel();
		panel4.setLayout(new BorderLayout(1, 1));
		panel4.add(new JPanel(), BorderLayout.NORTH);
		panel4.add(new JPanel(), BorderLayout.SOUTH);
		panel4.add(new JPanel(), BorderLayout.EAST);
		panel4.add(new JPanel(), BorderLayout.WEST);
		
		table4 = new JTable();
		table4.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		JScrollPane jscrollpane = new JScrollPane(table4);
		panel4.add(new JPanel().add(jscrollpane), BorderLayout.CENTER);
	}
	
	
	
	public void createMap(String path){
		browser.loadURL(path);
	}
	
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		mainFrame m_f = new mainFrame();
		m_f.setSize(1000, 550);
		m_f.setTitle("Mers Analytics");
		m_f.setVisible(true);
		m_f.setResizable(false);
//		System.out.println(new LatLng().getLatLongPositions("삼성서울병원")[0]);
		
	}
}
