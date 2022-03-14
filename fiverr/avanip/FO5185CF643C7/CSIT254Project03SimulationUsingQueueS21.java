package csit254project03simulationusingqueues21;

/**
 * Project 3 - simulation using Queues
 * @author Michael Main, Stephen Brower, and ???
 */
public class CSIT254Project03SimulationUsingQueueS21 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // settings/declarations
        int currentSecond;                          // for a specific second in simulation
        final int MAX_TIME_FOR_SIMULATION = 90 * 60;  // 90 minutes

        // probability of arrival for a second
        // based on 20 customers an hour
        final double PROBABILITY_OF_ARRIVAL = 250.0 / (60 * 60);
        final int MAX_TIME_TO_CHECKOUT = 5 * 60;      // maximum time to process "customer"
        int numCustomersArrived = 0;                // counter for number of customers that arrived

        Server[] regularServer = new Server[10];        // each server has their own Queue of customers
        // declare an expressServer here
        /* add code here */
        Server expressServer = new Server();

        int totalWaitingTime = 0;                   // initialize total waiting time

        for (int i = 0; i < regularServer.length; i++) {
            regularServer[i] = new Server();
        }

        int selectedServer = -1;
        
        // loop to simulate elapsing time
        for (currentSecond = 0; currentSecond < MAX_TIME_FOR_SIMULATION; currentSecond++) {
            // everything inside this loop is for 1 second of time

            // has someone arrived at this second?
            if (Math.random() < PROBABILITY_OF_ARRIVAL) {
                // a customer arrived - count them
                numCustomersArrived++;

                // 'create' this newCustomer with an estimate of time to take
                Customer newCustomer = new Customer(currentSecond, ((int) (Math.random() * MAX_TIME_TO_CHECKOUT)));
                
                /* add code here */
                 if (newCustomer.getInitialWorkToDo() <= 60) {
                    // stick this new customer on express Server's customerArrivalQueue queue
                    expressServer.addCustomer(newCustomer);
                 } else {
                    // otherwise 
                    // find a free server.  If no server is free, find the server with the shortest line
                    for (int i = 0; i < regularServer.length; i++) {
                      if (regularServer[i].isFree()) {
                        selectedServer = i;
                        break;
                      }
                      if (selectedServer == -1 || regularServer[i].queueSize() < regularServer[selectedServer].queueSize()) {
                        selectedServer = i;
                      }
                    }
                    // stick this new customer on Server's customerArrivalQueue queue
                    regularServer[selectedServer].addCustomer(newCustomer);
                    // display customer's arrival
                    System.out.println("Regular Server(" + selectedServer + "): At time " + currentSecond + " a customer arrived. "
                        + "work will be " + newCustomer.getInitialWorkToDo());
                }
                
            }

            // for this second, is a regular server free and someone waiting?
            for (int serverNum = 0; serverNum < regularServer.length; serverNum++) {
                if (regularServer[serverNum].isFree() && regularServer[serverNum].queueSize() > 0) {
                    regularServer[serverNum].serveCustomer();             // mark the 'server' as busy

                    // accumulate waiting time---note: the time this customer waited is: currentSecond - customerBeingServedArrivalTime
                    totalWaitingTime += currentSecond - regularServer[serverNum].currentCustomerArrivalTime();

                    // display working info
                    System.out.println("Regular Server(" + serverNum + "): At " + currentSecond
                            + " started working on customer who arrived at "
                            + regularServer[serverNum].currentCustomerArrivalTime()
                            + " -- work remaining is " + regularServer[serverNum].customerWorkToDoRemaining());
                }
            }

            // for this second, is an express server free and someone waiting?
            /* add code here */
            if (expressServer.isFree() && expressServer.queueSize() > 0) {
              expressServer.serveCustomer();
              // accumulate waiting time---note: the time this customer waited is: currentSecond - customerBeingServedArrivalTime
              totalWaitingTime += currentSecond - expressServer.currentCustomerArrivalTime();

              // display working info
              System.out.println("Express Server: At " + currentSecond
                      + " started working on customer who arrived at "
                      + expressServer.currentCustomerArrivalTime()
                      + " -- work remaining is " + expressServer.customerWorkToDoRemaining());
            }

            // for this second, if a regular server is working on a customer then decrease work to do by 1 second
            for (int serverNum = 0; serverNum < regularServer.length; serverNum++) {
                if (regularServer[serverNum].isNotFree()) {
                    // decrement work to do by 1 second
                    regularServer[serverNum].doWork();

                    // are we done with customer?
                    if (regularServer[serverNum].isFree()) {
                        System.out.println("Regular Server(" + serverNum + "): At " + currentSecond + " server free!");
                    }
                }
            }

            // for this second, if an express server is working on a customer then decrease work to do by 1 second
            /* add code here */
            if (expressServer.isNotFree()) {
                // decrement work to do by 1 second
                expressServer.doWork();

                // are we done with customer?
                if (expressServer.isFree()) {
                    System.out.println("Express Server: At " + currentSecond + " server free!");
                }
            }

            // for this second, if it is the last second, announce last second of simulation
            if (currentSecond == (MAX_TIME_FOR_SIMULATION - 1)) {
                // note: this section exists incase we "stay open" beyond MAX_TIME_FOR_SIMULATION
                System.out.println("At " + currentSecond + " \t\t\t\t\t\t\tWe are closed");
                // Display the number of customers stuck on line (still in Queue)
                for (int serverNum = 0; serverNum < regularServer.length; serverNum++) {
                    System.out.println("\t\t\t\t\t\tRegular Server(" + serverNum
                            + "): number of customers stuck on line: " + regularServer[serverNum].queueSize());
                }
                // announce Express Server: number of customers stuck on line:
                /* add code here */
                System.out.println("\t\t\t\t\t\tExpress Server: number of customers stuck on line: " + expressServer.queueSize());
            }
        } // for loop end

        System.out.println("\nAt " + currentSecond + " Simulation ended");

        // display statistics from simulation
        // display number of customers that arrived
        System.out.println("\n" + numCustomersArrived + " customers arrived");

        // Display the counter for the number of customers served by regular servers
        for (int serverNum = 0; serverNum < regularServer.length; serverNum++) {
            System.out.println("Number of customers served by Regular Server(" + serverNum
                    + "): " + regularServer[serverNum].getCustomersServed());
        }
        
        // Display the counter for the number of customers served by express servers
        /* add code here */
        System.out.println("Number of customers served by Express Server: " + expressServer.getCustomersServed());

        // Display the total wait time that ALL customers served had waiting on line
        System.out.println("Total Wait Time of customers Served: " + totalWaitingTime);

        int totalRegular = 0;
        for (int serverNum = 0; serverNum < regularServer.length; serverNum++) {
            totalRegular += regularServer[serverNum].getCustomersServed();
        }
        // Display the average wait time that the customers served had to wait on line
        System.out.printf("Average Wait Time of ALL customers Served: %,.1f\n",
                ((double) totalWaitingTime / (totalRegular + expressServer.getCustomersServed())));  // include with totalRegular the expressServers customers served
        /* add code here */

        // Display the number of customers stuck on regular lines (still in Queue)
        for (int serverNum = 0; serverNum < regularServer.length; serverNum++) {
            System.out.println("Regular Server(" + serverNum + "): number of customers stuck on line: "
                    + regularServer[serverNum].queueSize());
        }
        // Display the number of customers stuck on express lines (still in Queue)
        /* add code here */
        System.out.println("Express Server: number of customers stuck on line: "
                    + expressServer.queueSize());
    }
    
}
