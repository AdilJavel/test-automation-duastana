package com.solotesting.duastana.util;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        "file:${user.dir}/src/test/resources/config.properties"
})
public interface PropertiesReader extends Config{
    String browser();
    String urlDuAstana();
}
