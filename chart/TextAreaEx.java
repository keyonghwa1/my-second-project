package chart;

import javax.swing.*;

import org.openqa.selenium.Keys;

import java.awt.event.*;
import java.util.ArrayList;
import java.awt.*;

public class TextAreaEx extends JFrame {
	JTextArea [] ta_arr; 
	
	public TextAreaEx(ArrayList<String> genre, chart_db db) {
		String str = "";
		ta_arr = new JTextArea[genre.size()];
		setTitle("장르별 노래 정보");
		
		Container c = getContentPane();
		
		for(int i = 0; i < genre.size(); i++) { 
			ta_arr[i] = new JTextArea(10, 25);
			if( genre.get(i).equals("all")) {
				str += "종합" + "\n";
				System.out.println("호홍" + str);
			}
			else {
				str += genre.get(i) + "\n";
				System.out.println(str);
			}
			
			ta_arr[i].setText(genre.get(i) + "\n");	
			ArrayList<chartVO> list = db.selectData_genre(genre.get(i));
			
			System.out.println(list.size()); // 10
			
			for(int j = 0; j < list.size(); j++) {
				System.out.println(list.get(j).getRank());
				str += list.get(j).getRank() + "\n";
				str += list.get(j).getSong() + "\n";
				str += list.get(j).getArtist() + "\n";
				str += list.get(j).getAlbum() + "\n";
				str += "=================\n";
			}
			
			ta_arr[i].setText(str);
			c.add(new JScrollPane(ta_arr[i]));
			str = "";
		}
		c.setLayout(new FlowLayout());
		
		
		setSize(1000,500);
		setVisible(true);
	}

	
}
