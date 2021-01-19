package me.mayhem.game.level.spawning.impl;

import me.mayhem.game.entity.enemies.infected.Infected;
import me.mayhem.game.level.Level;
import me.mayhem.game.level.spawning.EntitySpawner;
import me.mayhem.util.Vector;

public class EasyEntitySpawner implements EntitySpawner {

    @Override
    public void spawnEntities(Level level) {
        //TODO:
        // create something that works based on the constraints of the editor
        level.getEntities().add(new Infected(new Vector(500,500)));

    }
}
