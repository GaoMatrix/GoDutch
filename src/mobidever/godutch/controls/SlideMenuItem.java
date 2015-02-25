package mobidever.godutch.controls;

public class SlideMenuItem {

    private int mItemID;
    private String mTitle;
    
    public SlideMenuItem(int mItemID, String mTitle) {
        super();
        this.mItemID = mItemID;
        this.mTitle = mTitle;
    }
    public int getItemID() {
        return mItemID;
    }
    public void setItemID(int itemID) {
        this.mItemID = itemID;
    }
    public String getTitle() {
        return mTitle;
    }
    public void setTitle(String title) {
        this.mTitle = title;
    }
}
