package de.nurmarvin.minesweeperbot.game;

import org.jetbrains.annotations.Contract;

public final class Field {
    private final boolean bomb;
    private final int x;
    private final int y;

    Field(boolean bomb, int x, int y) {
        this.bomb = bomb;
        this.x = x;
        this.y = y;
    }

    @Contract(pure = true)
    boolean isBomb() {
        return bomb;
    }

    @Contract(pure = true)
    int getX() {
        return x;
    }

    @Contract(pure = true)
    int getY() {
        return y;
    }

    @Contract(value = "null -> false", pure = true)
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Field) {
            Field other = (Field) obj;

            return other.getX() == this.getX() && other.getY() == this.getY() == other.isBomb() == this.isBomb();
        }
        return false;
    }
}
