package com.linkeriyo.f1minecraftersbot;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;

import javax.security.auth.login.LoginException;

public class DiscordConnection {
    JDA jda;

    public DiscordConnection(String token) throws LoginException {
        JDABuilder jdaBuilder = JDABuilder.createDefault(token);
        jdaBuilder.addEventListeners(new MessageListener());
        jda = jdaBuilder.build();
    }
}
