/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2019 Marvin Witt
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package de.nurmarvin.minesweeperbot.command.commands;

import de.nurmarvin.minesweeperbot.command.Command;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.Message;

/**
 *  @since 1.0
 *  @author NurMarvin
 */
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