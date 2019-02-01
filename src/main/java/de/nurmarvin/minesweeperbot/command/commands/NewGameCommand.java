package de.nurmarvin.minesweeperbot.command.commands;

import de.nurmarvin.minesweeperbot.command.Command;
import de.nurmarvin.minesweeperbot.game.Board;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.Message;

public final class NewGameCommand extends Command {
    public NewGameCommand() {
        super("newgame", "Starts a new game (may be run with optional parameter \"debug\" to create a copy paste " +
                         "field to send around)", "ng", "next");
    }

    public final void execute(String[] args, Message msg) {
        Board board = new Board();
        StringBuilder stringBuilder = new StringBuilder(board.toSpoiler());
        if (args.length > 0 && args[0].equalsIgnoreCase("debug")) stringBuilder.insert(0, "```").append("```");

        EmbedBuilder embedBuilder = new EmbedBuilder();

        embedBuilder.setTitle("Minesweeper");
        embedBuilder.setDescription(stringBuilder);
        embedBuilder.setFooter("Minesweeper Bot by NurMarvin#1337", null);

        msg.getChannel().sendMessage(embedBuilder.build()).queue();
    }
}