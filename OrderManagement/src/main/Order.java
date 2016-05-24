package main;

public class Order extends Node implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6371459706888463401L;
	private int cid;
	private int pid;
	private int oid;
	private int pieces;

	public Order() {

	}

	public Order(int pid, int cid, int pieces) {
		this.setPid(pid);
		this.setCid(cid);
		this.setPieces(pieces);
		this.oid = (int) (Math.random() * 2147483647);
	}

	public int getCid() {
		return cid;
	}

	public void setCid(int cid) {
		this.cid = cid;
	}

	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public int getOid() {
		return oid;
	}

	public void setOid(int oid) {
		this.oid = oid;
		this.setID(oid);
	}

	public int getPieces() {
		return pieces;
	}

	public int getProdQ() {
		return pieces;
	}
	
	public void setPieces(int pieces) {
		this.pieces = pieces;
	}

	public int getID() {
		return oid;
	}
	
	public String toString() {
		return oid + " " + cid + " " + pid + " " + pieces;
	}
}
