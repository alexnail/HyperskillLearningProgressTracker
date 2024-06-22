package tracker;

public record Submission(Integer java, Integer dsa, Integer dbs, Integer spring) {

    public Submission add(Submission points) {
        return new Submission(java + points.java, dsa + points.dsa, dbs + points.dbs, spring + points.spring);
    }

    public static Submission empty() {
        return new Submission(0, 0, 0, 0);
    }
}
