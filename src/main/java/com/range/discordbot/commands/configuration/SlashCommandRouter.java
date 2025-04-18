package com.range.discordbot.commands.configuration;


import com.range.discordbot.commands.admin.*;
import com.range.discordbot.commands.user.InfoCommand;
import com.range.discordbot.commands.user.SayCommand;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.function.Consumer;

@Component
public class SlashCommandRouter {

    private final Map<String, Consumer<SlashCommandInteractionEvent>> commandMap;
    public SlashCommandRouter(
            BanCommand banCommand
            , SayCommand sayCommand
            , LeaveCommand leaveCommand
            , PruneCommand pruneCommand
            , UnBanCommand unbanCommand
            , InfoCommand infoCommand
            , ListBannedUsers listBannedUsers) {
        this.commandMap= Map.of(
                "ban",banCommand::execute,
                "say",sayCommand::execute,
                "leave",leaveCommand::execute,
                "prune",pruneCommand::execute,
                "unban",unbanCommand::execute,
                "info",infoCommand::execute,
                "ban-list",listBannedUsers::execute
        );

    }
    public void route(SlashCommandInteractionEvent event)
    {
        Consumer<SlashCommandInteractionEvent> handler = commandMap.get(event.getName());
        if (handler != null) {
            handler.accept(event);
        }else
        {
            event.reply("Unknown command `"+event.getName()+"`").queue();
        }
    }}
