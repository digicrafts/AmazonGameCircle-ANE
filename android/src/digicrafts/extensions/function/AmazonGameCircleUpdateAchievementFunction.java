package digicrafts.extensions.function;

import com.adobe.fre.*;
import digicrafts.extensions.AmazonGameCircleContext;

public class AmazonGameCircleUpdateAchievementFunction implements FREFunction {

	  private static final String TAG = AmazonGameCircleUpdateAchievementFunction.class.getName();
	  
	  @Override
	  public FREObject call(FREContext context, FREObject[] args)
	  {

        try
        {
          FREObject achievementIdObj = args[0];
          String achievementId = achievementIdObj.getAsString();

          FREObject progressObj = args[1];
          float progress = (float)progressObj.getAsDouble();

          AmazonGameCircleContext cnt = (AmazonGameCircleContext)context;
          cnt.updateAchievement(achievementId,progress);

        }
        catch (IllegalStateException e) {
          e.printStackTrace();
        } catch (FRETypeMismatchException e) {
          e.printStackTrace();
        } catch (FREInvalidObjectException e) {
          e.printStackTrace();
        } catch (FREWrongThreadException e) {
          e.printStackTrace();
        }

        return null;
    }

}
