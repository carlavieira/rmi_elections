import java.rmi.RemoteException;
import java.util.Vector;
import java.util.HashMap;
import java.rmi.server.UnicastRemoteObject;

public class ElectionServant extends UnicastRemoteObject implements Election {

	private Vector<Vote> votes;
	private String cache;

	public ElectionServant() throws RemoteException {
		votes = new Vector<Vote>();
		cache = "-";
	}

	public Vote vote(String candidate, String voterId) throws RemoteException {
		synchronized (votes){ 
			if (cache.equals(voterId)){
				System.out.println("Vote already counted.");
				return votes.lastElement();
			} else {
				Vote vote = new Vote(candidate, voterId);
				votes.addElement(vote);
				System.out.println("Vote counted.");
				cache = vote.voterId;
				return vote;
			}
		}
	}

	public HashMap<String, Integer> result() throws RemoteException {
		HashMap<String, Integer> result = new HashMap<String,Integer>();
		synchronized(votes){
			votes.forEach((vote) -> result.put(vote.candidate, result.get(vote.candidate) + 1));
		}
		return result;
	}

}
