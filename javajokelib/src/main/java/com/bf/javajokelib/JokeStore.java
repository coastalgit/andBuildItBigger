package com.bf.javajokelib;

import java.util.ArrayList;
import java.util.Random;

public class JokeStore {
    private ArrayList<String> mStore;

    public JokeStore() {
        mStore = new ArrayList<>();
        mStore.add("Q: Java joke 1" + "\n" + "A: HaHa");
        mStore.add("Q: Java joke 2" + "\n" + "A: HaHa");
        mStore.add("Q: Java joke 3" + "\n" + "A: HaHa");
        mStore.add("Q: Java joke 4" + "\n" + "A: HaHa");
        mStore.add("Q: Java joke 5" + "\n" + "A: HaHa");
    }

    public String giveMeAJoke(){
        Random random = new Random();
        return mStore.get(random.nextInt(mStore.size()));
    }

}
