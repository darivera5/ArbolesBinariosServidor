import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import javax.swing.JTextArea;
import javax.swing.JTextField;


public class Servidor extends Thread{
    
    JTextArea texto;
    Tablero tablero;
    JTextField inMensaje;
    int puerto;
    
    Socket cliente;
    BufferedReader entrada;
    PrintWriter salida;
    
    // Instanciar clase Arbol
    Arbol arbol = new Arbol<>();
    
    public Servidor(Tablero tablero, int puerto,JTextArea mensaje) {
        this.tablero = tablero;
        texto = mensaje;
        this.puerto = puerto;
        start();
    }

    public void conectar() throws IOException {
        ServerSocket servidor = new ServerSocket(puerto);
        texto.append("Servidor iniciado en el puerto: " 
                + puerto + ", esperando cliente...\n");
        cliente = servidor.accept();

        entrada = new BufferedReader(new InputStreamReader(
                cliente.getInputStream()));

        salida = new PrintWriter(cliente.getOutputStream());
    }

    public void enviar(String msg) {
        arbol.EnOrden();
        //texto.append( msg );
        salida.println(msg);
        salida.flush();
        
    }

    public void desconectar() throws IOException {

        salida.close();
        entrada.close();
        cliente.close();

    }

    public void run() {
        try {
            conectar();
            String linea;
            String Dat[];
            String EnviarD;
            Boolean Existe;
            while (true) {
                while ((linea = entrada.readLine()) != null) {
                    Dat = linea.split(",");
                    
                    if(linea.equals("EnOrden")){
                         System.out.println(arbol.EnOrden());
                         enviar(arbol.EnOrden());
                    }else if(linea.equals("PosOrden")){
                         System.out.println(arbol.PosOrder());
                         enviar(arbol.PosOrder());
                    }else if(linea.equals("PreOrder")){
                         System.out.println(arbol.PreOrder());
                         enviar(arbol.PreOrder());
                    }else if(Dat[0].equals("Buscar")){
                        Existe = arbol.existe(Integer.parseInt(Dat[1]));
                        if(Existe){
                           EnviarD = ("El numero: " + Dat[1] + " Existe");
                           enviar(EnviarD);
                        }else{
                            EnviarD = "El numero: " + Dat[1] + " No Existe";
                            enviar(EnviarD);
                        }
                        System.out.println("Existe: " + Existe);
                        break;
                    }
                    
                    else{
                        String[] datos = linea.split(",");
                        for (String dato : datos) {
                        arbol.insertar(Integer.parseInt(dato));
                        tablero.setArbol(arbol);
                        tablero.repaint();                 
                        }
                    } 
               }
            }
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
