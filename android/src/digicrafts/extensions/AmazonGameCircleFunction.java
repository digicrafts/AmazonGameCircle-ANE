package digicrafts.extensions;

import com.adobe.fre.*;


public abstract class AmazonGameCircleFunction<T> implements FREFunction {

    private String name;
    private Class<?>[] argTypes;

    public AmazonGameCircleFunction(String name, Class<?> ... classes) {
        this.name = name;
        this.argTypes = classes;
    }

    @Override
    public FREObject call(FREContext context, FREObject[] args) {
        AmazonGameCircleContext cbCx = (AmazonGameCircleContext)context;
        try {
            T ret = onCall(cbCx, convertArgs(args));
            if (ret == null)
                return null;
            else if (ret.getClass() == Boolean.class)
                return FREObject.newObject((Boolean)ret);
            else if (ret.getClass() == String.class)
                return FREObject.newObject((String)ret);
            else if (Integer.class.isInstance(ret))
                return FREObject.newObject((Integer)ret);
            else if (Double.class.isInstance(ret))
                return FREObject.newObject((Double)ret);
            else if (Number.class.isInstance(ret))
                return FREObject.newObject(Double.valueOf(ret.toString()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private Object[] convertArgs(FREObject[] args)
            throws IllegalStateException, FRETypeMismatchException, FREInvalidObjectException, FREWrongThreadException {
        if (args == null)
            return null;
        Object[] ret = new Object[args.length];
        for (int i = 0; i < args.length; i++) {
            if (args[i] == null)
                ret[i] = null;
            else if (argTypes[i] == String.class)
                ret[i] = args[i].getAsString();
            else if (argTypes[i] == Integer.class)
                ret[i] = args[i].getAsInt();
            else if (argTypes[i] == Double.class)
                ret[i] = args[i].getAsDouble();
            else if (argTypes[i] == Boolean.class)
                ret[i] = args[i].getAsBool();
        }
        return ret;
    }

    public abstract T onCall(AmazonGameCircleContext context, Object[] args)
            throws IllegalStateException, FRETypeMismatchException, FREInvalidObjectException, FREWrongThreadException;

    public String getName() {
        return name;
    }

}
