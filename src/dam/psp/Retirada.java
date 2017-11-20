package dam.psp;

public class Retirada extends Thread {
	Almacen alm;
	int dias;
	public Retirada(Almacen alm) {
		this.alm = alm;
		dias = 0;
	}

	@Override
	public void run() {
		while(alm.getState()) {
		try { 
			sleep(2400); // simulación de un día 
			}
			catch (InterruptedException e)
			 { e.printStackTrace(); 
			 }
		dias++;
		System.out.println("Día "+dias);
		alm.retirada(2000+((int)(Math.random()*500+1)));
		
	}
	
}
}
