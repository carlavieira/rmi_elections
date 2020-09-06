
import java.rmi.*;
import java.util.HashMap;

public interface Election extends Remote {

	Vote vote(String candidate, String voterId) throws RemoteException;

	HashMap<String, Integer> result() throws RemoteException;

}
