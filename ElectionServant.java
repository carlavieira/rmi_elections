import java.rmi.RemoteException;
import java.util.Vector;
import java.util.HashMap;
import java.rmi.server.UnicastRemoteObject;

public class ElectionServant implements Election {

	private final Vector<Vote> votes;
	private String cache;

	public ElectionServant() throws RemoteException {
		votes = new Vector<Vote>();
		cache = "-";
	}

	public Vote vote(final String candidate, final String voterId) throws RemoteException {
		synchronized (votes) {
			if (cache.equals(voterId)) {
				System.out.println("Vote already counted.");
				return votes.lastElement();
			} else {
				final Vote vote = new Vote(candidate, voterId);
				votes.addElement(vote);
				System.out.println("Vote counted.");
				cache = vote.voterId;
				return vote;
			}
		}
	}

	public HashMap<String, Integer> result() throws RemoteException {
		final HashMap<String, Integer> result = new HashMap<String,Integer>();
		synchronized(votes){
			votes.forEach(
				(vote) -> { 
					if (result.containsKey(vote.candidate)) {
						result.put(vote.candidate, (result.get(vote.candidate) + 1)); 
					} else {
						result.put(vote.candidate,  1);
					}
				}
			);
			return result;
		}
	}

}
