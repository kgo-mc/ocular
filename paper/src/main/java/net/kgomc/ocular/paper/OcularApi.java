package net.kgomc.ocular.paper;

import net.kgomc.ocular.api.IOcularApi;

public class OcularApi implements IOcularApi {

    private final OcularPlugin plugin;

    public OcularApi(OcularPlugin plugin) {
        this.plugin = plugin;
    }
}
