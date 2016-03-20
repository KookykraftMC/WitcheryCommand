package com.kookykraftmc.witcherycmd;

import com.emoniph.witchery.common.ExtendedPlayer;
import com.mojang.authlib.GameProfile;
import cpw.mods.fml.common.FMLCommonHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentText;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by TimeTheCat on 3/20/2016.
 */
public class WCmd implements ICommand {

    private final List aliases;

    List<String> vw;

    List<Integer> lvls;

    public WCmd() {
        aliases = new ArrayList();
        aliases.add("wcmd");

    }

    @Override
    public String getCommandName() {
        return "wcmd";
    }

    @Override
    public String getCommandUsage(ICommandSender p_71518_1_) {
        return "wcmd <vampire/werewolf> <level> [player]";
    }

    @Override
    public List getCommandAliases() {
        return this.aliases;
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) {
        if (args.length < 2) {
            sender.addChatMessage(new ChatComponentText(getCommandUsage(sender)));
        } else if (args.length == 2) {
            EntityPlayer p = (EntityPlayer) sender;
            ExtendedPlayer ep = ExtendedPlayer.get(p);
            if (args[0].toLowerCase().matches("(?iu).*vampire.*")) {
                ep.setVampireLevel(Integer.parseInt(args[1]));
                sender.addChatMessage(new ChatComponentText("Your vampire level has been set to " + args[1]));
            } else if(args[0].toLowerCase().matches("(?iu).*werewolf.*")) {
                ep.setWerewolfLevel(Integer.parseInt(args[1]));
                sender.addChatMessage(new ChatComponentText("Your werewolf level has been set to " + args[1]));
            }
        } else if (args.length == 3) {
                EntityPlayer p = sender.getEntityWorld().getPlayerEntityByName(args[2]);
                ExtendedPlayer ep = ExtendedPlayer.get(p);
                if (args[0].toLowerCase().matches("(?iu).*vampire.*")) {
                    ep.setVampireLevel(Integer.parseInt(args[1]));
                    sender.addChatMessage(new ChatComponentText(args[3] + "'s vampire level has been set to " + args[1]));
                    p.addChatMessage(new ChatComponentText("Your vampire level has been set to " + args[1]));
                } else if (args[0].toLowerCase().matches("(?iu).*werewolf.*")) {
                    ep.setWerewolfLevel(Integer.parseInt(args[1]));
                    sender.addChatMessage(new ChatComponentText(args[3] + "'s werewolf level has been set to " + args[1]));
                    p.addChatMessage(new ChatComponentText("Your vampire level has been set to " + args[1]));
                }
        }
    }

    @Override
    public boolean canCommandSenderUseCommand(ICommandSender sender) {
        if (MinecraftServer.getServer().getConfigurationManager().func_152596_g((GameProfile) sender)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public List addTabCompletionOptions(ICommandSender sender, String[] args) {
        if (args.length == 1) {
            vw.add("vampire");
            vw.add("werewolf");
            return vw;
        } else if (args.length == 2) {
            lvls.add(1);
            lvls.add(2);
            lvls.add(3);
            lvls.add(4);
            lvls.add(5);
            lvls.add(6);
            lvls.add(7);
            lvls.add(8);
            lvls.add(9);
            lvls.add(10);
            lvls.add(0);
            return lvls;
        } else if (args.length == 3) {
            return Arrays.asList(MinecraftServer.getServer().getAllUsernames());
        } else {
            return null;
        }
    }

    @Override
    public boolean isUsernameIndex(String[] p_82358_1_, int p_82358_2_) {
        return false;
    }

    @Override
    public int compareTo(Object o) {
        return 0;
    }
}
