
import java.awt.Graphics;
import javax.swing.JOptionPane;

public class Arbol<T extends Comparable<T>>  {
    private Nodo<T> raiz;
    
    public Arbol(){
        raiz = null;
    }
    
    public void insertar(T n)
    {
        if (raiz == null) {
            raiz = new Nodo(n);
        } else {
            raiz.insertar(n);
        }
    }
    
    public String EnOrden()
    {
        return (raiz == null) ? "" : raiz.EnOrden();
    }
    
    public String PreOrder()
    {
        return (raiz == null) ? "" : raiz.PreOrder();
    }
    
    public String PosOrder()
    {
        return (raiz == null) ? "" : raiz.PosOrder();
    }
    

    public boolean existe(int dato) {
        Nodo aux = raiz;
        while (aux != null) {
            if (aux.getInfo().equals(dato)) {
                return true;
            } else if (aux.getInfo().compareTo(dato)<0) {
                aux = aux.getDer();
            } else {
                aux = aux.getIzq();
            }
        }
        return false;
    }
    
    public void dibujar(Graphics dibujo)
    {
        if (raiz != null) {
            raiz.dibujarNodo(dibujo, 0, 0);
        }
    }
    
}

