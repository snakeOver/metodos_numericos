

import org.lsmp.djep.djep.DJep;
import org.nfunk.jep.Node;
import org.nfunk.jep.ParseException;

import org.nfunk.jep.*;


public class NewtonRaphson{
    private JEP funcion;
    private DJep derivate;
    private double m, fa, fm, fafm;
    private String text;
    
    //constructor que recibe los valores para calcular
    public NewtonRaphson(double a, double b, String expresion, double error)throws ParseException{
        text="";
        //Creamos las nuevas instancias de los objetos que nos
        //serviran para resolver las expresiones
        funcion = new JEP();
        derivate = new DJep();

        //añadimos las constantes y funciones estandard que posiblemente se lleguen a utilizar
        funcion.addStandardConstants();
        funcion.addStandardFunctions();

        //añadimos las constantes y las funciones standar que
        //permitiran al resolver derivadas
        derivate.addStandardFunctions();
        derivate.addStandardConstants();
        derivate.addComplex();
        derivate.setAllowUndeclared(true);
        derivate.setAllowAssignment(true);
        derivate.setImplicitMul(true); 
        derivate.addStandardDiffRules();  

        //variable para llevar el contol del numero de incrementos
        //para el metodo
        int i=0;
        
        //While usado para llevar el control de las iteraciones de los metodos
        while(true){
            i++;
            //evalua la expresion para f(a)
            fa=desarrollar_funcion("x",a,expresion);

            //calcular el proximo valor o x_n+1
            m=truncar(a-(fa/desarrollar_funcion("x",a,derivar(expresion,"x"))));

            fafm = Math.abs(truncar(desarrollar_funcion("x",a,expresion)*desarrollar_funcion("x",m,expresion)));
            setTtext("Iteraccion:~$ "+(i)+"- [a] "+a+" [m] "+m+" [f(a)f(m)]"+fafm);

            if(fafm<error){
                setTtext("Raiz~$  " + m );
                break;
            }
            //intercambiamos a los valores para que b siga con el valor
            //mas actual, mientras que a recibe el valor de b.
            a=m;
        }

    }

    //Metodo que desarrolla la funcion de la forma f(x)
    public double desarrollar_funcion(String var, double num,String exp){
        funcion.addVariable(var,num);
        funcion.parseExpression(exp);
        return truncar(funcion.getValue());
    }

    //Metodo para truncar una cifra a un numero dado de decimales
    public double truncar(double valor){
        return Math.floor(valor*100000)/100000.d;
    }

    //Metodo auxiliar para desarrollar la derivada
    public String derivar(String expresion, String var){
        try{
            Node nodo_der = derivate.parse(expresion);
            Node differ = derivate.differentiate(nodo_der, var);
            Node simpli = derivate.simplify(differ);
            expresion = derivate.toString(simpli);
        }catch(ParseException pe){
            return "error";
        }
        return expresion;
    }

    //Metodo para añadir texto con un salto de linea
    //para mostrarlo en un TextArea
    public void setTtext(String txt){
        text+=txt+"\n";
    }

    //Devuelve el texto agregado en la variable text
    public String getTtext(){
        return text;
    }
}
