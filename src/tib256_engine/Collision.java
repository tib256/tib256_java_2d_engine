
package tib256_engine;

/**
 *
 * @author Tibor Fiak
 */
public class Collision {
    
  public static String collisionRactangleBlock(GameItem i1,GameItem i2){

  //A variable to tell us which side the 
  //collision is occurring on
  String collisionSide = "none";
  
  //Calculate the distance vector
  double vx = i1.centerX() - i2.centerX();
  double vy = i1.centerY() - i2.centerY();
  
  //Figure out the combined half-widths and half-heights
  double combinedHalfWidths = i1.halfWidth() + i2.halfWidth();
  double combinedHalfHeights = i1.halfHeight() + i2.halfHeight();
    
  //Check whether vx is less than the combined half widths 
  if(Math.abs(vx) < combinedHalfWidths){
    //A collision might be occurring! 
    //Check whether vy is less than the combined half heights 
    if(Math.abs(vy) < combinedHalfHeights){
      double overlapX = combinedHalfWidths - Math.abs(vx);
      double overlapY = combinedHalfHeights - Math.abs(vy);
       
      if(overlapX >= overlapY){
        if(vy > 0)
        {
          collisionSide = "top";         
          i1.y = (int)Math.round(i1.y + overlapY);
          i1.vy*=-0.5;
        }
        else{
          collisionSide = "bottom"; 
           i1.y = (int)Math.round(i1.y - overlapY);
           i1.vy=0;
        }   
      } 
      else{
        if(vx > 0)
        {
          collisionSide = "left";          
          i1.x = (int)Math.round(i1.x + overlapX);
        }
        else 
        {
          collisionSide = "right";
          i1.x = (int)Math.round(i1.x - overlapX);
        }       
      } 
    }
    else{
      collisionSide = "none";
    }
  } 
  else{
    collisionSide = "none";
  }
  
  return collisionSide;
  }//#collisionRactangle
    
     
 public static void  blockCircle(GameItem p1,GameItem p2){  

  double vx = p1.centerX() - p2.centerX();
  double vy = p1.centerY() - p2.centerY();
  
  double magnitude = Math.sqrt(vx * vx + vy * vy);
  
   double totalRadii = p1.halfWidth() + p2.halfWidth();
  
  if(magnitude < totalRadii){
    double overlap = totalRadii - magnitude;
    
    magnitude = 1/magnitude;
    double dx = vx * magnitude;
    double dy = vy * magnitude;

    p1.x += overlap * dx; 
    p1.y += overlap * dy;
  }
  
  
 }//blockCircle
 
  public static String collisionRactangle(GameItem i1,GameItem i2){

  //A variable to tell us which side the 
  //collision is occurring on
  String collisionSide = "none";
  
  //Calculate the distance vector
  double vx = i1.centerX() - i2.centerX();
  double vy = i1.centerY() - i2.centerY();
  
  //Figure out the combined half-widths and half-heights
  double combinedHalfWidths = i1.halfWidth() + i2.halfWidth();
  double combinedHalfHeights = i1.halfHeight() + i2.halfHeight();
    
  //Check whether vx is less than the combined half widths 
  if(Math.abs(vx) < combinedHalfWidths){
    //A collision might be occurring! 
    //Check whether vy is less than the combined half heights 
    if(Math.abs(vy) < combinedHalfHeights){
      double overlapX = combinedHalfWidths - Math.abs(vx);
      double overlapY = combinedHalfHeights - Math.abs(vy);
       
      if(overlapX >= overlapY){
        if(vy > 0)
        {
          collisionSide = "top";         
        }
        else{
          collisionSide = "bottom"; 
        }   
      } 
      else{
        if(vx > 0)
        {
          collisionSide = "left";
        }
        else 
        {
          collisionSide = "right";
        }       
      } 
    }
    else{
      collisionSide = "none";
    }
  } 
  else{
    collisionSide = "none";
  }
  
  return collisionSide;
  }//#collisionRactangle
 

  
}//class vege

 
