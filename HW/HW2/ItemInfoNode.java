package HW.HW2;

public class ItemInfoNode {
    private ItemInfo info;
    private ItemInfoNode prev;
    private ItemInfoNode next;

    public ItemInfoNode() {
        this.info = null;
        this.prev = null;
        this.next = null;
    }

    public void setInfo(ItemInfo info){
        this.info = info;
    }

    public ItemInfo getInfo(){
        return info;
    }

    public void setNext(ItemInfoNode node){
        this.next = node;
    }

    public void setPrev(ItemInfoNode node){
        this.prev = node;
    }

    public ItemInfoNode getNext(){
        return next;
    }

    public ItemInfoNode getPrev(){
        return prev;
    }
}
