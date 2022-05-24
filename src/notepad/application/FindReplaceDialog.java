package notepad.application;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class FindReplaceDialog implements ActionListener{

    JDialog findReplace;
    JLabel lb6,lb7;
    JTextField tf1,tf2;
    JButton b1,b2;
	
    String main_text,search_text,replace_text;
        
    FindReplaceDialog(){
        findReplace = new JDialog();
        findReplace.setTitle("Find Replace");
        findReplace.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	findReplace.setSize(370,300);
	findReplace.setLayout(null);
	findReplace.setLocation(100,100);
		
	lb6 = new JLabel("Find");
	lb7 = new JLabel("Replace");
		
	lb6.setBounds(50,50,50,30);
	lb7.setBounds(50,130,50,30);
		
	tf1 = new JTextField();
	tf2 = new JTextField();
		
	tf1.setBounds(120,50,200,30);
	tf2.setBounds(120,130,200,30);
		
	b1 = new JButton("Find");
	b2 = new JButton("Replace");
		
	b1.setBounds(60,200,100,30);
	b2.setBounds(210,200,100,30);
		
	b1.addActionListener(this);
	b2.addActionListener(this);
		
	findReplace.add(lb6);
	findReplace.add(lb7);
	findReplace.add(tf1);
	findReplace.add(tf2);
	findReplace.add(b1);
	findReplace.add(b2);
        
        findReplace.setVisible(true);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==b1){
            main_text = NotepadApplication.textArea.getText();
            search_text = tf1.getText();
			
            int start_index = main_text.indexOf(search_text);
            int len = search_text.length();
			
            NotepadApplication.textArea.select(start_index,start_index+len);
            NotepadApplication.textArea.requestFocus();
        }
        else if(e.getSource()==b2){
            
            replace_text = tf2.getText();
            NotepadApplication.textArea.replaceSelection(replace_text);
        }
    }
    
    public static void main(String args[]){
        new FindReplaceDialog();
    }
}
