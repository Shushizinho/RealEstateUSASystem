package pt.ipp.isep.dei.esoft.project.dto;

import pt.ipp.isep.dei.esoft.project.domain.DateTime;
import pt.ipp.isep.dei.esoft.project.domain.Pair;

import java.util.Calendar;

/**
 * The type Date formatted dto.
 */
public class DateTimeDTO {
    private int dayOfMonth;
    private int month;
    private int year;
    private int hour;
    private int minute;


    private final Calendar date;


    /**
     * Instantiates a new Date formatted dto.
     */
    public DateTimeDTO() {
        this.date = Calendar.getInstance();
    }

    /**
     * Instantiates a new Date formatted dto.
     *
     * @param date the date
     */
    public DateTimeDTO(Calendar date) {
        this.date = date;
    }

    /**
     * Instantiates a new Date formatted dto.
     *
     * @param day   the day
     * @param month the month
     * @param year  the year
     */
    public DateTimeDTO(int day, int month, int year) {
        Calendar c = Calendar.getInstance();
        c.set(year, month-1, day);
        this.date = c;
    }

    /**
     * Instantiates a new Date formatted dto.
     *
     * @param hour   the hour
     * @param minute the minute
     */
    public DateTimeDTO(int hour, int minute){
        Calendar c = Calendar.getInstance();
        c.set(hour, minute);
        this.date = c;
    }

    /**
     * Instantiates a new Date formatted dto.
     *
     * @param date      the date
     * @param timestamp the timestamp
     */
    public DateTimeDTO(DateTime date, Pair<Integer, Integer> timestamp){
        Calendar c = Calendar.getInstance();
        c.set(date.getYear(), date.getMonth()-1, date.getDayOfMonth(), timestamp.getLeft(), timestamp.getRight());
        this.date = c;
    }

    /**
     * Gets hour.
     *
     * @return the hour
     */
    public int getHour() {
        return date.get(Calendar.HOUR_OF_DAY);
    }

    /**
     * Get minute int.
     *
     * @return the int
     */
    public int getMinute(){
        return date.get(Calendar.MINUTE);
    }

    /**
     * This method returns the day of the month associated with the date.
     *
     * @return dayOfMonth int
     */
    public int getDayOfMonth(){
        return date.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * This method returns the month associated with the date.
     *
     * @return Month int
     */
    public int getMonth(){
        return date.get(Calendar.MONTH)+1;
    }

    /**
     * This method returns the year associated with the date.
     *
     * @return Year int
     */
    public int getYear(){
        return date.get(Calendar.YEAR);
    }

    /**
     * This method returns the current date.
     *
     * @return Date date
     */
    public Calendar getDate() {
        return date;
    }

    /**
     * To string dto string.
     *
     * @return the string
     */
    public String toStringDTO() {
        return getDayOfMonth()+"/"+getMonth()+"/"+getYear();
    }
}
