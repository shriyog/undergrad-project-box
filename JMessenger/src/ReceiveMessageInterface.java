import java.rmi.*;
public interface ReceiveMessageInterface extends Remote
{
     int connect(int cnfrm,String cntwith) throws RemoteException;
	 void disconnect(int cnfrm,int cntwith) throws RemoteException;
	 String anyRequests(String rnm) throws RemoteException;
	 int verify(String name,String password,int status)throws Exception;
	 void backup(String nm,String chat)throws Exception;
	 String loadup(String nm)throws Exception;
	 String onlinelist() throws RemoteException;
	 String userlist(String unm)throws Exception;
	 void broadcast(String msg)throws Exception;
} 