/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ucue.p3.huffman;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author davidvalladarez
 */
public class HuffmanTransversor {
    private Nodo nodoRaiz;
    private String patronDeBitsHuffman = "";
    //private StringBuilder texto;
    
    public HuffmanTransversor(Nodo nodo, File archivo) throws FileNotFoundException, IOException
    {
        //texto = new StringBuilder("");
        String temp;
        int i;
        nodoRaiz = nodo;
        //this.charArray = charArray;
        FileWriter fw = new FileWriter(new File("comprimido"));
        //DataOutputStream fw = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(new File("comprimido.dat"))));
        FileReader fr = new FileReader(archivo);
        int caracter = fr.read();
        //charArray = value.toCharArray();
        while(caracter != -1)
        {
            temp = search(nodoRaiz, "", (char) caracter);
            patronDeBitsHuffman += temp + " ";
            //System.out.println("My values: "+charArray[i]+" "+temp);
            /*texto.append("El valor de ");
            texto.append((char) caracter);
            texto.append(" es: ");
            texto.append(temp);
            texto.append("\n");*/
            caracter = fr.read();
            fw.write(temp);
            //fw.writeUTF(temp);
        }
        fr.close();
        fw.close();
        /*texto.append("Mi representación final del patrón de bits es: ");
        texto.append(patronDeBitsHuffman);*/
        
        descomprimir(nodoRaiz, new File("comprimido"));
        /*DataInputStream flujoEntradaActual = new DataInputStream(new BufferedInputStream(new FileInputStream(new File("comprimido.dat"))));
        FileWriter flujoSalidaActual = new FileWriter(new File("originalNuevo.txt"));
        
        while(true)
        {
            try {	
                i = (int) search(nodoRaiz,flujoEntradaActual.readUTF(),0);
                flujoSalidaActual.write(i);
            } catch (EOFException ex) {
                flujoEntradaActual.close();
                flujoSalidaActual.close();
                break;
            } finally {
                flujoEntradaActual.close();
                flujoSalidaActual.close();
                break;
            }
                
        }*/
        
      //  System.out.println("My final Bit Pattern: "+finalBitPattern);
    }
    
    public String search(Nodo nodoRaiz, String valor, char caracter)
    {
        String valorHuffman ="";
        if(nodoRaiz != null)
        {
            if(nodoRaiz.izquierda != null)
                valorHuffman = search(nodoRaiz.izquierda, valor+"0", caracter);
            if(nodoRaiz.c == caracter)
                return valor;

            else
            {
                if(valorHuffman == "")
                {
                    return search(nodoRaiz.derecha, valor+"1",caracter);
                }
                else
                {
                    return valorHuffman;
                }
            }
        }
        else
        {
            return "";
        }
    }

    public char search(Nodo nodoRaiz, String valor, int i) {
        if(valor.length() != i)
        {
            StringBuilder c = new StringBuilder();
            c.append(valor.charAt(i));
            if(nodoRaiz.izquierda != null && Integer.parseInt(c.toString()) == 0)
                return search(nodoRaiz.izquierda, valor, i+1);
            else if (nodoRaiz.derecha != null && Integer.parseInt(c.toString()) == 1)
                return search(nodoRaiz.derecha, valor, i+1);
            else
                return nodoRaiz.c;
        } else {
            return nodoRaiz.c;
        }
    }
    
    public void descomprimir(Nodo nodoRaiz, File archivo) throws FileNotFoundException, IOException {
        FileReader fr = new FileReader(archivo);
        FileWriter fw = new FileWriter(new File("originarNuevo.txt"));
        //String texto = "";
        int caracter = fr.read();
        while(caracter != -1) {
            if((char)caracter == '0'){
                if(nodoRaiz.izquierda != null) {
                    nodoRaiz = nodoRaiz.izquierda;
                } else {
                    fw.write(String.valueOf(nodoRaiz.c));
                    //texto += nodoRaiz.c;
                    nodoRaiz = this.nodoRaiz;
                    nodoRaiz = nodoRaiz.izquierda;
                }
            } else {
                if((char)caracter == '1') {
                    if(nodoRaiz.derecha != null) {
                        nodoRaiz = nodoRaiz.derecha;
                    } else {
                        fw.write(String.valueOf(nodoRaiz.c));
                        //texto += nodoRaiz.c;
                        nodoRaiz = this.nodoRaiz;
                        nodoRaiz = nodoRaiz.derecha;
                    }
                }
            }
        caracter = fr.read();
        }
        fw.close();
        //PrintWriter file = new PrintWriter("Descomprimido.txt");
        //file.print(texto);
    }
    
    /*public StringBuilder getTexto() {
        return texto;
    }*/
 
}
