package com.linkeriyo.f1minecraftersbot.commands;

import com.linkeriyo.f1minecraftersbot.F1MinecraftersBot;
import net.dv8tion.jda.api.entities.Message;

import java.sql.*;

public class CreateProfileCommand extends Command {

    Message msg;

    public CreateProfileCommand(Message msg) {
        this.msg = msg;
    }

    @Override
    public void run() {
        try {
            Connection connection = DriverManager.getConnection(F1MinecraftersBot.url);
            String query = "select * from user_profile where discorduid = ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, msg.getAuthor().getId());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                msg.reply("tu existes").queue();
            } else {
                msg.reply("tu no existes").queue();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
}
