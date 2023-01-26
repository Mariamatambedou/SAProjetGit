import java.awt.Color;
	import java.awt.event.ActionEvent;
	import java.awt.event.ActionListener;
	import java.net.*;
	import java.io .*;
	import javax.swing.JButton;
	import javax.swing.JComboBox;
	import javax.swing.JFileChooser;
	import javax.swing.JFrame;
	import javax.swing.JPanel;
	import javax.swing.JScrollPane; 
	import javax.swing.JTextArea;
	import javax.swing.JTextField;
	public class cli extends JFrame implements ActionListener{
	    int port;
	    boolean etat=true;
	    Socket s;
	    ServerSocket sv;
	    JButton b; 
	    JTextField txtF;
	    JComboBox comB1;
	    JTextArea txtA;
	    JButton b1;
	    JPanel pan;
	    JScrollPane sc;
	    File f=null;
	    public cli(){
	 
	    	this.setTitle("serveur");
	        this.setBounds(0,0,300,400);
	        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        this.setLocation(100,100);
	        this.setLayout(null);
	        pan=new JPanel();
	        pan.setLayout(null);
	        pan.setBounds(0,0,this.getWidth(),this.getHeight());
	        pan.setBackground(Color.GREEN);
	        b=new JButton("envoie");
	        b.setBounds(100,320,100, 30);
	        b1=new JButton("Add File");
	        b1.setBounds(150,280,100, 30);
	        b1.addActionListener(new ActionListener() {
	 
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                f=null;
	                JFileChooser file=new JFileChooser();
	                file.showOpenDialog(null);
	                f=file.getSelectedFile();
	                //txtF.setText(f.toString());
	            }
	        });
	        txtF=new JTextField();
	        comB1=new JComboBox();
	        txtF.setBounds(10, 280, 130, 30);
	        comB1.setBounds(50, 10, 200, 30);        
	        txtA=new JTextArea();
	        sc=new JScrollPane(txtA);
	        sc.setBounds(20,50,242,220);
	        pan.add(b) ;
	        pan.add(b1) ;
	        pan.add(txtF);
	        pan.add(comB1);
	        pan.add(sc) ; 
	        this.add(pan);
	        this.setVisible(true);
	        b.addActionListener(this);
	 
	 
	        try {
	            //if(!txtF.getText().equalsIgnoreCase(null)){
		        sv = new ServerSocket(80);
//	                JOptionPane.showConfirmDialog(new JFrame(), "", "", JOptionPane.OK_OPTION);
		        while (etat) {              
		            Socket sok = sv.accept();
		            BufferedReader in = new BufferedReader( new InputStreamReader(sok.getInputStream()));
		            String message = in.readLine(); 
		            txtA.append("client dit :"+message+"\n");               
	              if(this.isVisible())
	              {
	                 etat=true;
	              }else{
	                 etat=false;
	              }
		   }
	         // }
		} catch (Exception e) { 
	      }	 
	   }
	 
	    @Override
	    public void actionPerformed(ActionEvent arg0) {
		       BufferedInputStream bis =null;
	               try { 
	                 bis =new BufferedInputStream(new FileInputStream(new File("s.avi")));
	                 Socket sock= new Socket("localhost",80);
	                 BufferedOutputStream bos =new BufferedOutputStream(sock.getOutputStream());
	 
	                 PrintStream out = new PrintStream(sock.getOutputStream());
	                 if(f!=null){
	                     out.println(f);
	                 }else{
	                 out.println(""+txtF.getText());
	                 }
	                 sock.close();   
	 
	   	       } catch (Exception e) {
	   	      }
		  }
	}


