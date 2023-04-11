package com.rollonit.convert.helper;

import java.util.EnumSet;

/**
 * This class is used to convert values from one unit of measurement to another.
 */
public final class Converter {

    static EnumSet<Unit> lengthUnits = EnumSet.of(Unit.METER, Unit.KILOMETER, Unit.CENTIMETER, Unit.MILLIMETER, Unit.MILE, Unit.YARD, Unit.FOOT, Unit.INCH, Unit.NAUTICAL_MILE);
    static EnumSet<Unit> weightUnits = EnumSet.of(Unit.GRAM, Unit.KILOGRAM, Unit.MILLIGRAM, Unit.OUNCE, Unit.POUND);
    static EnumSet<Unit> volumeUnits = EnumSet.of(Unit.LITER, Unit.MILLILITER, Unit.GALLON, Unit.QUART, Unit.PINT, Unit.CUP, Unit.TABLESPOON, Unit.TEASPOON, Unit.FLUID_OUNCE);
    static EnumSet<Unit> temperatureUnits = EnumSet.of(Unit.CELSIUS, Unit.FAHRENHEIT, Unit.KELVIN);
    static EnumSet<Unit> timeUnits = EnumSet.of(Unit.SECOND, Unit.MINUTE, Unit.HOUR, Unit.DAY, Unit.WEEK, Unit.MONTH, Unit.YEAR);


    /**
     * This method is used to convert the input value from one unit of measurement to another.
     * If the units are not compatible, 0 is returned.
     *
     * @param value The value to be converted.
     * @param from  The unit of measurement that the value is currently in.
     * @param to    The unit of measurement that the value should be converted to.
     * @return The converted value.
     */
    public static double convert(double value, Unit from, Unit to) {
        if (lengthUnits.contains(from) && lengthUnits.contains(to)) {
            return convertLength(value, from, to);
        } else if (weightUnits.contains(from) && weightUnits.contains(to)) {
            return convertWeight(value, from, to);
        } else if (volumeUnits.contains(from) && volumeUnits.contains(to)) {
            return convertVolume(value, from, to);
        } else if (temperatureUnits.contains(from) && temperatureUnits.contains(to)) {
            return convertTemperature(value, from, to);
        } else if (timeUnits.contains(from) && timeUnits.contains(to)) {
            return convertTime(value, from, to);
        } else {
            return 0;
        }
    }

    /**
     * This method is used to convert the display text form of a unit to its corresponding Unit enum value.
     * The text must be one of the values in the android unit string arrays in strings.xml.
     * If the text does not match any of the above, null is returned.
     * If the text is null or empty, null is returned.
     * If the text is not null or empty, but does not match any of the above, null is returned.
     *
     * @param text The display text of the unit.
     * @return The Unit enum value that corresponds to the display text or null if the text does not match any of the above.
     */
    public static Unit convertTextToUnit(String text) {
        if (text == null || text.isEmpty()) {
            return null;
        }

        switch (text) {
            case "Meters":
                return Unit.METER;
            case "Kilometers":
                return Unit.KILOMETER;
            case "Centimeters":
                return Unit.CENTIMETER;
            case "Millimeters":
                return Unit.MILLIMETER;
            case "Miles":
                return Unit.MILE;
            case "Yards":
                return Unit.YARD;
            case "Feet":
                return Unit.FOOT;
            case "Inches":
                return Unit.INCH;
            case "Nautical Miles":
                return Unit.NAUTICAL_MILE;
            case "Grams":
                return Unit.GRAM;
            case "Kilograms":
                return Unit.KILOGRAM;
            case "Milligrams":
                return Unit.MILLIGRAM;
            case "Ounces":
                return Unit.OUNCE;
            case "Pounds":
                return Unit.POUND;
            case "Liters":
                return Unit.LITER;
            case "Milliliters":
                return Unit.MILLILITER;
            case "Gallons":
                return Unit.GALLON;
            case "Quarts":
                return Unit.QUART;
            case "Pints":
                return Unit.PINT;
            case "Fluid Ounces":
                return Unit.FLUID_OUNCE;
            case "Cups":
                return Unit.CUP;
            case "Tablespoons":
                return Unit.TABLESPOON;
            case "Teaspoons":
                return Unit.TEASPOON;
            case "Celsius":
                return Unit.CELSIUS;
            case "Fahrenheit":
                return Unit.FAHRENHEIT;
            case "Kelvin":
                return Unit.KELVIN;
            case "Seconds":
                return Unit.SECOND;
            case "Minutes":
                return Unit.MINUTE;
            case "Hours":
                return Unit.HOUR;
            case "Days":
                return Unit.DAY;
            case "Weeks":
                return Unit.WEEK;
            case "Months":
                return Unit.MONTH;
            case "Years":
                return Unit.YEAR;
            default:
                return null;
        }
    }

    /**
     * This method is used to convert the input value from one unit of length to another.
     * The value is first converted to meters and then to the desired unit.
     * If the from or to unit is not a length unit, 0 is returned.
     * If the from or to unit is null, 0 is returned.
     *
     * @param value The value to be converted.
     * @param from  The unit of measurement that the value is currently in.
     * @param to    The unit of measurement that the value should be converted to.
     * @return The converted value.
     */
    private static double convertLength(double value, Unit from, Unit to) {
        // If the from or to unit is not a length unit, return 0
        if (from == null || to == null || !lengthUnits.contains(from) || !lengthUnits.contains(to)) {
            return 0;
        }
        double meters = 0;

        // Convert the value to meters
        switch (from) {
            case METER:
                meters = value;
                break;
            case KILOMETER:
                meters = value * 1000;
                break;
            case CENTIMETER:
                meters = value / 100;
                break;
            case MILLIMETER:
                meters = value / 1000;
                break;
            case MILE:
                meters = value * 1609.344;
                break;
            case YARD:
                meters = value * 0.9144;
                break;
            case FOOT:
                meters = value * 0.3048;
                break;
            case INCH:
                meters = value * 0.0254;
                break;
            case NAUTICAL_MILE:
                meters = value * 1852;
                break;
        }

        // Convert and return the value from meters to the desired unit
        switch (to) {
            case METER:
                return meters;
            case KILOMETER:
                return meters / 1000;
            case CENTIMETER:
                return meters * 100;
            case MILLIMETER:
                return meters * 1000;
            case MILE:
                return meters / 1609.344;
            case YARD:
                return meters / 0.9144;
            case FOOT:
                return meters / 0.3048;
            case INCH:
                return meters / 0.0254;
            case NAUTICAL_MILE:
                return meters / 1852;
        }

        return 0;
    }

    /**
     * This method is used to convert the input value from one unit of weight to another.
     * The value is first converted to grams and then to the desired unit.
     * If the from or to unit is not a weight unit, 0 is returned.
     * If the from or to unit is null, 0 is returned.
     *
     * @param value The value to be converted.
     * @param from  The unit of measurement that the value is currently in.
     * @param to    The unit of measurement that the value should be converted to.
     * @return The converted value.
     */
    private static double convertWeight(double value, Unit from, Unit to) {
        // If the from or to unit is not a weight unit, return 0
        if (from == null || to == null || !weightUnits.contains(from) || !weightUnits.contains(to)) {
            return 0;
        }
        double grams = 0;

        // Convert the value to grams
        switch (from) {
            case GRAM:
                grams = value;
                break;
            case KILOGRAM:
                grams = value * 1000;
                break;
            case MILLIGRAM:
                grams = value / 1000;
                break;
            case OUNCE:
                grams = value * 28.349523125;
                break;
            case POUND:
                grams = value * 453.59237;
                break;
        }

        // Convert and return the value from grams to the desired unit
        switch (to) {
            case GRAM:
                return grams;
            case KILOGRAM:
                return grams / 1000;
            case MILLIGRAM:
                return grams * 1000;
            case OUNCE:
                return grams / 28.349523125;
            case POUND:
                return grams / 453.59237;
        }

        return 0;
    }

    /**
     * This method is used to convert the input value from one unit of volume to another.
     * The value is first converted to liters and then to the desired unit.
     * If the from or to unit is not a volume unit, 0 is returned.
     * If the from or to unit is null, 0 is returned.
     *
     * @param value The value to be converted.
     * @param from  The unit of measurement that the value is currently in.
     * @param to    The unit of measurement that the value should be converted to.
     * @return The converted value.
     */
    private static double convertVolume(double value, Unit from, Unit to) {
        // If the from or to unit is not a volume unit, return 0
        if (from == null || to == null || !volumeUnits.contains(from) || !volumeUnits.contains(to)) {
            return 0;
        }
        double liters = 0;

        // Convert the value to liters
        switch (from) {
            case LITER:
                liters = value;
                break;
            case MILLILITER:
                liters = value / 1000;
                break;
            case GALLON:
                liters = value * 3.785411784;
                break;
            case QUART:
                liters = value * 0.946352946;
                break;
            case PINT:
                liters = value * 0.473176473;
                break;
            case FLUID_OUNCE:
                liters = value * 0.0295735295625;
                break;
            case CUP:
                liters = value * 0.2365882365;
                break;
            case TABLESPOON:
                liters = value * 0.01478676478125;
                break;
            case TEASPOON:
                liters = value * 0.00492892159375;
                break;
        }

        // Convert and return the value from liters to the desired unit
        switch (to) {
            case LITER:
                return liters;
            case MILLILITER:
                return liters * 1000;
            case GALLON:
                return liters / 3.785411784;
            case QUART:
                return liters / 0.946352946;
            case PINT:
                return liters / 0.473176473;
            case FLUID_OUNCE:
                return liters / 0.0295735295625;
            case CUP:
                return liters / 0.2365882365;
            case TABLESPOON:
                return liters / 0.01478676478125;
            case TEASPOON:
                return liters / 0.00492892159375;
        }

        return 0;
    }

    /**
     * This method is used to convert the input value from one unit of temperature to another.
     * The value is first converted to Kelvin and then to the desired unit.
     * If the from or to unit is not a temperature unit, 0 is returned.
     * If the from or to unit is null, 0 is returned.
     *
     * @param value The value to be converted.
     * @param from  The unit of measurement that the value is currently in.
     * @param to    The unit of measurement that the value should be converted to.
     * @return The converted value.
     */
    private static double convertTemperature(double value, Unit from, Unit to) {
        // If the from or to unit is not a temperature unit, return 0
        if (from == null || to == null || !temperatureUnits.contains(from) || !temperatureUnits.contains(to)) {
            return 0;
        }
        double kelvin = 0;

        // Convert the value to kelvin
        switch (from) {
            case KELVIN:
                kelvin = value;
                break;
            case CELSIUS:
                kelvin = value + 273.15;
                break;
            case FAHRENHEIT:
                kelvin = (value + 459.67) * 5 / 9;
                break;
        }

        // Convert and return the value from kelvin to the desired unit
        switch (to) {
            case KELVIN:
                return kelvin;
            case CELSIUS:
                return kelvin - 273.15;
            case FAHRENHEIT:
                return kelvin * 9 / 5 - 459.67;
        }

        return 0;
    }

    /**
     * This method is used to convert the input value from one unit of time to another.
     * The value is first converted to seconds and then to the desired unit.
     * If the from or to unit is not a time unit, 0 is returned.
     * If the from or to unit is null, 0 is returned.
     *
     * @param value The value to be converted.
     * @param from  The unit of measurement that the value is currently in.
     * @param to    The unit of measurement that the value should be converted to.
     * @return The converted value.
     */
    private static double convertTime(double value, Unit from, Unit to) {
        // If the from or to unit is not a time unit, return 0
        if (from == null || to == null || !timeUnits.contains(from) || !timeUnits.contains(to)) {
            return 0;
        }
        double seconds = 0;

        // Convert the value to seconds
        switch (from) {
            case SECOND:
                seconds = value;
                break;
            case MINUTE:
                seconds = value * 60;
                break;
            case HOUR:
                seconds = value * 3600;
                break;
            case DAY:
                seconds = value * 86400;
                break;
            case WEEK:
                seconds = value * 604800;
                break;
            case MONTH:
                seconds = value * 2629746;
                break;
            case YEAR:
                seconds = value * 31556952;
                break;
        }

        // Convert and return the value from seconds to the desired unit
        switch (to) {
            case SECOND:
                return seconds;
            case MINUTE:
                return seconds / 60;
            case HOUR:
                return seconds / 3600;
            case DAY:
                return seconds / 86400;
            case WEEK:
                return seconds / 604800;
            case MONTH:
                return seconds / 2629746;
            case YEAR:
                return seconds / 31556952;
        }

        return 0;
    }
}
