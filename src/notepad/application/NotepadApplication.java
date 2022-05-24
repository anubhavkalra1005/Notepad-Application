package notepad.application;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.filechooser.*;
import java.io.*;

public class NotepadApplication implements ActionListener{
    
    public static JFrame fr;
    JMenuBar bar;
    JMenu file,edit,format,help;
    JMenuItem item[] = new JMenuItem[11];
    public static JTextArea textArea;
    JScrollPane scrollPane;
    JCheckBoxMenuItem wordwrap;
    
    String item_name[] = {"New","Open","Save","Print","Close","Cut","Copy","Paste","Find & Replace","Font","About Notepad"};
    
    int i,status;
    
    NotepadApplication()
    {
        fr = new JFrame();
	fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	fr.setSize(600,500);
	fr.setTitle("Notepad Application");
	fr.setLocationRelativeTo(null);
        
        menubar();
        
	textArea = new JTextArea();
	textArea.setFont(new Font("Lucida Console",Font.PLAIN,20));
	scrollPane = new JScrollPane(textArea);
	
	fr.add(scrollPane);
	fr.setVisible(true);
    }
    
    public void menubar()
    {
	bar = new JMenuBar();
	
	file = new JMenu("File");
        edit = new JMenu("Edit");
	format = new JMenu("Format");
	help = new JMenu("Help");
	
	for(i=0; i<item.length; i++)
	{
            item[i] = new JMenuItem(item_name[i]);
            item[i].addActionListener(this);
	}
	for(i=0; i<5; i++)
	{
            file.add(item[i]);
	}
	for(i=5; i<9; i++)
	{
            edit.add(item[i]);
	}
	
        wordwrap = new JCheckBoxMenuItem("Word Wrap");  
        
        wordwrap.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
            
                if(wordwrap.getState()){
                textArea.setLineWrap(true);
                scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
                } else {
                    textArea.setLineWrap(false);
                  }
            }
        });
       
        format.add(wordwrap);
        
	format.add(item[i]);
	help.add(item[i+1]);
		
	bar.add(file);
	bar.add(edit);
	bar.add(format);
	bar.add(help);
	
        item[0].setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, KeyEvent.CTRL_MASK));
        item[1].setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, KeyEvent.CTRL_DOWN_MASK));
        item[2].setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.CTRL_DOWN_MASK));
        item[3].setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, KeyEvent.CTRL_DOWN_MASK));
        item[4].setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4,KeyEvent.ALT_DOWN_MASK));
        item[5].setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, KeyEvent.CTRL_DOWN_MASK));
        item[6].setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, KeyEvent.CTRL_DOWN_MASK));
        item[7].setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, KeyEvent.CTRL_DOWN_MASK));
        item[8].setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H, KeyEvent.CTRL_DOWN_MASK));
        
	fr.setJMenuBar(bar);
    }
   
    @Override
    public void actionPerformed(ActionEvent e) {
       
        if(e.getActionCommand().equalsIgnoreCase("New"))
	{
            textArea.setText(null);
	}
	if(e.getActionCommand().equalsIgnoreCase("Open"))
	{
            JFileChooser filechooser = new JFileChooser();
            FileNameExtensionFilter textFilter = new FileNameExtensionFilter("Only Text Files(.txt)","txt");
            filechooser.setAcceptAllFileFilterUsed(false);
            filechooser.addChoosableFileFilter(textFilter);
            
            int action = filechooser.showOpenDialog(null);
            if(action!=JFileChooser.APPROVE_OPTION){
                return;
            }
            else{
                String fileName = filechooser.getSelectedFile().toString();
            
                try {
                    BufferedReader reader = new BufferedReader(new FileReader(filechooser.getSelectedFile()));
                    textArea.read(reader,null);
                    fr.setTitle(fr.getTitle()+" - "+fileName);
                } catch (IOException ex) {ex.printStackTrace();}
            }
	}
	if(e.getActionCommand().equalsIgnoreCase("Save"))
	{
            JFileChooser filechooser = new JFileChooser();
            FileNameExtensionFilter textFilter = new FileNameExtensionFilter("Only Text Files(.txt)","txt");
            filechooser.setAcceptAllFileFilterUsed(false);
            filechooser.addChoosableFileFilter(textFilter);
            
            int action = filechooser.showSaveDialog(null);
            if(action!=JFileChooser.APPROVE_OPTION){
                return;
            }
            else{
                String fileName = filechooser.getSelectedFile().getAbsolutePath().toString();
                if(!fileName.contains(".txt")){
                    fileName+=".txt";
                }
                
                try {
                    BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
                    textArea.write(writer);
                    fr.setTitle(fr.getTitle()+" - "+fileName);
                } catch (IOException ex) {ex.printStackTrace();}
            }
	}
	if(e.getActionCommand().equalsIgnoreCase("Print"))
	{
            try{
                textArea.print();
            } catch(Exception ex){}
	}
	if(e.getActionCommand().equalsIgnoreCase("Close"))
	{
            fr.dispose();
	}
	if(e.getActionCommand().equalsIgnoreCase("Cut"))
	{
            textArea.cut();
	}
	if(e.getActionCommand().equalsIgnoreCase("Copy"))
	{
            textArea.copy();
	}
	if(e.getActionCommand().equalsIgnoreCase("Paste"))
	{
            textArea.paste();
	}
	if(e.getActionCommand().equalsIgnoreCase("Find & Replace"))
	{
            new FindReplaceDialog();
            //fr.setEnabled(false);
	}
	if(e.getActionCommand().equalsIgnoreCase("Font"))
	{
            new FontDialog();
	}
	if(e.getActionCommand().equalsIgnoreCase("About Notepad"))
	{
            new About().setVisible(true);
	}
    }
     
    public static void main(String[] args) throws Exception{
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        new NotepadApplication();
    }
}
