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

package de.nurmarvin.minesweeperbot;

import de.nurmarvin.minesweeperbot.command.CommandRegistry;
import de.nurmarvin.minesweeperbot.command.commands.HelpCommand;
import de.nurmarvin.minesweeperbot.command.commands.InviteCommand;
import de.nurmarvin.minesweeperbot.command.commands.NewGameCommand;
import de.nurmarvin.minesweeperbot.command.commands.StatsCommand;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.entities.Game;

import javax.security.auth.login.LoginException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 *  @since 1.0
 *  @author NurMarvin
 */
public final class MineSweeperBot {
    private static MineSweeperBot instance;
    private final CommandRegistry commandRegistry;

    private MineSweeperBot(String token) throws LoginException {
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);

        this.commandRegistry = new CommandRegistry();
        commandRegistry.registerCommand(new NewGameCommand());
        commandRegistry.registerCommand(new StatsCommand());
        commandRegistry.registerCommand(new HelpCommand());
        commandRegistry.registerCommand(new InviteCommand());

        JDA jda = new JDABuilder(token).setGame(Game.playing("m!help | Serving 0 Guilds"))
                                       .addEventListener(commandRegistry).build();

        executorService.scheduleAtFixedRate(() -> jda.getPresence().setGame(Game.playing(
                String.format("m!help | Serving %s Guilds", jda.getGuilds().size()))), 1, 10, TimeUnit.SECONDS);
    }

    public static void main(String[] args) throws LoginException {
        if (args.length == 0) {
            System.out.println("Token not provided");
            System.exit(1);
            return;
        }

        instance = new MineSweeperBot(args[0]);
    }

    /**
     * The {@link MineSweeperBot} instance
     * @return The {@link MineSweeperBot} instance
     */
    public static MineSweeperBot getInstance() {
        return instance;
    }

    /**
     * The {@link CommandRegistry} instance
     * @return The {@link CommandRegistry} instance
     */
    public final CommandRegistry getCommandRegistry() {
        return commandRegistry;
    }
}
