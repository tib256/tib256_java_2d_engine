
package tib256_engine;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;

/**
 * Picture of gameItem
 * @author Tibor Fiak
 */
public class Texture {
    
    public int width;
    public int height;
    
    private String dir;
    public String path; 
    
    public BufferedImage image = null;
    public BufferedImage copyImage = null;
    
    public ArrayList<BufferedImage> AnimationImages = new ArrayList<BufferedImage>();
    
    public Texture(){
    
    }
    
    public Texture(String dir){
    this.dir = dir;
    }
    
    public BufferedImage load(String name){
      
      
      try{ 
        image  = ImageIO.read(getClass().getResourceAsStream(dir+"/"+name));
        path=dir+"/"+name;
         int width2 = image.getWidth();
         int height2 = image.getHeight();
           copyImage = new BufferedImage(width2,height2,image.getType());
           for(int i=0; i<width2; i++)
                  for(int j=0; j<height2; j++)
                     copyImage.setRGB(i,j,image.getRGB(i,j));
           
      }
      catch (IOException e){
          System.out.println(e);
      }
      
      width= image.getWidth();
      height= image.getHeight();
      
      
      return image ;
    }
    
    public BufferedImage load_slice(String name,int sourceX,int sourceY,int destX,int destY){
      
        BufferedImage sliceImage =null;
      
      try{ 
        width=destX-sourceX;
        height=destY-sourceY;
        image  = ImageIO.read(getClass().getResourceAsStream(dir+"/"+name));
        path=dir+"/"+name;
           sliceImage = new BufferedImage(width,height,image.getType());
           
           int i2=0;
           int j2=0;
           for(int i=sourceX; i<destX; i++){
                  j2=0;
                  for(int j=sourceY; j<destY; j++){
                     sliceImage.setRGB(i2,j2,image.getRGB(i,j));
                     j2++;
                  }
                  i2++;
           }
         
           
      }
      catch (IOException e){
          System.out.println(e);
      }
      
      image=sliceImage;
      return sliceImage;
    }//#
    
    public void loadAnimationImages(String name,int width,int height,int row,int column,int allImagesNumber) {
        try{
            for (int currentImageNumber = 0; currentImageNumber < allImagesNumber; currentImageNumber++) {
                image  = ImageIO.read(getClass().getResourceAsStream(dir+"/"+name));
                BufferedImage currentImage = new BufferedImage(width,height,image.getType());
                
                int sourceX
                =(int)Math.round(Math.floor(currentImageNumber % column) * width);
                int sourceY
                = (int)Math.round(Math.floor(currentImageNumber / column) * height);

                int destX
                =(int)Math.round(Math.floor(currentImageNumber % column) * width)+width;
                int destY
                = (int)Math.round(Math.floor(currentImageNumber / column) * height)+height;

                    int i2=0;
                    int j2=0;
                    for(int i=sourceX; i<destX; i++){
                      j2=0;
                      for(int j=sourceY; j<destY; j++){
                         currentImage.setRGB(i2,j2,image.getRGB(i,j));
                         j2++;
                      }
                      i2++;
                    }
               
                
                AnimationImages.add(currentImage);
            }
            
            image=AnimationImages.get(0);
        }
        
        catch (IOException e){
          System.out.println(e);
        }

    }//#
    
    public void updateAnimation(int currenframes){
      image=AnimationImages.get(currenframes);
    }
    

}//end of class
