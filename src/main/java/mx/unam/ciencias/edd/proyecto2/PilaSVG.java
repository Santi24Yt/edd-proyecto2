package mx.unam.ciencias.edd.proyecto2;

import mx.unam.ciencias.edd.Lista;
import mx.unam.ciencias.edd.Pila;

public class PilaSVG<T> {

  private String svg = "<?xml version='1.0' encoding='UTF-8' ?>\n";

  private int ancho = 200;
  private int alto;

  public PilaSVG(Pila<T> p) {
    Lista<T> l = new Lista<T>();
    while(!p.esVacia())
    {
      l.agrega(p.saca());
    }
    alto = l.getElementos()*35 + 30;
    svg += "<svg version='1.1' width='"+(ancho+4)+"' height='"+(alto+4)+"'>\n";
   

    int i = 0;
    for(T e : l) {
      svg += "  <rect y='"+(35*i+2+ 30)+"' x='2' width='100' height='35' fill='#dbd1bd' stroke='black' />\n";
      svg += "  <path fill='#876d36' stroke='black' d='"+String.format("M %d,%d L %d,%d L %d,%d L %d,%d L %d,%d", (100+2), (35*i+2+ 30), (180+2), (35*i-15+2+ 30), (180+2), (35*i-15+2+ 30+35), (100+2), (35*i+2+ 30+35), (100+2), (35*i+2+ 30))+"' />\n";
      svg += "  <text y='"+(35*i+2+24+ 30)+"' x='52' text-anchor='middle' font-size='20' fill='black'>"+e.toString()+"</text>\n";
      i++;
    }

    i = 0;
    svg += "  <path fill ='#bb974b' stroke='black' d='"+String.format("M %d,%d L %d,%d L %d,%d L %d,%d L %d, %d", (100+2), (35*i+2+ 30), (180+2), (35*i-15+2+ 30), 82, 2+ 30-15, 2, 2+ 30, (100+2), (35*i+2+ 30))+"' />\n";

    svg += "</svg>";
  }

  public String getSvg() {
    return svg;
  }
}
