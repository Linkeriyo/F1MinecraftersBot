package com.linkeriyo.f1minecraftersbot;

import com.linkeriyo.f1minecraftersbot.commands.CreateProfileCommand;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

public class MessageListener extends ListenerAdapter {

    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        Message msg = event.getMessage();
        String contentRaw = msg.getContentRaw();
        if (contentRaw.startsWith(F1MinecraftersBot.prefix)) {
            String noPrefix = contentRaw.substring(F1MinecraftersBot.prefix.length());
            String[] args = noPrefix.split(" ");

            switch (args[0]) {
                case "signup":
                    new CreateProfileCommand(msg).run();
            }
        }


    }
}
