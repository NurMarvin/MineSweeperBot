package de.nurmarvin.minesweeperbot.command.commands;

import de.nurmarvin.minesweeperbot.MineSweeperBot;
import de.nurmarvin.minesweeperbot.command.Command;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.Message;

public final class HelpCommand extends Command {
    public HelpCommand() {
        super("help", "Shows some bot stats, like amount of guilds and users");
    }

    public final void execute(String[] args, Message msg) {
        EmbedBuilder embedBuilder = new EmbedBuilder();

        embedBuilder.setTitle("Help");
        for (Command command : MineSweeperBot.getInstance().getCommandRegistry().getCommands())
            if (!command.equals(this))
                embedBuilder.addField(command.getName(), command.getDescription(), false);
        embedBuilder.setFooter("Minesweeper Bot by NurMarvin#1337", null);

        msg.getChannel().sendMessage(embedBuilder.build()).queue();
    }
}