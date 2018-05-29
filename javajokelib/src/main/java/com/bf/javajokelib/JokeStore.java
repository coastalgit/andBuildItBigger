package com.bf.javajokelib;

import java.util.ArrayList;
import java.util.Random;

public class JokeStore {
    private ArrayList<String> mStore;

    public JokeStore() {
        mStore = new ArrayList<>();
        mStore.add("Q: Joke 1" + "\n" + "A: HaHa");
        mStore.add("Q: Joke 2" + "\n" + "A: HaHa");
        mStore.add("Q: Joke 3" + "\n" + "A: HaHa");
        mStore.add("Q: Joke 4" + "\n" + "A: HaHa");
        mStore.add("Q: Joke 5" + "\n" + "A: HaHa");
    }

    public String giveMeAJoke(){
        Random random = new Random();
        return mStore.get(random.nextInt(mStore.size()));
    }

}
