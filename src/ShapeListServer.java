
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class ShapeListServer {
	public static void main(String args[]) {
//		if (System.getSecurityManager() == null) {
//			System.setSecurityManager(new SecurityManager());
//		}
		try {
			// estancia o objeto remoto
			ShapeList aShapeList = new ShapeListServant();
			//extortando objeto do tipo shape list
			ShapeList stub = (ShapeList) UnicastRemoteObject.exportObject(aShapeList, 0);
			// criando o registry
			Registry registry = LocateRegistry.getRegistry();
			// adicionando o stub no registry
			registry.rebind("ShapeList", stub);
			System.out.println("Servidor ShapeList pronto...");
		} catch (Exception e) {
			System.err.println("ShapeListServer: m�todo main " + e.getMessage());
			e.printStackTrace();
		}
	}
}
