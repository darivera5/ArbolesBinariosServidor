import java.awt.Graphics;

public class Nodo<T extends Comparable<T>> {
    private T info;
    Nodo<T> izq, der;
    
    Nodo(T nodoInfo) {
        info = nodoInfo;
        izq = der = null;
    }

    public T getInfo() {
        return info;
    }

    public Nodo<T> getIzq() {
        return izq;
    }

    public Nodo<T> getDer() {
        return der;
    }

    public void setInfo(T info) {
        this.info = info;
    }

    public void setIzq(Nodo<T> izq) {
        this.izq = izq;
    }

    public void setDer(Nodo<T> der) {
        this.der = der;
    }
    
    public void insertar(T ValorInsertar) 
    {
        if (ValorInsertar.compareTo(info) < 0) {
            if (izq == null) {
                izq = new Nodo<T>(ValorInsertar);
            } else {
                izq.insertar(ValorInsertar);
            }
        } else if (ValorInsertar.compareTo(info)>0) {            
            if (der == null) {
                der = new Nodo<T>(ValorInsertar);
            } else {
                der.insertar(ValorInsertar);
            }
        }
    }
    
    
 
    public String EnOrden()
    {
        String salida = "";
        if (izq != null) {
            salida += izq.EnOrden();
        }
        
        salida += info + ", ";
        
        if (der != null) {
            salida += der.EnOrden();
        }
        
        return salida;
    }
    
    public String PreOrder()
    {
        String salida = "";
        salida += info + ", ";
        
        if (izq != null) {
            salida += izq.PreOrder();
        }
        
        if (der != null) {
            salida += der.PreOrder();
        }
        
        return salida;
    }
    
        public String PosOrder()
    {
        String salida = "";
        
        if (izq != null) {
            salida += izq.PosOrder();
        }
        
        if (der != null) {
            salida += der.PosOrder();
        }
        
        salida += info + ", ";
        
        return salida;
    }
    
    public void dibujarNodo(Graphics g, int x, int nivel)
    {
        int hor = (int)(250 / Math.pow(2,nivel+1));
        
        if (izq != null) {
            g.drawLine(265 + x, 40 * nivel + 30, 265 + x - hor, 40 * nivel + 40);
            izq.dibujarNodo(g, x - hor, nivel + 1);
        }
        
        g.drawOval(250 + x, 40 * nivel, 30, 30);
        g.drawString(info.toString(), 262 + x, 40 * nivel + 20 );
        
        if (der != null) {
            g.drawLine(265 + x, 40 * nivel + 30, 265 + x + hor, 40 * nivel + 40);
            der.dibujarNodo(g, x + hor, nivel + 1);
        }        
    }
}