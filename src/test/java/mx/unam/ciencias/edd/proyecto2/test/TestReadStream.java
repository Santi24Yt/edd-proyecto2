package mx.unam.ciencias.edd.proyecto2.test;

import java.io.BufferedReader;
import java.io.Reader;
import java.io.StringReader;
import java.util.Random;
import mx.unam.ciencias.edd.Lista;
import mx.unam.ciencias.edd.proyecto2.ReadStream;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

/**
 * Clase para pruebas unitarias de la clase ReadStream
 */
public class TestReadStream {

    /** Expiración para que ninguna prueba tarde más de 5 segundos. */
    @Rule public Timeout expiracion = Timeout.seconds(5);

    /* Generador de números aleatorios. */
    private Random random;


    public TestReadStream() {
        random = new Random();
    }

    @Test public void testWordsRandom() {
      String[] posibles = {
        "         ",            //0
        "\t",                   //1  
        "\n",                   //2
        "palabra1 ",            //3
        "palabra2 ",            //4
        "palabra3 ",            //5
        "palabra4 ",            //6
        "palabra5 ",            //7
        "palabra6 ",            //8
        "palabra7 ",            //9
        "#inicioComentario "    //10
      };
      String s = "";
      boolean comment = false;
      Lista<String> words = new Lista<String>();
      for(int i = 0; i < 100+random.nextInt(100); i++) {
        int e = random.nextInt(posibles.length);
        String w = posibles[e];
        s += w;
        if(e == 10)
          comment = true;
        if(e == 2 && comment)
          comment = false;
        
        if(!comment && e >= 3 && e <= 9)
          words.agrega(w.trim());
      }
      Reader inputString = new StringReader(s);
      BufferedReader reader = new BufferedReader(inputString);
      Lista<String> palabras = ReadStream.words(reader);
      Assert.assertTrue(palabras.equals(words));
    }

    @Test public void testWordsEjemplo() {
      String s = "# Clase:\n"+
                 "      ArbolRojinegro\n"+
                 "  # Elementos:\n"+
                 "  1 2 3 4\n"+
                 "# Más elementos\n"+
                 "5 6 7 8\n"+
                 "                      # Todavía MÁS elementos\n"+
                 "                      9 10 11 12\n"+
                 "  # Los últimos elementos\n"+
                 "  13 14 5";
      
      String r = "ArbolRojinegro 1 2 3 4 5 6 7 8 9 10 11 12 13 14 5";

      Reader inputString1 = new StringReader(s);
      BufferedReader reader1 = new BufferedReader(inputString1);
      Lista<String> palabras1 = ReadStream.words(reader1);

      Reader inputString2 = new StringReader(r);
      BufferedReader reader2 = new BufferedReader(inputString2);
      Lista<String> palabras2 = ReadStream.words(reader2);
      Assert.assertTrue(palabras1.equals(palabras2));

      ReadStream input = new ReadStream("testinput.txt");
      Lista<String> palabras3 = input.words();
      Assert.assertTrue(palabras2.equals(palabras3));
    }
    
}
