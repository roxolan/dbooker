# dbooker

## Problem: Double Booked

When maintaining a calendar of events, it is important to know if an event overlaps with another event.
Given a sequence of events, each having a start and end time, write a program that will return the sequence of all pairs of overlapping events.

## Solution

The data structure of the calendar is presumed to be a sequence of vectors holding integers < 12
denoting starting and ending time of the event (starting time always being smaller than ending)
The first version of solution doesn't use external dependencies, the second requires clojure.math.combinatorics
Leiningen project structure is necessary in order to enable the second option.
