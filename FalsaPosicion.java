

import org.nfunk.jep.*;

public class FalsaPosicion {
    private JEP funcion;
    private int iter;
    private double  m, fa,fb, fm, fafm,aux;
    private String error,text;

    //constructor que recibe los valores para calcularñ
    public FalsaPosicion(double a, double b, String expresion, double er)throws ParseException{
        m=0.0;
        text="";
        //creamos una nueva instancia del objeto para poder usar las funciones de JEP
        funcion = new JEP();

        //añadimos las constantes y funciones estandard que posiblemente se lleguen a utilizar
        funcion.addStandardConstants();
        funcion.addStandardFunctions();

        for(int i=1;true;i++){
            aux=m;
            //evalua la expresion para f(a)
            funcion.addVariable("x",a);
            funcion.parseExpression(expresion);
            fa=truncar(funcion.getValue());

            //evalua la expresion para f(b)
            funcion.addVariable("x",b);
            funcion.parseExpression(expresion);
            fb=truncar(funcion.getValue());

            //calcula el valor de x
            m=truncar(((a*fb)-(b*fa))/(fb-fa));

            //evalua la expresion para f(x)
            funcion.addVariable("x",m);
            funcion.parseExpression(expresion);
            fm=truncar(funcion.getValue());

            //obtiene el valor de la multiplicación de f(a) * f(x)
            fafm=truncar(fa*fm);

            setTtext("Iteraccion:~$ "+(i)+"- [a]: "+a+" [b]: "+b+" [m]: "+m+" [f(a)]: "+fa+" [f(b)] "+fb+"  [f(m)]: "+fm+" [f(a)f(m)]: "+fafm);

            if(i+1>=2)
                if(Math.abs(m-aux)<er){
                    setTtext("Raiz encontrada~$ "+m+""); 
                    break;
                }

            if(fafm>0){
                a=m;
                b=b;
            }else
            if(fafm<0){
                a=a;
                b=m;
            }
        }
    }

    //Metodo para truncar la cifra
    public double truncar(double valor){
        return Math.floor(valor*10000)/10000.d;
    }

    public void setTtext(String txt){
        text+=txt+"\n";
    }

    public String getTtext(){
        return text;
    }

}
