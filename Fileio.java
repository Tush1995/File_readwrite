import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.RandomAccess;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

class View extends JFrame{
	JLabel l1;
	public JTextField t1,lt1;
	public JButton b1,b2,b3;
	JPanel p1,p2;
	View(){
		l1=new JLabel("Enter file name");
		lt1=new JTextField(20);
		b1=new JButton("READ");
		b2=new JButton("WRITE");
		b3=new JButton("EXITS");
		t1=new JTextField(20);
		
		p1=new JPanel(new GridLayout(2,1));
		p1.add(l1);p1.add(lt1);
		p2=new JPanel(new GridLayout(3, 1));
		p2.add(b1);p2.add(b2);p2.add(b3);
		
		this.add(p1);
		this.add(p2);
		this.add(t1);
		setLayout(new GridLayout(1,1));
		this.setVisible(true);
		this.setSize(300,300);
		Controler cc = new Controler(this);
		b1.addActionListener(cc);
		b2.addActionListener(cc);
		b3.addActionListener(cc);
	}
	
	
}
class Controler implements ActionListener{
   
	View v;
     public Controler(View v)
     {
    	 this.v=v;
     }
 
	public void actionPerformed(ActionEvent e){
		
		if(e.getSource()==v.b1)
		{
			String fname = v.lt1.getText();
			try {
				
				System.out.println(fname);
			    Fileio m= new Fileio(fname);
				String rfile = m.readFile();
				
				
	            v.t1.setText(rfile);		
			
			} catch (IOException e1) {
				
				e1.printStackTrace();
			}
		}
      
		if(e.getSource()==v.b3){
			String fname = v.lt1.getText();
			try{
				File f= new File(fname);
				
				if(f.exists()==true){
					v.t1.setText("File is Exist/available");
				}
				else{
					v.t1.setText("file is not existed/not available");
				}
				
			}catch(Exception e1)
			{
				e1.printStackTrace();
			}
			
		}
		if(e.getSource()==v.b2){
			String fname = v.lt1.getText();
			try{
				RandomAccessFile r = new RandomAccessFile(fname,"rw");
				String data = v.t1.getText();
				r.write(data.getBytes());
			}catch(Exception e2){
				e2.printStackTrace();
			}
			
		}
	
}

	
}


public class Fileio {
	String fname;
	public Fileio(String fname)
	{
		this.fname=fname;
	}
	String readFile () throws IOException
	{
		int i=0;
		String s="";
		File f= new File(fname);
	
		FileInputStream fis = new FileInputStream(f);

		while((i=fis.read())!=-1){
			System.out.println(i);
			s=s+(char)i;
		}
		return s;
	}
	public static void main(String[] args) {
		
		View v= new View();
		
	}

}