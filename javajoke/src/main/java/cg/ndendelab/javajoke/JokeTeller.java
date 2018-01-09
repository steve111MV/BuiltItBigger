package cg.ndendelab.javajoke;

import java.util.Random;

public class JokeTeller {

    public static String[] jokesArray = {"There are only 2 types of people in the world: those that understand binary and those that don’t.",
            "The Internet: where men are men, women are men, and children are FBI agents.",
            "NT is the only OS that has caused me to beat a piece of hardware to death with my bare hands.",
            "Microsoft is not the answer. Microsoft is the question. NO is the answer.",
            "Computers are like air conditioners: they stop working when you open Windows"};

    public static String getRandomJoke(){

        Random random = new Random();

        return jokesArray[random.nextInt(jokesArray.length)];
    }

    public static String tellJoke(){

        Random random = new Random();

        return jokesArray[random.nextInt(jokesArray.length-1)];
    }
}
