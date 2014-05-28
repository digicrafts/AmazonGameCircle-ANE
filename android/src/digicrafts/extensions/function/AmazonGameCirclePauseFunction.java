package digicrafts.extensions.function;

import com.adobe.fre.FREContext;
import com.adobe.fre.FREFunction;
import com.adobe.fre.FREObject;
import digicrafts.extensions.AmazonGameCircleContext;

public class AmazonGameCirclePauseFunction implements FREFunction {
	
	@Override
  	public FREObject call(FREContext context, FREObject[] args)
	{
        AmazonGameCircleContext cnt = (AmazonGameCircleContext)context;
        cnt.pause();
	    return null;
	}
}
