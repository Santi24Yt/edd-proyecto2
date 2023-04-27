package mx.unam.ciencias.edd.proyecto2;

import mx.unam.ciencias.edd.Lista;

public class ListaSVG<T> {

  private String svg = "<?xml version='1.0' encoding='UTF-8' ?>\n";

  private int ancho;
  private int alto = 100;

  public ListaSVG(Lista<T> l) {
    ancho = l.getElementos()*100 + l.getElementos()*80;
    svg += "<svg version='1.1' width='"+(ancho+4)+"' height='"+(alto+4)+"'>\n";

    int i = 0;
    for(T e : l) {
      svg += "  <rect x='"+(180*i+2)+"' y='12' width='100' height='80' fill='white' stroke='black' />\n";
      svg += "  <text x='"+(180*i+52)+"' y='67' text-anchor='middle' font-size='40' fill='black'>"+e.toString()+"</text>\n";
      if(i+1 < l.getElementos())
      {
        svg += "  <rect x='"+(180*(i+1)-80+2)+"' y='2' width='80' height='100' fill='white' stroke='black' />\n";
        svg += "  <text x='"+(180*(i+1)-80+2+40)+"' y='72' text-anchor='middle' font-size='70' fill='black'>⇄</text>\n";
      }
      i++;
    }    

    svg += "</svg>";
  }

  public String getSvg() {
    return svg;
  }
}
