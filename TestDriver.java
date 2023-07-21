//Peter Alonzo: Final Project
import java.util.Scanner;                                                         //imported scanner used to take in user input
public class TestDriver                                                           //Test Driver for this project (tests every method and class)
{
    public static void main (String [] args)
    {
        NetflixTitle test;                                                        //instantiates a NetflixTitle object to be populated by user
        Scanner scan = new Scanner(System.in);                                    //Initializes scanner variable called scan
        System.out.print("Do you want to build a movie or a TV show? Enter \"TV Show\" or \"Movie\" to continue: ");
        String type = scan.nextLine();                                            //Type represents user's choice between Movie and TV Show
        System.out.print("Enter the id for your " + type + ": ");
        String id = scan.nextLine();                                              //Id is the id value of the particular Netflix title
        System.out.print("Enter the name for your " + type + ": ");
        String name = scan.nextLine();                                            //Name is the name of that particular title
        System.out.print("Enter the year of release for your " + type + ": ");
        int releaseYear = Integer.parseInt(scan.nextLine());                      //Stores the release year of the desired title
        System.out.print("Enter the directors for your " + type + " as a comma-separated list: ");
        String [] directors = scan.nextLine().split(",");                   //Initializes a list of all contributing directors
        System.out.print("Enter the countries for your " + type + " as a comma-separated list: ");
        String [] countries = scan.nextLine().split(",");                   //Initializes a list of all associated countries
        System.out.print("Enter the genres for your " + type + " as a comma-separated list: ");
        String [] genres = scan.nextLine().split(",");                      //Initializes a list of all relevant genres
        System.out.print("Enter the duration for your " + type + ": ");
        int duration = Integer.parseInt(scan.nextLine());                         //Stores duration of desired title (seasons for show, minutes for movie)
        System.out.print("Enter the rating for your " + type + ": ");
        String rating = scan.nextLine();                                          //Stores rating for that particular title
        if (type.equalsIgnoreCase("Movie"))                            //Checks if user selected Movie in the beginning of the problem
        {
            test = new NetflixMovie(id, name, releaseYear, rating, duration, directors, countries, genres); //Creates a NetflixMovie object with all given parameters
            ((NetflixMovie)test).printMovieInfo();                                //casts NetflixTitle object to NetflixMovie in order to access printMovieInfo()
        }
        else                                                                      //Implies user wanted a TV show
        {
            test = new NetflixTVShow(id, name, releaseYear, rating, duration, directors, countries, genres); //Creates a NetflixTVshow object with all given parameters
            ((NetflixTVShow)test).printTVInfo();                                  //casts NetflixTitle object to NetflixTVShow in order to access printTVInfo()
        }
        System.out.print("\nDo you wish to change an attribute? ");               //Asks if user wants to change a particular attribute
        String answer = scan.nextLine();                                          //Stores their yes/no answer to the previous question
        if (answer.equalsIgnoreCase("yes"))                            //Checks if user wants to change an attribute
        {
            System.out.print("Which attribute do you want to change? ");          //Asks which attribute the user wants to change
            answer = scan.nextLine();                                             //Stores their selection
            if (answer.equalsIgnoreCase("id"))                         //Case 1: User wants to change the id
            {
                System.out.print("Old value for id: " + test.getId());            //Prints old id value using getter method
                System.out.print("\nNew id is: ");
                test.setId(scan.nextLine());                                      //Uses setter method to change the id of the object
            }
            else if (answer.equalsIgnoreCase("name"))                  //Case 2: User wants to change the name
            {
                System.out.print("Old value for name: " + test.getName());        //Prints old name value using getter method
                System.out.print("\nNew name is: ");
                test.setTitleName(scan.nextLine());                               //Uses setter method to change the name of the object
            }
            else if (answer.equalsIgnoreCase("year"))                  //Case 3: User wants to change the year
            {
                System.out.print("Old value for year: " + test.getReleaseYear()); //Prints old year using getter method
                System.out.print("\nNew year is: ");
                test.setReleaseYear(Integer.parseInt(scan.nextLine()));           //Uses setter method to change the year of the object
            }
            else if (answer.equalsIgnoreCase("duration"))              //Case 4: User wants to change the duration
            {
                if (type.equalsIgnoreCase("Movie"))                    //Checks if user is dealing with a Movie
                {
                    System.out.print("Old value for duration: " + ((NetflixMovie)test).getDuration()); //Calls movie-specific getter method for duration
                    System.out.print("\nNew duration is: ");
                    ((NetflixMovie)test).setDuration(Integer.parseInt(scan.nextLine())); //Uses setter method to change the duration of the NetflixMovie
                }
                else                                                              //Implies the object is a NetflixTVShow
                {
                    System.out.print("Old value for duration: " + ((NetflixTVShow)test).getSeasons()); //Calls show-specific getter method for seasons
                    System.out.print("\nNew duration is: ");
                    ((NetflixTVShow)test).setSeasons(Integer.parseInt(scan.nextLine())); //Uses setter method to change the seasons of the NetflixTVShow
                }
            }
            else if (answer.equalsIgnoreCase("rating"))                //Case 5: User wants to change the rating
            {
                if (type.equalsIgnoreCase("Movie"))                    //Checks if user is dealing with a Movie
                {
                    System.out.print("Old value for rating: " + ((NetflixMovie)test).getRating()); //Calls movie-specific getter method for rating
                    System.out.print("\nNew rating is: ");
                    ((NetflixMovie)test).setRating(scan.nextLine());              //Uses setter method to change the rating of the NetflixMovie
                }
                else                                                              //Implies the object is a NetflixTVShow
                {
                    System.out.print("Old value for rating: " + ((NetflixTVShow)test).getRating()); //Calls show-specific getter method for rating
                    System.out.print("\nNew rating is: ");
                    ((NetflixTVShow)test).setRating(scan.nextLine());             //Uses setter method to change the rating of the NetflixTVShow
                }
            }
            else if (answer.equalsIgnoreCase("directors"))             //Case 6: User wants to change the directors
            {
                System.out.print("Old value for directors: " + test.getDirectors()); //Prints old value for directors using getter method
                System.out.print("\nEnter new directors as a comma-separated list: ");
                test.setDirectors(scan.nextLine().split(","));              //Uses setter method to change the list of directors
            }
            else if (answer.equalsIgnoreCase("countries"))             //Case 7: User wants to change the countries
            {
                System.out.print("Old value for countries: " + test.getCountries()); //Prints old value for countries using getter method
                System.out.print("\nEnter new countries as a comma-separated list: ");
                test.setCountries(scan.nextLine().split(","));              //Uses setter method to change the list of countries
            }
            else                                                                  //Case 8: User wants to change the genres
            {
                System.out.print("Old value for genres: " + test.getGenres());    //Prints old value for genres using getter method
                System.out.print("\nEnter new genres as a comma-separated list: ");
                test.setGenres(scan.nextLine().split(","));                 //Uses setter method to change the list of genres
            }
            if (type.equalsIgnoreCase("Movie"))                        //Once attribute is changed, check if object is a NetflixMovie
            {
                ((NetflixMovie)test).printMovieInfo();                            //Calls movie-specific printInfo method to display updated information
            }
            else                                                                  //Implies object is a NetflixTVShow
            {
                ((NetflixTVShow)test).printTVInfo();                              //Calls show-specific printInfo method to display updated information
            }
        }
    }
}
