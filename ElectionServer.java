
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class ElectionServer {
	public static void main(String args[]) {
//		if (System.getSecurityManager() == null) {
//			System.setSecurityManager(new SecurityManager());
//		}
		try {
			// estancia o objeto remoto
			Election aElection = new ElectionServant();
			//extortando objeto do tipo Election
			Election stub = (Election) UnicastRemoteObject.exportObject(aElection, 0);
			// criando o registry
			Registry registry = LocateRegistry.getRegistry();
			// adicionando o stub no registry
			registry.rebind("Election", stub);
			System.out.println("Servidor Election pronto...");
		} catch (Exception e) {
			System.err.println("ElectionServer: m�todo main " + e.getMessage());
			e.printStackTrace();
		}
	}
}
