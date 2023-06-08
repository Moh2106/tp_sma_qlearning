package sma;

public class Path implements Comparable{
    private String routes;
    private int iteration;

    private String localName;

    public Path() {
    }

    public Path(String routes, int iteration, String localName) {
        this.routes = routes;
        this.iteration = iteration;
        this.localName = localName;
    }

    public String getRoutes() {
        return routes;
    }

    public void setRoutes(String routes) {
        this.routes = routes;
    }

    public int getIteration() {
        return iteration;
    }

    public void setIteration(int iteration) {
        this.iteration = iteration;
    }

    public String getLocalName() {
        return localName;
    }

    public void setLocalName(String localName) {
        this.localName = localName;
    }

    @Override
    public int compareTo(Object o) {

        Path path = (Path) o;

        if (this.iteration > path.iteration)
            return 1;
        else if (this.iteration < path.iteration)
            return -1;

        return 0;

    }
}
