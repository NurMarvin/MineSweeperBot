package de.nurmarvin.minesweeperbot.command;

import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

import java.util.ArrayList;

public final class CommandRegistry extends ListenerAdapter {
    private final ArrayList<Command> commands;

    public CommandRegistry() {
        this.commands = new ArrayList<>();
    }

    public final void registerCommand(Command command) {
        this.commands.add(command);
    }

    private Command getCommand(String name) {
        for (Command command : this.commands)
            if (command.getName().equalsIgnoreCase(name)) return command;
            else for (String alias : command.getAliases()) if (name.equalsIgnoreCase(alias)) return command;
        return null;
    }

    @Override
    public final void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        String prefix = "m!";

        if (!event.getMessage().getContentRaw().startsWith(prefix))
            return;

        String commandName = event.getMessage().getContentRaw().substring(prefix.length());
        String[] args = null;

        if (commandName.contains(" ")) {
            commandName = commandName.split(" ")[0];
            args = event.getMessage().getContentRaw().substring(event.getMessage().getContentRaw().indexOf(' ') + 1)
                        .split(" ");
        }

        if (args == null) {
            args = new String[0];
        }

        Command command = this.getCommand(commandName);

        if (command == null)
            return;

        command.execute(args, event.getMessage());
    }

    public final ArrayList<Command> getCommands() {
        return commands;
    }
}
