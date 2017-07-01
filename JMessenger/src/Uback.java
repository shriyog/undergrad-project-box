import java.net.*;
import java.io.*;
import java.util.*;
import java.rmi.*;
import java.rmi.registry.*;
import java.rmi.server.*;
import java.sql.*;
import javax.swing.*;

class F1 extends Thread
{
		DataInputStream dis;
		DataOutputStream dos;
		public int stop=0;
		F1(DataInputStream dis,DataOutputStream dos)
		{
				super("1");
				this.dis=dis;
				this.dos=dos;
		}
		public void run()
		{
				while(true)
				{	
					try
					{
						String str=dis.readUTF();
						
						//System.out.println("____________________1: "+str);
						if(stop==1)
							break;
						//System.out.println("____________________2: "+str);
						dos.writeUTF(str);
					}
					catch(Exception e1){
					//System.out.println("e1: "+e1);
					}
				}/*
				try{
					dis.close();
					dos.close();
					}catch(Exception c){}*/
				stop=0;
		}
}

class F2 extends Thread
{		
		DataInputStream dis;
		DataOutputStream dos;
		public int stop=0;
		F2(DataInputStream dis,DataOutputStream dos)
		{
				super("2");
				this.dis=dis;
				this.dos=dos;
		}
		public void run()
		{
				while(true)
				{	try
					{
						String str=dis.readUTF();
					//	System.out.println("____________________1: "+str);
						if(stop==1)
							break;
						//System.out.println("____________________2: "+str);
						dos.writeUTF(str);
					}
					catch(Exception e2){//System.out.println("e2: "+e2);
					}
				}/*
				try{
				dis.close();
				dos.close();
				}catch(Exception c){}*/
				stop=0;
		}
}

class Client
{
	public String nm="",bs="no";
	public int alive=1;
	public Socket cs;
	DataOutputStream cdos;
	Client(String n,Socket c)
	{nm=n;
	cs=c;
	try{
	cdos=new DataOutputStream(cs.getOutputStream());
	}catch(Exception cls){}
	}
}
public class Uback extends UnicastRemoteObject
implements ReceiveMessageInterface,Runnable
{
		ServerSocket ss;
		Socket s;
		LinkedList l,sl,dlist; 
		DataInputStream dis;
		DataOutputStream dos;
		DataOutputStream dtmp;
		 int thisPort,no=0,flg=0,ccnt=0;
		String   thisAddress,rl="",rqto="",cnm="",imtorqst="",diswith="";
		 Registry registry;
		 Thread th;
		 int q=0,w=0,disflag=0;
		 PreparedStatement psi,psd,pss,psu,psdel;
		
		ResultSet rs1,rs2;

		public int verify(String unm,String upw,int status)throws Exception
		{
			//System.out.println("unm "+unm+"  pw : "+upw+"  st : "+status);
				System.out.println("[server_Security] >> Verifying Client "+cnm+"....");
				pss.setString(1,unm);
				rs1=pss.executeQuery();
				if(rs1.next())
				{	
					if(status==0)
					{
					String s1=rs1.getString(1);
					String s2=rs1.getString(2);
					String s3=rs1.getString(3);

					if(upw.equals(s2))
						return 1;
					else
						return 2;
					}
					else
						return 5;
				}
				else{
				if(status==0)
				return 3;
			else
			{
				psi.setString(1,unm);
				psi.setString(2,upw);
				psi.setString(3,"");
				psi.executeUpdate();
				return 4;
			}
				}
		}

		public void backup(String usernm,String chat)throws Exception
		{
				pss.setString(1,usernm);
				rs1=pss.executeQuery();
				
				if(rs1.next())
				{	//System.out.println("back up :"+chat);
					psu.setString(1,chat);
					psu.setString(2,usernm);
					
					psu.executeUpdate();
				}
				int z=0;
				for(z=0;z<l.size();z++)
						{
								if(usernm.equalsIgnoreCase(((Client)l.get(z)).nm))			//srched for it
									break;
						}
				System.out.println("[server_ChatRepository] >> Client "+usernm+" has gone Offline.....Data BackedUp");
				((Client)l.get(z)).alive=0;
				if(z==l.size()-1)				//ie last element....so we don't add in dlist & just remove from l 
					l.remove(z);
				else
				dlist.add(z);
		
				Refresh();
				System.out.println("[server_OnlineRegistery] >> Refreshing OnlineList");	
		}
		public String loadup(String unm)throws Exception
		{
				String str="";
				pss.setString(1,unm);
				rs1=pss.executeQuery();
				
				if(rs1.next())
				{
					String s1=rs1.getString(1);
					String s2=rs1.getString(2);
					str+=rs1.getString(3);
					
				}
				
				//System.out.println("str :"+str);
				System.out.println("[server_ChatRepositery] >> Chat Loaded for client "+unm);
				return str;
		}
		public String userlist(String unm)throws Exception
		{
				rs2=psd.executeQuery();
				//System.out.println("Student data:\n\tID\tName\t\tCPI");
				String str="",tmp="";
				while(rs2.next())
				{
					tmp=rs2.getString(1);
					if(!tmp.equalsIgnoreCase(unm))
					str+="  "+tmp;
				}

				//System.out.println("\t"+rs1.getInt(1)+"\t"+rs1.getString(2)+"\t"+rs1.getInt(3));
				return str;
				
		}
		public String onlinelist() throws RemoteException
		 {			rl="";
					for(int i=0;i<l.size();i++)
					{
						if(((Client)l.get(i)).alive==1)
					rl=rl+((Client)l.get(i)).nm+"  ";
					}
				//	System.out.println("[server_OnlineRegistery] >> Refreshing OnLine List");
					return rl;
		 }
		
		public void broadcast(String msg) throws Exception
		{
				DataOutputStream bdos;
				Socket bsoc;
				for(int i=0;i<l.size();i++)
				{
					bsoc=((Client)l.get(i)).cs;
					bdos=new DataOutputStream(bsoc.getOutputStream());
					bdos.writeUTF(msg);
				}
		}
		public String anyRequests(String rnm) throws RemoteException
		{	
			if(disflag==1&&diswith.equalsIgnoreCase(rnm))
			{	disflag=0;
				return ("#disconnect#");
			}
			if(flg==1&&imtorqst.equalsIgnoreCase(rnm))
			{flg=0;
	//		System.out.println("here is:  "+imtorqst);
			imtorqst="";
					return rqto;
			}
			else
					return new String("");
			
		}
		
		public int connect(int hkey,String cntwith) throws RemoteException
		{
						ccnt++;
						Server sobj;
						
						int i=0,f=0;
						flg=1;
						disflag=0;
					/*	for(i=0;i<l.size();i++)
						{
								if(cnfrm.equalsIgnoreCase(((Client)l.get(i)).nm))			//srched for it
							{	f=i;
								break;
							}
						}*/
						for(i=0;i<l.size();i++)
						{
								if(cntwith.equalsIgnoreCase(((Client)l.get(i)).nm))			//srched for it
									break;
						}
						q=i;
						w=f;
						
					
						if(i!=l.size())
						{
							if(((Client)l.get(hkey)).bs=="yes"||((Client)l.get(i)).bs=="yes")
								return -1;
							try
							{
								
								dtmp=new DataOutputStream(((Socket)((Client)l.get(i)).cs).getOutputStream());
								dtmp.writeUTF("~"+hkey+((Client)l.get(hkey)).nm);
							
							sobj=new Server(((Client)l.get(hkey)).cs,((Client)l.get(i)).cs,hkey,i);								//made a pair connection	
							((Client)l.get(hkey)).bs="yes";
							((Client)l.get(i)).bs="yes";
							System.out.println("[server_ConnectionManager] >>client "+((Client)l.get(hkey)).nm+" connected with client  "+((Client)l.get(i)).nm);
							sl.add(sobj);
							//dtmp=new DataOutputStream(((Socket)((Client)l.get(hkey)).cs).getOutputStream());
							//dtmp.writeUTF(""+i);
							}
							catch (Exception ergs)
							{System.out.println("in exc: "+ergs);
								return -1;
							}
							return i;
						}
						else
						{
							System.out.println("not in list");
							return -1;
						}

		}
		public void disconnect(int hkey,int gkey) throws RemoteException
		{
				try{
						((Client)l.get(hkey)).bs=""+"no";
						((Client)l.get(gkey)).bs=""+"no";
						//dtmp=new DataOutputStream(((Socket)((Client)l.get(gkey)).cs).getOutputStream());
						((Client)l.get(gkey)).cdos.writeUTF("!X!");
						
					System.out.print("\njik dis keys : "+hkey+"  "+gkey+"  ");
				for(int tq=0;tq<sl.size();tq++)
				{
					int tmp1=((Server)sl.get(tq)).cf;//=0
					int tmp2=((Server)sl.get(tq)).cw;//=1
					if(tmp1==hkey||tmp2==hkey)
					{
						//System.out.println("$$$$    yes  $$$"+tmp1+"  &  "+tmp2+"  no:"+tq);
					((Server)sl.get(tq)).release();
					sl.remove(tq);
					}
				}

					}catch(Exception er1){System.out.println("1 here dis "+er1);}
					System.out.println("[server_ConnectionManager] >> Client  Disconnected with Client ");
		}

		public Uback() throws Exception
		{
		Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
		Connection con=DriverManager.getConnection("jdbc:odbc:JMessengerDSN");	
		
		psi=con.prepareStatement("insert into MyDb values (?,?,?)");		
		psd=con.prepareStatement("select UserName from MyDb");
		pss=con.prepareStatement("select *from MyDb where UserName=?");
		psu=con.prepareStatement("update MyDb set Chat=? where UserName=?");
	//	psdel=con.prepareStatement("delete from MyDb where ID=?");
			try
			{	
				ss=new ServerSocket(8085);
				l=new LinkedList();
				sl=new LinkedList();
				dlist=new LinkedList();
				th=new Thread(this);
				th.start();
			}
			catch(Exception e1){}
			
			try
			{
            thisAddress= (InetAddress.getLocalHost()).toString();
			}
        catch(Exception e){
            throw new RemoteException("can't get inet address.");
			}
		thisPort=3232; 
        System.out.println("\nMain Server started...\nServer IP address :"+thisAddress+"  Port="+thisPort+"\n");
        try
			{
              registry = LocateRegistry.createRegistry( thisPort );
            registry.rebind("rmiServer", this);
        }
        catch(RemoteException e){
        throw e;
        }
    }
		
		public void run()
		{
				String ol="";

				while(true)
				{
					cnm="";
					try
					{	
						s=ss.accept();
						dis=new DataInputStream(s.getInputStream());
						dos=new DataOutputStream(s.getOutputStream());
						cnm=dis.readUTF();								//got the nm and added to list
						System.out.println("[server_registery] >> Client "+cnm+" Registered now Online");
						Client cobj=new Client(cnm,s);
						userKeyManager(cobj);
						
						ol="";
						for(int i=0;i<l.size();i++)
						{
							if(cnm.equalsIgnoreCase(((Client)l.get(i)).nm)){}
							else
							ol=ol+"\n"+((Client)l.get(i)).nm;
							
						}
						//dos.writeUTF(ol);									//sent online list
						System.out.println("[server_OnlineRegistery] >> OnlineList sent for Client "+cnm);	
						Refresh();
						System.out.println("[server_OnlineRegistery] >> Refreshing OnlineList");	
					}
			catch(Exception e2){}
				
				}
		}
		
		void userKeyManager(Client ul)
		{
			if(dlist.size()==0)
				l.add(ul);
			else
			{
				int key=(Integer)dlist.get(0);
				//if(key==l.size()-1&&dlist.size()>1)
				dlist.remove(0);
				l.remove(key);
				l.add(key,ul);
			}
			try{
			dtmp=new DataOutputStream(((Socket)ul.cs).getOutputStream());
			dtmp.writeUTF(""+l.indexOf(ul));
			}catch(Exception er){}
		}
	void Refresh()
	{
		try{
			System.out.println("size : "+l.size()+"\n");
		for(int tq1=0;tq1<l.size();tq1++)
		{
			//dtmp=new DataOutputStream(((Socket)((Client)l.get(tq1)).cs).getOutputStream());
			if(((Client)l.get(tq1)).alive==1){
			((Client)l.get(tq1)).cdos.writeUTF("^*^");
			System.out.print(""+((Client)l.get(tq1)).nm);
			}//dtmp=null;
		}	/*
		DataOutputStream rdos;
				Socket rsoc;
				for(int i=0;i<l.size();i++)
				{
					if(((Client)l.get(i)).alive==1){
					rsoc=((Client)l.get(i)).cs;
					rdos=new DataOutputStream(rsoc.getOutputStream());
					rdos.writeUTF("^*^");
					}
				}
				*/
		}catch(Exception exs){
		System.out.println("in exex : "+exs);
		}
		try{
		for(int y1=0;y1<l.size();y1++)
				System.out.print("#"+y1+"# name : "+((Client)l.get(y1)).nm+" alive : "+((Client)l.get(y1)).alive+"\n");
		}catch(Exception ex2){System.out.println("in ex2 : "+ex2);}
	}
	public static void main(String[] args) throws Exception
	{
		try
		{
			Uback obj=new Uback();
		}
		catch(Exception em){}
	}
}


class Server  
{
		 Socket s1,s2;
		 DataInputStream di1,di2;
		 DataOutputStream do1,do2;
		 F1 f1;
		 F2 f2;
		int cf,cw;
		
		Server(Socket sa,Socket sb,int c1,int c2) throws Exception
		{
		cf=c1;
		cw=c2;
		s1=sa;
		di1=new DataInputStream(s1.getInputStream());
		do1=new DataOutputStream(s1.getOutputStream());
		s2=sb;
		di2=new DataInputStream(s2.getInputStream());
		do2=new DataOutputStream(s2.getOutputStream());
		f1=new F1(di1,do2);
		f2=new F2(di2,do1);
		f1.start();
		f2.start();
		}	

		void release() throws Exception
		{
			f1.stop=1;
			f2.stop=1;
		}
		
}

