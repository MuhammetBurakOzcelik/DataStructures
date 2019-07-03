public class Experiment {

    private String setup;
    private int day;
    private String time;
    private boolean completed;
    private float accuracy;

    public Experiment(String setup, String time, boolean completed,int day, float accuracy){
        this.day = day;
        this.setup = setup;
        this.time = time;
        this.completed = completed;
        this.accuracy = accuracy;
    }
    public Experiment(int day, String time,boolean completed,float accuracy){
        this.day = day;
        this.time = time;
        this.completed = completed;
        this.accuracy = accuracy;
    }

    public Experiment() {

    }

    public int getDay(){
        return this.day;
    }

    public void setDay(int day) {this.day = day;}

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed){ this.completed = completed; }

    @Override
    public String toString() {
        return "Experiment{" +
                "setup='" + setup + '\'' +',' +'\t' +
                "day=" + day +',' +'\t'+
                "time='" + time  + '\''+',' +'\t' +
                "accuracy=" + accuracy +',' +'\t' +
                "completed=" + completed +',' +
                '}';
    }

    public double getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(float accuracy) {
        this.accuracy = accuracy;
    }


    public String getSetup() {
        return setup;
    }

    public void setSetup(String setup) {
        this.setup = setup;
    }

}
