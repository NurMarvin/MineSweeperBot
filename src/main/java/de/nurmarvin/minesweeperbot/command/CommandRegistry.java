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

package de.nurmarvin.minesweeperbot.command;

import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

import java.util.ArrayList;

/**
 *  @since 1.0
 *  @author NurMarvin
 */
public final class CommandRegistry extends ListenerAdapter {
    private final ArrayList<Command> commands;

    public CommandRegistry() {
        this.commands = new ArrayList<>();
    }

    /**
     * Registers the given {@link Command}
     * @param command The command to register
     */
    public final void registerCommand(Command command) {
        this.commands.add(command);
    }

    /**
     * Gets the command with the given name.
     * @param name The name of the command that should be searched for. May be an alias of the command.
     * @return The {@link Command} if found otherwise null.
     */
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

    /**
     * The {@link Command}s ArrayList
     * @return The {@link Command}s ArrayList
     */
    public final ArrayList<Command> getCommands() {
        return commands;
    }
}
