package net.liavontsibrechka.spittr.data;

import net.liavontsibrechka.spittr.Spitter;

import java.util.List;

public interface SpitterRepository {
    Spitter save(Spitter spitter);

    Spitter findByUsername(String username);

    long count();

    Spitter findOne(long id);

    List<Spitter> findAll();
}
