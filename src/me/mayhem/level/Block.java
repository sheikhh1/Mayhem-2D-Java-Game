package me.mayhem.level;

public class Block {

    public int x,y;
    public int width, height;

    /**
     * Block Constructor
     * @param x - X position of block
     * @param y - Y position of block
     * @param width - Width of block
     * @param height - Height of block
     */
    public Block (int x, int y, int width, int height){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }
}
