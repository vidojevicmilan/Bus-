package com.nocomment.bus;
//cvor,stanica
public class LinkedNode {
    public int node;
    public Edge adj;
    public LinkedNode next;
    public int status;
    public String naziv;

    public LinkedNode(int nod, Edge potezi, LinkedNode cvor){
        node=nod;
        adj=potezi;
        next=cvor;
        status=0;
    }

    public LinkedNode(){
        //node=(Integer) null;
        adj=null;
        next=null;
        status=0;
        naziv=null;
    }

    public LinkedNode(int x){
        node=x;
        adj=null;
        next=null;
        status=0;
        naziv=null;
    }
    public LinkedNode(int x,String y){
        node=x;
        adj=null;
        next=null;
        status=0;
        naziv=y;
    }

    public String toString(){
        return naziv;
    }
    public void setName(String x){
        this.naziv=x;

    }
}
