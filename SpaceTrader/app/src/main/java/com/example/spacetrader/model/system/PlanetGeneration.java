package com.example.spacetrader.model.system;

import java.util.HashSet;
import java.util.Random;

public class PlanetGeneration {

    private static final String[] names = new String[]{"Acamar", "Adahn", "Aldea", "Campor", "Deneb"
            , "Endor", "Hulst", "Janus", "Ligon", "Melina", "Relva", "Sol", "Antedi", "Balonsee"
            , "Baratas", "Brax", "Bretel", "Calondia", "Capelle", "Carzon", "Castor", "Cestus"
            , "Cheron", "Courteny", "Dale", "Damast", "Davlos", "Deneva", "Devidia"
            , "Draylon", "Drema", "Esmee", "Exo", "Ferris", "Festen", "Fourmi", "Frolix", "Gemulon"
            , "Guinifer", "Hades", "Hamlet", "Helena", "Iodine", "Iralius", "Japori", "Jarada"
            , "Jason", "Kaylon", "Khefka", "Kira", "Klaatu", "Klaestron", "Korma", "Kravat", "Krios"
            , "Laertes", "Largo", "Lave", "Lowry", "Magrat", "Malcoria", "Mentar", "Merik"
            , "Mintaka", "Montor", "Mordan", "Myrthe", "Nelvana", "Nix", "Nyle", "Odet", "Og"
            , "Omega", "Omphalos", "Oria", "Othello", "Parade", "Penthara", "Picard", "Pollux"
            , "Quator", "Rakhar", "Ran", "Regulus", "Rhymus", "Rochani", "Rubicum", "Rutia"
            , "Sarpeidon", "Sefalla", "Seltrice", "Sigma", "Somari", "Stakoron", "Styris", "Telani"
            , "Tamus", "Tantalos", "Tarnuga", "Tarkhannon", "Terosa", "Thera", "Titan", "Torin"
            , "Triacus", "Turkana", "Tyrus", "Umberlee", "Utopia", "Vadera", "Vagra", "Vandor"
            , "Ventax", "Xenon", "Xerxes", "Yew", "Yojimbo", "Zalkon", "Zuul"};
    private static HashSet<Integer> takenNames;

    /**
     * Resets the set of taken names, should be done upon game creation
     */
    public static void clearNames() {
        takenNames = new HashSet<Integer>();
    }

    /**
     * Randomly picks a name from the planet without allowing duplicates
     * @return the name
     */
    public static String generateName () {
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
