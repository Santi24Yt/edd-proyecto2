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

  private String rojo = "#CF2B2B";
  private String negro = "#404040";
  private String colorArista = "#E6E6E6";

  public ARNSVG(ArbolRojinegro<T> arn) {
    ancho = (1 << arn.altura()) * 2 * (radio+8);
    alto  = arn.altura() * 2 * radio + 2*radio;
    svg += "<svg version='1.1' width='"+(ancho+4)+"' height='"+(alto+4)+"'>\n";
    svg += "  <defs>\n" +
           "   <radialGradient id='sphereRed' cx='70%' cy='30%' r='70%'\n" +
           "   gradientUnits='objectBoundingBox' >\n" +
           "      <stop offset='0%' stop-color='"+cambiarV(rojo, 30)+"' />\n" +
           "      <stop offset='10%' stop-color='"+rojo+"' />\n" +
           "      <stop offset='100%' stop-color='"+cambiarV(rojo, -90)+"' />\n" +
           "    </radialGradient>\n"+
           "   <radialGradient id='sphereBlack' cx='70%' cy='30%' r='70%'\n" +
           "   gradientUnits='objectBoundingBox' >\n" +
           "      <stop offset='0%' stop-color='"+cambiarV(negro, 40)+"' />\n" +
           "      <stop offset='10%' stop-color='"+negro+"' />\n" +
           "      <stop offset='100%' stop-color='"+cambiarV(negro, -40)+"' />\n" +
           "    </radialGradient>\n"+
           "    <linearGradient id='cylinder'>\n" +
           "     <stop offset='0%' stop-color='"+cambiarV(colorArista, -150)+"' />\n" +
           "     <stop offset='70%' stop-color='"+colorArista+"' />\n" +
           "     <stop offset='100%' stop-color='"+cambiarV(colorArista, -130)+"' />\n" +
           "   </linearGradient>\n" +
           " </defs>\n";

    this.arn = arn;

    aux(0, ancho, ancho/2, radio, arn.raiz());

    svg += vertices;
    
    svg += "</svg>";
  }

  private void aux(int ini, int fin, int x, int y, VerticeArbolBinario<T> v) {
    String color = arn.getColor(v) == Color.ROJO ? "sphereRed" : "sphereBlack";
    vertices += "  <circle cy='"+(y)+"' cx='"+(x)+"' r='"+(radio)+"' fill='url(\"#"+color+"\")' />\n";
    vertices += "  <text x='"+(x)+"' y='"+(y+10)+"' text-anchor='middle' font-size='20' fill='white' >"+v.get().toString()+"</text>\n";
    if(v.hayIzquierdo()) {
      int xi = (x+ini)/2;
      int yi = y + 2*radio;
      double co = xi - x;
      double ca = yi - y;
      double h = Math.sqrt(ca*ca + co*co);
      double a = Math.atan(co/ca)*180/Math.PI;
      svg += "  <line x1='"+x+"' x2='"+xi+"' y1='"+y+"' y2='"+yi+"' stroke='red' />\n";
      svg += "  <rect x='"+(x-5)+"' y='"+(y)+"' height='"+(Math.round(h))+"' width='10' fill='url(\"#cylinder\")'  transform='rotate("+(yi < y ? 180-a : -1*a)+", "+(x)+", "+y+")' />\n";
      aux(ini, x, xi, yi, v.izquierdo());
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
      aux(x, fin, xd, yd, v.derecho());
    }
  }


  public String getSvg() {
    return svg;
  }

  private String cambiarV(String c, int lvl) {
    int r = Integer.parseInt(c.substring(1, 3), 16);
    int g = Integer.parseInt(c.substring(3, 5), 16);
    int b = Integer.parseInt(c.substring(5, 7), 16);
    r += lvl;
    g += lvl;
    b += lvl;
    if(r < 0)
      r = 0;
    if(g < 0)
      g = 0;
    if(b < 0)
      b = 0;
    if(r > 255)
      r = 255;
    if(g > 255)
      g = 255;
    if(b > 255)
      b = 255;
    String color = "#"+String.format("%02X", r) + String.format("%02X", g) + String.format("%02X", b);
    return color;
  }
}
