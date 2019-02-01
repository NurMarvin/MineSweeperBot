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

public final class MineSweeperBot {
    private static MineSweeperBot instance;
    private CommandRegistry commandRegistry;

    private MineSweeperBot(String token) throws LoginException {
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);

        this.commandRegistry = new CommandRegistry();
        commandRegistry.registerCommand(new NewGameCommand());
        commandRegistry.registerCommand(new StatsCommand());
        commandRegistry.registerCommand(new HelpCommand());
        commandRegistry.registerCommand(new InviteCommand());

        JDA jda = new JDABuilder(token).setGame(Game.playing("Minesweeper | Serving 0 Guilds"))
                                       .addEventListener(commandRegistry).build();

        executorService.scheduleAtFixedRate(() -> jda.getPresence().setGame(Game.playing(
                String.format("Minesweeper | Serving %s Guilds", jda.getGuilds().size()))), 1, 10, TimeUnit.SECONDS);
    }

    public static void main(String[] args) throws LoginException {
        if (args.length == 0) {
            System.out.println("Token not provided");
            System.exit(1);
            return;
        }

        instance = new MineSweeperBot(args[0]);
    }

    public static MineSweeperBot getInstance() {
        return instance;
    }

    public final CommandRegistry getCommandRegistry() {
        return commandRegistry;
    }
}
