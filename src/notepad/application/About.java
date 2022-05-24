package notepad.application;

import java.awt.Font;
import javax.swing.*;

public class About extends JFrame{
    About(){
        setSize(600,380);
        setTitle("About Notepad");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        ImageIcon icon = new ImageIcon(getClass().getResource("Notepad.png"));
        setIconImage(icon.getImage());
        setLayout(null);
        
        JLabel img = new JLabel(new ImageIcon(getClass().getResource("Notepad.png")));
        img.setBounds(50,50,96,96);
        add(img);
        
        JLabel text = new JLabel("<html>Welcome to Notepad<br>Notepad is a simple text editor for Microsoft Windows<br>and a basic text-editing program which enables computer users to create documents</html>");
        text.setBounds(160,0,400,300);
        text.setHorizontalAlignment(SwingConstants.CENTER);
	text.setVerticalAlignment(SwingConstants.CENTER);
        text.setFont(new Font("Arial",Font.BOLD,15));
        add(text);
        
        JLabel rights = new JLabel("All Rights Reserved @2021");
        rights.setBounds(10,300,200,30);
        rights.setFont(new Font("Arial",Font.BOLD,15));
        add(rights);
    }
}
