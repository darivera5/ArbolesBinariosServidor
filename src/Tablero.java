import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;

public class Tablero extends Canvas {
    private Arbol arbol;

    public void setArbol(Arbol arbol) {
        this.arbol = arbol;
    }
    
    @Override
    public void paint(Graphics g)
    {
        setBackground(Color.white);
        
        if (arbol != null) {
            arbol.dibujar(g);
        }
        
    }
}
