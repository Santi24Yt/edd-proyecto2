package mx.unam.ciencias.edd.proyecto2;

import mx.unam.ciencias.edd.Grafica;
import mx.unam.ciencias.edd.VerticeGrafica;

public class GraficaSVG<T> {
  
  private String svg = "<?xml version='1.0' encoding='UTF-8' ?>\n";

  private int ancho;
  private int alto;

  private int radioGrande = 200;
  private int radio = 30;

  String vertices = "";

  public GraficaSVG(Grafica<T> g) {
    ancho = 2*radioGrande + 2*2*radio + 10;
    alto = ancho;
    svg += "<svg version='1.1' width='"+(ancho)+"' height='"+(alto)+"'>\n";
    
    int n = g.getElementos();
    int i = 0;
    T anterior = null;
    for(T v : g)
    {
      int x = (int)(Math.cos((2*3.14/n)*i) * radioGrande)+ancho/2;
      int y = (int)(Math.sin((2*3.14/n)*i) * radioGrande)+alto/2;
      vertices += "  <circle cy='"+(y)+"' cx='"+(x)+"' r='"+(radio)+"' />\n";
      vertices += "  <text x='"+(x)+"' y='"+(y+10)+"' text-anchor='middle' font-size='20' fill='white' >"+v.toString()+"</text>\n";
      if(anterior != null && g.sonVecinos(v, anterior)) {
        int xi = (int)(Math.cos((2*3.14/n)*(i-1)) * radioGrande)+ancho/2;
        int yi = (int)(Math.sin((2*3.14/n)*(i-1)) * radioGrande)+alto/2;
        svg += "  <line x1='"+x+"' x2='"+xi+"' y1='"+y+"' y2='"+yi+"' stroke='black' />\n";
      }
      i++;
      anterior = v;
    }

    svg += vertices;
    svg += "</svg>";
  }

  public String getSvg() {
    return svg;
  }
}
