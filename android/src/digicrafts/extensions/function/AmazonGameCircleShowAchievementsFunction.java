package digicrafts.extensions.function;

import com.adobe.fre.*;
import digicrafts.extensions.AmazonGameCircleContext;

public class AmazonGameCircleShowAchievementsFunction implements FREFunction {

	@Override
	public FREObject call(FREContext context, FREObject[] args)
    {
        AmazonGameCircleContext cnt = (AmazonGameCircleContext)context;
        cnt.showAchievements();

        return null;
    }

}
