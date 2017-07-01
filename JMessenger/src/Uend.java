import java.io.*;
import java.awt.*;
import java.awt.event.*;// jButton2.setIcon(new javax.swing.ImageIcon("E:\\go.png"));
import javax.swing.*;
import java.net.*;
import java.rmi.*;
import java.rmi.registry.*;

class Jpassword extends Frame implements ActionListener
{
		JButton bo,be;
		JLabel l1,l2,lt;
		JTextField t1,time;
		JPasswordField t2;
		Panel p1,pn,pe,ps,pw,psw;
		public static String nm="",sp="";
		public static int status=0,activate=0;
		

		Jpassword()
		{
				super("StartUp");
				l1=new JLabel("Name");
				l2=new JLabel("password");
				lt=new JLabel("Welcome");
				t1=new JTextField(5);
				t2=new JPasswordField(10);
				bo=new JButton("SignUp");
				bo.setToolTipText("New One ? Then SignUp for free");
				bo.addActionListener(this);
				be=new JButton("Login");
				be.setToolTipText("Already a User ? then log in");
				be.addActionListener(this);
				
			
				p1=new Panel();
				p1.setLayout(new GridLayout(3,2,25,25));
			//	p1.add(lt);
				p1.add(l1);
				p1.add(t1);
				p1.add(l2);
				p1.add(t2);
				p1.add(bo);
				p1.add(be);
				
				add(p1,"Center");				
		

				pn=new Panel();			//only for border spaces
				add(pn,"North");
				pe=new Panel();
				add(pe,"East");
				pw=new Panel();
				add(pw,"West");
				ps=new Panel();
				add(ps,"South");
			
				setSize(300,200);
				setLocation(600,300);
				setResizable(false);
				setVisible(true);
				
		}

		

		public void actionPerformed(ActionEvent e)
		{
				JButton b=(JButton)e.getSource();
				
				nm=t1.getText();
				sp=t2.getText();
				if(b==be)
					status=0;//login
				else
					status=1;//new
				activate=1;
				removeNotify();
				
		}
}

class Uend extends Frame implements Runnable,ActionListener,KeyListener
{
		JLabel l1,l2,lol;
		JTextArea tfc,tol;
		ScrollPane sc,scol;
		JTextField t,t2;
		Thread th,rfte;
		JButton bs,br,cd,bc,sf;
		JList j;
		String []arr=null;
		String userlist[]=null;
		String rslt="";
		int ok=0,brk=0,iscn=0,key1,key2;
		static int gameon=0;
		char rs;
		Socket s;  
	    DataInputStream dis=null;
		DataOutputStream dos=null;
		String nm="",ip="";
		ReceiveMessageInterface rmiServer;
       Registry registry;
       String serverAddress="Localhost";
       String serverPort="3232";
		Font f,f1;
		GRmiS robj;
		GRmi gobj;
		Uend(String sa)throws Exception
		{
				super("JMessenger");
				f=new Font("Microsoft JhengHei Light",Font.PLAIN,15);
				f1=new Font("Microsoft JhengHei Light",Font.PLAIN,13);
				setLayout(null);
				serverAddress=sa;
				try
				{
					s=new Socket(serverAddress,8085);
				}
				catch(Exception e1)
				{
					JOptionPane.showMessageDialog(null,"Main Server Down");
					System.exit(1);
				}
					
				//JOptionPane.showInputDialog(null,"Connected to server\nEnter your name");
				l1=new JLabel("Connect with:");			
				lol=new JLabel("      Online");
				l1.setFont(f);
				l1.setForeground(new Color(0,153,153));
				lol.setFont(f);
			//	lol.setForeground(Color.green);
				tfc=new JTextArea(10,100);
				tfc.setEditable(false);
				tfc.setFont(f1);
				j=new JList();
				j.setFont(f);
				//tol.setEditable(false);
				t=new JTextField(20);
				t.setEditable(false);
				t2=new JTextField(20);
				t.addKeyListener(this);
				t2.setFont(f1);
				bs=new JButton("Send");
				bs.setFont(f1);
				bs.setToolTipText("Click here to Send message");
				bs.addActionListener(this);
				br=new JButton("Bored ?  Send a Game Request ");
				br.addActionListener(this);
				br.setFont(f);
				cd=new JButton("Connect");
				cd.setFont(f1);
				cd.setToolTipText("Click here to connect");
				cd.addActionListener(this);
				bc=new JButton("Broadcast to the Network");
				bc.addActionListener(this);
				bc.setFont(f1);
				sf=new JButton("Send a File");
				sf.addActionListener(this);
				sf.setFont(f1);
				bs.setEnabled(false);
				br.setEnabled(false);
				bc.setEnabled(false);
				sf.setEnabled(false);
				setLayout(null);
				l1.setBounds(25,50,110,20);
				lol.setBounds(285,100,180,30);
				t2.setBounds(140,50,125,20);
				cd.setBounds(290,50,100,20);
				br.setBounds(25,475,365,25);
				bc.setBounds(25,435,250,25);
				sf.setBounds(290,435,100,25);
				tfc.setBounds(25,100,250,285);
				j.setBounds(290,135,100,245);
				t.setBounds(25,400,270,20);
				sc=new ScrollPane();
				scol=new ScrollPane();
				sc.setBounds(25,100,250,285);
				scol.setBounds(290,140,100,245);
				bs.setBounds(310,400,80,20);
				add(l1);
				scol.add(j);
				add(t2);
				add(cd);
				add(t);
				add(bs);
				sc.add(tfc);
				add(sc);
				add(scol);
				add(lol);
				add(br);
				add(bc);
				add(sf);
				addWindowListener(new WindowAdapter(){
						public void windowClosing(WindowEvent e)
						{try{
							if(iscn==1){
								rmiServer.disconnect(key1,key2);
								iscn=0;
								dos.writeUTF(nm);
							}	rmiServer.backup(nm,tfc.getText());	
							}catch(Exception et){
							System.out.println("et : "+et);}
								System.exit(0);
						}
				});
				
				setSize(418,520);
			//	setBackground(new Color(120,10,255));
		      //	setState(Frame.ICONIFIED);									//Rmi setup
			  try
				{
               registry=LocateRegistry.getRegistry(serverAddress,(new Integer(serverPort)).intValue());
			   rmiServer=(ReceiveMessageInterface)(registry.lookup("rmiServer"));
				}
				catch(Exception e){}

				dis=new DataInputStream(s.getInputStream());
				dos=new DataOutputStream(s.getOutputStream());
			//System.out.print("in if ac : "+Jpassword.activate);
				while(true)
				{			System.out.print("");												
					if(Jpassword.activate==1){
					
					nm=Jpassword.nm;
					if(nm.equalsIgnoreCase("")||(Jpassword.sp).equalsIgnoreCase(""))
					{	
						JOptionPane.showMessageDialog(null,"Fields Empty");
						return;
					}
					int resp=rmiServer.verify(nm,Jpassword.sp,Jpassword.status);//if 0 login
					if(resp==1)
						JOptionPane.showMessageDialog(null,"Successfully logged in");
					if(resp==2)
					{
						JOptionPane.showMessageDialog(null,"Invalid password");
						return;
					}
					if(resp==3)
					{	
						JOptionPane.showMessageDialog(null,"Not yet registered");
						return;
					}
					if(resp==4)
						JOptionPane.showMessageDialog(null,"Welcome . . .you r now Registered");
					if(resp==5)
						{	JOptionPane.showMessageDialog(null,"You are already registered...log on now");
					return;
					}
					break;
					}
				}
				 
				dos.writeUTF(nm);      //client registers its name at server database
				key1=Integer.parseInt(dis.readUTF());
				
				th=new Thread(this);
				setVisible(true);
				setLocation(600,150);
				th.start();
				try{
				tfc.setText(""+rmiServer.loadup(nm));											//received all user list
					String tmp=""+rmiServer.userlist(nm);
					userlist=tmp.split("  ");
					j.setListData(userlist);
		}catch(Exception jk){}
			
				Refresh();
		}
	
	public void run()
	{
			while(true)
			{
				try
				{
					String str=dis.readUTF();
					if(str.equals("#gr#")){
						gameon=1;
						rs='r';
						JOptionPane.showMessageDialog(null,"Hey... "+t2.getText()+" sent u a game request!");
						continue;
					}
					if(str.charAt(0)=='@')
					{
						JOptionPane.showMessageDialog(null,"Hey "+t2.getText()+" sent u a File");
						try{
						FileDialog fo1=new FileDialog(this,"filesave",FileDialog.SAVE);
						fo1.setVisible(true);
						File fl=new File(fo1.getDirectory()+fo1.getFile());
						FileOutputStream r=new FileOutputStream(fl);
						String fn="";
						System.out.println("rc strat ");
						int ic=0;
						for(ic=1;str.charAt(ic)!='@';ic++)
								fn+=str.charAt(ic);
						int fln=Integer.parseInt(fn);
						JProgressBar dw=new JProgressBar(0,fln);
		/*				UIManager.put("ProgressBar.background", Color.orange);
						UIManager.put("ProgressBar.foreground", Color.blue);
						UIManager.put("ProgressBar.selectionBackground", Color.red);
						UIManager.put("ProgressBar.selectionForeground", Color.green);*/
						Frame frm=new Frame("Downloads");
						JLabel pl=new JLabel("Downloaded 0 Kb out of 0 Kb");
						JLabel plf=new JLabel("Folder : "+fo1.getDirectory());
						frm.setLayout(null);
						frm.setSize(350,200);
						dw.setBounds(30,110,290,20);
						pl.setBounds(70,65,250,30);
						plf.setBounds(70,140,200,30);
						frm.add(dw);
						frm.add(plf);
						frm.add(pl);
						frm.setLocation(550,300);
						frm.setVisible(true);
						String huge=str;
						//System.out.println("h len : "+ic);
						byte b=(byte)0;
						
						huge=huge.substring(ic+1);
						int len=huge.length();
						//System.out.println("h len : "+fln);
						while(huge.charAt(len-1)=='+'){
							huge=huge.substring(0,len-1);
							
							len=huge.length();
							int szf=(int)fl.length()/1024;
							dw.setValue(szf);
							pl.setText("Downloaded "+szf+" Kb out of "+fln+" Kb");
							for(int i=0;i<len;i++)
							{
								b=(byte)huge.charAt(i);
								r.write(b);
							}
							huge=dis.readUTF();
								len=huge.length();
						}
							dw.setValue((int)fl.length()/1024);
							for(int i=0;i<len;i++)
							{
								b=(byte)huge.charAt(i);
								r.write(b);
							}
						frm.removeNotify();
						len=huge.length();
						System.out.println("h len : "+fln);
					 //   huge=huge.substring(0,len-1);
						
						/*byte [] barr;
						barr=(byte [])huge.getBytes();
						r.write(barr);
*/
						
						System.out.println("rc end ");
							r.close();
						}
						catch (Exception e){System.out.println(e);}
						continue;
					}
					if(gameon==1)
					{
						if(rs=='r')
						{
							if(str.equals("#g#st"))
							robj=new GRmiS(dos);
							if(str.equals("#stop#"))
							{
								gameon=0;
								robj.removeNotify();
							}
							String arr[];
							arr=str.split(",");
							String tmp=""+robj.Game(arr[0],arr[1],arr[2]);
							dos.writeUTF(tmp);
						}
						else{
							if(str.equals("#stop#"))
							{	gameon=0;
							//System.out.println("ha");
								gobj.removeNotify();
								//continue;
							}
							gobj.pds=Integer.parseInt(str);
							
						}
						continue;
					}
					if(str.equals("!X!"))
					{	JOptionPane.showMessageDialog(null,t2.getText()+" disconnected ");
						t2.setText("");
						dos.writeUTF(nm);
						l1.setText("Connect with :");
						cd.setText("Connect");
						iscn=0;
						continue;
					}
					if(str.charAt(0)=='~')
					{	
						key2=Integer.parseInt(""+str.charAt(1));
						String cnto=str.substring(2,str.length());						//we limit list size to 9 remember it.
						JOptionPane.showMessageDialog(null,cnto+" wants to chat with u...");
						l1.setText("Connected To :");
						cd.setText("Disconnect");
						t2.setText(""+cnto);
						t.setEditable(true);
						bs.setEnabled(true);
						br.setEnabled(true);
						bc.setEnabled(true);
						sf.setEnabled(true);
						iscn=1;
						System.out.println("k1 : "+key1+" k2: "+key2);
						continue;
					}

					if(str.equals("^*^"))
					{	Refresh();
						continue;
					}

					if(str.charAt(0)=='^')
					{	str=tfc.getText()+"\n"+str.substring(1);
					System.out.println("yup");
					}else
					str=tfc.getText()+"\n"+t2.getText()+": "+str;
					tfc.setText(str);
					sc.setScrollPosition(0,1490);
					ok=1;
				}
				catch(Exception e){}
				
		}
	}
	
	void Refresh()
	{
				try
				{	String tmp=""+rmiServer.onlinelist();							//received online list
					String rep=nm+"  ";
					String fnl=tmp.replaceAll(rep,"");
					System.out.println("ol : "+fnl);
					arr=fnl.split("  ");

					
					j.setSelectionBackground(new Color(153,255,0));
					int len=arr.length,ulen=userlist.length;								
					int []arin=new int[len];
					int olc=0;
					for(int h=0;h<len;h++)													//high overhead
					{
						for(int ul=0;ul<ulen;ul++)
							if(arr[h].equalsIgnoreCase(userlist[ul]))
							{	arin[olc]=ul;
								olc++;
							}
					}																		//located in userlist and colored
				j.setSelectedIndices(arin);
				j.setEnabled(false);
	
				
			}
			catch(Exception gf){}
		
	}

	public void keyPressed(KeyEvent k){}
		public void keyReleased(KeyEvent k){}
		public void keyTyped(KeyEvent k)
		{
			char ch=k.getKeyChar();
			if((int)ch==10)
			{
				try
				{
				///	sc.setScrollPosition(0,1490);
				String msg=t.getText();
				tfc.setText(tfc.getText()+"\n"+"Me: "+msg);
				sc.setScrollPosition(0,1490);
				dos.writeUTF(msg);
				t.setText("");
				
				}
				catch(Exception h){}
			}
			//JOptionPane.showMessageDialog(null,"key  char :"+(int)ch+"\nasc:"+as);
		}
	public void actionPerformed(ActionEvent e) 
	{
			JButton b=(JButton)e.getSource();
			if(b==cd)
			{
				if(e.getActionCommand()=="Connect")
				{
				
					try
					{
						int result=rmiServer.connect(key1,t2.getText());
						if(result>=0)
						{
						l1.setText("Connected To :");
						cd.setText("Disconnect");
						t.setEditable(true);
						bs.setEnabled(true);
						br.setEnabled(true);
						bc.setEnabled(true);
						sf.setEnabled(true);
						ok=1;
						iscn=1;
						key2=result;
						
						}
						else
						{JOptionPane.showMessageDialog(null,"Cannot connect...\nIts not online or either busy wid someone");
						t2.setText("");
						}
					}
					catch (Exception ej){}
				}
				else
				{try{
					rmiServer.disconnect(key1,key2);
					//s.close();
					//s=new Socket("Localhost",8085);
					iscn=0;
					dos.writeUTF(nm);
					
					//Uend obj=new Uend();
				}catch(Exception dd){}
					t2.setText("");
					l1.setText("Connect with :");
					cd.setText("Connect");
				}
			}
			if(b==bs)
			{	try
				{
				String msg=t.getText();
				tfc.setText(tfc.getText()+"\n"+"Me: "+msg);
				dos.writeUTF(msg);
				t.setText("");
				sc.setScrollPosition(0,1190);
				}
				catch(Exception h){}
			}
			if(b==sf)
			{
				String str="@";
			try
			{
			FileDialog fo=new FileDialog(this,"fileopen",FileDialog.LOAD);
			fo.setVisible(true);
			File f=new File(fo.getDirectory()+fo.getFile());
			System.out.println("File path :"+(fo.getDirectory()+fo.getFile()));
			FileInputStream fr=new FileInputStream(f);
			int flen=((int)f.length())/1024;
			str+=flen+"@";
			int no=0,c=0;
			while(true)
			{	c++;
				no=(int)fr.read();
				//System.out.print("\t no: "+no);
				if(no==-1)
					break;
				str+=(char)no;
				if(c>=33000)
				{	dos.writeUTF(str+"+");
					c=0;
					str="";
				}
			}
			dos.writeUTF(str);
			System.out.println("\nlen : "+str.length()+" sent");
			str=null;
			fr.close();
			fo=null;
			f=null;
			}catch(Exception sdf){
			System.out.println("in ex : "+sdf+"\nlen : ");}
			}
	
			if(b==bc)
			{
				try{
				String msg=t.getText();
				tfc.setText(tfc.getText()+"\n"+"Me: "+msg);
				rmiServer.broadcast("^[Broadcast from "+nm+" ] : "+msg);
				t.setText("");
				sc.setScrollPosition(0,1190);
				}catch(Exception bex){}
			}
			if(b==br)
			{try{
					dos.writeUTF("#gr#");
					gameon=1;
					rs='s';
					gobj=new GRmi(dos);
				}catch(Exception erd){}
			}
	}
	public static void main(String[] args) throws Exception
	{
				String sa=JOptionPane.showInputDialog(null,"Enter Server IP/machine name");
				Jpassword oj=new Jpassword();
				Uend uobj=new Uend(sa);		
    }
}

//Game request
class GRmi extends Frame implements Runnable,MouseMotionListener
{
	int delay=25,xi,sc1=0,sc2=0; 
	int x=400,y=199,xa=500,xc=400,pd=-100,pds=-100,q=-100,xb,xd,xi2,tb1,tb2;
	char fn,fp;
	Thread th;
	JLabel s1,s2,pnm;
	JTextField t1,t2;
	DataOutputStream dtos;

	GRmi(DataOutputStream dataos)
	{
		
		super("Tennis Game");
		dtos=dataos;
		th=new Thread(this);
		addMouseMotionListener(this);
		
		s1=new JLabel("Player 1");
		s2=new JLabel("Player 2");
		pnm=new JLabel("Player 1");
		t1=new JTextField(10);
		t2=new JTextField(10);
		t1.setText("0");
		t2.setText("0");
		setLayout(null);
		
		addWindowListener(new WindowAdapter()
			{
						public void windowClosing(WindowEvent e)
						{
							th.stop();
							Uend.gameon=0;
							try{
							dtos.writeUTF("#stop#");
							removeNotify();
							}catch(Exception stp){}
								//System.exit(0);
						}
			});
		pnm.setBounds(500,40,80,50);
		s1.setBounds(280,90,80,50);
		t1.setBounds(360,105,40,20);
		s2.setBounds(640,90,80,50);
		t2.setBounds(720,105,40,20);
		add(pnm);
		add(s1);
		add(t1);
		add(s2);
		add(t2);
		setSize(1100,900);
		setVisible(true);
		setResizable(false);
		JOptionPane.showMessageDialog(null,"Wanna Start??");
		try{
		dtos.writeUTF("#g#st");
		th.start();
		}catch(Exception tuj){}
	}
   
	public void run()
	{
	
		
		while(true)
		{				
				try
			{	Graphics gs=getGraphics();
				gs.setXORMode(Color.white);
				gs.fillRect(200,(q-50),18,100);
				q=pds;
				gs.fillRect(200,(q-50),18,100);	
			}
			catch(Exception nh)
			{
					//	System.out.println("ex"+nh);             //here exception occurs(null prt exc)....bt no prblm
			}
				if(x>=850)
				{	
						fp=fn;	xb=y;		xi=pd;
						fn='b';tb1=0;
				}
				if(y>=670)
				{
					fp=fn;
						fn='c';xc=x;
				}
				if(x<=220)
				{
					fp=fn;		xd=y;		xi2=q;
						fn='d';		tb2=0;		
				}
				if(y<=200)
				{
					fp=fn;
						fn='a';xa=x;
				}

			try
			{
					Graphics g=getGraphics();
					g.drawRect(200,200,700,502);
					g.fillOval(x,y,30,30);
				try
				{
					th.sleep(delay);
				}
				catch (Exception e){}
				
				g.setColor(new Color (255,255,255));
				g.fillOval(x,y,30,30);
	

				if(fn=='b')
				{
					
						if((xb<(xi-50)||xb>(xi+50))&&tb1==0)
						{
							sc2+=10;
							t2.setText(""+sc2);tb1=1;
						}
					
						x-=3;
						if(fp=='c')
							y-=3;
						else
							y+=3;
				}
				if(fn=='a')
				{

						y+=3;
						if(fp=='b')
							x-=3;
						if(fp=='d')
							x+=3;
						if(fp=='c'&&xc>550)
							x-=3;
						if(fp=='c'&&xc<=550)
							x+=3;
				}
				if(fn=='d')
				{
						if((xd<(xi2-50)||xd>(xi2+50))&&tb2==0)
						{	
							sc1+=10;
							t1.setText(""+sc1);tb2=1;
						}
						x+=3;
						if(fp=='a')
							y+=3;
						else
							y-=3;
							
				}
				if(fn=='c')
				{
					
						y-=3;
						if(fp=='d')
							x+=3;
						if(fp=='b')
							x-=3;
						if(fp=='a'&&xa<550)
							x+=3;
						if(fp=='a'&&xa>=550)
							x-=3;
					
				}
		
			dtos.writeUTF(pd+","+sc1+","+sc2);
			}
			catch(Exception e){}
		}
		
	}
	
	public void mouseDragged(MouseEvent e)
	{	
			Graphics g=getGraphics();
			g.setXORMode(Color.white);	
			g.fillRect(880,(pd-50),18,100);
			pd=e.getY();
			g.fillRect(880,(pd-50),18,100);
			
	}

	public void mouseMoved(MouseEvent e){}
}

//game rqst receiver class       ....a bit faster
class GRmiS extends Frame implements Runnable,MouseMotionListener
{
	int delay=25,xi,sc1=0,sc2=0; 
	int x=400,y=199,xa=500,xc=400,pd=-100,pds=-100,q=-100,xb,xd,xi2,tb1,tb2;
	char fn,fp;
	Thread th;
	JLabel s1,s2,pnm;
	JTextField t1,t2;
	DataOutputStream dtos;

	GRmiS(DataOutputStream dts)
	{
		
		super("Tennis Game");
		th=new Thread(this);
		dtos=dts;
		addMouseMotionListener(this);
		th.start();
		s1=new JLabel("Player 1");
		s2=new JLabel("Player 2");
		pnm=new JLabel("Player 2");
		t1=new JTextField(10);
		t2=new JTextField(10);
		t1.setText("0");
		t2.setText("0");
		setLayout(null);
		
		addWindowListener(new WindowAdapter()
			{
						public void windowClosing(WindowEvent e)
						{
								//System.exit(0);
								th.stop();
								Uend.gameon=0;
								try{
								dtos.writeUTF("#stop#");
								//System.out.println("ha dw");
								removeNotify();
								}catch(Exception edq){}
						}
			});
		pnm.setBounds(500,40,80,50);
		s1.setBounds(280,90,80,50);
		t1.setBounds(360,105,40,20);
		s2.setBounds(640,90,80,50);
		t2.setBounds(720,105,40,20);
		add(pnm);
		add(s1);
		add(t1);
		add(s2);
		add(t2);
		setSize(1100,900);
		setVisible(true);
		setResizable(false);
		
	}
   
	public void run()
	{
		
		while(true)
		{				
				try
			{	Graphics gs=getGraphics();
				gs.setXORMode(Color.white);
				gs.fillRect(880,(q-50),18,100);
				q=pds;
				gs.fillRect(880,(q-50),18,100);
			
						
			}
			catch(Exception nh)
			{
					//	System.out.println("ex"+nh);             //here exception occurs(null prt exc)....bt no prblm
			}
				if(x>=850)
				{	
						fp=fn;	xb=y;		xi=pd;
						fn='b';tb1=0;
				}
				if(y>=670)
				{
					fp=fn;
						fn='c';xc=x;
				}
				if(x<=220)
				{
					fp=fn;		xd=y;		xi2=q;
						fn='d';		tb2=0;		
				}
				if(y<=200)
				{
					fp=fn;
						fn='a';xa=x;
				}

			try
			{
					Graphics g=getGraphics();
					g.drawRect(200,200,700,502);
					g.fillOval(x,y,30,30);
				try
				{
					th.sleep(delay);
				}
				catch (Exception e){}
				
				g.setColor(new Color (255,255,255));
				g.fillOval(x,y,30,30);
	

				if(fn=='b')
				{
					
						if((xb<(xi-50)||xb>(xi+50))&&tb1==0)
						{
							sc2+=10;
							t2.setText(""+sc2);tb1=1;
						}
					
						x-=3;
						if(fp=='c')
							y-=3;
						else
							y+=3;
				}
				if(fn=='a')
				{

						y+=3;
						if(fp=='b')
							x-=3;
						if(fp=='d')
							x+=3;
						if(fp=='c'&&xc>550)
							x-=3;
						if(fp=='c'&&xc<=550)
							x+=3;
				}
				if(fn=='d')
				{
						if((xd<(xi2-50)||xd>(xi2+50))&&tb2==0)
						{	
							sc1+=10;
							t1.setText(""+sc1);tb2=1;
						}
						x+=3;
						if(fp=='a')
							y+=3;
						else
							y-=3;
							
				}
				if(fn=='c')
				{
					
						y-=3;
						if(fp=='d')
							x+=3;
						if(fp=='b')
							x-=3;
						if(fp=='a'&&xa<550)
							x+=3;
						if(fp=='a'&&xa>=550)
							x-=3;
					
				}
		
			
			}
			catch(Exception e){}
		}
		
	}
	
	public void mouseDragged(MouseEvent e1)
	{	
			Graphics g=getGraphics();
			g.setXORMode(Color.white);
			g.fillRect(200,(pd-50),18,100);
			pd=e1.getY();
			g.fillRect(200,(pd-50),18,100);	
	}

	int Game(String pdc1,String sc1,String sc2)
		{	
			Graphics gc=getGraphics();
			pds=Integer.parseInt(pdc1);
			this.sc1=Integer.parseInt(sc1);
			this.sc2=Integer.parseInt(sc2);
			/*gc.setXORMode(Color.white);	
			gc.fillRect(880,(q-50),18,100);
			q=pdc;
			gc.fillRect(880,(q-50),18,100);
			*/
			return pd;
		}
	public void mouseMoved(MouseEvent e){}
}