//Peter Alonzo: Final Project
import java.io.File;                                                                //Imported in order to open up txt files
import java.io.FileNotFoundException;                                               //Imported in order to iterate through file
import java.util.Scanner;                                                           //Imported to take in user input
public class DataContainerTestDriver
{
    public static void main(String [] args)
    {
        Scanner scan = new Scanner(System.in);                                      //Will be used to take in user input
        TitleInventory inventory = new TitleInventory();                            //Will hold all NetflixTitle objects
        System.out.print("Please enter the name of the input file: ");
        String name = scan.nextLine();                                              //Name of file that holds all object values
        Scanner iterate = null;                                                     //Scanner used to iterate through file line by line
        try
        {
            iterate = new Scanner(new File(name));                                  //Try block attempts to open up file
        }
        catch (FileNotFoundException e)                                             //Will exit program if a FileNotFoundException is thrown
        {
            System.exit(0);
        }
        while (iterate.hasNextLine())                                               //Will continue running until file is empty
        {
            String[] data = iterate.nextLine().split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);  //Splits up elements of every title into an array of Strings (ignores commas in between quotes)
            if (data[0].equals("show_id"))                                          //Used to skip the first line since it isn't a title
            {
                continue;
            }
            int year = Integer.parseInt(data[5]);                                   //Casts releaseYear to an integer
            int duration = Integer.parseInt(data[7].split(" ")[0]);                 //Casts title duration to an integer
            String [] directors = data[3].replace("\"", "").split(", ");            //Converts directors into a String array
            String [] countries = data[4].replace("\"", "").split(", ");            //Converts countries into a String array
            String [] genres = data[8].replace("\"", "").split(", ");               //Converts genres into a String array
            if (data[1].equals("Movie"))                                            //Creates a NetflixMovie object if it is a Movie type
            {

                NetflixMovie temp = new NetflixMovie(data[0], data[2], year, data[6], duration, directors, countries, genres);
                inventory.addTitle(temp);                                           //Adds created object to inventory
            }
            else                                                                    //Creates a NetflixTVShow object if it is a TV Show type
            {
                NetflixTVShow temp = new NetflixTVShow(data[0], data[2], year, data[6], duration, directors, countries, genres);
                inventory.addTitle(temp);                                           //Adds created object to inventory
            }
        }
        System.out.print("Options:\n1. Add a title\n2. Delete a title\n3. Search for titles\n4. Modify a title\n");
        System.out.print("Type Exit to end program, 1-4 otherwise: ");
        String response = scan.nextLine();                                          //Stores initial action from user into response
        while(!response.equalsIgnoreCase("Exit"))                                   //Will continuously prompt user for more actions until they type Exit
        {
            if (response.equals("1"))                                                                           //Add a title case
            {
                System.out.print("\nEnter the Type (Movie or TV Show): ");                                      //Asks user if the title is a Movie or TV Show
                String addType = scan.nextLine();
                System.out.print("Enter the id of your " + addType + ": ");                                     //Gathers information regarding the title's id
                String id = scan.nextLine();
                System.out.print("Enter the title of your " + addType + ": ");                                  //Stores the title of the user's added title
                String title = scan.nextLine();
                System.out.print("Enter the " + addType + "'s directors (separated by \", \"): ");              //Prompts and stores directors of user's added title as a list
                String [] directors = scan.nextLine().split(", ");
                System.out.print("Enter the " + addType + "'s countries (separated by \", \"): ");              //Prompts and stores countries of user's added title as a list
                String [] countries = scan.nextLine().split(", ");
                System.out.print("Enter the release year: ");                                                   //Casts the release year entered and stores it in yearAdded
                int yearAdded = Integer.parseInt(scan.nextLine());
                System.out.print("Enter the rating: ");                                                         //Prompts and stores the rating
                String rating = scan.nextLine();
                System.out.print("Enter the duration (min if Movie or seasons if TV Show): ");                  //Prompts and stores the duration of user's added title
                int duration = Integer.parseInt(scan.nextLine());
                System.out.print("Enter the " + addType + "'s genres (separated by \", \"): ");                 //Prompts and stores genres of user's added title as a list
                String [] genres = scan.nextLine().split(", ");
                if (addType.equals("Movie"))                                                                    //Creates a NetflixMovie object if it is a Movie type
                {
                    NetflixMovie temp = new NetflixMovie(id, title, yearAdded, rating, duration, directors, countries, genres);
                    inventory.addTitle(temp);                                                                   //Adds created object to inventory
                }
                else                                                                                            //Creates a NetflixTVShow object if it is a TV Show type
                {
                    NetflixTVShow temp = new NetflixTVShow(id, title, yearAdded, rating, duration, directors, countries, genres);
                    inventory.addTitle(temp);                                                                   //Adds created object to inventory
                }
                inventory.updateFile(name);                                                                     //Updates input file with newly added title
                System.out.print(title + " has been added to the collection!\n");
            }
            else if (response.equals("2"))                                                                      //Delete a title case
            {
                String input = " ";                                                                             //Initializes input to a space to start future while loop
                int index = 0, oldStatus = 0, newStatus = 0;                                                    //Variables needed to properly run the display method
                System.out.print("\nThe list of all the Movies will show first, followed by the list of shows");
                while(input.equals(" "))                                                                        //Will continue to loop until user enters an index
                {
                    newStatus = inventory.showTitles(index, index + 1, oldStatus);                              //Updates newStatus and displays titles based on index and the oldStatus
                    if (oldStatus == 0)                                                                         //Indicates user can be told to hit space or enter a number still
                    {
                        index++;                                                                                //Increments index accordingly
                        System.out.print("\nHit space bar to see more, or type the number of the title you would like to remove: ");
                    }
                    oldStatus = newStatus;                                                                      //oldStatus value is updated after checking its value
                    input = scan.nextLine();                                                                    //Checks for next input from user
                }
                int removeIndex = Integer.parseInt(input);                                                      //Converts input from user into desired index
                inventory.deleteTitle(removeIndex);                                                             //Deletes the desired object from the inventory
                inventory.updateFile(name);                                                                     //Updates input file and does not include deleted title
            }
            else if(response.equals("3"))                                                                       //Search for titles case
            {
                System.out.print("\nAre you looking for a Movie or a TV Show? ");
                String type = scan.nextLine();                                                                  //Stores the choice between "Movie" and "TV Show"
                System.out.print("Which attribute are you searching based on?");
                System.out.print("\n1. Rating\n2. Director\n3. Genre\n4. Duration\n5. Country\n6. Year\n");
                System.out.print("Enter the number of your attribute (1-6): ");
                String attribute = scan.nextLine();                                                             //Stores the choice of attribute
                TitleInventory elements;                                                                        //Will be used to gather all titles with the specified parameter
                if (attribute.equals("1"))                                                                      //Case 1: Search by Rating
                {
                    inventory.displayRatings(type);                                                             //Prints all possible values for ratings given the object type (Movie or TV Show)
                    String rating = scan.nextLine();                                                            //Stores user's choice
                    elements = new TitleInventory(inventory.searchRating(rating, type));                        //Makes an inventory with ratings returned from searchRating method
                }
                else if (attribute.equals("2"))                                                                 //Case 2: Search by Director
                {
                    inventory.displayDirectors(type);                                                           //Prints all possible values for directors given the object type (Movie or TV Show)
                    String director = scan.nextLine();                                                          //Stores user's choice
                    elements = new TitleInventory(inventory.searchDirector(director, type));                    //Makes an inventory with directors returned from searchDirector method
                }
                else if (attribute.equals("3"))                                                                 //Case 3: Search by Genre
                {
                    inventory.displayGenres(type);                                                              //Prints all possible values for genres given the object type (Movie or TV Show)
                    String genre = scan.nextLine();                                                             //Stores user's choice
                    elements = new TitleInventory(inventory.searchGenre(genre, type));                          //Makes an inventory with genres returned from searchGenre method
                }
                else if (attribute.equals("4"))                                                                 //Case 4: Search by Duration
                {
                    inventory.displayDurations(type);                                                           //Prints all possible values for duration given the object type (Movie or TV Show)
                    int range = Integer.parseInt(scan.nextLine());                                              //Stores user's choice
                    elements = new TitleInventory(inventory.searchDuration(range, type));                       //Makes an inventory with durations returned from searchDuration method
                }
                else if (attribute.equals("5"))                                                                 //Case 5: Search by Country
                {
                    inventory.displayCountries(type);                                                           //Prints all possible values for countries given the object type (Movie or TV Show)
                    String country = scan.nextLine();                                                           //Stores user's choice
                    elements = new TitleInventory(inventory.searchCountry(country, type));                      //Makes an inventory with countries returned from searchCountry method
                }
                else                                                                                            //Case 6: Search by Year
                {
                    inventory.displayYears(type);                                                               //Prints all possible values for years given the object type (Movie or TV Show)
                    int year = Integer.parseInt(scan.nextLine());                                               //Stores user's choice
                    elements = new TitleInventory(inventory.searchYear(year, type));                            //Makes an inventory with years returned from searchYear method
                }
                elements.printTitles();                                                                         //Prints all elements from that inventory
            }
            else                                                                                                //Modify a title case
            {
                String input = " ";                                                                             //Initializes input to a space to start future while loop
                int index = 0, oldStatus = 0, newStatus = 0;                                                    //Variables needed to properly run the display method
                System.out.print("\nThe list of all the Movies will show first, followed by the list of shows");
                while(input.equals(" "))                                                                        //Will continue to loop until user enters an index
                {
                    newStatus = inventory.showTitles(index, index + 1, oldStatus);                              //Updates newStatus and displays titles based on index and the oldStatus
                    if (oldStatus == 0)                                                                         //Indicates user can be told to hit space or enter a number still
                    {
                        index++;                                                                                //Increments index accordingly
                        System.out.print("\nHit space bar to see more, or type the number of the title you would like to change the rating of: ");
                    }
                    oldStatus = newStatus;                                                                      //oldStatus value is updated after checking its value
                    input = scan.nextLine();                                                                    //Checks for next input from user
                }
                int changeIndex = Integer.parseInt(input);                                                      //Converts input from user into desired index
                System.out.print("\nEnter the new rating for the title you chose: ");                           //Prompts user for the new rating
                String newRating = scan.nextLine();
                String modifiedTitle = inventory.changeRating(changeIndex, newRating);                          //Updates rating of the specified title
                inventory.updateFile(name);                                                                     //Updates input file with modified title
                System.out.print("The rating for " + modifiedTitle + " is now " + newRating + "!\n");           //Notifies user of the update
            }
            System.out.print("\nOptions:\n1. Add a title\n2. Delete a title\n3. Search for titles\n4. Modify a title\n");
            System.out.print("Type Exit to end program, 1-4 otherwise: ");
            response = scan.nextLine();                                                                         //Gathers user's next choice in action
        }
    }
}
