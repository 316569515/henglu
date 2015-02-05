package test.hentglu.erp;

public class MyJob {
	
	private final Object delegateMonitor = new Object();
	
	
	public void work(){
		
		synchronized (delegateMonitor) {
			System.out.println("111111111111111111111111111111"+delegateMonitor);
			
			
			
			
			
		}
		
	}

}
