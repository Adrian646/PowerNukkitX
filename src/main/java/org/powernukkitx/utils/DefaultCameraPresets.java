package org.powernukkitx.utils;

import it.unimi.dsi.fastutil.objects.ObjectLinkedOpenHashSet;
import org.cloudburstmc.math.vector.Vector2f;
import org.cloudburstmc.math.vector.Vector3f;
import org.cloudburstmc.protocol.bedrock.data.camera.CameraPreset;
import org.cloudburstmc.protocol.common.DefinitionRegistry;
import org.cloudburstmc.protocol.common.NamedDefinition;
import org.cloudburstmc.protocol.common.SimpleDefinitionRegistry;

import java.util.List;
import java.util.Set;

/**
 * @author Kaooot
 */
public class DefaultCameraPresets {

    public static final CameraPreset FIRST_PERSON = CameraPreset.builder()
            .name("minecraft:first_person")
            .build();
    public static final CameraPreset FIXED_BOOM = CameraPreset.builder()
            .name("minecraft:fixed_boom")
            .viewOffset(Vector2f.ZERO)
            .entityOffset(Vector3f.ZERO)
            .build();
    public static final CameraPreset FOLLOW_ORBIT = CameraPreset.builder()
            .name("minecraft:follow_orbit")
            .viewOffset(Vector2f.ZERO)
            .entityOffset(Vector3f.ZERO)
            .radius(10.0f)
            .build();
    public static final CameraPreset FREE = CameraPreset.builder()
            .name("minecraft:free")
            .pos(Vector3f.ZERO)
            .pitch(0f)
            .yaw(0f)
            .build();
    public static final CameraPreset THIRD_PERSON = CameraPreset.builder()
            .name("minecraft:third_person")
            .build();
    public static final CameraPreset THIRD_PERSON_FRONT = CameraPreset.builder()
            .name("minecraft:third_person_front")
            .build();


    private static final Set<CameraPreset> CAMERA_PRESETS = new ObjectLinkedOpenHashSet<>();
    private static final DefinitionRegistry<NamedDefinition> CAMERA_PRESET_DEFINITIONS;

    static {
        CAMERA_PRESETS.addAll(
                List.of(
                        FIRST_PERSON, FIXED_BOOM, FOLLOW_ORBIT, FREE, THIRD_PERSON, THIRD_PERSON_FRONT
                )
        );

        final SimpleDefinitionRegistry.Builder<NamedDefinition> definitions = new SimpleDefinitionRegistry.Builder<>();
        int runtimeId = 0;
        for (CameraPreset preset : CAMERA_PRESETS) {
            definitions.add(new CameraPresetDefinition(preset.getName(), runtimeId++));
        }
        CAMERA_PRESET_DEFINITIONS = definitions.build();
    }

    public static Set<CameraPreset> getAll() {
        return CAMERA_PRESETS;
    }

    /**
     * Returns the definitions used to reference the default presets in camera instructions.
     *
     * @return the camera preset definition registry
     */
    public static DefinitionRegistry<NamedDefinition> getDefinitionRegistry() {
        return CAMERA_PRESET_DEFINITIONS;
    }

    private record CameraPresetDefinition(String identifier, int runtimeId) implements NamedDefinition {

        @Override
        public String getIdentifier() {
            return this.identifier;
        }

        @Override
        public int getRuntimeId() {
            return this.runtimeId;
        }
    }
}
