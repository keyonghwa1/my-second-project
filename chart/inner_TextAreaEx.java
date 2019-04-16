package chart;

import java.awt.Container;
import java.awt.FlowLayout;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class inner_TextAreaEx extends JFrame{
	JTextArea ta = new JTextArea(10, 25);
	String song_str = "";
	
	public inner_TextAreaEx(ArrayList<chartVO> list) {
		Container c = getContentPane();
		c.setLayout(new FlowLayout()); 
		
		c.add(new JScrollPane(ta));
		for(int i = 0; i < list.size(); i++) {
			boolean same = false;
			for(int j = 0; j < i; j++) {
				if(list.get(j).getSong().equals(list.get(i).getSong())) {
					same = true;
					break;
				}
			}
			if(same) {
				continue;
			}
			song_str += list.get(i).getSong() + "\n";
			song_str += "============\n";
			
		}
		ta.setText(song_str);

		setSize(300,300);
		setVisible(true);
	}
}
