package cf.avicia.avomod2;

import net.minecraft.client.MinecraftClient;
import net.minecraft.text.ClickEvent;
import net.minecraft.text.HoverEvent;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;

import java.util.Locale;
import java.util.Objects;
import java.util.UUID;

public class Utils {
    public static String firstLetterCapital(String input) {
        return input.substring(0, 1).toUpperCase() + input.substring(1).toLowerCase();
    }

    public static String getReadableTime(int minutes) {
        return (minutes >= 1440.0 ? (int) Math.floor((minutes / 1440.0)) + "d " : "") + (int) (Math.floor((minutes % 1440) / 60.0)) + "h " + minutes % 60 + "m";
    }

    public static String getFormattedWorld(String world) {
        String formattedWorld = world;
        if (world.matches("^\\d+")) {
            formattedWorld = "WC" + world;
        }
        return formattedWorld.toUpperCase(Locale.ROOT);
    }

    public static String getUnformattedString(String string) {
        return string.replaceAll("§.", "");
    }

    public static String getCurrentWorld() {
        try {
            String name = Objects.requireNonNull(Objects.requireNonNull(Objects.requireNonNull(MinecraftClient.getInstance().getNetworkHandler()).getPlayerListEntry(UUID.fromString("16ff7452-714f-3752-b3cd-c3cb2068f6af"))).getDisplayName()).getString();
            return name.substring(name.indexOf("[") + 1, name.indexOf("]"));
        } catch (NullPointerException | IndexOutOfBoundsException ignored) {
            return null;
        }
    }

    public static LiteralText makeMessageThatRunsCommand(String message, String command) {
        LiteralText messageRes = new LiteralText(message);
        messageRes.fillStyle(messageRes.getStyle().withClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, command)));
        messageRes.fillStyle(messageRes.getStyle().withHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, Text.of("§7Click to run §f" + command))));
        return messageRes;
    }
}
