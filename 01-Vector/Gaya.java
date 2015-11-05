
public class Gaya
{
    // instance variables - replace the example below with your own
    private Vector3D titikAcuan;
    private Vector3D arah;

    /**
     * Constructor for objects of class Gaya
     */
    public Gaya(Vector3D titikAcuan, Vector3D arah)
    {
        this.titikAcuan=titikAcuan;
        this.arah=arah;
    }

    /**
     * An example of a method - replace this comment with your own
     * 
     * @param  y   a sample parameter for a method
     * @return     the sum of x and y 
     */
    public Vector3D getTitikAcuan()
    {
        // put your code here
        return titikAcuan;
    }
    public Vector3D getArah()
    {
        return arah;
    }
}
