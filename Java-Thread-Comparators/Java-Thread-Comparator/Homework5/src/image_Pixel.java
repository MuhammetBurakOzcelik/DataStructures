public class image_Pixel {
    private int red;
    private int green;
    private int blue;

    public image_Pixel(int red, int green, int blue) {
        this.red = red;
        this.green = green;
        this.blue = blue;
    }
    public String toString()
    {
        return getRed()+ " " + getGreen() + " " + getBlue();
    }
    public int getBlue() {
        return blue;
    }

    public void setBlue(int blue) {
        this.blue = blue;
    }

    public int getGreen() {
        return green;
    }

    public void setGreen(int green) {
        this.green = green;
    }

    public int getRed() {
        return red;
    }

    public void setRed(int red) {
        this.red = red;
    }
}
