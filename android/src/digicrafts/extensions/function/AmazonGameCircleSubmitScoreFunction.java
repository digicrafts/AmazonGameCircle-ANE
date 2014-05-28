package digicrafts.extensions.function;

import com.adobe.fre.FREContext;
import com.adobe.fre.FREFunction;
import com.adobe.fre.FREInvalidObjectException;
import com.adobe.fre.FREObject;
import com.adobe.fre.FRETypeMismatchException;
import com.adobe.fre.FREWrongThreadException;

import digicrafts.extensions.AmazonGameCircleContext;

public class AmazonGameCircleSubmitScoreFunction implements FREFunction {

	  private static final String TAG = AmazonGameCircleSubmitScoreFunction.class.getName();
	  
	  @Override
	  public FREObject call(FREContext context, FREObject[] args)
	  {

        try
        {
          FREObject leaderboardIdObj = args[0];
          String leaderboardId = leaderboardIdObj.getAsString();

          FREObject scoreObj = args[1];
          int score = scoreObj.getAsInt();

          AmazonGameCircleContext cnt = (AmazonGameCircleContext)context;
          cnt.submitScore(leaderboardId,score);

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
