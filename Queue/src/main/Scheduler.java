package main;

public class Scheduler {
	private Server newServer;
	
	public Scheduler(int i){
		newServer = new Server(i);
		Thread th = new Thread(newServer);
		th.start();
	}

	public Server getNewServer() {
		return newServer;
	}

	public void setNewServer(Server newServer) {
		this.newServer = newServer;
	}
	
	public void dispatchTaskOnServer(Task t){
		newServer.addTask(t);
	}
}
