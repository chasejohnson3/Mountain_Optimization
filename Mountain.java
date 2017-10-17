
public class Mountain
{
    final Point PEAK = new Point(.325881,-.142442); 
    //max height is 
    final Point HEAD = new Point(-2,0);
    
    private static Point location;
    private static Point nextStep;
    
    public static void main(String[] args)
    {
        Point peak = new Point(.325881,-.142442);
        Mountain mountain = new Mountain();
        location = new Point(-2,0);
        nextStep = new Point(-1.99,0);
        Point nextTry = nextStep;
        do{
            // Check every point around the current one incrementing by 2 degrees at each check
            for(double theta = 0.0; theta < 360; theta+=2.0)
            {
                nextTry.setX(location.getX()+.01*Math.cos(theta*(2.0*Math.PI/360.0)));
                //nextTry.setX(location.getX()+.01);
                nextTry.setY(location.getY()+.01*Math.sin(theta*(2.0*Math.PI/360.0)));
                // the nextTry is not steeper than 45 degrees and gets closer to the peak than the previous best nextStep, set the next step to nextTry
                if(mountain.isLegal(location,nextTry) && mountain.getDistance(nextTry,peak) < mountain.getDistance(nextStep,peak))
                {
                    nextStep = nextTry;
                }
            }
            System.out.println("{"+nextStep.getX()+","+nextStep.getY()+ "," +mountain.getHeight(nextStep)+"},");
            location = nextStep;
        }
        while(mountain.getDistance(nextStep,peak)>=.01);
        
    }
    // Return the current location
    public Point getLocation()
    {
        return location;
    }
    // Get the vertical height of a point
    public double getHeight(Point a)
    {
        double x = a.getX();
        double y = a.getY();
        return (.9*Math.exp(1 - Math.pow(x, 2) - Math.pow(y,2)) + Math.exp(-Math.pow(x - 1,2)- 
        Math.pow(y + .25,2))- Math.exp(-Math.pow(y,2)) - Math.exp(-.5*Math.pow(x,2)) + .25*x - .2*y + 1.5);
    }
    // get the vertical distance between two points
    public double getVertDist(Point a, Point b)
    {
        double hc = this.getHeight(b)-this.getHeight(a);
        double dist = this.getDistance(b, a);
        return Math.sqrt(Math.pow(hc,2)+Math.pow(dist,2));
    }
    // Get the vertical slope between two points
    public double getVertSlope(Point a, Point b)
    {
        double hc = this.getHeight(b)-this.getHeight(a);
        double dist = this.getDistance(b, a);
        return hc/dist;
    }
    // Get the distance between two points
    public double getDistance(Point a, Point b)
    {
        return Math.sqrt((a.getY()-b.getY())*(a.getY()-b.getY())+(a.getX()-b.getX())*(a.getX()-b.getX()));   
    }
    // Return true if the vertical angle between two points is less than or equal to 45 degrees
    public boolean isLegal(Point a, Point b)
    {
        if (getVertSlope(a, b) <= 1)//&& 
        //this.getDistance(a, PEAK)>this.getDistance(b, PEAK))
        //returns true if the verticle slope is less than 1 and the distance to the peak decreases
        {
            return true;//for now
        }
        else
            return false;
    }
    
    
    
    
}
