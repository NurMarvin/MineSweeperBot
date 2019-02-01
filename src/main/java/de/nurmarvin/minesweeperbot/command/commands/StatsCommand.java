package de.nurmarvin.minesweeperbot.command.commands;

import de.nurmarvin.minesweeperbot.command.Command;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.Message;

public final class StatsCommand extends Command {
    public StatsCommand() {
        super("stats", "Shows some stats");
    }

    public final void execute(String[] args, Message msg) {
        EmbedBuilder embedBuilder = new EmbedBuilder();

        embedBuilder.setTitle("Stats");
        embedBuilder.addField("Guilds", String.valueOf(msg.getJDA().getGuilds().size()), true);
        embedBuilder.addField("Users", String.valueOf(msg.getJDA().getUsers().size()), true);
        embedBuilder.setFooter("Minesweeper Bot by NurMarvin#1337", null);

        msg.getChannel().sendMessage(embedBuilder.build()).queue();
    }
}