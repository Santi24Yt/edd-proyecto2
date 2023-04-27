package mx.unam.ciencias.edd.proyecto2;

import mx.unam.ciencias.edd.Lista;
import mx.unam.ciencias.edd.Cola;

public class ColaSVG<T> {

  private String svg = "<?xml version='1.0' encoding='UTF-8' ?>\n";

  private int ancho = 100;
  private int alto;

  public ColaSVG(Cola<T> p) {
    Lista<T> l = new Lista<T>();
    while(!p.esVacia())
    {
      l.agregaInicio(p.saca());
    }
    alto = l.getElementos()*80;
    svg += "<svg version='1.1' width='"+(ancho+4)+"' height='"+(alto+4)+"'>\n";
   
    int i = 0;
    for(T e : l) {
      svg += "  <rect y='"+(80*i+2)+"' x='2' width='100' height='80' fill='white' stroke='black' />\n";
      svg += "  <text y='"+(80*i+2+54)+"' x='52' text-anchor='middle' font-size='40' fill='black'>"+e.toString()+"</text>\n";
      if(i % 4 == 2)
        svg += "  <text y='"+(80*i+2+20)+"' x='90' text-anchor='middle' font-size='35' fill='black'>⇣</text>\n";
      i++;
    }

    svg += "  <rect y='0' x='0' width='"+(ancho+4)+"' height='2' fill='white' />\n";
    svg += "  <rect y='"+(alto)+"' x='0' width='"+(ancho+4)+"' height='2' fill='white' />\n";
    svg += "</svg>";
  }

  public String getSvg() {
    return svg;
  }
}
