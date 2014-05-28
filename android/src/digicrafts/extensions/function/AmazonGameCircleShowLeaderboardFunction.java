package digicrafts.extensions.function;

import com.adobe.fre.FREContext;
import com.adobe.fre.FREFunction;
import com.adobe.fre.FREInvalidObjectException;
import com.adobe.fre.FREObject;
import com.adobe.fre.FRETypeMismatchException;
import com.adobe.fre.FREWrongThreadException;

import digicrafts.extensions.AmazonGameCircleContext;

public class AmazonGameCircleShowLeaderboardFunction implements FREFunction {

	@Override
	public FREObject call(FREContext context, FREObject[] args)
    {
        FREObject leaderboardIdObj = args[0];
        try
        {
          String leaderboardId = leaderboardIdObj.getAsString();
          AmazonGameCircleContext cnt = (AmazonGameCircleContext)context;
          if(leaderboardId==null){
              cnt.showLeaderboard(null);
          } else {
              cnt.showLeaderboard(leaderboardId);
          }

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
