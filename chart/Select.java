package chart;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.TitledBorder;


public class Select extends JFrame {
	Crawler cr;
	chart_db c_db = new chart_db();
	static String csvPath;
	int count;
	
	ArrayList<String> genre = new ArrayList<>();
	Artist ar;
	
	private JPanel contentPane;
	private JTextField textField;

	private JCheckBox checkBox1;
	private JCheckBox checkBox2;
	private JCheckBox checkBox3;
	private JCheckBox checkBox4;
	private JCheckBox checkBox5;

	private JComboBox comboBox;
	private JComboBox comboBox1;

	// create frame
	
	public Select() {
		setTitle("음악 찾기");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setBounds(100, 100, 650, 400);

		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(null);

		Container con = getContentPane();
		con.setLayout(null);
		
		JTextField tf = new JTextField("날짜 입력"); 
		tf.setBounds(40, 45, 120, 30);
		con.add(tf);
		
		JButton btn = new JButton("크롤링"); 
		btn.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				String str_date = tf.getText(); 
				tf.setText(""); 
				int date = Integer.parseInt(str_date); 
				cr = new Crawler(date);
				System.out.println("크롤링 정보 보기: " + cr);
				
				System.out.println("/////////////////////////////////");

				for(int i = 0; i < cr.list.size(); i++) {
					c_db.insertData(cr.list.get(i).getGenre()
									, cr.list.get(i).getRank()
									, cr.list.get(i).getSong()
									, cr.list.get(i).getArtist()
									, cr.list.get(i).getAlbum());
				}
				
			}
		});
		btn.setBounds(250, 35, 80, 50);
		contentPane.add(btn);

		JPanel panel = new JPanel(); 
		panel.setBorder(new TitledBorder(null, "날짜를 입력 하세요. 예) 20180919", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(12, 26, 230, 62);
		contentPane.add(panel);

		JPanel panel1 = new JPanel(); 
		panel1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "노래 종류", TitledBorder.LEADING,
				TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel1.setBounds(12, 110, 320, 62);
		contentPane.add(panel1);

		checkBox1 = new JCheckBox("종합");
		panel1.add(checkBox1);
		checkBox2 = new JCheckBox("ballad");
		panel1.add(checkBox2);
		checkBox3 = new JCheckBox("dance");
		panel1.add(checkBox3);
		checkBox4 = new JCheckBox("hiphop");
		panel1.add(checkBox4);
		checkBox5 = new JCheckBox("rock");
		panel1.add(checkBox5);


		JButton button1 = new JButton("출력");
		button1.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				genre.clear(); 
				
				if( (checkBox1.isSelected()) ) {
					genre.add("all");
					
					System.out.println(genre);
				}
				
				if( (checkBox2.isSelected()) ) {
					genre.add("ballad");
					
					System.out.println(genre);

				}
				
				if( (checkBox3.isSelected()) ) {
					genre.add("dance");
					
					System.out.println(genre);

				}
				
				if( (checkBox4.isSelected()) ) {
					genre.add("hiphop");
					
					System.out.println(genre);

				}
				
				if( (checkBox5.isSelected()) ) {
					genre.add("rock");
					
					System.out.println(genre);

				}
				
				if( genre.size() == 0 ) {
//					System.out.println("장르를 체크하세요");
					return;
				}
								
				new TextAreaEx(genre, c_db);
			}
		});
		button1.setBounds(340, 115, 100, 50);
		contentPane.add(button1);
		
		JButton button2 = new JButton("DB 초기화");
		button2.addActionListener(new ActionListener() {			

			@Override
			public void actionPerformed(ActionEvent e) {
				c_db.deleteData();				
			}
		});
		button2.setBounds(450, 115, 120, 50);
		contentPane.add(button2);

		JButton btn1 = new JButton("파일로 저장");
		btn1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				FileWrite(cr.list);
				
			}
		});
		btn1.setBounds(450, 35, 120, 50);
		contentPane.add(btn1);

		JButton btn2 = new JButton("가수 입력");
		btn2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ar = new Artist(c_db);
				
			}
		});
		btn2.setBounds(340, 35, 100, 50);
		contentPane.add(btn2);
		
		setVisible(true);
	}
	
	// launch application
	public static void main(String[] args) {
		new Select();
	}// main ends
	
	static void FileWrite(ArrayList<chartVO> list) {

		String projectPath 	= System.getProperty("user.dir");
		csvPath = projectPath + "\\lib\\chartlist.csv";
		
		try {
						
			BufferedWriter writer = new BufferedWriter( new FileWriter(csvPath) );
			writer.write("Genre,Rank,Song,Artist,Album\n");
			
			for(int i = 0; i < list.size(); i++) {
				chartVO c = list.get(i);
				
				writer.write( String.format("%s,%s,%s,%s,%s\n", 
												c.getGenre(),
												c.getRank(),
												c.getSong(),
												c.getArtist(),
												c.getAlbum() ) );
			} 
			
			writer.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}


