package com.example.spacetrader;

import com.example.spacetrader.model.TradeGood;
import com.example.spacetrader.model.system.Position;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class EricJUnit {

    private Position pos;

     @Before
     public void setUp() {
         pos = new Position(1, 1);
     }

     @Test
    public void testEquals() {
         Position temp = null; //null
         Position temp2 = new Position(1, 1); //equal position
         Position temp3 = new Position(1, 3);
         Position temp4 = new Position(2, 4);

         assertEquals(false, pos.equals(temp));
         assertEquals(true, pos.equals(temp2));
         assertEquals(true, pos.equals(pos));
         assertEquals(false, pos.equals(TradeGood.WATER));
         assertEquals(false, pos.equals(temp3));
         assertEquals(false, pos.equals(temp4));

     }

     @Test
    public void testEuclideanDistance() {

         Position temp5 = new Position(1, 3);
         int mDistance = pos.getManhattanDistanceTo(temp5);
         assertEquals(2, mDistance);

         int euclideanDistance = pos.getEuclideanDistanceTo(temp5);
         assertEquals(2, euclideanDistance);

         String str = "(" + pos.getX() + ", " + pos.getY() + ")";
         assertEquals(str, pos.toString());
     }
}
