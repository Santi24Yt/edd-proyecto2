package mx.unam.ciencias.edd.proyecto2;

import mx.unam.ciencias.edd.Lista;

public class Proyecto2 {
  
  public static void main(String[] args) {
    ReadStream input = null;
    try{
      input = new ReadStream(args[0]);
    }catch(Exception e){
      try{
        input = new ReadStream();
      }catch(Exception e2){
        System.err.println(e2);
        System.exit(1);
      }
    }

    Lista<String> palabras = input.words();
    palabras.forEach((w) -> System.out.println(w));
  }
}
