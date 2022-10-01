package web.server.domain;

import java.nio.file.Path;

public interface RequestDeserializer {
    Path deserialize();
}
