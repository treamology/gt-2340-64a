package com.example.spacetrader;

import com.example.spacetrader.model.Player;
import com.example.spacetrader.model.system.Position;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertArrayEquals;

public class EricJUnit {

    private Position pos;

     @Before
     public void setUp() {
         pos = new Position(1, 1);
     }

     @Test
    public void testPosition() {
         Position temp = null; //null
         Position temp2 = new Position(1, 1); //equal position
         Player player = new Player("Name", 2, 2, 2, 2); //not an instance of Position
         Position temp3 = new Position(1, 3);
         Position temp4 = new Position(2, 4);

         assertEquals(false, pos.equals(temp));
         assertEquals(true, pos.equals(temp2));
         assertEquals(true, pos.equals(pos));
         assertEquals(false, pos.equals(player));
         assertEquals(false, pos.equals(temp3));
         assertEquals(false, pos.equals(temp4));
     }
}
