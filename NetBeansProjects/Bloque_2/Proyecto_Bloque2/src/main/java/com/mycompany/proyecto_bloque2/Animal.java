/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto_bloque2;

/**
 *
 * @author USUARIO
 */
public class Animal {

    private String nombre;
    private int edad;
    private int estancia;
    private String affeccion;
    private String especie;
    private String ingreso;
    private String notas;
    
    Animal (String nombre,int edad,int estancia,String ingreso,String affeccion,String especie, String notas){
        this.nombre = nombre;
        this.edad =edad;
        this.estancia =estancia;
        this.ingreso = ingreso;
        this.affeccion = affeccion;
        this.especie = especie;
        this.notas = notas;
    }
    
    public String getNombre(){
        return nombre;
    }
    
    public int getEdad(){
        return edad;
    }
    
    public int getEstancia(){
        return estancia;
    }
    
    public String getAffeccion(){
        return affeccion;
    }
    
    public String getEspecie(){
        return especie;
    }
    
    public String getIngreso(){
        return ingreso;
    }
    public String getNotas(){
        return notas;
    }
    
    
    public void setNombre(String nombre){
        this.nombre = nombre;
    }
    
    public void setEdad(int edad){
        this.edad = edad;
    }
    
    public void setEstancia(int estancia){
        this.estancia = estancia;
    }
    
    public void setAffeccion(String affeccion){
        this.affeccion = affeccion;
    }
    
    public void setEspecie(String especie){
        this.especie = especie;
    }
    
    public void setIngreso(String ingreso){
        this.ingreso = ingreso;
    }
    
    public void setNotas(String notas){
        this.notas = notas;
    }
}
