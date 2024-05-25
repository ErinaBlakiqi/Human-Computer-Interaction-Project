package model.dto;

public class DailyChartDto {
    private String day;
    private int count;

    public DailyChartDto(String day, int count) {
        this.day = day;
        this.count = count;
    }

    public String getDay() {
        return day;
    }

    public int getCount() {
        return count;
    }
}
