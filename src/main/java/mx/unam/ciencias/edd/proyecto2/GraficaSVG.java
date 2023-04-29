package mx.unam.ciencias.edd.proyecto2;

import mx.unam.ciencias.edd.Grafica;
import mx.unam.ciencias.edd.Lista;
import mx.unam.ciencias.edd.VerticeGrafica;

public class GraficaSVG<T> {
  
  private String svg = "<?xml version='1.0' encoding='UTF-8' ?>\n";

  private int ancho;
  private int alto;

  private int radioGrande = 200;
  private int radio = 30;

  String vertices = "";

  Lista<Vertice> listaVertices = new Lista<Vertice>();

  protected class Vertice {
    protected T e;
    protected int x;
    protected int y;

    protected Vertice(T e, int x, int y) {
      this.e = e;
      this.x = x;
      this.y = y;
    }
  }

  public GraficaSVG(Grafica<T> g) {
    ancho = 2*radioGrande + 2*2*radio + 10;
    alto = ancho;
    svg += "<svg version='1.1' width='"+(ancho)+"' height='"+(alto)+"'>\n";
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
    
    int n = g.getElementos();
    int i = 0;
    for(T v : g)
    {
      int x = (int)(Math.cos((2*3.14/n)*i) * radioGrande)+ancho/2;
      int y = (int)(Math.sin((2*3.14/n)*i) * radioGrande)+alto/2;
      Vertice vertice = new Vertice(v, x , y);
      listaVertices.agrega(vertice);
      vertices += "  <circle cy='"+(y)+"' cx='"+(x)+"' r='"+(radio)+"' fill='url(\"#sphere\")' />\n";
      vertices += "  <text x='"+(x)+"' y='"+(y+10)+"' text-anchor='middle' font-size='20' fill='white' >"+v.toString()+"</text>\n";
      i++;
    }

    String aristasDibujadas = "";
    for(Vertice v : listaVertices) {
      Iterable<? extends VerticeGrafica<T>> vecinos = g.vertice(v.e).vecinos();
      for(VerticeGrafica<T> vecino : vecinos) {
        if(aristasDibujadas.contains("("+v.e+", "+vecino.get()+")") || aristasDibujadas.contains("("+vecino.get()+", "+v.e+")"))
          continue;
        Vertice u = getVertice(vecino);
        int xi = u.x;
        int yi = u.y;
        int x = v.x;
        int y = v.y;
        double co = xi - x;
        double ca = yi - y;
        double h = Math.sqrt(ca*ca + co*co);
        double a = Math.atan(co/ca)*180/Math.PI;
        svg += "  <line x1='"+x+"' x2='"+xi+"' y1='"+y+"' y2='"+yi+"' stroke='red' />\n";
        svg += "  <rect x='"+(x-5)+"' y='"+(y)+"' height='"+(Math.round(h))+"' width='10' fill='url(\"#cylinder\")'  transform='rotate("+(yi < y ? 180-a : -1*a)+", "+(x)+", "+y+")' />\n";
        aristasDibujadas += "("+v.e+", "+u.e+")";
      }
    }

    svg += vertices;
    svg += "</svg>";
  }

  public String getSvg() {
    return svg;
  }

  private Vertice getVertice(VerticeGrafica<T> v) {
    for(Vertice x : listaVertices) {
      if(x.e.equals(v.get()))
        return x;
    }
    return null;
  }
}
