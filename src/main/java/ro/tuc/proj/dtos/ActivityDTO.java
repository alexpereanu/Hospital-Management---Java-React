package ro.tuc.ds2020.dtos;


public class ActivityDTO {


    private int id;

    private Long startDate;

    private Long endDate;

    private String activity;

    private boolean anomaly = false;

    public ActivityDTO(int id, Long startDate, Long endDate, String activity) {
        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
        this.activity = activity;
    }

    public ActivityDTO() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Long getStartDate() {
        return startDate;
    }

    public void setStartDate(Long startDate) {
        this.startDate = startDate;
    }

    public Long getEndDate() {
        return endDate;
    }

    public void setEndDate(Long endDate) {
        this.endDate = endDate;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public boolean isAnomaly() {
        return anomaly;
    }

    public void setAnomaly(boolean anomaly) {
        this.anomaly = anomaly;
    }
}
