/**
 * FlightManager: this is a class that implements some operations related to flight management
 * 
 * @author Yichao Xue <yichaox>
 * @section B
 * 
 */

// You may not import any additional classes or packages.

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class FlightManager {

	public ArrayList<Flight> flights = new ArrayList<Flight>();

	// Use this method to test your code. Completely remove the main method
	// when you're done.

	
	// Do not change this method.
	public FlightManager() {
		loadFlights();
	}

	/**
	 * Loads the flight data using the given specification. You must use the
	 * specification provided in the write-up in order to receive full credit.
	 */
	private void loadFlights() {
		String filename = "flights.txt";

		try {
			Scanner sc = new Scanner(new File(filename));
			while (sc.hasNextLine()) {
				String line = sc.nextLine();
				// Look up the String.split method in the Java API to
				// understand how it works!
				String[] splitLine = line.split("[|]");

				/* initialize a new Flight object */
				Flight flight = new Flight(splitLine[0], splitLine[1],
						splitLine[2], splitLine[3], splitLine[4],
						Integer.valueOf(splitLine[5]));

				flights.add(flight); // add new flight to flights list

			}
		} catch (FileNotFoundException fnfe) {
			System.out.println("I could not load the file. Please make sure "
					+ "the file is in the correct directory.");
			System.exit(0);
		} catch (Exception e) {
			System.out.println("Error while creating the flights:");
			e.printStackTrace();
			System.exit(0);
		}
	}

	/**
	 * Returns the flights ArrayList.
	 * 
	 * @return ArrayList<Flight>
	 */
	public ArrayList<Flight> getFlights() {
		return flights;
	}

	/**
	 * Finds and returns all Flights that depart from the given ICAO airport
	 * code. This method must run in O(n) time. For example (your data may be
	 * different): Flights f = new Flights(); f.getFlightsDepartingFrom("PIT");
	 * // could return [DL8273, WN2834, WN5243]
	 * 
	 * @param airport
	 *            - the ICAO airport code to search for
	 * @return - an ArrayList of Flight objects that depart from the given ICAO
	 *         code
	 */
	public ArrayList<Flight> getFlightsDepartingFrom(String airport) {
		ArrayList<Flight> temp = new ArrayList<Flight>();

		for (int i = 0; i < flights.size(); i++) {
			/* add flights whose departure airport are the same with airport */
			if (flights.get(i).getDepartureAirport().equals(airport)) {
				temp.add(flights.get(i));
			}
		}
		return temp;
	}

	/**
	 * Finds and returns all Flights that arrive at the given ICAO airport code.
	 * This method must run in O(n) time. For example (your data may be
	 * different): Flights f = new Flights(); f.getFlightsArriveAt("LAS"); //
	 * could return [WN2834, WN5243]
	 * 
	 * @param airport
	 *            - the ICAO airport code to search for
	 * @return - an ArrayList of Flights that arrive at the given airport
	 */
	public ArrayList<Flight> getFlightsArrivingAt(String airport) {
		/* same implementation with getFlightsDepartingFrom */
		ArrayList<Flight> temp = new ArrayList<Flight>();

		for (int i = 0; i < flights.size(); i++) {
			if (flights.get(i).getArrivalAirport().equals(airport)) {
				temp.add(flights.get(i));
			}
		}
		return temp;
	}

	/**
	 * Finds and returns an ArrayList<Flight> representing all direct Flights
	 * starting in startAirport and ending at endAirport, grouped by their
	 * unique identifier. For example: Flights f = new Flights();
	 * f.getFlightsAlongPath("PIT", "LAS"); // could return [WN2834, WN5243]
	 * 
	 * @param departureAirport
	 *            - the ICAO code of the departure airport
	 * @param arrivalAirport
	 *            - the ICAO code of the arrival airport
	 * @return - an ArrayList<Flight> that are non-stop
	 */
	public ArrayList<Flight> getNonStopFlights(String departureAirport,
			String arrivalAirport) {
		/*
		 * temp is a ArrayList<Flight> which contains all fights depart from
		 * departureAirport
		 */
		ArrayList<Flight> temp = getFlightsDepartingFrom(departureAirport);

		/*
		 * remove those flights whose arrival Airport doesn't match
		 * arrivalAirport in temp
		 */
		for (int i = temp.size() - 1; i >= 0; i--) {
			if (!temp.get(i).getArrivalAirport().equals(arrivalAirport)) {
				temp.remove(i);
			}
		}
		return temp;
	}

	/**
	 * Cancels all flights that stop in the given airport. You must deepCopy the
	 * global flights ArrayList and then remove any flights that ever exist in
	 * at a given airport.
	 * 
	 * @param airport
	 *            - the airport that has canceled all flights
	 * @return - an ArrayList<Flight> that are still able to fly
	 */
	public ArrayList<Flight> cancelFlights(String airport) {
		ArrayList<Flight> temp = deepCopy(flights);

		for (int i = temp.size() - 1; i >= 0; i--) {
			/* remove flights whose departure or arrival airports match airport */
			if (temp.get(i).getDepartureAirport().equals(airport)
					|| temp.get(i).getArrivalAirport().equals(airport)) {
				temp.remove(i);
			}
		}
		return temp;
	}

	// Extra credit method. If you don't want to implement this method,
	// just leave it as is.

	/**
	 * The flaw of cancelFlights is this method can only cancel the flight which
	 * stops at airport, it can not cancel all the flights in a flight path.
	 * 
	 * For example, flight SB911 is from A to B to C to D, if all the flights at
	 * B are canceled, cancelFlights can only cancel fight from A to B and
	 * flight from B to C, it cannot cancel flight from C to D. In real
	 * situation, all these three flights in flight path should be canceled.
	 * 
	 * @param airport
	 *            - the airport that has canceled all flights
	 * @return - an ArrayList<Flight> that are still able to fly
	 */
	public ArrayList<Flight> cancelFlightsCorrectly(String airport) {
		ArrayList<Flight> temp = deepCopy(flights);

		for (int i = temp.size() - 1; i >= 0; i--) {
			if (temp.get(i).getDepartureAirport().equals(airport)
					|| temp.get(i).getArrivalAirport().equals(airport)) {
				/* find the flight ID which is canceled at airport */
				String flightId = temp.get(i).getIdentifier();

				/* remove the entire path when flight identifier equals flightId */
				for (int j = temp.size() - 1; j >= 0; j--) {
					if (temp.get(j).getIdentifier().equals(flightId)) {
						temp.remove(j);
					}
				}
				i = temp.size(); // update i;
			}
		}
		return temp;
	}

	/**
	 * Calculates the total distance traveled for all given flightIdentifiers
	 * 
	 * @param flightIdentifiers
	 *            - an ArrayList<String> representing the flightIdentifiers to
	 *            search for
	 * @return - the total distance (in miles) traveled
	 */
	public int getTotalDistance(ArrayList<String> flightIdentifiers) {
		int distance = 0;

		for (int i = 0; i < flightIdentifiers.size(); i++) {
			/* calculate the total distance for a certain path */
			for (int j = 0; j < flights.size(); j++) {
				if (flights.get(j).getIdentifier()
						.equals(flightIdentifiers.get(i))) {
					distance += flights.get(j).getDistance();
				}
			}
		}

		return distance;
	}

	/**
	 * Arranges the flights in an ArrayList<ArrayList<Flight>>. Each
	 * ArrayList<ArrayList> represents a unique flight path. Each
	 * ArrayList<ArrayList<Flight>> represents a collection of the flight path,
	 * in the same order as the input file.
	 */
	public ArrayList<ArrayList<Flight>> arrangedFlights() {
		ArrayList<ArrayList<Flight>> arrangedFlights = new ArrayList<ArrayList<Flight>>();
		ArrayList<Flight> path = new ArrayList<Flight>();

		path.add(flights.get(0)); // add the first flight to a new path

		for (int i = 1; i < flights.size(); i++) {
			/*
			 * if the current flight is a new flight, add the previous path to
			 * the arrangedFlights, then create a new path
			 */
			if (!flights.get(i - 1).getIdentifier()
					.equals(flights.get(i).getIdentifier())) {
				arrangedFlights.add(path);
				path = new ArrayList<Flight>();
			}

			path.add(flights.get(i)); // add current flight to path
		}

		arrangedFlights.add(path); // add last path

		return arrangedFlights;
	}

	/**
	 * Returns an ArrayList<ArrayList<Flight>> corresponding to all flights that
	 * start at departureAirport and end at arrivalArrival airport and have at
	 * least 1 stop in between.
	 * 
	 * HINT: You may find it helpful to use the arrangedFlights() method.
	 * 
	 * @param departureAirport
	 *            - the ICAO code of the departure airport
	 * @param arrivalAirport
	 *            - the ICAO code of the arrival airport
	 * @return - an organized list of all multi-hop flights
	 */
	public ArrayList<ArrayList<Flight>> getMultiHopFlights(
			String departureAirport, String arrivalAirport) {

		ArrayList<ArrayList<Flight>> result = new ArrayList<ArrayList<Flight>>();
		ArrayList<ArrayList<Flight>> arrangedFlights = arrangedFlights();

		for (int i = 0; i < arrangedFlights.size(); i++) {
			ArrayList<Flight> path = arrangedFlights.get(i);

			/* skip non-stop flights */
			if (path.size() > 1) {
				ArrayList<Flight> temp = new ArrayList<Flight>();
				for (int j = 0; j < path.size(); j++) {
					if (path.get(j).getDepartureAirport()
							.equals(departureAirport)) {
						/* find the start flight, add it to a temp ArrayList */
						temp.add(path.get(j));
						for (int k = j + 1; k < path.size(); k++) {
							/* add the next flight to ArrayList */
							temp.add(path.get(k));
							if (path.get(k).getArrivalAirport()
									.equals(arrivalAirport)) {
								/*
								 * find the flight has the same arrivalAirport,
								 * then add the current temp ArrayList of
								 * flights to result
								 */
								ArrayList<Flight> tmp = new ArrayList<Flight>();
								tmp = deepCopy(temp);
								result.add(tmp);
							}
						}
					}
				}
			}
		}

		return result;
	}

	/**
	 * Returns an ArrayList<Flight> of all the flights that depart from the
	 * given airport in the AM (i.e. between 0000 and 1159, midnight and noon).
	 * This method must run in O(n) time.
	 * 
	 * @param departureAirport
	 *            - the ICAO code of the departure airport
	 * @return - a list of all the departing AM flights from the airport
	 */
	public ArrayList<Flight> morningDepartingFlights(String departureAirport) {
		ArrayList<Flight> morningFlights = new ArrayList<Flight>();

		for (int i = 0; i < flights.size(); i++) {
			if (flights.get(i).getDepartureAirport().equals(departureAirport)
					&& Integer.valueOf(flights.get(i).getDepartureTime()) >= 0
					&& Integer.valueOf(flights.get(i).getDepartureTime()) < 1200) {
				morningFlights.add(flights.get(i));
			}
		}
		return morningFlights;
	}

	/**
	 * Returns an ArrayList<Flight> of all the flights that arrive at the given
	 * airport in the PM (i.e. between 1200 and 2359, noon and midnight). This
	 * method must run in O(n) time.
	 * 
	 * @param arrivalAirport
	 *            - the ICAO code of the arrival airport
	 * @return - a list of all the departing PM flights from the airport
	 */
	public ArrayList<Flight> afternoonArrivingFlights(String arrivalAirport) {
		ArrayList<Flight> afternoonFlights = new ArrayList<Flight>();

		for (int i = 0; i < flights.size(); i++) {
			if (flights.get(i).getArrivalAirport().equals(arrivalAirport)
					&& Integer.valueOf(flights.get(i).getDepartureTime()) >= 1200
					&& Integer.valueOf(flights.get(i).getDepartureTime()) < 2359) {
				afternoonFlights.add(flights.get(i));
			}
		}
		return afternoonFlights;
	}

	/**
	 * Returns an ArrayList<Flight> of Flight(s) that go from the specified
	 * departure airport to the specified arrival airport in the fewest number
	 * of miles. The shortest flight path can be nonstop or multi-hop. If there
	 * is no flight path that goes between the two specified airports, return an
	 * empty List.
	 * 
	 * @param departureAirport
	 *            - the ICAO code of the departure airport
	 * @param arrivalAirport
	 *            - the ICAO code of the arrival airport
	 * @return - a list of the Flights in the shortest flight path, in order
	 */
	public ArrayList<Flight> shortestFlight(String departureAirport,
			String arrivalAirport) {
		ArrayList<ArrayList<Flight>> multiHopFlights = getMultiHopFlights(
				departureAirport, arrivalAirport);
		ArrayList<Flight> nonStopFlights = getNonStopFlights(departureAirport,
				arrivalAirport);
		ArrayList<Flight> result = multiHopFlights.get(0);

		int minDistance = 0;
		/* calculate initial minimum distance */
		for (int j = 0; j < result.size(); j++) {
			minDistance += result.get(j).getDistance();
		}

		/* find minimum distance in multi hop flights */
		for (int i = 0; i < multiHopFlights.size(); i++) {
			ArrayList<Flight> path = multiHopFlights.get(i);

			/* temp distance of a path for comparing */
			int tempDis = 0;
			/* calculate the total distance for a path */
			for (int k = 0; k < path.size(); k++) {
				tempDis += path.get(k).getDistance();
			}

			if (minDistance > tempDis) {
				minDistance = tempDis;
				result = path;
			}
		}

		/* find minimum distance in non-stop flights */
		for (int m = 0; m < nonStopFlights.size(); m++) {
			if (minDistance > nonStopFlights.get(m).getDistance()) {
				minDistance = nonStopFlights.get(m).getDistance();
				/* clear result and add a new flight with minimum distance */
				result.clear();
				result.add(nonStopFlights.get(m));
			}
		}
		return result;
	}

	/**
	 * deepCopies an ArrayList<Flight>
	 * 
	 * @param theFlights
	 * @return - a new ArrayList<Flight> containing the same Flights in the same
	 *         order as the given ArrayList<Flight>
	 */
	private ArrayList<Flight> deepCopy(ArrayList<Flight> theFlights) {
		ArrayList<Flight> newFlights = new ArrayList<Flight>();

		for (int i = 0; i < theFlights.size(); i++) {
			newFlights.add(theFlights.get(i));
		}

		return newFlights;
	}
}
