import java.awt.Rectangle;
import java.awt.Color;
import java.io.Serializable;

@SuppressWarnings("serial")
public class Vote implements Serializable {
	public String candidate;
	public String voterId;

	public Vote(String candidate, String voterId) {
		this.candidate = candidate;
		this.voterId = voterId;
	}

	public void print() {
		System.out.println("Candidate: "+candidate+ " -  Voter ID: "+voterId); }
}
