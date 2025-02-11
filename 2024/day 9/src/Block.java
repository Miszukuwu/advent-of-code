public class Block {
    public int id;
    public int freeSpace;
    public int size;

    public Block(int id, int size, int freeSpace) {
        this.id = id;
        this.size = size;
        this.freeSpace = freeSpace;
    }
}
