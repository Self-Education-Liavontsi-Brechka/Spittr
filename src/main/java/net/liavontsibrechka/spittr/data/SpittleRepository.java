package net.liavontsibrechka.spittr.data;


import net.liavontsibrechka.spittr.Spittle;

import java.util.List;

public interface SpittleRepository {
    List<Spittle> findRecentSpittles();

    List<Spittle> findSpittles(long max, int count);

    Spittle findOne(long id);

    void save(Spittle spittle);
}
