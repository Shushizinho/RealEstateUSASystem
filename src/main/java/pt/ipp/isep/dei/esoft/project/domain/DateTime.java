package pt.ipp.isep.dei.esoft.project.domain;

import java.io.Serializable;
import java.util.Calendar;

/**
 * The type Date time.
 */
public class DateTime implements Serializable {

    private final Calendar date;


    /**
     * Instantiates a new Date time.
     */
    public DateTime() {
        this.date = Calendar.getInstance();
    }


    /**
     * Instantiates a new Date time.
     *
     * @param day   the day
     * @param month the month
     * @param year  the year
     */
    public DateTime(int day, int month, int year) {
        Calendar c = Calendar.getInstance();
        c.set(year, month-1, day);
        this.date = c;
    }

    /**
     * Instantiates a new Date time.
     *
     * @param hour   the hour
     * @param minute the minute
     */
    public DateTime(int hour, int minute){
        Calendar c = Calendar.getInstance();
        c.set(hour, minute);
        this.date = c;
    }

    /**
     * Instantiates a new Date time.
     *
     * @param date      the date
     * @param timestamp the timestamp
     */
    public DateTime(DateTime date, Pair<Integer, Integer> timestamp){
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
     * Get second int.
     *
     * @return the int
     */
    public int getSecond(){
        return date.get(Calendar.SECOND);
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
     * Checks if the date represented by the current object is before the provided date.
     *
     * @param otherDate the date to compare against
     * @return {@code true} if the current date is before {@code otherDate}, {@code false} otherwise
     */
    public boolean isBefore(DateTime otherDate) {
        return this.date.before(otherDate.getDate());
    }

    /**
     * Checks if the date represented by the current object is after the provided date.
     *
     * @param otherDate the date to compare against
     * @return {@code true} if the current date is after {@code otherDate}, {@code false} otherwise
     */


    public boolean isAfter(DateTime otherDate) {
        return this.date.after(otherDate.getDate());
    }

    /**
     * Is equal boolean.
     *
     * @param otherDate the other date
     * @return the boolean
     */
    public boolean isEqual(DateTime otherDate){
        return this.date.equals(otherDate.getDate());
    }


    /**
     * Is after or equal boolean.
     *
     * @param otherDate the other date
     * @return the boolean
     */
    public boolean isAfterOrEqual(DateTime otherDate) {
        return this.date.after(otherDate.getDate()) || (this.getYear()==otherDate.getYear() && this.getMonth()==otherDate.getMonth() && this.getDayOfMonth()==otherDate.getDayOfMonth());
    }

    /**
     * Is before or equal boolean.
     *
     * @param otherDate the other date
     * @return the boolean
     */
    public boolean isBeforeOrEqual(DateTime otherDate) {
        return this.date.before(otherDate.getDate()) || (this.getYear()==otherDate.getYear() && this.getMonth()==otherDate.getMonth() && this.getDayOfMonth()==otherDate.getDayOfMonth());
    }



    /**
     * This method displays the date information: day, month and year.
     * @return String
     */
    @Override
    public String toString() {
        return getDayOfMonth()+"/"+getMonth()+"/"+getYear();
    }

    /**
     * Gets timestamp.
     *
     * @return the timestamp
     */
    public String getTimestamp() {
        return String.format("%02d:%02d",getHour(),getMinute());
    }

    /**
     * This method cheks is the given object is equal to this Date object.
      * @param o
     * @return boolean
     */
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DateTime)) {
            return false;
        }
        DateTime data = (DateTime) o;
        Boolean dayEqual = getDayOfMonth() == data.getDayOfMonth();
        Boolean MonthEqual = getMonth() == data.getMonth();
        Boolean YearEqual = getYear() == data.getYear();
        Boolean HourEqual = getHour() ==data.getHour();
        Boolean MinuteEqual = getMinute() == data.getMinute();
        return ( dayEqual && MonthEqual && YearEqual && HourEqual && MinuteEqual);
    }

    /**
     * Equals date boolean.
     *
     * @param o the o
     * @return the boolean
     */
    public boolean equalsDate(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DateTime)) {
            return false;
        }
        DateTime data = (DateTime) o;
        Boolean dayEqual = getDayOfMonth() == data.getDayOfMonth();
        Boolean MonthEqual = getMonth() == data.getMonth();
        Boolean YearEqual = getYear() == data.getYear();
        return (dayEqual && MonthEqual && YearEqual);
    }


}
