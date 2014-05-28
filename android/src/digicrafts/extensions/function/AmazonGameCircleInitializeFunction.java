package digicrafts.extensions.function;

import com.adobe.fre.*;
import digicrafts.extensions.AmazonGameCircleContext;

public class AmazonGameCircleInitializeFunction implements FREFunction {
	
	@Override
  	public FREObject call(FREContext context, FREObject[] args)
	{
//	    try
//	    {

            AmazonGameCircleContext cnt = (AmazonGameCircleContext)context;
            cnt.initialize(context.getActivity());
//	    }
//	    catch (IllegalStateException e) {
//	      e.printStackTrace();
//	    } catch (FRETypeMismatchException e) {
//	      e.printStackTrace();
//	    } catch (FREInvalidObjectException e) {
//	      e.printStackTrace();
//	    } catch (FREWrongThreadException e) {
//	      e.printStackTrace();
//	    }

	    return null;
	}
}
