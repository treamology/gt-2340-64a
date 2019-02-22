package com.example.spacetrader.model.PlanetData;

import java.util.HashSet;
import java.util.Random;

public class PlanetGeneration {

    private static final String[] names = new String[]{"Acamar", "Campor", "Deneb"
            , "Endor", "Hulst", "Janus", "Ligon", "Melina", "Relva", "Sol"};
    private static HashSet<Integer> takenNames;

    /**
     * Resets the set of taken names, should be done upon game creation
     */
    public static void ClearNames() {
        takenNames = new HashSet<Integer>();
    }

    /**
     * Randomly picks a name from the planet without allowing duplicates
     * @return the name
     */
    public static String GenerateName () {
        String name = null;
        int index = new Random().nextInt(names.length);
        int endLoop = index;
        do {
            if (!takenNames.contains(index)) {
                takenNames.add(index);
                name = names[index];
            } else {
                index = (index + 1) % names.length;
            }
        } while (name == null && index != endLoop);
        if (name == null) {
            name = "Unidentified planet";
        }
        return name;
    }

}
