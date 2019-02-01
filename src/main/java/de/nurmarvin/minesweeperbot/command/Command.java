package de.nurmarvin.minesweeperbot.command;

import net.dv8tion.jda.core.entities.Message;

public abstract class Command {

    private final String name;
    private final String[] aliases;
    private final String description;

    public Command(String name, String description, String... aliases) {
        this.name = name;
        this.aliases = aliases;
        this.description = description;
    }

    public abstract void execute(String[] args, Message msg);

    public String getName() {
        return name;
    }

    String[] getAliases() {
        return aliases;
    }

    public String getDescription() {
        return description;
    }
}