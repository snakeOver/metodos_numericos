   

/**
 * Este codigo fue desarrollado para la materia de Metodos Numericos
 * en la carrera de Ingenierias en Sistemas Computacionales.
 * Puede hacer uso de este cdigo, siempre y cuando respete el trabajo que he realizado.
 * 
 * @author (Alfredo_Reyes@Zeronull) 
 * @version (2.2)
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import org.nfunk.jep.*;

public class WindowsMethod extends JFrame
{
    private Biseccion biseccion;
    private FalsaPosicion falsaPosicion;
    private Secante secante;
    private NewtonRaphson newtonRaphson;

    private JTextField caja_f,caja_x0,caja_x1,caja_e;
    private JTextArea jta;
    private JComboBox jcb;
    private JLabel mensaje, function, a, b, error;
    private JButton calcular;

    public WindowsMethod(){
        super("Metodos de Resolucion de Ecuaciones no Lineales");
        //creamos todos las etiquetas del programa con sus respectivas cajas de texto
        //etiqueta y caja 1
        function=  new JLabel("Funcion: ");
        function.setBounds(20,120,70,30);

        caja_f= new JTextField("");
        caja_f.setBounds(90,120,100,30);

        //etiqueta y caja 2
        a= new JLabel("x0:");
        a.setBounds(200,120,30,30);

        caja_x0= new JTextField("");
        caja_x0.setBounds(230,120,80,30);

        //etiqueta y caja 3
        b= new JLabel("x1:");
        b.setBounds(330,120,50,30);

        caja_x1= new JTextField("");
        caja_x1.setBounds(360,120,80,30);

        //etiqueta y caja 4
        error= new JLabel("Error:");
        error.setBounds(20,170,50,30);

        caja_e= new JTextField("");
        caja_e.setBounds(90,170,100,30);

        mensaje = new JLabel("Seleccione el Metodo");
        mensaje.setBounds(20,20, 200, 30);

        //boton para calcular la funcion
        calcular = new JButton("Calcular");
        calcular.setBounds(230,170,110,30);
        calcular.setBackground(Color.black);
        calcular.setForeground(Color.white);

        jcb = new JComboBox (new DefaultComboBoxModel(new Object[]{"Biseccion","Falsa Posicion","Newton Raphson","Secante"}));
        jcb.setLocation(20,60);
        jcb.setSize(150,30);

        jta= new JTextArea();
        jta.setBounds(20, 220,460, 250);

        JScrollPane sp = new JScrollPane(jta);  
        sp.setBounds(20, 220,460, 250);

        setLayout(null);
        setSize(500,500);
        add(sp);
        add(jcb);
        add(mensaje);
        add(function);
        add(caja_f);
        add(a);
        add(caja_x0);
        add(b);
        add(caja_x1);
        add(error);
        add(caja_e);
        add(calcular);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setVisible(true);
        setLocationRelativeTo(null);
        metodo_boton();
    }

    public void metodo_boton(){
        calcular.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent ae){
                    try{
                        if(jcb.getSelectedIndex()==0){
                            biseccion = new Biseccion(Double.parseDouble(caja_x0.getText()),Double.parseDouble(caja_x1.getText()),caja_f.getText(),Double.parseDouble(caja_e.getText()));
                            jta.setText(biseccion.getTtext());
                        }
                        if(jcb.getSelectedIndex()==1){
                            falsaPosicion= new FalsaPosicion(Double.parseDouble(caja_x0.getText()),Double.parseDouble(caja_x1.getText()),caja_f.getText(),Double.parseDouble(caja_e.getText()));
                            jta.setText(falsaPosicion.getTtext());
                        }
                        if(jcb.getSelectedIndex()==3){
                            secante = new Secante(Double.parseDouble(caja_x0.getText()),Double.parseDouble(caja_x1.getText()),caja_f.getText(),Double.parseDouble(caja_e.getText()));
                            jta.setText(secante.getTtext());
                        }
                        if(jcb.getSelectedIndex()==2){
                            newtonRaphson = new NewtonRaphson(Double.parseDouble(caja_x0.getText()),Double.parseDouble(caja_x1.getText()),caja_f.getText(),Double.parseDouble(caja_e.getText()));
                            jta.setText(newtonRaphson.getTtext());
                        }
                    }catch(ParseException pe){

                    }
                }
            }
        );
    }

    public static void main(String []args){
        new WindowsMethod();
    }
}
