package mx.unam.ciencias.edd.proyecto2;

import mx.unam.ciencias.edd.ArbolRojinegro;
import mx.unam.ciencias.edd.Color;
import mx.unam.ciencias.edd.VerticeArbolBinario;

public class ARNSVG<T extends Comparable<T>> {
  
  private String svg = "<?xml version='1.0' encoding='UTF-8' ?>\n";

  private int radio = 30;

  private int ancho;
  private int alto;

  private String vertices = "";
  private ArbolRojinegro<T> arn;

  public ARNSVG(ArbolRojinegro<T> arn) {
    ancho = (1 << arn.altura()) * 2 * (radio+8);
    alto  = arn.altura() * 2 * radio + 2*radio;
    svg += "<svg version='1.1' width='"+(ancho+4)+"' height='"+(alto+4)+"'>\n";

    this.arn = arn;

    aux(0, ancho, ancho/2, radio, arn.raiz());

    svg += vertices;
    
    svg += "</svg>";
  }

  private void aux(int ini, int fin, int x, int y, VerticeArbolBinario<T> v) {
    String color = arn.getColor(v) == Color.ROJO ? "red" : "black";
    vertices += "  <circle cy='"+(y)+"' cx='"+(x)+"' r='"+(radio)+"' fill='"+color+"' />\n";
    vertices += "  <text x='"+(x)+"' y='"+(y+10)+"' text-anchor='middle' font-size='20' fill='white' >"+v.get().toString()+"</text>\n";
    if(v.hayIzquierdo()) {
      int xi = (x+ini)/2;
      int yi = y + 2*radio;
      svg += "  <line x1='"+x+"' x2='"+xi+"' y1='"+y+"' y2='"+yi+"' stroke='black' />\n";
      aux(ini, x, xi, yi, v.izquierdo());
    }

    if(v.hayDerecho()) {
      int xd = (x+fin)/2;
      int yd = y + 2*radio;
      aux(x, fin, xd, yd, v.derecho());
      svg += "  <line x1='"+x+"' x2='"+xd+"' y1='"+y+"' y2='"+yd+"' stroke='black' />\n";
    }
  }


  public String getSvg() {
    return svg;
  }
}
