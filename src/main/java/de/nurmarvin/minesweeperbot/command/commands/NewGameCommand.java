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
import de.nurmarvin.minesweeperbot.game.Board;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.Message;

/**
 *  @since 1.0
 *  @author NurMarvin
 */
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