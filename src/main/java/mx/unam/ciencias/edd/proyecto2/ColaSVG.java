package mx.unam.ciencias.edd.proyecto2;

import mx.unam.ciencias.edd.Lista;
import mx.unam.ciencias.edd.Cola;

public class ColaSVG<T> {

  private String svg = "<?xml version='1.0' encoding='UTF-8' ?>\n";

  private int ancho = 100;
  private int alto = 200;

  public ColaSVG(Cola<T> p) {
    Lista<T> l = new Lista<T>();
    while(!p.esVacia())
    {
      l.agregaFinal(p.saca());
    }
    ancho = 130 + l.getElementos()*100;
    svg += "<svg version='1.1' width='"+(ancho+4)+"' height='"+(alto+4)+"'>\n";
    
    svg += "<g stroke='black'>\n"+
           "    <rect width='100' height='50' x='10' y='110' fill='#594519' />\n"+
           "    <rect width='10' height='50' x='15' y='60' fill='#403112' />\n"+
           "    <rect width='10' height='50' x='95' y='60' fill='#403112' />\n"+
           "    <rect width='100' height='30' x='10' y='30' fill='#594519' />\n"+
           "    <text x='60' y='50' fill='black' text-anchor='middle' stroke='none' >Tortillas</text>\n"+
           "</g>\n";

    int i = 0;
    for(T e : l) {
      svg += "  <rect x='"+(130 + 100*i+2)+"' y='62' width='100' height='80' fill='white' stroke='black' />\n";
      svg += "  <text x='"+(130 + 100*i+52)+"' y='117' text-anchor='middle' font-size='40' fill='black'>"+e.toString()+"</text>\n";
      i++;
    }

    svg += "</svg>";
  }

  public String getSvg() {
    return svg;
  }
}
