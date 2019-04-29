package com.example.spacetrader;

import org.junit.Test;

import org.junit.Before;
import static org.junit.Assert.*;

import com.example.spacetrader.model.GameState;
import com.example.spacetrader.model.TradeGood;
import com.example.spacetrader.model.Universe;
import com.example.spacetrader.model.system.SolarSystem;
import com.example.spacetrader.model.system.Position;
import com.example.spacetrader.model.system.TechLevel;


import java.util.List;
import java.util.LinkedHashMap;
/*Andrew's JUnit Test
 * Tests whether a planet correctly creates resources
 */
public class AndrewJUnitTest {

    private SolarSystem testSystem;
    @Before
    /*Sets up the test
    *
     */
    public void setUp() {


        Position p1 = new Position(1,1);
        GameState testState = GameState.generateGame("Player", 1, 1, 1, 1, GameState.Difficulty.BEGINNER);
        Universe testUniverse = testState.getUniverse();
        List<SolarSystem> testSystems = testUniverse.getSystems();
        testSystem = testSystems.get(1);


    }
    /*performs the test
    *
    */
    @Test
    public void testResources() {
        testSystem.generateGoodsAndPrices();
        LinkedHashMap<TradeGood, Integer> resourceMap = testSystem.getQuantities();
        TechLevel Level = testSystem.getTechLevel();
        int levelInt = Level.level;
        assertNotEquals(0,levelInt );
        assertNotEquals(resourceMap, null);
        switch(levelInt){
            case 0:
                assertEquals(0, (int) resourceMap.get(TradeGood.ROBOTS));
                assertEquals(0, (int) resourceMap.get(TradeGood.NARCOTICS));
                assertEquals(0, (int) resourceMap.get(TradeGood.MACHINES));
                assertEquals(0, (int) resourceMap.get(TradeGood.MEDICINE));
                assertEquals(0, (int) resourceMap.get(TradeGood.FIREARMS));
                assertEquals(0, (int) resourceMap.get(TradeGood.GAMES));
                assertEquals(0, (int) resourceMap.get(TradeGood.ORE));
                assertEquals(0, (int) resourceMap.get(TradeGood.FOOD));
                assertNotEquals(0, ((int) resourceMap.get(TradeGood.FOOD)));
                assertNotEquals(0, ((int) resourceMap.get(TradeGood.FURS)));
                break;
            case 1:
                assertEquals(0, (int) resourceMap.get(TradeGood.ROBOTS));
                assertEquals(0, (int) resourceMap.get(TradeGood.NARCOTICS));
                assertEquals(0, (int) resourceMap.get(TradeGood.MACHINES));
                assertEquals(0, (int) resourceMap.get(TradeGood.MEDICINE));
                assertEquals(0, (int) resourceMap.get(TradeGood.FIREARMS));
                assertEquals(0, (int) resourceMap.get(TradeGood.GAMES));
                assertEquals(0, (int) resourceMap.get(TradeGood.ORE));
                assertNotEquals(0, (int) resourceMap.get(TradeGood.FOOD));
                assertNotEquals(0, ((int) resourceMap.get(TradeGood.FOOD)));
                assertNotEquals(0, ((int) resourceMap.get(TradeGood.FURS)));

                break;
            case 2:
                assertEquals(0, (int) resourceMap.get(TradeGood.ROBOTS));
                assertEquals(0, (int) resourceMap.get(TradeGood.NARCOTICS));
                assertEquals(0, (int) resourceMap.get(TradeGood.MACHINES));
                assertEquals(0, (int) resourceMap.get(TradeGood.MEDICINE));
                assertEquals(0, (int) resourceMap.get(TradeGood.FIREARMS));
                assertEquals(0, (int) resourceMap.get(TradeGood.GAMES));
                assertNotEquals(0, (int) resourceMap.get(TradeGood.ORE));
                assertNotEquals(0, (int) resourceMap.get(TradeGood.FOOD));
                assertNotEquals(0, ((int) resourceMap.get(TradeGood.FOOD)));
                assertNotEquals(0, ((int) resourceMap.get(TradeGood.FURS)));

                break;

            case 3:
                assertEquals(0, (int) resourceMap.get(TradeGood.ROBOTS));
                assertEquals(0, (int) resourceMap.get(TradeGood.NARCOTICS));
                assertEquals(0, (int) resourceMap.get(TradeGood.MACHINES));
                assertEquals(0, (int) resourceMap.get(TradeGood.MEDICINE));
                assertNotEquals(0, (int) resourceMap.get(TradeGood.FIREARMS));
                assertNotEquals(0, (int) resourceMap.get(TradeGood.GAMES));
                assertNotEquals(0, (int) resourceMap.get(TradeGood.ORE));
                assertNotEquals(0, (int) resourceMap.get(TradeGood.FOOD));
                assertNotEquals(0, ((int) resourceMap.get(TradeGood.FOOD)));
                assertNotEquals(0, ((int) resourceMap.get(TradeGood.FURS)));

                break;

            case 4:
                assertEquals(0, (int) resourceMap.get(TradeGood.ROBOTS));
                assertEquals(0, (int) resourceMap.get(TradeGood.NARCOTICS));
                assertNotEquals(0, (int) resourceMap.get(TradeGood.MACHINES));
                assertNotEquals(0, (int) resourceMap.get(TradeGood.MEDICINE));
                assertNotEquals(0, (int) resourceMap.get(TradeGood.FIREARMS));
                assertNotEquals(0, (int) resourceMap.get(TradeGood.GAMES));
                assertNotEquals(0, (int) resourceMap.get(TradeGood.ORE));
                assertNotEquals(0, (int) resourceMap.get(TradeGood.FOOD));
                assertNotEquals(0, ((int) resourceMap.get(TradeGood.FOOD)));
                assertNotEquals(0, ((int) resourceMap.get(TradeGood.FURS)));

                break;

            case (5):
                assertEquals(0, (int) resourceMap.get(TradeGood.ROBOTS));
                assertNotEquals(0, (int) resourceMap.get(TradeGood.NARCOTICS));
                assertNotEquals(0, (int) resourceMap.get(TradeGood.MACHINES));
                assertNotEquals(0, (int) resourceMap.get(TradeGood.MEDICINE));
                assertNotEquals(0, (int) resourceMap.get(TradeGood.FIREARMS));
                assertNotEquals(0, (int) resourceMap.get(TradeGood.GAMES));
                assertNotEquals(0, (int) resourceMap.get(TradeGood.ORE));
                assertNotEquals(0, (int) resourceMap.get(TradeGood.FOOD));
                assertNotEquals(0, ((int) resourceMap.get(TradeGood.FOOD)));
                assertNotEquals(0, ((int) resourceMap.get(TradeGood.FURS)));

                break;

            default:
                assertNotEquals(0, (int) resourceMap.get(TradeGood.ROBOTS));
                assertNotEquals(0, (int) resourceMap.get(TradeGood.NARCOTICS));
                assertNotEquals(0, (int) resourceMap.get(TradeGood.MACHINES));
                assertNotEquals(0, (int) resourceMap.get(TradeGood.MEDICINE));
                assertNotEquals(0, (int) resourceMap.get(TradeGood.FIREARMS));
                assertNotEquals(0, (int) resourceMap.get(TradeGood.GAMES));
                assertNotEquals(0, (int) resourceMap.get(TradeGood.ORE));
                assertNotEquals(0, (int) resourceMap.get(TradeGood.FOOD));
                assertNotEquals(0, ((int) resourceMap.get(TradeGood.FOOD)));
                assertNotEquals(0, ((int) resourceMap.get(TradeGood.FURS)));

                break;


        }

    }
}
