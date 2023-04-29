package mx.unam.ciencias.edd.proyecto2;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import mx.unam.ciencias.edd.Lista;

/**
 * Clase para leer las lineas de un archivo o del stdin
 */
public class ReadStream {
  
  /* El buffer del que leeremos las lineas */
  private BufferedReader entrada;

  /**
   * Constructor para leer de un archivo
   * @param ruta la ruta a un archivo existente
   * @throws IllegalArgumentException si el archivo no existe
   */
  public ReadStream(String ruta) throws IllegalArgumentException {
    try{
      entrada = new BufferedReader(new InputStreamReader(new FileInputStream(ruta))); 
    }catch(IOException err){
      this.cierra();
      throw new IllegalArgumentException("Error al leer stream, es posible que sea un archivo inválido");
    }
  }

  /**
   * Constructor para leer del stdin
   * @throws Exception cuando hay un error al leer System.in
   */
  public ReadStream() throws Exception {
    try{
      entrada = new BufferedReader(new InputStreamReader(System.in));
    }catch(Exception err){
      this.cierra();
      throw new Exception("Error al leer System.in");
    }
  }

  /**
   * Cierra el buffer
   */
  private void cierra() {
    try{
      if(entrada != null)
        entrada.close();
    }catch(IOException err){}
  }

  static public Lista<String> words(BufferedReader in) {
    Lista<String> palabras = new Lista<String>();
    String palabra = "";
    boolean comentario = false;
    int charcode = -1;
    try{
      charcode = in.read();
    }catch(IOException e) {}
    while(charcode != -1) {
      if((char)charcode == '#')
        comentario = true;
      if((""+(char)charcode).matches("\\s")) {
        if(palabra != "")
          palabras.agrega(palabra);
        palabra = "";
        if((""+(char)charcode).matches("\\R"))
          comentario = false;
      }else{
        if(!comentario)
          palabra += (char)charcode;
      }
      try{
        charcode = in.read();
      }catch(IOException e){}
    }
    if(palabra != "")
      palabras.agrega(palabra);
    return palabras;
  }

  public Lista<String> words() {
    Lista<String> palabras = words(entrada);
    this.cierra();
    return palabras;
  }
}
