package endless.model.factory;

import java.util.ArrayList;
import java.util.HashMap;
import java.awt.Color;

public class ShapeFactory {
   private static HashMap<Color, ArrayList<Shape>> poolCoin = new HashMap();

   public static Shape getCoin(Color color) {
	  if(poolCoin.get(color) == null)
		  poolCoin.put(color,new ArrayList<Shape>());
	   
	  Coin coin;
	  int size = poolCoin.get(color).size();
      if(size == 0) {
    	  coin = new Coin(color);
      }
      else{
    	  coin = (Coin) poolCoin.get(color).get(0);
    	  coin.setVisible(true);
    	  poolCoin.get(color).remove(0);
      }
      return coin;
   }
   public static void store(Coin coin){
		poolCoin.get(coin.getColor()).add(coin);
	   
   }
}