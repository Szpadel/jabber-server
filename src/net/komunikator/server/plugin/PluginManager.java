package net.komunikator.server.plugin;

import net.komunikator.server.models.Message;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: ziomek
 * Date: 17.06.13
 * Time: 13:41
 * To change this template use File | Settings | File Templates.
 */
public class PluginManager {
    List<Plugin> plugins;

    public void registerPlugin(Plugin plugin) {
        plugins.add(plugin);
    }

    public void removePlugin(Plugin plugin) {
        plugins.remove(plugin);
    }

    public List<Plugin> getPluginsList() {
        return plugins;
    }

    public void emitReceiveMessageEvent(Message message) {

    }
}
