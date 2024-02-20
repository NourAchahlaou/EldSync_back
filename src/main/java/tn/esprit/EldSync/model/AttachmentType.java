package tn.esprit.EldSync.model;

import java.util.stream.Stream;

public enum AttachmentType {
    IMAGE,
    VIDEO,
    AUDIO,
    DOCUMENT,
    OTHER;

    public static AttachmentType fromString(String value) {
        return Stream.of(AttachmentType.values())
                .filter(type -> type.name().equalsIgnoreCase(value))
                .findFirst()
                .orElse(OTHER);
    }
}
