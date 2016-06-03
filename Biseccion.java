

import org.nfunk.jep.*;

public class Biseccion {
    private JEP funcion;
    private int iter;
    private double x, fa,fb, fx, fafx;
    private String error,text;

    //constructor que recibe los valores para calcular
    
    public Biseccion(double a, double b, String expresion, double er)throws ParseException{
        text="";
        //creamos una nueva instancia del objeto para poder usar las funciones de JEP
        funcion = new JEP();

        //añadimos las constantes y funciones estandard 
        //que posiblemente se lleguen a utilizar
        funcion.addStandardConstants();
        funcion.addStandardFunctions();

        //calculamos las posibilidades
        iter=(int)Math.ceil((Math.log((b-a)/er)/Math.log(2))-1);

        //For que lleva el control de las iteraciones dentro 
        //de las posinilidades
        for(int i=0;i<=iter;i++){
            //evalua la expresion para f(a)
            funcion.addVariable("x",a);
            funcion.parseExpression(expresion);
            fa=truncar(funcion.getValue());//truncarCifra();

            //calcula el valor de x
            x=truncar((a+b)/2.d);//truncarCifra()

            //evalua la expresion para f(x)
            funcion.addVariable("x",x);
            funcion.parseExpression(expresion);
            fx=truncar(funcion.getValue());//truncarCifra();

            //obtiene el valor de la multiplicación de f(a) * f(x)
            fafx=truncar(fa*fx);

            setTtext("Iteraccion:~$ "+(i)+"- a: "+a+" b: "+b+" x: "+x+" f(a): "+fa+" f(x): "+fx+" f(a)f(x): "+fafx);
            
            //if que evaluan las condiciones para asignar un valor a 
            //cierta variable o delvolver la raiz
            if(fafx<0){
                a=a;
                b=x;
            }else
            if(fafx>0){
                a=x;
                b=b;
            }else{
                setTtext("¡Raiz Encontrada! -  [raiz]: "+x);
                break;
            }
        }
    }

    //metodo para truncar la cifra a un numero de decimales.
    //La cantidad de numeros ceros indican hasta que punto 
    //disminuyen las cifras.
    public double truncar(double valor){
        return Math.floor(valor*10000)/10000.d;
    }

    //metodo para almacenar cadenas de caracteres a la variable text
    //la cual se pasara a un TextArea
    public void setTtext(String txt){
        text+=txt+"\n";
    }

    public String getTtext(){
        return text;
    }
}