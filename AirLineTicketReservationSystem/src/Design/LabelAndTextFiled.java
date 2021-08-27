package Design;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class LabelAndTextFiled extends JPanel {
	JTextField txt;
	public LabelAndTextFiled(String s,String s1) {
		JLabel lbl= new JLabel(s);
		txt = new JTextField();
		txt.setText(s1);
		txt.setColumns(20);
		
		add(lbl);
		add(txt);	
		setMaximumSize(new Dimension(500,60));
		
	}
	public String getText() {
		return txt.getText();
	}
	public void setText(String text) {
		txt.setText(text);
	}

}
