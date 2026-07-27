package org.powernukkitx.utils;

import org.cloudburstmc.protocol.common.NamedDefinition;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DefaultCameraPresetsTest {

    @Test
    void definitionsMatchPresetPacketOrder() {
        var definitions = DefaultCameraPresets.getDefinitionRegistry();
        int runtimeId = 0;

        for (var preset : DefaultCameraPresets.getAll()) {
            NamedDefinition definition = definitions.getDefinition(runtimeId);

            Assertions.assertNotNull(definition);
            Assertions.assertEquals(preset.getName(), definition.getIdentifier());
            Assertions.assertEquals(runtimeId, definition.getRuntimeId());
            Assertions.assertEquals(runtimeId, definitions.getRuntimeIdByName(preset.getName()));
            Assertions.assertTrue(definitions.isRegistered(definition));
            runtimeId++;
        }

        Assertions.assertEquals(DefaultCameraPresets.getAll().size(), runtimeId);
    }
}
