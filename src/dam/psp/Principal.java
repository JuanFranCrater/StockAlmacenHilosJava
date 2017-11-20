package dam.psp;

public class Principal {

	public static void main(String[] args) {
		Almacen alm = new Almacen();
		
		Retirada r = new Retirada(alm);
		Envio e = new Envio(alm);
	
		r.start();
		e.start();
		try {
			r.join();
			e.join();
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
	}

}
