

import org.nfunk.jep.*;

public class Secante
{   
    private JEP funcion;
    private double m, fa, fb,fa_fb; 
    private String text;

    //constructor que recibe los valores para calcular la raiz
    public Secante(double a, double b, String expresion, double error)throws ParseException{
        text="";
        //Creamos las nuevas instancias de JEP para poder usarlo
        funcion = new JEP();

        //añadimos las constantes y funciones estandard que 
        //posiblemente se lleguen a utilizar
        funcion.addStandardConstants();
        funcion.addStandardFunctions();

        int i=0;
        while(true){
            i++;
            //evalua la expresion para f(a)
            fa=desarrollar_funcion("x",a,expresion);

            //evalua la expresion para f(b)
            fb=desarrollar_funcion("x",b,expresion);

            //calcular el proximo valor o x_n+1
            m=truncar(b-((fb*(a-(b)))/(fa-fb)));

            fa_fb = Math.abs(truncar(desarrollar_funcion("x",a,expresion)*desarrollar_funcion("x",b,expresion)));
            setTtext("Iteraccion:~$ "+(i)+"- [a] "+a+" [b] "+b+" [m] "+m+" [f(a)]: "+fa+" [f(b)] "+fb+" [f(a)f(b)]"+fa_fb);

            //evaluacion para encontrar la raiz
            if(fa_fb<error){
                setTtext("Raiz~$  " + m );
                break;
            }
            //intercambiamos a los valores para que b siga con el valor
            //mas actual, mientras que a recibe el valor de b.
            a=b;
            b=m;
        }
    }

    public double desarrollar_funcion(String var, double num,String exp){
        funcion.addVariable(var,num);
        funcion.parseExpression(exp);
        return truncar(funcion.getValue());
    }

    //metodo para truncar la cifra
    public double truncar(double valor){
        return  Math.floor(valor*10000)/10000.d;
    }

    public void setTtext(String txt){
        text+=txt+"\n";
    }

    public String getTtext(){
        return text;
    }

}
