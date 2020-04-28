package mose.core.time;

import org.apache.commons.lang3.time.DateUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.Locale;

/**
 * Created by Administrator on 2014/8/7.
 */
public class TimeUtil {
    public static TimeUtil INSTANCE = new TimeUtil();

    private TimeUtil() {
    }

    /**
     * Number of milliseconds in a standard second.
     *
     * @since 2.1
     */
    public static final long MILLIS_PER_SECOND = DateUtils.MILLIS_PER_SECOND;
    /**
     * Number of milliseconds in a standard minute.
     *
     * @since 2.1
     */
    public static final long MILLIS_PER_MINUTE = DateUtils.MILLIS_PER_MINUTE;
    /**
     * Number of milliseconds in a standard hour.
     *
     * @since 2.1
     */
    public static final long MILLIS_PER_HOUR = DateUtils.MILLIS_PER_HOUR;
    /**
     * Number of milliseconds in a standard day.
     *
     * @since 2.1
     */
    public static final long MILLIS_PER_DAY = DateUtils.MILLIS_PER_DAY;

    /**
     * This is half a month, so this represents whether a date is in the top
     * or bottom half of the month.
     */
    public static final int SEMI_MONTH = DateUtils.SEMI_MONTH;

    /**
     * A week range, starting on Sunday.
     */
    public static final int RANGE_WEEK_SUNDAY = DateUtils.RANGE_WEEK_SUNDAY;
    /**
     * A week range, starting on Monday.
     */
    public static final int RANGE_WEEK_MONDAY = DateUtils.RANGE_WEEK_MONDAY;
    /**
     * A week range, starting on the day focused.
     */
    public static final int RANGE_WEEK_RELATIVE = DateUtils.RANGE_WEEK_RELATIVE;
    /**
     * A week range, centered around the day focused.
     */
    public static final int RANGE_WEEK_CENTER = DateUtils.RANGE_WEEK_CENTER;
    /**
     * A month range, the week starting on Sunday.
     */
    public static final int RANGE_MONTH_SUNDAY = DateUtils.RANGE_MONTH_SUNDAY;
    /**
     * A month range, the week starting on Monday.
     */
    public static final int RANGE_MONTH_MONDAY = DateUtils.RANGE_MONTH_MONDAY;


    //-----------------------------------------------------------------------

    /**
     * <p>Checks if two date objects are on the same day ignoring time.</p>
     * <p/>
     * <p>28 Mar 2002 13:45 and 28 Mar 2002 06:01 would return true.
     * 28 Mar 2002 13:45 and 12 Mar 2002 13:45 would return false.
     * </p>
     *
     * @param date1 the first date, not altered, not null
     * @param date2 the second date, not altered, not null
     * @return true if they represent the same day
     * @throws IllegalArgumentException if either date is <code>null</code>
     * @since 2.1
     */
    public boolean isSameDay(final Date date1, final Date date2) {
        return DateUtils.isSameDay(date1, date2);
    }

    /**
     * <p>Checks if two calendar objects are on the same day ignoring time.</p>
     * <p/>
     * <p>28 Mar 2002 13:45 and 28 Mar 2002 06:01 would return true.
     * 28 Mar 2002 13:45 and 12 Mar 2002 13:45 would return false.
     * </p>
     *
     * @param cal1 the first calendar, not altered, not null
     * @param cal2 the second calendar, not altered, not null
     * @return true if they represent the same day
     * @throws IllegalArgumentException if either calendar is <code>null</code>
     * @since 2.1
     */
    public boolean isSameDay(final Calendar cal1, final Calendar cal2) {
        return DateUtils.isSameDay(cal1, cal2);
    }

    //-----------------------------------------------------------------------

    /**
     * <p>Checks if two date objects represent the same instant in time.</p>
     * <p/>
     * <p>This method compares the long millisecond time of the two objects.</p>
     *
     * @param date1 the first date, not altered, not null
     * @param date2 the second date, not altered, not null
     * @return true if they represent the same millisecond instant
     * @throws IllegalArgumentException if either date is <code>null</code>
     * @since 2.1
     */
    public boolean isSameInstant(final Date date1, final Date date2) {
        return DateUtils.isSameInstant(date1, date2);
    }

    /**
     * <p>Checks if two calendar objects represent the same instant in time.</p>
     * <p/>
     * <p>This method compares the long millisecond time of the two objects.</p>
     *
     * @param cal1 the first calendar, not altered, not null
     * @param cal2 the second calendar, not altered, not null
     * @return true if they represent the same millisecond instant
     * @throws IllegalArgumentException if either date is <code>null</code>
     * @since 2.1
     */
    public boolean isSameInstant(final Calendar cal1, final Calendar cal2) {
        return DateUtils.isSameInstant(cal1, cal2);
    }

    //-----------------------------------------------------------------------

    /**
     * <p>Checks if two calendar objects represent the same local time.</p>
     * <p/>
     * <p>This method compares the values of the fields of the two objects.
     * In addition, both calendars must be the same of the same type.</p>
     *
     * @param cal1 the first calendar, not altered, not null
     * @param cal2 the second calendar, not altered, not null
     * @return true if they represent the same millisecond instant
     * @throws IllegalArgumentException if either date is <code>null</code>
     * @since 2.1
     */
    public boolean isSameLocalTime(final Calendar cal1, final Calendar cal2) {
        return DateUtils.isSameLocalTime(cal1, cal2);
    }

    //-----------------------------------------------------------------------

    /**
     * <p>Parses a string representing a date by trying a variety of different parsers.</p>
     * <p/>
     * <p>The parse will try each parse pattern in turn.
     * A parse is only deemed successful if it parses the whole of the input string.
     * If no parse patterns match, a ParseException is thrown.</p>
     * The parser will be lenient toward the parsed date.
     *
     * @param str           the date to parse, not null
     * @param parsePatterns the date format patterns to use, see SimpleDateFormat, not null
     * @return the parsed date
     * @throws IllegalArgumentException if the date string or pattern array is null
     * @throws ParseException if none of the date patterns were suitable (or there were none)
     */
    public Date parseDate(final String str, final String... parsePatterns) throws ParseException {
        return DateUtils.parseDate(str, parsePatterns);
    }

    //-----------------------------------------------------------------------

    /**
     * <p>Parses a string representing a date by trying a variety of different parsers,
     * using the default date format symbols for the given locale.</p>
     * <p/>
     * <p>The parse will try each parse pattern in turn.
     * A parse is only deemed successful if it parses the whole of the input string.
     * If no parse patterns match, a ParseException is thrown.</p>
     * The parser will be lenient toward the parsed date.
     *
     * @param str           the date to parse, not null
     * @param locale        the locale whose date format symbols should be used. If <code>null</code>,
     *                      the system locale is used (as per {@link #parseDate(String, String...)}).
     * @param parsePatterns the date format patterns to use, see SimpleDateFormat, not null
     * @return the parsed date
     * @throws IllegalArgumentException if the date string or pattern array is null
     * @throws ParseException if none of the date patterns were suitable (or there were none)
     * @since 3.2
     */
    public Date parseDate(final String str, final Locale locale,
                          final String... parsePatterns) throws ParseException {
        return DateUtils.parseDate(str, locale, parsePatterns);
    }

    //-----------------------------------------------------------------------

    /**
     * <p>Parses a string representing a date by trying a variety of different parsers.</p>
     * <p/>
     * <p>The parse will try each parse pattern in turn.
     * A parse is only deemed successful if it parses the whole of the input string.
     * If no parse patterns match, a ParseException is thrown.</p>
     * The parser parses strictly - it does not allow for dates such as "February 942, 1996".
     *
     * @param str           the date to parse, not null
     * @param parsePatterns the date format patterns to use, see SimpleDateFormat, not null
     * @return the parsed date
     * @throws IllegalArgumentException if the date string or pattern array is null
     * @throws ParseException if none of the date patterns were suitable
     * @since 2.5
     */
    public Date parseDateStrictly(final String str, final String... parsePatterns) throws ParseException {
        return DateUtils.parseDateStrictly(str, parsePatterns);
    }

    /**
     * <p>Parses a string representing a date by trying a variety of different parsers,
     * using the default date format symbols for the given locale..</p>
     * <p/>
     * <p>The parse will try each parse pattern in turn.
     * A parse is only deemed successful if it parses the whole of the input string.
     * If no parse patterns match, a ParseException is thrown.</p>
     * The parser parses strictly - it does not allow for dates such as "February 942, 1996".
     *
     * @param str           the date to parse, not null
     * @param locale        the locale whose date format symbols should be used. If <code>null</code>,
     *                      the system locale is used (as per {@link #parseDateStrictly(String, String...)}).
     * @param parsePatterns the date format patterns to use, see SimpleDateFormat, not null
     * @return the parsed date
     * @throws IllegalArgumentException if the date string or pattern array is null
     * @throws ParseException if none of the date patterns were suitable
     * @since 3.2
     */
    public Date parseDateStrictly(final String str, final Locale locale,
                                  final String... parsePatterns) throws ParseException {
        return DateUtils.parseDateStrictly(str, locale, parsePatterns);
    }


    //-----------------------------------------------------------------------

    /**
     * Adds a number of years to a date returning a new object.
     * The original {@code Date} is unchanged.
     *
     * @param date   the date, not null
     * @param amount the amount to add, may be negative
     * @return the new {@code Date} with the amount added
     * @throws IllegalArgumentException if the date is null
     */
    public Date addYears(final Date date, final int amount) {
        return DateUtils.addYears(date, amount);
    }

    //-----------------------------------------------------------------------

    /**
     * Adds a number of months to a date returning a new object.
     * The original {@code Date} is unchanged.
     *
     * @param date   the date, not null
     * @param amount the amount to add, may be negative
     * @return the new {@code Date} with the amount added
     * @throws IllegalArgumentException if the date is null
     */
    public Date addMonths(final Date date, final int amount) {
        return DateUtils.addMonths(date, amount);
    }

    //-----------------------------------------------------------------------

    /**
     * Adds a number of weeks to a date returning a new object.
     * The original {@code Date} is unchanged.
     *
     * @param date   the date, not null
     * @param amount the amount to add, may be negative
     * @return the new {@code Date} with the amount added
     * @throws IllegalArgumentException if the date is null
     */
    public Date addWeeks(final Date date, final int amount) {
        return DateUtils.addWeeks(date, amount);
    }

    //-----------------------------------------------------------------------

    /**
     * Adds a number of days to a date returning a new object.
     * The original {@code Date} is unchanged.
     *
     * @param date   the date, not null
     * @param amount the amount to add, may be negative
     * @return the new {@code Date} with the amount added
     * @throws IllegalArgumentException if the date is null
     */
    public Date addDays(final Date date, final int amount) {
        return DateUtils.addDays(date, amount);
    }

    //-----------------------------------------------------------------------

    /**
     * Adds a number of hours to a date returning a new object.
     * The original {@code Date} is unchanged.
     *
     * @param date   the date, not null
     * @param amount the amount to add, may be negative
     * @return the new {@code Date} with the amount added
     * @throws IllegalArgumentException if the date is null
     */
    public Date addHours(final Date date, final int amount) {
        return DateUtils.addHours(date, amount);
    }

    //-----------------------------------------------------------------------

    /**
     * Adds a number of minutes to a date returning a new object.
     * The original {@code Date} is unchanged.
     *
     * @param date   the date, not null
     * @param amount the amount to add, may be negative
     * @return the new {@code Date} with the amount added
     * @throws IllegalArgumentException if the date is null
     */
    public Date addMinutes(final Date date, final int amount) {
        return DateUtils.addMinutes(date, amount);
    }

    //-----------------------------------------------------------------------

    /**
     * Adds a number of seconds to a date returning a new object.
     * The original {@code Date} is unchanged.
     *
     * @param date   the date, not null
     * @param amount the amount to add, may be negative
     * @return the new {@code Date} with the amount added
     * @throws IllegalArgumentException if the date is null
     */
    public Date addSeconds(final Date date, final int amount) {
        return DateUtils.addSeconds(date, amount);
    }

    //-----------------------------------------------------------------------

    /**
     * Adds a number of milliseconds to a date returning a new object.
     * The original {@code Date} is unchanged.
     *
     * @param date   the date, not null
     * @param amount the amount to add, may be negative
     * @return the new {@code Date} with the amount added
     * @throws IllegalArgumentException if the date is null
     */
    public Date addMilliseconds(final Date date, final int amount) {
        return DateUtils.addMilliseconds(date, amount);
    }

    //-----------------------------------------------------------------------


    /**
     * Sets the years field to a date returning a new object.
     * The original {@code Date} is unchanged.
     *
     * @param date   the date, not null
     * @param amount the amount to set
     * @return a new {@code Date} set with the specified value
     * @throws IllegalArgumentException if the date is null
     * @since 2.4
     */
    public Date setYears(final Date date, final int amount) {
        return DateUtils.setYears(date, amount);
    }

    //-----------------------------------------------------------------------

    /**
     * Sets the months field to a date returning a new object.
     * The original {@code Date} is unchanged.
     *
     * @param date   the date, not null
     * @param amount the amount to set
     * @return a new {@code Date} set with the specified value
     * @throws IllegalArgumentException if the date is null
     * @since 2.4
     */
    public Date setMonths(final Date date, final int amount) {
        return DateUtils.setMonths(date, amount);
    }

    //-----------------------------------------------------------------------

    /**
     * Sets the day of month field to a date returning a new object.
     * The original {@code Date} is unchanged.
     *
     * @param date   the date, not null
     * @param amount the amount to set
     * @return a new {@code Date} set with the specified value
     * @throws IllegalArgumentException if the date is null
     * @since 2.4
     */
    public Date setDays(final Date date, final int amount) {
        return DateUtils.setDays(date, amount);
    }

    //-----------------------------------------------------------------------

    /**
     * Sets the hours field to a date returning a new object.  Hours range
     * from  0-23.
     * The original {@code Date} is unchanged.
     *
     * @param date   the date, not null
     * @param amount the amount to set
     * @return a new {@code Date} set with the specified value
     * @throws IllegalArgumentException if the date is null
     * @since 2.4
     */
    public Date setHours(final Date date, final int amount) {
        return DateUtils.setHours(date, amount);
    }

    //-----------------------------------------------------------------------

    /**
     * Sets the minute field to a date returning a new object.
     * The original {@code Date} is unchanged.
     *
     * @param date   the date, not null
     * @param amount the amount to set
     * @return a new {@code Date} set with the specified value
     * @throws IllegalArgumentException if the date is null
     * @since 2.4
     */
    public Date setMinutes(final Date date, final int amount) {
        return DateUtils.setMinutes(date, amount);
    }

    //-----------------------------------------------------------------------

    /**
     * Sets the seconds field to a date returning a new object.
     * The original {@code Date} is unchanged.
     *
     * @param date   the date, not null
     * @param amount the amount to set
     * @return a new {@code Date} set with the specified value
     * @throws IllegalArgumentException if the date is null
     * @since 2.4
     */
    public Date setSeconds(final Date date, final int amount) {
        return DateUtils.setSeconds(date, amount);
    }

    //-----------------------------------------------------------------------

    /**
     * Sets the miliseconds field to a date returning a new object.
     * The original {@code Date} is unchanged.
     *
     * @param date   the date, not null
     * @param amount the amount to set
     * @return a new {@code Date} set with the specified value
     * @throws IllegalArgumentException if the date is null
     * @since 2.4
     */
    public Date setMilliseconds(final Date date, final int amount) {
        return DateUtils.setMilliseconds(date, amount);
    }

    /**
     * Converts a {@code Date} into a {@code Calendar}.
     *
     * @param date the date to convert to a Calendar
     * @return the created Calendar
     * @throws NullPointerException if null is passed in
     * @since 3.0
     */
    public Calendar toCalendar(final Date date) {
        return DateUtils.toCalendar(date);
    }

    //-----------------------------------------------------------------------

    /**
     * <p>Rounds a date, leaving the field specified as the most
     * significant field.</p>
     * <p/>
     * <p>For example, if you had the date-time of 28 Mar 2002
     * 13:45:01.231, if this was passed with HOUR, it would return
     * 28 Mar 2002 14:00:00.000. If this was passed with MONTH, it
     * would return 1 April 2002 0:00:00.000.</p>
     * <p/>
     * <p>For a date in a timezone that handles the change to daylight
     * saving time, rounding to Calendar.HOUR_OF_DAY will behave as follows.
     * Suppose daylight saving time begins at 02:00 on March 30. Rounding a
     * date that crosses this time would produce the following values:
     * </p>
     * <ul>
     * <li>March 30, 2003 01:10 rounds to March 30, 2003 01:00</li>
     * <li>March 30, 2003 01:40 rounds to March 30, 2003 03:00</li>
     * <li>March 30, 2003 02:10 rounds to March 30, 2003 03:00</li>
     * <li>March 30, 2003 02:40 rounds to March 30, 2003 04:00</li>
     * </ul>
     *
     * @param date  the date to work with, not null
     * @param field the field from {@code Calendar} or {@code SEMI_MONTH}
     * @return the different rounded date, not null
     * @throws ArithmeticException if the year is over 280 million
     */
    public Date round(final Date date, final int field) {
        return DateUtils.round(date, field);
    }

    /**
     * <p>Rounds a date, leaving the field specified as the most
     * significant field.</p>
     * <p/>
     * <p>For example, if you had the date-time of 28 Mar 2002
     * 13:45:01.231, if this was passed with HOUR, it would return
     * 28 Mar 2002 14:00:00.000. If this was passed with MONTH, it
     * would return 1 April 2002 0:00:00.000.</p>
     * <p/>
     * <p>For a date in a timezone that handles the change to daylight
     * saving time, rounding to Calendar.HOUR_OF_DAY will behave as follows.
     * Suppose daylight saving time begins at 02:00 on March 30. Rounding a
     * date that crosses this time would produce the following values:
     * </p>
     * <ul>
     * <li>March 30, 2003 01:10 rounds to March 30, 2003 01:00</li>
     * <li>March 30, 2003 01:40 rounds to March 30, 2003 03:00</li>
     * <li>March 30, 2003 02:10 rounds to March 30, 2003 03:00</li>
     * <li>March 30, 2003 02:40 rounds to March 30, 2003 04:00</li>
     * </ul>
     *
     * @param date  the date to work with, not null
     * @param field the field from {@code Calendar} or <code>SEMI_MONTH</code>
     * @return the different rounded date, not null
     * @throws IllegalArgumentException if the date is <code>null</code>
     * @throws ArithmeticException      if the year is over 280 million
     */
    public Calendar round(final Calendar date, final int field) {
        return DateUtils.round(date, field);
    }

    /**
     * <p>Rounds a date, leaving the field specified as the most
     * significant field.</p>
     * <p/>
     * <p>For example, if you had the date-time of 28 Mar 2002
     * 13:45:01.231, if this was passed with HOUR, it would return
     * 28 Mar 2002 14:00:00.000. If this was passed with MONTH, it
     * would return 1 April 2002 0:00:00.000.</p>
     * <p/>
     * <p>For a date in a timezone that handles the change to daylight
     * saving time, rounding to Calendar.HOUR_OF_DAY will behave as follows.
     * Suppose daylight saving time begins at 02:00 on March 30. Rounding a
     * date that crosses this time would produce the following values:
     * </p>
     * <ul>
     * <li>March 30, 2003 01:10 rounds to March 30, 2003 01:00</li>
     * <li>March 30, 2003 01:40 rounds to March 30, 2003 03:00</li>
     * <li>March 30, 2003 02:10 rounds to March 30, 2003 03:00</li>
     * <li>March 30, 2003 02:40 rounds to March 30, 2003 04:00</li>
     * </ul>
     *
     * @param date  the date to work with, either {@code Date} or {@code Calendar}, not null
     * @param field the field from {@code Calendar} or <code>SEMI_MONTH</code>
     * @return the different rounded date, not null
     * @throws IllegalArgumentException if the date is <code>null</code>
     * @throws ClassCastException       if the object type is not a {@code Date} or {@code Calendar}
     * @throws ArithmeticException      if the year is over 280 million
     */
    public Date round(final Object date, final int field) {
        return DateUtils.round(date, field);
    }

    //-----------------------------------------------------------------------

    /**
     * <p>Truncates a date, leaving the field specified as the most
     * significant field.</p>
     * <p/>
     * <p>For example, if you had the date-time of 28 Mar 2002
     * 13:45:01.231, if you passed with HOUR, it would return 28 Mar
     * 2002 13:00:00.000.  If this was passed with MONTH, it would
     * return 1 Mar 2002 0:00:00.000.</p>
     *
     * @param date  the date to work with, not null
     * @param field the field from {@code Calendar} or <code>SEMI_MONTH</code>
     * @return the different truncated date, not null
     * @throws IllegalArgumentException if the date is <code>null</code>
     * @throws ArithmeticException      if the year is over 280 million
     */
    public Date truncate(final Date date, final int field) {
        return DateUtils.truncate(date, field);
    }

    /**
     * <p>Truncates a date, leaving the field specified as the most
     * significant field.</p>
     * <p/>
     * <p>For example, if you had the date-time of 28 Mar 2002
     * 13:45:01.231, if you passed with HOUR, it would return 28 Mar
     * 2002 13:00:00.000.  If this was passed with MONTH, it would
     * return 1 Mar 2002 0:00:00.000.</p>
     *
     * @param date  the date to work with, not null
     * @param field the field from {@code Calendar} or <code>SEMI_MONTH</code>
     * @return the different truncated date, not null
     * @throws IllegalArgumentException if the date is <code>null</code>
     * @throws ArithmeticException      if the year is over 280 million
     */
    public Calendar truncate(final Calendar date, final int field) {
        return DateUtils.truncate(date, field);
    }

    /**
     * <p>Truncates a date, leaving the field specified as the most
     * significant field.</p>
     * <p/>
     * <p>For example, if you had the date-time of 28 Mar 2002
     * 13:45:01.231, if you passed with HOUR, it would return 28 Mar
     * 2002 13:00:00.000.  If this was passed with MONTH, it would
     * return 1 Mar 2002 0:00:00.000.</p>
     *
     * @param date  the date to work with, either {@code Date} or {@code Calendar}, not null
     * @param field the field from {@code Calendar} or <code>SEMI_MONTH</code>
     * @return the different truncated date, not null
     * @throws IllegalArgumentException if the date is <code>null</code>
     * @throws ClassCastException       if the object type is not a {@code Date} or {@code Calendar}
     * @throws ArithmeticException      if the year is over 280 million
     */
    public Date truncate(final Object date, final int field) {
        return DateUtils.truncate(date, field);
    }

    //-----------------------------------------------------------------------

    /**
     * <p>Gets a date ceiling, leaving the field specified as the most
     * significant field.</p>
     * <p/>
     * <p>For example, if you had the date-time of 28 Mar 2002
     * 13:45:01.231, if you passed with HOUR, it would return 28 Mar
     * 2002 14:00:00.000.  If this was passed with MONTH, it would
     * return 1 Apr 2002 0:00:00.000.</p>
     *
     * @param date  the date to work with, not null
     * @param field the field from {@code Calendar} or <code>SEMI_MONTH</code>
     * @return the different ceil date, not null
     * @throws IllegalArgumentException if the date is <code>null</code>
     * @throws ArithmeticException      if the year is over 280 million
     * @since 2.5
     */
    public Date ceiling(final Date date, final int field) {
        return DateUtils.ceiling(date, field);
    }

    /**
     * <p>Gets a date ceiling, leaving the field specified as the most
     * significant field.</p>
     * <p/>
     * <p>For example, if you had the date-time of 28 Mar 2002
     * 13:45:01.231, if you passed with HOUR, it would return 28 Mar
     * 2002 14:00:00.000.  If this was passed with MONTH, it would
     * return 1 Apr 2002 0:00:00.000.</p>
     *
     * @param date  the date to work with, not null
     * @param field the field from {@code Calendar} or <code>SEMI_MONTH</code>
     * @return the different ceil date, not null
     * @throws IllegalArgumentException if the date is <code>null</code>
     * @throws ArithmeticException      if the year is over 280 million
     * @since 2.5
     */
    public Calendar ceiling(final Calendar date, final int field) {
        return DateUtils.ceiling(date, field);
    }

    /**
     * <p>Gets a date ceiling, leaving the field specified as the most
     * significant field.</p>
     * <p/>
     * <p>For example, if you had the date-time of 28 Mar 2002
     * 13:45:01.231, if you passed with HOUR, it would return 28 Mar
     * 2002 14:00:00.000.  If this was passed with MONTH, it would
     * return 1 Apr 2002 0:00:00.000.</p>
     *
     * @param date  the date to work with, either {@code Date} or {@code Calendar}, not null
     * @param field the field from {@code Calendar} or <code>SEMI_MONTH</code>
     * @return the different ceil date, not null
     * @throws IllegalArgumentException if the date is <code>null</code>
     * @throws ClassCastException       if the object type is not a {@code Date} or {@code Calendar}
     * @throws ArithmeticException      if the year is over 280 million
     * @since 2.5
     */
    public Date ceiling(final Object date, final int field) {
        return DateUtils.ceiling(date, field);
    }

    /**
     * <p>Constructs an <code>Iterator</code> over each day in a date
     * range defined by a focus date and range style.</p>
     * <p/>
     * <p>For instance, passing Thursday, July 4, 2002 and a
     * <code>RANGE_MONTH_SUNDAY</code> will return an <code>Iterator</code>
     * that starts with Sunday, June 30, 2002 and ends with Saturday, August 3,
     * 2002, returning a Calendar instance for each intermediate day.</p>
     * <p/>
     * <p>This method provides an iterator that returns Calendar objects.
     * The days are progressed using {@link Calendar#add(int, int)}.</p>
     *
     * @param focus      the date to work with, not null
     * @param rangeStyle the style constant to use. Must be one of
     *                   {@link DateUtils#RANGE_MONTH_SUNDAY},
     *                   {@link DateUtils#RANGE_MONTH_MONDAY},
     *                   {@link DateUtils#RANGE_WEEK_SUNDAY},
     *                   {@link DateUtils#RANGE_WEEK_MONDAY},
     *                   {@link DateUtils#RANGE_WEEK_RELATIVE},
     *                   {@link DateUtils#RANGE_WEEK_CENTER}
     * @return the date iterator, not null, not null
     * @throws IllegalArgumentException if the date is <code>null</code>
     * @throws IllegalArgumentException if the rangeStyle is invalid
     */
    public Iterator<Calendar> iterator(final Date focus, final int rangeStyle) {
        return DateUtils.iterator(focus, rangeStyle);
    }

    /**
     * <p>Constructs an <code>Iterator</code> over each day in a date
     * range defined by a focus date and range style.</p>
     * <p/>
     * <p>For instance, passing Thursday, July 4, 2002 and a
     * <code>RANGE_MONTH_SUNDAY</code> will return an <code>Iterator</code>
     * that starts with Sunday, June 30, 2002 and ends with Saturday, August 3,
     * 2002, returning a Calendar instance for each intermediate day.</p>
     * <p/>
     * <p>This method provides an iterator that returns Calendar objects.
     * The days are progressed using {@link Calendar#add(int, int)}.</p>
     *
     * @param focus      the date to work with, not null
     * @param rangeStyle the style constant to use. Must be one of
     *                   {@link DateUtils#RANGE_MONTH_SUNDAY},
     *                   {@link DateUtils#RANGE_MONTH_MONDAY},
     *                   {@link DateUtils#RANGE_WEEK_SUNDAY},
     *                   {@link DateUtils#RANGE_WEEK_MONDAY},
     *                   {@link DateUtils#RANGE_WEEK_RELATIVE},
     *                   {@link DateUtils#RANGE_WEEK_CENTER}
     * @return the date iterator, not null
     * @throws IllegalArgumentException if the date is <code>null</code>
     * @throws IllegalArgumentException if the rangeStyle is invalid
     */
    public Iterator<Calendar> iterator(final Calendar focus, final int rangeStyle) {
        return DateUtils.iterator(focus, rangeStyle);
    }

    /**
     * <p>Constructs an <code>Iterator</code> over each day in a date
     * range defined by a focus date and range style.</p>
     * <p/>
     * <p>For instance, passing Thursday, July 4, 2002 and a
     * <code>RANGE_MONTH_SUNDAY</code> will return an <code>Iterator</code>
     * that starts with Sunday, June 30, 2002 and ends with Saturday, August 3,
     * 2002, returning a Calendar instance for each intermediate day.</p>
     *
     * @param focus      the date to work with, either {@code Date} or {@code Calendar}, not null
     * @param rangeStyle the style constant to use. Must be one of the range
     *                   styles listed for the {@link #iterator(Calendar, int)} method.
     * @return the date iterator, not null
     * @throws IllegalArgumentException if the date is <code>null</code>
     * @throws ClassCastException       if the object type is not a {@code Date} or {@code Calendar}
     */
    public Iterator<?> iterator(final Object focus, final int rangeStyle) {
        return DateUtils.iterator(focus, rangeStyle);
    }

    /**
     * <p>Returns the number of milliseconds within the
     * fragment. All datefields greater than the fragment will be ignored.</p>
     * <p/>
     * <p>Asking the milliseconds of any date will only return the number of milliseconds
     * of the current second (resulting in a number between 0 and 999). This
     * method will retrieve the number of milliseconds for any fragment.
     * For example, if you want to calculate the number of milliseconds past today,
     * your fragment is Calendar.DATE or Calendar.DAY_OF_YEAR. The result will
     * be all milliseconds of the past hour(s), minutes(s) and second(s).</p>
     * <p/>
     * <p>Valid fragments are: Calendar.YEAR, Calendar.MONTH, both
     * Calendar.DAY_OF_YEAR and Calendar.DATE, Calendar.HOUR_OF_DAY,
     * Calendar.MINUTE, Calendar.SECOND and Calendar.MILLISECOND
     * A fragment less than or equal to a SECOND field will return 0.</p>
     * <p/>
     * <ul>
     * <li>January 1, 2008 7:15:10.538 with Calendar.SECOND as fragment will return 538</li>
     * <li>January 6, 2008 7:15:10.538 with Calendar.SECOND as fragment will return 538</li>
     * <li>January 6, 2008 7:15:10.538 with Calendar.MINUTE as fragment will return 10538 (10*1000 + 538)</li>
     * <li>January 16, 2008 7:15:10.538 with Calendar.MILLISECOND as fragment will return 0
     * (a millisecond cannot be split in milliseconds)</li>
     * </ul>
     *
     * @param date     the date to work with, not null
     * @param fragment the {@code Calendar} field part of date to calculate
     * @return number of milliseconds within the fragment of date
     * @throws IllegalArgumentException if the date is <code>null</code> or
     *                                  fragment is not supported
     * @since 2.4
     */
    public long getFragmentInMilliseconds(final Date date, final int fragment) {
        return DateUtils.getFragmentInMilliseconds(date, fragment);
    }

    /**
     * <p>Returns the number of seconds within the
     * fragment. All datefields greater than the fragment will be ignored.</p>
     * <p/>
     * <p>Asking the seconds of any date will only return the number of seconds
     * of the current minute (resulting in a number between 0 and 59). This
     * method will retrieve the number of seconds for any fragment.
     * For example, if you want to calculate the number of seconds past today,
     * your fragment is Calendar.DATE or Calendar.DAY_OF_YEAR. The result will
     * be all seconds of the past hour(s) and minutes(s).</p>
     * <p/>
     * <p>Valid fragments are: Calendar.YEAR, Calendar.MONTH, both
     * Calendar.DAY_OF_YEAR and Calendar.DATE, Calendar.HOUR_OF_DAY,
     * Calendar.MINUTE, Calendar.SECOND and Calendar.MILLISECOND
     * A fragment less than or equal to a SECOND field will return 0.</p>
     * <p/>
     * <ul>
     * <li>January 1, 2008 7:15:10.538 with Calendar.MINUTE as fragment will return 10
     * (equivalent to deprecated date.getSeconds())</li>
     * <li>January 6, 2008 7:15:10.538 with Calendar.MINUTE as fragment will return 10
     * (equivalent to deprecated date.getSeconds())</li>
     * <li>January 6, 2008 7:15:10.538 with Calendar.DAY_OF_YEAR as fragment will return 26110
     * (7*3600 + 15*60 + 10)</li>
     * <li>January 16, 2008 7:15:10.538 with Calendar.MILLISECOND as fragment will return 0
     * (a millisecond cannot be split in seconds)</li>
     * </ul>
     *
     * @param date     the date to work with, not null
     * @param fragment the {@code Calendar} field part of date to calculate
     * @return number of seconds within the fragment of date
     * @throws IllegalArgumentException if the date is <code>null</code> or
     *                                  fragment is not supported
     * @since 2.4
     */
    public long getFragmentInSeconds(final Date date, final int fragment) {
        return DateUtils.getFragmentInSeconds(date, fragment);
    }

    /**
     * <p>Returns the number of minutes within the
     * fragment. All datefields greater than the fragment will be ignored.</p>
     * <p/>
     * <p>Asking the minutes of any date will only return the number of minutes
     * of the current hour (resulting in a number between 0 and 59). This
     * method will retrieve the number of minutes for any fragment.
     * For example, if you want to calculate the number of minutes past this month,
     * your fragment is Calendar.MONTH. The result will be all minutes of the
     * past day(s) and hour(s).</p>
     * <p/>
     * <p>Valid fragments are: Calendar.YEAR, Calendar.MONTH, both
     * Calendar.DAY_OF_YEAR and Calendar.DATE, Calendar.HOUR_OF_DAY,
     * Calendar.MINUTE, Calendar.SECOND and Calendar.MILLISECOND
     * A fragment less than or equal to a MINUTE field will return 0.</p>
     * <p/>
     * <ul>
     * <li>January 1, 2008 7:15:10.538 with Calendar.HOUR_OF_DAY as fragment will return 15
     * (equivalent to deprecated date.getMinutes())</li>
     * <li>January 6, 2008 7:15:10.538 with Calendar.HOUR_OF_DAY as fragment will return 15
     * (equivalent to deprecated date.getMinutes())</li>
     * <li>January 1, 2008 7:15:10.538 with Calendar.MONTH as fragment will return 15</li>
     * <li>January 6, 2008 7:15:10.538 with Calendar.MONTH as fragment will return 435 (7*60 + 15)</li>
     * <li>January 16, 2008 7:15:10.538 with Calendar.MILLISECOND as fragment will return 0
     * (a millisecond cannot be split in minutes)</li>
     * </ul>
     *
     * @param date     the date to work with, not null
     * @param fragment the {@code Calendar} field part of date to calculate
     * @return number of minutes within the fragment of date
     * @throws IllegalArgumentException if the date is <code>null</code> or
     *                                  fragment is not supported
     * @since 2.4
     */
    public long getFragmentInMinutes(final Date date, final int fragment) {
        return DateUtils.getFragmentInMinutes(date, fragment);
    }

    /**
     * <p>Returns the number of hours within the
     * fragment. All datefields greater than the fragment will be ignored.</p>
     * <p/>
     * <p>Asking the hours of any date will only return the number of hours
     * of the current day (resulting in a number between 0 and 23). This
     * method will retrieve the number of hours for any fragment.
     * For example, if you want to calculate the number of hours past this month,
     * your fragment is Calendar.MONTH. The result will be all hours of the
     * past day(s).</p>
     * <p/>
     * <p>Valid fragments are: Calendar.YEAR, Calendar.MONTH, both
     * Calendar.DAY_OF_YEAR and Calendar.DATE, Calendar.HOUR_OF_DAY,
     * Calendar.MINUTE, Calendar.SECOND and Calendar.MILLISECOND
     * A fragment less than or equal to a HOUR field will return 0.</p>
     * <p/>
     * <ul>
     * <li>January 1, 2008 7:15:10.538 with Calendar.DAY_OF_YEAR as fragment will return 7
     * (equivalent to deprecated date.getHours())</li>
     * <li>January 6, 2008 7:15:10.538 with Calendar.DAY_OF_YEAR as fragment will return 7
     * (equivalent to deprecated date.getHours())</li>
     * <li>January 1, 2008 7:15:10.538 with Calendar.MONTH as fragment will return 7</li>
     * <li>January 6, 2008 7:15:10.538 with Calendar.MONTH as fragment will return 127 (5*24 + 7)</li>
     * <li>January 16, 2008 7:15:10.538 with Calendar.MILLISECOND as fragment will return 0
     * (a millisecond cannot be split in hours)</li>
     * </ul>
     *
     * @param date     the date to work with, not null
     * @param fragment the {@code Calendar} field part of date to calculate
     * @return number of hours within the fragment of date
     * @throws IllegalArgumentException if the date is <code>null</code> or
     *                                  fragment is not supported
     * @since 2.4
     */
    public long getFragmentInHours(final Date date, final int fragment) {
        return DateUtils.getFragmentInHours(date, fragment);
    }

    /**
     * <p>Returns the number of days within the
     * fragment. All datefields greater than the fragment will be ignored.</p>
     * <p/>
     * <p>Asking the days of any date will only return the number of days
     * of the current month (resulting in a number between 1 and 31). This
     * method will retrieve the number of days for any fragment.
     * For example, if you want to calculate the number of days past this year,
     * your fragment is Calendar.YEAR. The result will be all days of the
     * past month(s).</p>
     * <p/>
     * <p>Valid fragments are: Calendar.YEAR, Calendar.MONTH, both
     * Calendar.DAY_OF_YEAR and Calendar.DATE, Calendar.HOUR_OF_DAY,
     * Calendar.MINUTE, Calendar.SECOND and Calendar.MILLISECOND
     * A fragment less than or equal to a DAY field will return 0.</p>
     * <p/>
     * <ul>
     * <li>January 28, 2008 with Calendar.MONTH as fragment will return 28
     * (equivalent to deprecated date.getDay())</li>
     * <li>February 28, 2008 with Calendar.MONTH as fragment will return 28
     * (equivalent to deprecated date.getDay())</li>
     * <li>January 28, 2008 with Calendar.YEAR as fragment will return 28</li>
     * <li>February 28, 2008 with Calendar.YEAR as fragment will return 59</li>
     * <li>January 28, 2008 with Calendar.MILLISECOND as fragment will return 0
     * (a millisecond cannot be split in days)</li>
     * </ul>
     *
     * @param date     the date to work with, not null
     * @param fragment the {@code Calendar} field part of date to calculate
     * @return number of days  within the fragment of date
     * @throws IllegalArgumentException if the date is <code>null</code> or
     *                                  fragment is not supported
     * @since 2.4
     */
    public long getFragmentInDays(final Date date, final int fragment) {
        return DateUtils.getFragmentInDays(date, fragment);
    }

    /**
     * <p>Returns the number of milliseconds within the
     * fragment. All datefields greater than the fragment will be ignored.</p>
     * <p/>
     * <p>Asking the milliseconds of any date will only return the number of milliseconds
     * of the current second (resulting in a number between 0 and 999). This
     * method will retrieve the number of milliseconds for any fragment.
     * For example, if you want to calculate the number of seconds past today,
     * your fragment is Calendar.DATE or Calendar.DAY_OF_YEAR. The result will
     * be all seconds of the past hour(s), minutes(s) and second(s).</p>
     * <p/>
     * <p>Valid fragments are: Calendar.YEAR, Calendar.MONTH, both
     * Calendar.DAY_OF_YEAR and Calendar.DATE, Calendar.HOUR_OF_DAY,
     * Calendar.MINUTE, Calendar.SECOND and Calendar.MILLISECOND
     * A fragment less than or equal to a MILLISECOND field will return 0.</p>
     * <p/>
     * <ul>
     * <li>January 1, 2008 7:15:10.538 with Calendar.SECOND as fragment will return 538
     * (equivalent to calendar.get(Calendar.MILLISECOND))</li>
     * <li>January 6, 2008 7:15:10.538 with Calendar.SECOND as fragment will return 538
     * (equivalent to calendar.get(Calendar.MILLISECOND))</li>
     * <li>January 6, 2008 7:15:10.538 with Calendar.MINUTE as fragment will return 10538
     * (10*1000 + 538)</li>
     * <li>January 16, 2008 7:15:10.538 with Calendar.MILLISECOND as fragment will return 0
     * (a millisecond cannot be split in milliseconds)</li>
     * </ul>
     *
     * @param calendar the calendar to work with, not null
     * @param fragment the {@code Calendar} field part of calendar to calculate
     * @return number of milliseconds within the fragment of date
     * @throws IllegalArgumentException if the date is <code>null</code> or
     *                                  fragment is not supported
     * @since 2.4
     */
    public long getFragmentInMilliseconds(final Calendar calendar, final int fragment) {
        return DateUtils.getFragmentInMilliseconds(calendar, fragment);
    }

    /**
     * <p>Returns the number of seconds within the
     * fragment. All datefields greater than the fragment will be ignored.</p>
     * <p/>
     * <p>Asking the seconds of any date will only return the number of seconds
     * of the current minute (resulting in a number between 0 and 59). This
     * method will retrieve the number of seconds for any fragment.
     * For example, if you want to calculate the number of seconds past today,
     * your fragment is Calendar.DATE or Calendar.DAY_OF_YEAR. The result will
     * be all seconds of the past hour(s) and minutes(s).</p>
     * <p/>
     * <p>Valid fragments are: Calendar.YEAR, Calendar.MONTH, both
     * Calendar.DAY_OF_YEAR and Calendar.DATE, Calendar.HOUR_OF_DAY,
     * Calendar.MINUTE, Calendar.SECOND and Calendar.MILLISECOND
     * A fragment less than or equal to a SECOND field will return 0.</p>
     * <p/>
     * <ul>
     * <li>January 1, 2008 7:15:10.538 with Calendar.MINUTE as fragment will return 10
     * (equivalent to calendar.get(Calendar.SECOND))</li>
     * <li>January 6, 2008 7:15:10.538 with Calendar.MINUTE as fragment will return 10
     * (equivalent to calendar.get(Calendar.SECOND))</li>
     * <li>January 6, 2008 7:15:10.538 with Calendar.DAY_OF_YEAR as fragment will return 26110
     * (7*3600 + 15*60 + 10)</li>
     * <li>January 16, 2008 7:15:10.538 with Calendar.MILLISECOND as fragment will return 0
     * (a millisecond cannot be split in seconds)</li>
     * </ul>
     *
     * @param calendar the calendar to work with, not null
     * @param fragment the {@code Calendar} field part of calendar to calculate
     * @return number of seconds within the fragment of date
     * @throws IllegalArgumentException if the date is <code>null</code> or
     *                                  fragment is not supported
     * @since 2.4
     */
    public long getFragmentInSeconds(final Calendar calendar, final int fragment) {
        return DateUtils.getFragmentInSeconds(calendar, fragment);
    }

    /**
     * <p>Returns the number of minutes within the
     * fragment. All datefields greater than the fragment will be ignored.</p>
     * <p/>
     * <p>Asking the minutes of any date will only return the number of minutes
     * of the current hour (resulting in a number between 0 and 59). This
     * method will retrieve the number of minutes for any fragment.
     * For example, if you want to calculate the number of minutes past this month,
     * your fragment is Calendar.MONTH. The result will be all minutes of the
     * past day(s) and hour(s).</p>
     * <p/>
     * <p>Valid fragments are: Calendar.YEAR, Calendar.MONTH, both
     * Calendar.DAY_OF_YEAR and Calendar.DATE, Calendar.HOUR_OF_DAY,
     * Calendar.MINUTE, Calendar.SECOND and Calendar.MILLISECOND
     * A fragment less than or equal to a MINUTE field will return 0.</p>
     * <p/>
     * <ul>
     * <li>January 1, 2008 7:15:10.538 with Calendar.HOUR_OF_DAY as fragment will return 15
     * (equivalent to calendar.get(Calendar.MINUTES))</li>
     * <li>January 6, 2008 7:15:10.538 with Calendar.HOUR_OF_DAY as fragment will return 15
     * (equivalent to calendar.get(Calendar.MINUTES))</li>
     * <li>January 1, 2008 7:15:10.538 with Calendar.MONTH as fragment will return 15</li>
     * <li>January 6, 2008 7:15:10.538 with Calendar.MONTH as fragment will return 435 (7*60 + 15)</li>
     * <li>January 16, 2008 7:15:10.538 with Calendar.MILLISECOND as fragment will return 0
     * (a millisecond cannot be split in minutes)</li>
     * </ul>
     *
     * @param calendar the calendar to work with, not null
     * @param fragment the {@code Calendar} field part of calendar to calculate
     * @return number of minutes within the fragment of date
     * @throws IllegalArgumentException if the date is <code>null</code> or
     *                                  fragment is not supported
     * @since 2.4
     */
    public long getFragmentInMinutes(final Calendar calendar, final int fragment) {
        return DateUtils.getFragmentInMinutes(calendar, fragment);
    }

    /**
     * <p>Returns the number of hours within the
     * fragment. All datefields greater than the fragment will be ignored.</p>
     * <p/>
     * <p>Asking the hours of any date will only return the number of hours
     * of the current day (resulting in a number between 0 and 23). This
     * method will retrieve the number of hours for any fragment.
     * For example, if you want to calculate the number of hours past this month,
     * your fragment is Calendar.MONTH. The result will be all hours of the
     * past day(s).</p>
     * <p/>
     * <p>Valid fragments are: Calendar.YEAR, Calendar.MONTH, both
     * Calendar.DAY_OF_YEAR and Calendar.DATE, Calendar.HOUR_OF_DAY,
     * Calendar.MINUTE, Calendar.SECOND and Calendar.MILLISECOND
     * A fragment less than or equal to a HOUR field will return 0.</p>
     * <p/>
     * <ul>
     * <li>January 1, 2008 7:15:10.538 with Calendar.DAY_OF_YEAR as fragment will return 7
     * (equivalent to calendar.get(Calendar.HOUR_OF_DAY))</li>
     * <li>January 6, 2008 7:15:10.538 with Calendar.DAY_OF_YEAR as fragment will return 7
     * (equivalent to calendar.get(Calendar.HOUR_OF_DAY))</li>
     * <li>January 1, 2008 7:15:10.538 with Calendar.MONTH as fragment will return 7</li>
     * <li>January 6, 2008 7:15:10.538 with Calendar.MONTH as fragment will return 127 (5*24 + 7)</li>
     * <li>January 16, 2008 7:15:10.538 with Calendar.MILLISECOND as fragment will return 0
     * (a millisecond cannot be split in hours)</li>
     * </ul>
     *
     * @param calendar the calendar to work with, not null
     * @param fragment the {@code Calendar} field part of calendar to calculate
     * @return number of hours within the fragment of date
     * @throws IllegalArgumentException if the date is <code>null</code> or
     *                                  fragment is not supported
     * @since 2.4
     */
    public long getFragmentInHours(final Calendar calendar, final int fragment) {
        return DateUtils.getFragmentInHours(calendar, fragment);
    }

    /**
     * <p>Returns the number of days within the
     * fragment. All datefields greater than the fragment will be ignored.</p>
     * <p/>
     * <p>Asking the days of any date will only return the number of days
     * of the current month (resulting in a number between 1 and 31). This
     * method will retrieve the number of days for any fragment.
     * For example, if you want to calculate the number of days past this year,
     * your fragment is Calendar.YEAR. The result will be all days of the
     * past month(s).</p>
     * <p/>
     * <p>Valid fragments are: Calendar.YEAR, Calendar.MONTH, both
     * Calendar.DAY_OF_YEAR and Calendar.DATE, Calendar.HOUR_OF_DAY,
     * Calendar.MINUTE, Calendar.SECOND and Calendar.MILLISECOND
     * A fragment less than or equal to a DAY field will return 0.</p>
     * <p/>
     * <ul>
     * <li>January 28, 2008 with Calendar.MONTH as fragment will return 28
     * (equivalent to calendar.get(Calendar.DAY_OF_MONTH))</li>
     * <li>February 28, 2008 with Calendar.MONTH as fragment will return 28
     * (equivalent to calendar.get(Calendar.DAY_OF_MONTH))</li>
     * <li>January 28, 2008 with Calendar.YEAR as fragment will return 28
     * (equivalent to calendar.get(Calendar.DAY_OF_YEAR))</li>
     * <li>February 28, 2008 with Calendar.YEAR as fragment will return 59
     * (equivalent to calendar.get(Calendar.DAY_OF_YEAR))</li>
     * <li>January 28, 2008 with Calendar.MILLISECOND as fragment will return 0
     * (a millisecond cannot be split in days)</li>
     * </ul>
     *
     * @param calendar the calendar to work with, not null
     * @param fragment the {@code Calendar} field part of calendar to calculate
     * @return number of days within the fragment of date
     * @throws IllegalArgumentException if the date is <code>null</code> or
     *                                  fragment is not supported
     * @since 2.4
     */
    public long getFragmentInDays(final Calendar calendar, final int fragment) {
        return DateUtils.getFragmentInDays(calendar, fragment);
    }


    /**
     * Determines if two calendars are equal up to no more than the specified
     * most significant field.
     *
     * @param cal1  the first calendar, not <code>null</code>
     * @param cal2  the second calendar, not <code>null</code>
     * @param field the field from {@code Calendar}
     * @return <code>true</code> if equal; otherwise <code>false</code>
     * @throws IllegalArgumentException if any argument is <code>null</code>
     * @see #truncate(Calendar, int)
     * @see #truncatedEquals(Date, Date, int)
     * @since 3.0
     */
    public boolean truncatedEquals(final Calendar cal1, final Calendar cal2, final int field) {
        return DateUtils.truncatedEquals(cal1, cal2, field);
    }

    /**
     * Determines if two dates are equal up to no more than the specified
     * most significant field.
     *
     * @param date1 the first date, not <code>null</code>
     * @param date2 the second date, not <code>null</code>
     * @param field the field from {@code Calendar}
     * @return <code>true</code> if equal; otherwise <code>false</code>
     * @throws IllegalArgumentException if any argument is <code>null</code>
     * @see #truncate(Date, int)
     * @see #truncatedEquals(Calendar, Calendar, int)
     * @since 3.0
     */
    public boolean truncatedEquals(final Date date1, final Date date2, final int field) {
        return DateUtils.truncatedEquals(date1, date2, field);
    }

    /**
     * Determines how two calendars compare up to no more than the specified
     * most significant field.
     *
     * @param cal1  the first calendar, not <code>null</code>
     * @param cal2  the second calendar, not <code>null</code>
     * @param field the field from {@code Calendar}
     * @return a negative integer, zero, or a positive integer as the first
     * calendar is less than, equal to, or greater than the second.
     * @throws IllegalArgumentException if any argument is <code>null</code>
     * @see #truncate(Calendar, int)
     * @see #truncatedCompareTo(Date, Date, int)
     * @since 3.0
     */
    public int truncatedCompareTo(final Calendar cal1, final Calendar cal2, final int field) {
        return DateUtils.truncatedCompareTo(cal1, cal2, field);
    }

    /**
     * Determines how two dates compare up to no more than the specified
     * most significant field.
     *
     * @param date1 the first date, not <code>null</code>
     * @param date2 the second date, not <code>null</code>
     * @param field the field from <code>Calendar</code>
     * @return a negative integer, zero, or a positive integer as the first
     * date is less than, equal to, or greater than the second.
     * @throws IllegalArgumentException if any argument is <code>null</code>
     * @see #truncate(Calendar, int)
     * @see #truncatedCompareTo(Date, Date, int)
     * @since 3.0
     */
    public int truncatedCompareTo(final Date date1, final Date date2, final int field) {
        return DateUtils.truncatedCompareTo(date1, date2, field);
    }

    public int getDayOfWeek(Calendar calendar) {
        return calendar.get(Calendar.DAY_OF_WEEK);
    }

    public int getDayOfWeek(Date date) {
        return getDayOfWeek(toCalendar(date));
    }

    public boolean isWeekend(Calendar calendar) {
        int dayOfWeek = getDayOfWeek(calendar);
        return dayOfWeek == Calendar.SATURDAY || dayOfWeek == Calendar.SUNDAY;
    }

    public boolean isWeekend(Date date) {
        return isWeekend(toCalendar(date));
    }

    public boolean isMondayToFriday(Calendar calendar) {
        return !isWeekend(calendar);
    }

    public boolean isMondayToFriday(Date date) {
        return !isWeekend(date);
    }

    public String format(String patten, Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(patten);
        return simpleDateFormat.format(date);
    }
}
