import java.rmi.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class ElectionClient {
	public static void main(String args[]) {
		String option = "result";
		String voterName = "-";
		String candidate = "Nulo";


		// System.out.println("argslen: " + args.length);
		if (args.length > 0)
			option = args[0]; // specifies result or vote
		if (args.length > 1)
			voterName = args[1]; // get Voter Name
		if (args.length > 2)
			candidate = args[2]; // get Voter Candidate

//		if (System.getSecurityManager() == null) {
//			System.setSecurityManager(new SecurityManager());
//		} else
//			System.out.println("Já possui um Security Manager.");

		Election aElection = null;

		for (int i = 1; i <= 5 ; i++){

			try {
				Registry registry = LocateRegistry.getRegistry("localhost");
				aElection = (Election) registry.lookup("Election");
				// System.out.println("Election found.");

				//// invocação remota
				HashMap<String, Integer> sResult = aElection.result();
				// System.out.println("Retornado o vetor de Election");

				if (option.equals("results")) {
					sResult.entrySet().stream()
							.sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
							.limit(10)
							.forEach(System.out::println);
				} else {
					// invocação remota
					Vote v = aElection.vote(candidate, getVoterId(voterName));
					System.out.println("Your Vote: ");
					v.print();
				}
				break;
			} catch (RemoteException | NotBoundException e) {
				try {
					java.lang.Thread.sleep(6000);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
			}
		}
	}

	static String getVoterId(String name){
		try {
			// Gera o hash MD5 baseado no nome do eleitor
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(name.getBytes());
			byte[] md5 = md.digest();

			// Formata em Hexadecimal min�sculo para exibir na tela
			BigInteger numMd5 = new BigInteger(1, md5);
			String hashMd5 = String.format("%022x", numMd5);

			return hashMd5;
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
	}
}
