package day20;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day20Test {

    @Test
    void testExample() {
        Day20 day20 = new Day20("day20/example.txt");
        List<Tile> cornerTileIds = day20.getCornerTiles();
        assertEquals(20899048083289L, day20.productOfTiles(cornerTileIds));
        Tile tile = day20.assembleImage(cornerTileIds.getFirst().tileNumber);
        assertEquals(273, tile.countWaves());

    }

    @Test
    void testActual() {
        Day20 day20 = new Day20("day20/actual.txt");
        List<Tile> cornerTileIds = day20.getCornerTiles();
        System.out.println(day20.productOfTiles(cornerTileIds));
        Tile tile = day20.assembleImage(cornerTileIds.getFirst().tileNumber);
        System.out.println(tile.countWaves());

    }

}
