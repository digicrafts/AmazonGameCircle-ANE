package digicrafts.extensions.function;

import com.adobe.fre.*;
import digicrafts.extensions.AmazonGameCircleContext;

public class AmazonGameCircleSetPopUpLocationFunction implements FREFunction {

	@Override
	public FREObject call(FREContext context, FREObject[] args)
    {
        FREObject locationObj = args[0];
        try
        {
            String location = locationObj.getAsString();
            AmazonGameCircleContext cnt = (AmazonGameCircleContext)context;
            cnt.setPopUpLocation(location);

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
