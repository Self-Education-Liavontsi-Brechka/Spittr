package net.liavontsibrechka.spittr.data;

import net.liavontsibrechka.spittr.Spitter;

public interface SpitterRepository {
    Spitter save(Spitter spitter);

    Spitter findByUsername(String username);
}
