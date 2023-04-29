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
    svg += "  <defs>" +
           "   <radialGradient id='sphere' cx='70%' cy='30%' r='70%'\n" +
           "   gradientUnits='objectBoundingBox' >\n" +
           "      <stop offset='0%' stop-color='#7679ff' />\n" +
           "      <stop offset='10%' stop-color='#6669ff' />\n" +
           "      <stop offset='100%' stop-color='#080052' />\n" +
           "    </radialGradient>\n"+
           "    <linearGradient id='cylinder'>\n" +
           "     <stop offset='0%' stop-color='#181062' />\n" +
           "     <stop offset='70%' stop-color='#6669ff' />\n" +
           "     <stop offset='100%' stop-color='#283082' />\n" +
           "   </linearGradient>\n" +
           " </defs>\n";

    aux(0, ancho, ancho/2, radio+40, avl.raiz(), true);

    svg += vertices;
    
    svg += "</svg>";
  }

  private void aux(int ini, int fin, int x, int y, VerticeArbolBinario<T> v, boolean izq) {
    vertices += "  <circle cy='"+(y)+"' cx='"+(x)+"' r='"+(radio)+"' fill='url(\"#sphere\")' />\n";
    vertices += "  <text x='"+(x)+"' y='"+(y+10)+"' text-anchor='middle' font-size='20' fill='white' >"+v.get().toString()+"</text>\n";
    vertices += "  <text x='"+(x-(izq ? 20 : -20))+"' y='"+(y-35)+"' text-anchor='middle' font-size='15' fill='black' >("+v.toString().split(" ")[1]+")</text>\n";
    if(v.hayIzquierdo()) {
      int xi = (x+ini)/2;
      int yi = y + 2*radio;
      double co = xi - x;
      double ca = yi - y;
      double h = Math.sqrt(ca*ca + co*co);
      double a = Math.atan(co/ca)*180/Math.PI;
      svg += "  <line x1='"+x+"' x2='"+xi+"' y1='"+y+"' y2='"+yi+"' stroke='red' />\n";
      svg += "  <rect x='"+(x-5)+"' y='"+(y)+"' height='"+(Math.round(h))+"' width='10' fill='url(\"#cylinder\")'  transform='rotate("+(yi < y ? 180-a : -1*a)+", "+(x)+", "+y+")' />\n";
      aux(ini, x, xi, yi, v.izquierdo(), true);
    }

    if(v.hayDerecho()) {
      int xd = (x+fin)/2;
      int yd = y + 2*radio;
      double co = xd - x;
      double ca = yd - y;
      double h = Math.sqrt(ca*ca + co*co);
      double a = Math.atan(co/ca)*180/Math.PI;
      svg += "  <line x1='"+x+"' x2='"+xd+"' y1='"+y+"' y2='"+yd+"' stroke='red' />\n";
      svg += "  <rect x='"+(x-5)+"' y='"+(y)+"' height='"+(Math.round(h))+"' width='10' fill='url(\"#cylinder\")'  transform='rotate("+(yd < y ? 180-a : -1*a)+", "+(x)+", "+y+")' />\n";
      aux(x, fin, xd, yd, v.derecho(), false);
    }
  }


  public String getSvg() {
    return svg;
  }
}
