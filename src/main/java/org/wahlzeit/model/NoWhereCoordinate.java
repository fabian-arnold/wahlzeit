/*
 * Copyright (c) 2006-2017 by Fabian Arnold
 *
 * This file is part of the Wahlzeit photo rating application.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public
 * License along with this program. If not, see
 * <http://www.gnu.org/licenses/>.
 *
 */

package org.wahlzeit.model;

/**
 * A coordinate which is nowhere and can never be reached
 */
public class NoWhereCoordinate extends AbstractCoordinate {

    private static final int NOWHERE_HASH_CODE = 948234134;

    /**
     * Creates a new no where coordinate
     */
    public NoWhereCoordinate() {

    }

    /**
     * Calculates the distance to target.
     * Returns always the positive infinity
     * @param target The target the distance to is calculated
     */
    @Override
    public double getDistance(Coordinate target) {
        // nowhere is infinite far away we could use the implementation
        // of the base class but this gives more performance
        return Double.POSITIVE_INFINITY;
    }

    @Override
    public boolean equals(Object o) {
        // we can always return false cause nowhere is nowhere
        return false;
    }

    @Override
    public int hashCode() {
        // we return a hash code so we don't break a hashmap or something else
        return NOWHERE_HASH_CODE;
    }

    @Override
    public String toString() {
        return "NoWhereCoordinate{}";
    }
}
