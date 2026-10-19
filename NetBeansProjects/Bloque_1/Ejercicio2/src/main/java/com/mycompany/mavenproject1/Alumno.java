/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mavenproject1;

/**
 *
 * @author PC114
 */
public class Alumno {
    
    //Atributos
        String nom;
        String email;
        String naci;
        String gen;
        String tit;
        String obs;
    //Constructor
    public Alumno(String nom, String email, String naci, String gen, String tit, String obs){
        this.email = email;
        this.nom = nom;
        this.naci = naci;
        this.gen = gen;
        this.tit = tit;
        this.obs = obs;
        
    }
    
    //Setters
    public void setEmail(String email){
        this.email = email;
    }
    public void setNom(String nom){
        this.nom = nom;
    }
    public void setNaci(String naci){
        this.naci = naci;   
    }
    public void setGen(String gen){
        this.gen = gen;
    }
    public void setTit(String tit){
        this.gen = tit;
    }
    public void setObs(String obs){
        this.gen = obs;
    }
    
    //Getters
    public String getEmail(){
        return this.email;
    }
    public String getNom(){
        return this.nom;
    }
    public String getNaci(){
        return this.naci;
    }
    public String getGen(){
        return this.gen;
    }
    public String getTit(){
        return this.tit;
    }
    public String getObs(){
        return this.obs;
    }
    
    //toString
    public String toString(){
        return "El alumno " + this.nom + " con el email " + this.email + " nacido el " + this.naci + " con genero " + this.gen + " titulo " + this.tit + " y con estas observaciones " + this.obs;
    }
}
