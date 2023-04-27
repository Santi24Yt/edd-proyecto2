package mx.unam.ciencias.edd.proyecto2;

import java.util.Iterator;

import mx.unam.ciencias.edd.ArbolAVL;
import mx.unam.ciencias.edd.ArbolBinarioCompleto;
import mx.unam.ciencias.edd.ArbolBinarioOrdenado;
import mx.unam.ciencias.edd.ArbolRojinegro;
import mx.unam.ciencias.edd.Cola;
import mx.unam.ciencias.edd.Grafica;
import mx.unam.ciencias.edd.Lista;
import mx.unam.ciencias.edd.Pila;

public class Proyecto2 {
  
  public static void main(String[] args) {
    ReadStream input = null;
    Lista<String> palabras = null;
    try{
      input = new ReadStream(args[0]);
      palabras = input.words();
    }catch(Exception e){
      if(args.length == 0)
      {
        try{
          input = new ReadStream();
        }catch(Exception e2){
          System.err.println(e2);
          System.exit(1);
        }
        palabras = input.words();
      }else{
        palabras = new Lista<String>();
        for(String a : args) {
          palabras.agrega(a);
        }
      }
    }

    String clase = palabras.eliminaPrimero();

    Lista<Integer> elementos = new Lista<Integer>();
    for(String w : palabras) {
      try{
        int n = Integer.parseInt(w);
        elementos.agrega(n);
      }catch(NumberFormatException nfe){
        System.err.println("Los elementos deben ser números enteros");
        System.exit(1);
      }
    }

    switch (clase) {
      case "Lista":
        Lista<Integer> l = new Lista<Integer>();
        for(int n : elementos)
          l.agrega(n);
        ListaSVG<Integer> lista = new ListaSVG<Integer>(l);
        System.out.println(lista.getSvg());
        break;

      case "Pila":
        Pila<Integer> p = new Pila<Integer>();
        for(int n : elementos)
          p.mete(n);
        PilaSVG<Integer> pila = new PilaSVG<Integer>(p);
        System.out.println(pila.getSvg());
        break;

      case "Cola":
        Cola<Integer> c = new Cola<Integer>();
        for(int n : elementos)
          c.mete(n);
        ColaSVG<Integer> cola = new ColaSVG<Integer>(c);
        System.out.println(cola.getSvg());
        break;

      case "ArbolBinarioCompleto":
        ArbolBinarioCompleto<Integer> abc = new ArbolBinarioCompleto<Integer>();
        for(int n : elementos)
          abc.agrega(n);
        ABCSVG<Integer> abcsvg = new ABCSVG<Integer>(abc);
        System.out.println(abcsvg.getSvg());
        break;

      case "ArbolBinarioOrdenado":
        ArbolBinarioOrdenado<Integer> abo = new ArbolBinarioOrdenado<Integer>();
        for(int n : elementos)
          abo.agrega(n);
        ABOSVG<Integer> abosvg = new ABOSVG<Integer>(abo);
        System.out.println(abosvg.getSvg());
        break;

      case "ArbolRojinegro":
        ArbolRojinegro<Integer> arn = new ArbolRojinegro<Integer>();
        for(int n : elementos)
          arn.agrega(n);
        ARNSVG<Integer> arnsvg = new ARNSVG<Integer>(arn);
        System.out.println(arnsvg.getSvg());
        break;

      case "ArbolAVL":
        ArbolAVL<Integer> avl = new ArbolAVL<Integer>();
        for(int n : elementos)
          avl.agrega(n);
        AVLSVG<Integer> avlsvg = new AVLSVG<Integer>(avl);
        System.out.println(avlsvg.getSvg());
        break;

      case "Grafica":
        if(elementos.getLongitud() % 2 != 0) {
          System.err.println("El número de elementos para gráficas debe ser par");
          System.exit(1);
        }
        Grafica<Integer> g = new Grafica<Integer>();
        Iterator<Integer> it = elementos.iterator();
        while(it.hasNext()) {
          int elem1 = it.next();
          g.agrega(elem1);
          int elem2 = it.next();
          if(elem1 != elem2) {
            g.agrega(elem2);
            g.conecta(elem1, elem2);
          }
        }
        GraficaSVG<Integer> gsvg = new GraficaSVG<Integer>(g);
        System.out.println(gsvg.getSvg());
        break;
    
      default:
        System.err.println("El primer argumento debe ser una de las clases permitidas");
        System.exit(1);
        break;
    }
  }
}
