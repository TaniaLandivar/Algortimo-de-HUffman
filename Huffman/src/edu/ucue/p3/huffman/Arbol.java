/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ucue.p3.huffman;

/**
 *
 * @author davidvalladarez
 */
public class Arbol extends Nodo {
    private Nodo raiz;
    
    public Arbol()
    {
        raiz = null;
    }
    
    public Arbol(Nodo node1, Nodo node2)
    {
        raiz = super.agregarNodo(node1, node2);
    }
    
    public void insertarNodo(int freq, char c)
    {
        raiz.frecuencia =  freq;
        raiz.c = c;
        raiz.izquierda = null;
        raiz.derecha = null;
    }
    
    public void insertarNodo(int freq, char c, Nodo left, Nodo right)
    {
        raiz.frecuencia =  freq;
        raiz.c = c;
        this.raiz.izquierda = left;
        this.raiz.derecha = right;
    }
    
    public void insertarNodo(Nodo node)
    {
        this.raiz.frecuencia = node.frecuencia;
        this.raiz.c = node.c;
        this.raiz.izquierda = node.izquierda;
        this.raiz.derecha = node.derecha;
    }
    
    public void insertarNodo(Nodo node1, Nodo node2)
    {
        raiz = super.agregarNodo(node1, node2);   
    }

}
