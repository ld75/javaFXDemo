package application;

public class ControllerActionBoundry
{
    String action;
    Object parameter;

    public ControllerActionBoundry(String action, Object object)
    {
        this.action=action;
        this.parameter=object;
    }

    public String getAction()
    {
        return action;
    }

    public void setAction(String action)
    {
        this.action = action;
    }

    public Object getParameter()
    {
        return parameter;
    }

    public void setParameter(Object parameter)
    {
        this.parameter = parameter;
    }
}
