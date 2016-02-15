package org.academiadecodigo.bootcamp.filipejorge.uberlisbondriver;

public class Main {

    public static void main(String[] args) throws InterruptedException, ClassNotFoundException, IllegalAccessException, InstantiationException {


        Game g = new Game(1);
        g.init(); // Creates a bunch of cars and randomly puts them in the field
        g.start(); // Starts the car crash animation


    }
}
