package me.almana.almanapvp.utils;

import org.bukkit.inventory.ItemStack;
import org.bukkit.util.io.BukkitObjectInputStream;
import org.bukkit.util.io.BukkitObjectOutputStream;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.List;

public class Base64Utils {

    public static String itemToBase64(ItemStack item) throws IOException {

        ByteArrayOutputStream io = new ByteArrayOutputStream();
        BukkitObjectOutputStream os = new BukkitObjectOutputStream(io);

        os.writeObject(item);
        os.flush();

        byte[] serialised = io.toByteArray();

        return Base64.getEncoder().encodeToString(serialised);
    }

    public static ItemStack itemFromBase64(String base64) throws IOException, ClassNotFoundException {

        byte[] serialised = Base64.getDecoder().decode(base64);

        ByteArrayInputStream in = new ByteArrayInputStream(serialised);
        BukkitObjectInputStream os = new BukkitObjectInputStream(in);

        return (ItemStack) os.readObject();
    }

    public static List<ItemStack> itemListFromBase64(String base64) throws IOException, ClassNotFoundException {

        byte[] serialised = Base64.getDecoder().decode(base64);

        ByteArrayInputStream in = new ByteArrayInputStream(serialised);
        BukkitObjectInputStream os = new BukkitObjectInputStream(in);

        return (List<ItemStack>) os.readObject();
    }

    public static String itemListToBase64(List<ItemStack> items) throws IOException {

        ByteArrayOutputStream io = new ByteArrayOutputStream();
        BukkitObjectOutputStream os = new BukkitObjectOutputStream(io);

        os.writeObject(items);
        os.flush();

        byte[] serialised = io.toByteArray();

        return Base64.getEncoder().encodeToString(serialised);
    }
//    private byte[] serializeNbtToBytes(CompoundTag compound) {
//        compound.putInt("DataVersion", getDataVersion());
//        java.io.ByteArrayOutputStream outputStream = new java.io.ByteArrayOutputStream();
//        try {
//            net.minecraft.nbt.NbtIo.writeCompressed(
//                    compound,
//                    outputStream
//            );
//        } catch (IOException ex) {
//            throw new RuntimeException(ex);
//        }
//        return outputStream.toByteArray();
//    }
}
