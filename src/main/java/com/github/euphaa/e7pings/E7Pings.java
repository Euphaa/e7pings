package com.github.euphaa.e7pings;

import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.util.BlockPos;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import org.lwjgl.input.Keyboard;

@Mod(modid = "e7pings", useMetadata = true)
public class E7Pings
{

    public static KeyBinding pingButton = new KeyBinding("Ping", Keyboard.KEY_X, "e7pings");

    @Mod.EventHandler
    public void init(FMLInitializationEvent event)
    {
        ClientRegistry.registerKeyBinding(pingButton);
        MinecraftForge.EVENT_BUS.register(new E7Pings());
    }

    @SubscribeEvent
    public void onPingButtonPressed(InputEvent event)
    {
        if (pingButton.isPressed())
        {
            sendRaytraceCoords();
        }
    }

    private void sendRaytraceCoords()
    {
        Minecraft minecraft = Minecraft.getMinecraft();
        int distance = Math.min(160, minecraft.gameSettings.renderDistanceChunks*15);

        BlockPos coords = minecraft.thePlayer.rayTrace(distance, 0).getBlockPos();
        minecraft.thePlayer.sendChatMessage(String.format("/pc x: %d y: %d z: %d", coords.getX(), coords.getY(), coords.getZ()));
    }
}
