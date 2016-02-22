package org.academiadecodigo.bootcamp.filipejorge.uberlisbondriver;

public class Main {

    public static void main(String[] args) throws InterruptedException, ClassNotFoundException, IllegalAccessException, InstantiationException {


        //Start Screen with instructions. Space to Start
        //Go to the green marker and stop to Pick up the client.
        //go to the red marker and stop to deliver the client.
        //go to the new green marker.. earn points for every client deliver.
        //taxis try to not crushing on to each other but they crush onto you.
        //you lose when you crash.


        //Game
        Game g = new Game(2);
        g.init(); // Creates a bunch of cars and randomly puts them in the field
        g.start(); // Starts the car crash animation

        //Game end. Score, play again?

    }
}
