package day12;

import utils.FileReader;

import java.util.ArrayList;
import java.util.List;

public class Day12 {

    final List<String> lines;
    final List<Instruction> instructions = new ArrayList<>();
    Direction currentDirection = Direction.EAST;
    int currentHorizontal = 0;
    int currentVertical = 0;
    Position waypoint = new Position(10L, 1L);
    Position boat = new Position(0L, 0L);

    public Day12(String filename) {
        lines = FileReader.readFileToString(filename);
        lines.forEach(line -> {
            instructions.add(new Instruction(Direction.toDirection(line.substring(0, 1)), Integer.parseInt(line.substring(1))));
        });
    }

    public int partA() {
        for (Instruction instruction : instructions) {
            executeInstruction(instruction);
        }
        return Math.abs(currentHorizontal) + Math.abs(currentVertical);
    }

    public long partB() {
        for (Instruction instruction : instructions) {
            executeWaypointInstruction(instruction);
        }
        return Math.abs(boat.horizontal()) + Math.abs(boat.vertical());
    }

    private void executeInstruction(Instruction instruction) {

        switch (instruction.direction()) {
            case FORWARD:
                executeInstruction(new Instruction(currentDirection, instruction.units()));
                break;
            case EAST:
                currentHorizontal += instruction.units();
                break;
            case WEST:
                currentHorizontal -= instruction.units();
                break;
            case NORTH:
                currentVertical += instruction.units();
                break;
            case SOUTH:
                currentVertical -= instruction.units();
                break;
            case RIGHT:
                currentDirection = Direction.rotateRight(currentDirection, instruction.units() / 90);
                break;
            case LEFT:
                currentDirection = Direction.rotateLeft(currentDirection, instruction.units() / 90);
                break;
        }
    }

    private void executeWaypointInstruction(Instruction instruction) {
        switch (instruction.direction()) {
            case FORWARD:
                long hor = boat.horizontal();
                long ver = boat.vertical();
                hor += (waypoint.horizontal() * instruction.units());
                ver += (waypoint.vertical() * instruction.units());
                boat = new Position(hor, ver);
                break;
            case EAST:
                waypoint = new Position(waypoint.horizontal() + instruction.units(), waypoint.vertical());
                break;
            case WEST:
                waypoint = new Position(waypoint.horizontal() - instruction.units(), waypoint.vertical());
                break;
            case NORTH:
                waypoint = new Position(waypoint.horizontal(), waypoint.vertical() + instruction.units());
                break;
            case SOUTH:
                waypoint = new Position(waypoint.horizontal(), waypoint.vertical() - instruction.units());
                break;
            case RIGHT:
                rotateWaypointRight(instruction.units() / 90);
                break;
            case LEFT:
                rotateWaypointLeft(instruction.units() / 90);
                break;
        }
    }

    private void rotateWaypointRight(int times) {
        long h = waypoint.horizontal();
        long v = waypoint.vertical();
        for (int i = 0; i < times; i++) {
            long nv, nh;
            nv = h * -1;
            nh = v;
            h = nh;
            v = nv;
        }
        waypoint = new Position(h, v);
    }

    private void rotateWaypointLeft(int times) {
        long h = waypoint.horizontal();
        long v = waypoint.vertical();
        for (int i = 0; i < times; i++) {
            long nv, nh;
            nv = h;
            nh = v * -1;
            h = nh;
            v = nv;
        }
        waypoint = new Position(h, v);
    }
}

record Instruction(Direction direction, int units) {
}

enum Direction {
    NORTH, SOUTH, EAST, WEST, FORWARD, RIGHT, LEFT;

    public static Direction toDirection(String direction) {
        return switch (direction) {
            case "N" -> NORTH;
            case "E" -> EAST;
            case "S" -> SOUTH;
            case "W" -> WEST;
            case "F" -> FORWARD;
            case "R" -> RIGHT;
            case "L" -> LEFT;
            default -> null;
        };
    }

    public static Direction rotateRight(Direction direction, int times) {
        Direction dir = direction;
        for (int i = 0; i < times; i++) {
            switch (dir) {
                case NORTH:
                    dir = EAST;
                    break;
                case EAST:
                    dir = SOUTH;
                    break;
                case SOUTH:
                    dir = WEST;
                    break;
                case WEST:
                    dir = NORTH;
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + dir);
            }
        }
        return dir;
    }

    public static Direction rotateLeft(Direction direction, int times) {
        Direction dir = direction;
        for (int i = 0; i < times; i++) {
            switch (dir) {
                case NORTH:
                    dir = WEST;
                    break;
                case WEST:
                    dir = SOUTH;
                    break;
                case SOUTH:
                    dir = EAST;
                    break;
                case EAST:
                    dir = NORTH;
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + dir);
            }
        }
        return dir;
    }
}

record Position(long horizontal, long vertical){}