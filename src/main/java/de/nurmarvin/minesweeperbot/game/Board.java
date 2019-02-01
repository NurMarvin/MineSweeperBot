package de.nurmarvin.minesweeperbot.game;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public final class Board {
    private final ArrayList<Field> fields;
    private final int maxBombs;
    private Field revealedField;

    public Board() {
        this(32);
    }

    private Board(int maxBombs) {
        this.maxBombs = maxBombs;
        this.fields = new ArrayList<>();
        this.populate();
    }

    private void populate() {
        int currentBombs = 0;
        for (int y = 0; y < 8; y++)
            for (int x = 0; x < 8; x++)
                if (currentBombs < maxBombs) {
                    if (ThreadLocalRandom.current().nextBoolean()) {
                        this.addField(new Field(true, x, y));
                        currentBombs++;
                    } else this.addField(new Field(false, x, y));
                } else this.addField(new Field(false, x, y));
    }

    private void addField(Field field) {
        if (this.revealedField == null && ThreadLocalRandom.current().nextBoolean() && !field.isBomb())
            this.revealedField = field;
        this.fields.add(field);
    }

    public final String toSpoiler() {
        boolean revealedFieldSet = false;
        StringBuilder stringBuilder = new StringBuilder();
        for (Field field : this.fields) {
            if (field.getX() == 0 && field.getY() != 0) stringBuilder.append("\n");

            if (field.isBomb()) {
                stringBuilder.append("||\uD83D\uDCA3||");
                continue;
            }

            if ((!revealedFieldSet && this.revealedField == null) || revealedField.equals(field)) {
                stringBuilder.append(this.getBombsNear(field)).append("⃣");
                revealedFieldSet = true;
            } else stringBuilder.append("||").append(this.getBombsNear(field)).append("⃣").append("||");
        }
        return stringBuilder.toString();
    }

    private int getBombsNear(@NotNull Field field) {
        return this.getBombsNear(field.getX(), field.getY());
    }

    private int getBombsNear(int x, int y) {
        int bombs = 0;
        for (int xOffset = -1; xOffset < 2; xOffset++)
            for (int yOffset = -1; yOffset < 2; yOffset++) {
                Field field = this.getFieldAt(x + xOffset, y + yOffset);
                if (field == null) continue;

                if (field.isBomb()) bombs++;
            }
        return bombs;
    }

    @Nullable
    private Field getFieldAt(int x, int y) {
        for (Field field : this.fields) {
            if (field.getX() == x && field.getY() == y) return field;
        }
        return null;
    }
}
