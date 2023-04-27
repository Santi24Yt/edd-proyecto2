package mx.unam.ciencias.edd.proyecto2;

import mx.unam.ciencias.edd.ArbolAVL;
import mx.unam.ciencias.edd.VerticeArbolBinario;

public class AVLSVG<T extends Comparable<T>> {
  
  private String svg = "<?xml version='1.0' encoding='UTF-8' ?>\n";

  private int radio = 30;

  private int ancho;
  private int alto;

  private String vertices = "";

  public AVLSVG(ArbolAVL<T> avl) {
    ancho = (1 << avl.altura()) * 2 * (radio+20);
    alto  = avl.altura() * 2 * radio + 2*radio + 50;
    svg += "<svg version='1.1' width='"+(ancho+4)+"' height='"+(alto+4)+"'>\n";

    aux(0, ancho, ancho/2, radio+40, avl.raiz());

    svg += vertices;
    
    svg += "</svg>";
  }

  private void aux(int ini, int fin, int x, int y, VerticeArbolBinario<T> v) {
    vertices += "  <circle cy='"+(y)+"' cx='"+(x)+"' r='"+(radio)+"' />\n";
    vertices += "  <text x='"+(x)+"' y='"+(y+10)+"' text-anchor='middle' font-size='20' fill='white' >"+v.get().toString()+"</text>\n";
    vertices += "  <text x='"+(x-20)+"' y='"+(y-30)+"' text-anchor='middle' font-size='15' fill='black' >("+v.toString().split(" ")[1]+")</text>\n";
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
