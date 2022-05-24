package notepad.application;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.*;

class FontDialog implements ActionListener{
    JDialog fontDialog;
    JLabel lb1,lb2,lb3,lb4,lb5;
    JComboBox cb1,cb2,cb3;
    JButton apply,cancel;
	
    int style_num;
    int size[] = new int[32];
	
    String style[] = {"Plain","Bold","Italic","Bold Italic"};
    String font[] = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
    Border border = BorderFactory.createLineBorder(Color.BLACK);
	
    String font_name;
    String style_name;
    int font_size;
    
    FontDialog(){
        fontDialog = new JDialog();
        fontDialog.setTitle("Select Font");
        fontDialog.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	fontDialog.setSize(550,450);
	fontDialog.setLocation(100,100);
	fontDialog.setLayout(null);
		
	lb1 = new JLabel("Font:");
	lb2 = new JLabel("Font Style:");
	lb3 = new JLabel("Size:");
		
	lb1.setBounds(50,50,100,30);
	lb2.setBounds(250,50,100,30);
	lb3.setBounds(400,50,100,30);
		
	cb1 = new JComboBox(font);
	cb2 = new JComboBox(style);
	cb3 = new JComboBox();
		
	cb1.setBounds(50,100,150,30);
	cb2.setBounds(250,100,100,30);
	cb3.setBounds(400,100,100,30);
		
	for(int i=10,j=0; i<72; i+=2,j++)
	{
            size[j] = i;
            cb3.addItem(size[j]);
	}
		
	cb1.setSelectedItem("Arial");
	cb2.setSelectedItem("Plain");
	cb3.setSelectedItem(20);
		
	cb1.addActionListener(this);
	cb2.addActionListener(this);
	cb3.addActionListener(this);
	
	lb4 = new JLabel("Sample Text: ");
	lb4.setBounds(200,200,150,30);
	lb4.setFont(new Font("Arial",Font.BOLD,15));
		
	lb5 = new JLabel("AaBbCcDd");
	lb5.setFont(new Font("Arial",Font.PLAIN,20));
	lb5.setBounds(200,230,300,100);
	lb5.setBorder(BorderFactory.createCompoundBorder(border,BorderFactory.createEmptyBorder(10, 10, 10, 10)));
	lb5.setHorizontalAlignment(SwingConstants.CENTER);
	lb5.setVerticalAlignment(SwingConstants.CENTER);
	
	apply = new JButton("Apply");
	apply.setBounds(150,350,100,30);
	apply.addActionListener(this);
		
	cancel = new JButton("Cancel");
	cancel.setBounds(300,350,100,30);
	cancel.addActionListener(this);
		
	cb1.setFocusable(false);
	cb2.setFocusable(false);
	cb3.setFocusable(false);
	apply.setFocusable(false);
	cancel.setFocusable(false);
		
	fontDialog.add(lb1);
	fontDialog.add(lb2);
	fontDialog.add(lb3);
	fontDialog.add(lb4);
	fontDialog.add(lb5);
	fontDialog.add(cb1);
	fontDialog.add(cb2);
	fontDialog.add(cb3);
	fontDialog.add(apply);
	fontDialog.add(cancel);
        
        fontDialog.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        font_name = (String)cb1.getSelectedItem();
	style_name = (String)cb2.getSelectedItem();
	font_size = (int)cb3.getSelectedItem();
			
	if(e.getSource()==cb1 || e.getSource()==cb2 || e.getSource()==cb3 )
	{
            if(style_name.equals("Plain"))
            {
		style_num = Font.PLAIN;
            }
            else if(style_name.equals("Italic"))
            {
		style_num = Font.ITALIC;
            }
            else if(style_name.equals("Bold"))
            {
		style_num = Font.BOLD;
            }
            else if(style_name.equals("Bold Italic"))
            {
		style_num = Font.BOLD|Font.ITALIC;
            }
				
            lb5.setFont(new Font(font_name,style_num,font_size));
	}
	if(e.getSource()==apply)
	{
            NotepadApplication.textArea.setFont(new Font(font_name,style_num,font_size));
            fontDialog.dispose();
	}			
	if(e.getSource()==cancel)
            fontDialog.dispose();
    }
}
