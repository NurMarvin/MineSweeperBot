package de.nurmarvin.minesweeperbot.command.commands;

import de.nurmarvin.minesweeperbot.command.Command;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.Message;

public final class InviteCommand extends Command {
    public InviteCommand() {
        super("invite", "Sends the invite URL for the bot");
    }

    public final void execute(String[] args, Message msg) {
        EmbedBuilder embedBuilder = new EmbedBuilder();

        embedBuilder.setTitle("Invite");
        embedBuilder.setDescription("Invite URL: https://discordapp" +
                                    ".com/api/oauth2/authorize?client_id=540920926057922561&permissions=2048&scope" +
                                    "=bot");
        embedBuilder.setFooter("Minesweeper Bot by NurMarvin#1337", null);

        msg.getChannel().sendMessage(embedBuilder.build()).queue();
    }
}