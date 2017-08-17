
package tib256_engine;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

/**
 * GameItem object this object represation the actor objects. Actor objects act,show on the window.
 *
 *@author Fiak Tibor
 */
public class GameItem {
    /**
     * You can give name the GameItem
     */
    public String name="";
    
    public  int id = 0;
    /**
    * x position
    */
    public  int x = 100;
    /**
    * y position
    */
    public  int y = 100;
    /**
    * picture width
    */
    public  int width = 100;
    /**
    * picture height
    */
    public  int height = 100;
    public  int widthA = 100;
    public  int heightA = 100;
    /**
     * piece of picture topleft point X position
     */
    public  int sourceX = 0;
    /**
     * piece of picture topleft point Y position
     */
    public  int sourceY  = 0;
    /**
     * piece of picture bottomright point X position
     */
    public  int destX = 100;
    /**
     * piece of picture topleft point Y position
     */
    public  int destY  = 100;
    
    /**
    * GameItem velocity in pixel/updatecircle
    */
    public  float  velocity = 3;
    public  float  vx = 0;
    public  float  vy = 0;
    public  float  jumpForce = -11;
    public  float  gravity = 0.3f;
    public  float  friction = 0.96f;
    public  float  bounce = -0.3f;
    
    /**
     * animation pictures number of pieces
     */
    public int numberOfFrames = 5;
    /**
     * animation current picture
     */
    public int currentFrame = 0;
    /**
     * Animation pictures number of rows
     */
    public int rows = 3;
    /**
     * Animation pictures number of columns
     */
    public int colummns = 3;
    /**
     * this in gameItem have a animation 
     */
    public  boolean animend = false;
    
    public  boolean moveUp = false;
    public  boolean moveDown = false;
    public  boolean moveLeft = false;
    public  boolean moveRight = false;
    
    /**
     * this gameItem visible
     */
    public  boolean visible = true;
    /**
     * this gameItem on the ground
     */
    public  boolean isOnGround = true;
    
    
    public boolean moveable = false;
    
    /**
     * this gameItem angle in degree
     */
    public double angle = 0;
    
    private Texture texture = new Texture("/images");
    
    /**
     * this picture of gameItem of path 
     */
    public String filename;
    
    /**
     * You can settings your GameItem picture
     * @param filename example: "images/picture1.png"
     */
    public void setImage(String filename){
       this.filename=filename;
       init();
    }

    /**
     * You can settings your GameItem picture , you can draw a piece of a picture.<br/>
       You have to setting the sourceX,sourceY,destX,destY parameters.<br/>
       <br/>Example: You have a big picture and in the picture there are a lot of character , you can draw just a character.
       You can give the piece of picture <b>lefttop positions(sourceY and sourceX)</b> and <b>rightbottom positions(destX,destY)</b>.<br/>
     *
     *
     * @param name example: "images/picture1.png"
     */
    public void setImageCutPiece(String name){
       this.filename=name;
       texture.load_slice(this.filename, sourceX, sourceY, destX, destY);
    }
    
    /**
     * You can setting the animationImage.<br/>
     * You have to setting width,height,rows and columns variables.
     * @param name 
     */
    public void setAnimationImage(String name){
        texture.loadAnimationImages(name, width, height, rows,colummns,numberOfFrames);
    }
           
    private  void init(){
      texture.load(filename);
    }

    /**
     * You can mirror the picture horizontally.
     */
    public void mirrorHorizontally()
    {
        AffineTransform tx = AffineTransform.getScaleInstance(1, -1);
        tx.translate(0, -texture.image.getHeight());
        AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
        texture.image=op.filter(texture.image, null);
    }
     
     /**
     * You can mirror the picture vertically.
     */
    public void mirrorVertically()
    {
        AffineTransform tx = AffineTransform.getScaleInstance(-1, 1);
        tx.translate(-texture.image.getWidth(), 0);
        AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
        texture.image=op.filter(texture.image, null);
    }
      
    /**
    * You can update animation. You can update the picture.
    */
    public void updateAnimation(){  
        texture.updateAnimation(currentFrame);
    }
    
    /**
    * @return the center point of the picture Y position
    */
    public double centerX(){
      return this.x + (this.width * 0.5);
    }
    
    /**
    * @return the center point of the picture Y position
    */
    public double  centerY()
    {
        return this.y + (this.height * 0.5);
    }
    
    /**
     * 
     * @return half with of the picture
     */
    public double halfWidth()
    {
      return this.width * 0.5;
    }
    
     /**
     * 
     * @return half height of the picture
     */
    public double halfHeight()
    {
        return this.height * 0.5;
    }
    
    /**
     * You can draw text on the window
     * @param text this is the text , which you would like wirte on the picture
     * @param x this is x position of the text
     * @param y this is y position of the text
     * @param fontSize this is the font size of the text in pixel
     */
    public void text_write(String text,int x,int y,int fontSize){
      texture = new Texture("/images");
        BufferedImage img = new BufferedImage(
            width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = img.createGraphics();
        g2d.drawImage(img, 0, 0, width, height, null);
        g2d.setPaint(Color.BLACK);
        g2d.setFont(new Font("Serif", Font.BOLD, 20));
        FontMetrics fm = g2d.getFontMetrics();
        g2d.drawString(text, x, y);
        g2d.dispose();
        texture.image=img;
    }
    
    /**
     * You can make gray a picture
     */
    public void gray()
    {
        BufferedImage image = new BufferedImage(texture.image.getWidth(), texture.image.getHeight(),  
            BufferedImage.TYPE_BYTE_GRAY);  
        Graphics2D g = image.createGraphics();  
        g.drawImage(texture.image, 0, 0, null);  
        g.dispose();
        texture.image=image;
    }//#
    
     /**
     * You can make gray a picturem, the invisible area won't became gray.
     */
     public void grayPng() {
   
      try {
         width = texture.image.getWidth();
         height = texture.image.getHeight();
         
         for(int i=0; i<height; i++){
         
            for(int j=0; j<width; j++){
            
               Color c = new Color(texture.image.getRGB(j, i));
               if(c.getRGB()!=-16777216){
                    int red = (int)(c.getRed() * 0.299);
                    int green = (int)(c.getGreen() * 0.587);
                    int blue = (int)(c.getBlue() *0.114);
                    Color newColor = new Color(red+green+blue,
                    red+green+blue,red+green+blue);

                    texture.image.setRGB(j,i,newColor.getRGB());
               }
                
            }
         }

      } catch (Exception e) {}
   }//#
   
   /*
   * @return return BufferedImage
   */
   public BufferedImage getImage(){
      return texture.image;
   }
   
   /**
   * You can repair a picture. Example you make gray picture and you would like make this color again.
   */
   public void imageRepair(){
     texture.image=texture.copyImage;
   }

    @Override
    public String toString() {
        return "GameItem{" + "name=" + name + '}'+"\n";
    }

   
    
}
