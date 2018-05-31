package com.bf.javajokelib;

import java.util.ArrayList;
import java.util.Random;

public class JokeStore {
    private final ArrayList<String> mStore;

    public JokeStore() {
        mStore = new ArrayList<>();
//        mStore.add("Q: Java joke 1" + "\n" + "A: HaHa");
//        mStore.add("Q: Java joke 2" + "\n" + "A: HaHa");
//        mStore.add("Q: Java joke 3" + "\n" + "A: HaHa");
//        mStore.add("Q: Java joke 4" + "\n" + "A: HaHa");
//        mStore.add("Q: Java joke 5" + "\n" + "A: HaHa");

        mStore.add("Q: What do you call a Scottish iPhone?" + "\n" + "A: An AyePhone.");
        mStore.add("Q: What do you call an iPhone that isn’t kidding around?" + "\n" + "A: Dead Siri-ous.");
        mStore.add("Q: Eight bytes walk into a bar. The bartender asks, \"Can I get you anything?\"" + "\n" + "A: \"Yeah,\" reply the bytes. \"Make us a double.\"");
        mStore.add("Q: How many programmers does it take to change a light bulb?" + "\n" + "A: None. It's a hardware problem.");
        mStore.add("Q: How did the computer get out of the house?" + "\n" + "A: He used windows.");
        mStore.add("Q: What does a shark and a computer have in common?" + "\n" + "A: They both have megabites.");

    }

    public String giveMeAJoke(){
        Random random = new Random();
        return mStore.get(random.nextInt(mStore.size()));
    }

}
