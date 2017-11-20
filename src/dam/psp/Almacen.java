package dam.psp;

public class Almacen {
	int numPiezas=8000;
	int maxPiezas = 20000;
	boolean state;
	int contadorEnvios;
	boolean pedidoRealizado;
	
	public synchronized boolean getState()
	{
		return state;
	}
	public Almacen() {
		contadorEnvios =0;
		pedidoRealizado=false;
		state = true;
	}

	public synchronized void envio(int numPiezasEnvio)
	{
		while(!pedidoRealizado)
		{
			try {
			this.wait();
			}catch (InterruptedException e)
			{
				e.printStackTrace();
			}
		}
		if(state)
		{
		if(numPiezas+numPiezasEnvio>maxPiezas)
		{
			System.out.println("Hemos llenado el almacen!");
			state = false;
			pedidoRealizado=false;
		}else {
			numPiezas=numPiezas+numPiezasEnvio;
			System.out.println("Llegan "+numPiezasEnvio+" piezas");
			System.out.println("Hay "+numPiezas+" piezas en el almacén");
			contadorEnvios++;
		}
		if(contadorEnvios==3)
		{
			pedidoRealizado=false;
			contadorEnvios=0;
			
		}
		}
		notify();
	}
	
	public synchronized void retirada(int numPiezasRetirada)
	{
		while(pedidoRealizado)
		{
			try {
				this.wait();
				}catch (InterruptedException e)
				{
					e.printStackTrace();
				}
		}
		if(state)
		{
		System.out.println("Pedido de "+numPiezasRetirada);
		if(numPiezas-numPiezasRetirada<1)
		{
			System.out.println("No hay piezas suficientes!");
			state = false;
			pedidoRealizado=true;
		}
		else {
			
			numPiezas=numPiezas-numPiezasRetirada;
			pedidoRealizado=true;
			System.out.println("Hay "+numPiezas+" piezas en el almacén");
		}
		}
		notify();
	}
	
}
