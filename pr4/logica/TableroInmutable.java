package tp.pr4.logica;

public interface TableroInmutable {
	int getFilas();
	int getColumnas();
	Ficha getCasilla(int fila, int columna);
}
