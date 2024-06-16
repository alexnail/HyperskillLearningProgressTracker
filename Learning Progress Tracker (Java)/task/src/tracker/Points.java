package tracker;

public record Points(Integer java, Integer dsa, Integer dbs, Integer spring) {

    public Points add(Points points) {
        return new Points(java + points.java, dsa + points.dsa, dbs + points.dbs, spring + points.spring);
    }
}
