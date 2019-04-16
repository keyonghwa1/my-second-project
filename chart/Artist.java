package chart;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;


class Artist extends JFrame { 
	Container cp = getContentPane();
	JTextField textField = new JTextField("");
	public Artist(chart_db db){
		setTitle("singer 검색");
		cp.setLayout(null);
		
		textField.setBounds(20, 20, 120, 40);
		cp.add(textField);
		
		JButton btn = new JButton("확인");
		btn.setBounds(150, 20, 100, 40);
		btn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (textField.getText().equals("")) {

					return;
				}
				
				ArrayList<chartVO> list = db.selectData_artist(textField.getText());
				
				if( list.size() == 0 ) {

					return;
				}
				
				new inner_TextAreaEx(list);
				textField.setText("");
			}
		});
		
		
		cp.add(btn);
		
		setSize(300,300);
		setVisible(false); 
	}
	
}